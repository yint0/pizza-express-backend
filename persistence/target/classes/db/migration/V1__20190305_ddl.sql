SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`
(
  `id`       int(11)                                                 NOT NULL AUTO_INCREMENT,
  `account`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `name`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for delivery_clerk
-- ----------------------------
DROP TABLE IF EXISTS `delivery_clerk`;
CREATE TABLE `delivery_clerk`
(
  `id`         int(11)                                                 NOT NULL AUTO_INCREMENT,
  `name`       varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `telephone`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话',
  `factory_id` int(11)                                                 NOT NULL COMMENT '所属工厂id',
  `status`     tinyint(255)                                            NOT NULL DEFAULT 1 COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `factory_id` (`factory_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for dishes
-- ----------------------------
DROP TABLE IF EXISTS `dishes`;
CREATE TABLE `dishes`
(
  `id`          int(11)                                                  NOT NULL AUTO_INCREMENT,
  `name`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci  NOT NULL COMMENT '名称',
  `description` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '描述',
  `cost_price`  decimal(10, 2)                                           NOT NULL COMMENT '成本',
  `sale_price`  decimal(10, 2)                                           NOT NULL COMMENT '售价',
  `status`      tinyint(2)                                               NOT NULL DEFAULT 1 COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for dishes_content
-- ----------------------------
DROP TABLE IF EXISTS `dishes_content`;
CREATE TABLE `dishes_content`
(
  `dishes_id`   int(11)        NOT NULL COMMENT '菜品id',
  `material_id` int(11)        NOT NULL COMMENT '材料id',
  `use_count`   double(255, 0) NOT NULL DEFAULT 0 COMMENT '用量',
  PRIMARY KEY (`dishes_id`, `material_id`) USING BTREE,
  INDEX `material_id` (`material_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for factory
-- ----------------------------
DROP TABLE IF EXISTS `factory`;
CREATE TABLE `factory`
(
  `id`            int(11)                                                 NOT NULL AUTO_INCREMENT,
  `name`          varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `head_portrait` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '头像',
  `address`       text CHARACTER SET utf8 COLLATE utf8_general_ci         NOT NULL COMMENT '地址',
  `telephone`     varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '电话',
  `open_time`     time(0)                                                 NOT NULL COMMENT '开门时间',
  `close_time`    time(0)                                                 NOT NULL COMMENT '停业时间',
  `max_quantity`  int(11)                                                 NOT NULL COMMENT '最大接单量',
  `status`        tinyint(2)                                              NOT NULL DEFAULT 1 COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for inventory
-- ----------------------------
DROP TABLE IF EXISTS `inventory`;
CREATE TABLE `inventory`
(
  `material_id` int(11) NOT NULL COMMENT '原料id',
  `factory_id`  int(11) NOT NULL COMMENT '工厂id',
  `count`       double  NOT NULL DEFAULT 0 COMMENT '原料数量',
  PRIMARY KEY (`material_id`, `factory_id`) USING BTREE,
  INDEX `factory_id` (`factory_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for material
-- ----------------------------
DROP TABLE IF EXISTS `material`;
CREATE TABLE `material`
(
  `id`        int(11)                                                 NOT NULL AUTO_INCREMENT,
  `name`      varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `threshold` int(11)                                                 NOT NULL DEFAULT 0 COMMENT '进价阈值',
  `status`    tinyint(2)                                              NOT NULL DEFAULT 1 COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for material_log
-- ----------------------------
DROP TABLE IF EXISTS `material_log`;
CREATE TABLE `material_log`
(
  `id`           int(11)                                         NOT NULL AUTO_INCREMENT,
  `admin_id`     int(11)                                         NOT NULL COMMENT '管理员id',
  `material_id`  int(11)                                         NOT NULL COMMENT '材料id',
  `factory_id`   int(11)                                         NOT NULL COMMENT '工厂id',
  `operate_time` datetime(0)                                     NOT NULL COMMENT '操作时间',
  `content`      text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '操作内容',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `admin_id` (`admin_id`) USING BTREE,
  INDEX `material_id` (`material_id`) USING BTREE,
  INDEX `factory_id` (`factory_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`
(
  `id`                int(11)                                                  NOT NULL AUTO_INCREMENT,
  `uuid`              varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci   NOT NULL COMMENT '识别码',
  `user_id`           int(11)                                                  NOT NULL COMMENT '客户id',
  `status`            tinyint(2)                                               NOT NULL COMMENT '订单状态',
  `cost_price`        decimal(10, 2)                                           NOT NULL DEFAULT 0.00 COMMENT '成本',
  `sale_price`        decimal(10, 2)                                           NOT NULL COMMENT '售价',
  `factory_id`        int(11)                                                  NOT NULL COMMENT '工厂id',
  `delivery_clerk_id` int(11)                                                  NOT NULL COMMENT '配送员id',
  `remark`            varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评价',
  `create_time`       datetime(0)                                              NOT NULL COMMENT '创建时间',
  `address`           varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '配送地址',
  `delivery_time`     datetime(0)                                                       DEFAULT NULL COMMENT '送达时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id` (`user_id`) USING BTREE,
  INDEX `factory_id` (`factory_id`) USING BTREE,
  INDEX `delivery_clerk_id` (`delivery_clerk_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for order_dishes
-- ----------------------------
DROP TABLE IF EXISTS `order_dishes`;
CREATE TABLE `order_dishes`
(
  `order_id`  int(11) NOT NULL COMMENT '订单号',
  `dishes_id` int(11) NOT NULL COMMENT '菜品号',
  `count`     int(11) NOT NULL DEFAULT 0 COMMENT '数量',
  PRIMARY KEY (`order_id`, `dishes_id`) USING BTREE,
  INDEX `dishes_id` (`dishes_id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for order_log
-- ----------------------------
DROP TABLE IF EXISTS `order_log`;
CREATE TABLE `order_log`
(
  `id`            int(11)                                                  NOT NULL AUTO_INCREMENT,
  `order_id`      int(11)                                                  NOT NULL COMMENT '订单号',
  `operator_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci   NOT NULL COMMENT '操作类型',
  `operator_id`   int(11)                                                  NOT NULL COMMENT '操作员id',
  `content_from`  varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '原始内容',
  `content_to`    varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改后内容',
  `operate_time`  datetime(0)                                              NOT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `order_id_index` (`order_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
  `id`        int(11) NOT NULL AUTO_INCREMENT,
  `account`   varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '账号',
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci  DEFAULT NULL COMMENT '手机号',
  `qq`        varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'qq',
  `wechat`    varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '微信号',
  `password`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '密码',
  `nick_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '昵称',
  `birthday`  date                                                    DEFAULT NULL COMMENT '生日',
  `address`   text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '收货地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
