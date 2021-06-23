package ru.stqa.lesson11.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ProductPage {

    private WebDriver driver;
    private WebDriverWait wait;
    private By addToCartButton = By.cssSelector("button[name=add_cart_product]");
    private By cartCounter = By.cssSelector("span.quantity");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void addProduct() {
        WebElement cartQuantity = driver.findElement(cartCounter);
        String fromCheckout = cartQuantity.getAttribute("innerText");
        driver.findElement(addToCartButton).click();
        while (driver.findElement(cartCounter).getAttribute("innerText").equals(fromCheckout)) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        }
    }

}
