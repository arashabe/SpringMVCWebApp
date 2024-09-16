package com.spring.ums.security;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUserDetails extends User {
	
	/**
	* Questo campo memorizza il nome dell'utente. 
	* La parola chiave final indica che il valore del campo non può essere modificato dopo l'inizializzazione.
	 */
    private final String firstName;
    
	/**
    * Questo è il costruttore della classe. Prende in input il nome utente,
    * la password, una collezione di autorità concesse (ruoli) e il nome dell'utente.
    * Richiama il costruttore della classe madre User passando i parametri necessari.
    */
    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, String firstName) {
        super(username, password, authorities);
        this.firstName = firstName;
    }

    //Questo metodo restituisce il nome dell'utente 
    public String getFirstName() {
        return firstName;
    }
}