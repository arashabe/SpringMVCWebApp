### Iterazione 1: Implementazione del Login e Registrazione
#### Obiettivo
Implementare le funzionalità di base di login e registrazione degli utenti, garantendo sicurezza e validazione degli input.

#### 1. Requisiti funzionali
- Gli utenti devono poter creare un account con username, email e password.
- Gli utenti devono poter effettuare il login utilizzando le loro credenziali.
  
#### 2. Design UML
- **Diagramma delle classi**: Includere le entità `User`, `Role`, e i controller per login e registrazione (`UserRegistrationController`, `LoginController`).

#### 3. Implementazione
- **Controller**: `UserRegistrationController` gestisce la registrazione; `LoginController` gestisce il login.
- **Service Layer**: `UserService` e `UserDetailsService` per la gestione della sicurezza e autenticazione.
- **Database**: Aggiunta di un repository per salvare i dati degli utenti con Hibernate.

#### 4. Testing
- **JUnit**: Scrivere test di unità per validare il flusso di login e registrazione.
- **EclEmma**: Misurare la copertura del codice con almeno l'80%.

#### 5. Analisi statica
- **STAN4J**: Analisi statica per trovare eventuali errori o problemi di design.

---
