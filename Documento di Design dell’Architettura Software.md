## Documento di Design dell’Architettura Software

#### 1. Introduzione

##### 1.1 Scopo del documento
Il presente documento descrive l'architettura software del progetto **Web App per la gestione di gruppi di studio**, con particolare attenzione alle componenti principali, alle interazioni tra i vari livelli e alle scelte tecnologiche adottate. Inoltre, verrà presentato lo pseudocodice degli algoritmi fondamentali utilizzati all'interno del sistema.

##### 1.2 Contesto del sistema
L'architettura del sistema segue il pattern **Model-View-Controller (MVC)**, che consente di separare la logica di business, la presentazione e il controllo del flusso dell'applicazione. La web app, sviluppata utilizzando **Spring Framework** e **Spring Security**, interagisce con un database embedded **H2** per la gestione dei dati relativi agli utenti, ai gruppi di studio e alle notifiche.

#### 2. Descrizione dell’Architettura

##### 2.1 Architettura a tre livelli (MVC)

L'applicazione è progettata utilizzando un'architettura a tre livelli composta da:
- **Presentation Layer (View)**: Gestisce l'interfaccia utente e la visualizzazione delle informazioni. In questo livello, viene utilizzato **Thymeleaf** come motore di template per generare dinamicamente le pagine HTML.
- **Business Logic Layer (Controller)**: Gestisce la logica di business e l'elaborazione delle richieste provenienti dalla vista. Utilizza i **Controller di Spring** per coordinare le operazioni tra la vista e il modello.
- **Data Access Layer (Model)**: Gestisce l'accesso ai dati e la persistenza. Questa parte dell'architettura utilizza **JPA (Java Persistence API)** e **H2 Database** per l'interazione con il database.

##### 2.2 Componenti principali dell’architettura

1. **Controller**: I controller di Spring elaborano le richieste HTTP e orchestrano le interazioni tra la vista e il modello. Alcuni esempi di controller sono:
    - **ChangePasswordController**: Gestisce la logica per mostrare il modulo di cambio password e processare la richiesta di modifica della password dell'utente autenticato.
    - **HomeController**: Gestisce il reindirizzamento alla pagina di login e reindirizza la homepage alla pagina "index".
    - **IndexController**: Gestisce la logica per visualizzare la pagina "index" e recupera le notifiche per l'utente autenticato, aggiungendole al modello per la visualizzazione.
    - **SearchController**: Gestisce la logica per la ricerca di utenti in base a una query e per l'invio di richieste di creazione di gruppo tramite notifiche tra utenti.
    - **RegistrationController**: Gestisce le richieste di registrazione e validazione delle informazioni.
    - **UserProfileController**: Gestisce la visualizzazione e l'aggiornamento del profilo dell'utente autenticato, consentendo di mostrare i dati dell'utente e di salvarne eventuali modifiche tramite il servizio UserService.
    - **NotificationController**: Gestisce l'invio e la ricezione di notifiche per formare gruppi di studio.

2. **Service Layer**: Contiene la logica di business dell'applicazione. Gli oggetti service si occupano di eseguire operazioni complesse, come la gestione delle password e delle notifiche. Alcuni servizi includono:
    - **NotificationService**: Definisce i metodi per gestire le notifiche, inclusa la creazione, il salvataggio e il recupero di notifiche per singoli o due utenti, nonché la ricerca di notifiche tramite ID.
    - **NotificationServiceImpl**: Implementa l'interfaccia NotificationService, gestendo il salvataggio delle notifiche di richieste di gruppo, il recupero di notifiche per singoli utenti o due utenti, e l'aggiornamento delle notifiche nel database tramite il repository JPA.
    - **UserService**: Estende UserDetailsService di Spring Security e definisce i servizi per gestire gli utenti, inclusi la registrazione, l'aggiornamento delle informazioni, il controllo delle password, la modifica delle password e la ricerca di utenti, per integrare l'autenticazione e la sicurezza nel sistema.
    - **UserServiceImpl**: Implementa la logica di business per la registrazione, ricerca, aggiornamento e autenticazione degli utenti, integrandosi con Spring Security per la gestione delle password e dei ruoli.

3. **Repository Layer**: Utilizza **JPA** per l'interazione con il database. Le classi repository forniscono metodi per eseguire query e operazioni CRUD (Create, Read, Update, Delete). Alcuni repository includono:
    - **UserRepository**: Estende JpaRepository e definisce metodi per trovare un utente tramite email e per cercare utenti in base a nome, cognome, competenze e corso di studio.
    - **NotificationRepository**: Estende JpaRepository e definisce metodi per recuperare le notifiche in base all'email del destinatario e per ottenere notifiche per due utenti con uno stato specifico.

4. **Entity Layer**: Contiene le classi che mappano le tabelle del database. Gli oggetti in questo livello rappresentano le entità del sistema, come:
    - **User**: Rappresenta gli utenti della piattaforma.
    - **Notification**: Rappresenta un'entità JPA che gestisce le notifiche nel sistema. Include attributi come id, senderEmail, recipientEmail, recipientSkills, message, creationDate, e status, con i relativi metodi getter e setter per l'accesso e la modifica di questi dati.
    - **Role**: Gestisce i ruoli degli utenti (es. utente standard, amministratore).


#### Flusso di Dati nell'Applicazione

1. **Richiesta di Registrazione**
   - L'utente accede alla pagina di registrazione e invia il form con i dati.
   - Il `UserRegistrationController` riceve la richiesta POST, chiama il `UserService` per creare un nuovo utente e lo salva nel database attraverso il `UserRepository`.
   - In caso di successo, l'utente viene reindirizzato a una pagina di conferma con un messaggio di successo.

2. **Visualizzazione Profilo**
   - Quando un utente autenticato accede alla pagina del profilo, il `UserProfileController` recupera le informazioni dal `UserService` tramite l'email dell'utente autenticato.
   - Le informazioni utente vengono aggiunte al modello e passate alla vista (`profile.html`), che visualizza i dati.

3. **Invio di una Richiesta di Gruppo**
   - Un utente autenticato invia una richiesta per formare un gruppo di studio.
   - Il `NotificationController` gestisce la richiesta, salva una notifica nel database tramite `NotificationService`, e reindirizza l'utente alla pagina di ricerca con un messaggio di conferma.

4. **Gestione delle Notifiche**
   - Il `NotificationController` gestisce la visualizzazione delle notifiche. Recupera le notifiche dal database attraverso il `NotificationService` e le passa alla vista `notifications.html`, dove vengono mostrate all'utente.

### Analisi della Complessità

#### Ricerca Utenti
- **Metodo coinvolto**: `userService.searchUsers(query)`
- La ricerca di utenti nel sistema avviene attraverso query al database. Se il numero di utenti aumenta significativamente, la complessità di questa operazione può diventare rilevante.
- La complessità computazionale di una ricerca utente dipende dall'implementazione della query e dall'indicizzazione nel database. Nel caso di una query lineare (ad es., ricerca senza indici), la complessità è **O(n)**, dove **n** è il numero di utenti nel database. Se viene implementata una query indicizzata, la complessità può essere ridotta a **O(log n)**.

#### Gestione delle Notifiche
- **Metodo coinvolto**: `notificationService.getNotificationsForUser(userEmail)`
- La complessità della visualizzazione delle notifiche è anch'essa legata alla dimensione del dataset. Poiché recuperiamo tutte le notifiche di un singolo utente, la complessità di questa operazione è **O(m)**, dove **m** è il numero di notifiche associate all'utente.
- Ottimizzazioni, come la paginazione o la gestione delle notifiche non lette, possono ridurre l'overhead computazionale e migliorare le prestazioni.

#### Invio di Richieste di Gruppo
- **Metodo coinvolto**: `notificationService.saveGroupRequestNotification(senderEmail, recipientEmail)`
- L'invio di una richiesta di gruppo comporta l'aggiunta di una nuova notifica nel database. Questa operazione è in generale **O(1)** per quanto riguarda il tempo di inserimento, se il database utilizza una struttura indicizzata o bilanciata per le operazioni di inserimento.
  


##### 2.3 Diagramma dell'architettura

![Diagramma arch](https://www.planttext.com/api/plantuml/png/ZPC_JyCm4CLtVufJzowC3AZyapfKg2I51J4SxZLOE7OuEq24-ExObeZi9Y0swvtVxzdFyifvPD-tMhWNPJfaR47QjhE6ZA_ze0cOf4VJQ19YdC1Yo-J20hso1ZOu42_WKm0yx9w7bZJX0NRKTXezkSUernqhqy2bDPwjriH1_YgG5xrJXfo3ZMsKBA1tZhWcVbEI4kp6UdLGCgMFUacxGezmBcK27g4gwgnJtl8meUMZAV097HMqzGdyw6d_xFLXkJoRBG1EuLxHErHazXIdOdPNikSL15z5hpqv3rksyOf_U-Vg2enSXZMQlKwO8cdCpee9rgX0Lh6OpvYabvJY00NjE361yhA3-xOd7k3wWsGV0UBNeF-OnIgyGVm7tm00)


#### 3. Design Dettagliato delle Componenti

##### 3.1 LoginController
Il **LoginController** gestisce il processo di autenticazione. Utilizza **Spring Security** per la validazione delle credenziali e il controllo degli accessi.

**Pseudocodice per il login:**
```plaintext
// Metodo per gestire la richiesta di login
funzione gestisciLogin(username, password):
    utente = UserService.trovaUtentePerUsername(username)
    se utente == null:
        mostraErrore("Utente non trovato")
    se !PasswordValidator.valida(password, utente.password):
        mostraErrore("Password errata")
    altrimenti:
        autenticaUtente(utente)
        reindirizzaVersoHome()
```

##### 3.2 RegistrationController
Il **RegistrationController** gestisce la registrazione di nuovi utenti, con validazione delle informazioni inserite e salvataggio nel database.

**Pseudocodice per la registrazione:**
```plaintext
// Metodo per gestire la richiesta di registrazione
funzione gestisciRegistrazione(formRegistrazione):
    se !validaEmail(formRegistrazione.email):
        mostraErrore("Email non valida")
    se UserService.trovaUtentePerEmail(formRegistrazione.email) != null:
        mostraErrore("Email già in uso")
    se !validaPassword(formRegistrazione.password):
        mostraErrore("Password non valida")
    altrimenti:
        utente = creaUtente(formRegistrazione)
        UserService.salva(utente)
        mostraMessaggio("Registrazione completata con successo")
```

##### 3.3 NotificationService
Il **NotificationService** gestisce l’invio e la ricezione di notifiche tra utenti, coordinando il salvataggio delle notifiche nel database.

**Pseudocodice per l'invio di notifiche:**
```plaintext
// Metodo per inviare una notifica a un utente
funzione inviaNotifica(utenteDestinatario, messaggio):
    notifica = creaNotifica(utenteDestinatario, messaggio)
    NotificationRepository.salva(notifica)
    inviaNotificaReale(utenteDestinatario.email, messaggio)
    mostraMessaggio("Notifica inviata con successo")
```

#### 4. Diagrammi UML delle Classi Principali

##### 4.1 Diagramma delle Classi (Esempio semplificato)

![Diagramma delle Classi](https://www.planttext.com/api/plantuml/png/TL3B2i903BplLomzATA3Lm_IWqVneFW1eOqbi0_Pf8A8_sww2cwnNfRip6GoISC2IGRlJEk0sPuPatqOQ_Snz9OwhKwIIEj1cG0UlmXw86UUvjDwY48NQa4eXdaBZypGznGLoeC6mI2uGnOAe46KKRjCrVKsQuvuZKmIqzrkRBMkp6JCg2g17tMnLi6DwLQMmTrWyGwYpt9-bJzjanYX-6IR1aCt7lK5)

#### 5. Scelte Progettuali

##### 5.1 Pattern Architetturali
- **MVC (Model-View-Controller)**: Questo pattern separa chiaramente la logica di presentazione dalla logica di business e dai dati, facilitando la manutenibilità e la scalabilità del sistema.
- **DTO (Data Transfer Object)**: I DTO vengono utilizzati per trasferire dati tra le varie componenti del sistema, riducendo il legame tra il modello di dominio e la vista.

##### 5.2 Sicurezza
L'autenticazione e l'autorizzazione degli utenti sono gestite tramite **Spring Security**, che fornisce una gestione sicura delle sessioni utente e delle credenziali. Le password vengono memorizzate in formato criptato utilizzando algoritmi sicuri (ad esempio **bcrypt**).

#### 6. Conclusioni

Il design dell'architettura presentato in questo documento fornisce una soluzione modulare, scalabile e sicura per la gestione di una piattaforma che permette la formazione di gruppi di studio online. L’utilizzo di pattern consolidati come MVC e l'integrazione di strumenti come Spring Security e JPA garantiscono la robustezza e l'efficienza del sistema.

## Next

- [Documenti per l’Analisi Dinamica](https://github.com/arashabe/ums/blob/main/Documenti%20per%20l%E2%80%99Analisi%20Dinamica%20Junit.md)
