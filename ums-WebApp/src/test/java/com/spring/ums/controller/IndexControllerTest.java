package com.spring.ums.controller;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.spring.ums.entity.Notification;
import com.spring.ums.service.NotificationService;

@ExtendWith(MockitoExtension.class)
public class IndexControllerTest {

    @Mock
    private NotificationService notificationService;

    @Mock
    private Model model;

    @InjectMocks
    private IndexController indexController;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    private List<Notification> mockNotifications;

    @BeforeEach
    public void setUp() {
        // Preparare una lista di notifiche di test
        mockNotifications = new ArrayList<>();
        Notification notification1 = new Notification();
        notification1.setMessage("Notification 1");
        Notification notification2 = new Notification();
        notification2.setMessage("Notification 2");
        mockNotifications.add(notification1);
        mockNotifications.add(notification2);
    }

    @Test
    public void testShowIndex() {
        // Simulare il contesto di sicurezza
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("user@example.com");
        SecurityContextHolder.setContext(securityContext);

        // Simulare il comportamento del service
        when(notificationService.getNotificationsForTwoUsers("user@example.com")).thenReturn(mockNotifications);

        // Chiamare il metodo da testare
        String viewName = indexController.showIndex(model);

        // Verificare che il model contenga le notifiche
        verify(model).addAttribute("userNotifications", mockNotifications);

        // Verificare che il metodo restituisca la vista corretta
        assertEquals("index", viewName);
    }
}
