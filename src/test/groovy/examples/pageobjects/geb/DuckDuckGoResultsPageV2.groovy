package examples.pageobjects.geb

import examples.pageobjects.geb.pagemodules.DuckDuckSearchModule
import geb.Page

class DuckDuckGoResultsPageV2 extends Page {

    static at = { title ==~ /.*DuckDuckGo.*/ }

    static content = {
        search { module DuckDuckSearchModule }
        mavenTable(required: false) { $(".c-base__content > table:nth-child(1) > tbody:nth-child(1)") }
    }

    Map mavenArtifacts() {
        def headers = mavenTable.find("tr:nth-child(1) > th")*.text()
        mavenTable.find("tr:not(:first-child)").collectEntries {
            def moduleDetails = it.find("td")*.text()
            [(moduleDetails[1]): [(headers[0]): moduleDetails[0], (headers[1]): moduleDetails[1], (headers[2]): moduleDetails[2]]]
        }
    }

}

