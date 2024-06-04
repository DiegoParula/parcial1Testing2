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

        registerPage.mailRegistrado().equals("Warning: E-Mail Address is already registered!");
    }

    @Test
    @Tag("REGISTRO")
    @Tag("FALLIDO")
    public void RegistroFallidoContraseña() throws InterruptedException {
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

        registerPage.contraseiaNoCoinciden().equals("Password confirmation does not match password!");
    }

    @Test
    @Tag("REGISTRO")
    @Tag("FALLIDO")
    public void RegistroSinLastName() throws InterruptedException {
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

        registerPage.lastNameVacio().equals("Last Name must be between 1 and 32 characters!");
    }

    @Test
    @Tag("REGISTRO")
    @Tag("FALLIDO")
    public void RegistroSinName() throws InterruptedException {
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

        registerPage.nameVacio().equals("First Name must be between 1 and 32 characters!");
    }

    @Test
    @Tag("REGISTRO")
    @Tag("FALLIDO")
    public void RegistroSinMail() throws InterruptedException {
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

        registerPage.mailVacio().equals("E-Mail Address does not appear to be valid!");
    }

    @Test
    @Tag("REGISTRO")
    @Tag("FALLIDO")
    public void RegistroSinTelephone() throws InterruptedException {
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

        registerPage.telephoneVacio().equals("Telephone must be between 3 and 32 characters!");
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
