package Opencart;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        registerPage.ingresarName("Reeceal");
        registerPage.ingresarApellido("Kaufman");
        registerPage.ingresarMail("praesent22.eu.dui@aol.couk");
        registerPage.ingresarTelephone("(238) 996-2450");
        registerPage.ingresarContrasenia("1234");
        registerPage.confirmarConytrasenia("1234");
        registerPage.clickNoNewsletter();
        registerPage.clickAgree();
        registerPage.clickSubmit();
        //registerPage.cuentaCreada().equals("Congratulations! Your new account has been successfully created!");
        String mensajeExito = registerPage.cuentaCreada();
        assertEquals("Congratulations! Your new account has been successfully created!", mensajeExito);

    }

    @Test
    @Tag("REGISTRO")
    @Tag("FALLIDO")
    public void RegistroFallidoMailRepetido() throws InterruptedException {
        RegisterPage registerPage = new RegisterPage(driver, wait);

        registerPage.clickMyAccount();
        registerPage.clickRegister();
        registerPage.ingresarName("Reece");
        registerPage.ingresarApellido("Kaufman");
        registerPage.ingresarMail("praesent.eu.dui@aol.couk");
        registerPage.ingresarTelephone("(238) 996-2450");
        registerPage.ingresarContrasenia("1234");
        registerPage.confirmarConytrasenia("1234");
        registerPage.clickNoNewsletter();
        registerPage.clickAgree();
        registerPage.clickSubmit();

        String mss = registerPage.mailRegistrado();
        assertEquals("Warning: E-Mail Address is already registered!", mss);
        //registerPage.mailRegistrado().equals("Warning: E-Mail Address is already registered!");
    }

    @Test
    @Tag("REGISTRO")
    @Tag("FALLIDO")
    public void RegistroFallidoContraseña() throws InterruptedException {
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.clickMyAccount();
        registerPage.clickRegister();
        registerPage.ingresarName("Seth");
        registerPage.ingresarApellido("Simmons");
        registerPage.ingresarMail("tellus.lorem.eu@yahoo.org");
        registerPage.ingresarTelephone("(643) 561-8278");
        registerPage.ingresarContrasenia("1234");
        registerPage.confirmarConytrasenia("12345");
        registerPage.clickNoNewsletter();
        registerPage.clickAgree();
        registerPage.clickSubmit();

        String mss = registerPage.contraseiaNoCoinciden();
        assertEquals("Password confirmation does not match password!", mss);
        //registerPage.contraseiaNoCoinciden().equals("Password confirmation does not match password!");
    }

    @Test
    @Tag("REGISTRO")
    @Tag("FALLIDO")
    public void RegistroSinLastName() throws InterruptedException {
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.clickMyAccount();
        registerPage.clickRegister();
        registerPage.ingresarName("Seth");
        registerPage.ingresarApellido("");
        registerPage.ingresarMail("tellus.lorem.eu@yahoo.org");
        registerPage.ingresarTelephone("(643) 561-8278");
        registerPage.ingresarContrasenia("1234");
        registerPage.confirmarConytrasenia("12345");
        registerPage.clickNoNewsletter();
        registerPage.clickNoNewsletter();
        registerPage.clickAgree();
        registerPage.clickSubmit();
        String mss = registerPage.lastNameVacio();
        assertEquals("Last Name must be between 1 and 32 characters!", mss);
        //registerPage.lastNameVacio().equals("Last Name must be between 1 and 32 characters!");
    }

    @Test
    @Tag("REGISTRO")
    @Tag("FALLIDO")
    public void RegistroSinName() throws InterruptedException {
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.clickMyAccount();
        registerPage.clickRegister();
        registerPage.ingresarName("");
        registerPage.ingresarApellido("Simmons");
        registerPage.ingresarMail("tellus.lorem.eu@yahoo.org");
        registerPage.ingresarTelephone("(643) 561-8278");
        registerPage.ingresarContrasenia("1234");
        registerPage.confirmarConytrasenia("12345");
        registerPage.clickNoNewsletter();
        registerPage.clickNoNewsletter();
        registerPage.clickAgree();
        registerPage.clickSubmit();
        String mss = registerPage.nameVacio();
        assertEquals("First Name must be between 1 and 32 characters!", mss);
        //registerPage.nameVacio().equals("First Name must be between 1 and 32 characters!");
    }

    @Test
    @Tag("REGISTRO")
    @Tag("FALLIDO")
    public void RegistroSinMail() throws InterruptedException {
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.clickMyAccount();
        registerPage.clickRegister();
        registerPage.ingresarName("Seth");
        registerPage.ingresarApellido("Simmons");
        registerPage.ingresarMail("");
        registerPage.ingresarTelephone("(643) 561-8278");
        registerPage.ingresarContrasenia("1234");
        registerPage.confirmarConytrasenia("12345");
        registerPage.clickNoNewsletter();
        registerPage.clickNoNewsletter();
        registerPage.clickAgree();
        registerPage.clickSubmit();
        String mss = registerPage.mailVacio();
        assertEquals("E-Mail Address does not appear to be valid!", mss);
        //registerPage.mailVacio().equals("E-Mail Address does not appear to be valid!");
    }

    @Test
    @Tag("REGISTRO")
    @Tag("FALLIDO")
    public void RegistroSinTelephone() throws InterruptedException {
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.clickMyAccount();
        registerPage.clickRegister();
        registerPage.ingresarName("Seth");
        registerPage.ingresarApellido("Simmons");
        registerPage.ingresarMail("tellus.lorem.eu@yahoo.org");
        registerPage.ingresarTelephone("");
        registerPage.ingresarContrasenia("1234");
        registerPage.confirmarConytrasenia("12345");
        registerPage.clickNoNewsletter();
        registerPage.clickNoNewsletter();
        registerPage.clickAgree();
        registerPage.clickSubmit();
        String mss = registerPage.telephoneVacio();
        assertEquals("Telephone must be between 3 and 32 characters!", mss);
        //registerPage.telephoneVacio().equals("Telephone must be between 3 and 32 characters!");
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
