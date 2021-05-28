package ru.stqa.lesson2;

import jdk.jfr.Timespan;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;


public class TaskThree {

    private WebDriver driver;
    private By username = By.xpath("//input[@name = 'username']");
    private By password = By.xpath("//input[@name = 'password']");
    private By loginButton = By.xpath("//button[@name='login']");

    @Before
    /*public void driverSetUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-fullscreen");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
    }*/
    public void driverSetUp() {
        //DesiredCapabilities caps = new DesiredCapabilities();
        //caps.setCapability(FirefoxDriver.MARIONETTE, true);
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary(new FirefoxBinary(new File("/home/kristinaglushkova/Documents/Nightly/firefox/firefox")));
        driver = new FirefoxDriver(options);
        //System.out.println(((HasCapabilities) driver).getCapabilities());
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
