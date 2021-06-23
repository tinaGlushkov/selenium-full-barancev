package ru.stqa.lesson11.tests;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import ru.stqa.lesson11.app.Application;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Tests extends BaseTest{

    @Test
    public void testAddToCart() {
        app.addToCart();
        int added  = app.howManyLeftInCart();
        app.removeProduct();
        assertTrue(app.isCartEmpty());

    }
}
