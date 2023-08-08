package com.burakdelice.web;

import com.burakdelice.model.User;
import com.burakdelice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import static com.burakdelice.constant.RestApiUrl.*;

import java.util.List;

@Controller
@RequestMapping("/api"+USER)
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/list")
    public String getAllUsers(Model model){
        List<User> listUsers = userService.findAll();
        model.addAttribute("listUsers",listUsers);
        return "list-users";
    }

    @GetMapping("/add")
    public String getAddUser(Model model){
        return "add-users";
    }
}
