package com.spring.ums.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.spring.ums.entity.User;
import com.spring.ums.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/profile")
public class UserProfileController {

    @Autowired
    private UserService userService;

    /**
     * Metodo per visualizzare il profilo dell'utente.
     * 
     * @param principal Oggetto che rappresenta l'utente autenticato.
     * @param model Modello per passare dati alla vista.
     * @return La stringa del nome della vista da visualizzare ("profile").
     */
    
    @GetMapping
    public String viewProfile(Principal principal, Model model) {
    	// Verifica se l'utente è autenticato
        if (principal instanceof Authentication) {
        	// Ottieni il nome utente dall'oggetto Authentication
            String username = ((Authentication) principal).getName();
            // Trova l'utente nel database tramite l'indirizzo email
            User user = userService.findByEmail(username);
            // Aggiungi l'utente al modello per visualizzazione nella vista
            model.addAttribute("user", user);
        }
        return "profile";
    }

    /**
     * Metodo per aggiornare il profilo dell'utente.
     * 
     * @param updatedUser Oggetto User contenente i dati aggiornati dell'utente.
     * @param model Modello per passare dati alla vista.
     * @param redirectAttributes Attributi per reindirizzare e aggiungere informazioni alla successiva richiesta.
     * @return La stringa del percorso di reindirizzamento ("redirect:/profile").
     */
    
    @PostMapping("/update")
    public String updateProfile(@ModelAttribute("user") User updatedUser, Model model, RedirectAttributes redirectAttributes) {
        try {
            // Chiamata al servizio per aggiornare l'utente nel database
            userService.updateUser(updatedUser);
            // Aggiungi attributo di successo per visualizzazione nella vista successiva
            redirectAttributes.addAttribute("success", true);
        } catch (Exception e) {
            // Gestisci eventuali eccezioni e aggiungi messaggio di errore al modello
            model.addAttribute("error", e.getMessage());
        }
        return "redirect:/profile";
    }
}
