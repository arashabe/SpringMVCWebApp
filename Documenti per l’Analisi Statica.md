## Documento per l’Analisi Statica  
#### Analisi Statica con SonarLint

---

### 1. **Introduzione**

L'analisi statica del software è un processo fondamentale per valutare la qualità del codice sorgente senza eseguire il programma. Questo documento descrive l'uso di **SonarLint**, un plugin per IDE che fornisce feedback in tempo reale su problemi di qualità del codice, vulnerabilità di sicurezza e cattive pratiche di codifica.

---

### 2. **Obiettivi dell'Analisi Statica**

Gli obiettivi principali dell'analisi statica sono:
- Identificare vulnerabilità di sicurezza nel codice.
- Rilevare bug e anomalie prima dell'esecuzione del software.
- Assicurare che il codice rispetti le linee guida e le convenzioni di stile.
- Migliorare la manutenibilità e la leggibilità del codice.

---

### 3. **Strumenti Utilizzati**

Per eseguire l'analisi statica e fornire feedback sul codice, è stato utilizzato il seguente strumento:
- **SonarLint**: Un plugin per IDE che analizza il codice sorgente e fornisce suggerimenti per migliorarlo in tempo reale.

---

### 4. **Definizione dei Problemi Rilevati**

Di seguito vengono elencati alcuni dei principali avvisi e problematiche identificate tramite SonarLint.

#### 4.1 **Costruttore Vuoto**
- **Classe Interessata**: `LoginDto`
- **Descrizione del Problema**: Il costruttore pubblico senza argomenti è considerato una cattiva pratica poiché non fornisce alcuna funzionalità e può confondere i lettori.
- **Raccomandazione**: Aggiungere un commento esplicativo per indicare che il costruttore vuoto è necessario per la serializzazione o l'iniezione di dipendenza.

```java
public LoginDto() {
    // Costruttore vuoto necessario per la serializzazione/deserializzazione
}
```

#### 4.2 **Iniezione di Dipendenze tramite @Autowired**
- **Classe Interessata**: `SecurityConfig`
- **Descrizione del Problema**:  L'uso dell'annotazione @Autowired per l'iniezione di dipendenze tramite campi può portare a problematiche di inizializzazione anticipata dei bean, causando confusione e difficoltà nella risoluzione delle dipendenze.
- **Raccomandazione**: Utilizzare l'iniezione di dipendenze tramite parametri nei metodi dei bean per ottimizzare il caricamento del contesto e ridurre la possibilità di problemi di inizializzazione.

```java
@Configuration
public class SecurityConfig {

    private final UserService userService;

    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Implementazione della configurazione della sicurezza
    }
}

```

#### 4.3 **Uso di collect(Collectors.toList()) invece di toList()**
- **Classe Interessata**: `UserServiceImpl`
- **Descrizione del Problema**: L'uso di collect(Collectors.toList()) può essere sostituito con il metodo toList() per ottenere una lista non modificabile in modo più conciso e chiaro. Questo miglioramento aumenta la leggibilità del codice e sfrutta le funzionalità più recenti di Java.
- **Raccomandazione**: Utilizzare toList() per ottenere una lista non modificabile direttamente dallo stream.

```java
private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
    return roles.stream()
            .map(role -> new SimpleGrantedAuthority(role.getName()))
            .toList(); // Sostituito collect(Collectors.toList()) con toList()
}

```

#### 4.4 **Utilizzo di Eccezioni Generiche**
- **Classe Interessata**: `UserServiceImpl`
- **Descrizione del Problema**: Lanciare eccezioni generiche come RuntimeException non fornisce informazioni sufficienti sul tipo di errore che si è verificato. Questo può rendere più difficile il debug e la gestione degli errori, poiché non si ha una chiara comprensione del problema specifico.
- **Raccomandazione**: Creare e lanciare eccezioni personalizzate o utilizzare eccezioni più specifiche per riflettere il tipo di errore verificatosi, migliorando così la gestione degli errori nel codice.

```java
else {
    // L'utente non esiste nel database, gestire la conseguenza
    throw new UserNotFoundException("User not found"); // Utilizziamo un'eccezione personalizzata
}


```

Pssiamo definirlo in questo modo ad esempio:

```java
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}



```

---

### 5. **Risultati dell'Analisi**

L'analisi eseguita con SonarLint ha rivelato diversi problemi che potrebbero influenzare la qualità complessiva del codice. Sono stati identificati avvisi di variabilità di stile, potenziali vulnerabilità e pratiche di codifica subottimali. È importante affrontare questi problemi per migliorare la robustezza e la manutenibilità del software.

- **Rapporto**: Sono stati identificati 22 avvisi critici, 4 avvisi maggiori e 18 avvisi minori.
---

### 6. **Conclusioni**

L'analisi statica condotta con SonarLint ha fornito un'importante panoramica della qualità del codice sorgente del progetto. Gli avvisi emersi indicano aree in cui il codice può essere migliorato per aumentare la manutenibilità, la sicurezza e la conformità agli standard di codifica. L'implementazione delle raccomandazioni di SonarLint porterà a un codice più pulito e a una riduzione del rischio di bug e vulnerabilità.

---

## Next

- [Demo del Progetto](https://github.com/arashabe/ums/blob/main/images/demo.md)

