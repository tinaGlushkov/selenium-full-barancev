package ru.stqa.lesson5;


import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.lesson4.BaseTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class TaskNine extends BaseTest {

    private static final String URL_COUNTRIES = "http://localhost/litecart/admin/?app=countries&doc=countries";
    private static final String GEO_ZONES = "http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones";

    @Test
    public void testCountriesOrder() {
        driver.get(URL_COUNTRIES);

        ArrayList<String> countriesFromLinks = new ArrayList<>();
        WebElement table = driver.findElement(By.cssSelector("table.dataTable"));
        List<WebElement> rows = table.findElements(By.cssSelector("tr.row"));
        for(WebElement row : rows){
            String countryFromLink = row.findElement(By.xpath("./td[5]")).getText();
            //int zonesNumber = Integer.parseInt(row.findElement(By.xpath("./td[6]")).getText());

            countriesFromLinks.add(countryFromLink);
        }
        assertTrue("The list is not Alphabetically ordered", isSorted(countriesFromLinks));
    }

    @Test
    public void testZonesOrder() {
        driver.get(URL_COUNTRIES);
        WebElement table = driver.findElement(By.cssSelector("table.dataTable"));
        List<WebElement> rows = table.findElements(By.cssSelector("tr.row"));

        ArrayList<String> links = new ArrayList<>();
        for (WebElement row : rows) {
            int zonesCell = Integer.parseInt(row.findElement(By.xpath("./td[6]")).getText());
            if (zonesCell > 0) {
                WebElement zoneNumber = row.findElement(By.cssSelector("td a"));
                links.add(zoneNumber.getAttribute("href"));
            }
        }
        if (links.size() > 0) {
            for (String link : links) {
                ArrayList<String> zones = new ArrayList<>();
                driver.get(link);
                table = driver.findElement(By.cssSelector("table.dataTable"));
                rows = table.findElements(By.cssSelector("tr:not(.header)"));
                for (int i = 0; i < rows.size() - 1; i++) {
                    WebElement row = rows.get(i);
                    zones.add(row.findElement(By.xpath("./td[3]")).getText());
                }
                assertTrue(isSorted(zones));
            }
        }
    }

    @Test
    public void testGeoZones() {
        driver.get(GEO_ZONES);
        List<WebElement> rows = driver.findElements(By.cssSelector("tr.row"));
        List<String> links = new ArrayList<>();
        for (WebElement row : rows) {
            links.add(row.findElement(By.cssSelector("td:nth-child(3) a")).getAttribute("href"));
        }
        if (links.size() > 0) {
            for (String link : links) {
                ArrayList<String> zones = new ArrayList<>();
                driver.get(link);
                WebElement table = driver.findElement(By.cssSelector("table.dataTable"));
                rows = table.findElements(By.cssSelector("tr td:nth-child(3)"));
                for (WebElement row : rows) {
                    zones.add(row.findElement(By.cssSelector("[selected=selected]")).getAttribute("innerHTML"));
                }
                assertTrue(isSorted(zones));
            }
        }
    }


    public boolean isSorted(ArrayList<String> listToCheck) {
        String previous = "";
        boolean isSorted = true;
        for(String current : listToCheck) {
            if (previous.compareTo(current) > 0) {
                isSorted = false;
            }
            previous = current;
        }
        return isSorted;
    }


}
