
# Documentazione sui Test End-to-End con Playwright

## Introduzione

Ho implementato test end-to-end (E2E) nel mio progetto Maven Spring Boot utilizzando Playwright, una libreria moderna per l'automazione dei browser. I test E2E sono progettati per simulare il comportamento dell'utente finale e verificare che tutte le parti dell'applicazione funzionino correttamente insieme. Questi test sono cruciali per garantire che l'applicazione risponda come previsto in scenari reali.

## Installazione di Playwright

Per iniziare a utilizzare Playwright, è importante assicurarsi di avere installato Node.js e npm (Node Package Manager) sul proprio sistema Windows. 
Playwright può essere installato come pacchetto npm, quindi bisogna eseguire il seguente comando nel terminale:

```bash
npm install playwright
```

Dopo l'installazione, ho eseguito il comando per scaricare i browser necessari per i test:

```bash
npx playwright install
```

## Integrazione con il Progetto Maven Spring Boot

Ho integrato Playwright nel mio progetto Maven Spring Boot creando una struttura di cartelle dedicata per i test. Ho posizionato gli script di test all'interno della seguente directory:

```
ums-WebApp\src\test\java\end-to-end-playwright
```

### Creazione di `package.json`

Per gestire le dipendenze e gli script di test, ho creato un file `package.json` nella cartella di test. Questo file definisce il progetto e specifica le dipendenze necessarie, così come gli script che possono essere eseguiti.

Ecco come appare il mio `package.json`:

```json
{
  "name": "ums-webapp",
  "version": "1.0.0",
  "main": "index.js",
  "scripts": {
   "test:e2e:registration": "node src/test/java/end-to-end-playwright/registration-page-test.js",
    "test:e2e:login": "node src/test/java/end-to-end-playwright/login-page-screenshot-test.js",
    "test:e2e:dashboard": "node src/test/java/end-to-end-playwright/dashboard-page-test.js"
  },
  "keywords": [],
  "author": "",
  "license": "ISC",
  "description": "",
  "dependencies": {
    "playwright": "^1.47.2"
  }
}


```

## Test Eseguiti

### 1. Test di Registrazione

#### Ho implementato un test per verificare se un utente può accedere alla pagina di registrazione e completare con successo il processo di registrazione. Lo script registration-page-test.js esegue le seguenti operazioni:
- Avvia un'istanza del browser Chromium (una versione open-source di Chrome) in modalità non headless.
- Apre una nuova pagina all'interno del browser.
- Il browser carica la pagina web all'indirizzo `http://localhost:8080/registration`.
- Dopo che l'utente si è registrato con successo, viene salvata un'immagine della pagina.
- L'immagine risultante dal test si trova nella directory: /ums/tree/main/ums-WebApp.


```
const { chromium } = require('playwright');

(async () => {
  const browser = await chromium.launch({ headless: false });
  const page = await browser.newPage();

  await page.goto('http://localhost:8080/registration');
  
  await page.fill('#firstName', 'John');
  await page.fill('#lastName', 'Rossi');
  await page.fill('#skills', 'Java');
  await page.fill('#courseOfStudy', 'Computer Science');
  await page.fill('#email', 'john@example.com');
  await page.fill('#password', '123456');

  await page.click('button[type="submit"]');
  
  await page.waitForSelector('.alert-info');

  const successMessage = await page.textContent('.alert-info');
  if (successMessage.includes("You've successfully registered")) {
    console.log('La registrazione è avvenuta con successo.');
  } else {
    console.log('La registrazione non è riuscita.');
  }

  await page.screenshot({ path: `registration-screenshot.png` });

  await browser.close();
})();

```
#### Eseguzione test:
```
npm run test:e2e:registration
```


### 2. Test di Login

#### Ho implementato un test per verificare se un utente può accedere alla pagina di login. Lo script `login-page-screenshot-test.js` esegue le seguenti operazioni:
- Avviamo un'istanza del browser Chromium (una versione open-source di Chrome) in modalità headless
- Apre una nuova pagina all'interno del browser.
- Il browser carica la pagina web che si trova all'indirizzo http://localhost:8080
- Salviamo l'immagine della pagina login con il nome screenshot-login-page-to-fill-playwright.png


```
const { chromium } = require('playwright');
(async () => {
  const browser = await chromium.launch();
  const page = await browser.newPage();
  await page.goto('http://localhost:8080'); 
  await page.screenshot({ path: `screenshot-login-page-to-fill-playwright.png` });
  await browser.close();
})();

```
#### Eseguzione test:
```
npm run test:e2e:login
```

### 3. Test della Dashboard

#### Ho creato un secondo test, `dashboard-page-test.js`, per verificare che il contenuto della pagina dashboard fosse corretto. Questo test:
- Accediamo alla pagina di login
- Inseriamo le credenziali
- Aspettiamo che il pulsante di login sia visibile prima di cliccarlo
- Il pulsante di login verrà cliccato automaticamente e verificheremo la presenza del testo specificato nella pagina Dashboard.
- Il test avrà successo se verrà salvato uno screenshot della pagina Dashboard che soddisfi la condizione menzionata.

```
const { chromium } = require('playwright');

(async () => {
  const browser = await chromium.launch({ headless: false });
  const page = await browser.newPage();
  await page.goto('http://localhost:8080/login');
  
 
  await page.fill('input[name="username"]', 'john@example.com');
  await page.fill('input[name="password"]', '123456');


  await page.waitForSelector('#login-submit');
  await page.click('#login-submit');


  const paragraphText = await page.textContent('p'); 
  if (paragraphText.includes('here you can find all your active groups.')) {
    console.log('Il testo nella pagina dashboard è presente: "here you can find all your active groups."');
  } else {
    console.log('Il testo non è presente nella pagina dashboard.');
  }

  await page.screenshot({ path: `dashboard-screenshot-test-playwright.png` });

  await browser.close();
})();

```
#### Eseguzione test:
```
npm run test:e2e:dashboard
```
---
## Esecuzione dei Test

Per eseguire i test, ho avviato prima l'applicazione Spring Boot. Una volta che l'applicazione era in esecuzione, ho eseguito il test desiderato utilizzando npm. Ad esempio, per eseguire il test della dashboard, ho utilizzato il comando:

Terminal:
```bash
npm run test:e2e:dashboard
```



