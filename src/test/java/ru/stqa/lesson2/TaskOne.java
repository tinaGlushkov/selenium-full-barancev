package ru.stqa.lesson2;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TaskOne {

    WebDriver driver;

    @Before
    public void driverSetUp() {
        driver = new ChromeDriver();

    }

    @Test
    public void taskOne() {
        driver.navigate().to("https://software-testing.ru/");
    }


    @After
    public void quit() {
        driver.quit();
    }

}
