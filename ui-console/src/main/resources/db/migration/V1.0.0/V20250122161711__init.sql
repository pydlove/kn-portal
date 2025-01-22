CREATE TABLE `t_test_info`
(
    `id`            bigint NOT NULL AUTO_INCREMENT,
    `create_time`   datetime     DEFAULT NULL,
    `update_time`   datetime     DEFAULT NULL,
    `create_uid`    bigint       DEFAULT NULL,
    `modify_uid`    bigint       DEFAULT NULL,
    `version`       int          DEFAULT NULL,
    `delete_status` int          DEFAULT NULL,
    `test_name`     varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;