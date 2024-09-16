package com.spring.ums.controller;

import com.spring.ums.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ChangePasswordController {

    @Autowired
    private UserService userService;

    @GetMapping("/change-password")
    public String showChangePasswordForm(Model model) {
        // Logica per mostrare il modulo di cambio password
        return "change-password";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam("currentPassword") String currentPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 @RequestParam("confirmPassword") String confirmPassword,
                                 RedirectAttributes redirectAttributes) {

        // Ottieni l'username dell'utente corrente
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Verifica la correttezza del password attuale
        if (!userService.checkPassword(username, currentPassword)) {
            redirectAttributes.addFlashAttribute("changePasswordError", "Current password is incorrect");
            return "redirect:/change-password";
        }

        // Verifica che il nuovo password e la conferma coincidano
        if (!newPassword.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("changePasswordError", "New password and confirmation do not match");
            return "redirect:/change-password";
        }

        // Effettua il cambio password
        userService.changePassword(username, newPassword);

        // Aggiungi attributo flash per indicare che il cambio password è avvenuto con successo
        redirectAttributes.addFlashAttribute("changePasswordSuccess", true);

        return "redirect:/login?passwordChanged";
    }
}