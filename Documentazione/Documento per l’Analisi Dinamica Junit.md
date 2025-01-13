## Documento per l’Analisi Dinamica  
#### Unit Test e Report di Copertura con JUnit

---

### 1. **Introduzione**

L'analisi dinamica del software è un processo essenziale per verificare il comportamento dell'applicazione in fase di esecuzione, assicurandosi che il sistema funzioni come previsto. Questo documento definisce gli unit test eseguiti utilizzando **JUnit** e il report di copertura prodotto con **EclEmma**, un plug-in per Eclipse, che misura la copertura del codice in termini di classi, metodi e linee di codice testate.

---

### 2. **Obiettivi degli Unit Test**

Gli obiettivi principali degli unit test sono:
- Verificare che i singoli componenti del sistema funzionino correttamente in isolamento.
- Assicurare che la logica di business implementata nelle classi sia corretta.
- Verificare la gestione delle eccezioni e delle condizioni di errore.
- Aumentare la robustezza del sistema rilevando bug e anomalie prima dell'integrazione e della distribuzione.
  
---

### 3. **Strumenti Utilizzati**

Per eseguire i test unitari e generare il report di copertura, sono stati utilizzati i seguenti strumenti:
- **JUnit**: Framework di testing per Java.
- **EclEmma**: Strumento integrato in Eclipse per la misurazione della copertura del codice.

---

### 4. **Definizione degli Unit Test**

Di seguito, vengono definiti i principali casi di test implementati per le funzionalità critiche della web app.

#### 4.1 **Test della Funzionalità di Salvataggio Utente**
- **Classe Testata**: `UserService`
- **Descrizione del Test**: Verifica che un nuovo utente venga salvato correttamente nel sistema quando vengono forniti i dettagli di registrazione.
- **Test Cases**:

1. **Salvataggio di un utente con dettagli validi**
   - **Descrizione**: Verifica che il sistema possa salvare correttamente un utente quando vengono forniti dettagli di registrazione validi.
   - **Input**:
     - **Email**: test@example.com
     - **Password**: "password"
   - **Simulazione**: Il repository `userRepository` simula il salvataggio dell'utente e restituisce un oggetto `User` con l'email specificata.
   - **Comportamento Atteso**:
     - Il metodo `save` del `UserService` deve restituire un oggetto `User` con l'email "test@example.com".
     - Il repository deve essere chiamato una volta per salvare l'oggetto `User`.
   - **Stato**: Superato

2. **Tentativo di salvataggio con email già esistente**
   - **Descrizione**: Verifica che il sistema gestisca correttamente il tentativo di registrare un utente con un'email già esistente.
   - **Input**:
     - **Email**: existing@example.com (email già presente nel database)
     - **Password**: "password"
   - **Simulazione**: Il repository `userRepository` restituisce un errore o lancia un'eccezione quando si tenta di salvare l'utente.
   - **Comportamento Atteso**:
     - Il sistema deve lanciare un'eccezione o restituire un messaggio di errore appropriato, indicando che l'email è già in uso.
   - **Stato**: Superato

3. **Tentativo di salvataggio con dettagli mancanti**
   - **Descrizione**: Verifica che il sistema gestisca correttamente il tentativo di registrare un utente senza dettagli richiesti (ad esempio, senza email o password).
   - **Input**:
     - **Email**: null
     - **Password**: "password"
   - **Simulazione**: L'oggetto `UserRegisterDto` non ha l'email impostata.
   - **Comportamento Atteso**:
     - Il sistema deve lanciare un'eccezione o restituire un messaggio di errore appropriato, indicando che l'email è obbligatoria.
   - **Stato**: Superato

```java
    @Test
    void save_ShouldReturnSavedUser() {
        UserRegisterDto userRegDto = new UserRegisterDto();
        userRegDto.setEmail("test@example.com");
        userRegDto.setPassword("password");

        User user = new User();
        user.setEmail("test@example.com");

        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.save(userRegDto);

        assertThat(savedUser.getEmail()).isEqualTo("test@example.com");
        verify(userRepository, times(1)).save(any(User.class));
    }
```

#### 4.2 **Test della Funzionalità della Home Page per Utenti Autenticati**
- **Classe Testata**: `HomeController`
- **Descrizione del Test**: Verifica che un utente autenticato venga reindirizzato correttamente alla pagina principale dell'applicazione quando accede alla home page.
- **Test Cases**:
**Accesso alla home page con utente autenticato**
   - **Descrizione**: Verifica che un utente autenticato venga reindirizzato alla pagina "index" quando accede all'URL della home page.
   - **Input**:
     - **Utente**: autenticato con username "testuser"
   - **Simulazione**: L'utente è autenticato nel contesto del test.
   - **Comportamento Atteso**:
     - Quando l'utente accede all'URL della home page (`/`), il sistema deve restituire uno stato di reindirizzamento (`3xx Redirection`).
     - L'URL di reindirizzamento deve essere `/index`, indicando che l'utente è portato alla pagina principale.
   - **Stato**: Superato

```java
    @Test
    @WithMockUser(username = "testuser")
    public void testHomePageWithAuthenticatedUser() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/index"));
    }
```

#### 4.3 **Test della Funzionalità di Notifica**
- **Classe Testata**: `NotificationController`
- **Descrizione del Test**: erifica che un utente possa accettare una notifica, aggiornando il suo stato e salvandola nel sistema.
- **Test Cases**:
- **Ricerca utenti con query valida**
   - **Descrizione**: Verifica che il sistema restituisca correttamente i risultati della ricerca quando viene fornita una query valida.
   - **Input**:
     - `query`: "testQuery"
   - **Simulazione**: Il servizio `UserService` simula il comportamento della ricerca restituendo dei risultati (`mockSearchResults`).
   - **Comportamento Atteso**:
     - I risultati della ricerca devono essere aggiunti al modello (`model.addAttribute("searchResults", mockSearchResults)`).
     - Il metodo deve restituire il nome della vista corretta, ovvero "search".
   - **Stato**: Superato

```java
    @Test
    public void testAcceptNotification() {
        Notification mockNotification = new Notification();
        mockNotification.setId(1L);
        mockNotification.setStatus("Pending");

        // Simula il comportamento del service
        when(notificationService.findById(1L)).thenReturn(mockNotification);

        // Chiama il metodo da testare
        String viewName = notificationController.acceptNotification(1L);

        // Verifica che lo stato della notifica sia stato aggiornato
        assertEquals("Accepted", mockNotification.getStatus());

        // Verifica che la notifica sia stata salvata
        verify(notificationService).save(mockNotification);

        // Verifica il redirect corretto
        assertEquals("redirect:/notifications", viewName);
    }
```

#### 4.4 **Test della Funzionalità di Ricerca Utenti**
- **Classe Testata**: `SearchController`
- **Descrizione del Test**: Verifica che il metodo di ricerca mostri correttamente i risultati di una query di ricerca degli utenti e che i dati siano visualizzati correttamente nella pagina di ricerca.
- **Test Cases**:
1. **Ricerca utenti con query valida**
   - **Descrizione**: Verifica che il sistema restituisca correttamente i risultati della ricerca quando viene fornita una query valida.
   - **Input**:
     - `query`: "testQuery"
   - **Simulazione**: Il servizio `UserService` simula il comportamento della ricerca restituendo dei risultati (`mockSearchResults`).
   - **Comportamento Atteso**:
     - I risultati della ricerca devono essere aggiunti al modello (`model.addAttribute("searchResults", mockSearchResults)`).
     - Il metodo deve restituire il nome della vista corretta, ovvero "search".
   - **Stato**: Superato

2. **Ricerca con query vuota**
   - **Descrizione**: Verifica che il sistema gestisca correttamente una query vuota o mancante, mostrando un messaggio di errore o una vista vuota.
   - **Input**:
     - `query`: ""
   - **Simulazione**: Il servizio `UserService` non restituisce risultati, poiché la query è vuota.
   - **Comportamento Atteso**:
     - Nessun risultato deve essere aggiunto al modello.
     - Il metodo deve restituire la vista "search" senza risultati.
   - **Stato**: Superato

3. **Ricerca utenti con query inesistente**
   - **Descrizione**: Verifica che il sistema gestisca una query che non restituisce risultati (ad esempio, una query che non corrisponde a nessun utente).
   - **Input**:
     - `query`: "nonExistentQuery"
   - **Simulazione**: Il servizio `UserService` non trova alcun utente corrispondente alla query, restituendo una lista vuota.
   - **Comportamento Atteso**:
     - Il modello deve contenere un attributo con una lista vuota per i risultati della ricerca.
     - Il metodo deve restituire la vista "search" con un messaggio che indica che non ci sono risultati.
   - **Stato**: Superato

```java

    @Test
    public void testShowSearchForm_WithQuery() {
        // Simula il comportamento del servizio di ricerca utenti
        when(userService.searchUsers("testQuery")).thenReturn(mockSearchResults);

        // Chiama il metodo da testare
        String viewName = searchController.showSearchForm("testQuery", model);

        // Verifica che i risultati della ricerca siano stati aggiunti al model
        verify(model).addAttribute("searchResults", mockSearchResults);

        // Verifica che il metodo restituisca la vista corretta
        assertEquals("search", viewName);
    }
}
```

#### 4.5 **Test della Funzionalità di Cambio Password**
- **Classe Testata**: `ChangePasswordController`
- **Descrizione del Test**: Verifica che un utente autenticato possa cambiare con successo la propria password inserendo la password corrente corretta e confermando correttamente la nuova password.
- **Test Cases**:

1. **Cambio password con successo**
   - **Descrizione**: Verifica che l'utente possa cambiare la password correttamente quando la password attuale è valida e la nuova password è confermata.
   - **Input**:
     - `currentPassword`: "oldPassword"
     - `newPassword`: "newPassword"
     - `confirmPassword`: "newPassword"
   - **Simulazione**: Il servizio `UserService` conferma che la password attuale è corretta (`checkPassword` restituisce `true`).
   - **Comportamento Atteso**: Il sistema deve reindirizzare alla pagina di login (`/login?passwordChanged`), indicando che il cambio della password è avvenuto con successo.
   - **Stato**: Superato

2. **Cambio password con password corrente errata**
   - **Descrizione**: Verifica che l'utente non possa cambiare la password se la password corrente inserita è errata.
   - **Input**:
     - `currentPassword`: "wrongPassword"
     - `newPassword`: "newPassword"
     - `confirmPassword`: "newPassword"
   - **Simulazione**: Il servizio `UserService` rileva che la password attuale è errata (`checkPassword` restituisce `false`).
   - **Comportamento Atteso**: Il sistema deve visualizzare un messaggio di errore e non permettere il cambio della password.
   - **Stato**: Superato

3. **Cambio password con nuova password non corrispondente**
   - **Descrizione**: Verifica che l'utente non possa cambiare la password se la nuova password e la conferma non coincidono.
   - **Input**:
     - `currentPassword`: "oldPassword"
     - `newPassword`: "newPassword1"
     - `confirmPassword`: "newPassword2"
   - **Simulazione**: Il sistema rileva che le nuove password non corrispondono.
   - **Comportamento Atteso**: Il sistema deve visualizzare un messaggio di errore e impedire il cambio della password.
   - **Stato**: Superato

4. **Cambio password con nuova password non valida**
   - **Descrizione**: Verifica che l'utente non possa cambiare la password se la nuova password non rispetta i requisiti (ad esempio, troppo corta).
   - **Input**:
     - `currentPassword`: "oldPassword"
     - `newPassword`: "123"
     - `confirmPassword`: "123"
   - **Simulazione**: Il sistema rileva che la nuova password non è conforme ai requisiti di sicurezza.
   - **Comportamento Atteso**: Il sistema deve visualizzare un messaggio di errore e non permettere il cambio della password.
   - **Stato**: Superato


```java
    @Test
    @WithMockUser(username = "testuser")
    public void testChangePasswordSuccess() throws Exception {
        // Simula che la password corrente sia corretta
        when(userService.checkPassword("testuser", "oldPassword")).thenReturn(true);

        mockMvc.perform(post("/change-password")
                        .param("currentPassword", "oldPassword")
                        .param("newPassword", "newPassword")
                        .param("confirmPassword", "newPassword")
                        .with(csrf())) // Aggiunge il token CSRF
                .andExpect(status().is3xxRedirection()) // Verifica un redirect a login dopo il successo
                .andExpect(redirectedUrl("/login?passwordChanged")); // Verifica che l'URL di reindirizzamento sia corretto
    }
```

---

### 5. **Copertura del Codice**

Abbiamo utilizzato EclEmma, uno strumento di analisi della copertura del codice integrato in Eclipse, che permette di misurare quanto del codice sorgente viene effettivamente eseguito durante i test unitari. Questo strumento è stato scelto per ottenere una visione chiara della qualità dei nostri test, evidenziando le parti di codice non coperte e aiutandoci a individuare eventuali punti deboli nel sistema.

Grazie a EclEmma, abbiamo potuto identificare le aree critiche del nostro progetto che richiedono un miglioramento in termini di test. In particolare, sebbene la copertura delle linee di codice sia soddisfacente, la copertura dei metodi potrebbe essere ulteriormente migliorata per garantire una verifica più completa dei casi limite e degli scenari di errore.
- Report: Abbiamo ottenuto una copertura totale del 83%. 

---

### 6. **Conclusioni**

Gli unit test sviluppati coprono le principali funzionalità della web app, verificando che il sistema si comporti correttamente nelle condizioni previste. L'uso di JUnit ha permesso di automatizzare il processo di test e rilevare eventuali regressioni durante lo sviluppo. La copertura del codice è soddisfacente, ma possiamo aggiungere ulteriori test per coprire casi limite e condizioni di errore che potrebbero non essere stati ancora verificati.  


---

## Next

- [Documento di Testing delle API con Postman](../Documentazione/Documento%20di%20Testing%20delle%20API%20con%20Postman.md)

