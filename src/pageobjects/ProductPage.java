package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ProductPage extends PageObject {

    @FindBy(how = How.ID, using = "product-options-wrapper")
    private List<WebElement> productOptionsElement;

    @FindBy(how = How.ID, using = "product-addtocart-button")
    private WebElement addToCartButton;

    @FindBy(how = How.CSS, using = "div[data-block=minicart]")
    private WebElement sidebarCart;

    @FindBy(how = How.CSS, using = "div[data-block=minicart] div.actions a.viewcart")
    private WebElement viewCartLink;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void addToCart() {
        if (productOptionsElement.size() > 0) {
            // select first option
            WebElement selectElement = productOptionsElement.get(0).findElement(By.cssSelector("select"));
            Select optionsSelect = new Select(selectElement);
            optionsSelect.selectByIndex(1);
        }
        addToCartButton.click();
    }

    public WebElement getSidebarCart() {
        return this.sidebarCart;
    }

    public CartPage clickOnViewCartLink() {
        viewCartLink.click();
        return new CartPage(driver);
    }
}
