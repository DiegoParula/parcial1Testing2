package Opencart;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends BasePage {
    private By searchBox = By.xpath("//*[@id=\"search\"]/input");
    private By searchButtom = By.xpath("//*[@id=\"search\"]/span/button");
    //private By validateSearch = By.className("categoria");

    private By addToCart = By.xpath("//*[@id=\"content\"]/div[3]/div/div/div[2]/div[2]/button[1]");

    private By addSucces = By.xpath("//*[@id=\"product-search\"]/div[1]");

    public SearchPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }

    /** Completa la búsqueda con la ciudad especificada.
     * @param ciudad La ciudad a buscar.
     * @throws InterruptedException Si ocurre un error durante la espera.
     */
    public void completarBusqueda(String product) throws InterruptedException {
        this.sendText(product, searchBox);
        this.sendKey(Keys.ENTER, searchBox);
    }

    /** Hace click en el botón de búsqueda.
     * @throws InterruptedException Si ocurre un error durante la espera.
     */
    public void clickBuscar() throws InterruptedException {
        this.click(searchButtom);
    }

    public void clickAddToCart() throws InterruptedException {
        this.click(addToCart);
    }

    public String agregadoCarritoExitoso() throws InterruptedException {
        String res = this.getText(addSucces);
        System.out.println(res);
        return res;
    }

    public By getAddToCart(){
        return addToCart;
    }

    public By getAddSucces(){
        return addSucces;
    }

    ////////////////////////////////////////////////////////

    /** Obtiene el resultado de la búsqueda.
     * @return El texto del resultado de la búsqueda.
     * @throws InterruptedException Si ocurre un error durante la espera.
     *//*
    public String resultadoBusqueda() throws InterruptedException {
        System.out.println("RESULTADO DE LA BUSQUEDA: " + this.getText(validateSearch));
        return this.getText(validateSearch);
    }*/
}