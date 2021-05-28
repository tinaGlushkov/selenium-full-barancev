package ru.stqa.lesson4;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import static org.junit.Assert.assertEquals;
public class StickerTests extends BaseTest{



    private By username = By.name("username");
    private By password = By.name("password");
    private By loginButton = By.name("login");
    private By productIcon = By.className("product");
    private By sticker = By.cssSelector("div.sticker");
    private By shop = By.xpath("//a[@title='Catalog']");

    @Test
    public void testStickers() {

        driver.findElement(username).sendKeys("admin");
        driver.findElement(password).sendKeys("admin");
        driver.findElement(loginButton).click();
        driver.findElement(shop).click();

        List<WebElement> productList = driver.findElements(productIcon);
        if(productList.size()> 0) {
            for(int i = 0; i < productList.size(); i++) {
                productList = driver.findElements(productIcon);
                WebElement product = productList.get(i);
                int numberOfStickers = product.findElements(sticker).size();
                assertEquals(1, numberOfStickers);
            }
        }
    }
}

