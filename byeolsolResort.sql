-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: localhost    Database: resort
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `answer` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'answer 식별 id',
  `title` varchar(45) NOT NULL COMMENT 'answer의 title ',
  `message` text NOT NULL COMMENT '답변의 message',
  `question_id` int NOT NULL COMMENT '답변을 쓴 질문의 id',
  `writer` varchar(45) NOT NULL COMMENT '작성자',
  `reg_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성일 (입력 안할시 기본값 현재 시간)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '게시글의 식별 id',
  `title` varchar(45) NOT NULL COMMENT '게시글 제목',
  `content` text NOT NULL COMMENT '게시글 내용',
  `user_Id` varchar(45) NOT NULL COMMENT '작성자(유저 Id , 현재 로그인 중인 사용자의 ID)',
  `state` enum('admin','others') NOT NULL DEFAULT 'others' COMMENT '게시글이 admin이 작성한 건지 다른 사람이 작성한 건지 확인하기 위한 컬럼 ( null이라면 others , "others","admin"밖에 안됨)',
  `w_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성일 시간(입력 안할시 기본값 현재 시간)',
  `first_path` text COMMENT '첫번째 이미지 경로',
  `second_path` text COMMENT '두번째 이미지 경로',
  `third_path` text COMMENT '세번째 이미지 경로',
  `comment_count` int NOT NULL DEFAULT '0' COMMENT '댓글의 수(입력 하지 않을 시 기본 값 0)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '식별 id',
  `board_id` int NOT NULL COMMENT '게시글의 식별 id',
  `message` text NOT NULL COMMENT '댓글의 message',
  `user_id` varchar(45) NOT NULL COMMENT '작성자(로그인 되어있는 ID)',
  `w_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성일 (입력 안할시 기본값 현재 시간)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '식별 id',
  `user_id` varchar(45) NOT NULL COMMENT '유저의 ID',
  `password` varchar(45) NOT NULL COMMENT '비밀번호',
  `name` varchar(45) NOT NULL COMMENT '이름',
  `email` varchar(45) NOT NULL COMMENT '이메일',
  `zip_code` int NOT NULL COMMENT '우편번호',
  `address` varchar(45) NOT NULL COMMENT '주소',
  `address_detail` varchar(45) DEFAULT NULL COMMENT '상세 주소(기본값 null)',
  `phone` varchar(45) NOT NULL COMMENT '전화번호',
  `email_state` varchar(45) NOT NULL DEFAULT '미인증' COMMENT '이메일 인증 상태를 확인 하기 위한 컬럼(기본값 ''미인증'')',
  `birth_date` date NOT NULL COMMENT '생일',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'admin','admin04','진교범','gyobeom291@gmail.com',1234,'서울특별시 광진구 천호대로 107길 48-7',NULL,'01040449135','인증','1998-08-29'),(2,'test','password','별솔테스트','gyobeom29@gmail.com',3140,'서울특별시 종로구 수표로 105, 육의전빌딩','','01012345678','인증','1998-08-29'),(3,'test01','password01','진교범','wlsryqka@naver.com',3140,'서울특별시 종로구 수표로 105, 육의전빌딩','','01056789156','미인증','2015-07-01');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'event 식별 아이디',
  `title` varchar(45) NOT NULL COMMENT '이벤트의 제목',
  `img_path` text NOT NULL COMMENT '이벤트 ftp올린 이미지 경로',
  `reg_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성일 (입력 안할시 기본값 현재 시간)',
  `start_date` date NOT NULL COMMENT '이벤트 시작일',
  `end_date` date NOT NULL COMMENT '이벤트 종료일',
  `state` enum('상시','미상시') NOT NULL DEFAULT '미상시' COMMENT '이벤트가 상시 이벤트 인지  기간이 있는 이벤트 인지 확인 하는 컬럼 (기본값 ''미상시'')',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (1,'함께 이겨내요,코로나!','https://gyonewproject.000webhostapp.com/byeolsolResort/event/16_48_47_7895/코로나1(1~).png','2020-07-16 07:48:51','1850-01-01','1850-12-31','상시'),(2,'봄맞이 이벤트','https://gyonewproject.000webhostapp.com/byeolsolResort/event/16_49_48_4818/봄맞이1(3~4).png','2020-07-16 07:49:52','2020-03-18','2020-04-18','미상시'),(3,'오래 있으면 더~~즐겁다!','https://gyonewproject.000webhostapp.com/byeolsolResort/event/16_50_44_7722/31이벤트1(5~6).png','2020-07-16 07:50:48','2020-05-01','2020-06-30','미상시'),(4,'호캉스 이벤트!','https://gyonewproject.000webhostapp.com/byeolsolResort/event/16_55_03_9130/호캉스1(7~8).png','2020-07-16 07:55:07','2020-07-01','2020-08-30','미상시');
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event_img`
--

DROP TABLE IF EXISTS `event_img`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `event_img` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'event 임지 식병Id',
  `event_id` int NOT NULL COMMENT '썸네일을 올릴 event의 id',
  `img_path` text NOT NULL COMMENT 'ftp에 올린 썸네일 이미지 경로',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event_img`
--

LOCK TABLES `event_img` WRITE;
/*!40000 ALTER TABLE `event_img` DISABLE KEYS */;
INSERT INTO `event_img` VALUES (1,1,'https://gyonewproject.000webhostapp.com/byeolsolResort/event/event_1_thumbnail/코로나2(1~).png'),(2,2,'https://gyonewproject.000webhostapp.com/byeolsolResort/event/event_2_thumbnail/봄맞이2(3~4).png'),(3,3,'https://gyonewproject.000webhostapp.com/byeolsolResort/event/event_3_thumbnail/3+1이벤트2(5~6).png'),(4,4,'https://gyonewproject.000webhostapp.com/byeolsolResort/event/event_4_thumbnail/호캉스2(7~8).png');
/*!40000 ALTER TABLE `event_img` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'question 식별 iid',
  `title` varchar(45) NOT NULL COMMENT '질문의 제목',
  `message` text NOT NULL COMMENT '질문 내용',
  `division` varchar(45) NOT NULL COMMENT '분류',
  `writer` varchar(45) NOT NULL COMMENT '작성자',
  `reg_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성일 (입력 안할시 기본값 현재 시간)',
  `state` enum('yet','cea') NOT NULL DEFAULT 'yet' COMMENT '작성자가 질문을 하였을 때 관리자가 답변을 달아 주었는가 아닌가를 판단하기 위한 컬럼 (기본값 ''yet''  , yet 과 cea밖에 안들어감)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `remove`
--

DROP TABLE IF EXISTS `remove`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `remove` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'remove 식별 id',
  `user_id` varchar(45) NOT NULL COMMENT '사용자의 ID',
  `room_id` int NOT NULL COMMENT '방 id',
  `start_date` date NOT NULL COMMENT '입실일 ',
  `end_date` date NOT NULL COMMENT '종료일',
  `total_price` int NOT NULL COMMENT '입금 가격',
  `user_name` varchar(45) NOT NULL COMMENT 'customer의 이름',
  `user_phone` varchar(45) NOT NULL COMMENT 'customer의 전화번호',
  `state` enum('처리','미처리') NOT NULL DEFAULT '미처리' COMMENT '환불 처리를 했는지 판단 하기 위한 컬럼(기본값 ''미처리'')',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `remove`
--

LOCK TABLES `remove` WRITE;
/*!40000 ALTER TABLE `remove` DISABLE KEYS */;
/*!40000 ALTER TABLE `remove` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserv`
--

DROP TABLE IF EXISTS `reserv`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `reserv` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'reserv의 식별 id',
  `user_id` varchar(45) NOT NULL COMMENT 'customer의 ID',
  `room_id` int NOT NULL COMMENT 'room의 id',
  `start_date` date NOT NULL COMMENT '입실일',
  `end_date` date NOT NULL COMMENT '퇴실일',
  `total_price` int NOT NULL COMMENT '총 가격',
  `people_count` int NOT NULL COMMENT '인원수',
  `reg_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '예약일 (입력 안할시 기본값 현재 시간)',
  `state` enum('입금','미입금') NOT NULL DEFAULT '미입금' COMMENT '입금을 했는지 안했는지 판단하기 위한 컬럼(기본값 ''미입금'')',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserv`
--

LOCK TABLES `reserv` WRITE;
/*!40000 ALTER TABLE `reserv` DISABLE KEYS */;
/*!40000 ALTER TABLE `reserv` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '식별 id',
  `room_num` int NOT NULL COMMENT '방의 번호',
  `min_people` int NOT NULL COMMENT '최소 인원',
  `max_people` int NOT NULL COMMENT '최대 인원',
  `day_price` int NOT NULL COMMENT '평일 가격',
  `weekend_price` int NOT NULL COMMENT '주말 , 공휴일 , 성수기 가격',
  `concept` varchar(45) NOT NULL COMMENT '방의 컨셉',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,101,4,6,120000,150000,'kids'),(2,102,4,6,120000,150000,'kids'),(3,103,4,6,120000,150000,'kids'),(4,104,4,8,160000,190000,'kids'),(5,105,4,8,160000,190000,'kids'),(6,201,4,6,120000,150000,'game'),(7,202,4,6,120000,150000,'game'),(8,203,4,6,120000,150000,'game'),(9,204,4,8,160000,190000,'game'),(10,205,4,8,160000,190000,'game'),(11,301,4,6,120000,150000,'healing'),(12,302,4,6,120000,150000,'healing'),(13,303,4,6,120000,150000,'healing'),(14,304,4,8,160000,190000,'healing'),(15,305,4,8,160000,190000,'healing');
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-07-17 12:52:26
