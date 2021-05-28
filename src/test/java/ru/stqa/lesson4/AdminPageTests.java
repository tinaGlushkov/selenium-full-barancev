package ru.stqa.lesson4;

import org.junit.Test;
import org.openqa.selenium.By;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AdminPageTests extends BaseTest {

    private By username = By.name("username");
    private By password = By.name("password");
    private By loginButton = By.name("login");
    private By mainLinks = By.cssSelector(".list-vertical  > li > a");
    private By innerLinks = By.cssSelector(".docs > li");

    @Test
    public void testAdminPage() {
        driver.findElement(username).sendKeys("admin");
        driver.findElement(password).sendKeys("admin");
        driver.findElement(loginButton).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='notice warnings']")));
        assertEquals("http://localhost/litecart/admin/", driver.getCurrentUrl());

        List<WebElement> pages = driver.findElements(mainLinks);
        for (int i = 0; i < pages.size(); i++) {
            pages = driver.findElements(mainLinks);
            pages.get(i).click();
            assertTrue(driver.findElement(By.cssSelector("h1")).isDisplayed());

            List<WebElement> innerPages = driver.findElements(By.cssSelector(".docs"));
            if (innerPages.size() > 0) {
                List<WebElement> links = driver.findElements(innerLinks);
                for (int j = 0; j < links.size(); j++) {
                    links = driver.findElements(innerLinks);
                    links.get(j).click();
                    assertTrue(driver.findElement(By.cssSelector("h1")).isDisplayed());
                }
            }
        }
    }
}

