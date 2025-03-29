package com.example.demo.config;

import com.example.demo.models.Account;
import com.example.demo.models.Authority;
import com.example.demo.models.Post;
import com.example.demo.services.AccountService;
import com.example.demo.services.AuthorityService;
import com.example.demo.services.PostService;
import com.example.demo.util.constants.Privillages;
import com.example.demo.util.constants.Roles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


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
        Account account04 = new Account();

        account01.setEmail("user@user.com");
        account01.setPassword("02112002a");
        account01.setFirstname("User");
        account01.setLastname("Lastname");


        account02.setEmail("admin@admin.com");
        account02.setPassword("02112002a");
        account02.setFirstname("admin");
        account02.setLastname("Lastname");
        account02.setRole(Roles.ADMIN.getRole());

        account03.setEmail("editor@editor.com");
        account03.setPassword("02112002a");
        account03.setFirstname("Editor");
        account03.setLastname("Lastname");
        account03.setRole(Roles.EDITOR.getRole());

        account04.setEmail("supper_editor@editor.com");
        account04.setPassword("02112002a");
        account04.setFirstname("Supper_Editor");
        account04.setLastname("Lastname");
        account04.setRole(Roles.EDITOR.getRole());
        // account04.setRole(Roles.ADMIN.getRole());
        Set<Authority> authorities = new HashSet<>();
        // authorityService.findById(Privillages.RESET_ANY_USER_PASSWORD.getId()).ifPresent(authorities::add);
        authorityService.findById(Privillages.RESET_ANY_USER_PASSWORD.getId()).ifPresent(authorities::add);
        authorityService.findById(Privillages.ACCESS_ADMIN_PANEL.getId()).ifPresent(authorities::add); 
        account04.setAuthorities(authorities);



        accountService.save(account01);
        accountService.save(account02);
        accountService.save(account03);
        accountService.save(account04);





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
