package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class CheckoutPage extends PageObject {

    @FindBy(how = How.ID, using = "customer-email")
    private WebElement emailField;

    @FindBy(how = How.ID, using = "customer-email-error")
    private WebElement emailFieldError;

    @FindBy(how = How.NAME, using = "firstname")
    private WebElement firstNameField;

    @FindBy(how = How.NAME, using = "lastname")
    private WebElement lastNameField;

    @FindBy(how = How.NAME, using = "street[0]")
    private WebElement addressField;

    @FindBy(how = How.NAME, using = "city")
    private WebElement cityField;

    @FindBy(how = How.NAME, using = "country_id")
    private WebElement countryField;

    @FindBy(how = How.NAME, using = "region_id")
    private WebElement stateProvinceField;

    @FindBy(how = How.NAME, using = "postcode")
    private WebElement postCodeField;

    @FindBy(how = How.NAME, using = "telephone")
    private WebElement telephoneField;

    @FindBy(how = How.NAME, using = "cardnumber")
    private WebElement cardNumberField;

    @FindBy(how = How.NAME, using = "exp-date")
    private WebElement cardExpirationField;

    @FindBy(how = How.NAME, using = "cvc")
    private WebElement cardCVCField;

    @FindBy(how = How.CSS, using = "button.checkout[title=' Place Order']")
    private WebElement placeOrderButton;

    @FindBy(how = How.XPATH, using = "//div[@data-ui-id = 'checkout-cart-validationmessages-message-error']")
    private WebElement creditCardErrorMessage;


    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getEmailField() {
        return this.emailField;
    }

    public WebElement getEmailFieldError() {
        return this.emailFieldError;
    }

    public void clickOnPlaceOrderButton() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("*[data-role=loader]"))));
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton));
        placeOrderButton.click();
    }

    public void fillFormWithData() {
        // Fill in the form with hard-coded data. This could eventually be passed as parameters.
        emailField.sendKeys("someone@whocares.com");
        firstNameField.sendKeys("Test");
        lastNameField.sendKeys("Tester");
        addressField.sendKeys("Comandante Izarduy 67");
        cityField.sendKeys("Barcelona");

        /*Select countrySelectElement = new Select(countryField);
        countrySelectElement.selectByValue("Spain");

        Select stateProvinceSelectElement = new Select(stateProvinceField);
        stateProvinceSelectElement.selectByValue("Barcelona");*/

        postCodeField.sendKeys("08940");
        telephoneField.sendKeys("5555555555");

        WebDriverWait wait = new WebDriverWait(driver, 20);
        // wait until everything has finished loading
        wait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("*[data-role=loader]"))));

        // switch to the Stripe payment iframe so we can fill in the card fields
        WebElement paymentIframe = driver.findElement(By.xpath("//div[@class='amcheckout-wrapper']//div[@class='payment-method-content']//form//iframe"));
        driver.switchTo().frame(paymentIframe);

        cardNumberField.sendKeys("4111111111111111");
        cardExpirationField.sendKeys("08/24");
        cardCVCField.sendKeys("111");

        // switch back to main content
        driver.switchTo().defaultContent();
    }

    public WebElement getCCErrorMessage() {
        return this.creditCardErrorMessage;
    }

    public String getCCErrorMessageText() {
        return creditCardErrorMessage.getText();
    }


}
