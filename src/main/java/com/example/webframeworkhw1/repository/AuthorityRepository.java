package com.example.webframeworkhw1.repository;

import com.example.webframeworkhw1.model.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority, String> {

    Optional<Authority> findByUsername(String username);

}
