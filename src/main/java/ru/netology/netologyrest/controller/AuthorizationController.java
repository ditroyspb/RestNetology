package ru.netology.netologyrest.controller;

import org.springframework.web.bind.annotation.*;
import ru.netology.netologyrest.repository.Authorities;

import ru.netology.netologyrest.service.AuthorizationService;

import java.util.List;

@RestController
public class AuthorizationController {
//    AuthorizationService service;
    AuthorizationService service = new AuthorizationService();

    @GetMapping("/authorize")
    public List<Authorities> getAuthorities(@RequestParam("user") String user, @RequestParam("password") String password) {
        return service.getAuthorities(user, password);
    }
}