-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Aug 16, 2018 at 08:56 AM
-- Server version: 5.6.39-cll-lve
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `chef_hire`
--

-- --------------------------------------------------------

--
-- Table structure for table `chef_meals`
--

CREATE TABLE `chef_meals` (
  `id` int(11) NOT NULL,
  `chef_id` varchar(300) NOT NULL,
  `meal_id` varchar(300) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `chef_meals`
--

INSERT INTO `chef_meals` (`id`, `chef_id`, `meal_id`) VALUES
(88, '964309', '78'),
(8, '964309', '12'),
(84, '984706', '53'),
(91, '984706', '32'),
(93, '984706', '43'),
(87, '984706', '63'),
(85, '984706', '12'),
(89, '964309', '32'),
(90, '  87631727', '12');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chef_meals`
--
ALTER TABLE `chef_meals`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `chef_meals`
--
ALTER TABLE `chef_meals`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=94;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
