package ru.stqa.lesson4;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {

    private static final String BASE_URL = "http://localhost/litecart/admin/";
    public static WebDriver driver;
    public static WebDriverWait wait;

    @Before
    public void setUp() {
        if(driver!=null){
            return;
        }
        driver = new ChromeDriver();
        driver.get(BASE_URL);
        wait = new WebDriverWait(driver, 30);

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {driver.quit(); driver = null;})
        );
    }

    @After
    public void tearDown() {
       // driver.quit();
       // driver = null;
    }
}
