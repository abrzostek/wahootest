package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WahooTests {
    private WebDriver driver;

    @BeforeEach
    public void startBrowser() {
        System.setProperty("webdriver.chrome.driver", "bin/chromedriver");
        this.driver = new ChromeDriver();
    }

    @Test
    public void checkoutTest() {
        HomePage homePage = new HomePage(driver);
        //Check that homepage was opened
        assertEquals("Indoor Bike Trainers, GPS Bike Computers, Cycling Sensors & Heart Rate Monitors | Wahoo Fitness EU", homePage.getPageTitle());

        //accept Cookies
        homePage.acceptCookies();

        //Open product category
        ProductList productList = homePage.clickOnShopLink();

        //Check that product list page was opened
        assertEquals("Cycling Trainers, Running, and Fitness Accessories | Wahoo Fitness EU", productList.getPageTitle());

        ProductPage productPage = productList.clickOnRandomAvailableProduct();
        productPage.addToCart();

        // wait until sidebar cart is visible
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(productPage.getSidebarCart()));

        // click on "view and edit cart" link
        CartPage cartPage = productPage.clickOnViewCartLink();

        // TODO: tests to remove items from cart, change quantities

        // proceed to checkout
        CheckoutPage checkoutPage = cartPage.clickOnProceedToCheckoutButton();
        wait.until(ExpectedConditions.elementToBeClickable(checkoutPage.getEmailField()));

        // checkout with empty fields should display errors
        checkoutPage.clickOnPlaceOrderButton();
        wait.until(ExpectedConditions.visibilityOf(checkoutPage.getEmailFieldError()));

        // fill the forms with data, click Place Order button and assert that error message is shown
        checkoutPage.fillFormWithData();
        checkoutPage.clickOnPlaceOrderButton();
        wait.until(ExpectedConditions.visibilityOf(checkoutPage.getCCErrorMessage()));
        assertEquals("Your card was declined. Your request was in live mode, but used a known test card.", checkoutPage.getCCErrorMessageText());
    }

    @AfterEach
    public void tearDown() {
        this.driver.close();
    }
}