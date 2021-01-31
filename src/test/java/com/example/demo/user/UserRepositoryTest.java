package com.example.demo.user;

import com.example.demo.article.Article;
import com.example.demo.article.ArticleRepository;
import com.example.demo.article.ColorOfArticle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(
        properties = {
                "spring.jpa.properties.javax.persistence.validation.mode=none"
        }
)
public class UserRepositoryTest {
    private UserRepository underTestUser;
    private ArticleRepository underTestArticle;

    @Autowired
    public UserRepositoryTest(UserRepository underTestUser, ArticleRepository underTestArticle) {
        this.underTestUser = underTestUser;
        this.underTestArticle = underTestArticle;
    }

    @Test
    void itShouldSelectUserByColorOfArticle() {
        // Given

        User user = new User("Abel", 21);
        Article article = new Article("text", ColorOfArticle.BLACK, user);
        // When
        underTestUser.save(user);
        underTestArticle.save(article);

        // Then
        Optional<List<Object>> optionalCustomer = underTestUser.getUserByColor("BLACK");
        Optional<Object> objectOptional= Optional.ofNullable(optionalCustomer.get().get(0));
        assertThat(objectOptional)
                .isPresent()
                .hasValueSatisfying(c -> {
                    assertThat(c).isEqualToComparingFieldByField(user);
                });
    }

    @Test
    void itNotShouldSelectUserByColorOfArticleWhenColorDoesNotExists() {
        // Given

        // When
        Optional<List<Object>> optionalCustomer = underTestUser.getUserByColor("ORANGE");
        // Then
        assertThat(optionalCustomer.get()).isEmpty();
    }

    @Test
    void itShouldSaveUser() {
        // Given
        underTestUser.deleteAll();
        User customer = new User( "Rick", 22);

        // When
        underTestUser.save(customer);

        // Then
        Optional<User> optionalCustomer = underTestUser.findById(3L);
                    assertThat(optionalCustomer)
                            .isPresent()
                            .hasValueSatisfying(c -> {
                                assertThat(c.getId()).isEqualTo(3L);
                                assertThat(c.getName()).isEqualTo("Rick");
                                assertThat(c.getAge()).isEqualTo(22);
                                assertThat(c).isEqualToComparingFieldByField(customer);
                });
    }

    @Test
    void itShouldNotSaveUserWhenNameIsNull() {
        // Given;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // When
        User user = new User(1L, null, 21);
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

        // Then
        assertThat(constraintViolations.size()).isEqualTo(1);


    }

    @Test
    void itShouldNotSaveUserWhenAgeIsNull() {
        // Given
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // When
        User user = new User(1L, "Alex", null);
        Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);

        // Then
        assertThat(constraintViolations.size()).isEqualTo(1);

    }

    @Test
    void itShouldSelectUserByAge() {
        // Given
        User user = new User("Abel", 100);

        // When
        underTestUser.save(user);

        // Then
        //List<User> optionalCustomer = underTestUser.getAllUsersByAge(20);
        Optional<List<User>> optionalCustomer = underTestUser.getAllUsersByAge(100);
        Optional<User> user1= Optional.ofNullable(optionalCustomer.get().get(0));
        assertThat(user1)
                .isPresent()
                .hasValueSatisfying(c -> {
                    assertThat(c.getName()).isEqualTo("Abel");
                    assertThat(c.getId()).isEqualTo(7L);
                    assertThat(c.getArticle()).isEqualTo(null);
                    assertThat(c.getAge()).isEqualTo(100);
                    assertThat(c).isEqualToComparingFieldByField(user);
                });
    }
    @Test
    void itShouldSelectUserByCountOfArticles() {
        // Given
        User user = new User("Abel", 21);
        Article article1 = new Article("text1", ColorOfArticle.BLACK, user);
        Article article2 = new Article("text2", ColorOfArticle.BLACK, user);
        Article article3 = new Article("text3", ColorOfArticle.BLACK, user);
        // When
        underTestUser.save(user);
        underTestArticle.saveAll(List.of(article1, article2, article3));

        // Then
        Optional<List<String>> optionalCustomer = underTestUser.getUsersByCountOfArticles(3);
        Optional<String> name= Optional.ofNullable(optionalCustomer.get().get(0));
        assertThat(name)
                .isPresent()
                .hasValueSatisfying(c -> {
                    assertThat(c).isEqualTo(user.getName());
                });
    }
    @Test
    void itShouldSaveArticle() {
        // Given
        User userForArticleTest=new User("Gleb",21);
        Article article = new Article( "text1", ColorOfArticle.BLACK,userForArticleTest);


        // When
        underTestUser.save(userForArticleTest);
        underTestArticle.save(article);

        // Then
        Optional<Article> optionalCustomer = underTestArticle.findById(5L);
        assertThat(optionalCustomer)
                .isPresent()
                .hasValueSatisfying(c -> {
                    assertThat(c.getArt_id()).isEqualTo(5L);
                    assertThat(c.getText()).isEqualTo("text1");
                    assertThat(c.getColorOfArticle()).isEqualTo(ColorOfArticle.BLACK);
                    assertThat(c).isEqualToComparingFieldByField(article);
                });

    }
    @Test
    void itShouldNotSaveArticleWhenTextIsNull() {
        // Given;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // When
        User userForArticleTest=new User("Gleb",21);
        underTestUser.save(userForArticleTest);
        Article article = new Article( 1L,null,ColorOfArticle.BLACK,userForArticleTest );
        Set<ConstraintViolation<Article>> constraintViolations = validator.validate(article);

        // Then
        assertThat(constraintViolations.size()).isEqualTo(1);


    }
    @Test
    void itShouldNotSaveArticleWhenColorIsNull() {
        // Given;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // When
        User userForArticleTest=new User("Gleb",21);
        underTestUser.save(userForArticleTest);
        Article article = new Article( 1L,"text1",null,userForArticleTest );
        Set<ConstraintViolation<Article>> constraintViolations = validator.validate(article);

        // Then
        assertThat(constraintViolations.size()).isEqualTo(1);


    }
    @Test
    void itShouldNotSaveArticleWhenUserIsNull() {
        // Given;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        // When

        Article article = new Article( 1L,"text1",ColorOfArticle.BLACK,null );
        Set<ConstraintViolation<Article>> constraintViolations = validator.validate(article);

        // Then
        assertThat(constraintViolations.size()).isEqualTo(1);


    }

}
