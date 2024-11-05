package com.task.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.task.Model.Todo;
import com.task.Service.TodoService;

@Controller
public class TodoController {
    @Autowired
    private TodoService todoService;

  
    @PostMapping("/todo")
    public ModelAndView addTodo(@RequestParam String TaskName, HttpSession session) {
        ModelAndView mv = new ModelAndView("todo");
        int userId = (int) session.getAttribute("userId");
        List<Todo>todoList = todoService.listAllTodosForUser(userId);
        System.out.println(todoList);
        mv.addObject("ListOfTodo",todoList);
        

        todoService.addTodoForUser(userId, TaskName);
        return  mv;
    }
}