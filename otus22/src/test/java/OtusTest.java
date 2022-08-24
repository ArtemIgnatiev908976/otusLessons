import config.ServerConfig;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class OtusTest {
    private String login = "payix78520@agrolivana.com";
    private String password = "Qwe908976!";
    private WebDriver driver;
    private ServerConfig cfg = ConfigFactory.create(ServerConfig.class);
    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

    }

    @After
    public void setDow() {
        if (driver != null)
            driver.quit();
    }
    @Test
    public void otusTest(){
        //открыть отус
        driver.get(cfg.url());

        //авторизоваться на сайте
        auth();
        //войт в личный кабинет
        enterToLK();
        //зайти в раздел о себе заполнить данными +2 контакта
        enterTextArea(driver.findElement(By.xpath("//input[@id='id_fname']")),"Артём" );
        enterTextArea(driver.findElement(By.xpath("//input[@id='id_fname_latin']")),"Artem" );
        enterTextArea(driver.findElement(By.xpath("//input[@id='id_lname']")),"Игнатьев" );
        enterTextArea(driver.findElement(By.xpath("//input[@id='id_lname_latin']")),"Ignatev" );
        enterTextArea(driver.findElement(By.xpath("//input[@id='id_blog_name']")),"АртемБлог" );
        enterTextArea(driver.findElement(By.xpath("//input[@name='date_of_birth']")),"21.09.1995" );
        //Нажать сохранить
        driver.findElement(By.xpath("//button[contains(text(),'Сохранить и продолжить')]")).submit();
        //открыть в чистом браузере
        driver.quit();
        driver = new ChromeDriver();
        driver.get(cfg.url());
        //авторизоваться на сайте
        auth();
        //войти в личный кабинет
        enterToLK();
        //проверить чт ов разделе о себе указываются правильные данные
        checkTextTextArea(driver.findElement(By.xpath("//input[@id='id_fname']")),"Артём" );
        checkTextTextArea(driver.findElement(By.xpath("//input[@id='id_fname_latin']")),"Artem" );
        checkTextTextArea(driver.findElement(By.xpath("//input[@id='id_lname']")),"Игнатьев" );
        checkTextTextArea(driver.findElement(By.xpath("//input[@id='id_lname_latin']")),"Ignatev" );
        checkTextTextArea(driver.findElement(By.xpath("//input[@id='id_blog_name']")),"АртемБлог" );
        checkTextTextArea(driver.findElement(By.xpath("//input[@name='date_of_birth']")),"21.09.1995" );

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
    private void enterToLK(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.get("https://otus.ru/lk/biography/personal/");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void enterTextArea(WebElement element, String text){
        element.clear();
        element.sendKeys(text);
    }


    private void checkTextTextArea(WebElement element, String expectedText){
        Assert.assertEquals(expectedText, element.getAttribute("value"));
    }
}
