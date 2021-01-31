package com.example.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value =
            " select s.id,s.name,s.age" +
            " from user s where s.age>= :age ",
            nativeQuery = true)
    Optional<List<User>> getAllUsersByAge(@Param("age") Integer age);

    @Query( value =
            " select u.id,u.name,u.age,a.art_id,a.text,a.color_of_article from user u" +
            " join article a" +
            " on u.id = a.user_id" +
            " where a.color_of_article = :color_of_article",
            nativeQuery = true)
    Optional<List<Object>> getUserByColor(@Param("color_of_article") String color_of_article);

   @Query(value =
           " select distinct name from user u" +
           " inner join article a" +
           " on u.id=a.user_id" +
           " group by name" +
           " having count(a.user_id)>= :count",
           nativeQuery = true)
   Optional<List<String>> getUsersByCountOfArticles(@Param("count") Integer count);

}
