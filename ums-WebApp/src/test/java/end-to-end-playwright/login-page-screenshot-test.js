//avviamo un'istanza del browser Chromium (una versione open-source di Chrome) in modalità headless
const { chromium } = require('playwright');

(async () => {
  const browser = await chromium.launch();
  // la riga sotto apre una nuova pagina all'interno del browser
  const page = await browser.newPage();
  //il browser carica la pagina web che si trova all'indirizzo http://localhost:8080
  await page.goto('http://localhost:8080'); 
  //salviamo l'immagine della pagina login con il nome screenshot-login-page-to-fill-playwright.png 
  await page.screenshot({ path: `screenshot-login-page-to-fill-playwright.png` });
  await browser.close();
})();
