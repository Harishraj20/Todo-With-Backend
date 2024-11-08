package com.task.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.task.Model.Todo;
import com.task.Service.TodoService;

@Controller
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping("/addTodo")
    public String addTodo(@RequestParam String TaskName, HttpSession session) {
        int userId = (int) session.getAttribute("userId");
        todoService.addTodoForUser(userId, TaskName);
        return "redirect:/todoList";
    }

    @GetMapping("/todoList")
    public ModelAndView viewTodoList(HttpSession session) {
        ModelAndView mv = new ModelAndView("todo");
        int userId = (int) session.getAttribute("userId");
        List<Todo> todoList = todoService.listAllTodosForUser(userId);
        mv.addObject("ListOfTodo", todoList);
        return mv;
    }

    @GetMapping("/delete")
    public String deleteTask(@RequestParam int deleteId) {

        boolean result = todoService.deleteTaskById(deleteId);
        if (result) {
            return "redirect:/todoList";
        }
        return "message";
    }

    @PostMapping("/editTask")
    public String editTask(@RequestParam int editTaskId, @RequestParam String TaskName) {

        boolean result = todoService.editById(editTaskId,TaskName);
        if (!result) {
            return "message";

        } else {
            return "redirect:/todoList";

        }
    }

}
