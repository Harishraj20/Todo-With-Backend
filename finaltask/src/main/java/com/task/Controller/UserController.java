package com.task.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.task.Model.User;
import com.task.Service.UserService;

@Controller
public class UserController {
    
    @Autowired
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String homepage() {
        return "home";
    }

    @PostMapping("/addUser")
    public ModelAndView addUser(@ModelAttribute User user) {
        ModelAndView mv = new ModelAndView("message");
        String msg = service.addUsers(user);
        mv.addObject("msg", msg);
        return mv;
    }

    @GetMapping("/Views")
    public ModelAndView viewUsers() {
        ModelAndView mv = new ModelAndView("Details");
        List<User> usersList = service.fetchDetails();
        mv.addObject("userList", usersList);
        return mv;
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, HttpSession session) {
        User useravail = service.validateUser(user);
        if (useravail != null) {
            session.setAttribute("userId", useravail.getId());
            return "redirect:/todoList";
        } else {
            return "redirect:/";
        }
    }
}
