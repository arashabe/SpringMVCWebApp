## Documento di Testing delle API con Postman

---

### 1. **Introduzione**

In aggiunta agli unit test sviluppati con JUnit, è stata effettuata un’analisi delle API della web app utilizzando **Postman**. Questo strumento permette di testare le API REST esposte dall'applicazione, verificandone il corretto funzionamento in ambienti reali, simulando le interazioni che gli utenti potrebbero avere con il sistema.

---

### 2. **Obiettivi del Testing con Postman**

L’obiettivo principale del testing con Postman è verificare il corretto comportamento delle API della web app in vari scenari, tra cui:
- **Verifica della correttezza delle risposte**: Controllo delle risposte del server ai vari endpoint.
- **Validazione dei codici di stato HTTP**: Verifica che le risposte HTTP siano conformi alle aspettative (200, 201, 400, 401, 404, ecc.).
- **Gestione degli errori**: Test dei casi limite, come input non validi o mancanti.
- **Validazione dei dati**: Controllo della correttezza dei dati restituiti o inviati.

---

### 3. **Test delle API con Postman**

Di seguito sono riportati alcuni dei principali test condotti su Postman per le API esposte dall'applicazione.

---

#### 3.1 **Test API di Registrazione Utente**

- **Endpoint**: `/api/register`
- **Metodo HTTP**: `POST`
- **Richiesta**:

```json
{
  "username": "test_user",
  "email": "test_user@example.com",
  "password": "securePassword"
}
```

- **Risultato Atteso**: 
  - **Codice di Stato**: `201 Created`
  - **Risposta**: Un messaggio di conferma della registrazione.

- **Test Effettuati**:
  - **Registrazione con dati validi**: Verifica che l'utente venga creato con successo.
  - **Registrazione con username esistente**: Verifica che venga restituito un errore `409 Conflict`.
  - **Registrazione con dati mancanti**: Testa la gestione degli input incompleti con il codice di errore `400 Bad Request`.

#### Esempio di Risposta per Registrazione Avvenuta con Successo:

```json
{
  "message": "User registered successfully"
}
```

---

#### 3.2 **Test API di Login**

- **Endpoint**: `/api/login`
- **Metodo HTTP**: `POST`
- **Richiesta**:

```json
{
  "username": "test_user",
  "password": "securePassword"
}
```

- **Risultato Atteso**: 
  - **Codice di Stato**: `200 OK`
  - **Risposta**: Un token JWT per l'autenticazione nelle richieste successive.

- **Test Effettuati**:
  - **Login con credenziali valide**: Verifica la ricezione di un token JWT.
  - **Login con credenziali errate**: Verifica che venga restituito un errore `401 Unauthorized`.
  - **Login con account disabilitato**: Verifica che venga gestito il caso con un messaggio di errore specifico.

#### Esempio di Risposta per Login Avvenuto con Successo:

```json
{
  "token": "eyJhbGciOiJIUzUxMiJ9..."
}
```

---

#### 3.3 **Test API di Recupero Notifiche**

- **Endpoint**: `/api/notifications`
- **Metodo HTTP**: `GET`
- **Richiesta**: 

Richiesta effettuata dopo l’autenticazione con un token JWT.

- **Risultato Atteso**: 
  - **Codice di Stato**: `200 OK`
  - **Risposta**: Un array contenente le notifiche non lette dell’utente autenticato.

- **Test Effettuati**:
  - **Recupero di notifiche con token valido**: Verifica la corretta restituzione delle notifiche.
  - **Recupero notifiche senza autenticazione**: Testa la gestione della mancata autenticazione con codice `401 Unauthorized`.

#### Esempio di Risposta per Recupero Notifiche:

```json
[
  {
    "id": 1,
    "message": "You have been invited to join the group 'Spring Boot Enthusiasts'.",
    "read": false
  },
  {
    "id": 2,
    "message": "Your password has been changed successfully.",
    "read": true
  }
]
```

---

#### 3.4 **Test API di Ricerca Utenti**

- **Endpoint**: `/api/users/search`
- **Metodo HTTP**: `GET`
- **Parametri di Query**: `?interest=Java`

- **Risultato Atteso**: 
  - **Codice di Stato**: `200 OK`
  - **Risposta**: Un array di utenti corrispondenti al criterio di ricerca.

- **Test Effettuati**:
  - **Ricerca con criteri validi**: Verifica che venga restituito un elenco di utenti con l’interesse specificato.
  - **Ricerca con risultati vuoti**: Testa la gestione del caso in cui non vengano trovati utenti con i criteri di ricerca.

#### Esempio di Risposta per Ricerca Utenti:

```json
[
  {
    "username": "jane_doe",
    "interests": ["Java", "Spring Boot"]
  },
  {
    "username": "john_smith",
    "interests": ["Java", "Kotlin"]
  }
]
```

---

#### 3.5 **Test API di Cambio Password**

- **Endpoint**: `/api/users/change-password`
- **Metodo HTTP**: `POST`
- **Richiesta**:

```json
{
  "currentPassword": "oldPassword",
  "newPassword": "newSecurePassword"
}
```

- **Risultato Atteso**: 
  - **Codice di Stato**: `200 OK`
  - **Risposta**: Un messaggio che conferma l’avvenuto cambiamento di password.

- **Test Effettuati**:
  - **Cambio password con credenziali corrette**: Verifica che la password venga aggiornata correttamente.
  - **Cambio password con password attuale errata**: Verifica che venga restituito un errore `400 Bad Request`.
  - **Cambio password con nuova password non valida**: Testa la gestione delle password che non rispettano i criteri di sicurezza.

#### Esempio di Risposta per Cambio Password Avvenuto con Successo:

```json
{
  "message": "Password changed successfully"
}
```

---

### 4. **Test delle API Autenticazione e Autorizzazione**

Poiché la web app utilizza **Spring Security** per gestire l'autenticazione e l'autorizzazione, è stato necessario eseguire una serie di test mirati per verificare il corretto funzionamento del sistema di autenticazione basato su JWT.

#### 4.1 **Test di Accesso Autenticato**
- Verifica che, se un utente tenta di accedere a risorse protette senza un token JWT valido, venga restituito un codice di stato `401 Unauthorized`.
  
#### 4.2 **Test di Accesso a Risorse Protette**
- Simulazione di un utente che accede a endpoint protetti, come la modifica del profilo o la creazione di notifiche, con un token JWT valido.

---

### 5. **Conclusioni**

L'uso di Postman ha permesso di simulare in modo realistico le interazioni con le API della web app, fornendo una piattaforma semplice e potente per testare gli endpoint REST. I test condotti hanno coperto i principali scenari di utilizzo delle API, compresi i casi di successo e di errore. 


---
## Next

- [Demo del Progetto](https://github.com/arashabe/ums/blob/main/images/demo.md)
