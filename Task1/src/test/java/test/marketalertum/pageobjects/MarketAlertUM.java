package test.marketalertum.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class MarketAlertUM {

    public WebDriver driver;
    public List<Alert> alertPane;

    public MarketAlertUM(){
        driver=null;
        alertPane = new ArrayList<>();
    }

    public MarketAlertUM(WebDriver newDriver){
        driver = newDriver;
        driver.get("https://www.marketalertum.com/");
        alertPane = new ArrayList<>();
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void goToSite(){
        driver.get("https://www.marketalertum.com/");
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    public void navToLogin(){
        List<WebElement> topBarOptions = driver.findElements(By.tagName("a"));
        WebElement login = topBarOptions.get(3);
        login.click();

    }

    public boolean login(String credentials){
        WebElement inputField = driver.findElement(By.id("UserId"));
        inputField.sendKeys(credentials);
        inputField.submit();
        String status = driver.getCurrentUrl();

        return status.contains("/Alerts/List");
    }

    public void goToAlerts(){
        List<WebElement> topBarOptions = driver.findElements(By.tagName("a"));
        WebElement alerts = topBarOptions.get(2);
        alerts.click();
    }

    public void fillAlertPane(){
        List<WebElement> alerts = driver.findElements(By.tagName("tbody"));
        for(WebElement el: alerts){
            alertPane.add(new Alert(el));
        }
    }

}
