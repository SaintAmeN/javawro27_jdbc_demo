package com.javawro27.jdbc;

import com.javawro27.jdbc.model.Student;

public class Main {
    public static void main(String[] args) {
        // trzeba mieć stworzoną bazę i tabelę
        StudentDao dao = new StudentDao();

        Student student = Student.builder()
                .firstName("Paweł")
                .lastName("Gawel")
                .alive(true)
                .height(1.8)
                .age(25)
                .build();

        // C - Create
        dao.addToDatabase(student);


    }
}
