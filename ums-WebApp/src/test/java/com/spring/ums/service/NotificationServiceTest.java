package com.spring.ums.service;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.spring.ums.entity.Notification;
import com.spring.ums.repository.NotificationRepository;

class NotificationServiceTest {
	
	@Mock
	private UserService userService;

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationServiceImpl notificationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveGroupRequestNotification_ShouldCallRepositorySave() {
        String senderEmail = "sender@test.com";
        String recipientEmail = "recipient@test.com";

        notificationService.saveGroupRequestNotification(senderEmail, recipientEmail);

        verify(notificationRepository, times(1)).save(any(Notification.class));
    }

    @Test
    void getNotificationsForUser_ShouldReturnNotificationList() {
        String userEmail = "user@test.com";
        Notification notification = new Notification();
        List<Notification> expectedNotifications = Arrays.asList(notification);

        when(notificationRepository.findByRecipientEmail(userEmail)).thenReturn(expectedNotifications);

        List<Notification> actualNotifications = notificationService.getNotificationsForUser(userEmail);

        assertThat(actualNotifications).isEqualTo(expectedNotifications);
        verify(notificationRepository, times(1)).findByRecipientEmail(userEmail);
    }

    @Test
    void findById_ShouldReturnNotification() {
        Long id = 1L;
        Notification expectedNotification = new Notification();  
        Optional<Notification> optionalNotification = Optional.of(expectedNotification);

        when(notificationRepository.findById(id)).thenReturn(optionalNotification);

        Notification actualNotification = notificationService.findById(id);  

        assertThat(actualNotification).isEqualTo(expectedNotification);  
        verify(notificationRepository, times(1)).findById(id);
    }



}
