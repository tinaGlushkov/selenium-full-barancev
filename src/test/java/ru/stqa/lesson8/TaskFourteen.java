package ru.stqa.lesson8;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.stqa.lesson4.BaseTest;

import java.util.List;
import java.util.Set;

public class TaskFourteen extends BaseTest {

    private static final String URL = "http://localhost/litecart/admin/?app=countries&doc=countries";
    private By Countries = By.linkText("Countries");
    private By addNewButton = By.cssSelector("#content .button");
    private By link = By.cssSelector("form .fa.fa-external-link");

    @Test
    public void testLinks() {
        login();
        driver.findElement(Countries).click();
        driver.findElement(addNewButton).click();
        List<WebElement> links = driver.findElements(link);
        for(WebElement el : links){
            String mainWindow = driver.getWindowHandle(); // get the main window id
            el.click();
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            String newWindow = getNewWindowHandle(mainWindow);
            driver.switchTo().window(newWindow);
            System.out.println(driver.getTitle());
            driver.close();
            driver.switchTo().window(mainWindow);
        }
    }

    public String getNewWindowHandle(String existingWindow) {
        String newWindow = "";
        Set<String> handles = driver.getWindowHandles();
        for(String s:handles) {
            if (!s.equals(existingWindow)) {
                newWindow = s;
            }
        }
        return newWindow;
    }
}
