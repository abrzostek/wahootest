package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.List;

public class ProductList extends PageObject {

    private static String PAGE_URL = "https://eu.wahoofitness.com/devices";

    @FindBy(how = How.CSS, using = "ul.products-grid li.item")
    private List<WebElement> productList;

    public ProductList(WebDriver driver) {
        super(driver, PAGE_URL);
    }

    public ProductPage clickOnRandomAvailableProduct() {
        List<WebElement> availableProducts = new ArrayList<WebElement>();

        for(WebElement item : this.productList) {
            WebElement actionsElement;
            if (item.findElements(By.cssSelector("div.text div.actions form")).size() > 0
                || item.findElements(By.cssSelector("div.text div.actions a[title='View Details']")).size() > 0) {
                // product has "add to cart" link or "view details" link
                availableProducts.add(item);
            }
        }

        int randomIndex = (int)Math.floor(Math.random() * (availableProducts.size() - 1));
        WebElement randomProduct = availableProducts.get(randomIndex);

        randomProduct.findElement(By.cssSelector("div.amlabel-div a")).click();

        return new ProductPage(driver);
    }
}
