-- phpMyAdmin SQL Dump
-- version 4.0.10.18
-- https://www.phpmyadmin.net
--
-- Host: localhost:3306
-- Generation Time: Jan 14, 2018 at 07:14 AM
-- Server version: 5.6.36-cll-lve
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `zeencrush`
--

-- --------------------------------------------------------

--
-- Table structure for table `friends`
--

CREATE TABLE IF NOT EXISTS `friends` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `sender_id` varchar(129) NOT NULL,
  `sender_name` varchar(255) DEFAULT NULL,
  `recipient_id` varchar(128) NOT NULL,
  `recipient_name` varchar(255) DEFAULT NULL,
  `alpha` varchar(10) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=282 ;

--
-- Dumping data for table `friends`
--

INSERT INTO `friends` (`ID`, `sender_id`, `sender_name`, `recipient_id`, `recipient_name`, `alpha`) VALUES
(184, '2296085800617843', 'Sushant Kumar', '12654', 'lori thakur', '3'),
(190, '175597413045950', 'Manbir Rathi', '107093673408546', 'Chanchal Prajapati', '3'),
(191, '175597413045950', 'Manbir Rathi', '113361396113584', 'Shivangi Chauhan', '3'),
(262, '1305207302935169', 'Santosh Kumar', '1860273867347173', 'Kulwinder Singh', '1'),
(266, '1652917904760934', 'Samarth Singh', '1305207302935169', 'Santosh Kumar', '1'),
(267, '1305207302935169', 'Santosh Kumar', '1652917904760934', 'Samarth Singh', '1'),
(268, '1860273867347173', 'Kulwinder Singh', '1305207302935169', 'Santosh Kumar', '1'),
(276, '1305207302935169', 'Santosh Kumar', '12654', 'lori thakur', '3'),
(277, '1305207302935169', 'Santosh Kumar', '340945', 'raju ', '3'),
(278, '1305207302935169', 'Santosh Kumar', '399800', 'lori thakur', '3'),
(279, '1305207302935169', 'Santosh Kumar', '234', 'Elisha Papabi', '3'),
(280, '1305207302935169', 'Santosh Kumar', '312814095893696', 'Jyoti Jain', '3'),
(281, '1305207302935169', 'Santosh Kumar', '2296085800617843', 'Sushant Kumar', '3');

-- --------------------------------------------------------

--
-- Table structure for table `images`
--

CREATE TABLE IF NOT EXISTS `images` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `fb_id` varchar(128) NOT NULL,
  `url` varchar(500) NOT NULL,
  `img_id` varchar(50) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Dumping data for table `images`
--

INSERT INTO `images` (`ID`, `fb_id`, `url`, `img_id`) VALUES
(2, '1305207302935169', 'http://zeenarch.com/zeencrush/php_files/upload_image/sent_user2.png', '2'),
(3, '167', 'http://zeenarch.com/zeencrush/php_files/upload_image/sent_user2.png', '1'),
(4, '1305207302935169', 'http://zeenarch.com/zeencrush/php_files/upload_image//go_live.png', '1'),
(8, '1305207302935169', 'http://zeenarch.com/zeencrush/php_files/upload_image/', '3'),
(9, '1305207302935169', 'http://zeenarch.com/zeencrush/php_files/upload_image/', '3'),
(10, '1305207302935169', 'http://zeenarch.com/zeencrush/php_files/upload_image/', '3'),
(11, '1305207302935169', 'http://zeenarch.com/zeencrush/php_files/upload_image/', '3'),
(12, '113361396113584', 'http://zeenarch.com/zeencrush/php_files/upload_image/', '4'),
(13, '113361396113584', 'http://zeenarch.com/zeencrush/php_files/upload_image/', '4'),
(14, '', 'http://zeenarch.com/zeencrush/php_files/upload_image/', '');

-- --------------------------------------------------------

--
-- Table structure for table `persons`
--

CREATE TABLE IF NOT EXISTS `persons` (
  `ID` int(100) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `fb_id` varchar(255) NOT NULL,
  `lat` varchar(255) DEFAULT NULL,
  `log` varchar(255) DEFAULT NULL,
  `gender` varchar(120) NOT NULL,
  `birthday` varchar(128) NOT NULL,
  `fcm_token` varchar(500) NOT NULL,
  `time` varchar(128) NOT NULL,
  `email` varchar(128) NOT NULL,
  `about` varchar(150) NOT NULL,
  `profession` varchar(200) NOT NULL,
  PRIMARY KEY (`fb_id`),
  UNIQUE KEY `id` (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=235 ;

--
-- Dumping data for table `persons`
--

INSERT INTO `persons` (`ID`, `name`, `fb_id`, `lat`, `log`, `gender`, `birthday`, `fcm_token`, `time`, `email`, `about`, `profession`) VALUES
(167, 'Anjali Sharma', '100681284052060', '28.4182917', '77.0861203', 'female', '23', 'c0rTV1A43ME:APA91bEb30_sJv7oWFRzTed6FmPcOPNX2H7X1LcU4C-nuQcAlzOIbIiEk6YeYQPeJLG1jKnqEfeFG4ztyhXvILPZIlU7efamJUj-ljet7DaLoBabgRDQTtnWfh00k11BtJ3cXWIp4YV1', '2018/01/13/02/22/42', '', '', ''),
(197, 'Kostantis Noulas', '10211012429082451', '38.0076934', '23.7790268', 'male', '28', 'cslylJ1j2K0:APA91bEIKBpGiD-WwzZMDR4M-vQSiPM3hEDkbcLK-hg72CeEtTDYr3B_MjqLv_pyZEuOV3Pp5wzvv6Lbmf8mqm-CRyNXdvf4JmlD8oioU_0-kJiFlhEML2MrNaXAxLIeyKyEWVnQQYGV', '2018/01/13/22/22/42', '', '', ''),
(160, 'Chanchal Prajapati', '107093673408546', '28.4182954', '77.0861205', 'female', '21', 'f_u6u5GEV0s:APA91bGk1GGU1YwYqVOHhrNaEcJGAiJflRsBUjP_MnqDCHZwkT5t4ebX9_3TXI15V8L9yOYeAiaZ8Nqp2_xjz5-UYegwmvIt9_W8ofd4a8t6Em8M86E3IF8BzX1iDW_-yNk3MYnCNYPu', '2018/01/13/08/21/42', '', '', ''),
(162, 'Shivangi Chauhan', '113361396113584', '28.4182879', '77.0861236', 'female', '20', 'dwVi_9baktA:APA91bFaQFXu2KVX-Zoxw_0mfB27is5i4d5rn-0xrsREICxpktpU7DJ8bSzVngKzkvR0WC7WKY8CYatpjVUg-XASgtN8TuiDzEbw5u4cUsq56n7gGnobGLxDQbUAS0Ds6EFmtoVwkxyz', '2018/01/13/02/21/42', '', '', ''),
(172, 'lori thakur', '12654', '28.4182879', '77.0861236', 'female', '22', '', '', '', '', ''),
(234, 'Santosh Kumar', '1305207302935169', '28.4913742', '77.1925961', 'male', '23', 'f6rH7qrScmc:APA91bHcGUtWnVNn1RhbUEeQnjCKnq0YRi3DTsJkbUpsmToD5ighmVy49PyoUcJBTbjoEoWRfZoeyOwXJ5LwnLWF8zVhvIS2umvDpW3JhCtItf-bDEGJaMM_ZiYPfiRaenAwJGytjke6', '2018/01/14/19/24/22', 'no email', 'awesome ', 'Android developer,at Reelover media ltd pvt.,passing out from GITM, gurgaon'),
(209, 'Samarth Singh', '1652917904760934', '28.4183573', '77.0860624', 'male', '22', 'cf8woiE4Xoo:APA91bEL0-kJPU5Zcxu1sX034Eu49j_2R38_HXFQ04dmNoSpBmlo1hSFsgrfuly4f7_WRF4snhGT0cGV6Y88mK2weSD6gW5uOGAAVdvk8BCOyrKhWcmLR_q6UzikhPHDTY9W45sXTtl5', '2018/01/12/18/47/11', 'email', '', ''),
(184, 'Manbir Rathi', '175597413045950', '27.0987', '77.9087', 'male', '22', 'fXvFB6oDJ34:APA91bESayBQ_XkeniW4XM2kCcvOK9oIN2dWDIZLz2-ewm2KUdNAk8rrhrs87tsn_rCAGFAZ_TaGntTj2sCH3n8gGudR1YCifNg3NSNz9qI0PoodYc-MeGr9C5qm9Fu0U1sUYL3NZ1Fb', '2018/01/13/22/01/42', '', '', ''),
(206, 'Kulwinder Singh', '1860273867347173', '28.4984578', '77.0807734', 'male', '22', 'c0Pc2Hs3iKE:APA91bHyM0f80AXU5NFuvSwU6T6CrIcdOO4Q8Z5Vt0I9OnWhFQCRgYGKpySDdBsoEsgSOtzkDiQO6fxGtPyGAPuaIJcoyWoK_GvtcSlM18crSxe7DZ6YKwTvXLasD9jAVfueY5hccuBp', '2018/01/12/18/01/38', '', '', ''),
(168, 'Elisha Papabi', '198', '28.4183444', '77.0861236', 'female', '20', '', '2018/01/13/21/21/42', '', '', ''),
(215, 'Sushant Kumar', '2296085800617843', '28.491385', '77.1925922', 'male', '20', 'cDKL8RQRv8s:APA91bEe_qEIMuQ061PDYqoOQdWi7idAxii6uwjmsLZE1PBUjQ1Ra0KTmHix1KqxyV_Cznc5W1wdFkqcxmf3vicQ32027mrP5oqnLa7NSRvS_V7f_sY2gQH7Q8qCGN5ex4iCuYivhkrS', '2018/01/14/00/13/15', 'email', 'this is about', 'i am a college student'),
(166, 'Elisha Papabi', '234', '28.4914749', '77.1925137', 'female', '18', '', '2018/01/03/22/21/42', '', '', ''),
(194, 'Jyoti Jain', '312814095893696', '28.5314716', '77.0863592', 'female', '18', 'd2Nnh7LDhEY:APA91bGLrdDSQ6yoT1ZU-x383sIjDeoF0vXtm8Ax58rNG9IzOQ-dWJS2JdpMU-0dA79wPsa-ZYulVpgEf_gI2y4TCpji2rNHpHQuhHU8AShXnh_3VOtkBjMmRO_-kNDhXdhOsJYHAgl3', '2018/01/13/22/21/42', '', '', ''),
(174, 'salu', '32445', '38.898556', '77.037852', 'female', '26', '', '2018/01/13/22/11/42', '', '', ''),
(170, 'salu', '3245345', '38.898556', '77.037852', 'female', '28', '', '2018/01/13/22/21/42', '', '', ''),
(175, 'raju ', '340945', '38.898556', '77.037852', 'male', '30', '', '2018/01/13/12/21/42', '', '', ''),
(171, 'raju ', '345345', '38.898556', '77.037852', 'male', '32', '', '2018/01/13/22/21/42', '', '', ''),
(173, 'lori thakur', '399800', '38.898556', '77.037852', 'female', '32', '', '2018/01/13/22/21/42', '', '', ''),
(169, 'Elisha Brown', '549', '28.4183444', '77.0861236', 'female', '33', '', '2018/01/13/22/21/42', '', '', ''),
(161, 'Elisha Brown', '7890', '28.4183444', '77.0861236', 'female', '34', '', '2018/01/13/22/21/12', '', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `persons_chat`
--

CREATE TABLE IF NOT EXISTS `persons_chat` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `my_fb_id` varchar(255) NOT NULL,
  `chat` varchar(255) DEFAULT NULL,
  `friend_fb_id` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=221 ;

--
-- Dumping data for table `persons_chat`
--

INSERT INTO `persons_chat` (`ID`, `my_fb_id`, `chat`, `friend_fb_id`) VALUES
(153, '207014809866109', ' bsjs', '1652917904760934'),
(154, '207014809866109', 'hello', '1652917904760934'),
(155, '1652917904760934', 'yo', '207014809866109'),
(156, '1652917904760934', 'kesa hai', '207014809866109'),
(157, '207014809866109', 'yoo', '1652917904760934'),
(158, '1652917904760934', 'yoo', '207014809866109'),
(159, '1652917904760934', 'kesa hai', '207014809866109'),
(160, '207014809866109', 'lok', '1652917904760934'),
(161, '1652917904760934', 'londo', '207014809866109'),
(162, '207014809866109', 'ðŸ˜‚ ', '1652917904760934'),
(163, '1652917904760934', 'lol', '207014809866109'),
(164, '1652917904760934', 'ðŸ˜ŠðŸ˜Š', '207014809866109'),
(165, '1652917904760934', 'ðŸ˜„ðŸ˜„ðŸ˜„', '207014809866109'),
(166, '1305207302935169', 'hii', '113361396113584'),
(167, '1305207302935169', 'hekd', '113361396113584'),
(184, '1305207302935169', 'hskdia', '2296085800617843'),
(185, '1305207302935169', 'sj', '2296085800617843'),
(186, '1305207302935169', 'helli', '2296085800617843'),
(199, '1305207302935169', 'hello', '1860273867347173'),
(200, '1305207302935169', 'ha bhai', '1860273867347173'),
(201, '1305207302935169', 'bhai tere pas notification ja rahi h na ?', '1860273867347173'),
(202, '1860273867347173', 'haan', '1305207302935169'),
(203, '1305207302935169', 'okay', '1860273867347173'),
(204, '1305207302935169', 'ðŸ˜ðŸ˜ðŸ˜', '1860273867347173'),
(209, '', '', ''),
(210, '1305207302935169', 'i just relase new version', '1860273867347173'),
(211, '1305207302935169', 'update bro ', '1860273867347173'),
(212, '1305207302935169', 'jdjdk', '1860273867347173'),
(213, '1305207302935169', 'hello', '1860273867347173'),
(214, '1305207302935169', 'test', '1860273867347173'),
(220, '1305207302935169', 'good morning', '312814095893696');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
