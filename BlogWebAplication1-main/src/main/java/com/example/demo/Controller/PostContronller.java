package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.models.Post;
import com.example.demo.services.PostService;

@Controller
public class PostContronller {
    
    @Autowired
    private PostService postService;

    @GetMapping("/posts/{id}")
    public String getPosts(@PathVariable Long id, Model model){ {
        Optional<Post> optionalPost = postService.getById(id);
        
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            model.addAttribute("post", post);
            return "post_views/post";
            
        }else{
            return "404";
        }
        

    }
}
}
