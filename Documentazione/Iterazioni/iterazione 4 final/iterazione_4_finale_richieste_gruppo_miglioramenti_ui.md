### **Iterazione 4 - Implementazione Richieste di Creazione di Gruppo e Miglioramenti UI**

#### **1. Introduzione**
L'Iterazione 4 rappresenta la fase finale del progetto, dove ci siamo concentrati sull'implementazione delle funzionalità di richiesta di gruppo, gestione delle notifiche per la formazione di gruppi di studio, e sul miglioramento dell'esperienza utente attraverso un'interfaccia utente più interattiva e fluida. Sono state implementate funzionalità AJAX per gestire richieste asincrone, migliorando la reattività dell'applicazione.

#### **2. Obiettivi**
- Implementare la richiesta di creazione di gruppi di studio tra gli utenti registrati.
- Migliorare la gestione delle notifiche di accettazione o rifiuto delle richieste di gruppo.
- Ottimizzare l'interfaccia utente (UI) per renderla più intuitiva e dinamica, con l'aggiunta di interazioni asincrone tramite AJAX.
- Garantire che il sistema sia pronto per essere consegnato come prodotto finale completo.

#### **3. Requisiti**
**Requisiti Funzionali:**
- Gli utenti devono poter inviare richieste di gruppo ad altri utenti tramite un pulsante nella pagina di ricerca.
- Gli utenti destinatari devono poter accettare o rifiutare le richieste di gruppo direttamente dalle notifiche.
- Le notifiche devono essere aggiornate dinamicamente senza necessità di ricaricare la pagina.
- Il miglioramento dell'interfaccia utente deve essere basato su usabilità e accessibilità.

**Requisiti Non Funzionali:**
- La risposta dell'interfaccia deve essere rapida e fluida, utilizzando tecnologie asincrone come AJAX.
- L’applicazione deve essere stabile e scalabile, pronta per il deployment.
- Il design della UI deve essere conforme agli standard di accessibilità e usabilità.

#### **4. Design**
**Architettura:**
- Il design MVC rimane al centro del progetto, con i **controller** che gestiscono la logica di business e le **view** che rappresentano l'interfaccia utente. I miglioramenti includono l'integrazione di richieste AJAX per una gestione fluida delle notifiche.
  
**Modelli UML:**
- **Class Diagram:** Includiamo le modifiche nei controller (`NotificationController`), nei servizi (`NotificationService`) e nei modelli (`Notification`), che ora supportano le richieste di gruppo.

#### **5. Implementazione**
In questa fase, vengono introdotte le funzionalità per inviare richieste di gruppo e migliorare l’esperienza utente con l’uso di AJAX. Le funzionalità principali sono:

**5.1 Richiesta di Creazione Gruppo**
- È stata introdotta una funzionalità che consente agli utenti di inviare una richiesta di creazione di gruppo ad altri utenti. L'invio della richiesta avviene tramite un form presente nella pagina di ricerca utenti:

```html
<form th:action="@{/notifications/send-group-request}" method="post">
    <input type="hidden" name="recipientEmail" th:value="${user.email}" />
    <button type="submit" class="btn btn-primary">Group Request</button>
</form>
```
- **Thymeleaf** viene utilizzato per popolare il campo nascosto con l'email del destinatario. Il pulsante "Group Request" invia una richiesta POST al controller che gestisce la creazione di una notifica per il destinatario.

**5.2 Gestione delle Notifiche**
- Dopo l'invio di una richiesta di gruppo, il destinatario riceve una notifica con la possibilità di accettare o rifiutare la richiesta.
- L'interazione con le notifiche è stata ottimizzata utilizzando **AJAX** per **evitare il ricaricamento della pagina** quando si accetta o si rifiuta una richiesta.

```javascript
<script>
    function acceptNotification(notificationId) {
        $.post("/notifications/accept-notification/" + notificationId, function(data) {
            window.location.reload();
        });
    }

    function rejectNotification(notificationId) {
        $.post("/notifications/reject-notification/" + notificationId, function(data) {
            window.location.reload();
        });
    }
</script>
```
- Quando un utente accetta o rifiuta una richiesta, viene effettuata una chiamata POST asincrona al controller tramite **AJAX**, permettendo di aggiornare lo stato della notifica senza dover ricaricare l'intera pagina.

**5.3 Controller e Service Layer**
- Il controller `NotificationController` gestisce le richieste di accettazione e rifiuto della notifica:

```java
   
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
```
- Nel service layer, la logica di gestione delle notifiche di gruppo è implementata nel `NotificationService`. Quando una richiesta viene accettata o rifiutata, il servizio aggiorna lo stato della notifica nel database e invia una risposta al client.

**5.4 Miglioramenti dell'Interfaccia Utente**
- Sono stati introdotti **pulsanti dinamici** per la gestione delle richieste di gruppo, che cambiano aspetto in base allo stato della richiesta (ad esempio, accettato, rifiutato).
  
- **Feedback visivo:** Quando una notifica viene accettata, un messaggio di successo viene visualizzato nella pagina, migliorando l'esperienza utente.

- **Responsive design migliorato:** Ulteriori miglioramenti nell’interfaccia utente sono stati fatti utilizzando Bootstrap per garantire che la gestione delle notifiche e delle richieste di gruppo sia facilmente accessibile su dispositivi mobili.

#### **6. Analisi Statica e Dinamica**
**Analisi Statica:**
- Durante questa fase, il codice è stato sottoposto a un'ulteriore analisi statica per assicurarsi che rispettasse le best practice di programmazione e che non vi fossero problemi di sicurezza o di performance.

**Analisi Dinamica:**
- **Testing di prestazioni:** Abbiamo condotto test sulle performance delle chiamate AJAX per assicurarci che le notifiche fossero gestite in modo efficiente anche con più utenti attivi contemporaneamente.
- **Testing di UI:** Abbiamo eseguito test manuali e automatici per verificare che l'interfaccia utente fosse intuitiva e che la gestione delle notifiche funzionasse correttamente su dispositivi mobili e desktop.

#### **7. Testing**
**Unit Test:**
- Sono stati aggiunti test sui metodi del `NotificationService` per verificare la corretta gestione delle richieste di gruppo e delle notifiche.

- Test specifici per i controller (`NotificationController`) sono stati implementati per simulare richieste di accettazione e rifiuto, assicurandoci che il flusso di notifica funzioni correttamente.

**Integration Test:**
- Sono stati eseguiti test d’integrazione che hanno verificato la comunicazione tra il controller, il service layer e il database, con focus sulla gestione delle notifiche e delle richieste di gruppo.
  
- Il flusso end-to-end (dall'invio della richiesta di gruppo fino all'accettazione o rifiuto) è stato testato in ambiente reale per garantire la stabilità dell'applicazione.

#### **8. Conclusione**
L'Iterazione 4 ha portato alla completa implementazione delle funzionalità di gestione delle richieste di gruppo e delle notifiche, nonché al miglioramento dell'interfaccia utente tramite l’uso di AJAX e un design responsive. Questa iterazione segna il completamento del progetto, che ora include tutte le funzionalità richieste, offrendo un'esperienza utente fluida e interattiva. L'applicazione è pronta per essere consegnata come prodotto finale.

