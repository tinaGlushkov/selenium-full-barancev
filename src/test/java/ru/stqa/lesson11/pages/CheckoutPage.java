package ru.stqa.lesson11.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfElementsToBeLessThan;

public class CheckoutPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private By checkoutLink = By.cssSelector("div#cart a.link");
    private By removeButton = By.cssSelector("button[value='Remove']");
    private By summaryTableQuantity = By.cssSelector("#order_confirmation-wrapper tr:nth-child(2) td:first-child");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void openCart() {
        driver.findElement(checkoutLink).click();
        wait.until((WebDriver d) -> d.findElement(removeButton));
    }

    public void removeFromCart() {
        int quantity = getQuantity();
        if(quantity > 0){
            WebElement table = driver.findElement(summaryTableQuantity);
            WebElement remove = wait.until((WebDriver d) -> d.findElement(removeButton));
            remove.click();
            wait.until(numberOfElementsToBeLessThan(By.cssSelector("td.items"),
                    quantity));
        }

    }
    public int getQuantity() {
        wait.until((WebDriver d) -> d.findElement(By.cssSelector("td.item")));
        List<WebElement> productsInCart = driver.findElements(By.cssSelector("td.item"));
        return productsInCart.size();
    }

    public String cartIsEmptyMessage() {
        return driver.findElement(By.cssSelector("#checkout-cart-wrapper em")).getText();
    }

}
