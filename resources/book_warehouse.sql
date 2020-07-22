-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               5.7.31-log - MySQL Community Server (GPL)
-- Операционная система:         Win32
-- HeidiSQL Версия:              11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Дамп структуры базы данных book_warehouse
CREATE DATABASE IF NOT EXISTS `book_warehouse` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `book_warehouse`;

-- Дамп структуры для таблица book_warehouse.books
CREATE TABLE IF NOT EXISTS `books` (
  `id_book` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `authors` varchar(45) NOT NULL,
  `year` int(11) NOT NULL,
  `pages` int(11) NOT NULL,
  `publisher` varchar(45) NOT NULL,
  PRIMARY KEY (`id_book`) USING BTREE,
  UNIQUE KEY `id_book` (`id_book`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

-- Дамп данных таблицы book_warehouse.books: ~4 rows (приблизительно)
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
REPLACE INTO `books` (`id_book`, `title`, `authors`, `year`, `pages`, `publisher`) VALUES
	(1, 'Lord of the rings', 'John Tolkien', 2002, 900, 'Vysnova'),
	(2, 'Harry Potter and the deathly hallows', 'Joan Roaling', 2007, 800, 'Delibri'),
	(3, 'The little golden calf', 'Ilya Ilf, Eugene Petrov', 2015, 416, 'Ishi Press'),
	(4, 'Harry Potter and the goblet of fire', 'Joan Roaling', 2005, 788, 'Minsk');
/*!40000 ALTER TABLE `books` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
