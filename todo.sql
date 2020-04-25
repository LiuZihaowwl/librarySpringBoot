CREATE TABLE `todo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'Todo列表ID',
  `u_id` bigint(20) NOT NULL COMMENT '用户ID',
  `todolist` text NOT NULL COMMENT '用户登录类型',
  PRIMARY KEY (`id`),
  FOREIGN KEY (`u_id`) REFERENCES user(`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1029 DEFAULT CHARSET=utf8;s