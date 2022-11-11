package com.screenscraper.components;

import com.screenscraper.components.Scraper;
import com.screenscraper.components.pageobjects.ECommerceSite;
import com.screenscraper.components.pageobjects.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.openqa.selenium.WebDriver;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

public class ScraperTests {


    @Test
    public void testScraperSetDriver(){
        //setup
        Scraper scraper = new Scraper();
        WebDriver driver = Mockito.mock(WebDriver.class);

        //exercise
        scraper.setDriver(driver);

        //verify
        Assertions.assertNotNull(scraper.driver);

        //teardown
    }

    @Test
    public void testScraperSetSite(){
        //setup
        Scraper scraper = new Scraper();
        ECommerceSite site = Mockito.mock(ECommerceSite.class);

        //exercise
        scraper.setWebsiteObject(site);

        //verify
        Assertions.assertNotNull(scraper.website);

        //teardown
    }

    @Test
    public void testScraperGoToSite(){
        //setup
        WebDriver driver = Mockito.mock(WebDriver.class);
        ECommerceSite site = Mockito.mock(ECommerceSite.class);
        Scraper scraper = new Scraper(driver, site);
        String url = "https://www.alibaba.com/";
        Mockito.when(driver.getCurrentUrl()).thenReturn(url);

        //exercise
        String currentURL = scraper.goToSite(url);

        //verify
        Assertions.assertEquals(currentURL, url);

        //teardown
        scraper.quitDriver();
    }

    @Test
    public void testScraperGoToSiteNoWebDriver(){
        //setup
        Scraper scraper = new Scraper();
        String url = "https://www.alibaba.com/";

        //exercise
        String currentURL = scraper.goToSite(url);

        //verify
        Assertions.assertNull(currentURL);

    }

    @Test
    public void testScraperSearchForSmartPhones(){
        //setup
        WebDriver driver = Mockito.mock(WebDriver.class);
        ECommerceSite site = Mockito.mock(ECommerceSite.class);
        Scraper scraper = new Scraper(driver, site);
        String title = "Smartphone-Smartphone Manufacturers, Suppliers and Exporters on Alibaba.comMobile Phones";
        Mockito.when(driver.getTitle()).thenReturn(title);
        Mockito.when(site.searchBar(any(String.class))).thenReturn(true);
        Mockito.when(site.applyFilter()).thenReturn(true);

        //exercise
        String returnedTitle = scraper.searchForSmartphone();
        //verify
        Assertions.assertEquals(returnedTitle, title);
        //teardown
    }

    @Test
    public void testScraperSearchForSmartPhonesFailedSearch(){
        //setup
        WebDriver driver = Mockito.mock(WebDriver.class);
        ECommerceSite site = Mockito.mock(ECommerceSite.class);
        Scraper scraper = new Scraper(driver, site);
        String title = "Smartphone-Smartphone Manufacturers, Suppliers and Exporters on Alibaba.comMobile Phones";
        Mockito.when(driver.getTitle()).thenReturn(title);
        Mockito.when(site.searchBar(any(String.class))).thenReturn(false);
        Mockito.when(site.applyFilter()).thenReturn(true);

        //exercise
        String returnedTitle = scraper.searchForSmartphone();
        //verify
        Assertions.assertNull(returnedTitle);
        //teardown
    }

    @Test
    public void testScraperSearchForSmartPhonesFailedFilterApplication(){
        //setup
        WebDriver driver = Mockito.mock(WebDriver.class);
        ECommerceSite site = Mockito.mock(ECommerceSite.class);
        Scraper scraper = new Scraper(driver, site);
        String title = "Smartphone-Smartphone Manufacturers, Suppliers and Exporters on Alibaba.comMobile Phones";
        Mockito.when(driver.getTitle()).thenReturn(title);
        Mockito.when(site.searchBar(any(String.class))).thenReturn(true);
        Mockito.when(site.applyFilter()).thenReturn(false);

        //exercise
        String returnedTitle = scraper.searchForSmartphone();
        //verify
        Assertions.assertNull(returnedTitle);
        //teardown
    }

    @Test
    public void testScraperSearchForSmartPhonesNoWebsiteNoDriver(){
        //setup
        Scraper scraper = new Scraper();

        //exercise
        String returnedTitle = scraper.searchForSmartphone();
        //verify
        Assertions.assertNull(returnedTitle);
        //teardown
    }

    @Test
    public void testScraperProductRetrieval5Products(){
        //setup
        WebDriver driver = Mockito.mock(WebDriver.class);
        ECommerceSite site = Mockito.mock(ECommerceSite.class);
        Scraper scraper = new Scraper(driver, site);
        List<Product> products = new ArrayList<Product>(List.of(new Product(), new Product(), new Product(), new Product(),new Product()));
        Mockito.when(site.getProducts()).thenReturn(products);

        //exercise
        scraper.retrieveRequiredProducts();

        //verify
        Assertions.assertEquals(5, scraper.finalProductList.size());
        //teardown
    }

    @Test
    public void testScraperProductRetrieval6Products(){
        //setup
        WebDriver driver = Mockito.mock(WebDriver.class);
        ECommerceSite site = Mockito.mock(ECommerceSite.class);
        Scraper scraper = new Scraper(driver, site);
        List<Product> products = new ArrayList<Product>(List.of(new Product(), new Product(), new Product(), new Product(),new Product(), new Product()));
        Mockito.when(site.getProducts()).thenReturn(products);

        //exercise
        scraper.retrieveRequiredProducts();

        //verify
        Assertions.assertEquals(5, scraper.finalProductList.size());
        //teardown
    }

    @Test
    public void testScraperProductRetrieval10Products(){
        //setup
        WebDriver driver = Mockito.mock(WebDriver.class);
        ECommerceSite site = Mockito.mock(ECommerceSite.class);
        Scraper scraper = new Scraper(driver, site);
        List<Product> products = new ArrayList<Product>(List.of(new Product(), new Product(), new Product(), new Product(),new Product(), new Product(),new Product(), new Product(),new Product(), new Product()));
        Mockito.when(site.getProducts()).thenReturn(products);

        //exercise
        scraper.retrieveRequiredProducts();

        //verify
        Assertions.assertEquals(5, scraper.finalProductList.size());
        //teardown
    }

    @Test
    public void testScraperProductRetrieval1Product(){
        //setup
        WebDriver driver = Mockito.mock(WebDriver.class);
        ECommerceSite site = Mockito.mock(ECommerceSite.class);
        Scraper scraper = new Scraper(driver, site);
        List<Product> products = new ArrayList<Product>(List.of(new Product()));
        Mockito.when(site.getProducts()).thenReturn(products);

        //exercise
        scraper.retrieveRequiredProducts();

        //verify
        Assertions.assertEquals(0, scraper.finalProductList.size());
        //teardown
    }

    @Test
    public void testScraperProductRetrieval0Product(){
        //setup
        WebDriver driver = Mockito.mock(WebDriver.class);
        ECommerceSite site = Mockito.mock(ECommerceSite.class);
        Scraper scraper = new Scraper(driver, site);
        List<Product> products = new ArrayList<Product>();
        Mockito.when(site.getProducts()).thenReturn(products);

        //exercise
        scraper.retrieveRequiredProducts();

        //verify
        Assertions.assertEquals(0, scraper.finalProductList.size());
        //teardown
    }



    @Test
    public void testScraperUploadToAPI() throws Exception {
        //setup
        Scraper scraper = new Scraper();
        scraper.finalProductList = new ArrayList<Product>(List.of(new Product(), new Product(), new Product(), new Product(),new Product()));

        HttpClient client = Mockito.mock(HttpClient.class);
        HttpResponse<String> resp = Mockito.mock(HttpResponse.class);
        Mockito.when(resp.statusCode()).thenReturn(201);
        Mockito.when(client.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(resp);

        //exercise
        List<HttpResponse<String>> returnedResponses = scraper.uploadToAPI(client);

        //verify
        Assertions.assertEquals(5, returnedResponses.size());
        Assertions.assertEquals(201, returnedResponses.get(3).statusCode());

        //teardown
    }

    @Test
    public void testScraperUploadToAPILessThan5Products() throws Exception {
        //setup
        Scraper scraper = new Scraper();
        scraper.finalProductList = new ArrayList<Product>(List.of(new Product(), new Product(), new Product(), new Product()));
        HttpClient client = Mockito.mock(HttpClient.class);

        //exercise
        List<HttpResponse<String>> returnedResponses = scraper.uploadToAPI(client);

        //verify
        Assertions.assertNull(returnedResponses);

        //teardown
    }
}
