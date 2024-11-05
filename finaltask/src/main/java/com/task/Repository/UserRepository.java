package com.task.Repository;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.task.Model.User;

@Repository
@Transactional
public class UserRepository {

    private final SessionFactory sessionFactory;
    
    @Autowired
    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public String addUserInfo(User user) {
        User existingUser = checkExistingUser(user);

        if (existingUser != null) {
            return "User Already Exists!";
        }
        try {
            Session session = sessionFactory.getCurrentSession();
            session.save(user);
            return "User \"" + user.getUserName() + "\" Created Successfully!!";
        } catch (HibernateException e) {
            System.out.println("Error saving user: " + e);
            return "Corrupted";
        }
    }

    public List<User> getUsers() {
        Session session = sessionFactory.openSession();
        List<User> users = null;

        try {
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.gt("visitCount", 0));
            users = criteria.list();
        } catch (HibernateException e) {
            System.out.println("Error fetching users: " + e);
        } finally {
            session.close(); 
        }
        return users;
    }

    public String validateLogin(User user) {
        String message = "Invalid Credentials... Please check username or Password!!";
        try {
            User existingUser = checkExistingUser(user);

            if (existingUser != null) {
                existingUser.setVisitCount(existingUser.getVisitCount() + 1);
                existingUser.setLastLogin(LocalDateTime.now());

                Session session = sessionFactory.getCurrentSession();
                session.update(existingUser);

                message = "User \"" + existingUser.getUserName() + "\" has Logged in Successfully.";
            }
        } catch (HibernateException e) {
            System.out.println("Error validating login: " + e);
        }
        return message;
    }

    public User checkExistingUser(User user) {
        Session session = sessionFactory.getCurrentSession();

        try {
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("userName", user.getUserName()));
            criteria.add(Restrictions.eq("password", user.getPassword()));
            return (User) criteria.uniqueResult();
        } catch (HibernateException e) {
            System.out.println("Error checking existing user: " + e);
            return null;
        }
    }
    public User getUserById(int userId) {
        Session session = sessionFactory.getCurrentSession();
    
        try {
            return session.get(User.class, userId);
        } catch (HibernateException e) {
            System.out.println("Error fetching user by ID: " + e);
            return null;
        }
    }
    
}
