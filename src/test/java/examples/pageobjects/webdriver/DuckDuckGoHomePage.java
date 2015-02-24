package examples.pageobjects.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.junit.Assert.assertTrue;

public class DuckDuckGoHomePage {

    static String url = "https://duckduckgo.com";
    public boolean at() {
        return webDriver.getTitle().contains("DuckDuckGo") &&
                webDriver.findElement(By.id("content_homepage")).isDisplayed();
    }

    @FindBy(name = "q") private WebElement searchField;

    public void searchFor(String searchCriteria) {
        searchField.sendKeys(searchCriteria);
        searchField.submit();
    }

    private WebDriver webDriver;
    public DuckDuckGoHomePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void go() {
        webDriver.get(url);
        assertTrue(at());
    }

}

