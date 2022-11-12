package test.marketalertum.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Alert {

    WebDriver driver;
    WebElement alertBody;
    public String iconLink;

    public Alert(WebElement element){
        alertBody = element;
    }

    public boolean hasIcon(){
        try {
            WebElement icon = alertBody.findElement(By.tagName("img"));
            iconLink = icon.getAttribute("src");
            return iconLink.contains(".png");
        }catch (Exception e){
            return false;
        }
    }

    public void getIcon(){
        try {
            WebElement icon = alertBody.findElement(By.tagName("img"));
            iconLink = icon.getAttribute("src");
        }catch (Exception ignored){}
    }

    public boolean hasImage(){
        try {
            List<WebElement> images = alertBody.findElements(By.tagName("img"));
            return images.get(1).getAttribute("src").contains(".jpg");
        }catch (Exception e){
            return false;
        }
    }

    public boolean hasHeader(){
        try {
            WebElement header = alertBody.findElement(By.tagName("h4"));
            return !header.getText().isBlank();
        }catch (Exception e){
            return false;
        }
    }

    public boolean hasDescription(){
        try {
            List<WebElement> sections = alertBody.findElements(By.tagName("td"));
            return !sections.get(2).getText().isBlank();
        }catch (Exception e){
            return false;
        }
    }

    public boolean hasPrice(){
        try {
            List<WebElement> sections = alertBody.findElements(By.tagName("td"));
            return !sections.get(3).getText().isBlank();
        }catch (Exception e){
            return false;
        }
    }

    public boolean hasLink(){
        try {
            WebElement link = alertBody.findElement(By.tagName("a"));
            return link.getAttribute("href").contains("https://");
        }catch (Exception e){
            return false;
        }
    }
}
