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

import com.spring.ums.entity.Notification;
import com.spring.ums.service.NotificationService;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class NotificationControllerTest {

    @Mock
    private NotificationService notificationService;

    @Mock
    private Model model;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityContext securityContext;

    @InjectMocks
    private NotificationController notificationController;

    private List<Notification> mockNotifications;

    @BeforeEach
    public void setUp() {
        // Prepara una lista di notifiche di test
        mockNotifications = new ArrayList<>();
        Notification notification1 = new Notification();
        notification1.setMessage("Notification 1");
        Notification notification2 = new Notification();
        notification2.setMessage("Notification 2");
        mockNotifications.add(notification1);
        mockNotifications.add(notification2);
    }

    @Test
    public void testShowNotifications() {
        // Simula il contesto di sicurezza
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("user@example.com");
        SecurityContextHolder.setContext(securityContext);

        // Simula il comportamento del service
        when(notificationService.getNotificationsForUser("user@example.com")).thenReturn(mockNotifications);

        // Chiama il metodo da testare
        String viewName = notificationController.showNotifications(model);

        // Verifica che il model contenga le notifiche
        verify(model).addAttribute("notifications", mockNotifications);

        // Verifica che il metodo restituisca la vista corretta
        assertEquals("notifications", viewName);
    }

    @Test
    public void testSendGroupRequest() {
        // Simula il contesto di sicurezza
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn("user@example.com");
        SecurityContextHolder.setContext(securityContext);

        // Chiama il metodo da testare
        String viewName = notificationController.sendGroupRequest("recipient@example.com", model);

        // Verifica che il service sia stato chiamato correttamente
        verify(notificationService).saveGroupRequestNotification("user@example.com", "recipient@example.com");

        // Verifica il redirect corretto
        assertEquals("redirect:/search?groupRequestSent", viewName);
    }

    @Test
    public void testAcceptNotification() {
        Notification mockNotification = new Notification();
        mockNotification.setId(1L);
        mockNotification.setStatus("Pending");

        // Simula il comportamento del service
        when(notificationService.findById(1L)).thenReturn(mockNotification);

        // Chiama il metodo da testare
        String viewName = notificationController.acceptNotification(1L);

        // Verifica che lo stato della notifica sia stato aggiornato
        assertEquals("Accepted", mockNotification.getStatus());

        // Verifica che la notifica sia stata salvata
        verify(notificationService).save(mockNotification);

        // Verifica il redirect corretto
        assertEquals("redirect:/notifications", viewName);
    }

    @Test
    public void testRejectNotification() {
        Notification mockNotification = new Notification();
        mockNotification.setId(1L);
        mockNotification.setStatus("Pending");

        // Simula il comportamento del service
        when(notificationService.findById(1L)).thenReturn(mockNotification);

        // Chiama il metodo da testare
        String viewName = notificationController.rejectNotification(1L);

        // Verifica che lo stato della notifica sia stato aggiornato
        assertEquals("Rejected", mockNotification.getStatus());

        // Verifica che la notifica sia stata salvata
        verify(notificationService).save(mockNotification);

        // Verifica il redirect corretto
        assertEquals("redirect:/notifications", viewName);
    }
}
