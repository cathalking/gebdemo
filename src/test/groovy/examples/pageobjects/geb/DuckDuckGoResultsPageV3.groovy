package examples.pageobjects.geb

import examples.pageobjects.geb.pagemodules.DuckDuckSearchModule
import geb.Page

class DuckDuckGoResultsPageV3 extends Page {

    static at = { title.contains "DuckDuckGo" }

    static content = {
        search { module DuckDuckSearchModule }
        mavenTable(required: false) { $(".zci__content > table:nth-child(1) > tbody:nth-child(1)")  }

        results { $("#links") }
        result { i -> results[i] }
        resultLink { i -> result(i).$("a.result__a").@href }
        topResult { resultLink(0) }
    }

    Map mavenArtifacts() {
        mavenTable.find("tr:not(:first-child)").collectEntries {
            def moduleDetails = it.find("td")*.text()
            [(moduleDetails[1]): [groupId: moduleDetails[0], artifactId: moduleDetails[1], version: moduleDetails[2]]]
        }
    }

}
