import config.ServerConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;



public class Logger {

    private org.apache.logging.log4j.Logger logger = LogManager.getLogger(Logger.class);
    protected static WebDriver driver;
    private ServerConfig cfg = ConfigFactory.create(ServerConfig.class);

//Структура подключения
    @Before
    public void StartUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер поднят");

    }

    @After
    public void End(){
        if(driver!=null)
            driver.quit();
    }
    @Test
    public void LogExample(){
        logger.info("Тест старт");
        logger.fatal("FATAL");
    }

    @Test
    public void WebDriverTest(){
        driver.get(cfg.url());
        logger.info("Сайт открыт");
        String actual = driver.getTitle();
        Assert.assertEquals("Онлайн‑курсы для профессионалов, дистанционное обучение современным профессиям", actual);
    }

}
