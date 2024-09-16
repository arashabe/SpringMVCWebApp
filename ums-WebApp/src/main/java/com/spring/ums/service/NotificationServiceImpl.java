package com.spring.ums.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.ums.entity.Notification; 
import com.spring.ums.entity.User;
import com.spring.ums.repository.NotificationRepository;


/**
 * l'annotazione @Service, che è una convenzione di Spring per indicare che
 *  questa classe è un componente di servizio e dovrebbe essere gestita dal framework.
 *   Quando Spring esegue la scansione dei componenti,
 *  individuerà questa classe e la registrerà come implementazione di NotificationService.
 *  NotificationServiceImpl implementa l'interfaccia NotificationService
 */
@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserService userService;
    
    @Override
    public void saveGroupRequestNotification(String senderEmail, String recipientEmail) {
	
    	/**
    	 * Notifica per il destinatario
    	 */

        Notification notification = new Notification();
        notification.setSenderEmail(senderEmail);
        notification.setRecipientEmail(recipientEmail);
        // Popola le skills dell'utente destinatario
        String recipientSkills = getUserSkills(recipientEmail);
        notification.setRecipientSkills(recipientSkills);
        notification.setCreationDate(LocalDateTime.now());
        notification.setMessage("Richiesta di creazione gruppo " + recipientSkills + " da " + senderEmail);

        /**
         * Il metodo save fornito dal repository JPA prende l'oggetto Notification e
         * si occupa di generare e eseguire la query SQL necessaria per salvarlo nel database.
         * Non è necessario scrivere manualmente la query SQL di inserimento.
         * JPA genererà una query di inserimento appropriata per l'entità Notification.
         */

        notificationRepository.save(notification);
    }

    private String getUserSkills(String userEmail) {
        User user = userService.findByEmail(userEmail);

        if (user != null) {
            return user.getSkills();
        } else {
            // Ritorna una stringa vuota o un valore predefinito se l'utente non è trovato
            return "";
        }
    }

    @Override
    public List<Notification> getNotificationsForUser(String userEmail) {
        // Implementa la logica per recuperare le notifiche per l'utente specificato
        return notificationRepository.findByRecipientEmail(userEmail);
    }

    public Notification findById(Long id) {
        return notificationRepository.findById(id).orElse(null);
    }

    public void save(Notification notification) {
        notificationRepository.save(notification);
    }

 
    @Override
    public List<Notification> getNotificationsForTwoUsers(String userEmail) {
        // Recupera tutte le notifiche in cui l'utente è il destinatario o il mittente
        return notificationRepository.findByRecipientEmailOrSenderEmailAndStatus(userEmail, userEmail, "Accepted");
    }
    
}
