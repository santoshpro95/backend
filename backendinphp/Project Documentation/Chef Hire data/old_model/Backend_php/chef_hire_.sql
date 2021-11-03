-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Aug 09, 2018 at 12:17 PM
-- Server version: 10.1.9-MariaDB
-- PHP Version: 5.6.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
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
-- Table structure for table `chef`
--

CREATE TABLE `chef` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `phone` varchar(200) NOT NULL,
  `address` varchar(300) NOT NULL,
  `rating` varchar(100) NOT NULL,
  `image` varchar(300) NOT NULL,
  `email` varchar(200) NOT NULL,
  `adhar` varchar(300) NOT NULL,
  `status` varchar(100) NOT NULL,
  `chef_id` varchar(250) NOT NULL,
  `verify` varchar(100) NOT NULL,
  `choose` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `chef`
--

INSERT INTO `chef` (`id`, `name`, `phone`, `address`, `rating`, `image`, `email`, `adhar`, `status`, `chef_id`, `verify`, `choose`) VALUES
(1, 'Rakesh Jain', '123456654', 'Gurgaon, Phase 4', '5.0', 'http://192.168.0.8:8850/chef_hire/php/images/chef1.jpg', '', '', '1', '131', '1', '1'),
(2, 'Rakesh Jain', '123456654', 'Gurgaon, Phase 4', '5.0', 'http://192.168.0.8:8850/chef_hire/php/images/chef1.jpg', '', '', '0', '1313', '1', '1'),
(3, 'Gendra kumar', '907', 'Chattarpur', '4.8', 'http://192.168.0.8:8850/chef_hire/php/images/chef1.jpg', 'santoshpro95@gmail.com', '', '1', '324234', '1', '1'),
(4, 'Gendra kumar', '567', 'Chattarpur', '4.6', 'http://192.168.0.8:8850/chef_hire/php/images/chef1.jpg', '', '', '1', '234234', '0', '1'),
(5, 'Samarth Singh', '099897877', 'Dwraka , sec 12', '4.8', 'http://192.168.0.8:8850/chef_hire/php/images/chef1.jpg', '', '', '0', '234324', '1', '1'),
(6, 'Samarth Singh', '123', 'Dwraka , sec 12', '4.8', 'http://192.168.0.8:8850/chef_hire/php/images/chef1.jpg', '', '', '1', '123213', '1', '0'),
(7, 'kuhk', '98789', '', '', '', 'kjhkj', '', '', '', '0', ''),
(8, 'lk', '987898', '', '', '', 'lklk', '', '', '', '', ''),
(9, 'fhcuf', '575757', '', '', '', 'ufyyfu', '', '', '', '', ''),
(10, 'jfuf', 't566788', '', '', '', 'hdufjfj', '', '', '', '', ''),
(11, 'hji', 'hhui', '', '', '', 'chh', '', '', '', '0', ''),
(12, 'hfjf', '5855855557552', '', '', '', 'hdudu', '', '', '', '0', ''),
(13, 'lk', '98', '', '', '', 'lklk', '', '', '', '0', ''),
(14, 'giucufu', '588258585', '', '', '', 'uffuyf', '', '', '', '0', ''),
(15, 'ggf', '5554441', '', '', '', 'gffdd', '', '', '', '0', ''),
(16, 'gffdtgg', '8555555', '', '', '', 'tvvvvggdde', '', '', '', '0', ''),
(17, 'fcc', '558544', '', '', '', 'fffghgff', '', '', '', '0', ''),
(18, 'vgvhggv', '855555', '', '', '', 'vvvffdd', '', '', '', '0', ''),
(19, 'ghgff', '888855', '', '', '', 'bhffdd', '', '', '', '0', ''),
(20, 'rff', '5555225', '', '', '', 'tffgg', '', '', '', '0', ''),
(21, 'bzjdjkd', '434675', '', '', '', 'djsjdjjd', '', '', '', '0', ''),
(22, 'raju chapdhri', '123456789', '', '', '', 'santosh@gnail.com', '', '1', '383928', '1', '1'),
(24, 'santosh kumar behera', '8826417738', 'Chattarpur New Delhi', '4.8', '', 'santoshpro95@gmail.com', '', '0', '892262', '1', '0'),
(25, 'f', '', '', '5.0', '', '', '', '1', '050659', '0', ''),
(26, 'raju rastogi', '88264', 'chattarpur new delhi', '5.0', '', 'bbbb', '', '1', '587660', '0', '');

-- --------------------------------------------------------

--
-- Table structure for table `chef_order`
--

CREATE TABLE `chef_order` (
  `id` int(11) NOT NULL,
  `order_id` varchar(200) NOT NULL,
  `user_name` varchar(200) NOT NULL,
  `user_phone` varchar(200) NOT NULL,
  `user_address` varchar(200) NOT NULL,
  `chef_name` varchar(200) NOT NULL,
  `chef_phone` varchar(200) NOT NULL,
  `chef_address` varchar(200) NOT NULL,
  `chef_id` varchar(200) NOT NULL,
  `status` varchar(200) NOT NULL,
  `food_name` varchar(250) NOT NULL,
  `food_price` varchar(100) NOT NULL,
  `meals_no` varchar(100) NOT NULL,
  `choose` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `chef_order`
--

INSERT INTO `chef_order` (`id`, `order_id`, `user_name`, `user_phone`, `user_address`, `chef_name`, `chef_phone`, `chef_address`, `chef_id`, `status`, `food_name`, `food_price`, `meals_no`, `choose`) VALUES
(58, '359583', 'santosh', '88264177358', 'chattarpur new delhi house no 2', 'Rakesh Jain', '123456654', 'Gurgaon, Phase 4', '131', 'waiting', 'Rice, 4 Roti, Kadi, sahi Paneer, Salad', '120/-', '1', ''),
(59, '162988', 'santosh', '88264177358', 'chattarpur new delhi house no 2', 'Gendra kumar', '4363463463', 'Chattarpur', '324234', 'travelling', 'Rice, 4 Roti, Rajma, sahi Paneer, Salad', '120/-', '1', '0'),
(60, '895659', 'santosh', '88264177358', 'chattarpur new delhi house no 2', 'Rakesh Jain', '123456654', 'Gurgaon, Phase 4', '131', 'waiting', 'Rice, 4 Roti, Kadi, sahi Paneer, Salad', '120/-', '1', '1'),
(61, '789310', 'santosh', '88264177358', 'chattarpur new delhi house no 2', 'Rakesh Jain', '123456654', 'Gurgaon, Phase 4', '131', 'waiting', 'Rice, 4 Roti, Chiken curry, salad', '150/-', '4', '1'),
(62, '519404', 'santosh', '88264177358', 'chattarpur new delhi house no 2', 'Rakesh Jain', '123456654', 'Gurgaon, Phase 4', '131', 'waiting', 'Rice, 4 Roti, Chiken curry, salad', '150/-', '4', '1'),
(63, '205097', 'santosh', '88264177358', 'chattarpur new delhi house no 2', 'Rakesh Jain', '123456654', 'Gurgaon, Phase 4', '131', 'waiting', 'Rice, 4 Roti, Kadi, sahi Paneer, Salad', '120/-', '3', '1'),
(64, '130484', '', '5566', '', 'Rakesh Jain', '123456654', 'Gurgaon, Phase 4', '131', 'waiting', 'Rice, 4 Roti, Kadi, sahi Paneer, Salad', '120/-', '1', '1'),
(65, '362606', 'santosh', '8826417738', 'chattarpur', 'Samarth Singh', '099897877', 'Dwraka , sec 12', '123213', 'waiting', 'Poori Thali', '150/-', '5', '0'),
(66, '111715', 'sa', '48888', 'chattarpur house no :2 ', 'Rakesh Jain', '123456654', 'Gurgaon, Phase 4', '131', 'waiting', 'Shahi Thali', '', '', '1'),
(67, '068371', 'sa', '48888', 'chattarpur rajpur house no :2 ', 'Rakesh Jain', '123456654', 'Gurgaon, Phase 4', '131', 'waiting', 'Poori Thali', '', '', '1'),
(68, '657388', 'sa', '48888', 'chattarpur rajpur house no :2 ', 'Rakesh Jain', '123456654', 'Gurgaon, Phase 4', '131', 'waiting', 'Mini Thali', '960', '8', '1'),
(69, '858863', 'santosh', '+91 8826417738', 'chattarpur new delhi near mcd school', 'Rakesh Jain', '123456654', 'Gurgaon, Phase 4', '131', 'waiting', 'Mini Thali', '240', '2', '1'),
(71, '100401', 'snsjjdjd', '+91 643737364344', 'hshdjdjdkdjdis', 'Rakesh Jain', '123456654', 'Gurgaon, Phase 4', '131', 'waiting', 'Mini Thali', '480', '4', '1'),
(72, '028248', 'santosh', '+91 875464', 'chattarpur', 'raju chapdhri', '123456789', '', '383928', 'waiting', 'Mini Thali', '360', '3', '1'),
(73, '214061', 'santosh', '+91 8826417738', 'chattarpur new delhi', 'Gendra kumar', '907', 'Chattarpur', '324234', 'delivered', 'Shahi Thali', '', '', '0'),
(74, '919282', 'santosh', '+91 8826417738', 'chattarpur new delhi', 'Gendra kumar', '907', 'Chattarpur', '324234', 'delivered', 'Shahi Thali', '480', '4', '0'),
(75, '389726', 'santosh', '+91 4664646646', 'chattarpur ', 'Rakesh Jain', '123456654', 'Gurgaon, Phase 4', '131', 'waiting', 'Poori Thali', '150', '1', '1'),
(76, '226050', 'santosh', '+91 4664646646', 'chattarpur ', 'Rakesh Jain', '123456654', 'Gurgaon, Phase 4', '131', 'waiting', 'Mini Thali', '360', '3', '1'),
(77, '967617', 'raju rastogi', '+91 8826646', 'chattarpur new delhi', 'santosh kumar', '8826417738', '', '892262', 'delivered', 'Poori Thali', '450', '3', '1'),
(78, '683905', 'santosh kumar', '+91 8826417738', 'chattarpur', 'santosh kumar', '8826417738', 'Chattarpur New Delhi', '892262', 'cancel', 'Poori Thali', 'get_food_price', '1', '1'),
(79, '189898', 'santosh', '+91 8826417738', 'jsjdididsj', 'Gendra kumar', '907', 'Chattarpur', '324234', 'waiting', 'Poori Thali', '150', '1', '1');

-- --------------------------------------------------------

--
-- Table structure for table `meals`
--

CREATE TABLE `meals` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `price` varchar(100) NOT NULL,
  `image` varchar(250) NOT NULL,
  `items` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `meals`
--

INSERT INTO `meals` (`id`, `name`, `price`, `image`, `items`) VALUES
(1, 'Shahi Thali', '120', 'http://192.168.0.8:8850/chef_hire/php/images/veg_meals.jpg', 'Shahi paneer + dal makhani + chole + rice + roti + achar + salad'),
(2, 'Mini Thali', '120', 'http://192.168.0.8:8850/chef_hire/php/images/veg_meals.jpg', 'Shahi paneer + dal makhani + chole + rice + roti + achar + salad'),
(3, 'Poori Thali', '150', 'http://192.168.0.8:8850/chef_hire/php/images/non_veg_meals.jpg', 'Shahi paneer + dal makhani + chole + rice + roti + achar + salad'),
(4, 'Plain Paratha Thali', '150', 'http://192.168.0.8:8850/chef_hire/php/images/non_veg_meals.jpg', 'Shahi paneer + dal makhani + chole + rice + roti + achar + salad'),
(5, 'Ghar ki Thali', '150', 'http://192.168.0.8:8850/chef_hire/php/images/non_veg_meals.jpg', 'Shahi paneer + dal makhani + chole + rice + roti + achar + salad'),
(6, 'Non Veg Meal', '150', 'http://192.168.0.8:8850/chef_hire/php/images/veg_meals.jpg', 'Shahi paneer + dal makhani + chole + rice + roti + achar + salad');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(120) NOT NULL,
  `phone` varchar(120) NOT NULL,
  `address` varchar(300) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `phone`, `address`) VALUES
(54, 'sa', '48888', 'chattarpur house no :2 '),
(55, 'santosh kumar', '+91 8826417738', 'jsjdididsj'),
(56, 'snsjjdjd', '+91 643737364344', 'hshdjdjdkdjdis'),
(58, 'santosh', '+91 875464', 'chattarpur'),
(59, 'santosh', '+91 4664646646', 'chattarpur '),
(60, 'santosh', '+91 8826646', 'chattarpur '),
(61, 'lala ka dukan', '+91 84646', 'chattarpur new delhi');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chef`
--
ALTER TABLE `chef`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `chef_order`
--
ALTER TABLE `chef_order`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `meals`
--
ALTER TABLE `meals`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `chef`
--
ALTER TABLE `chef`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;
--
-- AUTO_INCREMENT for table `chef_order`
--
ALTER TABLE `chef_order`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=81;
--
-- AUTO_INCREMENT for table `meals`
--
ALTER TABLE `meals`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=62;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
