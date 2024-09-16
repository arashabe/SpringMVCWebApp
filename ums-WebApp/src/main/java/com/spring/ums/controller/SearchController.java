package com.spring.ums.controller;

import com.spring.ums.entity.User;
import com.spring.ums.service.NotificationService;
import com.spring.ums.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SearchController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private NotificationService notificationService;


    @GetMapping("/search")
    public String showSearchForm(@RequestParam(name = "query", required = false) String query, Model model) {
        if (query != null && !query.isEmpty()) {
            List<User> searchResults = userService.searchUsers(query);
            model.addAttribute("searchResults", searchResults);
        }
        return "search";
    }

    @PostMapping("/send-group-request")
    public String sendGroupRequest(@RequestParam("recipientEmail") String recipientEmail, Model model) {
        // Logica per inviare la richiesta di creazione di gruppo
        // Ottieni l'utente corrente
        String senderEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        // Implementare la logica per salvare la notifica nel database
        notificationService.saveGroupRequestNotification(senderEmail, recipientEmail);


        return "redirect:/search?groupRequestSent";
    }
}