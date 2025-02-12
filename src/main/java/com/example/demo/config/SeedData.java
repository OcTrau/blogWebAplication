package com.example.demo.config;

import com.example.demo.models.Account;
import com.example.demo.models.Post;
import com.example.demo.services.AccountService;
import com.example.demo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeedData implements CommandLineRunner {
    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;



    @Override
    public void run(String... args) throws Exception {

        Account account01 = new Account();
        Account account02 = new Account();

        account01.setEmail("taoyasua@gmail.com");
        account01.setPassword("password");
        account01.setFirstname("PhamMinhBao");

        account02.setEmail("hihiok2002@gmail.com");
        account02.setPassword("Password");
        account02.setFirstname("PhamHongHai");

        accountService.save(account01);
        accountService.save(account02);





        List<Post> posts = postService.getAll();
        if (posts.isEmpty()){
            Post post01 = new Post();
            post01.setTitle("Post 01");
            post01.setBody("Seeedinggg1........");
            post01.setAccount(account01);
            postService.save(post01);

            Post post02 = new Post();
            post02.setTitle("Post 02");
            post02.setBody("Seeedinggg2........");
            post02.setAccount(account02);
            postService.save(post02);
        }

    }

}
