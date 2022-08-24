import config.ServerConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.apache.logging.log4j.LogManager;
import java.util.Objects;


public class WebDriverHomeWork {
    private org.openqa.selenium.WebDriver driver;
    private ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    private String login = "payix78520@agrolivana.com";
    private String password = "Qwe908976!";
    private org.apache.logging.log4j.Logger logger = LogManager.getLogger(Logger.class);

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        logger.info("Драйвер поднят");



    }


    @After
    public void setDow() {
        if (driver != null)
            driver.quit();
    }

    @Test
    public void duckDuckGoTest(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        driver = new ChromeDriver(options);
        driver.get("https://duckduckgo.com/");
        enterTextArea(driver.findElement(By.xpath("//input[@id='search_form_input_homepage']")),"ОТУС" );
        driver.findElement(By.xpath("//input[@id='search_button_homepage']")).click();
        checkTextTextArea(driver.findElement(By.xpath("//article[@id='r1-0']//h2/a/span")),"Онлайн‑курсы для профессионалов, дистанционное обучение современным ..." );

    }
    @Test
    public void w3LayoutsTest(){

        driver.manage().window().fullscreen();
        driver.get("https://demo.w3layouts.com/demos_new/template_demo/03-10-2020/photoflash-liberty-demo_Free/685659620/web/index.html?_ga=2.181802926.889871791.1632394818-2083132868.1632394818");
        driver.findElement(By.xpath("//span[@class='image-block']/a")).click();
        WebElement modal = driver.findElement(By.xpath("//div[@class='pp_pic_holder light_rounded']"));
        Assert.assertTrue(modal.isDisplayed());

    }

    @Test
    public void otusTest(){
        driver.get("https://otus.ru/");
        driver.manage().window().maximize();
        auth();
        String  cookiesGet = String.valueOf(driver.manage().getCookies());
        logger.info(cookiesGet);

    }



    private void enterTextArea(WebElement element, String text){
        element.clear();
        element.sendKeys(text);
    }

    private void checkTextTextArea(WebElement element, String expectedText){
        Assert.assertEquals(expectedText, element.getText());
    }

    private void auth(){
        driver.findElement(By.cssSelector("button.header2__auth")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(By.xpath("//form[@action = '/login/']//input[@name='email']")).sendKeys(login);
        driver.findElement(By.xpath("//form[@action = '/login/']//input[@name='password']")).sendKeys(password);
        driver.findElement(By.xpath("//form[@action = '/login/']//button[@type='submit']")).click();
    }
}
