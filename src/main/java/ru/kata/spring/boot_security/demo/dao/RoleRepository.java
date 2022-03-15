package ru.kata.spring.boot_security.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.spring.boot_security.demo.models.User;

public interface RoleRepository extends JpaRepository<User, Long> {
}
