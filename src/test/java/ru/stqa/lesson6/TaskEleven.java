package ru.stqa.lesson6;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.lesson4.BaseTest;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class TaskEleven extends BaseTest {

    private static final String SITE_URL = "http://localhost/litecart/en/";
    private By createAccountButton = By.cssSelector("#box-account-login a");
    private static String login;
    private String password = "123456";

    @Test
    public void test1() {

        driver.get(SITE_URL);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(createAccountButton).click();
        driver.findElement(By.cssSelector("input[name=firstname]")).sendKeys("Kristina");
        driver.findElement(By.cssSelector("input[name=lastname]")).sendKeys("Glushkova");
        driver.findElement(By.cssSelector("input[name = address1]")).sendKeys("4300 Key str");
        driver.findElement(By.cssSelector("input[name = postcode]")).sendKeys("22222");
        driver.findElement(By.cssSelector("input[name = city]")).sendKeys("Arlington");

        WebElement selectCountry = driver.findElement(By.cssSelector("select[name=country_code]"));
        JavascriptExecutor je = (JavascriptExecutor)driver;
        je.executeScript("arguments[0].selectedIndex = 224; arguments[0].dispatchEvent(new Event('change'))", selectCountry);

        login = emailGenerator();
        driver.findElement(By.cssSelector("input[name=email]")).sendKeys(login);
        driver.findElement(By.cssSelector("input[name=phone]")).sendKeys("4256330000");
        driver.findElement(By.cssSelector("input[name=password]")).sendKeys(password);
        driver.findElement(By.cssSelector("input[name=confirmed_password]")).sendKeys(password);
        driver.findElement(By.cssSelector("button[name=create_account]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.notice")));

        //wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.notice")));
       // assertEquals(" Your customer account has been created.", driver.findElement(By.cssSelector("div.notice")).getAttribute("innerText"));

    }

    @Test
    public void test2() {
        driver.findElement(By.linkText("Logout")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.notice")));
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#box-account-login h3.title")));
        //assertEquals(" You are now logged out.", driver.findElement(By.cssSelector("div.notice")).getAttribute("innerText"));
    }

    @Test//(dependsOnMethods = "test1")
    public void test3(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name=email]")));
        driver.findElement(By.cssSelector("input[name=email]")).sendKeys(login);
        driver.findElement(By.cssSelector("input[name=password]")).sendKeys(password);
        driver.findElement(By.cssSelector("button[name=login]")).click();
        //assertEquals(" You are now logged in as Kristina Glushkova.", driver.findElement(By.cssSelector("div.notice")).getAttribute("innerText"));

        driver.findElement(By.cssSelector("#box-account li:last-child")).click();
       // wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#box-account-login h3.title")));
       // assertEquals(" You are now logged out.", driver.findElement(By.cssSelector("div.notice")).getAttribute("innerText"));

    }




    public String emailGenerator() {
        String email = "";
            String candidateChars = "abcdefgmzop";
            StringBuilder sb = new StringBuilder();
            Random random = new Random();
            for (int i = 0; i < 6; i++) {
                email = sb.append(candidateChars.charAt(random.nextInt(candidateChars
                        .length()))).toString();
            }
        return email + "@email.com";
    }

}
