package com.example.demo.auth;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ApplicationUserRepository extends JpaRepository<ApplicationUser,Long>{

  @Query(value = "SELECT * FROM appuser  WHERE username= :username",nativeQuery = true)
  Optional<ApplicationUser> selectApplicationUserByUsername(@Param("username")String username);
}
