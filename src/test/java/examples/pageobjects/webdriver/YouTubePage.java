package examples.pageobjects.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class YouTubePage {
    private WebDriver webDriver;

    @FindBy(id = "masthead-search-term")
    private WebElement searchField;

    public YouTubePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void searchFor(String searchCriteria) {
        searchField.sendKeys(searchCriteria);
        searchField.submit();
    }

    public boolean at() {
        return webDriver.getTitle().contains("YouTube");
    }

    public WebElement getSearchField() {
        return searchField;
    }

    public String getSearchFieldInput() {
        return searchField.getAttribute("value");
    }
}

