package com.spring.ums.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spring.ums.entity.User;
import com.spring.ums.service.UserService;

import java.security.Principal;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class UserProfileControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @Mock
    private Principal principal;

    @Mock
    private Authentication authentication;

    @Mock
    private RedirectAttributes redirectAttributes;

    @InjectMocks
    private UserProfileController userProfileController;

    private User mockUser;

    @BeforeEach
    public void setUp() {
        // Prepara un utente di test
        mockUser = new User();
        mockUser.setEmail("user@example.com");
        mockUser.setFirstName("Test");
        mockUser.setLastName("User");
    }


    @Test
    public void testUpdateProfile_Failure() {
        // Simula un'eccezione durante l'aggiornamento dell'utente
        doThrow(new RuntimeException("Errore durante l'aggiornamento")).when(userService).updateUser(mockUser);

        // Chiama il metodo da testare
        String viewName = userProfileController.updateProfile(mockUser, model, redirectAttributes);

        // Verifica che il messaggio di errore sia stato aggiunto al modello
        verify(model).addAttribute("error", "Errore durante l'aggiornamento");

        // Verifica che venga effettuato il reindirizzamento corretto
        assertEquals("redirect:/profile", viewName);
    }
}
