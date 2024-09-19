### Iterazione 2: Visualizzazione e Ricerca Utenti, Notifiche

#### Obiettivo
Implementare la funzionalità per visualizzare e cercare utenti all'interno del sistema, oltre a inviare notifiche per la creazione di gruppi di studio.

#### 1. Requisiti funzionali
- Gli utenti possono cercare altri utenti in base a interessi comuni.
- Gli utenti ricevono notifiche quando vengono invitati a un gruppo di studio.

#### 2. Design UML
- **Diagramma delle classi**: Includere le classi `Notification`, `SearchController`, e `NotificationController`.
  
![Class Diagram](https://www.planttext.com/api/plantuml/png/dLH1JiCm4Bpx5LPEeLKZk4QeGe8ALOA26dY0vIof8N62lIb2g7_79fJQYNqXfyGpEvwTNULA8rFODpKX7UDlh0AQh2GA5A0J-aMeUNZDjAQR5iMBu0n5Aty1-ooeA7EwbWWLAETKWon1hHecwfmMg8Ii75G15vq0YJ520reRCm746CPUJu0vhG3NPNesDJCtPYpa63MiVIjAlxeuMROoamkqJmbq69JTUht6b4UiS411_ViXgfZm4Hub6axW9zjeymFg3y3LEH123iuYNRSILLlNe4QxL01JV7U2lNtgDQW2r5vmO_RvzE6PiVKMq-bx3-hJSNCdD5vQwERfOUdMxDgFuTx7QLh6TNZJ8nwbG_dpkfaUZkvt6R6OPNchshxRWcbCOzeFdGn9c9TG4UCSEdJ5KzVWsjlxIRM2L-3_g3xOMKUooh9bA0wQs-bgCfc_vJkZFl8Ywib5GcUB1KtEisoM-7-cd3PCcW5hGgvCblRFzWq0)

#### 3. Implementazione
- **SearchController**: Implementare la logica di ricerca e visualizzazione degli utenti.
- **NotificationController**: Implementare il meccanismo di invio delle notifiche.

#### 4. Testing
- **JUnit**: Test per assicurare che la ricerca funzioni correttamente e che le notifiche siano inviate.
- **EclEmma**: Controllare la copertura per le nuove funzionalità.

#### 5. Analisi dinamica
- **JUnit/Eclemma**: Test di unità per le funzionalità di ricerca e notifiche.

---
