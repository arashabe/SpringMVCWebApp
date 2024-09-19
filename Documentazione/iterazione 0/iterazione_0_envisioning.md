# Envisioning

## Obiettivo
Definire i requisiti iniziali del progetto, comprendere l'architettura di base e creare un piano di sviluppo per le iterazioni future. 
Questa fase si concentra sulla raccolta dei primi requisiti e sulla configurazione iniziale del sistema.

### 1. Documento di analisi dei requisiti

#### Requisiti funzionali:
- Gli utenti devono potersi registrare e fare login.
- Gli studenti possono cercare i profili degli altri utenti.
- Gli utenti possono creare gruppi di studio in base ai propri interessi.
- Notifiche inviate quando qualcuno richiede di creare a un gruppo.
#### Requisiti non funzionali:
-  L'applicazione deve essere sicura (utilizzo di Spring Security).
-  L'applicazione deve avere un'interfaccia utente semplice (Thymeleaf + Bootstrap).
-  La performance deve essere adeguata anche con numerosi utenti (DB H2 ottimizzato).
### 2. Toolchain
- Design: StarUML per la modellazione UML.
- Sviluppo: Spring Tool Suite (STS) con Maven per la gestione delle dipendenze.
-  Collaborazione: GitHub per il versionamento.
-  Testing: JUnit per test di unità, EclEmma per l'analisi della copertura.
### 3. Modello architetturale iniziale (Class Diagram)
-  Creare un UML class diagram che mostri:
   Le classi Notification e User.
