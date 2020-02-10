---- databse ----
USE gsol_app_db;

---- drop ----
DROP TABLE IF EXISTS `m_staff_basic_info`;
DROP TABLE IF EXISTS `m_staff_detail_info`;
DROP TABLE IF EXISTS `m_department`;
DROP TABLE IF EXISTS `m_position`;
DROP TABLE IF EXISTS `m_grade`;
DROP TABLE IF EXISTS `t_attendance`;
DROP TABLE IF EXISTS `m_attendance_setting`;
DROP TABLE IF EXISTS `m_staff`;

---- create ----
CREATE TABLE `m_staff`
(
    `staff_id` int not null auto_increment comment '社員ID',
    `name` varchar(15) comment '社員名',
    `name_kana` varchar(30) comment '社員名（かな）',
    `entered_date` date comment '入社日',
    `login_id` varchar(20) not null comment 'ログイン用ID',
    `password` varchar(20) not null comment 'パスワード',
    `create_at` timestamp not null default current_timestamp comment '登録日',
    `update_at` timestamp not null default current_timestamp on update current_timestamp comment '更新日',
    PRIMARY KEY(`staff_id`)
) DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `m_department`
(
    `department_id` int not null auto_increment comment '社員ID',
    `department` varchar(50) comment '部署名',
    `description` varchar(255) comment '説明文',
    `create_at` timestamp not null default current_timestamp comment '登録日',
    `update_at` timestamp not null default current_timestamp on update current_timestamp comment '更新日',
    PRIMARY KEY(`department_id`)
) DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `m_grade`
(
    `grade_id` int not null auto_increment comment 'グレードID',
    `grade` varchar(50) comment 'グレード名',
    `create_at` timestamp not null default current_timestamp comment '登録日',
    `update_at` timestamp not null default current_timestamp on update current_timestamp comment '更新日',
    PRIMARY KEY(`grade_id`)
) DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `m_position`
(
    `position_id` int not null auto_increment comment '役職ID',
    `position` varchar(50) comment '役職名',
    `create_at` timestamp not null default current_timestamp comment '登録日',
    `update_at` timestamp not null default current_timestamp on update current_timestamp comment '更新日',
    PRIMARY KEY(`position_id`)
) DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `m_staff_basic_info`
(
    `id` int not null auto_increment comment 'ID',
    `staff_id` int not null comment '社員ID',
    `staff_type_id` int not null default 1 comment '社員種別ID 0:admin, 1:nomal',
    `birthday` varchar(10) default 'yyyy-mm-dd' comment '誕生日',
    `telephone_number` varchar(13) default 'xxx-yyyy-zzzz' comment '電話番号',
    `mail_address` varchar(255) default 'mailadress@example.com' comment 'メールアドレス',
    `department_id` int default 0 comment '部署ID',
    `position_id` int default 0 comment '役職ID',
    `grade_id` int default 0 comment 'グレードID',
    `create_at` timestamp not null default current_timestamp comment '登録日',
    `update_at` timestamp not null default current_timestamp on update current_timestamp comment '更新日',
    PRIMARY KEY(`id`),
    CONSTRAINT fk_basic_info_staff_id  -- 制約の名前
        FOREIGN KEY (staff_id)  -- 外部キーに設定する絡む名
        REFERENCES m_staff (staff_id)  -- 参照するテーブルと絡む
) DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `m_staff_detail_info`
(
    `id` int not null auto_increment comment 'ID',
    `staff_id` int not null comment '社員ID',
    `create_at` timestamp not null default current_timestamp comment '登録日',
    `update_at` timestamp not null default current_timestamp on update current_timestamp comment '更新日',
    PRIMARY KEY(`id`),
    CONSTRAINT fk_detail_info_staff_id  -- 制約の名前
        FOREIGN KEY (staff_id)  -- 外部キーに設定する絡む名
        REFERENCES m_staff (staff_id)  -- 参照するテーブルと絡む
) DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `t_attendance`
(
    `attendance_id` int not null auto_increment comment '勤怠ID',
    `staff_id` int not null comment '社員ID',
    `create_at` timestamp not null default current_timestamp comment '登録日',
    `update_at` timestamp not null default current_timestamp on update current_timestamp comment '更新日',
    PRIMARY KEY(`attendance_id`),
    CONSTRAINT fk_attendance_staff_id  -- 制約の名前
        FOREIGN KEY (staff_id)  -- 外部キーに設定する絡む名
        REFERENCES m_staff (staff_id)  -- 参照するテーブルと絡む
) DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 勤務地、交通費、休憩時間など
CREATE TABLE `m_attendance_setting`
(
    `id` int not null auto_increment comment 'ID',
    `staff_id` int not null comment '社員ID',
    `create_at` timestamp not null default current_timestamp comment '登録日',
    `update_at` timestamp not null default current_timestamp on update current_timestamp comment '更新日',
    PRIMARY KEY(`id`),
    CONSTRAINT fk_attendance_setting_staff_id  -- 制約の名前
        FOREIGN KEY (staff_id)  -- 外部キーに設定する絡む名
        REFERENCES m_staff (staff_id)  -- 参照するテーブルと絡む
) DEFAULT CHARSET=utf8 COLLATE=utf8_bin;