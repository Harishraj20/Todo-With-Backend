package com.task.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.task.Model.Todo;
import com.task.Model.User;
import com.task.Service.TodoService;
import com.task.Service.UserService;

@Controller
public class UserController {
        @Autowired
    private TodoService todoService;

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String homepage() {
        System.out.println("Into Home Page");
        return "home";
    }

    @PostMapping("/addUser")
    public ModelAndView addUser(@ModelAttribute User user) {

        ModelAndView mv = new ModelAndView("message");

        String msg = service.addUsers(user);
        mv.addObject("msg", msg);
        System.out.println("Into add User");
        return mv;
    }

    @GetMapping("/Views")
    public ModelAndView viewUsers() {
        ModelAndView mv = new ModelAndView("Details");

        List<User> usersList = service.fetchDetails();
        mv.addObject("userList", usersList);
        System.out.println("into view Users method");
        return mv;
    }

    @PostMapping("/login")
    public ModelAndView loginUser(@ModelAttribute User user, HttpSession session) {
        System.out.println(user);
        User useravail = service.validateUser(user);
        System.out.println(useravail);
        ModelAndView mv = new ModelAndView("todo");
        session.setAttribute("userId", useravail.getId());
        System.out.println(useravail.getId());

        List<Todo>todoList = todoService.listAllTodosForUser(useravail.getId());
        System.out.println(todoList);
        mv.addObject("ListOfTodo",todoList);
        System.out.println("Into Login User");
        return mv;
    }

    @GetMapping("/back")
    public String goBack() {
        return "redirect:/";
    }

}
