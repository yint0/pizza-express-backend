DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`
(
  `id`   int(11)                                                 NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  CHARACTER SET = utf8
  COLLATE = utf8_general_ci
  ROW_FORMAT = Dynamic;
ALTER TABLE `dishes`
  ADD COLUMN `type_id` int(0) NOT NULL AFTER `status`,
  ADD COLUMN `type_name` varchar(255) NOT NULL AFTER `type_id`;