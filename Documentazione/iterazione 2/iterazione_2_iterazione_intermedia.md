### Iterazione 2: Visualizzazione e Ricerca Utenti, Notifiche

#### Obiettivo
Implementare la funzionalità per visualizzare e cercare utenti all'interno del sistema, oltre a inviare notifiche per la creazione di gruppi di studio.

#### 1. Requisiti funzionali
- Gli utenti possono cercare altri utenti in base a interessi comuni.
- Gli utenti ricevono notifiche quando vengono invitati a un gruppo di studio.

#### 2. Design UML
- **Diagramma delle classi**: Includere le classi `Notification`, `UserProfile`, `SearchController`, e `NotificationController`.
  
![Class Diagram](https://www.planttext.com/api/plantuml/png/dLNHJi8m57tVLpHxGWb4NuaXHYM6H968V43jBkFgQ6VROOpXtsqtb7PjNzmJxDnxUkwvPzkrqbJgPbTbMKtPEos1v3EkKIEed7ndn5oieagHXT2uGKOr2du4x3KYM4p8d6ieGJft5V02v6n7iPgGbPR8ImULmB16u3fLi0EbZ9W8OWJhHWN0a9IWvyNWB1GpD68iv0WrLNk1XT-zEacsDC41oY-9J1YbVVPcJJ7tgk86HVaVEwjInBzmpz6u0wstoU4xr5-0otF8dBoi5SYb51kim8jBO-xFev5vHH7PTzrnB97fHi9QXcbffQzRskJwZt3lyAW9DoXI3bZ8KHbjN_RrfiZ2mkIelia7vrcz5LnBKLKWUu_h2gXasnCSx605Se_Cv65z-kC9jFqMKuEF1kINe-K1bRwoqDtBqzJjsOhFxjnb64Wl7ExB9A6HEoL_MXVXpTxvRaKgoRoubwAfdy4CfdGR2ZD9vuJv4MkYZ46jNVA1Az3FLD0juGtOFxhRNIUy6e-dCJl8n6vNPS7-RRspwj-w17j-cPFpqOZa5-FnUUw__2TaHxbPiCgoQ-Eb_NZy0000)

#### 3. Implementazione
- **SearchController**: Implementare la logica di ricerca e visualizzazione degli utenti.
- **NotificationController**: Implementare il meccanismo di invio delle notifiche.

#### 4. Testing
- **JUnit**: Test per assicurare che la ricerca funzioni correttamente e che le notifiche siano inviate.
- **EclEmma**: Controllare la copertura per le nuove funzionalità.

#### 5. Analisi dinamica
- **JUnit/Eclemma**: Test di unità per le funzionalità di ricerca e notifiche.

---
