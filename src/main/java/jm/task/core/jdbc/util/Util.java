package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/my_database";
    private static final String USER = "root";
    private static final String PASSWORD = "imnamekim01";

    public static Connection getConnection() {
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("База данных подключена");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    private Util() {}

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}