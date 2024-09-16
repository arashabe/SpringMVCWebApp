package com.spring.ums.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.ums.dto.UserRegisterDto;
import com.spring.ums.entity.Role;
import com.spring.ums.entity.User;
import com.spring.ums.repository.UserRepository;
import com.spring.ums.security.CustomUserDetails;

/**
 * Implementazione del servizio UserService che gestisce la logica di business
 * relativa agli utenti.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Verifica se la password fornita corrisponde a quella dell'utente specificato.
     *
     * @param username L'username dell'utente.
     * @param password La password da verificare.
     * @return True se la password è corretta, False altrimenti.
     */
    public boolean checkPassword(String username, String password) {
        User user = userRepo.findByEmail(username);
        return user != null && passwordEncoder.matches(password, user.getPassword());
    }

    /**
     * Cambia la password dell'utente specificato nel sistema.
     *
     * @param username    L'username dell'utente.
     * @param newPassword La nuova password da impostare.
     */
    public void changePassword(String username, String newPassword) {
        User user = userRepo.findByEmail(username);
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepo.save(user);
    }

    /**
     * Carica i dettagli dell'utente per l'autenticazione Spring Security.
     *
     * @param username L'username dell'utente.
     * @return Dettagli dell'utente necessari per l'autenticazione.
     * @throws UsernameNotFoundException Se l'utente non è trovato.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new CustomUserDetails(
                user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()),
                user.getFirstName()
        );
    }

    /**
     * Trova un utente nel sistema dato il suo indirizzo email.
     *
     * @param email L'indirizzo email dell'utente da trovare.
     * @return L'oggetto User corrispondente all'indirizzo email specificato.
     */
    @Override
    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    /**
     * Aggiorna le informazioni di un utente nel sistema.
     *
     * @param updatedUser L'oggetto User con le informazioni aggiornate.
     */
    @Override
    public void updateUser(User updatedUser) {
        // Recupera l'utente esistente dal database
        User existingUser = userRepo.findByEmail(updatedUser.getEmail());

        // Verifica se l'utente esiste
        if (existingUser != null) {
            // Aggiorna le informazioni dell'utente
            existingUser.setFirstName(updatedUser.getFirstName());
            existingUser.setLastName(updatedUser.getLastName());
            existingUser.setSkills(updatedUser.getSkills());
            existingUser.setCourseOfStudy(updatedUser.getCourseOfStudy());

            // Salva l'utente aggiornato nel database
            userRepo.save(existingUser);
        } else {
            // L'utente non esiste nel database, gestisci di conseguenza
            throw new RuntimeException("User not found");
        }
    }

    /**
     * Registra un nuovo utente nel sistema.
     *
     * @param userRegDto Oggetto UserRegisterDto contenente i dati dell'utente da registrare.
     * @return L'oggetto User appena registrato.
     */
    @Override
    public User save(UserRegisterDto userRegDto) {
        // Controlla se l'email è già presente nel database
        if (userRepo.findByEmail(userRegDto.getEmail()) != null) {
            throw new RuntimeException("User with this email already exists");
        }

        User user = new User();
        user.setFirstName(userRegDto.getFirstName());
        user.setLastName(userRegDto.getLastName());
        user.setSkills(userRegDto.getSkills());
        user.setCourseOfStudy(userRegDto.getCourseOfStudy());
        user.setEmail(userRegDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRegDto.getPassword()));

        List<Role> roles = new ArrayList<>();
        roles.add(createRole("ROLE_USER"));
        user.setRoles(roles);

        return userRepo.save(user);
    }

    /**
     * Esegue una ricerca di utenti nel sistema in base a una query specificata.
     *
     * @param query La stringa di ricerca.
     * @return Una lista di utenti che corrispondono alla query.
     */
    @Override
    public List<User> searchUsers(String query) {
        // la logica di ricerca qui
        // cercare utenti per nome, cognome, skills, ecc.
        return userRepo.findByFirstNameContainingOrLastNameContainingOrSkillsContainingOrCourseOfStudyContaining(query, query, query, query);
    }

    /**
     * Mappa i ruoli dell'utente in autorizzazioni Spring Security.
     *
     * @param roles La lista di ruoli dell'utente.
     * @return Una collezione di autorizzazioni Spring Security.
     */
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    /**
     * Crea un nuovo oggetto Role con il nome specificato.
     *
     * @param roleName Il nome del ruolo.
     * @return L'oggetto Role appena creato.
     */
    private Role createRole(String roleName) {
        Role role = new Role();
        role.setName(roleName);
        return role;
    }
}
