
import config.ServerConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class FirstTest {
    private org.apache.logging.log4j.Logger logger = LogManager.getLogger(Logger.class);
    protected static WebDriver driver;
    private ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

    //Структура подключения
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер поднят");

    }

    @After
    public void setDown(){
        if(driver!=null)
            driver.quit();
    }


    @Test
    public void firstTest(){
        driver.get("https://ya.ru");
        driver.findElement(By.cssSelector("#text")).sendKeys("Wiki");
        driver.findElement(By.xpath("//button[@type='submit']")).click();

    }
}
