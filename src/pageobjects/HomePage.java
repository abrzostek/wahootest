package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePage extends PageObject {

    private static String PAGE_URL = "https://eu.wahoofitness.com/";

    //Locators
    //Accept cookies button
    @FindBy (how = How.ID, using = "onetrust-accept-btn-handler")
    private WebElement acceptCookiesButton;

    //Shop link
    @FindBy(how = How.XPATH, using = "//nav[@id='top_nav']//a[@href='/devices']")
    private WebElement shopLink;

    public HomePage(WebDriver driver) {
        super(driver, PAGE_URL);
    }

    public void acceptCookies() {
        acceptCookiesButton.click();
    }

    public ProductList clickOnShopLink() {
        shopLink.click();
        return new ProductList(driver);
    }


}
