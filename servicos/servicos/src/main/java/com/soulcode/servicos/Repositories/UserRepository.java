package com.soulcode.servicos.Repositories;

import com.soulcode.servicos.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public Optional<User> findByLogin(String login); // irá buscar pelo email do user
}
