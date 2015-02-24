package examples.pageobjects.geb

import geb.Page

class DuckDuckGoHomePage extends Page {

    static url = "https://duckduckgo.com"

    static at = {
        title == "DuckDuckGo"
        $("#content_homepage")
        // assert getTitle() == "DuckDuckGo"
        // assert $("#content_homepage") != null
    }

    static content = {
        searchField { $("input", name: "q") }
        searchBtn { $("#search_button_homepage") }
    }

    def searchFor (String searchCriteria) {
        searchField = searchCriteria
        searchBtn.click()
    }
}
