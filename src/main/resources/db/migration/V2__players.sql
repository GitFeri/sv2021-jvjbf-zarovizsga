CREATE TABLE IF NOT EXISTS `players` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `name` varchar(50) COLLATE utf8_hungarian_ci DEFAULT NULL,
    `birth_date` date DEFAULT NULL,
    `position` varchar(50) COLLATE utf8_hungarian_ci DEFAULT NULL,
    `team` bigint(20) DEFAULT NULL,
    PRIMARY KEY (`id`),
    FOREIGN KEY (team) REFERENCES teams (id)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;
