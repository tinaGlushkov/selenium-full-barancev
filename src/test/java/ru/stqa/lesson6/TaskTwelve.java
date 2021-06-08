package ru.stqa.lesson6;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.lesson4.BaseTest;

public class TaskTwelve extends BaseTest {

    private By catalogMenu = By.xpath("//span[text() = 'Catalog']");
    private By addNewProductButton = By.xpath("//a[@class='button'][2]");
    private By statusEnabled = By.cssSelector("label [value='1']");
    private By nameField = By.cssSelector("[name='name[en]']");
    private By codeField = By.cssSelector("input[name=code]");


    @Test
    public void test1() {
        login();
        driver.findElement(catalogMenu).click();
        wait.until(ExpectedConditions.elementToBeClickable(addNewProductButton)).click();
    }

    @Test
    public void test2() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
        driver.findElement(statusEnabled).click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement nameFieldEl = driver.findElement(nameField);
        js.executeScript("arguments[0].setAttribute('value', 'arguments[1])", nameFieldEl, "Black Duck");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("no element")));

        //driver.findElement(nameField).sendKeys("Black Duck");
        driver.findElement(codeField).sendKeys("rd006");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("no element")));


        /*driver.get("http://jqueryui.com/datepicker/");
        driver.switchTo().frame(
                driver.findElement(By.cssSelector("iframe.demo-frame")));
        setDatepicker(driver, "#datepicker", "02/20/2002");
*/
        //choose radio button enables

        //uncheck box, and check rubberDucks check directory
        //default category - rubber ducks
        //no gender
        //add quantity, sold out change to temp sold out
        //choose file photo
        //add dates
        //click save

    }

    public  void setDatepicker(WebDriver driver, String selector, String date) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(selector)));
        /*new WebDriverWait(driver, 3000).until(
                (WebDriver d) -> d.findElement(By.cssSelector(selector)).isDisplayed()
        );*/
        JavascriptExecutor.class.cast(driver).executeScript(String.format("$('%s').datepicker('setDate', '%s')", selector, date));
    }


}
