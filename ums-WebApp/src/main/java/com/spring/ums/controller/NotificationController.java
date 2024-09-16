package com.spring.ums.controller;

import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.spring.ums.entity.Notification;
import com.spring.ums.service.NotificationService;

@Controller
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping
    public String showNotifications(Model model) {
        // Ottieni l'utente corrente
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        // Recupera le notifiche per l'utente corrente
        List<Notification> notifications = notificationService.getNotificationsForUser(userEmail);

        // Aggiungi le notifiche al model
        model.addAttribute("notifications", notifications);
        


        return "notifications";
    }
    

    
    @PostMapping("/send-group-request")
    public String sendGroupRequest(@RequestParam("recipientEmail") String recipientEmail, Model model) {
        // Ottieni l'utente corrente
        String senderEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        // Implementare la logica per salvare la notifica nel database
        notificationService.saveGroupRequestNotification(senderEmail, recipientEmail);


        return "redirect:/search?groupRequestSent";
    }
    

    @PostMapping("/accept-notification/{notificationId}")
    public String acceptNotification(@PathVariable Long notificationId) {
        // Trova la notifica nel database
        Notification notification = notificationService.findById(notificationId);

        // Imposta lo stato su "Accepted"
        notification.setStatus("Accepted");

        // Salva la notifica nel database
        notificationService.save(notification);

        // Reindirizza alla pagina delle notifiche
        return "redirect:/notifications";
    }

    @PostMapping("/reject-notification/{notificationId}")
    public String rejectNotification(@PathVariable Long notificationId) {
        // Trova la notifica nel database
        Notification notification = notificationService.findById(notificationId);

        // Imposta lo stato su "Rejected"
        notification.setStatus("Rejected");

        // Salva la notifica nel database
        notificationService.save(notification);

        // Reindirizza alla pagina delle notifiche
        return "redirect:/notifications";
    }
    
    
}
