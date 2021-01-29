package com.example.demo.user;
import com.example.demo.article.Article;
import com.example.demo.article.ColorConverter;
import com.example.demo.article.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "users")
public class UserController {
    private  UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PreAuthorize("hasAuthority('ADMIN_PERMISSION')")
    @PostMapping
    public void saveUser(@RequestBody User user){
        userService.saveUser(user);
    }

    @GetMapping
    @ResponseBody
    public Optional<List<User>> getUsersByAge(@RequestParam Integer age){
        return userService.getUsersByAge(age);
    }

    @GetMapping(path = "/articles")
    @ResponseBody
    public Optional<List<Object>> getUsersByColor(@RequestParam(name = "color") String colorOfArticle){
            if(!ColorConverter.isExist(colorOfArticle)){
                throw new ResourceNotFoundException("Color " + colorOfArticle + " not found, use");
            }
        return userService.getUsersByColor(colorOfArticle);
    }
    @GetMapping(path = "{count}/articles")
    @ResponseBody
    public Optional<List<String>> getUsersByCountOfArticles(@PathVariable(name = "count") Integer count){
        return userService.getUsersByCountOfArticles(count);
    }
}
