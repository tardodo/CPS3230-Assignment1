package com.screenscraper.components.pageobjects;

import org.openqa.selenium.WebDriver;

import java.util.List;

public interface ECommerceSite {
    public void setDriver(WebDriver newDriver);
    public boolean searchBar(String search);
    public boolean applyFilter();
    public List<Product> getProducts();
}
