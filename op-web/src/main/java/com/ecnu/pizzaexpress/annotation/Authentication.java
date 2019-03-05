package com.ecnu.pizzaexpress.annotation;


import com.ecnu.pizzaexpress.constants.Role;

import java.lang.annotation.*;


@Documented
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Authentication {
    Role[] value() default {Role.User, Role.Admin};
}
