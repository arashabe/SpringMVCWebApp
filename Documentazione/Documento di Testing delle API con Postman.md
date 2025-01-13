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

- **Endpoint**: `/registration`
- **Metodo HTTP**: `POST`
- **Richiesta** (formato form-data):
  ```
  {
  "firstName": "John",
  "lastName": "Doe",
  "skills": "Java, Spring",
  "courseOfStudy": "Computer Science",
  "email": "johndoe@example.com",
  "password": "password123"
  }
  ```
- **Esempio di Script di Test Postman**:
```
pm.test("Verifica redirect (status 3xx)", function () {
    pm.response.to.have.status(200);
});
```

- **Risultato Atteso**:

    - **Codice di Stato**: 200 ok, registrazione avvenuta con successo.

---

#### 3.2 **Test API di Login**

- **Endpoint**: `/login`
- **Metodo HTTP**: `POST`
- **Richiesta** (formato form-data):
  ```
  {
  "username": "johndoe@example.com",
  "password": "password123"
  }
  ```
- **Esempio di Script di Test Postman**:
```
// Verifica che la risposta finale sia 200 OK
pm.test("Verifica risposta 200 OK dopo reindirizzamento", function () {
    pm.response.to.have.status(200);
});

// Verifica che la pagina contenga elementi dell'index (es. titolo, testo)
pm.test("Verifica contenuto della pagina index", function () {
    pm.expect(pm.response.text()).to.include('here you can find all your active groups.');
});

```
- **Risultato Atteso**: 
  - **Codice di Stato**: `200 OK`
  - **Reindirizzamento**: L'utente viene reindirizzato alla pagina /index dopo il login.

Questa sezione include la verifica della risposta e del contenuto della pagina di index dopo il login.

---

#### 3.3 **Test API di Ricerca**

- **Endpoint**: `/search`
- **Metodo HTTP**: `GET`
- **Parametri di Query**: `?query=Java`
#### Esempio di Script di Test Postman:

```
// Verifica che la risposta sia 200 OK
pm.test("Verifica risposta 200 OK", function () {
    pm.response.to.have.status(200);
});

// Verifica che il corpo della risposta contenga risultati
pm.test("Verifica che i risultati siano presenti", function () {
    var responseBody = pm.response.text();
    pm.expect(responseBody).to.include("Search Results");
});

// Verifica che la query sia correttamente processata
pm.test("Verifica che la query sia 'Java'", function () {
    var query = pm.request.url.query.toObject();
    pm.expect(query.query).to.eql("Java");
});


```
  
- **Risultato Atteso**:

    - **Codice di Stato**: 200 OK
    - **Risposta**: Un array di utenti corrispondenti al criterio di ricerca.
```
PASS
Verifica risposta 200 OK
PASS
Verifica che i risultati siano presenti
PASS
Verifica che la query sia 'Java'
```

---

#### 3.4 **Test API di Cambio Password**

- **Endpoint**: `/change-password`
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

### 5. **Conclusioni**

L'uso di Postman ha permesso di simulare in modo realistico le interazioni con le API della web app, fornendo una piattaforma semplice e potente per testare gli endpoint REST. I test condotti hanno coperto i principali scenari di utilizzo delle API, compresi i casi di successo e di errore. 


---
## Next

- [Documento per l’Analisi Statica](../Documentazione/Documento%20per%20l%E2%80%99Analisi%20Statica.md)
