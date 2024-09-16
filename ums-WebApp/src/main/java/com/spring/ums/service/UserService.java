package com.spring.ums.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import com.spring.ums.dto.UserRegisterDto;
import com.spring.ums.entity.User;

/**
 * Interfaccia che definisce i servizi per la gestione degli utenti.
 * La ragione principale per includere extends UserDetailsService in UserService è
 * la necessità di integrare con il framework di sicurezza di Spring. In particolare,
 * UserDetailsService è un'interfaccia fornita da Spring Security
 * per recuperare informazioni sull'utente durante il processo di autenticazione.
 * Il metodo chiave in UserDetailsService è loadUserByUsername, che viene utilizzato per caricare
 * i dettagli dell'utente dato il suo nome utente durante il processo di autenticazione.
 */
public interface UserService extends UserDetailsService {

    /**
     * Salva un nuovo utente nel sistema.
     *
     * @param userRegDto Oggetto UserRegisterDto contenente i dati dell'utente da registrare.
     * @return L'oggetto User appena registrato.
     */
    User save(UserRegisterDto userRegDto);

    /**
     * Trova un utente nel sistema dato il suo indirizzo email.
     *
     * @param email L'indirizzo email dell'utente da trovare.
     * @return L'oggetto User corrispondente all'indirizzo email specificato.
     */
    User findByEmail(String email);

    /**
     * Aggiorna le informazioni di un utente nel sistema.
     *
     * @param updatedUser L'oggetto User con le informazioni aggiornate.
     */
    void updateUser(User updatedUser);

    /**
     * Verifica se la password fornita corrisponde alla password dell'utente specificato.
     *
     * @param username L'username dell'utente.
     * @param password La password da verificare.
     * @return True se la password è corretta, False altrimenti.
     */
    boolean checkPassword(String username, String password);

    /**
     * Cambia la password dell'utente specificato nel sistema.
     *
     * @param username    L'username dell'utente.
     * @param newPassword La nuova password da impostare.
     */
    void changePassword(String username, String newPassword);

    /**
     * Esegue una ricerca di utenti nel sistema in base a una query specificata.
     *
     * @param query La stringa di ricerca.
     * @return Una lista di utenti che corrispondono alla query.
     */
    List<User> searchUsers(String query);
}
