package examples.junit

import examples.pageobjects.geb.DuckDuckGoHomePage
import examples.pageobjects.geb.YouTubePage
import geb.Browser
import geb.junit4.GebTest
import org.junit.Test

class GebJUnitTestAdapterExampleTest extends GebTest {

    @Test
    void testDriveTheBrowser() {
        Browser.drive {
            go "http://www.wikipedia.org"
            assert title == "Wikipedias"
        }
    }

    @Test
    void testDriveTheBrowser2() {
        go "http://www.wikipedia.org"
        assert title == "Wikipedia"
    }

    @Test
    void pageObjectUsage() {
        to DuckDuckGoHomePage
        searchField = "!yt geb selenium webdriver groovy"
        searchBtn.click()

        at YouTubePage
        assert searchField == "geb selenium webdriver groovy"
    }

    @Test
    void pageObjectUsage2() {
        to DuckDuckGoHomePage
        String searchTerm = "geb selenium webdriver groovy"
        searchFor "!yt ${searchTerm}"
        at YouTubePage
        assert title == "${searchTerm} - YouTube"
        assert searchField == "${searchTerm}"
    }

}
