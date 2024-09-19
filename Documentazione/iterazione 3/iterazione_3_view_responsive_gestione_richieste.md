### **Iterazione 3 - Sviluppo delle View Responsive e Gestione delle Richieste**


#### **1. Introduzione**
L'Iterazione 3 si concentra sullo sviluppo delle interfacce utente (view) dell'applicazione, rendendole dinamiche e responsive per garantire una buona esperienza utente su diversi dispositivi, inclusi smartphone, tablet e desktop. Bootstrap viene utilizzato per il design responsive, mentre Thymeleaf viene utilizzato per gestire la generazione dinamica delle pagine. In questa fase, verranno gestite anche le richieste HTTP inviate dal client al server attraverso i controller Spring Boot.

#### **2. Obiettivi**
- Creare pagine web dinamiche e responsive utilizzando Thymeleaf e Bootstrap.
- Implementare la gestione delle richieste tramite i controller per operazioni CRUD sugli studenti e altre funzionalità del sistema.
- Migliorare l'esperienza utente con una navigazione fluida e interfacce interattive.
- Ottimizzare le performance dell’applicazione lato client.

#### **3. Requisiti**
**Requisiti Funzionali:**
- Le pagine dell'applicazione devono essere accessibili da dispositivi mobili e desktop senza perdere usabilità.
- Le view devono supportare la creazione, modifica, visualizzazione e cancellazione dei dati degli studenti.
- Le richieste HTTP devono essere gestite correttamente dai controller per effettuare operazioni CRUD e altre interazioni con il database.

**Requisiti Non Funzionali:**
- Le view devono essere ottimizzate per tempi di caricamento rapidi.
- Il design dell'interfaccia deve seguire le best practices per garantire un’esperienza utente intuitiva e fluida.
- L'applicazione deve essere responsive e accessibile, conforme agli standard di web design moderni.

#### **4. Design**
**Architettura:**
- Il pattern MVC viene mantenuto, con il **controller** che gestisce la logica di business e le **view** che rappresentano l'interfaccia utente, mentre il **model** rappresenta i dati. Le pagine saranno rese dinamiche grazie a Thymeleaf, che consente di popolare i dati nelle view a runtime.
- Bootstrap è stato integrato per creare layout responsive che si adattano automaticamente alle diverse dimensioni dello schermo.
  
**Modelli UML:**
- **Class Diagram:** Aggiornato per includere i controller che gestiscono le operazioni CRUD (ad esempio, `StudentController`) e le classi di servizio coinvolte nella gestione delle view dinamiche.

#### **5. Implementazione**
Durante questa iterazione, viene implementata una serie di view per gestire le principali funzionalità dell'applicazione, utilizzando Thymeleaf per la generazione dinamica delle pagine e Bootstrap per il layout responsive.

**5.1 View Responsive con Bootstrap**
- **Layout generale:** Tutte le pagine utilizzano il framework CSS di **Bootstrap** per garantire che l'interfaccia si adatti correttamente a dispositivi di diverse dimensioni. Le griglie di Bootstrap (`container`, `row`, `col-*`) vengono utilizzate per posizionare e ridimensionare gli elementi dell'interfaccia.
  
- **Navigazione:** Implementazione di una barra di navigazione (**navbar**) che cambia stile in base al dispositivo (ad esempio, collassabile su dispositivi mobili). Include collegamenti alle principali funzionalità come elenco studenti, gestione gruppi e notifiche.

- **Formulari:** I form per l'inserimento/modifica degli studenti e per altre operazioni sono ottimizzati per essere user-friendly e facili da usare su schermi touch. Vengono utilizzati componenti Bootstrap come `form-group`, `input-group`, `buttons` per creare form dall’aspetto moderno e coerente.

- **Tabelle responsive:** Viene utilizzato **Bootstrap Table** per visualizzare elenchi di studenti e gruppi di studio. Le tabelle sono rese responsive per adattarsi a dispositivi mobili, utilizzando classi come `table-responsive`.

**5.2 Pagine Dinamiche con Thymeleaf**
- **Integrazione Thymeleaf:** Le pagine HTML sono generate dinamicamente utilizzando il motore di template **Thymeleaf**, che permette di popolare i dati provenienti dal backend direttamente nelle view. Ad esempio, la pagina dell'elenco studenti viene popolata con dati estratti dal database tramite Thymeleaf.
  
- **Template comuni:** I file HTML sono suddivisi in **template riutilizzabili**. Ad esempio, la barra di navigazione e il footer sono definiti in un unico file Thymeleaf (`fragments.html`) e inclusi in ogni pagina tramite il tag `<th:include>`.
  
- **Binding dati:** Thymeleaf consente il binding diretto dei dati tra il model e la view. Ad esempio, il form di modifica degli studenti utilizza i campi di input HTML con l'attributo `th:field` per associare gli input al model degli studenti.

**5.3 Gestione delle Richieste nei Controller**
- **StudentController:** Viene implementato il controller per gestire le richieste HTTP relative alla gestione degli studenti. I metodi sono mappati a URL specifici usando annotazioni come `@GetMapping`, `@PostMapping` e `@PutMapping` per operazioni di lettura, creazione e aggiornamento degli studenti.
  
- **Flusso delle richieste:**
  - **GET request:** Per visualizzare la lista degli studenti, il controller gestisce una richiesta HTTP GET (`/students/list`). Il metodo corrispondente recupera i dati dal database tramite il service layer e li passa alla view.
  - **POST request:** Per l'invio di dati (ad esempio, la creazione di un nuovo studente), viene gestita una richiesta HTTP POST, in cui i dati inviati tramite form HTML vengono gestiti dal controller e salvati nel database.
  - **PUT request:** L'aggiornamento di uno studente viene gestito da una richiesta PUT, in cui il controller riceve i dati modificati e aggiorna il record corrispondente nel database.

#### **6. Analisi Statica e Dinamica**
**Analisi Statica:**
- Viene utilizzato STAN4J per verificare la qualità del codice e assicurarsi che i principi di design siano rispettati. In particolare, vengono controllati la correttezza delle annotazioni nei controller e la separazione delle responsabilità tra view, controller e model.

**Analisi Dinamica:**
- **Copertura del codice:** Eclemma viene utilizzato per misurare la copertura del codice dei controller e dei metodi di servizio che gestiscono le richieste e restituiscono i dati alle view.
- **Testing di UI:** Viene eseguito un test manuale sulle view per verificare la responsività e la corretta visualizzazione sui dispositivi mobili e desktop.

#### **7. Testing**
**Unit Test:**
- Vengono eseguiti test sui metodi dei controller per verificare che le richieste HTTP vengano correttamente elaborate e che i dati passino in modo corretto dalle view ai controller e successivamente al service layer.
  
- **Test sui form:** Verifica che i dati inseriti nei form HTML vengano processati correttamente e salvati nel database.

**Integration Test:**
- Test sull’integrazione tra le view Thymeleaf e i controller, per verificare che i dati dinamici vengano popolati correttamente nelle pagine web.

