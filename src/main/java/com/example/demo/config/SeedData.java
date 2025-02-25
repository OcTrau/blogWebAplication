package com.example.demo.config;

import com.example.demo.models.Account;
import com.example.demo.models.Authority;
import com.example.demo.models.Post;
import com.example.demo.services.AccountService;
import com.example.demo.services.AuthorityService;
import com.example.demo.services.PostService;
import com.example.demo.util.constants.Privillages;

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

    @Autowired
    private AuthorityService authorityService;



    @Override
    public void run(String... args) throws Exception {


        for(Privillages auth: Privillages.values()){
            Authority authority = new Authority();
            authority.setId(auth.getId());
            authority.setName(auth.getPrivillage());
            authorityService.save(authority);
        }

        Account account01 = new Account();
        Account account02 = new Account();
        Account account03 = new Account();

        account01.setEmail("taoyasua@gmail.com");
        account01.setPassword("02112002a");
        account01.setFirstname("Pham");
        account01.setLastname("Bao");

        account02.setEmail("hihiok2002@gmail.com");
        account02.setPassword("Ph@mminhb@o02112002");
        account02.setFirstname("Pham");
        account02.setLastname("Hai");

        account03.setEmail("test1@gmail.com");
        account03.setPassword("02112002a");
        account03.setFirstname("jony");
        account03.setLastname("Dang");

        accountService.save(account01);
        accountService.save(account02);
        accountService.save(account03);





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

            Post post03 = new Post();
            post03.setTitle("Post 03");
            post03.setBody("Seeedinggg3........");
            post03.setAccount(account03);
            postService.save(post03);
        }


    }

}
