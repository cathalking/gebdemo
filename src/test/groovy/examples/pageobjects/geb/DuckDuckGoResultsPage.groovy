package examples.pageobjects.geb

class DuckDuckGoResultsPage extends geb.Page {

    static at = { title ==~ /.*DuckDuckGo.*/  }

    static content = {
        searchField { $("input[name=q]") }
        searchBtn { $("#search_button_homepage")}
        mavenTable(required: false
        ) { $(".zci__content > table:nth-child(1) > tbody:nth-child(1)") }
    }

    def searchFor (String searchCriteria) {
        searchField = searchCriteria
        searchBtn.click()
    }

    Map mavenArtifacts() {
        def headers = mavenTable.find("tr:nth-child(1) > th")*.text()
        mavenTable.find("tr:not(:first-child)").collectEntries {
            def moduleDetails = it.find("td")*.text()
            [(moduleDetails[1]): [(headers[0]): moduleDetails[0], (headers[1]): moduleDetails[1], (headers[2]): moduleDetails[2]]]
        }
    }
}
