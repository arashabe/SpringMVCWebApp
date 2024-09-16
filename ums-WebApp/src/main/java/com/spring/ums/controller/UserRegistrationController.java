package com.spring.ums.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.spring.ums.dto.UserRegisterDto;
import com.spring.ums.service.UserService;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

    @Autowired
    private UserService userService;

    /**
     * Metodo che fornisce un oggetto UserRegisterDto al modello per la registrazione.
     * 
     * @return Un oggetto UserRegisterDto vuoto.
     */
    @ModelAttribute("user")
    public UserRegisterDto userRegistrationDto() {
        return new UserRegisterDto();
    }

    /**
     * Metodo per visualizzare il modulo di registrazione.
     * 
     * @return La stringa del nome della vista da visualizzare ("registration").
     */
    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }

    /**
     * Metodo per gestire la registrazione dell'utente.
     * 
     * @param registrationDto Oggetto UserRegisterDto contenente i dati di registrazione.
     * @param model Modello per passare dati alla vista.
     * @return La stringa del percorso di reindirizzamento ("redirect:/registration?success") in caso di successo.
     *         La stringa del nome della vista da visualizzare ("registration") in caso di errore.
     */
    @PostMapping
    public String registerUserAccount(
            @ModelAttribute("user") UserRegisterDto registrationDto,
            Model model) {
        try {
            // Chiamata al servizio per salvare l'utente nel database
            userService.save(registrationDto);
            return "redirect:/registration?success"; // Reindirizza con un parametro di successo
        } catch (RuntimeException e) {
            // Gestisci eventuali eccezioni e aggiungi messaggio di errore al modello
            model.addAttribute("error", e.getMessage());
            return "registration"; // Tornare alla pagina di registrazione con un messaggio di errore
        }
    }
}





