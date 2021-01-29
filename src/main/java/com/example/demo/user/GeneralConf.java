package com.example.demo.user;

import com.example.demo.article.Article;
import com.example.demo.article.ArticleRepository;
import com.example.demo.article.ColorOfArticle;
import com.example.demo.auth.ApplicationUser;
import com.example.demo.auth.ApplicationUserPermission;
import com.example.demo.auth.ApplicationUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class GeneralConf {
    @Bean
    public CommandLineRunner commandLineRunnerForUser(
            UserRepository userRepository,
            ArticleRepository articleRepository,
            ApplicationUserRepository applicationUserDao,
            PasswordEncoder passwordEncoder
    ){
        User gleb=new User("Gleb",100);User stan=new User("Stan",50);
        User anna=new User("Anna",0);User marry=new User("Marry",20);
        User garry=new User("Garry",21);User nick= new User("Nick",30);
        User cris=new User("Cris",29);User john=new User("John",46);
        User kat=new User("Kat",19);User micky=new User("Micky",80);
        ArrayList<User> userList=new ArrayList<>(List.of(gleb,stan,anna,marry,garry,nick,cris,john,kat,micky));
        Collections.shuffle(userList);
        int randIntForUsers=5+(int)(Math.random() * 4);
        return args -> {
            for(int i=0;i<randIntForUsers;i++){
                userRepository.save(userList.get(i));
                int randIntForArticles=1+(int)(Math.random() * 3);
                for(int j=0;j<randIntForArticles;j++){
                    articleRepository.save(new Article("General text",ColorOfArticle.BLACK,userList.get(i)));
                }

            }
            ApplicationUser admin=new ApplicationUser( "admin",passwordEncoder.encode("34bb"), ApplicationUserPermission.ADMIN_PERMISSION,true,true,true,true);
            ApplicationUser user=new ApplicationUser("user",passwordEncoder.encode("34cc"),ApplicationUserPermission.USER_PERMISSION,true,true,true,true);
            applicationUserDao.saveAll(List.of(admin,user));
        };
    }

}
