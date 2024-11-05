package com.task.Repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.task.Model.Todo;
import com.task.Model.User;

@Repository
@Transactional
public class TodoRepository {

    private final SessionFactory sessionFactory;
    private final UserRepository userRepository;

    @Autowired
    public TodoRepository(SessionFactory sessionFactory, UserRepository userRepository) {
        this.sessionFactory = sessionFactory;
        this.userRepository = userRepository;
    }

    public void addTodoForUser(int userId, String taskName) {
        try {
            Session session = sessionFactory.getCurrentSession();
            User user = userRepository.getUserById(userId);

            if (user != null) {
                Todo todo = new Todo();
                todo.setTaskName(taskName);
                todo.setUsers(user);
                session.save(todo);
            } else {
                System.out.println("User not found for ID: " + userId);
            }
        } catch (HibernateException e) {
            System.out.println("Error saving todo: " + e);
        }
    }

    public List<Todo> fetchAllTodo(int userId) {
        System.out.println("Into fetch method");
        try {
            Session session = sessionFactory.getCurrentSession();
    
            User user = session.get(User.class, userId); 
    
            Criteria criteria = session.createCriteria(Todo.class);
            criteria.add(Restrictions.eq("users", user)); 
            List<Todo> todos = criteria.list();
            return todos;
        } catch (HibernateException e) {
            System.out.println("Error fetching todos: " + e);
        }
        return null;
    }
    
}
