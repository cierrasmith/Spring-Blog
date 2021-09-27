package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repos.PostRepository;
import com.codeup.springblog.repos.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    // Dependency injection
    private final PostRepository postDao;

    private final UserRepository userDao;


    public PostController(PostRepository postDao, UserRepository userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
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

        Post postToShow = postDao.getPostById(id);
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

        User currentUser = userDao.getById(1L);

        Post postToSubmit = new Post();
        postToSubmit.setTitle(title);
        postToSubmit.setBody(body);
        postToSubmit.setOwner(currentUser);

        postDao.save(postToSubmit);
        return "redirect:/posts";
    }

    // edit
    //Ry's way
    @GetMapping("posts/edit/{id}")
    public String editPostForm(@PathVariable long id, Model model) {
        Post editedPost = postDao.getById(id);
        model.addAttribute("post", editedPost.getId());
        return "post/edit";
    }

    @PostMapping("posts/edit/{id}")
    public String editPost(
            @PathVariable Long id,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "body") String body
    ) {
        Post editedPost = new Post(id, title, body);
        postDao.save(editedPost);

        return "redirect:/posts";
    }


    // delete
    @PostMapping("posts/delete/{id}")
    public String deletePost(
            @PathVariable Long id) {

        Post postToDelete = postDao.getById(id);

            postDao.delete(postToDelete);
            return "redirect:/posts";
        }
    }

