package com.example.demo.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;
    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public List<Article> getArticles(){
        return articleService.getArticles();
    }

    @PreAuthorize("hasAuthority('ADMIN_PERMISSION')")
    @PostMapping
    public void saveArticle(@RequestBody Article article,@RequestParam Long userID) {
        articleService.saveArticle(article,userID);
    }
}
