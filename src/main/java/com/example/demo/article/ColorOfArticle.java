package com.example.demo.article;

public enum ColorOfArticle {
    RED("red"),WHITE("white"),BLACK("black");
    private String code;

    ColorOfArticle(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public  String toString() {
        return "ColorOfArticle{" +
                "code='" + code + '\'' +
                '}';
    }
}
