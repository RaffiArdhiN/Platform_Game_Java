-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 21, 2024 at 11:41 AM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_score`
--

-- --------------------------------------------------------

--
-- Table structure for table `tscore`
--

CREATE TABLE `tscore` (
  `id` int(11) NOT NULL,
  `username` varchar(30) NOT NULL,
  `score` int(11) NOT NULL,
  `up` int(11) NOT NULL,
  `down` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tscore`
--

INSERT INTO `tscore` (`id`, `username`, `score`, `up`, `down`) VALUES
(1, 'NalarJalan', 1000, 200, 100),
(2, 'UseYourLogic', 800, 50, 80),
(3, 'NoJudgement', 700, 150, 40),
(6, 'hmm', 108, 1, 3),
(7, 'hah', 702, 13, 16),
(8, 'helo', 143, 3, 3),
(9, 'okee', 145, 3, 3),
(11, 'okeeh', 109, 2, 3),
(14, 'coba', 62, 1, 1),
(16, 'naise', 313, 6, 3),
(17, 'yangbelum', 177, 4, 2),
(19, 'coba2', 96, 2, 1),
(20, 'plisbisa', 191, 4, 3),
(21, 'gatau', 132, 1, 4),
(27, 'a', 69, 1, 1),
(28, 'aa', 92, 1, 3),
(29, 'rizan', 3819, 88, 13);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tscore`
--
ALTER TABLE `tscore`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tscore`
--
ALTER TABLE `tscore`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
