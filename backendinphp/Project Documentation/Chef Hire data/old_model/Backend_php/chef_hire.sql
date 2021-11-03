-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Sep 02, 2018 at 09:45 AM
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
  `lat` varchar(300) NOT NULL,
  `log` varchar(300) NOT NULL,
  `password` varchar(200) NOT NULL,
  `fcm` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `chef`
--

INSERT INTO `chef` (`id`, `name`, `phone`, `address`, `rating`, `image`, `email`, `adhar`, `status`, `chef_id`, `verify`, `lat`, `log`, `password`, `fcm`) VALUES
(28, 'santosh kumar behera', '8826417738', 'chattarpur new delhi', '5.0', 'http://zeenarch.com/chef_hire77_980bia/images/chef1.jpg', 'santoshpro ', '35345345345', '1', '984706', '1', '28.4912407', '77.1919371', '1234', 'd18MJ-1QzHQ:APA91bE8ZQxZQ1VTZuZUPNQYhRz98-9Y54Qs2aeFBKlAOuiqLTidoOuxuADpZ9czqvQs5B5b9bc2scSFzB6P8SA8TvtqJ_XXSFiJNZCZag-pKPLyc0SCGrzWNKSbPimq5O3byV2tjKpV'),
(30, 'jdjd', '456434', 'hdjsis', '5.0', 'http://zeenarch.com/chef_hire77_980bia/images/chef1.jpg', 'ususiis', '', '1', '964309', '1', '28.4263157', '77.1084242', '', ''),
(31, 'rajubala', '454664', 'bsjsjskd', '5.0', 'http://zeenarch.com/chef_hire77_980bia/chef_images/image.jpg', 'hsjsjskdkd', '2354345345', '1', '  87631727', '1', '28.491268284015', '77.192586643955', '', '');

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
(131, '984706', '12'),
(120, '984706', '63'),
(130, '984706', '12'),
(133, '984706', '32'),
(137, '984706', '53');

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
  `time` varchar(300) NOT NULL,
  `rating` varchar(100) NOT NULL,
  `lat` varchar(300) NOT NULL,
  `log` varchar(300) NOT NULL,
  `delivery_time` varchar(300) NOT NULL,
  `del_name` varchar(200) NOT NULL,
  `del_phone` varchar(200) NOT NULL,
  `user_fcm` varchar(500) NOT NULL,
  `del_price` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `chef_order`
--

INSERT INTO `chef_order` (`id`, `order_id`, `user_name`, `user_phone`, `user_address`, `chef_name`, `chef_phone`, `chef_address`, `chef_id`, `status`, `food_name`, `food_price`, `meals_no`, `time`, `rating`, `lat`, `log`, `delivery_time`, `del_name`, `del_phone`, `user_fcm`, `del_price`) VALUES
(151, '1735890042', 'santosh kumar', '8826417738', 'ram Chandra market booda', 'santosh kumar behera', '8826417738', 'chattarpur new delhi', '984706', 'delivered', 'Ghar ki Thali', '540', '3', '2018/09/02/13/31/56', '5.0', '28.491279', '77.1924808', '2018/09/02/13/52/13', 'kalesh', '9987543679', '', '0'),
(152, ' 299624908', 'santosh kumar', '8826417738', 'ram Chandra market booda', 'santosh kumar behera', '8826417738', 'chattarpur new delhi', '984706', 'delivered', 'Poori Thali', '540', '3', '2018/09/02/18/53/19', '0', '28.4912407', '77.1919371', '2018/09/02/19/24/42', 'kalesh', '9987543679', '', '0'),
(153, ' 377997659', 'santosh kumar', '8826417738', 'ram Chandra market booda', 'santosh kumar behera', '8826417738', 'chattarpur new delhi', '984706', 'waiting', 'Ghar ki Thali', '180', '1', '2018/09/02/19/28/05', '0', '28.4912407', '77.1919371', '0', '', '', '', '0');

-- --------------------------------------------------------

--
-- Table structure for table `delivery`
--

CREATE TABLE `delivery` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `address` varchar(300) NOT NULL,
  `phone` varchar(100) NOT NULL,
  `company_id` varchar(300) NOT NULL,
  `fcm` varchar(500) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `delivery`
--

INSERT INTO `delivery` (`id`, `name`, `address`, `phone`, `company_id`, `fcm`) VALUES
(1, 'kalesh', 'chattarpur', '9987543679', '1234', 'd18MJ-1QzHQ:APA91bE8ZQxZQ1VTZuZUPNQYhRz98-9Y54Qs2aeFBKlAOuiqLTidoOuxuADpZ9czqvQs5B5b9bc2scSFzB6P8SA8TvtqJ_XXSFiJNZCZag-pKPLyc0SCGrzWNKSbPimq5O3byV2tjKpV'),
(3, 'hofgh', 'dsgzdf', '457457568', '457', 'd18MJ-1QzHQ:APA91bE8ZQxZQ1VTZuZUPNQYhRz98-9Y54Qs2aeFBKlAOuiqLTidoOuxuADpZ9czqvQs5B5b9bc2scSFzB6P8SA8TvtqJ_XXSFiJNZCZag-pKPLyc0SCGrzWNKSbPimq5O3byV2tjKpV'),
(4, 'hofgh', 'dsgzdf', '457457568', '457', 'd18MJ-1QzHQ:APA91bE8ZQxZQ1VTZuZUPNQYhRz98-9Y54Qs2aeFBKlAOuiqLTidoOuxuADpZ9czqvQs5B5b9bc2scSFzB6P8SA8TvtqJ_XXSFiJNZCZag-pKPLyc0SCGrzWNKSbPimq5O3byV2tjKpV');

-- --------------------------------------------------------

--
-- Table structure for table `meals`
--

CREATE TABLE `meals` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `price` varchar(100) NOT NULL,
  `image` varchar(250) NOT NULL,
  `items` varchar(300) NOT NULL,
  `meal_id` varchar(200) NOT NULL,
  `verify` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `meals`
--

INSERT INTO `meals` (`id`, `name`, `price`, `image`, `items`, `meal_id`, `verify`) VALUES
(1, 'Shahi Thali', '120', 'http://zeenarch.com/chef_hire77_980bia/images/veg_meals.jpg', 'Shahi paneer + dal makhani + chole + rice + roti + achar + salad', '12', '1'),
(2, 'Mini Thali', '120', 'http://zeenarch.com/chef_hire77_980bia/images/veg_meals.jpg', 'Shahi paneer + dal makhani + chole + rice + roti + achar + salad', '78', '1'),
(3, 'Poori Thali', '150', 'http://zeenarch.com/chef_hire77_980bia/images/veg_meals.jpg', 'Shahi paneer + dal makhani + chole + rice + roti + achar + salad', '32', '1'),
(4, 'Plain Paratha Thali', '150', 'http://zeenarch.com/chef_hire77_980bia/images/veg_meals.jpg', 'Shahi paneer + dal makhani + chole + rice + roti + achar + salad', '43', '1'),
(5, 'Ghar ki Thali', '150', 'http://zeenarch.com/chef_hire77_980bia/images/veg_meals.jpg', 'Shahi paneer + dal makhani + chole + rice + roti + achar + salad', '53', '1'),
(6, 'Non Veg Meal', '150', 'http://zeenarch.com/chef_hire77_980bia/images/veg_meals.jpg', 'Shahi paneer + dal makhani + chole + rice + roti + achar + salad', '63', '1');

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE `menu` (
  `id` int(11) NOT NULL,
  `s_id` varchar(200) NOT NULL,
  `mon` varchar(300) NOT NULL,
  `tue` varchar(300) NOT NULL,
  `wed` varchar(300) NOT NULL,
  `thu` varchar(300) NOT NULL,
  `fri` varchar(300) NOT NULL,
  `sat` varchar(300) NOT NULL,
  `sun` varchar(300) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `menu`
--

INSERT INTO `menu` (`id`, `s_id`, `mon`, `tue`, `wed`, `thu`, `fri`, `sat`, `sun`) VALUES
(1, '456', 'bread omelet', 'ksr', 'srdgf', 'drghdzfg', 'dfhdfh', 'dfhdfh', 'dfhsdfhsdfh');

-- --------------------------------------------------------

--
-- Table structure for table `services`
--

CREATE TABLE `services` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `image` varchar(400) NOT NULL,
  `s_id` varchar(200) NOT NULL,
  `breakfast` varchar(200) NOT NULL,
  `lunch` varchar(200) NOT NULL,
  `dinner` varchar(200) NOT NULL,
  `info` varchar(300) NOT NULL,
  `address` varchar(300) NOT NULL,
  `lat` varchar(300) NOT NULL,
  `log` varchar(300) NOT NULL,
  `phone` varchar(300) NOT NULL,
  `state` varchar(300) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `services`
--

INSERT INTO `services` (`id`, `name`, `image`, `s_id`, `breakfast`, `lunch`, `dinner`, `info`, `address`, `lat`, `log`, `phone`, `state`) VALUES
(1, 'Nidhi Tiffin', 'http://zeenarch.com/chef_hire77_980bia/images/tiffin.jpg', '456', '35', '50', '50', 'this is information about this ', 'Chattarpur', '28.4913667', '77.1923899', '8826417738', 'delhi'),
(3, 'raju kitchen', 'http://zeenarch.com/chef_hire77_980bia/images/veg_meals.jpg', '147', '50', '100', '100', 'also gives salad free', 'chattarpur', '28.491268284015', '77.192586643955', '8989776787', 'Gurugram');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `name` varchar(120) NOT NULL,
  `phone` varchar(120) NOT NULL,
  `address` varchar(300) NOT NULL,
  `lat` varchar(300) NOT NULL,
  `log` varchar(300) NOT NULL,
  `password` varchar(200) NOT NULL,
  `email` varchar(200) NOT NULL,
  `fcm` varchar(500) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `phone`, `address`, `lat`, `log`, `password`, `email`, `fcm`) VALUES
(62, 'santosh kumar', '8826417738', 'ram Chandra market booda', '28.4912407', '77.1919371', '1234', '', 'd18MJ-1QzHQ:APA91bE8ZQxZQ1VTZuZUPNQYhRz98-9Y54Qs2aeFBKlAOuiqLTidoOuxuADpZ9czqvQs5B5b9bc2scSFzB6P8SA8TvtqJ_XXSFiJNZCZag-pKPLyc0SCGrzWNKSbPimq5O3byV2tjKpV'),
(67, 'man', '9899485425', 'asf', '28.4802739086175', '77.0803233029798', '', '', ''),
(68, 'Jijbb', '855588', 'Bjjkk', '28.491333', '77.1924243', '', '', ''),
(69, 'santosh', '865368886', 'chattarpur ', '28.4906199', '77.1924209', 'dnjsjdk', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `user_request`
--

CREATE TABLE `user_request` (
  `id` int(11) NOT NULL,
  `phone` varchar(200) NOT NULL,
  `lat` varchar(300) NOT NULL,
  `log` varchar(300) NOT NULL,
  `address` varchar(300) NOT NULL,
  `name` varchar(200) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_request`
--

INSERT INTO `user_request` (`id`, `phone`, `lat`, `log`, `address`, `name`) VALUES
(4, '8826417738', '28.4913667', '77.1923899', 'Chattarpur rajpur mcd', 'santosh kumar behera ');

-- --------------------------------------------------------

--
-- Table structure for table `user_service`
--

CREATE TABLE `user_service` (
  `id` int(11) NOT NULL,
  `phone` varchar(100) NOT NULL,
  `s_id` varchar(200) NOT NULL,
  `duration` varchar(200) NOT NULL,
  `lat` varchar(300) NOT NULL,
  `log` varchar(300) NOT NULL,
  `veg_only` varchar(100) NOT NULL,
  `breakfast` varchar(100) NOT NULL,
  `lunch` varchar(100) NOT NULL,
  `dinner` varchar(100) NOT NULL,
  `days` varchar(200) NOT NULL,
  `name` varchar(300) NOT NULL,
  `price` varchar(200) NOT NULL,
  `status` varchar(200) NOT NULL,
  `address` varchar(300) NOT NULL,
  `end_date` varchar(300) NOT NULL,
  `order_id` varchar(300) NOT NULL,
  `del_name` varchar(200) NOT NULL,
  `del_phone` varchar(200) NOT NULL,
  `pickup_address` varchar(300) NOT NULL,
  `user_name` varchar(200) NOT NULL,
  `service_phone` varchar(200) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_service`
--

INSERT INTO `user_service` (`id`, `phone`, `s_id`, `duration`, `lat`, `log`, `veg_only`, `breakfast`, `lunch`, `dinner`, `days`, `name`, `price`, `status`, `address`, `end_date`, `order_id`, `del_name`, `del_phone`, `pickup_address`, `user_name`, `service_phone`) VALUES
(34, '8826417738', '456', '2018/09/02/23/07/52', '28.4913503', '77.1924115', '0', '0', '0', '0', '7', 'Nidhi Tiffin', '0', 'canceled', 'chattarpur ', '2018/09/08/23/07/52', '9825328332', 'kalesh', '9987543679', 'Chattarpur', 'santosh kumar', '8826417738'),
(33, '8826417738', '147', '2018/09/02/16/21/39', '28.4912949', '77.1925429', '1', '0', '1', '1', '15', 'raju kitchen', '3000', 'active', 'Chattarpur rajpur pero', '2018/09/16/16/21/39', '8298442955', 'kalesh', '9987543679', 'chattarpur', 'santosh kumar', '8989776787');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `chef`
--
ALTER TABLE `chef`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `chef_meals`
--
ALTER TABLE `chef_meals`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `chef_order`
--
ALTER TABLE `chef_order`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `delivery`
--
ALTER TABLE `delivery`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `meals`
--
ALTER TABLE `meals`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `services`
--
ALTER TABLE `services`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_request`
--
ALTER TABLE `user_request`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_service`
--
ALTER TABLE `user_service`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `chef`
--
ALTER TABLE `chef`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT for table `chef_meals`
--
ALTER TABLE `chef_meals`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=139;

--
-- AUTO_INCREMENT for table `chef_order`
--
ALTER TABLE `chef_order`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=154;

--
-- AUTO_INCREMENT for table `delivery`
--
ALTER TABLE `delivery`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `meals`
--
ALTER TABLE `meals`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `menu`
--
ALTER TABLE `menu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `services`
--
ALTER TABLE `services`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=70;

--
-- AUTO_INCREMENT for table `user_request`
--
ALTER TABLE `user_request`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `user_service`
--
ALTER TABLE `user_service`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
