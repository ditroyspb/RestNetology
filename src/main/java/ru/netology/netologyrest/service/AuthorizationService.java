package ru.netology.netologyrest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.netology.netologyrest.exceptions.InvalidCredentials;
import ru.netology.netologyrest.exceptions.UnauthorizedUser;
import ru.netology.netologyrest.repository.Authorities;
import ru.netology.netologyrest.repository.User;
import ru.netology.netologyrest.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorizationService {

    private final UserRepository userRepository;

    private AuthorizationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Authorities> getAuthorities(String user, String password) {

        if (isEmpty(user) || isEmpty(password)) {
            throw new InvalidCredentials("User name or password is empty");
        }
        List<Authorities> userAuthorities = userRepository.getUserAuthorities(user, password);
        if (isEmpty(userAuthorities)) {
            throw new UnauthorizedUser("Unknown user " + user);
        }
        return userAuthorities;
    }

    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }

    private boolean isEmpty(List<?> str) {
        return str == null || str.isEmpty();
    }
}