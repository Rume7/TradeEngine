package com.mark.client.clientservice.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepo extends JpaRepository<Client, Integer> {
    Optional<Client> findByEmail(String email);
    List<Client> findAllByRole(Role role);
}
