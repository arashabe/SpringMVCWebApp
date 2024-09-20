package com.spring.ums.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.spring.ums.entity.User;
import com.spring.ums.service.NotificationService;
import com.spring.ums.service.UserService;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class SearchControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private NotificationService notificationService;

    @Mock
    private Model model;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @InjectMocks
    private SearchController searchController;

    private List<User> mockSearchResults;

    @BeforeEach
    public void setUp() {
        // Prepara una lista di utenti di test per la ricerca
        mockSearchResults = new ArrayList<>();
        User user1 = new User();
        user1.setEmail("user1@example.com");
        User user2 = new User();
        user2.setEmail("user2@example.com");
        mockSearchResults.add(user1);
        mockSearchResults.add(user2);
    }

    @Test
    public void testShowSearchForm_WithQuery() {
        // Simula il comportamento del servizio di ricerca utenti
        when(userService.searchUsers("testQuery")).thenReturn(mockSearchResults);

        // Chiama il metodo da testare
        String viewName = searchController.showSearchForm("testQuery", model);

        // Verifica che i risultati della ricerca siano stati aggiunti al model
        verify(model).addAttribute("searchResults", mockSearchResults);

        // Verifica che il metodo restituisca la vista corretta
        assertEquals("search", viewName);
    }

    @Test
    public void testShowSearchForm_WithoutQuery() {
        // Chiama il metodo senza query
        String viewName = searchController.showSearchForm("", model);

        // Verifica che non ci sia alcun risultato di ricerca aggiunto al model
        verify(model, never()).addAttribute(eq("searchResults"), any());

        // Verifica che il metodo restituisca la vista corretta
        assertEquals("search", viewName);
    }

    @Test
    public void testSendGroupRequest() {
        // Simula il contesto di sicurezza
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("sender@example.com");
        SecurityContextHolder.setContext(securityContext);

        // Chiama il metodo da testare
        String viewName = searchController.sendGroupRequest("recipient@example.com", model);

        // Verifica che il servizio di notifica sia stato chiamato correttamente
        verify(notificationService).saveGroupRequestNotification("sender@example.com", "recipient@example.com");

        // Verifica che venga effettuato il redirect corretto
        assertEquals("redirect:/search?groupRequestSent", viewName);
    }
}
