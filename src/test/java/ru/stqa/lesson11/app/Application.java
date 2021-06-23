package ru.stqa.lesson11.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.lesson11.pages.CheckoutPage;
import ru.stqa.lesson11.pages.HomePage;
import ru.stqa.lesson11.pages.ProductPage;

public class Application {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private HomePage homePage;
    private ProductPage productPage;
    private CheckoutPage cart;

    public Application() {
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cart = new CheckoutPage(driver);
    }

    public void quit() {
        driver.quit();
        driver = null;
    }

    public void addToCart() {
        homePage.openProductPage();
        productPage.addProduct();
    }

    public void removeProduct() {
        cart.removeFromCart();
    }

    public int howManyLeftInCart() {
        cart.openCart();
        return cart.getQuantity();
    }

    public boolean isCartEmpty() {
        System.out.println(cart.cartIsEmptyMessage());
        return cart.cartIsEmptyMessage().equals("There are no items in your cart.");
    }
}
