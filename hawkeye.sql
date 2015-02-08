CREATE TABLE IF NOT EXISTS `hawkeye_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `player_id` int(11) NOT NULL,
  `action` varchar(20) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `date` timestamp NOT NULL,
  `world_id` tinyint(4) NOT NULL,
  `x` double(11,1) NOT NULL,
  `y` double(11,1) NOT NULL,
  `z` double(11,1) NOT NULL,
  `data` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
  `nbt` blob DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `player_action` (`player_id`, `action`),
  KEY `date` (`date`),
  KEY `location` (`world_id`, `x`, `y`, `z`),
  KEY `coordinates` (`x`, `y`, `z`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE utf8_unicode_ci;

-- --------------------------------------------------------

CREATE TABLE IF NOT EXISTS `hawkeye_players` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` char(36) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE utf8_unicode_ci;

-- --------------------------------------------------------

CREATE TABLE IF NOT EXISTS `hawkeye_worlds` (
  `id` tinyint(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=MyISAM  DEFAULT CHARSET=utf8 COLLATE utf8_unicode_ci;