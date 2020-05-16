package com.javawro27.jdbc;

import com.javawro27.jdbc.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

// Student DAO reprezentuje obiekt DATA ACCESS OBJECT - obiekt dostępu do danych
// narzędzie do zarządzania obiektami w bazie danych
public class StudentDao {
    private MysqlConnection mysqlConnection;

    public StudentDao() {
        this.mysqlConnection = new MysqlConnection();
    }

    public void addToDatabase(Student student){
        try {
            // 1. stworzyć połączenie
            Connection connection = mysqlConnection.getConnection();

            // 2. otwieranie zapytania
            // zapytanie z możliwością ustawienia parametrów
            PreparedStatement statement = connection.prepareStatement(StudentTableQueries.INSERT_STUDENT_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setDouble(3, student.getHeight());
            statement.setInt(4, student.getAge());
            statement.setBoolean(5, student.isAlive());

            // 3. realizacja zapytnia
            // ile zostało edytowanych rekordów
            int affectedRecords = statement.executeUpdate();

            // todo: trzeba jeszcze odebrać identyfikator wygenerowanego rekordu.

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        // 4. przetworzenie/parsowanie wartości-
        // 5. posprzątanie po wszystkim


    }
}
