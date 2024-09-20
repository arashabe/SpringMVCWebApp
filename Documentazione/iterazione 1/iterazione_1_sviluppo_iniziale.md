### Iterazione 1: Implementazione del Login e Registrazione
#### Obiettivo
Implementare le funzionalità di base di login e registrazione degli utenti, garantendo sicurezza e validazione degli input.

#### 1. Requisiti funzionali
- Gli utenti devono poter creare un account con username, email e password.
- Gli utenti devono poter effettuare il login utilizzando le loro credenziali.
  
#### 2. Design UML
- **Diagramma delle classi**: Includere le entità `User`, `Role` e i controller per login e registrazione (`UserRegistrationController`, `LoginController`).
  
![Class Diagram](https://www.planttext.com/api/plantuml/png/hPNVRi8W5CRlUGhIAwUpsMwRnRWuJKpSNEny0D9Y9L8mG3NBuhiFMlz02sONwqLJ-Gu_ldCuu5Wg95HLKW3sADkX0iDeoXHH1Ci8_W2edumYAU5QOd4UCCyGaZo1SwPmWOKrkY52gWzKuWIcIX1MM9hcX2HS8a8zutkzz96Bt2F97Q5KUeICLqBYvIPLLVxTqGUmm6gUznwwxWTGrfALcOuwS9AxisUN70saWvTsGASSNk02tUTTzNO5lRIfgMK8rOXEkRsGptFXGvohtjuXBst5APO6DU6KuamHpbxCsCY5Dc72lBkXNd1QDq78v5brEyNBcTWz4yAvGTusi-4dO1qVay8VZW_p7Oz_QDbxtLLhBD1L9t2oheO9PqgOVH6Uss652oALG6RBRe5EqfKEIx4ua4mljhxzi0rj-T4cpRWelSv57OI5mVGC-ErhsK3rXpl1sOa5BqZ87whK5klhBwkNVaTCPoqI-EeRRdckiK6prAXNftQn7pjtNDkxTbzVlj5p1Fl38OoUuhWVDHsLm2sIu4xrutXa5rfFq0OaQ1V1X7cpl4m0Oynoyp_n2m00)

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
