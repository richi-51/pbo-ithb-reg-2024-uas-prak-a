-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 09, 2025 at 05:27 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_uas_1123051`
--

-- --------------------------------------------------------

--
-- Table structure for table `categorypackage`
--

CREATE TABLE `categorypackage` (
  `category` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `categorypackage`
--

INSERT INTO `categorypackage` (`category`) VALUES
('Regular'),
('Fast'),
('Super Fast');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `phone` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id`, `password`, `name`, `email`, `address`, `phone`) VALUES
(1, 'password123', 'John Doe', 'johndoe@gmail.com', 'Jl. Merdeka No.1', '081234567890'),
(2, 'password456', 'Jane Smith', 'janesmith@yahoo.com', 'Jl. Pahlawan No.5', '081345678901'),
(3, 'password789', 'Alice Brown', 'alicebrown@hotmail.com', 'Jl. Sudirman No.10', '081456789012'),
(4, 'password321', 'Bob Johnson', 'bobjohnson@gmail.com', 'Jl. Gatot Subroto No.3', '081567890123'),
(5, 'password654', 'Emily Davis', 'emilydavis@yahoo.com', 'Jl. Diponegoro No.7', '081678901234'),
(6, '123', 'Adrian', 'eidorian@example.com', 'Jalan jalan', '081234567');

-- --------------------------------------------------------

--
-- Table structure for table `shipmentdetails`
--

CREATE TABLE `shipmentdetails` (
  `id` int(11) NOT NULL,
  `transaction_id` varchar(255) NOT NULL,
  `status` enum('pending','transit','delivered') NOT NULL,
  `current_position` varchar(255) NOT NULL,
  `evidence` varchar(255) DEFAULT NULL,
  `date` date NOT NULL,
  `updated_by` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `shipmentdetails`
--

INSERT INTO `shipmentdetails` (`id`, `transaction_id`, `status`, `current_position`, `evidence`, `date`, `updated_by`) VALUES
(1, 'TXN001', 'pending', 'Jakarta', 'evidence1.jpg', '2025-01-02', 'Admin1'),
(2, 'TXN002', 'transit', 'Bandung', 'evidence2.jpg', '2025-01-03', 'Admin2'),
(3, 'TXN003', 'delivered', 'Surabaya', 'evidence3.jpg', '2025-01-04', 'Admin3'),
(4, 'TXN004', 'pending', 'Yogyakarta', NULL, '2025-01-05', 'Admin4'),
(5, 'TXN005', 'transit', 'Medan', 'evidence5.jpg', '2025-01-06', 'Admin5'),
(6, 'TXN001', 'pending', 'Gatau', 'D:\\Trace Maze-1 BFS.png', '2025-01-09', 'Person');

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `id` varchar(255) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `package_type` varchar(50) NOT NULL,
  `package_weight` double NOT NULL,
  `total_cost` int(11) NOT NULL,
  `created_at` date NOT NULL,
  `receipt_name` varchar(255) NOT NULL,
  `receipt_address` varchar(255) NOT NULL,
  `receipt_phone` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`id`, `customer_id`, `package_type`, `package_weight`, `total_cost`, `created_at`, `receipt_name`, `receipt_address`, `receipt_phone`) VALUES
('PER-TX0', 1, 'Regular', 3.4, 3, '2025-01-09', 'Andi', 'Jalan jalan', '081234567'),
('TXN001', 1, 'Regular', 1.5, 20000, '2025-01-01', 'Sarah Doe', 'Jl. Kemerdekaan No.2', '081789012345'),
('TXN002', 2, 'Fast', 2, 40000, '2025-01-02', 'Michael Smith', 'Jl. Ahmad Yani No.4', '081890123456'),
('TXN003', 3, 'Super Fast', 0.8, 30000, '2025-01-03', 'Lucy Brown', 'Jl. Panjang No.8', '081901234567'),
('TXN004', 4, 'Regular', 1.2, 22000, '2025-01-04', 'Tom Johnson', 'Jl. Diponegoro No.12', '081012345678'),
('TXN005', 5, 'Fast', 3.5, 70000, '2025-01-05', 'Emma Davis', 'Jl. Thamrin No.15', '081123456789');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `shipmentdetails`
--
ALTER TABLE `shipmentdetails`
  ADD PRIMARY KEY (`id`),
  ADD KEY `transaction_id` (`transaction_id`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`id`),
  ADD KEY `customer_id` (`customer_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `shipmentdetails`
--
ALTER TABLE `shipmentdetails`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `shipmentdetails`
--
ALTER TABLE `shipmentdetails`
  ADD CONSTRAINT `shipmentdetails_ibfk_1` FOREIGN KEY (`transaction_id`) REFERENCES `transaction` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
