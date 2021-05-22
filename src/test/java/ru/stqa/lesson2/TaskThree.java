package ru.stqa.lesson2;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.PseudoColumnUsage;

public class TaskThree {

    private WebDriver driver;
    private By username = By.xpath("//input[@name = 'username']");
    private By password = By.xpath("//input[@name = 'password']");
    private By loginButton = By.xpath("//button[@name='login']");

    @Before
    public void driverSetUp() {
        driver = new ChromeDriver();
    }

    @Test
    public void testTaskThree() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(username).sendKeys("admin"); //login
        driver.findElement(password).sendKeys("admin"); //password
        driver.findElement(loginButton).click();
        Assert.assertEquals("Login Successful", "http://localhost/litecart/admin/", driver.getCurrentUrl());
    }



    @After
    public void tearDown() {
        driver.quit();
        driver = null;
    }


}
