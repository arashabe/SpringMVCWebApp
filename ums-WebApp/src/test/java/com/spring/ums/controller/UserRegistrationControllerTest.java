package com.spring.ums.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import com.spring.ums.dto.UserRegisterDto;
import com.spring.ums.service.UserService;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class UserRegistrationControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @InjectMocks
    private UserRegistrationController userRegistrationController;

    private UserRegisterDto registrationDto;

    @BeforeEach
    public void setUp() {
        // Inizializza un oggetto UserRegisterDto di test
        registrationDto = new UserRegisterDto();
        registrationDto.setEmail("test@example.com");
        registrationDto.setFirstName("Test");
        registrationDto.setLastName("User");
        registrationDto.setPassword("password");
    }

    @Test
    public void testShowRegistrationForm() {
        // Test del metodo GET per mostrare il modulo di registrazione
        String viewName = userRegistrationController.showRegistrationForm();

        // Verifica che la vista restituita sia "registration"
        assertEquals("registration", viewName);
    }


    @Test
    public void testRegisterUserAccount_Failure() {
        // Simula un'eccezione durante la registrazione
        doThrow(new RuntimeException("Errore durante la registrazione")).when(userService).save(registrationDto);

        // Chiama il metodo di registrazione
        String viewName = userRegistrationController.registerUserAccount(registrationDto, model);

        // Verifica che venga gestito l'errore
        verify(model).addAttribute("error", "Errore durante la registrazione");

        // Verifica che la vista restituita sia "registration" in caso di errore
        assertEquals("registration", viewName);
    }
}
