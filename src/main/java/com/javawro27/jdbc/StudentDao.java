package com.javawro27.jdbc;

import com.javawro27.jdbc.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// Student DAO reprezentuje obiekt DATA ACCESS OBJECT - obiekt dostępu do danych
// narzędzie do zarządzania obiektami w bazie danych
public class StudentDao {
    private MysqlConnection mysqlConnection;

    public StudentDao() {
        this.mysqlConnection = new MysqlConnection();
        createDatabaseAndTable();
    }

    private void createDatabaseAndTable() {
        // Stwórz bazę / schema
//        try (Connection connection = mysqlConnection.getConnection()) {
//            try (PreparedStatement statement = connection.prepareStatement(StudentTableQueries.CREATE_DATABASE_QUERY)) {
//                statement.execute();
//            }
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
        // stwórz tabelę
        try (Connection connection = mysqlConnection.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(StudentTableQueries.CREATE_TABLE_QUERY)) {
                statement.execute();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void addToDatabase(Student student) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            // 1. stworzyć połączenie
            connection = mysqlConnection.getConnection();

            // 2. otwieranie zapytania
            // zapytanie z możliwością ustawienia parametrów
            statement = connection.prepareStatement(StudentTableQueries.INSERT_STUDENT_QUERY, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setDouble(3, student.getHeight());
            statement.setInt(4, student.getAge());
            statement.setBoolean(5, student.isAlive());

            // 3. realizacja zapytnia
            // ile zostało edytowanych rekordów
            int affectedRecords = statement.executeUpdate();

            // identyfikatory wygenerowane:
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()){ // jeśli jest rekord
                Long generatedKey = generatedKeys.getLong(1);
                student.setId(generatedKey);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            // 5. posprzątanie po wszystkim
            try {
                if (connection != null) {
                    if (statement != null) {
                        statement.close();
                    }
                    connection.close();
                }
            } catch (SQLException throwables) {
                System.err.println("Błąd zamknięcia połączenia");
            }
        }
    }

    public List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();

        try (Connection connection = mysqlConnection.getConnection()) {

            try (PreparedStatement statement = connection.prepareStatement(StudentTableQueries.SELECT_ALL_STUDENTS_QUERY)) {
                ResultSet resultSet = statement.executeQuery();

                // metoda next powoduje przejście do następnego rekordu
                while (resultSet.next()) {
                    // 4. przetworzenie/parsowanie wartości-
                    Student newStudent = Student.builder()
                            .id(resultSet.getLong(1))
                            .firstName(resultSet.getString(2))
                            .lastName(resultSet.getString(3))
                            .height(resultSet.getDouble(4))
                            .age(resultSet.getInt(5))
                            .alive(resultSet.getBoolean(6))
                            .build();

                    list.add(newStudent);
                }

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public void updateStudent(Student student) {
        if (student.getId() == null) {
            System.err.println("Can't edit student without id.");
            return;
        }
        try (Connection connection = mysqlConnection.getConnection()) {

            try (PreparedStatement statement = connection.prepareStatement(StudentTableQueries.UPDATE_STUDENT_QUERY)) {
                statement.setString(1, student.getFirstName());
                statement.setString(2, student.getLastName());
                statement.setDouble(3, student.getHeight());
                statement.setInt(4, student.getAge());
                statement.setBoolean(5, student.isAlive());

                // uzupełnienie klauzuli where
                statement.setLong(6, student.getId());

                int affectedRecords = statement.executeUpdate();
                System.out.println("Edytowanych rekordów: " + affectedRecords);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteStudent(Student student){
        if (student.getId() == null) {
            System.err.println("Can't edit student without id.");
            return;
        }
        deleteStudent(student.getId());
    }

    public void deleteStudent(Long studentId) {
        try (Connection connection = mysqlConnection.getConnection()) {

            try (PreparedStatement statement = connection.prepareStatement(StudentTableQueries.DELETE_STUDENT_QUERY)) {
                // uzupełnienie klauzuli where
                statement.setLong(1, studentId);

                int affectedRecords = statement.executeUpdate();
                System.out.println("Edytowanych rekordów: " + affectedRecords);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
