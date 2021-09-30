package com.codeup.springblog.controllers;

import com.codeup.springblog.models.User;
import com.codeup.springblog.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UsersController {

    private final UserRepository userDao;
    private final PasswordEncoder passwordEncoder;

    public UsersController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

//    @Autowired
//    private UserRepository userDao;

    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "user/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        return "redirect:/login";
    }

    // Had this in authentication controller but it wasn't working, maybe missing DAO?
    @GetMapping("/login")
    public String showLoginForm() {
        return "user/login";
    }


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
