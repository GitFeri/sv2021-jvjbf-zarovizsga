CREATE TABLE IF NOT EXISTS `teams` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `person_name` varchar(50) COLLATE utf8_hungarian_ci DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;
