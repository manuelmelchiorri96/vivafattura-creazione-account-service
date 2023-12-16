package com.vivafattura.repository;

import com.vivafattura.entity.AccountCliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreazioneAccountRepository extends JpaRepository<AccountCliente, Long> {

}
