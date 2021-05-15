毕设说明
guide
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for guide
-- ----------------------------
DROP TABLE IF EXISTS `guide`;
CREATE TABLE `guide`  (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `guide_type` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `start_place` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `start_longitude` decimal(14, 6) NULL DEFAULT NULL,
  `start_latitude` decimal(14, 6) NULL DEFAULT NULL,
  `end_place` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `end_longitude` decimal(14, 6) NULL DEFAULT NULL,
  `end_latitude` decimal(14, 6) NULL DEFAULT NULL,
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `delete_time` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
  `deleted` int(2) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of guide
-- ----------------------------
INSERT INTO `guide` VALUES (6, 'user357', '0', '黑龙江省哈尔滨市香坊区木材街59', 126.732132, 45.741996, '黑龙江省哈尔滨市南岗区测绘路', 126.627930, 45.722060, '2021-05-14 19:50:59', NULL, NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `sex` int(2) NULL DEFAULT NULL COMMENT '性别',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `qq` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'qq',
  `telephone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `address` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `delete_time` datetime(0) NULL DEFAULT NULL COMMENT '删除时间',
  `deleted` int(2) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`, `name`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE COMMENT '登录用户名'
) ENGINE = InnoDB AUTO_INCREMENT = 74 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (3, 'admin', NULL, 'UdbrIn92e8s', '2680288483', '15186001530', NULL, '2021-02-15 16:47:37', '2021-02-16 10:00:41', NULL, 0);
INSERT INTO `t_user` VALUES (15, 'admin22', NULL, 'w0DMFrtOcSM', NULL, NULL, NULL, '2021-05-14 12:49:03', NULL, NULL, 0);
INSERT INTO `t_user` VALUES (34, 'user357', NULL, 'QddmU3Z0V4E', NULL, NULL, NULL, '2021-05-14 13:59:10', NULL, NULL, 0);
INSERT INTO `t_user` VALUES (40, 'aderf', NULL, 'QddmU3Z0V4E', NULL, NULL, NULL, '2021-05-14 14:06:19', NULL, NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;
