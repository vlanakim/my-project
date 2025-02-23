package jm.task.core.jdbc.dao;

import com.mysql.cj.Session;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.SharedSessionContract;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.transaction.*;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    @Override
    public void createUsersTable() {
        try (org.hibernate.Session session = sessionFactory.openSession()) {
            Transaction transaction = (Transaction) session.beginTransaction();
            ((org.hibernate.Session) session).createNativeQuery("DELETE FROM users").executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (org.hibernate.Session session = sessionFactory.openSession()) {
            Transaction transaction = (Transaction) session.beginTransaction();
            ((org.hibernate.Session) session).createSQLQuery("DROP TABLE IF EXISTS users").executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (org.hibernate.Session session = sessionFactory.openSession()) {
            Transaction transaction = (Transaction) session.beginTransaction();
            ((org.hibernate.Session) session).save(new User(null, name, lastName, age));
            transaction.commit();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (org.hibernate.Session session = sessionFactory.openSession()) {
            Transaction transaction = (Transaction) session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }
            transaction.commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (org.hibernate.Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM User", User.class).list();
        }
    }

    @Override
    public void cleanUsersTable() {
        try (org.hibernate.Session session = sessionFactory.openSession()) {
            Transaction transaction = (Transaction) session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            transaction.commit();
        }
    }
}
