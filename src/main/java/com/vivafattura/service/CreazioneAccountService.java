package com.vivafattura.service;

import com.vivafattura.entity.AccountCliente;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface CreazioneAccountService {

    ResponseEntity<?> creazioneAccount(String username, AccountCliente accountDaCreare) throws UsernameNotFoundException;
}
