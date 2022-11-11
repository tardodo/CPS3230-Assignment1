package com.screenscraper.components;

import com.beust.ah.A;
import com.screenscraper.components.pageobjects.AlibabaSite;
import com.screenscraper.components.pageobjects.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

public class AlibabaSiteTests {

    @Test
    public void testUsingSearchBar(){
        WebDriver driver = Mockito.mock(WebDriver.class);
        AlibabaSite site = new AlibabaSite();
        site.setDriver(driver);
        WebElement el = Mockito.mock(WebElement.class);
        Mockito.when(driver.findElement(any(By.class))).thenReturn(el);

        boolean val = site.searchBar("Smartphone");

        Assertions.assertTrue(val);
    }

    @Test
    public void testUsingSearchBarCannotFindElement(){
        WebDriver driver = Mockito.mock(WebDriver.class);
        AlibabaSite site = new AlibabaSite();
        site.setDriver(driver);
        Mockito.when(driver.findElement(any(By.class))).thenThrow();

        boolean val = site.searchBar("Smartphone");

        Assertions.assertFalse(val);
    }

    @Test
    public void testApplyingFilter(){
        WebDriver driver = Mockito.mock(WebDriver.class);
        AlibabaSite site = new AlibabaSite();
        site.setDriver(driver);
        WebElement el = Mockito.mock(WebElement.class);
        Mockito.when(driver.findElement(any(By.class))).thenReturn(el);

        boolean val = site.applyFilter();

        Assertions.assertTrue(val);
    }

    @Test
    public void testApplyingFilterCannotFindElement(){
        WebDriver driver = Mockito.mock(WebDriver.class);
        AlibabaSite site = new AlibabaSite();
        site.setDriver(driver);
        Mockito.when(driver.findElement(any(By.class))).thenThrow();

        boolean val = site.applyFilter();

        Assertions.assertFalse(val);
    }

    @Test
    public void testGettingProductPane(){
        WebDriver driver = Mockito.mock(WebDriver.class);
        AlibabaSite site = new AlibabaSite();
        site.setDriver(driver);
        WebElement el = Mockito.mock(WebElement.class);
        WebElement returnedEl = Mockito.mock(WebElement.class);
        List<WebElement> webElementList = new ArrayList<WebElement>(List.of(el, el, el, el, el, el, el, el));
        Mockito.when(driver.findElements(any(By.class))).thenReturn(webElementList);
        Mockito.when(el.findElement(any(By.class))).thenReturn(returnedEl);
        Mockito.when(returnedEl.getAttribute(any(String.class))).thenReturn("");
        Mockito.when(returnedEl.getAttribute("href")).thenReturn("aproduct-detail/a");
        Mockito.when(returnedEl.getText()).thenReturn("");

        List<Product> val = site.getProducts();

        Assertions.assertEquals(8, val.size());
    }

    @Test
    public void testGettingProductPaneProductWebElementMissingData(){
        WebDriver driver = Mockito.mock(WebDriver.class);
        AlibabaSite site = new AlibabaSite();
        site.setDriver(driver);
        WebElement el = Mockito.mock(WebElement.class);
        WebElement returnedEl = Mockito.mock(WebElement.class);
        List<WebElement> webElementList = new ArrayList<WebElement>(List.of(el, el, el, el, el, el, el, el));
        Mockito.when(driver.findElements(any(By.class))).thenReturn(webElementList);
        Mockito.when(el.findElement(any(By.class))).thenReturn(returnedEl);
        Mockito.when(returnedEl.getAttribute(any(String.class))).thenReturn("");
//        Mockito.when(returnedEl.getAttribute("href")).thenReturn("aproduct-detail/a");
        Mockito.when(returnedEl.getText()).thenReturn("");

        List<Product> val = site.getProducts();

        Assertions.assertEquals(8, val.size());
        Assertions.assertNull(val.get(0));
    }


}
