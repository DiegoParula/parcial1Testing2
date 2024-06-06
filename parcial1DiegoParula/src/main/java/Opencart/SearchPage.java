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

    private By addSucces = By.cssSelector(".alert.alert-success.alert-dismissible");

    //private By addSucces = By.className("alert alert-success alert-dismissible");
    public SearchPage(WebDriver driver, WebDriverWait wait) {
        super(driver, null);
    }


    public void completarBusqueda(String product) throws InterruptedException {
        this.sendText(product, searchBox);
        this.sendKey(Keys.ENTER, searchBox);
    }


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


}