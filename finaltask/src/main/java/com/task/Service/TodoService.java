package com.task.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.Model.Todo;
import com.task.Repository.TodoRepository;

@Service
public class TodoService {

    private final TodoRepository todorepo;

    @Autowired
    public TodoService(TodoRepository todorepo) {
        this.todorepo = todorepo;
    }

    public void addTodoForUser(int userId, String TaskName) {
        todorepo.addTodoForUser(userId,TaskName);

    }
    public List<Todo> listAllTodosForUser(int userId){
        return todorepo.fetchAllTodo(userId);

    }

}
