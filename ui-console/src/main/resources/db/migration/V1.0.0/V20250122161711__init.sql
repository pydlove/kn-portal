CREATE TABLE `kn_menu`
(
    `id`             bigint NOT NULL AUTO_INCREMENT,
    `menu_name`      varchar(255) DEFAULT NULL,
    `menu_desc`      varchar(512) DEFAULT NULL,
    `parent_id`      bigint       DEFAULT NULL,
    `menu_level`     int          DEFAULT NULL,
    `order_no`       int          DEFAULT NULL,
    `create_time`    datetime     DEFAULT NULL,
    `update_time`    datetime     DEFAULT NULL,
    `version`        int          DEFAULT NULL,
    `create_uid`     bigint       DEFAULT NULL,
    `update_uid`     bigint       DEFAULT NULL,
    `deleted_status` int          DEFAULT NULL,
    `enabled_status` int          DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
