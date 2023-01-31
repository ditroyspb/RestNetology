package ru.netology.netologyrest.service;

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

    //    UserRepository userRepository;
    UserRepository userRepository = new UserRepository();

    public List<Authorities> getAuthorities(String user, String password) {

//        заполнение списка пользователей и их доступов
        List<Authorities> opt1 = new ArrayList<>();
        opt1.add(Authorities.READ);
        opt1.add(Authorities.WRITE);
        opt1.add(Authorities.DELETE);
        List<Authorities> opt2 = new ArrayList<>();
        opt2.add(Authorities.READ);
        opt2.add(Authorities.WRITE);
        List<Authorities> opt3 = new ArrayList<>();
        opt3.add(Authorities.READ);
        User user1 = new User("andrey", "12345");
        User user2 = new User("ivan", "123");
        User user3 = new User("nastya", "123456789");

        userRepository.usersAuthoritiesList.put(user3, opt3);
        userRepository.usersAuthoritiesList.put(user1, opt1);
        userRepository.usersAuthoritiesList.put(user2, opt2);
        // окончание заполнения списка пользователей и их доступов

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