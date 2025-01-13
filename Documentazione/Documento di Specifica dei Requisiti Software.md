## Documento di Specifica dei Requisiti Software

#### 1. Introduzione

##### 1.1 Scopo del documento
Questo documento descrive in dettaglio i requisiti funzionali e non funzionali del sistema software, nonché gli strumenti e le tecnologie utilizzati per lo sviluppo del progetto. L'obiettivo del sistema è quello di creare una **Web App** che consenta agli utenti di registrarsi, accedere, aggiornare il proprio profilo e interagire tramite notifiche per formare gruppi di studio in base a interessi comuni. Questa piattaforma è stata sviluppata con un’architettura basata su **Spring Framework**, un database embedded **H2** e segue il pattern **MVC (Model-View-Controller)**.

##### 1.2 Glossario
- **MVC (Model-View-Controller)**: Pattern architetturale che divide l'applicazione in tre componenti principali: Model (logica di business), View (interfaccia utente), Controller (logica di controllo).
- **DTO (Data Transfer Object)**: Oggetto usato per trasferire dati tra le varie parti del sistema.
- **H2**: Database SQL embedded utilizzato per facilitare lo sviluppo e il testing dell’applicazione.
- **Spring Security**: Modulo di Spring che fornisce funzionalità di autenticazione e autorizzazione.
- **Maven**: Strumento di build e gestione delle dipendenze utilizzato per il progetto.
- **JUnit/Eclemma**: Strumenti per il testing e la copertura del codice.

##### 1.3 Panoramica
L’applicazione si propone di facilitare la formazione di gruppi di studio online tramite una piattaforma che permette agli utenti di cercare altri utenti con interessi comuni e interagire tramite notifiche. L’architettura del sistema segue il pattern MVC e utilizza tecnologie moderne per la gestione dell'autenticazione, la sicurezza, la persistenza dei dati e l'interfaccia utente.

#### 2. Descrizione Generale

##### 2.1 Obiettivi del sistema
Gli obiettivi principali del sistema includono:
- Consentire agli utenti di registrarsi e autenticarsi.
- Gestire e aggiornare i profili degli utenti.
- Ricercare altri utenti in base agli interessi comuni.
- Inviare e ricevere notifiche per la creazione di gruppi di studio.
- Garantire la sicurezza dei dati personali tramite meccanismi di autenticazione sicura.

##### 2.2 Funzionalità di alto livello
1. **Registrazione degli utenti**: Gli utenti possono creare un account fornendo le loro credenziali e le informazioni di base.
2. **Login e autenticazione**: Gli utenti possono accedere alla piattaforma utilizzando **Spring Security**, che gestisce le sessioni e la sicurezza delle credenziali.
3. **Gestione del profilo**: Una volta autenticati, gli utenti possono aggiornare le informazioni personali, come la biografia, gli interessi e altre preferenze.
4. **Ricerca utenti**: Gli utenti possono cercare altri utenti registrati filtrando per interessi comuni, per facilitare la formazione di gruppi di studio.
5. **Notifiche**: Gli utenti possono inviare e ricevere notifiche per invitare altri utenti a far parte di gruppi di studio.
6. **Sicurezza**: Implementazione di meccanismi per la gestione sicura delle password e dei dati sensibili.

##### 2.3 Utenti del sistema
Il sistema è rivolto a:
- **Utenti registrati**: Persone che desiderano formare gruppi di studio e partecipare a discussioni basate su interessi comuni.

##### 2.4 Ambiente operativo
L'applicazione sarà sviluppata e testata in un ambiente di sviluppo locale, con un database embedded **H2**. Sarà utilizzabile attraverso un browser web su dispositivi desktop e mobili. In un futuro potrà essere estesa per essere ospitata su cloud o su un server dedicato.

#### 3. Requisiti Funzionali

##### 3.1 Requisiti Funzionali del Sistema

- **RF01 - Registrazione Utente**: L'applicazione deve permettere agli utenti di registrarsi fornendo informazioni come username, password e indirizzo email.
    - **Descrizione**: L'utente inserisce i propri dati, che vengono validati prima dell’inserimento nel database.
    - **Priorità**: Alta
    - **Attori**: Utente non autenticato
    - **Vincoli**: Il sistema deve verificare che l'email non sia già in uso.

- **RF02 - Login**: Il sistema deve permettere l'autenticazione degli utenti attraverso username e password.
    - **Descrizione**: Gli utenti devono poter accedere alla piattaforma fornendo credenziali valide.
    - **Priorità**: Alta
    - **Attori**: Utente registrato
    - **Vincoli**: Le credenziali devono essere criptate e verificate in modo sicuro.

- **RF03 - Aggiornamento Profilo Utente**: Gli utenti devono essere in grado di aggiornare le informazioni personali.
    - **Descrizione**: L'utente può modificare la propria biografia, gli interessi e altre informazioni.
    - **Priorità**: Media
    - **Attori**: Utente autenticato

- **RF04 - Ricerca Utenti**: L'utente deve essere in grado di cercare altri utenti in base a filtri specifici.
    - **Descrizione**: Il sistema permette la ricerca in base a interessi, località o altre informazioni.
    - **Priorità**: Alta
    - **Attori**: Utente autenticato

- **RF05 - Invio Notifiche**: Il sistema deve permettere agli utenti di inviare notifiche ad altri utenti per formare gruppi di studio.
    - **Descrizione**: Gli utenti possono inviare inviti a partecipare ai gruppi di studio.
    - **Priorità**: Alta
    - **Attori**: Utente autenticato

- **RF06 - Sicurezza delle Credenziali**: Il sistema deve garantire la sicurezza delle password utilizzando meccanismi di hashing.
    - **Descrizione**: Le password devono essere archiviate in modo sicuro e il sistema deve proteggere da attacchi di tipo brute-force.
    - **Priorità**: Alta
    - **Attori**: Tutti gli utenti

#### 4. Requisiti Non Funzionali

##### 4.1 Sicurezza
- Il sistema deve garantire l’integrità dei dati degli utenti e assicurare che solo utenti autenticati possano accedere alle informazioni personali.
- Utilizzo di **Spring Security** per gestire l’autenticazione e l’autorizzazione.

##### 4.2 Prestazioni
- Il sistema deve essere in grado di gestire fino a 1000 utenti simultanei in un ambiente di test.
- Il tempo di risposta alle richieste di ricerca utenti deve essere inferiore a 2 secondi.

##### 4.3 Manutenibilità
- Il codice deve essere modulare, seguendo il pattern **MVC**, per facilitare la manutenibilità e l'estensione del sistema.
- Utilizzo di **Maven** per gestire le dipendenze e facilitare il build del progetto.

##### 4.4 Usabilità
- L'interfaccia utente deve essere intuitiva e facile da utilizzare, seguendo i principi di design responsivo.
- Supporto per dispositivi mobili.

##### 4.5 Scalabilità
- Il sistema deve poter essere facilmente scalato per supportare un numero maggiore di utenti in futuro, utilizzando eventualmente un database esterno e hosting su cloud.

#### 5. Toolchain Utilizzata

##### 5.1 Tecnologie di Backend
- **Spring Framework**: Utilizzato per la logica di business e per l'implementazione del pattern MVC.
- **Spring Security**: Utilizzato per l’autenticazione e la gestione dei ruoli e dei permessi.
- **H2 Database**: Database embedded per lo sviluppo e il testing del sistema.
- **Maven**: Strumento di gestione delle dipendenze e build del progetto.

##### 5.2 Tecnologie di Frontend
- **HTML/CSS/JavaScript**: Utilizzati per creare l'interfaccia utente.
- **Thymeleaf**: Template engine per integrare il front-end con il back-end.

##### 5.3 Strumenti di Testing
- **JUnit**: Utilizzato per i test unitari del codice.
- **Eclemma**: Utilizzato per la misurazione della copertura del codice durante i test.

##### 5.4 Controllo del Versionamento
- **Git**: Utilizzato per la gestione del versionamento del codice sorgente.

#### 6. Conclusioni
Il documento di specifica dei requisiti ha descritto in dettaglio le funzionalità principali, i requisiti tecnici e gli strumenti utilizzati per lo sviluppo della web app.

--- 
## Next

- [Design dell'Architettura](../Documentazione/Documento%20di%20Design%20dell%E2%80%99Architettura%20Software.md)
