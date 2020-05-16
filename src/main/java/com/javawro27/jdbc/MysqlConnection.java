package com.javawro27.jdbc;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class MysqlConnection {
    private MysqlConnectionParameters mysqlConnectionParameters; // nasza klasa
    private MysqlDataSource mysqlDataSource;                     // klasa mysql'owa

    public MysqlConnection() {
        mysqlConnectionParameters = new MysqlConnectionParameters();
        initialize();
    }

    // tworzymy obiekt połączenia bazodanowego
    // ten obiekt pozwoli nam wywoływać zapytania
    private void initialize() {
        mysqlDataSource = new MysqlDataSource();

        mysqlDataSource.setUser(mysqlConnectionParameters.getUsername());
        mysqlDataSource.setPassword(mysqlConnectionParameters.getPassword());

        mysqlDataSource.setDatabaseName(mysqlConnectionParameters.getDatabaseName());
        mysqlDataSource.setServerName(mysqlConnectionParameters.getDatabaseHost());
        mysqlDataSource.setPort(Integer.parseInt(mysqlConnectionParameters.getDatabasePort()));

        try {
            // strefa czasowa
            // Jeśli komputer i baza danych są w różnych strefach, to musi być uwzględniona różnica czasu przy dodawaniu rekordów.
            mysqlDataSource.setServerTimezone("Europe/Warsaw");
            mysqlDataSource.setUseSSL(false); // czy szyfrowanie (nie)
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return mysqlDataSource.getConnection();
    }
}
