package com.screenscraper.components.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class AlibabaSite implements ECommerceSite{

    WebDriver driver;

    public AlibabaSite(){
        driver = null;
    }

    @Override
    public void setDriver(WebDriver newDriver) {
        driver = newDriver;
    }

    @Override
    public boolean searchBar(String search) {
        try {
            WebElement searchField = driver.findElement(By.name("SearchText"));
            searchField.sendKeys(search);
            WebElement searchButton = driver.findElement(By.className("ui-searchbar-submit"));
            searchButton.submit();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean applyFilter(){
        try {
            WebElement oledFilter = driver.findElement(By.className("cpv-refine-filter-item__wrapper"));
            oledFilter.click();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<Product> getProducts() {
        List<WebElement> productList = driver.findElements(By.className("traffic-product-card"));
        List<Product> refinedProducts = new ArrayList<>();

        for(WebElement prod: productList){
            Product refinedProd = new Product(driver);
            refinedProducts.add(refinedProd.setAttributes(prod));
        }
        return refinedProducts;
    }
}
