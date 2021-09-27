package com.codeup.springblog.controllers;

import com.codeup.springblog.models.User;
import com.codeup.springblog.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsersController {

    @Autowired
    private UserRepository userDao;

    @GetMapping("/user/create")
    public String createUserForm() {
        return "user/create";
    }

    @GetMapping("/user/{email}/posts")
    public String showUserPosts(
            @PathVariable String email,
            Model model
    ) {

        User userToDisplay = userDao.getByEmail(email);
        model.addAttribute("user", userToDisplay);

        return "user/displayPosts";

    }

    @PostMapping("/user/create")
    @ResponseBody
    public String createUser(
        @RequestParam(name = "uname") String username,
        @RequestParam(name = "psw") String password

    ){
        System.out.println("Username " + username);
        System.out.println("Password " + password);

        return "User Created";
    }



    // Anything that has to do with users will go in here
}
