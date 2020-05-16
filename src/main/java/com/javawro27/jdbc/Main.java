package com.javawro27.jdbc;

public class Main {
    public static void main(String[] args) {
        MysqlConnectionParameters mcp = new MysqlConnectionParameters();

        System.out.println("Login: " + mcp.getUsername());
        System.out.println("Pass : " + mcp.getPassword());
    }
}
