package Opencart;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import reportes.ReportFactory;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static reportes.ReportFactory.captureScreenshot;

public class testSearch {
    private WebDriver driver;
    private WebDriverWait wait;
    static ExtentSparkReporter info = new ExtentSparkReporter("reportes/Search-Test.html");
    static ExtentReports extent;

    @BeforeAll
    public static void createReport() {
        extent = ReportFactory.getInstance();
        extent.attachReporter(info);
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
        ExtentTest test = extent.createTest("Busqueda y añadir a favoritos");
        test.log(Status.INFO, "Comienza el Test");

        SearchPage searchPage = new SearchPage(driver, wait);

        searchPage.completarBusqueda("iphone");
        test.log(Status.PASS, "Ingreso el producto iphone a buscar");
        searchPage.clickBuscar();

        searchPage.clickAddToCart();
        String mss = searchPage.agregadoCarritoExitoso();

        try {
            assertEquals("Success: You have added iPhone to your shopping cart!\n×", mss);
            test.log(Status.PASS, "Validación de producto agregado a carrito");
        } catch (AssertionError e) {

            test.log(Status.FAIL, "Fallo en la validación del producto agregado al carrito: " + e.getMessage());
            captureScreenshot(test, "FAIL_Busqueda", driver);
            throw e;

        }
        test.log(Status.INFO, "Finaliza el Test");

    }


    @AfterEach
    public void cerrar() {
        SearchPage searchPage = new SearchPage(driver, wait);
        searchPage.close();
    }

    @AfterAll
    public static void saveReport() {
        extent.flush();
        System.out.println("<<< FINALIZAN LOS TEST DE BÚSQUEDA >>>");
    }
}