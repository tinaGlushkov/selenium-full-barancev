package ru.stqa.lesson10;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.stqa.lesson4.BaseTest;

import java.util.List;

public class TaskSeventeen extends BaseTest {

    private static final String PRODUCT_URL = "http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1";
    private  By productLink = By.cssSelector(".row td a:nth-child(2)");
    private  By cancelButton = By.cssSelector(".fa.fa-times");
    private String[] links = {"Green Duck", "Yellow Duck", "Blue Duck", "Purple Duck", "Red Duck"};

    @Test
    public void testLogs() {

        driver.get(PRODUCT_URL);
        driver.manage().logs().get("browser").forEach(l -> System.out.println(l));
        for(int i = 0; i < links.length; i++){
            driver.findElement(By.linkText(links[i])).click();
            List<LogEntry> logs = driver.manage().logs().get(LogType.BROWSER).getAll();
            for(LogEntry le : logs){
                System.out.println(le);
                if(le.getLevel().toString().equals("ERROR")){
                    System.out.println("ERROR DETECTED");
                }
            }
            wait.until(ExpectedConditions.presenceOfElementLocated(cancelButton)).click();

        }
    }


}
