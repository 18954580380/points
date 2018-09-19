drop table if exists koboro_empirical_value_item;
CREATE TABLE `koboro_empirical_value_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '经验值项目设置表',
  `points_item_name` varchar(255) NOT NULL COMMENT '积分项目名称',
  `item_id` int(11) DEFAULT NULL COMMENT '积分项目id,如果是任务同步到积分后台，可存储任务ID',
  `reward_period` tinyint(4) NOT NULL DEFAULT '1' COMMENT '奖惩周期 1：每天 2：每次 3：每小时',
  `reward_number` int(11) NOT NULL DEFAULT '0' COMMENT '奖惩次数',
  `empirical_value` int(11) NOT NULL DEFAULT '0' COMMENT '经验值xp',
  `points_item_source` tinyint(4) NOT NULL COMMENT '积分项目来源 1:任务模块 2：圈子模块',
  `set_date` datetime NOT NULL COMMENT '设定日期',
  `item_description` text COMMENT '项目说明',
  PRIMARY KEY (`id`,`reward_period`),
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8


