package test.marketalertum.scraper;

import com.google.gson.Gson;
import com.screenscraper.components.TechAlert;
import com.screenscraper.components.pageobjects.ECommerceSite;
import com.screenscraper.components.pageobjects.Product;
import org.openqa.selenium.WebDriver;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ModScraper {

    public WebDriver driver;
    public ECommerceSite website;
    public List<Product> finalProductList;

    int amountOfProducts;

    public ModScraper(){
        driver =  null;
        website = null;
        finalProductList = new ArrayList<>();
        amountOfProducts = 0;
    }
    public ModScraper(WebDriver newDriver, ECommerceSite site){
        driver = newDriver;
        website = site;
        site.setDriver(newDriver);
        finalProductList = new ArrayList<>();
        amountOfProducts = 0;
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

    public void retrieveRequiredProducts(int amount){
        if(driver != null && website != null) {
            List<Product> products = website.getProducts();

            if(products.size() >= amount) {
                finalProductList = products.subList(0, amount);
                amountOfProducts = amount;
            }
        }
    }

    public void quitDriver(){
        driver.quit();
    }

    public List<HttpResponse<String>> uploadToAPI(int alertType) throws Exception{
        Gson gson = new Gson();
        List<HttpResponse<String>> responses = new ArrayList<>();

        if(finalProductList.size() == amountOfProducts) {
            for (Product prod : finalProductList) {
                GeneralAlert alert = new GeneralAlert(prod, alertType);
                String jsonProduct = gson.toJson(alert);

                HttpRequest postReq = HttpRequest.newBuilder()
                        .uri(new URI("https://api.marketalertum.com/Alert"))
                        .header("content-type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(jsonProduct))
                        .build();

                HttpClient client = HttpClient.newHttpClient();
                HttpResponse<String> resp = client.send(postReq, HttpResponse.BodyHandlers.ofString());
                System.out.println(resp);
                responses.add(resp);
            }
            return responses;
        }else{
            return null;
        }
    }

    public HttpResponse<String> deleteProducts() throws Exception{
        Gson gson = new Gson();
        List<HttpResponse<String>> responses = new ArrayList<>();

        HttpRequest postReq = HttpRequest.newBuilder()
                .uri(new URI("https://api.marketalertum.com/Alert?userId=c483fe67-d39d-429a-9afa-273d26d2fe35"))
                .header("content-type", "application/json")
                .DELETE()
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> resp = client.send(postReq, HttpResponse.BodyHandlers.ofString());
        System.out.println(resp);

        return resp;
    }


}
