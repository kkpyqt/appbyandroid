#合同主表
DROP TABLE IF EXISTS `con_contract_head`;
CREATE TABLE `con_contract_head`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新日期',
  `sys_org_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属部门',
  `original_contract_num` varchar(50) DEFAULT NULL COMMENT '原始合同号',
  `contract_type` int(11) DEFAULT NULL COMMENT '合同类型',
  `customer_id` int(11) DEFAULT NULL COMMENT '客户ID',
  `customer_code` varchar(50) DEFAULT NULL COMMENT '客户编码',
  `contract_num` varchar(50) DEFAULT NULL COMMENT '合同号',
  `address` varchar(255) DEFAULT NULL COMMENT '服务地址',
  `region_name` varchar(255) DEFAULT NULL COMMENT '区域',
  `project_id` int(11) DEFAULT NULL COMMENT '项目名称',
  `recommend_name` varchar(50) DEFAULT NULL COMMENT '推荐人',
  `recommend_tel` varchar(50) DEFAULT NULL COMMENT '推荐人电话',
  `servics_demand` varchar(255) DEFAULT NULL COMMENT '注意事项',
  `status` int(11) DEFAULT NULL COMMENT '状态',         #草稿，审批，执行，暂停，关闭
  `amount` decimal(11,2) DEFAULT NULL COMMENT '合同金额',
  `images_url` varchar(255) DEFAULT NULL COMMENT '图片',
  `balance_status` int(11) DEFAULT NULL COMMENT '结算状态',   #未收，部分收款，全部收款

  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;
#合同交付明细
DROP TABLE IF EXISTS `con_contract_line`;
CREATE TABLE `con_contract_line`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新日期',
  `sys_org_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属部门',
   `contract_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '合同主键',
   `product_id` int(11) DEFAULT NULL COMMENT '商品(服务)名称',  #树形字典
  `standard` varchar(50) DEFAULT NULL COMMENT '规格',
  `request` varchar(255) DEFAULT NULL COMMENT '要求',
  `unit_price` decimal(10,2) DEFAULT NULL COMMENT '单价',
  `qty` int(11) DEFAULT NULL COMMENT '数量',
  `period_id` varchar(500) DEFAULT NULL COMMENT '周期',   #下拉字典
  `define_value` varchar(36) DEFAULT NULL COMMENT '指定时间',
  `start_date` datetime DEFAULT NULL COMMENT '开始日期',
  `end_date` datetime DEFAULT NULL COMMENT '结束日期',
  `finish_qty` int(11) DEFAULT NULL COMMENT '已完成数量',

  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic  COMMENT='合同表';
#合同收款期次
DROP TABLE IF EXISTS `con_contract_payment`;
CREATE TABLE `con_contract_payment`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新日期',
  `sys_org_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属部门',
   `contract_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '合同主键',
   `gathering_date` datetime DEFAULT NULL COMMENT '收款日期',
  `amount` decimal(10,2) DEFAULT NULL COMMENT '金额',

  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic  COMMENT='合同收款明细';
#客户主表
DROP TABLE IF EXISTS `con_customer`;
CREATE TABLE `con_customer`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新日期',
  `sys_org_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属部门',
   `customer_code` varchar(50) DEFAULT NULL COMMENT '客户编码',
    `customer_type` int(255) NOT NULL DEFAULT '0' COMMENT '客户类别',  #dict
  `weichat_openid` varchar(150) DEFAULT NULL COMMENT '微信id',
  `from_source` varchar(255) NOT NULL COMMENT '来源类型',
  `mobile_phone1` varchar(25) DEFAULT NULL COMMENT '电话号码',
  `mobile_phone2` varchar(25) DEFAULT NULL COMMENT '电话号码2',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
  `sex` varchar(255) DEFAULT NULL COMMENT '性别',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
  `tel` varchar(25) DEFAULT NULL COMMENT '座机电话',
  `id_number` varchar(25) DEFAULT NULL COMMENT '身份证号码',
  `soure_type` int(11) DEFAULT NULL COMMENT '来源',
  `area` varchar(255) DEFAULT NULL COMMENT '区域',
  `Community_address` varchar(255) DEFAULT NULL COMMENT '(小区)住址地址',


  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic COMMENT='联系人';

#人员联系人明细
dROP TABLE IF EXISTS `con_human`;
CREATE TABLE `con_human`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新日期',
  `sys_org_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属部门',
  `human_code` varchar(255) DEFAULT NULL COMMENT '编码',
  `name` varchar(255) DEFAULT NULL COMMENT '姓名',
	`weichat_openid` varchar(150) DEFAULT NULL COMMENT '微信id',
  `sex` varchar(255) DEFAULT NULL COMMENT '性别',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `birthday` date DEFAULT NULL COMMENT '出生日期',
	`mobile_phone1` varchar(25) DEFAULT NULL COMMENT '电话号码',
  `mobile_phone2` varchar(25) DEFAULT NULL COMMENT '电话号码2',
  `tel` varchar(25) DEFAULT NULL COMMENT '座机电话',
  `id_number` varchar(25) DEFAULT NULL COMMENT '身份证号码',
  `soure_type` int(11) DEFAULT NULL COMMENT '来源',
  `area` varchar(255) DEFAULT NULL COMMENT '区域',
  `address` varchar(255) DEFAULT NULL COMMENT '身份证地址',

  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `employ_date` datetime DEFAULT NULL COMMENT '入职日期',
  `resign_date` datetime DEFAULT NULL COMMENT '离职日期',
  `job_status` int(11) DEFAULT NULL COMMENT '工作类别', #dict
  `common_addr` varchar(255) DEFAULT NULL COMMENT '常用地址',
  `formal_date` datetime DEFAULT NULL COMMENT '转正日期',

  `nation` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '民族',
  `political` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '政治面貌',
  `native_place` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '籍贯',
  `height` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '身高',
  `weight` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '体重',
  `health` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '健康状况',

  `education` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '学历',
  `school` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '毕业学校',
  `major` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '专业',
  `contact_address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '联系地址',
  `zip_code` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮编',
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT 'Email',

  `foreign_language` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '外语语种',
  `foreign_language_level` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '外语水平',
  `computer_level` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '计算机水平',
  `graduation_time` datetime DEFAULT NULL COMMENT '毕业时间',
  `arrival_time` datetime DEFAULT NULL COMMENT '到职时间',

  `education_experience` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '教育经历',
  `work_experience` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '工作经历',

  `del_flag` tinyint(1) DEFAULT NULL COMMENT '删除标识0-正常,1-已删除',
  `human_state` int(11) DEFAULT '0' COMMENT '人员状态 ',  #0-不在职 1-入职 2-转正 3-离职 4-调岗
  `post_id` int(11) DEFAULT NULL COMMENT '岗位',

  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic COMMENT='员工';
#收付款单
dROP TABLE IF EXISTS `con_settle_head`;
CREATE TABLE `con_settle_head`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新日期',
  `sys_org_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '所属部门',
   `business_date` datetime DEFAULT NULL COMMENT '业务日期',
  `status` int(11) DEFAULT NULL COMMENT '状态',
	`project_id` int(11) DEFAULT NULL COMMENT '项目',
  `customer_id` int(11) DEFAULT NULL COMMENT '客户',
  `amount` decimal(11,2) DEFAULT NULL COMMENT '金额',
  `descript` varchar(50) DEFAULT NULL COMMENT '摘要',
	`settle_type` int(11) DEFAULT NULL COMMENT '收付类型',#dict

  `soure_bill_id` varchar(36)  DEFAULT NULL COMMENT '源单编号',
	`soure_bill_type` int(11) DEFAULT NULL COMMENT '源单类型',
  `handled_by` varchar(50) DEFAULT NULL COMMENT '经手人',

  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic  COMMENT='收付款单';

#收付款明细
dROP TABLE IF EXISTS `con_settle_LINE`;
CREATE TABLE `con_settle_LINE`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主键',
  `create_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_by` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新日期',

  `con_settle_id` varchar(36)  DEFAULT NULL COMMENT '源单编号',
	`soure_fund_type` int(11) DEFAULT NULL COMMENT '收款类型',  #dict
  `amount` decimal(11,2) DEFAULT NULL COMMENT '金额',

  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic  COMMENT='收付款明细' ;

#todo 人员新明细的字典 子模块构建2021-8-12