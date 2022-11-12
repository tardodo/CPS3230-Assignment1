package test.marketalertum;


import com.screenscraper.components.pageobjects.AlibabaSite;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import test.marketalertum.pageobjects.Alert;
import test.marketalertum.pageobjects.MarketAlertUM;
import test.marketalertum.scraper.ModScraper;

public class MarketalertumSteps {

    WebDriver driver;
    MarketAlertUM site;

    @After
    public void teardown(){
        driver.quit();
    }

    @Given("I am a user of marketalertum")
    public void iAmAUserOfMarketalertum() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\farad\\Documents\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        site = new MarketAlertUM(driver);
    }

    @When("I login using valid credentials")
    public void iLoginUsingValidCredentials() {
        site.navToLogin();
        site.login("c483fe67-d39d-429a-9afa-273d26d2fe35");
    }

    @Then("I should see my alerts")
    public void iShouldSeeMyAlerts() {
        Assertions.assertTrue(driver.getCurrentUrl().contains("/Alerts/List"));
    }

    @When("I login using invalid credentials")
    public void iLoginUsingInvalidCredentials() {
        site.navToLogin();
        site.login("blabla");
    }

    @Then("I should see the login screen again")
    public void iShouldSeeTheLoginScreenAgain() {
        Assertions.assertTrue(driver.getCurrentUrl().contains("/Alerts/Login"));
    }

    @Given("I am an administrator of the website and I upload {int} alerts")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAlerts(int arg0) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\farad\\Documents\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        AlibabaSite site = new AlibabaSite();

        ModScraper scraper = new ModScraper(driver, site);
        scraper.goToSite("https://www.alibaba.com/");
        scraper.searchForSmartphone();
        scraper.retrieveRequiredProducts(arg0);
        scraper.quitDriver();

        try {
            scraper.deleteProducts();
            scraper.uploadToAPI(6);
        }catch (Exception ignored){}

    }

    @When("I view a list of alerts")
    public void iViewAListOfAlerts() {
        site.goToAlerts();
        site.login("c483fe67-d39d-429a-9afa-273d26d2fe35");
        site.fillAlertPane();
    }

    @Then("each alert should contain an icon")
    public void eachAlertShouldContainAnIcon() {
        for(Alert al: site.alertPane){
            Assertions.assertTrue(al.hasIcon());
        }
    }

    @And("each alert should contain a heading")
    public void eachAlertShouldContainAHeading() {
        for(Alert al: site.alertPane){
            Assertions.assertTrue(al.hasHeader());
        }
    }

    @And("each alert should contain a description")
    public void eachAlertShouldContainADescription() {
        for(Alert al: site.alertPane){
            Assertions.assertTrue(al.hasDescription());
        }
    }

    @And("each alert should contain an image")
    public void eachAlertShouldContainAnImage() {
        for(Alert al: site.alertPane){
            Assertions.assertTrue(al.hasImage());
        }
    }

    @And("each alert should contain a price")
    public void eachAlertShouldContainAPrice() {
        for(Alert al: site.alertPane){
            Assertions.assertTrue(al.hasPrice());
        }
    }

    @And("each alert should contain a link to the original product website")
    public void eachAlertShouldContainALinkToTheOriginalProductWebsite() {
        for(Alert al: site.alertPane){
            Assertions.assertTrue(al.hasLink());
        }
    }

    @Given("I am an administrator of the website and I upload more than {int} alerts")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadMoreThanAlerts(int arg0) {
        ++arg0;

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\farad\\Documents\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        AlibabaSite site = new AlibabaSite();

        ModScraper scraper = new ModScraper(driver, site);
        scraper.goToSite("https://www.alibaba.com/");
        scraper.searchForSmartphone();
        scraper.retrieveRequiredProducts(arg0);
        scraper.quitDriver();

        try {
            scraper.deleteProducts();
            scraper.uploadToAPI(6);
        }catch (Exception ignored){}
    }

    @Then("I should see {int} alerts")
    public void iShouldSeeAlerts(int arg0) {
        Assertions.assertEquals(arg0, site.alertPane.size());
    }


    @Given("I am an administrator of the website and I upload an alert of type {int}")
    public void iAmAnAdministratorOfTheWebsiteAndIUploadAnAlertOfType(int arg0) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\farad\\Documents\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        AlibabaSite site = new AlibabaSite();

        ModScraper scraper = new ModScraper(driver, site);
        scraper.goToSite("https://www.alibaba.com/");
        scraper.searchForSmartphone();
        scraper.retrieveRequiredProducts(1);
        scraper.quitDriver();

        try {
            scraper.deleteProducts();
            scraper.uploadToAPI(arg0);
        }catch (Exception ignored){}
    }

    @And("the icon displayed should be {string}")
    public void theIconDisplayedShouldBe(String arg0) {
        Alert alert = site.alertPane.get(0);
        alert.getIcon();
        Assertions.assertTrue(alert.iconLink.contains(arg0));

    }
}
