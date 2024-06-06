package Opencart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    private WebDriver driver;
    public WebDriverWait wait;


    protected BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }


    protected void setup() {
        /*// Configura las opciones de Chrome
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true); // Ignorar errores SSL
        options.addArguments("--ignore-certificate-errors"); // Ignora errores de certificado
        options.addArguments("--ignore-ssl-errors"); // Ignora errores SSL
        options.addArguments("--disable-web-security"); // Desactiva la seguridad web
        options.addArguments("--allow-running-insecure-content"); // Permite contenido inseguro
        */
        driver.manage().window().maximize();
        String pageTitle = driver.getTitle();
        // Para manejar la advertencia "Your connection is not private" dependiendo del idioma del navegador
        if (pageTitle.contains("Privacy error") ||
                pageTitle.contains("Your connection is not private") ||
                pageTitle.contains("Error de privacidad") ||
                pageTitle.contains("Tu conexión no es privada")) {
            driver.findElement(By.id("details-button")).click();
            driver.findElement(By.id("proceed-link")).click();
        }


    }


    protected void getUrl(String url) throws InterruptedException {
        driver.get(url);
        wait.until(ExpectedConditions.urlToBe(url));
    }


    protected void close() {
        driver.quit();
    }


    protected WebElement findElement(By locator) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return driver.findElement(locator);
    }


    protected void sendText(String inputText, By locator) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        this.findElement(locator).clear();
        this.findElement(locator).sendKeys(inputText);
    }


    protected void click(By locator) throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        this.findElement(locator).click();
    }


    protected void sendKey(CharSequence key, By locator) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        this.findElement(locator).sendKeys(key);
    }


    protected String getText(By locator) throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return this.findElement(locator).getText();
    }
}