create database if not exists `jwro27_students_jdbc`;

use `jwro27_students_jdbc`;

create table if not exists `students` (
`id` INT AUTO_INCREMENT PRIMARY KEY,
`first_name` VARCHAR(30) NOT NULL,
`last_name` VARCHAR(30) NOT NULL,
`height` DECIMAL(10, 2),
`age` INT NOT NULL,
`alive` BOOLEAN NOT NULL);

-- insert into `students` (`first_name`, `last_name`, `height`, `age`, `alive`) values ( ?, ?, ?, ?, ?);
-- select * from `students`;