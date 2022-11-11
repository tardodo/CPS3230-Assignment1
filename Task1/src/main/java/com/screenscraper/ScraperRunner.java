package com.screenscraper;

import com.screenscraper.components.Scraper;
import com.screenscraper.components.pageobjects.AlibabaSite;
import com.screenscraper.components.pageobjects.Product;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.net.http.HttpClient;

public class ScraperRunner {
    public static void main(String[] args) throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\farad\\Documents\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        AlibabaSite site = new AlibabaSite();

        Scraper scraper = new Scraper(driver, site);
        String test = scraper.goToSite("https://www.alibaba.com/");
        String smart = scraper.searchForSmartphone();
        scraper.retrieveRequiredProducts();
        System.out.println(scraper.finalProductList.size());
        for(Product prod: scraper.finalProductList){
            System.out.println(prod.productTitle);
            System.out.println(prod.productDescription);
            System.out.println(prod.productLink);
            System.out.println(prod.productImage);
            System.out.println(prod.productPrice);
            System.out.println();
        }
        scraper.quitDriver();

        HttpClient client = HttpClient.newHttpClient();
        scraper.uploadToAPI(client);
    }
}
