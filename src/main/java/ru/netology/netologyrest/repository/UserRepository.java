package ru.netology.netologyrest.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {
    public HashMap<User, List<Authorities>> usersAuthoritiesList = new HashMap<>();

    public List<Authorities> getUserAuthorities(String user, String password) {
        List<Authorities> ans = new ArrayList<>();
        for (Map.Entry<User, List<Authorities>> pair : usersAuthoritiesList.entrySet()) {
            if (pair.getKey().getUser().equals(user) && pair.getKey().getPassword().equals(password))  {
                ans = pair.getValue();
            }
        }
        return ans;
    }
}
