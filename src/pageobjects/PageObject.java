package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageObject {

    protected WebDriver driver;

    // Constructors
    public PageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PageObject(WebDriver driver, String pageUrl){
        this.driver=driver;
        driver.get(pageUrl);
        //Initialise Elements
        PageFactory.initElements(driver, this);
    }

    //Get page title
    public String getPageTitle(){
        return driver.getTitle();
    }
}
