package com.example.demo.article;

import com.example.demo.article.exception.ResourceNotFoundException;
import com.example.demo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository,UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository=userRepository;
    }

    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    public Article saveArticle(Article article, Long postId) {
        return userRepository.findById(postId).map(user -> {
            article.setUser(user);
            return articleRepository.save(article);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }
}

