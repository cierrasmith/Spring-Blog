package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repos.PostRepository;
import com.codeup.springblog.repos.UserRepository;
import com.codeup.springblog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {

    // Dependency injection
    private final PostRepository postDao;
    private final UserRepository userDao;
    private final EmailService emailService;


    public PostController(PostRepository postDao, UserRepository userDao, EmailService emailService) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }

    @GetMapping("/posts")
    public String index(Model model) {

        List<Post> postsToShow = postDao.findAll();

        model.addAttribute("posts", postsToShow);

        return "post/index";
    }

    @GetMapping("/posts/{id}")
    public String individualPost
            (@PathVariable Long id, Model model) {

        Post postToShow = postDao.getPostById(id);
        model.addAttribute("post", postToShow);

        return "post/show";
    }

    // Create Method from Form Model Binding
    @GetMapping("/posts/create")
    public String showCreateForm(Model model) {
        model.addAttribute("post", new Post());
        return "post/create";
    }

    @PostMapping("/posts/create")
    public String createPost(
            @ModelAttribute Post post
    ) {
        User currentlyLoggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        post.setOwner(currentlyLoggedIn);

        emailService.prepareAndSend(
                post,
                "New Post Alert!",
                "You have created a new post!"
        );
        postDao.save(post);
        return "redirect:/posts";
    }

    // These are my original create methods
//    @GetMapping("/posts/create")
//    public String showCreateForm() {
//        return "post/create";
//    }
//
//    @PostMapping("/posts/create")
//    public String createPost(
//            @RequestParam(name = "title") String title,
//            @RequestParam(name = "body") String body
//    ) {
//
//        User currentUser = userDao.getById(1L);
//
//        Post postToSubmit = new Post();
//        postToSubmit.setTitle(title);
//        postToSubmit.setBody(body);
//        postToSubmit.setOwner(currentUser);
//
//        postDao.save(postToSubmit);
//        return "redirect:/posts";
//    }

    // Edit Method from Form Model Binding
    @GetMapping("/posts/{id}/edit")
    public String editPostForm(
            @PathVariable Long id,
            Model model
    ){
        Post editedPost = postDao.getById(id);
        model.addAttribute("post", editedPost);
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String editPost(
            @ModelAttribute Post post
    ) {
        postDao.save(post);
        return "redirect:/posts";
    }


    // edit
    //Ry's way
//    @GetMapping("posts/edit/{id}")
//    public String editPostForm(@PathVariable long id, Model model) {
//        Post editedPost = postDao.getById(id);
//        model.addAttribute("post", editedPost.getId());
//        return "post/edit";
//    }

//    @PostMapping("posts/edit/{id}")
//    public String editPost(
//            @PathVariable Long id,
//            @RequestParam(name = "title") String title,
//            @RequestParam(name = "body") String body
//    ) {
////        Post editedPost = new Post(id, title, body);
//
//        User currentUser = userDao.getById(1L);
//
//        Post editedPost = new Post();
//        editedPost.setId(id);
//        editedPost.setTitle(title);
//        editedPost.setBody(body);
//        editedPost.setOwner(currentUser);
//
//        postDao.save(editedPost);
//
//
//        return "redirect:/posts";
//    }


    // delete
    @PostMapping("posts/delete/{id}")
    public String deletePost(
            @PathVariable Long id) {

        Post postToDelete = postDao.getById(id);

            postDao.delete(postToDelete);
            return "redirect:/posts";
        }
    }

