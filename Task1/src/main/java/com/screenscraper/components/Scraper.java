package com.screenscraper.components;

import com.google.gson.Gson;
import com.screenscraper.components.pageobjects.ECommerceSite;
import com.screenscraper.components.pageobjects.Product;
import org.openqa.selenium.WebDriver;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class Scraper {

    public WebDriver driver;
    public ECommerceSite website;
    public List<Product> finalProductList;

    public Scraper(){
        driver =  null;
        website = null;
        finalProductList = new ArrayList<>();
    }
    public Scraper(WebDriver newDriver, ECommerceSite site){
        driver = newDriver;
        website = site;
        site.setDriver(newDriver);
        finalProductList = new ArrayList<>();
    }

    public void setDriver(WebDriver newDriver){
        driver = newDriver;
    }

    public void setWebsiteObject(ECommerceSite site){
        website = site;
    }

    public String goToSite(String site){
        if(driver != null) {
            driver.get(site);
            return driver.getCurrentUrl();
        }else return null;
    }

    public String searchForSmartphone(){
        if(driver != null && website != null) {
            if(website.searchBar("smartphone"))
                if(website.applyFilter())
                    return driver.getTitle();
        }
        return null;
    }

    public void retrieveRequiredProducts(){
        if(driver != null && website != null) {
            List<Product> products = website.getProducts();

            if(products.size() >= 5)
//            try {
                finalProductList = products.subList(0, 5);
//            }catch (Exception ignored){}
        }
    }

    public void quitDriver(){
        driver.quit();
    }

    public List<HttpResponse<String>> uploadToAPI(HttpClient newClient) throws Exception{
        Gson gson = new Gson();
        List<HttpResponse<String>> responses = new ArrayList<>();

        if(finalProductList.size() == 5) {
            for (Product prod : finalProductList) {
                TechAlert alert = new TechAlert(prod);
                String jsonProduct = gson.toJson(alert);

                HttpRequest postReq = HttpRequest.newBuilder()
                        .uri(new URI("https://api.marketalertum.com/Alert"))
                        .header("content-type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(jsonProduct))
                        .build();

                HttpClient client = HttpClient.newHttpClient();
                HttpResponse<String> resp = newClient.send(postReq, HttpResponse.BodyHandlers.ofString());
                System.out.println(resp);
                responses.add(resp);
            }
            return responses;
        }else{
            return null;
        }
    }


}
