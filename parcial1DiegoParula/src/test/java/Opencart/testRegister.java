package Opencart;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class testRegister {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeAll
    public static void createReport() {
        System.out.println("<<< INICIO TEST DE REGISTRO >>>");
    }

    @BeforeEach
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.getUrl("https://opencart.abstracta.us/index.php?route=common/home");
        registerPage.setup();
    }

    @Test
    @Tag("REGISTRO")
    @Tag("EXITOSO")
    public void RegistroExitoso() throws InterruptedException {
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.clickMyAccount();
        registerPage.clickRegister();
        registerPage.ingresarName("");
        registerPage.ingresarApellido("");
        registerPage.ingresarMail("");
        registerPage.ingresarTelephone("");
        registerPage.ingresarContrasenia("");
        registerPage.confirmarConytrasenia("");
        registerPage.clickNoNewsletter();
        registerPage.clickAgree();
        registerPage.clickSubmit();
        registerPage.cuentaCreada().equals("Congratulations! Your new account has been successfully created!");

    }

    @Test
    @Tag("REGISTRO")
    @Tag("FALLIDO")
    public void RegistroFallidoMailRepetido() throws InterruptedException {
        RegisterPage registerPage = new RegisterPage(driver, wait);

        registerPage.clickCrearCuenta();
        registerPage.escribirNombre("Sergio");
        registerPage.escribirApellido("Pace");
        registerPage.escribirCorreo("prueba00004@gmail.com");
        registerPage.escribirContraseña("123456");
        registerPage.repetirContraseña("123456");
        registerPage.clickRegistrarse();

        registerPage.mailRegistrado().equals("Ese email ya se encuentra registrado");
    }

    @Test
    @Tag("REGISTRO")
    @Tag("FALLIDO")
    public void RegistroFallidoContraseña() throws InterruptedException {
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.clickCrearCuenta();

        registerPage.escribirNombre("Sergio");
        registerPage.escribirApellido("Pace");
        registerPage.escribirCorreo("prueba00004@gmail.com");
        registerPage.escribirContraseña("123456321");
        registerPage.repetirContraseña("123456123");

        registerPage.clickRegistrarse();

        registerPage.contraseñaNoCoinciden().equals("Las contraseñas deben ser iguales");
    }

    @AfterEach
    public void cerrar() {
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.close();
    }

    @AfterAll
    public static void saveReport() {
        System.out.println("<<< FINALIZAN LOS TEST DE REGISTRO >>>");
    }
}
