package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public List<User> getUsers(){
        return userRepository.findAll();
    }
    public void saveUser(User user){
        userRepository.save(user);
    }
    public Optional<List<User>> getUsersByAge(Integer age){
        return userRepository.getAllUsersByAge(age);
    }
    public Optional<List<Object>> getUsersByColor(String colorOfArticle){
        return userRepository.getUserByColor(colorOfArticle);
    }
    public Optional<List<String>> getUsersByCountOfArticles(Integer count){
        return userRepository.getUsersByCountOfArticles(count);
    }
}
