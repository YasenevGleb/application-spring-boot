package com.example.demo.article;

import com.example.demo.article.exception.ResourceNotFoundException;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;
@Converter(autoApply = true)
public class ColorConverter implements AttributeConverter<ColorOfArticle,String> {
    @Override
    public String convertToDatabaseColumn(ColorOfArticle colorOfArticle) {
        if (colorOfArticle == null) {
            return null;
        }
        return colorOfArticle.getCode();


    }

    @Override
    public ColorOfArticle convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }
        return Stream.of(ColorOfArticle.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Code " + code + " not found"));
    }
    public static boolean isExist(String color) {
        for (ColorOfArticle c : ColorOfArticle.values()) {
            if (c.name().equals(color)) {
                return true;
            }
        }
        return false;
    }
}