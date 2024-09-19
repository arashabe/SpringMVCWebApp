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
#### Creare un UML deployment diagram che mostri:
- Il client web (browser) che comunica con il backend Spring Boot.
- Il database H2 embedded che interagisce con il backend.

![Deployment Diagram](https://www.planttext.com/api/plantuml/png/LP0n3u8m48Nt_egBEmkZWq5WC4v6I1mCmm4NRInjRG-DCVvtAP6OhlUzz-NbCi_eU1oKy3UfBJeSe3E3DPeqL_nI18uwHdrH98GsFS6gK7A2AtWBWFE9MiYTUNfoZVWiaN2jo3t8_MALTL9V83U6OMkjaXsoDBe1zE1d5j14_euI6Llq58jsAInWBb_JiWsTQ0WaoIRMhc5NrmSuqdqaprv4S8u7qHhsPLw8Z7GVNl85)
