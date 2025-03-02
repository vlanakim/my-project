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
    private static SessionFactory sessionFactory;

    private Util() {}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            synchronized (Util.class) {
                if (sessionFactory == null) {
                    sessionFactory = new Configuration().configure().buildSessionFactory();
                }
            }
        }
        return sessionFactory;
    }
}