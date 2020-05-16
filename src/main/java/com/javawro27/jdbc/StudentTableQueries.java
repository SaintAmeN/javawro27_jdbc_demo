package com.javawro27.jdbc;

public interface StudentTableQueries {
    String CREATE_DATABASE_QUERY = "create database if not exists `jwro27_students_jdbc`;";

    String CREATE_TABLE_QUERY = "create table if not exists `students` (\n" +
            "`id` INT AUTO_INCREMENT PRIMARY KEY,\n" +
            "`first_name` VARCHAR(30) NOT NULL,\n" +
            "`last_name` VARCHAR(30) NOT NULL,\n" +
            "`height` DECIMAL(10, 2),\n" +
            "`age` INT NOT NULL,\n" +
            "`alive` BOOLEAN NOT NULL);";

    String INSERT_STUDENT_QUERY =
            "insert into `students` " +
                    "(`first_name`, `last_name`, `height`, `age`, `alive`) values" +
                    " ( ?, ?, ?, ?, ?);";

    String SELECT_ALL_STUDENTS_QUERY =
            "select * from `students`;";
    String UPDATE_STUDENT_QUERY = "update `students` set " +
            "`first_name`= ?, " +
            "`last_name` = ?, " +
            "`height` = ?, " +
            "`age`= ?, " +
            "`alive`= ? " +
            "where `id` = ?;";

    String DELETE_STUDENT_QUERY = "delete from `students` where `id` = ?;";
}

