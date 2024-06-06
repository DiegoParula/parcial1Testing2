package Opencart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage extends BasePage{
    private By firstName = By.id("input-firstname");
    private By lastName = By.id("input-lastname");
    private By email = By.id("input-email");
    private By telephone = By.id("input-telephone");
    private By password = By.id("input-password");
    private By confirmPass = By.id("input-confirm");
    private By btnMyAccount = By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a");//*[@id="root"]/main/div/form/button
    private By btnInputNoNewsletter = By.xpath("//*[@id=\"content\"]/form/fieldset[3]/div/div/label[2]/input");
    private By btnRegister = By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li[1]/a");
    private By btnAgree = By.xpath("//*[@id=\"content\"]/form/div/div/input[1]");
    private By btnSubmit = By.xpath("//*[@id=\"content\"]/form/div/div/input[2]");
    private By account = By.xpath("//*[@id=\"content\"]/p[1]");
    private By errorDiv = By.xpath("//*[@id=\"account-register\"]/div[1]");
    private By errorDivPass = By.xpath("//*[@id=\"content\"]/form/fieldset[2]/div[2]/div/div");

    private By errorDivLasName = By.xpath("//*[@id=\"account\"]/div[3]/div/div");

    private By errorDivName = By.xpath("//*[@id=\"account\"]/div[2]/div/div");

    private By errorDivMail = By.xpath("//*[@id=\"account\"]/div[4]/div/div");

    private By errorDivTelephone = By.xpath("//*[@id=\"account\"]/div[5]/div/div");

    private By exito = By.className("txt-exito");
    private By mailRegister = By.className("form-feedback");
    private By passwordDis = By.className("small-feedback");


    public RegisterPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }


    public void clickMyAccount() throws InterruptedException {
        this.click(btnMyAccount);
    }

    public void clickRegister() throws InterruptedException {
        this.click(btnRegister);
    }


    public void ingresarName(String name) throws InterruptedException {
        this.sendText(name, firstName);
    }


    public void ingresarApellido(String lastname) throws InterruptedException {
        this.sendText(lastname, lastName);
    }


    public void ingresarMail(String mail) throws InterruptedException {
        this.sendText(mail, email);
    }

    public void ingresarTelephone(String telephoneNumber) throws InterruptedException {
        this.sendText(telephoneNumber, telephone);
    }


    public void ingresarContrasenia(String pass) throws InterruptedException {
        this.sendText(pass, password);
    }


    public void confirmarConytrasenia(String pass) throws InterruptedException {
        this.sendText(pass, confirmPass);
    }


    public void clickNoNewsletter() throws InterruptedException {
        this.click(btnInputNoNewsletter);
    }

    public void clickAgree() throws InterruptedException {
        this.click(btnAgree);
    }

    public void clickSubmit() throws InterruptedException {
        this.click(btnSubmit);
    }


    public String cuentaCreada() throws InterruptedException {
        String res = this.getText(account);
        System.out.println("Resultado: " + res);
        return res;
    }


    public String cuentaCreadaExito() throws InterruptedException {
        String res = this.getText(exito);
        System.out.println("Resultado Card value: " + res);
        return res;
    }
    //////////////////////////////////////////////////
    ///////////Registro Fallido//////////////////////

    public String mailRegistrado() throws InterruptedException {
        String res = this.getText(errorDiv);
        System.out.println("Resultado: " + res);
        return res;
    }


    public String contraseiaNoCoinciden() throws InterruptedException {
        String res = this.getText(errorDivPass);
        System.out.println("Resultado: " + res);
        return res;
    }

    public String lastNameVacio() throws InterruptedException {
        String res = this.getText(errorDivLasName);
        System.out.println(res);
        return res;
    }

    public String nameVacio() throws InterruptedException {
        String res = this.getText(errorDivName);
        System.out.println(res);
        return res;
    }

    public String mailVacio() throws InterruptedException {
        String res = this.getText(errorDivMail);
        System.out.println(res);
        return res;
    }

    public String telephoneVacio() throws InterruptedException {
        String res= this.getText(errorDivTelephone);
        System.out.println(res);
        return res;
    }
}
