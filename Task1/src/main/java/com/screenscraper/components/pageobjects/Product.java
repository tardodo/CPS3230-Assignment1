package com.screenscraper.components.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.regex.Pattern;

public class Product {


    WebDriver driver;
    public String productImage;

    public String productLink;

    public int productPrice;

    public String productTitle;

    public String productDescription;

    public Product(){

    }
    public Product(WebDriver newDriver){
        driver = newDriver;
    }

    public Product setAttributes(WebElement element){

        try {
            WebElement image = element.findElement(By.className("seb-img-switcher__imgs"));
            productImage = image.getAttribute("data-image");
            productImage = "https:" + productImage;

            WebElement info = element.findElement(By.className("elements-title-normal"));
            productTitle = info.getAttribute("href").split("product-detail/")[1].split("_")[0];
            productTitle = productTitle.replace("-", " ");
            productDescription = info.getAttribute("title");
            productLink = info.getAttribute("href");
            WebElement price = null;
            try {
                price = element.findElement(By.className("elements-offer-price-normal__promotion"));

            } catch (Exception e) {
                try {
                    price = element.findElement(By.className("elements-offer-price-normal__price"));
                } catch (Exception ex) {
                    return null;
                }
            }
            String prodPrice = price.getText();

            if (prodPrice.contains("-")) {
                prodPrice = prodPrice.split("-")[0];
            }
            prodPrice = prodPrice.replaceAll(Pattern.quote("US$"), "");
            prodPrice = prodPrice.replaceAll(Pattern.quote("."), "");
            productPrice = Integer.parseInt(prodPrice);


            return this;
        }catch (Exception e){
            return null;
        }
    }
}
