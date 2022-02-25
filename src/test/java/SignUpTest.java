import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SignUpTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void zipCode() {
        /*
        1. Открыть браузер
        2. Перейти по ссылке https://www.sharelane.com/cgi-bin/register.py
        3. Вводим 5 цифр, например 12345
        4. Нажать Continue
        5. Проверить, что кнопка Register видна
         */
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("12345");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        boolean isDisplayed = driver.findElement(By.cssSelector("[value=Register]")).isDisplayed();
        assertEquals(isDisplayed, true);
    }


    @Test
    public void zipCodeMin() {
        /*
        1. Открыть браузер
        2. Перейти по ссылке https://www.sharelane.com/cgi-bin/register.py
        3. Вводим менее пяти цифр, например 1234
        4. Нажать Continue
        5. Проверить, что видна ошибка "Oops, error on page. ZIP code should have 5 digits"
         */
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("1234");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        boolean isDisplayed = driver.findElement(By.className("error_message")).isDisplayed();
        assertEquals(isDisplayed, true);
    }


    @Test
    public void zipCodeMax() {
        /*
        1. Открыть браузер
        2. Перейти по ссылке https://www.sharelane.com/cgi-bin/register.py
        3. Вводим более пяти цифр, например 123456
        4. Нажать Continue
        5. Проверить, что видна ошибка "Oops, error on page. ZIP code should have 5 digits"
         */
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("123456");
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        boolean isDisplayed = driver.findElement(By.className("error_message")).isDisplayed();
        assertEquals(isDisplayed, true);
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }
}
