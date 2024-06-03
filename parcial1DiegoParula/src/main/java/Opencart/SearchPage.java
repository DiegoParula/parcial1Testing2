package Opencart;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends BasePage {
    private By searchBox = By.id("ciudad");
    private By searchButtom = By.id("btn-buscador");
    private By validateSearch = By.className("categoria");

    public SearchPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }

    /** Completa la búsqueda con la ciudad especificada.
     * @param ciudad La ciudad a buscar.
     * @throws InterruptedException Si ocurre un error durante la espera.
     */
    public void completarBusqueda(String ciudad) throws InterruptedException {
        this.sendText(ciudad, searchBox);
        this.sendKey(Keys.ENTER, searchBox);
    }

    /** Hace click en el botón de búsqueda.
     * @throws InterruptedException Si ocurre un error durante la espera.
     */
    public void clickBuscar() throws InterruptedException {
        this.click(searchButtom);
    }

    /** Obtiene el resultado de la búsqueda.
     * @return El texto del resultado de la búsqueda.
     * @throws InterruptedException Si ocurre un error durante la espera.
     */
    public String resultadoBusqueda() throws InterruptedException {
        System.out.println("RESULTADO DE LA BUSQUEDA: " + this.getText(validateSearch));
        return this.getText(validateSearch);
    }
}