package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CartPage extends PageObject {

    @FindBy(how = How.CSS, using = "button[data-role=proceed-to-checkout]")
    private WebElement proceedToCheckoutButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CheckoutPage clickOnProceedToCheckoutButton() {
        proceedToCheckoutButton.click();
        return new CheckoutPage(driver);
    }
}
