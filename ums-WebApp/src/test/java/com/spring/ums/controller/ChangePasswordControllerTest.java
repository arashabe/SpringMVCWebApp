package com.spring.ums.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.spring.ums.service.UserService;
/**
 * Questa classe contiene test unitari per il controller `ChangePasswordController`,
 * responsabile della gestione delle richieste di cambio password all'interno dell'applicazione.
 * Vengono utilizzati i framework JUnit 5 e Spring per eseguire test sul comportamento 
 * del controller in diversi scenari, come il successo del cambio password, 
 * password errata e mancata corrispondenza tra nuova password e conferma.
 */
@WebMvcTest(ChangePasswordController.class)
public class ChangePasswordControllerTest {

    /**
     * MockMvc è utilizzato per simulare richieste HTTP verso i controller 
     * senza dover avviare un server web reale. 
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * UserService è una dipendenza mockata che simula il servizio per la gestione degli utenti.
     * Viene utilizzato per verificare la validità delle password inserite nei test.
     */
    @MockBean
    private UserService userService; 

    /**
     * Testa il caso in cui il cambio password avviene con successo.
     * 
     * Scenario:
     * - L'utente inserisce la password corrente corretta.
     * - Le nuove password corrispondono.
     * - Viene verificato un reindirizzamento alla pagina di login con un messaggio di successo.
     * 
     * @throws Exception in caso di errori durante l'esecuzione della richiesta.
     */
    @Test
    @WithMockUser(username = "testuser")
    public void testChangePasswordSuccess() throws Exception {
        // Simula che la password corrente sia corretta
        when(userService.checkPassword("testuser", "oldPassword")).thenReturn(true);

        mockMvc.perform(post("/change-password")
                        .param("currentPassword", "oldPassword")
                        .param("newPassword", "newPassword")
                        .param("confirmPassword", "newPassword")
                        .with(csrf())) // Aggiunge il token CSRF
                .andExpect(status().is3xxRedirection()) // Verifica un redirect a login dopo il successo
                .andExpect(redirectedUrl("/login?passwordChanged")); // Verifica che l'URL di reindirizzamento sia corretto
    }

    
    /**
     * Testa il caso in cui il cambio password fallisce a causa di una password corrente errata.
     * 
     * Scenario:
     * - L'utente inserisce una password corrente sbagliata.
     * - Viene mostrato un messaggio di errore e l'utente viene reindirizzato alla pagina di cambio password.
     * 
     * @throws Exception in caso di errori durante l'esecuzione della richiesta.
     */
    @Test
    @WithMockUser(username = "testuser")
    public void testChangePasswordFailureWrongCurrentPassword() throws Exception {
        // Simula che la password corrente sia sbagliata
        when(userService.checkPassword("testuser", "wrongPassword")).thenReturn(false);

        mockMvc.perform(post("/change-password")
                        .param("currentPassword", "wrongPassword")
                        .param("newPassword", "newPassword")
                        .param("confirmPassword", "newPassword")
                        .with(csrf())) // Aggiunge il token CSRF
                .andExpect(status().is3xxRedirection()) // Verifica un redirect in caso di errore
                .andExpect(flash().attribute("changePasswordError", "Current password is incorrect")) // Verifica il messaggio di errore
                .andExpect(redirectedUrl("/change-password")); // Verifica che l'URL di reindirizzamento sia corretto
    }

    
    /**
     * Testa il caso in cui il cambio password fallisce a causa della mancata corrispondenza 
     * tra la nuova password e la conferma.
     * 
     * Scenario:
     * - L'utente inserisce la password corrente corretta.
     * - Le nuove password non corrispondono.
     * - Viene mostrato un messaggio di errore e l'utente viene reindirizzato alla pagina di cambio password.
     * 
     * @throws Exception in caso di errori durante l'esecuzione della richiesta.
     */
    @Test
    @WithMockUser(username = "testuser")
    public void testChangePasswordFailurePasswordMismatch() throws Exception {
        // Simula che la password corrente sia corretta, ma le nuove password non corrispondono
        when(userService.checkPassword("testuser", "oldPassword")).thenReturn(true);

        mockMvc.perform(post("/change-password")
                        .param("currentPassword", "oldPassword")
                        .param("newPassword", "newPassword")
                        .param("confirmPassword", "differentPassword")
                        .with(csrf())) // Aggiunge il token CSRF
                .andExpect(status().is3xxRedirection()) // Verifica un redirect in caso di errore
                .andExpect(flash().attribute("changePasswordError", "New password and confirmation do not match")) // Verifica il messaggio di errore
                .andExpect(redirectedUrl("/change-password")); // Verifica che l'URL di reindirizzamento sia corretto
    }
}
