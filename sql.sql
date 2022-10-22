// auto increment 초기화

ALTER TABLE `table_name` auto_increment = 1;
SET @COUNT = 0;
UPDATE `table_name` SET `table_column` = @COUNT:=@COUNT+1;

// 권한부여
GRANT ALL PRIVILEGES ON `%`.* TO 'root'@'%' IDENTIFIED BY '1234' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON *.* to 'root'@'%' IDENTIFIED BY '1234';
SET PASSWORD FOR 'root'@'localhost' = PASSWORD('1234');

// 스키마 생성
create schema `simpleweb` default character set utf8;

CREATE TABLE `member` (
  `member_no` int NOT NULL AUTO_INCREMENT,
  `member_id` varchar(50) DEFAULT NULL,
  `member_pwd` varchar(255) DEFAULT NULL,
  `member_email` varchar(100) DEFAULT NULL,
  `member_nickname` varchar(50) DEFAULT NULL,
  `member_mobile` varchar(45) DEFAULT NULL,
  `member_job` varchar(45) DEFAULT NULL,
  `member_gender` varchar(45) DEFAULT NULL,
  `member_introduce` varchar(200) DEFAULT NULL,
  `member_date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`member_no`)
);

CREATE TABLE `member_profileimg` (
  `member_profileimg_no` int NOT NULL AUTO_INCREMENT,
  `member_no` int DEFAULT NULL,
  `member_profileimg_filename` varchar(200) DEFAULT NULL,
  `member_profileimg_original_filename` varchar(200) DEFAULT NULL,
  `member_profileimg_url` varchar(300) DEFAULT NULL,
  `member_profileimg_date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`member_profileimg_no`),
  KEY `member_member_no_idx` (`member_no`),
  CONSTRAINT `member_profileimg_member_member_no` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `post` (
  `post_no` int NOT NULL AUTO_INCREMENT,
  `member_no` int DEFAULT NULL,
  `post_title` varchar(200) DEFAULT NULL,
  `post_subtitle` varchar(200) DEFAULT NULL,
  `post_contents` varchar(300) DEFAULT NULL,
  `post_date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`post_no`),
  KEY `member_member_no_idx` (`member_no`),
  CONSTRAINT `post_member_member_no` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `post_img` (
  `post_img_no` int NOT NULL AUTO_INCREMENT,
  `post_no` int DEFAULT NULL,
  `post_img_filename` varchar(200) DEFAULT NULL,
  `post_img_original_filename` varchar(200) DEFAULT NULL,
  `post_img_url` varchar(300) DEFAULT NULL,
  `post_img_date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`post_img_no`),
  KEY `post_img_post_post_no_idx` (`post_no`),
  CONSTRAINT `post_img_post_post_no` FOREIGN KEY (`post_no`) REFERENCES `post` (`post_no`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `post_hashtag` (
  `post_hashtag_no` int NOT NULL AUTO_INCREMENT,
  `post_no` int DEFAULT NULL,
  `post_hashtag_list` varchar(100) DEFAULT NULL,
  `post_hashtag_division` int DEFAULT NULL,
  PRIMARY KEY (`post_hashtag_no`),
  KEY `post_menu_hashtag_post_no_idx` (`post_no`),
  CONSTRAINT `post_hashtag_post_post_no` FOREIGN KEY (`post_no`) REFERENCES `post` (`post_no`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `like_stat` (
  `like_stat_no` int NOT NULL AUTO_INCREMENT,
  `member_no` int DEFAULT NULL,
  `post_no` int DEFAULT NULL,
  `like_stat_check` int DEFAULT NULL,
  PRIMARY KEY (`like_stat_no`),
  KEY `like_member_member_no_idx` (`member_no`),
  KEY `like_post_post_no_idx` (`post_no`),
  CONSTRAINT `like_stat_member_member_no` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `like_stat_post_post_no` FOREIGN KEY (`post_no`) REFERENCES `post` (`post_no`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `bookmark` (
  `bookmark_no` int NOT NULL AUTO_INCREMENT,
  `member_no` int DEFAULT NULL,
  `post_no` int DEFAULT NULL,
  `bookmark_check` int DEFAULT NULL,
  `bookmark_date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`bookmark_no`),
  KEY `bookmark_member_member_no_idx` (`member_no`),
  KEY `bookmark_post_post_no_idx` (`post_no`),
  CONSTRAINT `bookmark_member_member_no` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `bookmark_post_post_no` FOREIGN KEY (`post_no`) REFERENCES `post` (`post_no`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `comment` (
  `comment_no` int NOT NULL AUTO_INCREMENT,
  `member_no` int DEFAULT NULL,
  `post_no` int DEFAULT NULL,
  `comment_text` varchar(200) DEFAULT NULL,
  `comment_date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`comment_no`),
  KEY `comment_member_member_no_idx` (`member_no`),
  KEY `comment_post_post_no_idx` (`post_no`),
  CONSTRAINT `comment_member_member_no` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_post_post_no` FOREIGN KEY (`post_no`) REFERENCES `post` (`post_no`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `comment_like_stat` (
  `comment_like_stat_no` int NOT NULL AUTO_INCREMENT,
  `member_no` int DEFAULT NULL,
  `post_no` int DEFAULT NULL,
  `comment_no` int DEFAULT NULL,
  `comment_like_stat_check` int DEFAULT NULL,
  `comment_like_stat_date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`comment_like_stat_no`),
  KEY `comment_like_stat_member_no_idx` (`member_no`),
  KEY `comment_like_stat_comment_no_idx` (`comment_no`),
  KEY `comment_like_stat_post_no_idx` (`post_no`),
  CONSTRAINT `comment_like_stat_comment_no` FOREIGN KEY (`comment_no`) REFERENCES `comment` (`comment_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_like_stat_member_no` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `comment_like_stat_post_no` FOREIGN KEY (`post_no`) REFERENCES `post` (`post_no`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `follow` (
  `follow_no` int NOT NULL AUTO_INCREMENT,
  `member_no` int DEFAULT NULL,
  `follow_member_no` int DEFAULT NULL,
  `follow_date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`follow_no`),
  KEY `follow_member_member_no_idx` (`member_no`),
  KEY `follow(you)_member_member_no_idx` (`follow_member_no`),
  CONSTRAINT `follow(me)_member_member_no` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `follow(you)_member_member_no` FOREIGN KEY (`follow_member_no`) REFERENCES `member` (`member_no`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `chatroom` (
  `chatroom_no` int NOT NULL AUTO_INCREMENT,
  `chatroom_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`chatroom_no`)
);

CREATE TABLE `chatroom_member` (
  `chatroom_member_no` int NOT NULL AUTO_INCREMENT,
  `chatroom_no` int DEFAULT NULL,
  `member_no` int DEFAULT NULL,
  `chatroom_alarm` int DEFAULT NULL,
  PRIMARY KEY (`chatroom_member_no`),
  KEY `chatroom_member_member_no_idx` (`member_no`),
  KEY `chatroom_member_chatroom_no_idx` (`chatroom_no`),
  CONSTRAINT `chatroom_member_chatroom_no` FOREIGN KEY (`chatroom_no`) REFERENCES `chatroom` (`chatroom_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `chatroom_member_member_no` FOREIGN KEY (`member_no`) REFERENCES `member` (`member_no`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `chatlog` (
  `chatlog_no` int NOT NULL AUTO_INCREMENT,
  `member_no` int DEFAULT NULL,
  `chatroom_no` int DEFAULT NULL,
  `chatlog_log` varchar(500) DEFAULT NULL,
  `chatlog_division` varchar(30) DEFAULT NULL,
  `chatlog_split_date` varchar(45) DEFAULT NULL,
  `chatlog_split_time` varchar(45) DEFAULT NULL,
  `chatlog_date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`chatlog_no`),
  KEY `chatlog_chatroom_no_idx` (`chatroom_no`),
  CONSTRAINT `chatlog_chatroom_chatroom_no` FOREIGN KEY (`chatroom_no`) REFERENCES `chatroom` (`chatroom_no`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `chat_filelist` (
  `chat_filelist_no` int NOT NULL AUTO_INCREMENT,
  `member_no` int DEFAULT NULL,
  `chatroom_no` int DEFAULT NULL,
  `chat_filelist_filename` varchar(200) DEFAULT NULL,
  `chat_filelist_original_filename` varchar(200) DEFAULT NULL,
  `chat_filelist_url` varchar(300) DEFAULT NULL,
  `chat_filelist_date` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`chat_filelist_no`),
  KEY `chat_filelist_chatlog_chatlog_log_idx` (`chat_filelist_filename`)
);

CREATE TABLE `alarm_chat` (
  `alarm_no` int NOT NULL AUTO_INCREMENT,
  `alarm_member_no` int DEFAULT NULL,
  `alarm_division` varchar(45) DEFAULT NULL,
  `alarm_contents_pk` int DEFAULT NULL,
  `chatroom_no` int DEFAULT NULL,
  PRIMARY KEY (`alarm_no`),
  KEY `alarm_member_no_idx` (`alarm_member_no`),
  KEY `alarm_chatroom_no_idx` (`chatroom_no`),
  CONSTRAINT `alarm_chatroom_no` FOREIGN KEY (`chatroom_no`) REFERENCES `chatroom` (`chatroom_no`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `alarm_member_no` FOREIGN KEY (`alarm_member_no`) REFERENCES `member` (`member_no`) ON DELETE CASCADE ON UPDATE CASCADE
);


















