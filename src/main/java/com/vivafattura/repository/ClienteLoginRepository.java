package com.vivafattura.repository;

import com.vivafattura.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteLoginRepository extends JpaRepository<Cliente, Long> {

    Cliente findByUsername(String username);

}
