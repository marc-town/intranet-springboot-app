---- databse ----
USE gsol_app_db;

---- drop ----
DROP TABLE IF EXISTS `m_staff_basic_info`;
DROP TABLE IF EXISTS `m_staff_detail_info`;
DROP TABLE IF EXISTS `m_department`;
DROP TABLE IF EXISTS `m_position`;
DROP TABLE IF EXISTS `m_grade`;
DROP TABLE IF EXISTS `t_attendance`;
DROP TABLE IF EXISTS `m_absence_type`;
DROP TABLE IF EXISTS `m_attendance_setting`;
DROP TABLE IF EXISTS `m_staff`;

---- create ----
CREATE TABLE `m_staff`
(
    `staff_id` int not null auto_increment comment '社員ID',
    `mail_address` varchar(255) UNIQUE comment 'メールアドレス',
    `login_id` varchar(255) not null UNIQUE comment 'ログイン用ID',
    `password` varchar(255) not null comment 'パスワード',
    `create_at` timestamp not null default current_timestamp comment '登録日',
    `update_at` timestamp not null default current_timestamp on update current_timestamp comment '更新日',
    PRIMARY KEY(`staff_id`)
) DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `m_department`
(
    `department_id` int not null auto_increment comment '社員ID',
    `department_name` varchar(50) comment '部署名',
    `description` varchar(255) comment '説明文',
    `create_at` timestamp not null default current_timestamp comment '登録日',
    `update_at` timestamp not null default current_timestamp on update current_timestamp comment '更新日',
    PRIMARY KEY(`department_id`)
) DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `m_position`
(
    `position_id` int not null auto_increment comment '役職ID',
    `position_name` varchar(50) comment '役職名',
    `create_at` timestamp not null default current_timestamp comment '登録日',
    `update_at` timestamp not null default current_timestamp on update current_timestamp comment '更新日',
    PRIMARY KEY(`position_id`)
) DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `m_grade`
(
    `grade_id` int not null auto_increment comment 'グレードID',
    `grade_name` varchar(50) comment 'グレード名',
    `create_at` timestamp not null default current_timestamp comment '登録日',
    `update_at` timestamp not null default current_timestamp on update current_timestamp comment '更新日',
    PRIMARY KEY(`grade_id`)
) DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE `m_absence_type`
(
    `absence_type_id` int not null auto_increment comment 'ID',
    `absence_type_name` varchar(255) comment '欠勤種別名',
    `create_at` timestamp not null default current_timestamp comment '登録日',
    `update_at` timestamp not null default current_timestamp on update current_timestamp comment '更新日',
    PRIMARY KEY(`absence_type_id`)
) DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `m_staff_basic_info`
(
    `staff_basic_info_id` int not null auto_increment comment 'ID',
    `staff_id` int not null comment '社員ID',
    `name` varchar(100) comment '社員名',
    `name_kana` varchar(100) comment '社員名（かな）',
    `entered_date` varchar(10) default 'yyyy-mm-dd' comment '入社日',
    `staff_type_id` int not null default 1 comment '社員種別ID 0:admin, 1:nomal',
    `birthday` varchar(10) default 'yyyy-mm-dd' comment '誕生日',
    `telephone_number` varchar(13) default 'xxx-yyyy-zzzz' comment '電話番号',
    `department_id` int comment '部署ID',
    `position_id` int comment '役職ID',
    `grade_id` int default 0 comment '階級ID',
    `create_at` timestamp not null default current_timestamp comment '登録日',
    `update_at` timestamp not null default current_timestamp on update current_timestamp comment '更新日',
    PRIMARY KEY(`staff_basic_info_id`),
    CONSTRAINT fk_basic_info_staff_id  -- 制約の名前
        FOREIGN KEY (staff_id)  -- 外部キーに設定するカラム名
        REFERENCES m_staff (staff_id)  -- 参照するテーブルとカラム
) DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `m_staff_detail_info`
(
    `staff_detail_info_id` int not null auto_increment comment 'ID',
    `staff_id` int not null comment '社員ID',
    `hobby` varchar(255) comment '趣味',
    `interest` varchar(255) comment '興味のある仕事',
    `project_summary` text comment '案件概要',
    `create_at` timestamp not null default current_timestamp comment '登録日',
    `update_at` timestamp not null default current_timestamp on update current_timestamp comment '更新日',
    PRIMARY KEY(`staff_detail_info_id`),
    CONSTRAINT fk_detail_info_staff_id  -- 制約の名前
        FOREIGN KEY (staff_id)  -- 外部キーに設定するカラム名
        REFERENCES m_staff (staff_id)  -- 参照するテーブルとカラム
) DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `t_attendance`
(
    `attendance_id` int not null auto_increment comment '勤怠ID',
    `year_month` char(6) not null default '190001' comment '年月',
    `day` char(2) not null default '01' comment '日付',
    `staff_id` int not null comment '社員ID',
    `start_time` char(5) default '09:00' comment '始業時間',
    `end_time` char(5) default '18:00' comment '終業時間',
    `rest_time` decimal(4, 2) comment '休憩時間',
    `absence_type_id` int comment '欠勤種別',
    `absence_reason` varchar(255) comment '欠勤理由',
    `working_time` decimal(4, 2) comment '労働時間',
    `night_time` decimal(4, 2) comment '夜間時間',
    `operating_expenses` int comment '営業費用',
    `section` varchar(255) comment '営業区間',
    `remarks` varchar(255) comment '備考',
    `create_at` timestamp not null default current_timestamp comment '登録日',
    `update_at` timestamp not null default current_timestamp on update current_timestamp comment '更新日',
    PRIMARY KEY(`attendance_id`),
    CONSTRAINT fk_attendance_staff_id  -- 制約の名前
        FOREIGN KEY (staff_id)  -- 外部キーに設定するカラム名
        REFERENCES m_staff (staff_id),  -- 参照するテーブルとカラム
    CONSTRAINT fk_attendance_absence_type_id  -- 制約の名前
        FOREIGN KEY (absence_type_id)  -- 外部キーに設定するカラム名
        REFERENCES m_absence_type (absence_type_id)  -- 参照するテーブルとカラム
) DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `m_attendance_setting`
(
    `attendance_setting_id` int not null auto_increment comment 'ID',
    `staff_id` int not null comment '社員ID',
    `home_nearest` varchar(100) comment '自宅最寄り駅',
    `working_nearest` varchar(100) comment '現場最寄り駅',
    `transportation_expenses` int default 0 comment '交通費',
    `working_place` varchar(100) comment '現場名',
    `regular_start_time` varchar(5) default '09:00' comment '始業定時',
    `regular_end_time` varchar(5) default '17:30' comment '就業定時',
    `create_at` timestamp not null default current_timestamp comment '登録日',
    `update_at` timestamp not null default current_timestamp on update current_timestamp comment '更新日',
    PRIMARY KEY(`attendance_setting_id`),
    CONSTRAINT fk_attendance_setting_staff_id  -- 制約の名前
        FOREIGN KEY (staff_id)  -- 外部キーに設定するカラム名
        REFERENCES m_staff (staff_id)  -- 参照するテーブルとカラム
) DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

---- insert ----

-- m_department
INSERT INTO m_department (department_name) VALUES
("開発事業部"),
("インフラ事業部"),
("営業部"),
("人事部"),
("総務部")
;

-- m_position
INSERT INTO m_position (position_name) VALUES
("会長"),
("平社員"),
("雑用")
;

-- m_grade
INSERT INTO m_grade (grade_name) VALUES
("Platinum"),
("Gold"),
("Silver"),
("Bronze"),
("Green")
;

-- m_absence_type
INSERT INTO m_absence_type (absence_type_name) VALUES
("欠勤"),
("代休"),
("有給"),
("忌引")
;

-- m_staff
INSERT INTO m_staff (login_id, password) VALUES
("admin", "admin"),
("takenomikazuchi", "takenomikazuchi")
;

-- m_staff_basic_info
INSERT INTO m_staff_basic_info (staff_id, name, name_kana, entered_date, staff_type_id, department_id, position_id, grade_id) VALUES
(1, "管理者", "かんりしゃ", "2020-02-11", 0, 3, 1, 1),
(2, "タケノミカヅチ", "たけちゃん", "2020-02-11", 0, 3, 1, 1)
;

INSERT INTO t_attendance (`year_month`, `day`, `staff_id`, `start_time`, `end_time`, `rest_time`, `absence_type_id`, `working_time`) VALUES
("202002", "01", 1, null, null, 0, null, 0),
("202002", "02", 1, null, null, 0, null, 0),
("202002", "03", 1, "09:00", "18:00", 1, null, 8.0),
("202002", "04", 1, "09:00", "18:00", 1, null, 8.0),
("202002", "05", 1, "09:00", "18:00", 1, null, 8.0),
("202002", "06", 1, "09:00", "18:00", 1, null, 8.0),
("202002", "07", 1, "09:00", "18:00", 1, null, 8.0),
("202002", "08", 1, null, null, 0, null, 0),
("202002", "09", 1, null, null, 0, null, 0),
("202002", "10", 1, null, null, 0, 1, 0),
("202002", "11", 1, null, null, 0, null, 0),
("202002", "12", 1, "09:00", "18:00", 1, null, 8.0),
("202002", "13", 1, "09:00", "18:00", 1, null, 8.0),
("202002", "14", 1, "09:00", "18:00", 1, null, 8.0),
("202002", "15", 1, null, null, 0, null, 0),
("202002", "16", 1, null, null, 0, null, 0),
("202002", "17", 1, "09:00", "18:00", 1, null, 8.0),
("202002", "18", 1, "09:00", "18:00", 1, null, 8.0),
("202002", "19", 1, "09:00", "18:00", 1, null, 8.0),
("202002", "20", 1, "09:00", "18:00", 1, null, 8.0),
("202002", "21", 1, "09:00", "18:00", 1, null, 8.0),
("202002", "22", 1, null, null, 0, null, 0),
("202002", "23", 1, null, null, 0, null, 0),
("202002", "24", 1, null, null, 0, null, 0),
("202002", "25", 1, "09:00", "18:00", 1, null, 8.0),
("202002", "26", 1, "09:00", "18:00", 1, null, 8.0),
("202002", "27", 1, "09:00", "18:00", 1, null, 8.0),
("202002", "28", 1, "09:00", "18:00", 1, null, 8.0),
("202002", "29", 1, "09:00", "18:00", 1, null, 8.0)
;