package examples.junit;

import examples.pageobjects.geb.DuckDuckGoResultsPageV2;
import examples.pageobjects.webdriver.DuckDuckGoResultsPage;
import examples.pageobjects.webdriver.YouTubePage;
import examples.pageobjects.webdriver.DuckDuckGoHomePage;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WebDriverExampleTest {
    WebDriver driver = new FirefoxDriver();

    @After
    public void cleanup() {
        driver.close();
    }

    @Ignore // Show what test failure reporting looks like with JUnit's plain old assertions (as opposed to Groovy's "power asserts")
    @Test
    public void testDriveTheBrowser() {
        driver.get("http://www.wikipedia.org");
        assertEquals(driver.getTitle(), "Wikipedias");
    }

    @Test
    public void pageObjectUsage() {
        DuckDuckGoHomePage homePage = PageFactory.initElements(driver, DuckDuckGoHomePage.class);
        homePage.go();
        homePage.searchFor("!yt geb selenium webdriver groovy");

        YouTubePage youTubePage = PageFactory.initElements(driver, YouTubePage.class);
        assertTrue(youTubePage.at());
        assertEquals(youTubePage.getSearchFieldInput(), "geb selenium webdriver groovy");
    }

    @Test
    public void latestVersionGeb_Spock_is_0_10_0 () {
        DuckDuckGoHomePage homePage = PageFactory.initElements(driver, DuckDuckGoHomePage.class);
        homePage.go();
        homePage.searchFor("maven geb*");

        DuckDuckGoResultsPage resultsPage = PageFactory.initElements(driver, DuckDuckGoResultsPage.class);
        assertTrue(resultsPage.at());
        assertEquals(resultsPage.mavenArtifacts().get("geb-spock").get("version"), "0.10.0");
   }

    @Test
    public void latestVersionGeb_Spock_is_0_10_0_Java8Impl () {
        DuckDuckGoHomePage homePage = PageFactory.initElements(driver, DuckDuckGoHomePage.class);
        homePage.go();
        homePage.searchFor("maven geb*");

        DuckDuckGoResultsPage resultsPage = PageFactory.initElements(driver, DuckDuckGoResultsPage.class);
        assertTrue(resultsPage.at());
        assertEquals(resultsPage.mavenArtifactsJava8Impl().get("geb-spock").getVersion(), "0.10.0");
    }

    @Test
    public void searchResultsExample() {
        DuckDuckGoHomePage homePage = PageFactory.initElements(driver, DuckDuckGoHomePage.class);
        homePage.go();
        homePage.searchFor("maven geb*");

        DuckDuckGoResultsPage resultsPage = PageFactory.initElements(driver, DuckDuckGoResultsPage.class);
        assertTrue(resultsPage.at());
        assertEquals(resultsPage.topResult(), "http://search.maven.org/");
    }

}
