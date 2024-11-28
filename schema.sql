-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               11.5.2-MariaDB-ubu2404 - mariadb.org binary distribution
-- Server OS:                    debian-linux-gnu
-- HeidiSQL Version:             12.8.0.6908
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for weeb
DROP DATABASE IF EXISTS `weeb`;
CREATE DATABASE IF NOT EXISTS `weeb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `weeb`;

-- Dumping structure for table weeb.Board
DROP TABLE IF EXISTS `Board`;
CREATE TABLE IF NOT EXISTS `Board` (
  `id` int(10) unsigned NOT NULL DEFAULT 0,
  `title` varchar(256) NOT NULL,
  `content` longtext NOT NULL,
  `added` datetime NOT NULL DEFAULT current_timestamp(),
  `updated` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `userid` varchar(32) DEFAULT NULL,
  `status` int(2) unsigned NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for function weeb.create_board_id
DROP FUNCTION IF EXISTS `create_board_id`;
DELIMITER //
CREATE FUNCTION `create_board_id`() RETURNS int(11)
BEGIN
    DECLARE random_id INT;
    DECLARE found INT;

    -- Loop until a unique ID is found
    REPEAT
        -- Generate a random number between min_id and max_id
        SET random_id = FLOOR(RAND() * (1000000000 - 100 + 1)) + 100;

        -- Check if the generated ID exists in the Board table
        SELECT COUNT(*) INTO found
        FROM Board
        WHERE id = random_id;
    UNTIL found = 0
    END REPEAT;

    -- Return the unique random ID
    RETURN random_id;
END//
DELIMITER ;

-- Dumping structure for table weeb.Food
DROP TABLE IF EXISTS `Food`;
CREATE TABLE IF NOT EXISTS `Food` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(256) NOT NULL,
  `description` varchar(1024) NOT NULL,
  `wonPrice` bigint(20) unsigned NOT NULL,
  `status` int(2) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  CONSTRAINT `nameConstraint` CHECK (octet_length(`name`) > 0),
  CONSTRAINT `descriptionConstraint` CHECK (octet_length(`description`) > 0)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table weeb.FoodReview
DROP TABLE IF EXISTS `FoodReview`;
CREATE TABLE IF NOT EXISTS `FoodReview` (
  `foodId` bigint(20) unsigned NOT NULL,
  `userid` varchar(32) NOT NULL,
  `rate` int(1) unsigned NOT NULL DEFAULT 5,
  `added` datetime NOT NULL DEFAULT current_timestamp(),
  `updated` datetime NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`foodId`,`userid`) USING BTREE,
  CONSTRAINT `RateRange` CHECK (`rate` between 1 and 5)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table weeb.Patron
DROP TABLE IF EXISTS `Patron`;
CREATE TABLE IF NOT EXISTS `Patron` (
  `userid` varchar(32) NOT NULL,
  `username` varchar(32) NOT NULL,
  `secret` varbinary(256) NOT NULL,
  `email` varchar(256) NOT NULL,
  `added` datetime NOT NULL DEFAULT current_timestamp(),
  `status` int(2) NOT NULL DEFAULT 0,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table weeb.Task
DROP TABLE IF EXISTS `Task`;
CREATE TABLE IF NOT EXISTS `Task` (
  `id` bigint(20) unsigned NOT NULL DEFAULT 0,
  `title` varchar(300) NOT NULL,
  `content` longtext NOT NULL,
  `start` datetime NOT NULL DEFAULT current_timestamp(),
  `end` datetime NOT NULL DEFAULT (current_timestamp() + interval 3 day),
  `finished` int(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for trigger weeb.set_board_id_hash
DROP TRIGGER IF EXISTS `set_board_id_hash`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='STRICT_TRANS_TABLES,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `set_board_id_hash` BEFORE INSERT ON `Board` FOR EACH ROW BEGIN
    DECLARE is_duplicate BOOLEAN DEFAULT TRUE;
    DECLARE generated_id BIGINT;
    DECLARE attempts INT DEFAULT 0;
    DECLARE max_attempts INT DEFAULT 5;

    IF NEW.id = 0 THEN
        WHILE is_duplicate AND attempts < max_attempts DO
            -- Generate a hash-based ID using SHA2
            SET generated_id = CONV(LEFT(SHA2(CONCAT(NEW.title, NEW.content, NOW()), 256), 8), 16, 10);

            -- Check if the generated ID already exists in the Board table
            IF NOT EXISTS (SELECT 1 FROM Board WHERE id = generated_id) THEN
                SET is_duplicate = FALSE;
            ELSE
                -- Modify input slightly to create a new hash
                SET NEW.title = CONCAT(NEW.title, RAND());  -- Adding a random element to change the title
                SET attempts = attempts + 1;  -- Increment the attempt counter
            END IF;
        END WHILE;

        -- If after max attempts a unique ID is not found, raise an error
        IF attempts >= max_attempts THEN
            SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Unable to generate a unique ID after multiple attempts';
        END IF;

        -- Assign the unique ID
        SET NEW.id = generated_id;
    END IF;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
