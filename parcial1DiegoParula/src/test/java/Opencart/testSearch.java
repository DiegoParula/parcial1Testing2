package Opencart;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class testSearch {
    private WebDriver driver;
    private WebDriverWait wait;

    @Test
    @Tag("BUSQUEDA")
    @Tag("EXITOSO")
    public void test_BusquedaExitosa() throws InterruptedException {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        SearchPage searchPage = new SearchPage(driver, wait);
        searchPage.setup();
        searchPage.getUrl("https://digital-booking-front.digitalhouse.com/");

        searchPage.completarBusqueda("punta del este");

        searchPage.clickBuscar();

        String resultado = searchPage.resultadoBusqueda();
        assertTrue(resultado.equals("CASA DE PLAYA\nVilla Kantounes Studio Ostria"));

        searchPage.close();
    }
}