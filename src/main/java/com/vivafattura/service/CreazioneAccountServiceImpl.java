package com.vivafattura.service;

import com.vivafattura.entity.AccountCliente;
import com.vivafattura.entity.Cliente;
import com.vivafattura.repository.ClienteLoginRepository;
import com.vivafattura.repository.CreazioneAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CreazioneAccountServiceImpl implements CreazioneAccountService, UserDetailsService {

    private static CreazioneAccountRepository creazioneAccountRepository;

    private final ClienteLoginRepository clienteLoginRepository;


    @Override
    public ResponseEntity<?> creazioneAccount(String username, AccountCliente accountDaCreare) throws UsernameNotFoundException {

        Map<String, String> resultCreazioneAccount = new HashMap<>();

        if(loadUserByUsername(username) == null){
            throw new UsernameNotFoundException("Cliente non trovato", null);
        }
        else{
            if(!creazioneAccountRepository.existsById(accountDaCreare.getNumeroConto())) {
                creazioneAccountRepository.save(accountDaCreare);
                resultCreazioneAccount.put("message", "Creazione account andata a buon fine");
            }
            else{
                resultCreazioneAccount.put("message", "Account già esistente");
            }
        }

        return new ResponseEntity<>(resultCreazioneAccount, HttpStatusCode.valueOf(201));
    }


    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //Write Logic to get the client from the DB
        Cliente cliente = clienteLoginRepository.findByUsername(username);

        if(cliente == null){
            throw new UsernameNotFoundException("Cliente non trovato", null);
        }
        return new User(cliente.getUsername(), cliente.getPassword(), new ArrayList<>());
    }
}
