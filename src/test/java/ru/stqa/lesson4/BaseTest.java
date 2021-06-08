package ru.stqa.lesson4;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class BaseTest {

    private static final String BASE_URL = "http://localhost/litecart/admin/";
    public static WebDriver driver;
    public static WebDriverWait wait;
    private By username = By.name("username");
    private By password = By.name("password");
    private By loginButton = By.name("login");

    @Before
    public void setUp() {
        if(driver!=null){
            return;
        }
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        wait = new WebDriverWait(driver, 30);
        //login();

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {driver.quit(); driver = null;})
        );
    }

    public void login() {
        driver.findElement(username).sendKeys("admin");
        driver.findElement(password).sendKeys("admin");
        driver.findElement(loginButton).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='notice warnings']")));
        assertEquals("http://localhost/litecart/admin/", driver.getCurrentUrl());
    }

    @After
    public void tearDown() {
       // driver.quit();
       // driver = null;
    }
}
