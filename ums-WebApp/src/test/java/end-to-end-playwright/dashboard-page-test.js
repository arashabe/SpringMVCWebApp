const { chromium } = require('playwright');

(async () => {
  // Lanciamo il browser
  const browser = await chromium.launch({ headless: false });
  const page = await browser.newPage();

  // Accediamo alla pagina di login
  await page.goto('http://localhost:8080/login');
  
  
  // Inseriamo le credenziali
  await page.fill('input[name="username"]', 'john@example.com');
  await page.fill('input[name="password"]', '123456');


  // Aspettiamo che il pulsante di login sia visibile prima di cliccarlo
  await page.waitForSelector('#login-submit');
  await page.click('#login-submit');


  // Verifichiamo la presenza del tag <p> con il testo specificato
  const paragraphText = await page.textContent('p'); 
  if (paragraphText.includes('here you can find all your active groups.')) {
    console.log('Il testo nella pagina dashboard è presente: "here you can find all your active groups."');
  } else {
    console.log('Il testo non è presente nella pagina dashboard.');
  }

  // Facciamo uno screenshot della pagina dashboard
  await page.screenshot({ path: `dashboard-screenshot-test-playwright.png` });

  // Chiudiamo il browser
  await browser.close();
})();
