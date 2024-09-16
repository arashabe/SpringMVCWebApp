package com.spring.ums.service;

import java.util.List;
import com.spring.ums.entity.Notification;

/**
 * Interfaccia che definisce i servizi per la gestione delle notifiche.
 */
public interface NotificationService {

    /**
     * Salva una notifica di richiesta di gruppo nel database.
     *
     * @param senderEmail    L'indirizzo email del mittente della richiesta.
     * @param recipientEmail L'indirizzo email del destinatario della richiesta.
     */
    void saveGroupRequestNotification(String senderEmail, String recipientEmail);

    /**
     * Ottiene la lista di notifiche per un utente specificato.
     *
     * @param userEmail L'indirizzo email dell'utente per il quale recuperare le notifiche.
     * @return Una lista di oggetti Notification relative all'utente.
     */
    List<Notification> getNotificationsForUser(String userEmail);

    /**
     * Trova una notifica nel database dato il suo ID.
     *
     * @param id L'ID della notifica da trovare.
     * @return L'oggetto Notification corrispondente all'ID specificato.
     */
    Notification findById(Long id);

    /**
     * Salva una notifica nel database.
     *
     * @param notification L'oggetto Notification da salvare.
     */
    void save(Notification notification);

    /**
     * Ottiene la lista di notifiche per due utenti specificati.
     *
     * @param userEmail L'indirizzo email dell'utente per il quale recuperare le notifiche.
     * @return Una lista di oggetti Notification relative agli utenti specificati.
     */
    List<Notification> getNotificationsForTwoUsers(String userEmail);
}
