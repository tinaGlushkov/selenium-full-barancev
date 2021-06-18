package ru.stqa.lesson7;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.lesson4.BaseTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TaskThirteen extends BaseTest {

    private final String MAIN_PAGE = "http://localhost/litecart/en/rubber-ducks-c-1/";
    private By item = By.cssSelector(".product a:nth-child(1)");
    private By addToCartButton = By.cssSelector("button[name=add_cart_product]");
    private By cartCounter = By.cssSelector("span.quantity");
    private By checkoutLink = By.cssSelector("#cart a[class=link]");
    private By removeButton = By.cssSelector("button[value='Remove']");
    private By summaryTableQuantity = By.cssSelector("#order_confirmation-wrapper tr:nth-child(2) td:first-child");

    @Test
    public void testAddToCart() {

        for (int i = 2; i < 5; i++) {
            driver.get(MAIN_PAGE);
            List<WebElement> items = driver.findElements(item);
            items.get(i).click();
            WebElement cartQuantity = driver.findElement(cartCounter);
            String fromCheckout = cartQuantity.getAttribute("innerText");
            driver.findElement(addToCartButton).click();
            while(driver.findElement(cartCounter).getAttribute("innerText").equals(fromCheckout)) {
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            }
        }
        driver.findElement(checkoutLink).click();
        wait.until((WebDriver d) -> d.findElement(removeButton));
        assertTrue(isRemovedFromCart());

    }

    public boolean isRemovedFromCart() {
        boolean isRemoved = false;
        wait.until((WebDriver d) -> d.findElement(By.cssSelector("li.shortcut")));
        List<WebElement> cartItems = driver.findElements(By.cssSelector("li.shortcut"));
        while(cartItems.size() > 0){
            WebElement table = driver.findElement(summaryTableQuantity);
            WebElement remove = wait.until((WebDriver d) -> d.findElement(removeButton));
            cartItems = driver.findElements(By.cssSelector("li.shortcut"));
            remove.click();
            wait.until(ExpectedConditions.stalenessOf(table));
        }
        if(driver.findElement(By.cssSelector("#checkout-cart-wrapper em")).getText().equals("There are no items in your cart.")){
            isRemoved = true;
        }
        return isRemoved;
    }

}
