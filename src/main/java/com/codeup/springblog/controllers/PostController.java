package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.repos.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    // Dependency injection
    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }

    @GetMapping("/posts")
    public String index(Model model) {

        List<Post> postsToShow = postDao.findAll();

//        List<Post> posts = new ArrayList<>();

//        posts.add(new Post("One post", "One body"));
//        posts.add(new Post("Second post", "Second body"));

        model.addAttribute("posts", postsToShow);

        return "post/index";
    }

    @GetMapping("/posts/{id}")
    public String individualPost
            (@PathVariable Long id, Model model) {

        Post postToShow = postDao.getById(id);
        model.addAttribute("post", postToShow);

//        Post post = new Post("New title", "New Body");
//        model.addAttribute("postId", id);
//        model.addAttribute("post", post);

        return "post/show";
    }

    @GetMapping("/posts/create")
    public String showCreateForm() {
        return "post/create";
    }

    @PostMapping("/posts/create")
    public String createPost(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "body") String body
    ) {

        Post postToSubmitToDB = new Post(title, body);
        postDao.save(postToSubmitToDB);
        return "redirect:/posts";
    }


}
