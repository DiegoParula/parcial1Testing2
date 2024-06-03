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


    private By exito = By.className("txt-exito");
    private By mailRegister = By.className("form-feedback");
    private By passwordDis = By.className("small-feedback");

    /**Constructor de la clase RegisterPage
     * @param driver la instancia de WebDriver utilizada para interactuar con la página web
     */
    public RegisterPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }

    /** Hace click en el botón "Crear Cuenta".
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void clickMyAccount() throws InterruptedException {
        this.click(btnMyAccount);
    }

    public void clickRegister() throws InterruptedException {
        this.click(btnRegister);
    }

    /** Ingresa el nombre proporcionado en el campo de nombre.
     * @param name el nombre a ingresar en el campo
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void ingresarName(String name) throws InterruptedException {
        this.sendText(name, firstName);
    }

    /** Ingresa el apellido proporcionado en el campo de apellido.
     * @param name el apellido a ingresar en el campo
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void ingresarApellido(String lastname) throws InterruptedException {
        this.sendText(lastname, lastName);
    }

    /** Ingresa el correo electrónico proporcionado en el campo de correo electrónico.
     * @param mail el correo electrónico a ingresar en el campo
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void ingresarMail(String mail) throws InterruptedException {
        this.sendText(mail, email);
    }

    public void ingresarTelephone(String telephoneNumber) throws InterruptedException {
        this.sendText(telephoneNumber, telephone);
    }

    /** Ingresa la contraseña proporcionada en el campo de contraseña.
     * @param pass la contraseña a ingresar en el campo
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void ingresarContrasenia(String pass) throws InterruptedException {
        this.sendText(pass, password);
    }

    /** Reingresa la contraseña proporcionada en el campo de confirmación de contraseña.
     * @param pass la contraseña a reingresar en el campo
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void confirmarConytrasenia(String pass) throws InterruptedException {
        this.sendText(pass, confirmPass);
    }

    /** Hace click en el botón "Registrarse".
     *
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public void clickNoNewsletter() throws InterruptedException {
        this.click(btnInputNoNewsletter);
    }

    public void clickAgree() throws InterruptedException {
        this.click(btnAgree);
    }

    public void clickSubmit() throws InterruptedException {
        this.click(btnSubmit);
    }

    /** Obtiene el texto del mensaje de "Gracias" indicando la creación de la cuenta.
     * @return el texto del mensaje de "Gracias"
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public String cuentaCreada() throws InterruptedException {
        String res = this.getText(account);
        System.out.println("Resultado Card value: " + res);
        return res;
    }

    /** Obtiene el texto del mensaje de éxito indicando la creación de la cuenta.
     * @return el texto del mensaje de éxito
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public String cuentaCreadaExito() throws InterruptedException {
        String res = this.getText(exito);
        System.out.println("Resultado Card value: " + res);
        return res;
    }
    //////////////////////////////////////////////////
    ///////////Registro Fallido//////////////////////
    /** Obtiene el texto del mensaje indicando que el correo ya está registrado.
     * @return el texto del mensaje de correo registrado
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public String mailRegistrado() throws InterruptedException {
        String res = this.getText(mailRegister);
        System.out.println("Resultado Card value: " + res);
        return res;
    }

    /** Obtiene el texto del mensaje indicando que las contraseñas no coinciden.
     * @return el texto del mensaje de contraseñas no coincidentes
     * @throws InterruptedException si el hilo es interrumpido mientras espera
     */
    public String contraseñaNoCoinciden() throws InterruptedException {
        String res = this.getText(passwordDis);
        System.out.println("Resultado Card value: " + res);
        return res;
    }
}
