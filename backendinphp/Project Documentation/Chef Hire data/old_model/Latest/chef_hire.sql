-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Sep 13, 2018 at 08:05 AM
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
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(11) NOT NULL,
  `email` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `email`, `password`) VALUES
(1, 'santoshpro95@gmail.com', '1234'),
(2, 'manmohan.0008@gmail.com', '1234');

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
  `fcm` varchar(500) NOT NULL,
  `type` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `chef`
--

INSERT INTO `chef` (`id`, `name`, `phone`, `address`, `rating`, `image`, `email`, `adhar`, `status`, `chef_id`, `verify`, `lat`, `log`, `password`, `fcm`, `type`) VALUES
(28, 'santosh kumar  behera ', '8826417738', 'chattarpur new Delhi 2', '5.0', 'http://zeenarch.com/chef_hire77_980bia/chef_images/chef1.jpg', 'santoshpro95@gmail.com', '35345345345', '1', '984706', '1', '28.4912923', '77.1924793', '1234', 'f46m3cHRJYM:APA91bGBa1AF2IL5ED6p09qmiC3jqJvQWDr-CRJNLlxZza4VZdBtm8xqWgFE_5auhnotVi_yebGBoXRGL7Rg0aoE5z5gquXy4WwjZYeZxR3QSFObi6PsGnq0klWrbOdqfjZ8j59yBr9X', '0'),
(30, 'Kulbinder Singh', '1234567890', 'Gurgaon Phase 4', '4.8', 'http://zeenarch.com/chef_hire77_980bia/chef_images/chef2.jpg', 'kulbinder@gmail.com', '', '1', '964309', '1', '28.4912673', '77.1925064', '1234', 'f46m3cHRJYM:APA91bGBa1AF2IL5ED6p09qmiC3jqJvQWDr-CRJNLlxZza4VZdBtm8xqWgFE_5auhnotVi_yebGBoXRGL7Rg0aoE5z5gquXy4WwjZYeZxR3QSFObi6PsGnq0klWrbOdqfjZ8j59yBr9X', '1'),
(47, 'Rakes Sharma ', '9990757966', 'Chattarpur ', '5.0', '', 'santosh@reelover.com', '', '0', '5671121147', '0', '28.4802771', '77.0802672', '1234', 'f46m3cHRJYM:APA91bGBa1AF2IL5ED6p09qmiC3jqJvQWDr-CRJNLlxZza4VZdBtm8xqWgFE_5auhnotVi_yebGBoXRGL7Rg0aoE5z5gquXy4WwjZYeZxR3QSFObi6PsGnq0klWrbOdqfjZ8j59yBr9X', '0');

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
(174, '964309', '5'),
(181, '964309', '7'),
(175, '964309', '4'),
(236, '984706', '3'),
(235, '984706', '4');

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
(170, '7355785179', 'Santosh ', '8826417738', 'Chattarpur ', 'Kulbinder Singh', '1234567890', 'Gurgaon Phase 4', '964309', 'delivered', 'Matar Paneer', '540', '3', '2018/09/08/14/50/59', '0', '28.4913111', '77.1924664', '2018/09/08/14/58/27', 'kalesh', '9987543679', 'ew2HJx_3UII:APA91bH0qQr2qwwIpTfGCGmoTwhzoSuOP1kInjInPDTDno6-keKR8gLJYZq9MGF_ALhtBFED-dYsuRk7yZJ-7wSE8qm6ddNEuodHlQM2Wb1WVj5xlJe4_7JiRjryWEQ87_VShyQdb9w8', '0'),
(171, '1161088343', 'Santosh ', '8826417738', 'Chattarpur ', 'Kulbinder Singh', '1234567890', 'Gurgaon Phase 4', '964309', 'delivered', 'Chana Masala', '180', '1', '2018/09/08/15/09/18', '0', '28.4913111', '77.1924664', '2018/09/08/15/23/22', 'kalesh', '9987543679', 'ew2HJx_3UII:APA91bH0qQr2qwwIpTfGCGmoTwhzoSuOP1kInjInPDTDno6-keKR8gLJYZq9MGF_ALhtBFED-dYsuRk7yZJ-7wSE8qm6ddNEuodHlQM2Wb1WVj5xlJe4_7JiRjryWEQ87_VShyQdb9w8', '0'),
(172, '2292144881', 'Santosh', '1234567890', 'Chattarpur ', 'santosh kumar behera ', '8826417738', 'chattarpur new delhi', '984706', 'delivered', 'Butter Chicken', '480', '2', '2018/09/08/18/14/51', '0', '28.4913667', '77.1923899', '2018/09/08/18/22/10', 'kalesh', '9987543679', 'ew2HJx_3UII:APA91bH0qQr2qwwIpTfGCGmoTwhzoSuOP1kInjInPDTDno6-keKR8gLJYZq9MGF_ALhtBFED-dYsuRk7yZJ-7wSE8qm6ddNEuodHlQM2Wb1WVj5xlJe4_7JiRjryWEQ87_VShyQdb9w8', '0'),
(178, '6582149127', 'Santosh ', '8826417738', 'Chattarpur new Delhi ', 'Kulbinder Singh', '1234567890', 'Gurgaon Phase 4', '964309', 'delivered', 'Chowmein', '360', '3', '2018/09/09/23/43/39', '0', '28.4912673', '77.1925064', '2018/09/09/23/44/41', 'kalesh', '9987543679', 'f46m3cHRJYM:APA91bGBa1AF2IL5ED6p09qmiC3jqJvQWDr-CRJNLlxZza4VZdBtm8xqWgFE_5auhnotVi_yebGBoXRGL7Rg0aoE5z5gquXy4WwjZYeZxR3QSFObi6PsGnq0klWrbOdqfjZ8j59yBr9X', '0'),
(179, '5892620836', 'Santosh ', '8826417738', 'Chattarpur new Delhi ', 'Kulbinder Singh', '1234567890', 'Gurgaon Phase 4', '964309', 'waiting', 'Chana Masala', '720', '4', '2018/09/13/14/46/47', '0', '28.4802761', '77.080268', '0', '', '', 'f46m3cHRJYM:APA91bGBa1AF2IL5ED6p09qmiC3jqJvQWDr-CRJNLlxZza4VZdBtm8xqWgFE_5auhnotVi_yebGBoXRGL7Rg0aoE5z5gquXy4WwjZYeZxR3QSFObi6PsGnq0klWrbOdqfjZ8j59yBr9X', '110');

-- --------------------------------------------------------

--
-- Table structure for table `chef_product`
--

CREATE TABLE `chef_product` (
  `id` int(11) NOT NULL,
  `p_name` varchar(200) NOT NULL,
  `p_price` varchar(200) NOT NULL,
  `c_name` varchar(200) NOT NULL,
  `c_phone` varchar(100) NOT NULL,
  `c_address` varchar(300) NOT NULL,
  `delivery_date` varchar(200) NOT NULL,
  `order_date` varchar(200) NOT NULL,
  `chef_id` varchar(300) NOT NULL,
  `order_id` varchar(300) NOT NULL,
  `status` varchar(200) NOT NULL,
  `quantity` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `chef_product`
--

INSERT INTO `chef_product` (`id`, `p_name`, `p_price`, `c_name`, `c_phone`, `c_address`, `delivery_date`, `order_date`, `chef_id`, `order_id`, `status`, `quantity`) VALUES
(1, 'Almunium package', '130', 'santosh kumar ', '8826417738 ', 'chattarpur new delhi ', '2018/09/12/16/36/47', '2018/09/11/16/36/47', '984706', '7303028544', 'active', '1'),
(2, 'Almunium package', '130', 'santosh kumar ', '8826417738 ', 'chattarpur new delhi ', '2018/09/12/16/42/17', '2018/09/11/16/42/17', '984706', '8732099970', 'active', '1'),
(3, 'Almunium package', '260', 'santosh kumar ', '8826417738 ', 'chattarpur new  ', '2018/09/12/16/44/06', '2018/09/11/16/44/06', '984706', '5690930085', 'delivered', '2'),
(5, 'Foil Paper', '300', 'santosh kumar ', '8826417738 ', 'chattarpur new  ', '2018/09/12/16/51/56', '2018/09/11/16/51/56', '984706', '1969737006', 'active', '3'),
(6, 'Almunium package', '1040', 'santosh kumar ', '8826417738 ', 'chattarpur new  ', '2018/09/12/18/07/13', '2018/09/11/18/07/13', '984706', '8286006422', 'active', '8'),
(7, 'Foil Paper', '500', 'santosh kumar ', '8826417738 ', 'chattarpur new Delhi  ', '2018/09/12/19/35/16', '2018/09/11/19/35/16', '984706', '3252580818', 'active', '5');

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
(1, 'kalesh', 'chattarpur', '9987543679', '1234', 'f46m3cHRJYM:APA91bGBa1AF2IL5ED6p09qmiC3jqJvQWDr-CRJNLlxZza4VZdBtm8xqWgFE_5auhnotVi_yebGBoXRGL7Rg0aoE5z5gquXy4WwjZYeZxR3QSFObi6PsGnq0klWrbOdqfjZ8j59yBr9X');

-- --------------------------------------------------------

--
-- Table structure for table `meals`
--

CREATE TABLE `meals` (
  `id` int(11) NOT NULL,
  `food_name` varchar(200) NOT NULL,
  `price` varchar(100) NOT NULL,
  `food_image` varchar(250) NOT NULL,
  `items` varchar(300) NOT NULL,
  `meal_id` varchar(200) NOT NULL,
  `verify` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `meals`
--

INSERT INTO `meals` (`id`, `food_name`, `price`, `food_image`, `items`, `meal_id`, `verify`) VALUES
(8, 'Burger', '50', 'http://zeenarch.com/chef_hire77_980bia/images/burger.jpg', 'Veg Burger with sause', '2', '1'),
(9, 'Butter Chicken', '200', 'http://zeenarch.com/chef_hire77_980bia/images/butter_chiken.jpg', '4 pice chicken, with butter gravy', '3', '1'),
(11, 'Chowmein', '100', 'http://zeenarch.com/chef_hire77_980bia/images/Chowmein.jpg', 'Full plate veg chaumin', '4', '1'),
(12, 'Chana Masala', '150', 'http://zeenarch.com/chef_hire77_980bia/images/chana masala.jpg', 'Full plate chana masala', '5', '1'),
(13, 'Dosa', '70', 'http://zeenarch.com/chef_hire77_980bia/images/masala_dosa.jpg', 'masala dosha', '6', '1'),
(14, 'Matar Paneer', '150', 'http://zeenarch.com/chef_hire77_980bia/images/matar_paneer.jpg', 'Full plate matar paneer', '7', '1'),
(15, 'Rajma Chawal', '100', 'http://zeenarch.com/chef_hire77_980bia/images/rajma_chawal.jpg', 'rajma, chawal, green chatni, salad', '8', '1'),
(16, 'Kadi Chawal', '100', 'http://zeenarch.com/chef_hire77_980bia/images/kadi_chawal.JPG', 'kadi, chawal, salad', '9', '1'),
(17, 'Chili Chicken', '160', 'http://zeenarch.com/chef_hire77_980bia/images/chili_chicken.jpg', 'red chili chicken dry', '10', '1');

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
(3, '10', 'Aaloo Paratha/ chana ka dal, Plain rice, Aaloo bhujia, roti 4 pics/ Veg Kofta plain rice', 'Choole mater poori/ Rajma, plain rice, 4 roti, Sahi paneer, poori rice', 'Aaloo Paratha/ chana ka dal, Plain rice, Aaloo bhujia, roti 4 pics/ Veg Kofta plain rice', 'Choole mater poori/ Rajma, plain rice, 4 roti, Sahi paneer, poori rice', 'Aaloo Paratha/ chana ka dal, Plain rice, Aaloo bhujia, roti 4 pics/ Veg Kofta plain rice', 'Choole mater poori/ Rajma, plain rice, 4 roti, Sahi paneer, poori rice', 'Poori or Poha/Plain dal, rice, chana Masala/mater paneer, zeera rice and roti');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `p_name` varchar(200) NOT NULL,
  `p_image` varchar(300) NOT NULL,
  `p_price` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `p_name`, `p_image`, `p_price`) VALUES
(1, 'Foil Paper', 'http://zeenarch.com/chef_hire77_980bia/images/foil_paper.jpg', '100'),
(2, 'Almunium package', 'http://zeenarch.com/chef_hire77_980bia/images/aluminium.jpg', '10');

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
(5, 'Kitchenoid Tiffin Services', 'http://zeenarch.com/chef_hire77_980bia/service/images/onetiffin.jpg', '10', '50', '100', '100', 'food to offices, PG students and individuals', '908, B-9, ITL Tower, Netaji Subhash Place, New Delhi', '', '', '9650755331', 'delhi');

-- --------------------------------------------------------

--
-- Table structure for table `token_auth`
--

CREATE TABLE `token_auth` (
  `id` int(11) NOT NULL,
  `token` varchar(500) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `token_auth`
--

INSERT INTO `token_auth` (`id`, `token`) VALUES
(2, '8826417738.09-06-2018-09-55-27-am'),
(3, '9899485425.09-07-2018-04-44-06-am'),
(4, '9953238996.09-10-2018-11-35-14-am'),
(5, '9990757966.09-11-2018-12-43-18-pm');

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
(68, 'Jijbb', '855588', 'Bjjkk', '28.491333', '77.1924243', '', '', ''),
(69, 'santosh', '865368886', 'chattarpur ', '28.4906199', '77.1924209', 'dnjsjdk', '', ''),
(70, 'Santosh', '1234567890', 'Chattarpur ', '28.4293289', '77.1090065', '1234', 'santoshpro95@gmail.com', 'f46m3cHRJYM:APA91bGBa1AF2IL5ED6p09qmiC3jqJvQWDr-CRJNLlxZza4VZdBtm8xqWgFE_5auhnotVi_yebGBoXRGL7Rg0aoE5z5gquXy4WwjZYeZxR3QSFObi6PsGnq0klWrbOdqfjZ8j59yBr9X'),
(71, 'raju bacha', '9990757966', 'Jdjdjd', '28.4910992', '77.191914', 'jejdjs', 'santoshpro95@gmail.com', 'ellV5rpXGzI:APA91bGq3sf9l0jrf6ukEBeyrubQIN0zbTAsAf58tV0Ttg859GjbHs5aNL19Ups1TJqecPQgYlylA-IHRw3s83WwuzIkdsjz8LdyL7zy4JiwWOo6JcJ6MmZ-Vhp7HwJjpvc70fp6r7w0'),
(73, 'Santosh ', '8826417738', 'Chattarpur new Delhi ', '28.4802325', '77.08028', '1234', 'santoshpro95@gmail.com', 'f46m3cHRJYM:APA91bGBa1AF2IL5ED6p09qmiC3jqJvQWDr-CRJNLlxZza4VZdBtm8xqWgFE_5auhnotVi_yebGBoXRGL7Rg0aoE5z5gquXy4WwjZYeZxR3QSFObi6PsGnq0klWrbOdqfjZ8j59yBr9X'),
(74, 'Manmohan ', '9899485425', 'Dwarka', '28.4906142', '77.1924062', '1244', 'manmohan.0008@gmail.com', 'f2PwH2uraUo:APA91bHUpjte09IihYnHqeYK7fiQmBaZOh_FEi3s1DWY-4fozqyM02mbk25kKJqKS8YWMB6xSTgsLaGLuDW6htIUeC3-UMQZqB1LKUfawQnNM2uiEhaKgMAmKjWF1IRHMZyJER0Aw6Y6'),
(75, 'Sushant', '9953238996', 'Delhi', '28.4911565', '77.1925968', '123sushant', 'ravi@amail.club', 'f46m3cHRJYM:APA91bGBa1AF2IL5ED6p09qmiC3jqJvQWDr-CRJNLlxZza4VZdBtm8xqWgFE_5auhnotVi_yebGBoXRGL7Rg0aoE5z5gquXy4WwjZYeZxR3QSFObi6PsGnq0klWrbOdqfjZ8j59yBr9X');

-- --------------------------------------------------------

--
-- Table structure for table `user_coupons`
--

CREATE TABLE `user_coupons` (
  `id` int(11) NOT NULL,
  `name` varchar(200) NOT NULL,
  `coupon_id` varchar(300) NOT NULL,
  `exp_date` varchar(300) NOT NULL,
  `status` varchar(100) NOT NULL,
  `phone` varchar(200) NOT NULL,
  `discount` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

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
(4, '8826417738', '28.4913667', '77.1923899', 'Chattarpur rajpur mcd', 'santosh kumar behera '),
(6, '1234567890', '28.4906142', '77.1924062', 'Chattarpur ', 'Santosh');

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
(37, '1234567890', '10', '2018/09/06/07/36/33', '28.4912774', '77.1924899', '0', '1', '1', '1', '15', 'Kitchenoid Tiffin Services', '4500', 'active', 'Chattarpur ', '2018/09/20/07/36/33', ' 542685530', 'kalesh', '9987543679', '908, B-9, ITL Tower, Netaji Subhash Place, New Delhi', 'Santosh', '9650755331'),
(38, '1234567890', '10', '2018/09/10/19/08/55', '28.4912971', '77.1924757', '0', '0', '1', '0', '15', 'Kitchenoid Tiffin Services', '1800', 'active', 'Chattarpur ', '2018/09/24/19/08/55', ' 574170254', 'kalesh', '9987543679', '908, B-9, ITL Tower, Netaji Subhash Place, New Delhi', 'Santosh', '9650755331'),
(36, '8826417738', '10', '2018/09/05/15/35/57', '28.4800904', '77.0762182', '0', '1', '1', '0', '7', 'Kitchenoid Tiffin Services', '420', 'active', 'ram Chandra market ', '2018/09/11/15/35/57', '1259288406', 'kalesh', '9987543679', '908, B-9, ITL Tower, Netaji Subhash Place, New Delhi', 'santosh kumar', '9650755331');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

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
-- Indexes for table `chef_product`
--
ALTER TABLE `chef_product`
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
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `services`
--
ALTER TABLE `services`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `token_auth`
--
ALTER TABLE `token_auth`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_coupons`
--
ALTER TABLE `user_coupons`
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
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `chef`
--
ALTER TABLE `chef`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT for table `chef_meals`
--
ALTER TABLE `chef_meals`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=237;

--
-- AUTO_INCREMENT for table `chef_order`
--
ALTER TABLE `chef_order`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=180;

--
-- AUTO_INCREMENT for table `chef_product`
--
ALTER TABLE `chef_product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `delivery`
--
ALTER TABLE `delivery`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `meals`
--
ALTER TABLE `meals`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `menu`
--
ALTER TABLE `menu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `services`
--
ALTER TABLE `services`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `token_auth`
--
ALTER TABLE `token_auth`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=76;

--
-- AUTO_INCREMENT for table `user_coupons`
--
ALTER TABLE `user_coupons`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user_request`
--
ALTER TABLE `user_request`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `user_service`
--
ALTER TABLE `user_service`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
