package Opencart;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class testSearch {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeAll
    public static void createReport() {
        System.out.println("<<< INICIO TEST DE BÚSQUEDA >>>");
    }

    @BeforeEach
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(10000));
        SearchPage searchPage = new SearchPage(driver, wait);
        searchPage.getUrl("https://opencart.abstracta.us/index.php?route=common/home");
        searchPage.setup();
    }

    @Test
    @Tag("BUSQUEDA")
    @Tag("EXITOSO")
    public void test_BusquedaYAniadirExitosa() throws InterruptedException {
        SearchPage searchPage = new SearchPage(driver, wait);

        searchPage.completarBusqueda("iphone");
        searchPage.clickBuscar();

        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(searchPage.getAddToCart()));
        addToCartButton.click();

        WebElement addSuccesMs = wait.until(ExpectedConditions.presenceOfElementLocated(searchPage.getAddSucces()));

        System.out.println(addSuccesMs.getText());
        String mss = addSuccesMs.getText();
        //assertTrue(mss.contains("Success: You have added iPhone to your shopping cart!"));
        assertEquals("Success: You have added iPhone to your shopping cart!\n×", mss);






    }

    @AfterEach
    public void cerrar() {
        SearchPage searchPage = new SearchPage(driver, wait);
        searchPage.close();
    }

    @AfterAll
    public static void saveReport() {
        System.out.println("<<< FINALIZAN LOS TEST DE BÚSQUEDA >>>");
    }
}