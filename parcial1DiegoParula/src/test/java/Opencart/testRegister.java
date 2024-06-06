package Opencart;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import reportes.ReportFactory;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class testRegister {
    private WebDriver driver;
    private WebDriverWait wait;
    static ExtentSparkReporter info = new ExtentSparkReporter("reportes/Register-Test.html");
    static ExtentReports extent;

    @BeforeAll
    public static void createReport() {
        extent = ReportFactory.getInstance();
        extent.attachReporter(info);
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
    @Order(1)
    @Tag("REGISTRO")
    @Tag("EXITOSO")
    public void RegistroExitoso() throws InterruptedException {
        ExtentTest test = extent.createTest("Registro Exitoso");
        test.log(Status.INFO, "Comienza el Test");
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.clickMyAccount();
        test.log(Status.PASS, "Presiono el botón My Account");
        registerPage.clickRegister();
        test.log(Status.PASS, "Presiono el botón Register");
        registerPage.ingresarName("Reeceal");
        registerPage.ingresarApellido("Kaufman");
        registerPage.ingresarMail("praesent2112.eu.dui@aol.couk");
        registerPage.ingresarTelephone("(238) 996-2450");
        registerPage.ingresarContrasenia("1234");
        registerPage.confirmarConytrasenia("1234");
        registerPage.clickNoNewsletter();
        registerPage.clickAgree();
        registerPage.clickSubmit();
        test.log(Status.PASS, "Ingreso los datos para el registro y validaciones");
        //registerPage.cuentaCreada().equals("Congratulations! Your new account has been successfully created!");
        String mensajeExito = registerPage.cuentaCreada();
        String expectedMessage = "Congratulations! Your new account has been successfully created!";
        try{       assertEquals(expectedMessage, mensajeExito);
            test.log(Status.PASS, "Cuenta creada con exito");
        }catch (AssertionError e){
            test.log(Status.FAIL, "Error en la validación del registro: esperado: '" + expectedMessage + "' pero fue: '" + mensajeExito + "'");
            throw e;
        }finally {
            test.log(Status.INFO, "Finaliza el test");
        }

    }

    @Test
    @Order(2)
    @Tag("REGISTRO")
    @Tag("FALLIDO")
    public void RegistroFallidoMailRepetido() throws InterruptedException {
        ExtentTest test = extent.createTest("Registro Fallido Mail Repetido");
        test.log(Status.INFO, "Comienza el Test");
        String mss = null;
        String expectedMessage = "Warning: E-Mail Address is already registered!";
        try {


        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.clickMyAccount();
        test.log(Status.PASS, "Presiono el botón My Account");
        registerPage.clickRegister();
        test.log(Status.PASS, "Presiono el botón Register");
        registerPage.ingresarName("Reece");
        registerPage.ingresarApellido("Kaufman");
        registerPage.ingresarMail("praesent23.eu.dui@aol.couk");
        test.log(Status.PASS, "Ingresó mail repetido");
        registerPage.ingresarTelephone("(238) 996-2450");
        registerPage.ingresarContrasenia("1234");
        registerPage.confirmarConytrasenia("1234");
        test.log(Status.PASS, "Ingresó los datos del registro");
        registerPage.clickNoNewsletter();
        registerPage.clickAgree();
        registerPage.clickSubmit();
        test.log(Status.PASS, "Completó el registro y aceptó los términos");
        mss = registerPage.mailRegistrado();

        assertEquals(expectedMessage, mss);
        test.log(Status.PASS, "Validacion de mail repetido exitosa");
        //registerPage.mailRegistrado().equals("Warning: E-Mail Address is already registered!");
        }catch (AssertionError e){
            test.log(Status.FAIL, "Error en la validación del mail repetido: mensajes esperado: '" + expectedMessage + "' pero fue: '" + mss + "'");
            throw e;
        }catch (Exception e) {
            test.log(Status.FAIL, "Ocurrió un error: " + e.getMessage());
            throw e;

        } finally {
            test.log(Status.INFO, "Finaliza el Test");
        }
    }

    @Test
    @Order(3)
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
    @Order(5)
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
    @Order(4)
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
    @Order(6)
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
    @Order(7)
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

        extent.flush();
        System.out.println("<<< FINALIZAN LOS TEST DE REGISTRO >>>");
    }
}
