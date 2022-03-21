package ru.kata.spring.boot_security.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;

import java.util.Set;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);

   // @Modifying
   // @Query(value = "update User u set u.firstName = ?1, u.lastName = ?2," +
           // " u.password = ?3, u.email = ?4, u.age = ?5  where u.id =?6")
  //  void update(String firstName, String lastName, String password, String email, Integer age, Long id );
}
