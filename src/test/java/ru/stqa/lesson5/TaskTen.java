package ru.stqa.lesson5;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import ru.stqa.lesson4.BaseTest;

import static org.junit.Assert.*;

public class TaskTen extends BaseTest {
//Сделайте сценарий, который проверяет, что при клике на товар открывается правильная страница товара
    private static final String SITE_URL = "http://localhost/litecart/en/";
    private By productText = By.cssSelector("#box-campaigns div.name");


    // Более точно, нужно открыть главную страницу, выбрать первый товар в блоке Campaigns и проверить следующее:
    @Test
    public void testText() {
        driver.get(SITE_URL);
        String productNameMain = driver.findElement(productText).getAttribute("innerHTML");
        System.out.println(productNameMain);
        driver.findElement(productText).click();
        String productName = driver.findElement(By.cssSelector("#box-product .title")).getAttribute("innerText");
        System.out.println(productName);
        assertEquals(productName, productNameMain);
    }

    @Test
    public void testPrice() {
        driver.get(SITE_URL);

        WebElement originalPriceElementMainPage = driver.findElement(By.cssSelector("#box-campaigns .regular-price"));
        WebElement salePriceElementMainPage = driver.findElement(By.cssSelector("#box-campaigns .campaign-price"));

        String originalPriceMain = originalPriceElementMainPage.getAttribute("innerHTML");
        String salePriceMain = salePriceElementMainPage.getAttribute("innerHTML");
        String colorOriginalMain = originalPriceElementMainPage.getCssValue("color");
        String colorSaleMain = salePriceElementMainPage.getCssValue("color");
        String sizeOriginalMain = originalPriceElementMainPage.getCssValue("font-size");
        String sizeSaleMain = salePriceElementMainPage.getCssValue("font-size");
        String fontOriginalMain = originalPriceElementMainPage.getCssValue("font-weight");
        String fontSaleMain = salePriceElementMainPage.getCssValue("font-weight");

        salePriceElementMainPage.click();

        WebElement originalPriceElement = driver.findElement(By.cssSelector(".regular-price"));
        WebElement salePriceElement = driver.findElement(By.cssSelector(".campaign-price"));

        String originalPrice = originalPriceElement.getAttribute("innerHTML");
        String salePrice = salePriceElement.getAttribute("innerHTML");
        String colorOriginal = originalPriceElement.getCssValue("color");
        String colorSale = salePriceElement.getCssValue("color");
        String sizeOriginal = originalPriceElement.getCssValue("font-size");
        String sizeSale = salePriceElement.getCssValue("font-size");
        String fontOriginal = originalPriceElement.getCssValue("font-weight");
        String fontSale = salePriceElement.getCssValue("font-weight");

        assertEquals(originalPrice, originalPriceMain);
        assertEquals(salePrice, salePriceMain);
        assertEquals(colorSale, colorSaleMain);
        assertTrue(isGrey(colorOriginalMain));
        assertTrue(isGrey(colorOriginal));
        assertTrue(isRed(colorSaleMain));
        assertTrue(isRed(colorSale));
        assertTrue(salePriceSizeIsBigger(sizeSaleMain, sizeOriginalMain));
        assertTrue(salePriceSizeIsBigger(sizeSale, sizeOriginal));
        assertFalse(isBold(fontOriginalMain));
        assertTrue(isBold(fontSaleMain));
        assertFalse(isBold(fontOriginal));
        assertTrue(isBold(fontSale));


    }

        public boolean isGrey (String color) {
            boolean isGrey = false;

            int r = Integer.parseInt(color.substring(5,8));
            int g = Integer.parseInt(color.substring(10, 13));
            int b = Integer.parseInt(color.substring(15,18));
            if(r == g && g == b){
                isGrey = true;
            }
            return isGrey;
        }

    public boolean isRed (String color) {
        boolean isRed = false;

        int g = Integer.parseInt(color.substring(10, 11));
        int b = Integer.parseInt(color.substring(13,14));
        if(g == 0 && b == 0){
            isRed = true;
        }
        return isRed;
    }

    public boolean salePriceSizeIsBigger(String salePrice, String originalPrice) {
        boolean salePriceSizeIsBigger = false;

        float salePriceSize = Float.parseFloat(salePrice.substring(0, salePrice.length()-2));
        float originalPriceSize = Float.parseFloat(originalPrice.substring(0, originalPrice.length()-2));
        if(salePriceSize > originalPriceSize) {
            salePriceSizeIsBigger = true;
        }
        return salePriceSizeIsBigger;
    }

    public boolean isBold(String weight) {
        boolean isBold = false;
        if (Integer.parseInt(weight) >= 700){
            isBold = true;
        }
        return isBold;
    }

}
