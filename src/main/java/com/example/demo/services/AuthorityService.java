package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Authority;
import com.example.demo.repositories.AuthorityRepository;


@Service
public class AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;   

    public Authority save(Authority authority){
        return authorityRepository.save(authority);
    }
    
}
