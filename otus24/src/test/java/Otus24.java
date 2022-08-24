import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import config.ServerConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class Otus24 {



    protected static WebDriver driver;
    private org.apache.logging.log4j.Logger logger = LogManager.getLogger(Logger.class);


    @Before
    public void StartUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер поднят");

    }


    @Test
    public void checkMessage() throws InterruptedException {
        By button = By.xpath("//button[contains(text(),'Change message')]");
        By message = By.xpath("//*[contains(text(),'Message successfully changed.')]");



        driver.get("https://ng-bootstrap.github.io/#/components/alert/examples");


        JavascriptExecutor je = (JavascriptExecutor)driver;
        je.executeScript("attributes[0].scrollIntoView()", getElement(button));
        je.executeScript("attributes[0].click()", getElement(button));
//
////        getElement(button).click();
        String messageBefore = getElement(message).getText();
//
        Thread.sleep(1500);
        getElement(button).click();
        String messageAfter= getElement(message).getText();


        Assert.assertNotEquals(messageBefore,messageAfter, "Messages are equals");

    }

    private WebElement getElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    @After
    public void End() {
        if (driver != null)
            driver.quit();
    }

}
