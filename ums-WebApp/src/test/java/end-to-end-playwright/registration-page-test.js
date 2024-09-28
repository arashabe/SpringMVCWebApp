const { chromium } = require('playwright');

(async () => {
  // Lanciamo il browser in modalità non headless per visualizzare il processo
  const browser = await chromium.launch({ headless: false });
  const page = await browser.newPage();

  // Accediamo alla pagina di registrazione
  await page.goto('http://localhost:8080/registration');
  
  // Inseriamo i dati nel form di registrazione
  await page.fill('#firstName', 'John');
  await page.fill('#lastName', 'Rossi');
  await page.fill('#skills', 'Java');
  await page.fill('#courseOfStudy', 'Computer Science');
  await page.fill('#email', 'john@example.com');
  await page.fill('#password', '123456');

  // Clicchiamo sul pulsante di registrazione
  await page.click('button[type="submit"]');
  
  // Aspettiamo che il messaggio di successo appaia
  await page.waitForSelector('.alert-info');
  
  // Verifichiamo la presenza del messaggio di conferma della registrazione
  const successMessage = await page.textContent('.alert-info');
  if (successMessage.includes("You've successfully registered")) {
    console.log('La registrazione è avvenuta con successo.');
  } else {
    console.log('La registrazione non è riuscita.');
  }

  // Facciamo uno screenshot della pagina di conferma
  await page.screenshot({ path: `registration-screenshot.png` });

  // Chiudiamo il browser
  await browser.close();
})();
