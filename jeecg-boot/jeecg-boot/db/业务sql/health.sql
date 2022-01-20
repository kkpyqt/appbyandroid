DROP TABLE IF EXISTS `hm_measure_info`;
CREATE TABLE `hm_measure_info` (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新日期',
  `user_id` varchar(36) DEFAULT NULL COMMENT '用户ID',
  `device_` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '设备类型',
  `type` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '测量类型 (每个设备有不同的测量类型)',
  `value` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '测量值',
  `unit` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '测量单位',
  `set_macadd` varchar(45) DEFAULT NULL COMMENT '设备mac',
  `set_IMEI` varchar(45) DEFAULT NULL COMMENT '设备IMEI',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '删除标志',
  `version` int(11) DEFAULT NULL COMMENT '版本号',
  `level_type` int(4) DEFAULT NULL COMMENT '结果类型',
  `tips_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '结果建议',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1369897483439792131 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='测量信息';


DROP TABLE IF EXISTS `hm_version_info`;
CREATE TABLE `hm_version_info` (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建日期',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新日期',
  `versionCode` tinyint(4) DEFAULT NULL COMMENT '当前版本',
  `versionName` varchar(25) COLLATE utf8_bin DEFAULT NULL COMMENT '版本名称',
  `content` text COLLATE utf8_bin COMMENT '更新内容描述',
  `minSupport` tinyint(4) DEFAULT NULL COMMENT '最小支持版本',
  `url` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '下载地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='版本更新记录';



  DROP TABLE IF EXISTS `hm_user_set_rel`;
CREATE TABLE `hm_user_set_rel` (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新日期',
  `user_id` varchar(36) DEFAULT NULL COMMENT '用户ID',
	`band_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '厂家名称',
  `device_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '设备类型',
  `set_macadd` varchar(45) DEFAULT NULL COMMENT '设备mac',
  `set_IMEI` varchar(45) DEFAULT NULL COMMENT '设备IMEI',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '删除标志',

  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1369897483439792131 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户设备关系';


 DROP TABLE IF EXISTS `hm_user_set_list`;
CREATE TABLE `hm_user_set_list` (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新日期',
  `user_id` varchar(36) DEFAULT NULL COMMENT '用户ID',
	`set_user_name` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT '' COMMENT '设备用户名称',
    `height` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '身高',
  `weight` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '体重',
  `health` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '健康状况',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '删除标志',
	`birthday` date DEFAULT NULL COMMENT '出生日期',

  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1369897483439792131 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='设备用户清单';

SET FOREIGN_KEY_CHECKS = 1;

