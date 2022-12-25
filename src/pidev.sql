-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Dec 25, 2022 at 04:30 PM
-- Server version: 5.7.31
-- PHP Version: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `pidev`
--

-- --------------------------------------------------------

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
CREATE TABLE IF NOT EXISTS `categories` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `categories`
--

INSERT INTO `categories` (`id`, `title`, `description`) VALUES
(1, 'lala', 'monji'),
(2, 'la la 2', 'description'),
(3, 'google', 'description EEE'),
(4, 'la la 2', 'description'),
(5, 'la la 3', 'description EEE');

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
CREATE TABLE IF NOT EXISTS `comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `body` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `created` datetime DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `post_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_9474526CA76ED395` (`user_id`),
  KEY `IDX_9474526C4B89032C` (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `comment`
--

INSERT INTO `comment` (`id`, `body`, `created`, `user_id`, `post_id`) VALUES
(22, 'Cool!!', '2021-04-29 00:00:00', 6, 366),
(24, 'Awsome', '2021-04-29 00:00:00', 6, 367),
(26, 'IN NEW YORKKKK XD', '2021-04-29 00:00:00', 1, 368),
(27, 'Wonderful Tunisia. \nNice Trip', '2021-04-29 00:00:00', 4, 369),
(29, 'hey', '2021-05-03 00:00:00', 1, 367),
(30, 'chiheb', '2021-05-04 00:00:00', 6, 366),
(32, 'aaa', '2021-05-04 00:00:00', 7, 366),
(33, 'salut', '2021-05-22 00:00:00', 7, 383),
(36, 'salut!', '2021-05-22 23:15:52', 20, 384),
(37, 'heyy!!', '2021-05-22 23:18:10', 20, 384),
(38, 'this is chiheb', '2021-05-22 23:20:26', 20, 384),
(40, 'hey this is paris', '2021-05-23 01:55:22', 20, 401),
(42, 'barra nayek entaa', '2022-02-22 00:00:00', 7, 406),
(43, 'hello', '2022-04-18 00:00:00', 7, 408),
(44, 'Nice Post', '2022-10-20 00:00:00', 6, 5);

-- --------------------------------------------------------

--
-- Table structure for table `currentuser`
--

DROP TABLE IF EXISTS `currentuser`;
CREATE TABLE IF NOT EXISTS `currentuser` (
  `id` int(11) NOT NULL DEFAULT '1',
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `tel` varchar(255) NOT NULL,
  `birthDate` date DEFAULT NULL,
  `adresse` varchar(255) NOT NULL,
  `roles` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  `iduser` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `currentuser`
--

INSERT INTO `currentuser` (`id`, `email`, `password`, `username`, `tel`, `birthDate`, `adresse`, `roles`, `image`, `iduser`) VALUES
(1, 'chiheb.marijuana@gmail.com', 'chiheb', 'chiheb', '27777831', NULL, 'henchire hmaima', 'Client', '', 6);

-- --------------------------------------------------------

--
-- Table structure for table `doctrine_migration_versions`
--

DROP TABLE IF EXISTS `doctrine_migration_versions`;
CREATE TABLE IF NOT EXISTS `doctrine_migration_versions` (
  `version` varchar(191) COLLATE utf8_unicode_ci NOT NULL,
  `executed_at` datetime DEFAULT NULL,
  `execution_time` int(11) DEFAULT NULL,
  PRIMARY KEY (`version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `doctrine_migration_versions`
--

INSERT INTO `doctrine_migration_versions` (`version`, `executed_at`, `execution_time`) VALUES
('DoctrineMigrations\\Version20210327170923', '2021-03-27 17:10:37', 48),
('DoctrineMigrations\\Version20210328025447', '2021-03-28 02:55:05', 112),
('DoctrineMigrations\\Version20210331050151', '2021-03-31 05:02:01', 482),
('DoctrineMigrations\\Version20210331073402', '2021-03-31 07:34:08', 132);

-- --------------------------------------------------------

--
-- Table structure for table `evenements`
--

DROP TABLE IF EXISTS `evenements`;
CREATE TABLE IF NOT EXISTS `evenements` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prix` double DEFAULT NULL,
  `localisation` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `date` date DEFAULT NULL,
  `categorie` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nombre_place` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `evenements`
--

INSERT INTO `evenements` (`id`, `title`, `description`, `prix`, `localisation`, `date`, `categorie`, `nombre_place`) VALUES
(1, 'cinema', 'cinema', 350, 'ghazella', '2017-11-20', 'lala', 245),
(2, 'attaquant', 'titan', 400, 'ghazella', '2017-12-18', 'lala', 99),
(3, 'cinema', 'cinema', 50, 'ghazella', '2021-10-20', 'lala', 48),
(4, 'mahdi', 'mahdi', 3000, 'laouina', '2026-08-19', 'google', 248);

-- --------------------------------------------------------

--
-- Table structure for table `guest`
--

DROP TABLE IF EXISTS `guest`;
CREATE TABLE IF NOT EXISTS `guest` (
  `room_ID` int(10) NOT NULL,
  `Name` varchar(50) NOT NULL,
  `Email` varchar(50) NOT NULL,
  `Address` varchar(50) NOT NULL,
  `city` varchar(50) NOT NULL,
  `Nationality` varchar(50) NOT NULL,
  `passport_Number` varchar(50) NOT NULL,
  `phoneNo` varchar(50) NOT NULL,
  `Card_Number` varchar(50) NOT NULL,
  `card_Pass` varchar(50) NOT NULL,
  `number_Of_Days` int(10) NOT NULL,
  `fees` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `guest`
--

INSERT INTO `guest` (`room_ID`, `Name`, `Email`, `Address`, `city`, `Nationality`, `passport_Number`, `phoneNo`, `Card_Number`, `card_Pass`, `number_Of_Days`, `fees`) VALUES
(2, 'ahmed', 'ahmed@esprit.tn', 'tunis', 'tunis', 'tunisien', '12345-2547896325_4', '+216 25266784', '598712587469', '5698', 3, 600);

-- --------------------------------------------------------

--
-- Table structure for table `listeamis`
--

DROP TABLE IF EXISTS `listeamis`;
CREATE TABLE IF NOT EXISTS `listeamis` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_utl` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `idami` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
CREATE TABLE IF NOT EXISTS `location` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `localisation` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prix` double NOT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `places_dispo` int(11) DEFAULT NULL,
  `url` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `location`
--

INSERT INTO `location` (`id`, `description`, `localisation`, `prix`, `type`, `places_dispo`, `url`) VALUES
(1, 'hotel', 'espagne', 4000, 'my house', 1000, NULL),
(2, 'hotel', 'italie', 3500, 'el calcio', 796, 'images/7.svg'),
(4, 'hotel', 'italie', 3540, 'ibiza', 513, 'images/2.svg'),
(6, 'hotel', 'Almagne', 3500, 'zurik', 500, NULL),
(7, 'hotel', 'France', 4200, 'trocadero', 500, NULL),
(8, 'hotel', 'grèce', 2000, 'chichou', 100, NULL),
(9, 'hotel', 'Espagne', 1500, 'bluegrana', 560, NULL),
(10, 'hotel', 'Almagne', 3600, 'kook', 1200, NULL),
(11, 'hotel', 'Almagne', 4000, 'tuti', 3000, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `messenger`
--

DROP TABLE IF EXISTS `messenger`;
CREATE TABLE IF NOT EXISTS `messenger` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_exp` int(50) NOT NULL,
  `id_recp` int(50) NOT NULL,
  `message` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `datee` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_recp` (`id_recp`),
  KEY `id_exp` (`id_exp`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `messenger`
--

INSERT INTO `messenger` (`id`, `id_exp`, `id_recp`, `message`, `datee`) VALUES
(1, 1, 2, 'hello', '2021-03-31 02:33:03'),
(2, 1, 2, 'cc', '2021-03-31 02:34:38');

-- --------------------------------------------------------

--
-- Table structure for table `mots_interdis`
--

DROP TABLE IF EXISTS `mots_interdis`;
CREATE TABLE IF NOT EXISTS `mots_interdis` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mots` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `mots_interdis`
--

INSERT INTO `mots_interdis` (`id`, `mots`) VALUES
(1, 'mort'),
(2, 'esprit');

-- --------------------------------------------------------

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
CREATE TABLE IF NOT EXISTS `post` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `body` longtext COLLATE utf8mb4_unicode_ci NOT NULL,
  `time` datetime NOT NULL,
  `slug` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `image` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `likes` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_5A8A6C8DA76ED395` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `post`
--

INSERT INTO `post` (`id`, `title`, `body`, `time`, `slug`, `image`, `type`, `likes`, `user_id`) VALUES
(5, 'Berlin', 'La capitale allemande, Berlin, est née au XIIIe siècle. Le Mémorial de l’Holocauste et les pans restants du mur de Berlin, sur lesquels des graffitis ont été peints, témoignent de son passé tumultueux. Divisé en deux pendant la guerre froide, le pays a adopté la porte de Brandebourg du XVIIIe siècle comme symbole de sa réunification. La ville est aussi réputée pour sa scène artistique et ses monuments modernes, comme la Philharmonie de Berlin, un bâtiment doré construit en 1963 dont le toit présente une forme géométrique particulière.', '2022-10-20 16:18:10', 'thisslug', 'C:\\wamp64\\www\\wildream\\public\\images\\e8bb0931ef0a4a9b50e061b8c4deeb53.jpeg', 'image', 5, 20),
(6, 'Paris', 'Paris, capitale de la France, est une grande ville européenne et un centre mondial de l\'art, de la mode, de la gastronomie et de la culture. Son paysage urbain du XIXe siècle est traversé par de larges boulevards et la Seine. Outre les monuments comme la tour Eiffel et la cathédrale gothique Notre-Dame du XIIe siècle, la ville est réputée pour ses cafés et ses boutiques de luxe bordant la rue du Faubourg-Saint-Honoré', '2022-10-20 16:19:56', 'thisslug', 'C:\\wamp64\\www\\wildream\\public\\images\\24623e206736daa9a0958f188a2883e9.jpeg', 'image', 2, 20),
(7, 'Stockholm', 'Stockholm, la capitale de la Suède, est située sur un vaste archipel de la mer Baltique, comprenant 14 îles et plus de 50 ponts. Les rues pavées et les bâtiments de couleur ocre de Gamla stan (la vieille ville) abritent la cathédrale de Storkyrkan du XIIIe siècle, le palais royal de Stockholm et le musée Nobel, consacré au prix Nobel. Des ferries et des bateaux de tourisme transportent les passagers entre les îles.', '2022-10-20 16:21:30', 'thisslug', 'C:\\wamp64\\www\\wildream\\public\\images\\27397581b53775513fb732a1fb067854.jpeg', 'image', 2, 20);

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE IF NOT EXISTS `reservation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `destination` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `id_utli` int(11) NOT NULL,
  `date_debut` date NOT NULL,
  `date_fin` date NOT NULL,
  `nombres_personnes` int(11) NOT NULL,
  `prix` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_utli` (`id_utli`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
CREATE TABLE IF NOT EXISTS `room` (
  `roomID` int(10) NOT NULL,
  `room_Type` varchar(15) NOT NULL,
  `room_capacity` varchar(15) NOT NULL,
  `Check_In_Date` date NOT NULL,
  `Check_Out_Date` date NOT NULL,
  `isEmpty` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`roomID`, `room_Type`, `room_capacity`, `Check_In_Date`, `Check_Out_Date`, `isEmpty`) VALUES
(1, 'Economy', 'Single', '2021-04-26', '2021-04-30', 1),
(2, 'Economy', 'Single', '2019-08-13', '2021-04-24', 1),
(3, 'Economy', 'Single', '2020-06-08', '2021-04-30', 1),
(4, 'Economy', 'Single', '2019-04-14', '2021-04-30', 1),
(5, 'Economy', 'Single', '2019-04-14', '2019-04-14', 1),
(6, 'Economy', 'Single', '2019-04-14', '2019-04-14', 1),
(7, 'Economy', 'Single', '2019-04-14', '2019-04-14', 1),
(8, 'Economy', 'Single', '2019-04-14', '2019-04-19', 1),
(9, 'Economy', 'Single', '2019-04-14', '2019-04-22', 1),
(10, 'Economy', 'Single', '2019-04-14', '2019-04-22', 1),
(11, 'Economy', 'Double', '2019-04-23', '2019-04-15', 1),
(12, 'Economy', 'Double', '2019-04-14', '2019-04-18', 1),
(13, 'Economy', 'Double', '2019-04-14', '2019-04-14', 1),
(14, 'Economy', 'Double', '2019-04-14', '2019-04-15', 1),
(15, 'Economy', 'Double', '2019-04-14', '2019-04-14', 1),
(16, 'Economy', 'Double', '2019-04-14', '2019-04-16', 1),
(17, 'Economy', 'Double', '2019-04-14', '2019-04-14', 1),
(18, 'Economy', 'Double', '2019-04-14', '2019-04-18', 1),
(19, 'Economy', 'Double', '2019-04-14', '2019-04-15', 1),
(20, 'Economy', 'Double', '2019-04-14', '2019-04-18', 1),
(21, 'Economy', 'Triple', '2019-04-16', '2019-04-21', 1),
(22, 'Economy', 'Triple', '2019-04-16', '2019-04-21', 1),
(23, 'Economy', 'Triple', '2019-04-18', '2019-04-18', 1),
(24, 'Economy', 'Triple', '2019-04-15', '2019-04-15', 1),
(25, 'Economy', 'Triple', '2019-04-14', '2019-04-15', 1),
(26, 'Economy', 'Triple', '2019-04-14', '2019-04-15', 1),
(27, 'Economy', 'Triple', '2019-04-14', '2019-04-16', 1),
(28, 'Economy', 'Triple', '2019-04-14', '2019-04-14', 1),
(29, 'Economy', 'Triple', '2019-04-14', '2019-04-14', 1),
(30, 'Economy', 'Triple', '2019-04-14', '2019-04-14', 1),
(31, 'Normal', 'Single', '2019-04-14', '2019-04-14', 1),
(32, 'Normal', 'Single', '2019-04-14', '2019-04-14', 1),
(33, 'Normal', 'Single', '2019-04-14', '2019-04-14', 1),
(34, 'Normal', 'Single', '2019-04-14', '2019-04-14', 1),
(35, 'Normal', 'Single', '2019-04-14', '2019-04-14', 1),
(36, 'Normal', 'Single', '2019-04-14', '2019-04-14', 1),
(37, 'Normal', 'Single', '2019-04-14', '2019-04-14', 1),
(38, 'Normal', 'Single', '2019-04-14', '2019-04-14', 1),
(39, 'Normal', 'Single', '2019-04-14', '2019-04-14', 1),
(40, 'Normal', 'Single', '2019-04-14', '2019-04-14', 1),
(41, 'Normal', 'Double', '2021-04-24', '2021-04-27', 0),
(42, 'Normal', 'Double', '2021-04-30', '2021-04-30', 1),
(43, 'Normal', 'Double', '2019-04-14', '2019-04-14', 1),
(44, 'Normal', 'Double', '2019-04-18', '2019-04-21', 1),
(45, 'Normal', 'Double', '2019-04-14', '2019-04-21', 1),
(46, 'Normal', 'Double', '2019-04-14', '2019-04-14', 1),
(47, 'Normal', 'Double', '2019-04-14', '2019-04-14', 1),
(48, 'Normal', 'Double', '2019-04-14', '2019-04-16', 1),
(49, 'Normal', 'Double', '2019-04-14', '2019-04-14', 1),
(50, 'Normal', 'Double', '2019-04-14', '2019-04-15', 1),
(51, 'Normal', 'Triple', '2021-04-30', '2021-04-30', 1),
(52, 'Normal', 'Triple', '2019-04-30', '2019-04-30', 1),
(53, 'Normal', 'Triple', '2019-04-17', '2019-04-30', 1),
(54, 'Normal', 'Triple', '2019-04-14', '2019-05-04', 1),
(55, 'Normal', 'Triple', '2019-04-14', '2019-04-18', 1),
(56, 'Normal', 'Triple', '2019-04-14', '2019-04-14', 1),
(57, 'Normal', 'Triple', '2019-04-14', '2019-04-16', 1),
(58, 'Normal', 'Triple', '2019-04-14', '2019-04-16', 1),
(59, 'Normal', 'Triple', '2019-04-14', '2019-04-16', 1),
(60, 'Normal', 'Triple', '2019-04-14', '2019-04-14', 1),
(61, 'Vip', 'Single', '2019-05-27', '2019-05-04', 1),
(62, 'Vip', 'Single', '2019-04-14', '2019-04-14', 1),
(63, 'Vip', 'Single', '2019-04-14', '2019-04-14', 1),
(64, 'Vip', 'Single', '2019-04-14', '2019-04-14', 1),
(65, 'Vip', 'Single', '2019-04-14', '2019-04-18', 1),
(66, 'Vip', 'Single', '2019-04-15', '2019-04-16', 1),
(67, 'Vip', 'Single', '2019-04-14', '2019-04-14', 1),
(68, 'Vip', 'Single', '2019-04-14', '2019-04-14', 1),
(69, 'Vip', 'Single', '2019-04-14', '2019-04-14', 1),
(70, 'Vip', 'Single', '2019-04-14', '2019-04-14', 1),
(71, 'Vip', 'Double', '2019-04-24', '2019-04-30', 0),
(72, 'Vip', 'Double', '2019-05-04', '2019-05-30', 0),
(73, 'Vip', 'Double', '2021-04-24', '2021-04-24', 1),
(74, 'Vip', 'Double', '2019-04-14', '2019-04-14', 1),
(75, 'Vip', 'Double', '2019-04-14', '2019-04-14', 1),
(76, 'Vip', 'Double', '2019-04-14', '2019-04-22', 1),
(77, 'Vip', 'Double', '2019-04-01', '2019-04-21', 1),
(78, 'Vip', 'Double', '2019-04-14', '2019-04-14', 1),
(79, 'Vip', 'Double', '2019-04-14', '2019-04-14', 1),
(80, 'Vip', 'Double', '2019-04-14', '2019-04-14', 1),
(81, 'Vip', 'Triple', '2019-05-16', '2019-05-22', 0),
(82, 'Vip', 'Triple', '2019-04-14', '2019-04-14', 1),
(83, 'Vip', 'Triple', '2019-04-14', '2019-04-14', 1),
(84, 'Vip', 'Triple', '2019-04-14', '2019-04-14', 1),
(85, 'Vip', 'Triple', '2019-04-19', '2019-04-21', 1),
(86, 'Vip', 'Triple', '2019-04-18', '2019-04-19', 1),
(87, 'Vip', 'Triple', '2019-04-16', '2019-04-19', 1),
(88, 'Vip', 'Triple', '2019-04-14', '2019-04-19', 1),
(89, 'Vip', 'Triple', '2019-04-14', '2019-04-19', 1),
(90, 'Vip', 'Triple', '2019-04-14', '2021-04-24', 1);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(180) COLLATE utf8mb4_unicode_ci NOT NULL,
  `roles` varbinary(2555) DEFAULT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `nom` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `prenom` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `phone` bigint(20) NOT NULL,
  `adresse` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `photos` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `job` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `pseudo` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `localisation` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `activation_token` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `resert_token` varchar(50) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_8D93D649E7927C74` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `email`, `roles`, `password`, `nom`, `prenom`, `phone`, `adresse`, `photos`, `job`, `pseudo`, `localisation`, `activation_token`, `resert_token`) VALUES
(2, 'mariembenarfa123@gmail.com', 0x5b5d, '$argon2i$v=19$m=65536,t=4,p=1$ejM3SWlHbzJ3dXhoOUtPRA$UYmkl0BmIDuiQNJ1EEmBomijzK5PbrjEoathCcCl9CI', 'ben arfa', 'mariem', 51070100, 'manar', 'hdhdxhdxdd', 'hamas', 'mar', 'tunis', NULL, NULL),
(3, 'mohamedyassine.brirmi@esprit.tn', 0x5b5d, '$argon2i$v=19$m=65536,t=4,p=1$UWdkcHpJTXR5UVYyRXQxaw$/3sGPhTQmxB8KaamMHQIvVkgMDuVqEyZ8sbr1BBGnu4', 'brirmi', 'yassine', 93783700, 'ariana', 'hdhdxhdxdd', 'khadhar', 'yassou', 'france', NULL, NULL),
(4, 'balkissbenarfa@gmail.com', 0x5b5d, '$argon2i$v=19$m=65536,t=4,p=1$VzhJOFA0WjFDa2lPTWgwVg$NRgMxMc1Q65X6o+1BYn546ab7t+xmx8zxa96bjH1xNE', 'ben arfa', 'balkiss', 5457771222, 'manar', 'hdhdxhdxdd', 'architecte', 'bal', 'tunis', NULL, NULL),
(5, 'mariem.benarfa@esprit.tn', 0x5b5d, '$argon2i$v=19$m=65536,t=4,p=1$TmtucEhzZjZZbUw4ekRWSw$cTLxdvPKSHF9ciLqTEjhXQZpYh2/VUvre1ARo6e3Yhg', 'maaaa', 'ffff', 111212, 'affafaf', 'ffaffaaf', 'faaffa', 'fafafa', 'fafa', NULL, NULL),
(6, 'mahdi@gmail.com', 0x5b5d, '$argon2i$v=19$m=65536,t=4,p=1$TDVyN0ZqOVhZVTlDT0gyVw$5OSWDehcXY4FzW5hn8N3PIltp1M7NebUeIG2L7yEgTQ', 'guettiti', 'mahdi', 5454545, 'laouina', 'nsdhzd', 'ingenieure', 'titi', 'tunis', NULL, NULL),
(7, 'test@gmail.com', 0x5b5d, '$argon2i$v=19$m=65536,t=4,p=1$bWs0YlNSMUF2ekdabHB3Lg$qBBcacYMuTY9jSGpYU2C7wc2ClQhkuxit2s0Njrw9Fk', 'test', 'testtest', 55555555, 'test', 'bd', 'testeur', 'te', 'tunis', NULL, NULL),
(8, 'aman@gmail.com', 0x5b5d, '$argon2i$v=19$m=65536,t=4,p=1$VnIyRFY0cWJBRHN5Qi8uSQ$YGLNwUOh1Z07UQ5u8V9AfnAx4LCgT7HXaiuQ+SZVDsY', 'aman', 'aman', 5555, 'djdj', 'ddjzd', '8888', 'djdj', 'jdjdjjd', NULL, NULL),
(9, 'user@gmail.com', 0x5b5d, '$argon2i$v=19$m=65536,t=4,p=1$ZmF0OU0vaFpFbjlGL1pWbw$B113t2qCSk0vAR1ByYQSsknI9UVIfS3bIuNVT3XMPiM', 'user', 'user', 444444, 'userrue', 'bb', 'dndn', 'ddbdb', 'dddbbdbd', NULL, NULL),
(10, 'tesst', 0x5b5d, '$argon2i$v=19$m=65536,t=4,p=1$WE5vQlo1V0dTNWNVc1JJeQ$9VH+xRQQ0K17IXow0pV4yGVxx94pp/wWf+zY+tNQGNQ', 'test', 'tesst', 11111, 'manar', 'hdhdxhdxdd', 'ingenieure', 'mar', 'tunis', 'ac2d8ccf06228ec21244e5bfa209ee05', NULL),
(12, 'test1@gmail..com', 0x5b5d, '$argon2i$v=19$m=65536,t=4,p=1$WlA2c2dkRDBqYzhVdUhtYg$yUMsAnFtW6tGeqIoT5a+reZkjF+yy2lET35X9bWkcrA', 'ttt', 'mariem', 510000, 'dhhdh', 'hdhdhdh', 'ddhhdhdh', 'eeee', 'ssh', '3aa3429a8e684a5adc1f6fde6effc3b7', NULL),
(13, 'ali@gmail.com', 0x5b5d, '$argon2i$v=19$m=65536,t=4,p=1$eG91RFBnd0ZLcnhLbGVqNw$VOVGFEa2NuznDMUafvYpOS894/dVpscgMR8xCN1UVFA', 'hhhhhhhhhhhhhhhhhhhhhh', 'ali', 51000055, 'ali', 'ajajaj', 'ddhhdhdh', 'eeee', 'ssh', '11d72113762168adf7b5e5c54ec961e3', NULL),
(14, '444@gmail.com', 0x5b5d, '$argon2i$v=19$m=65536,t=4,p=1$ZzNZYWx0eDV6SFU3RWw4Zg$sRTmmvz05YPmGRrkCd2y16qYg7pTxBDe8PkeRvXB68s', 'ben arfa', 'mariem', 55555555, 'manar', 'hdhdxhdxdd', 'hamas', 'mar', 'tunis', '5b1c60611f76e0c0894088bef8be3c29', NULL),
(15, 'cm@gmail.com', 0x5b5d, '$argon2i$v=19$m=65536,t=4,p=1$b01DWU9nSTRTNjU4LldmQg$fuQcZHx5VyCFvfaJxeN0a9KsdXoxAkLuwPtJtjURVcA', '1', 'cm', 12345678, 'lllllllllllllllllll', 'hdhdxhdxdd', 'hamas', 'mar', 'tunis', '140a2015c64b1dda76c558d8422b040f', NULL),
(16, 'bbbhhh', 0x61646d696e, 'ndndnd', 'nnnn', 'hhhh', 112, 'adeze', 'hhhhhhhhh', 'ena', 'lae', 'ar', 'jjjjj', 'jeje');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `tel` varchar(255) NOT NULL,
  `birthDate` date NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `roles` varchar(255) NOT NULL,
  `image` varchar(255) NOT NULL,
  `idu` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `email`, `password`, `username`, `tel`, `birthDate`, `adresse`, `roles`, `image`, `idu`) VALUES
(1, 'mariembenarfa123@esprit.tn', 'mariem123', 'Mariem Ben Arfa', '98563214', '2021-03-29', 'Ariena', 'Client', '', 1),
(2, 'mariembenarfa123@gmail.com', 'mariem1234', 'mariem2', '51070100', '2021-03-29', 'manar1', 'Client', '', 2),
(3, 'mariem123', 'mariem123', 'mariem.benarfa@esprit.tn', '5555555', '2021-04-29', 'mariem ben arfa', 'Admin', '', 3),
(4, 'eyanasri@esprit.tn', 'Eya12345', 'eya', '29644787', '2021-03-30', 'ariana', 'Client', '', 4),
(5, 'mohamedyassine.brirmi@esprit.tn', '$2y$13$xdKX5KRt6c08AFQffFPZeu8st.P.zFQIOBNpyJJiQzEhXUNUJtx/i', 'Yassine', '12345678', '2021-03-29', 'ghazela', 'Admin', '', NULL),
(6, 'chiheb.marijuana@gmail.com', 'chiheb', 'chiheb', '27777831', '1998-08-07', 'henchire hmaima', 'Client', '', 6),
(7, 'chikhaouichiheb1@gmail.com', 'Chiheb7898', 'chiheb_', '27777831', '1998-08-07', 'hhhhhh', 'Client', '', 7);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `messenger`
--
ALTER TABLE `messenger`
  ADD CONSTRAINT `messenger_ibfk_1` FOREIGN KEY (`id_recp`) REFERENCES `user` (`id`);

--
-- Constraints for table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`id_utli`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
