package com.example.demo.article;

import com.example.demo.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Article {
    @Id
    @SequenceGenerator(
            name = "article_sequence",
            sequenceName = "article_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "article_sequence"
    )
    private Long art_id;
    @NotNull
    private String text;
    @NotNull
    @Enumerated(EnumType.STRING)
    private ColorOfArticle colorOfArticle;
    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    @JsonIgnore
    private User user;
    public Article(String text, ColorOfArticle colorOfArticle, User user_id) {
        this.text = text;
        this.colorOfArticle = colorOfArticle;
        this.user = user_id;
    }

    public Article() {
    }

    public Article(String text, ColorOfArticle colorOfArticle) {
        this.text = text;
        this.colorOfArticle = colorOfArticle;
    }

    public Article(Long id, String text, ColorOfArticle colorOfArticle,User user) {
        this.art_id = id;
        this.text = text;
        this.colorOfArticle = colorOfArticle;
        this.user=user;
    }

    public Long getArt_id() {
        return art_id;
    }

    public void setArt_id(Long art_id) {
        this.art_id = art_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ColorOfArticle getColorOfArticle() {
        return colorOfArticle;
    }

    public void setColorOfArticle(ColorOfArticle colorOfArticle) {
        this.colorOfArticle = colorOfArticle;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User id) {
        this.user = id;
    }
}
