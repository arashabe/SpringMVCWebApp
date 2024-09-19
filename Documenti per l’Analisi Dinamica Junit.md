## Documenti per l’Analisi Dinamica  
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

#### 4.1 **Test della Funzionalità di Registrazione Utente**
- **Classe Testata**: `RegistrationController`
- **Descrizione del Test**: Verifica che un nuovo utente possa essere registrato con successo.
- **Test Cases**:
  - Registrazione con dati validi.
  - Tentativo di registrazione con username già esistente.
  - Registrazione con campi vuoti o con valori non validi (email non valida, password troppo corta).

```java
@Test
public void testRegistrationWithValidData() {
    RegistrationDTO registrationDTO = new RegistrationDTO("john_doe", "john@example.com", "securePassword");
    ResponseEntity<?> response = registrationController.registerUser(registrationDTO);
    assertEquals(HttpStatus.OK, response.getStatusCode());
}
```

#### 4.2 **Test della Funzionalità di Login**
- **Classe Testata**: `LoginController`
- **Descrizione del Test**: Verifica che un utente possa effettuare il login con credenziali valide e che venga gestito correttamente il login fallito.
- **Test Cases**:
  - Login con credenziali corrette.
  - Login con username o password errati.
  - Tentativo di login con account disabilitato.

```java
@Test
public void testLoginWithValidCredentials() {
    LoginDTO loginDTO = new LoginDTO("john_doe", "securePassword");
    ResponseEntity<?> response = loginController.login(loginDTO);
    assertEquals(HttpStatus.OK, response.getStatusCode());
}
```

#### 4.3 **Test della Funzionalità di Notifica**
- **Classe Testata**: `NotificationService`
- **Descrizione del Test**: Verifica che un utente riceva correttamente una notifica dopo l'invio da parte di un altro utente.
- **Test Cases**:
  - Invio di una notifica valida.
  - Invio di una notifica con dati mancanti.
  - Recupero delle notifiche non lette.

```java
@Test
public void testSendNotification() {
    NotificationDTO notificationDTO = new NotificationDTO("john_doe", "Message text");
    notificationService.sendNotification(notificationDTO);
    assertTrue(notificationRepository.findAllByRecipient("john_doe").size() > 0);
}
```

#### 4.4 **Test della Funzionalità di Ricerca Utenti**
- **Classe Testata**: `SearchController`
- **Descrizione del Test**: Verifica che la ricerca degli utenti basata su interessi e altri criteri funzioni correttamente.
- **Test Cases**:
  - Ricerca con criteri validi.
  - Ricerca senza criteri specifici.
  - Gestione della ricerca con risultati vuoti.

```java
@Test
public void testSearchUsersByInterest() {
    List<User> users = searchController.searchUsersByInterest("Java");
    assertNotNull(users);
    assertTrue(users.size() > 0);
}
```

#### 4.5 **Test della Funzionalità di Cambio Password**
- **Classe Testata**: `PasswordController`
- **Descrizione del Test**: Verifica che un utente possa cambiare la propria password.
- **Test Cases**:
  - Cambio password con credenziali valide.
  - Tentativo di cambio password con password attuale errata.
  - Tentativo di cambio password con nuova password non valida.

```java
@Test
public void testChangePasswordWithValidData() {
    ChangePasswordDTO changePasswordDTO = new ChangePasswordDTO("john_doe", "oldPassword", "newSecurePassword");
    ResponseEntity<?> response = passwordController.changePassword(changePasswordDTO);
    assertEquals(HttpStatus.OK, response.getStatusCode());
}
```

---

### 5. **Copertura del Codice**

Abbiamo utilizzato EclEmma, uno strumento di analisi della copertura del codice integrato in Eclipse, che permette di misurare quanto del codice sorgente viene effettivamente eseguito durante i test unitari. Questo strumento è stato scelto per ottenere una visione chiara della qualità dei nostri test, evidenziando le parti di codice non coperte e aiutandoci a individuare eventuali punti deboli nel sistema.

Grazie a EclEmma, abbiamo potuto identificare le aree critiche del nostro progetto che richiedono un miglioramento in termini di test. In particolare, sebbene la copertura delle linee di codice sia soddisfacente, la copertura dei metodi potrebbe essere ulteriormente migliorata per garantire una verifica più completa dei casi limite e degli scenari di errore.

---

### 6. **Conclusioni**

Gli unit test sviluppati coprono le principali funzionalità della web app, verificando che il sistema si comporti correttamente nelle condizioni previste. L'uso di JUnit ha permesso di automatizzare il processo di test e rilevare eventuali regressioni durante lo sviluppo. La copertura del codice è soddisfacente, ma si raccomanda di aggiungere ulteriori test per coprire casi limite e condizioni di errore che potrebbero non essere stati ancora verificati.  


---

## Next

- [Documento di Testing delle API con Postman](https://github.com/arashabe/ums/blob/main/Documento%20di%20Testing%20delle%20API%20con%20Postman.md)

