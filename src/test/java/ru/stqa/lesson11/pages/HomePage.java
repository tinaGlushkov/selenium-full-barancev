package ru.stqa.lesson11.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;
    private static final String HOME_PAGE_URL = "http://localhost/litecart/en/";
    private By item = By.cssSelector(".product a:nth-child(1)");
    private By addToCartButton = By.cssSelector("button[name=add_cart_product]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
    }

    public void openProductPage(){
        driver.get(HOME_PAGE_URL);
        driver.findElement(item).click();
        wait.until(presenceOfElementLocated(addToCartButton));
    }


}
