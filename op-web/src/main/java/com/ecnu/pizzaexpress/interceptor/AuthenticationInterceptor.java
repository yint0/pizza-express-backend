package com.ecnu.pizzaexpress.interceptor;

import com.ecnu.pizzaexpress.annotation.Authentication;
import com.ecnu.pizzaexpress.constants.Role;
import com.ecnu.pizzaexpress.controller.BaseController;
import com.ecnu.pizzaexpress.dto.Token;
import com.ecnu.pizzaexpress.service.base.TokenService;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

@Service("authenticationInterceptor")
public class AuthenticationInterceptor extends BaseInterceptor {

  private static final Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);

  @Autowired
  private TokenService tokenService;

  @Override
  public boolean preHandle(
      HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse,
      Object handler)
      throws Exception {
    if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
      Set<Role> roles = new HashSet<>();
      HandlerMethod handlerMethod = (HandlerMethod) handler;
      Authentication authentication = ((HandlerMethod) handler)
          .getMethodAnnotation(Authentication.class);
      BaseController controller = null;
      if (authentication != null) {
        Collections.addAll(roles, authentication.value());
      }
      if (handlerMethod.getBean() instanceof BaseController) {
        controller = (BaseController) handlerMethod.getBean();
        authentication = controller.getClass().getAnnotation(Authentication.class);
        if (authentication != null) {
          Collections.addAll(roles, authentication.value());
        }
      }
      Token token = getToken(httpServletRequest);
      if (roles.isEmpty()) { // 没有声明需要权限,或者声明不验证权限
        if (token != null) {
          tokenService.setToken(token);
        }
        return true;
      } else {
        logger.info("token:" + token + " request:" + httpServletRequest.getRequestURI());
        if (token == null || token.isExpired()) {
          makeResponse(controller, httpServletRequest, httpServletResponse);
          return false;
        }
        tokenService.setToken(token);
        if (roles.contains(token.getRole())) {
          return true;
        } else {
          makeResponse(handlerMethod.getBean(), httpServletRequest, httpServletResponse);
          return false;
        }
      }
    } else {
      return true;
    }
  }

  @Override
  public void postHandle(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler,
      ModelAndView modelAndView)
      throws Exception {

  }

  private Token getToken(HttpServletRequest request) {
    String tokenString = getTokenFromHeaderOrCookie(request, "token");
    if (tokenString == null) {
      return null;
    }
    try {
      Token token = tokenService.parseToken(tokenString);
      return token;
    } catch (RuntimeException e) {
      return null;
    }
  }
}
