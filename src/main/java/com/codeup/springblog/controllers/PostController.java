package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String index() {
        return "/posts";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String individualPost
            (@PathVariable String id) {
            return "/posts/" + id;
    }

    @GetMapping("/posts/create")
    @ResponseBody
    public String createForm() {
        return "/posts/create";
    }

    @PostMapping("/posts/create")
    @ResponseBody
    public String create() {
        return "/posts/create";
    }


}
