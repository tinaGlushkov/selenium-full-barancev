package ru.stqa.lesson6;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.lesson4.BaseTest;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TaskTwelve extends BaseTest {

    private By catalogMenu = By.xpath("//span[text() = 'Catalog']");
    private By addNewProductButton = By.xpath("//a[@class='button'][2]");
    private By statusEnabled = By.cssSelector("label [value='1']");
    private By nameField = By.xpath("//input[@name='name[en]']");
    private By codeField = By.cssSelector("input[name=code]");

    @Test
    public void addProduct() {
        login();
        driver.findElement(catalogMenu).click();
        wait.until(ExpectedConditions.elementToBeClickable(addNewProductButton)).click();

        //GENERAL TAB
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
        driver.findElement(statusEnabled).click();
        driver.findElement(nameField).sendKeys("Black Duck");
        driver.findElement(codeField).sendKeys("rd006");
        driver.findElement(By.xpath("//input[@name='new_images[]']")).sendKeys("/home/kristinaglushkova/Desktop/selenium-full-barancev/src/test/resources/images.jpeg");
        driver.findElement(By.name("quantity")).clear();
        driver.findElement(By.name("quantity")).sendKeys("1");
        driver.findElement(By.name("date_valid_from")).sendKeys("02/02/2012");
        driver.findElement(By.name("date_valid_to")).sendKeys("02/03/2012");

        //INFORMATION TAB
        driver.findElement(By.linkText("Information")).click();
        Select manufacturer = new Select(wait.until(ExpectedConditions.presenceOfElementLocated(By.name("manufacturer_id"))));
        manufacturer.selectByVisibleText("ACME Corp.");
        driver.findElement(By.name("keywords")).sendKeys("black duck");
        driver.findElement(By.name("short_description[en]")).sendKeys("very black duck");
        driver.findElement(By.name("description[en]")).sendKeys( "small black duck, perfect for every event");
        driver.findElement(By.name("head_title[en]")).sendKeys("Black Duck");
        driver.findElement(By.name("meta_description[en]")).sendKeys("meta");

        //PRICE TAB
        driver.findElement(By.linkText("Prices")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("table-prices")));
        driver.findElement(By.name("purchase_price")).clear();
        driver.findElement(By.name("purchase_price")).sendKeys("1000");
        Select priceCurrency = new Select(driver.findElement(By.name("purchase_price_currency_code")));
        priceCurrency.selectByVisibleText("US Dollars");
        driver.findElement(By.name("prices[USD]")).sendKeys("1000");

        driver.findElement(By.name("save")).click();
        List<WebElement> newProduct = driver.findElements(By.linkText("Black Duck"));
        assertTrue(newProduct.size() > 0);

    }
}
