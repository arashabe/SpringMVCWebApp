package com.spring.ums.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.spring.ums.entity.Notification;
import com.spring.ums.service.NotificationService;

@Controller
public class IndexController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/index")
    public String showIndex(Model model) {
        // Ottieni l'utente corrente
        String userEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        // Recupera le notifiche per l'utente corrente
        List<Notification> userNotifications = notificationService.getNotificationsForTwoUsers(userEmail);

        // Aggiungi le notifiche al model
        model.addAttribute("userNotifications", userNotifications);

        return "index";
    }
}
