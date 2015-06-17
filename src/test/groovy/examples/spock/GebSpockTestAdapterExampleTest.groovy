package examples.spock

import examples.pageobjects.geb.DuckDuckGoHomePage
import examples.pageobjects.geb.DuckDuckGoHomePageV2
import examples.pageobjects.geb.DuckDuckGoResultsPage
import examples.pageobjects.geb.DuckDuckGoResultsPageV2
import examples.pageobjects.geb.DuckDuckGoResultsPageV3
import examples.pageobjects.geb.YouTubePage
import geb.spock.GebReportingSpec
import spock.lang.Unroll

class GebSpockTestAdapterExampleTest extends GebReportingSpec {

    /**
     * More Natural Language support:
     * 1. "Plain text 'test' names"
     * 2. Implicit assertions. No need for 'assert' keyword
     * 3. Plain text given/when/then comments can be pulled out to build detailed test reports
     * 4. Powerful support for Data-Driven tests
     * 5. Lots of other features supporting functional testing + unit testing.
     */

    def "PageObject example - using page elements"() {
        given:
        to DuckDuckGoHomePage

        when:
        searchField = "!yt geb selenium webdriver groovy"
        searchBtn.click()

        then:
        at YouTubePage
        searchField == "geb selenium webdriver groovy"
    }

    def "PageObject example - using 'domain' methods"() {
        given:
        to DuckDuckGoHomePage

        when:
        searchFor "!yt geb selenium webdriver groovy"

        then:
        at YouTubePage
        searchField == "geb selenium webdriver groovy"
    }

    def "PageObject example - using Modules - Latest version of Geb-Spock module is 0.10.0 - "() {
        given:
        to DuckDuckGoHomePageV2

        when:
        search.searchFor "maven geb*"
        at DuckDuckGoResultsPageV2

        then:
        mavenArtifacts()["geb-spock"].version == "0.10.0"
    }


    // 4. Powerful support for Data-Driven tests

    @Unroll("DuckDuckGo goes straight to #expectedSite when search starts with shortcut #bangShortcut")
    def "Lands on expected site when using DuckDuckGo 'Bang' shortcut"() {
        given:
        to DuckDuckGoHomePage

        when:
        searchFor "${bangShortcut} Anything"

        then:
        title ==~ expectedSiteTitle

        where:
        bangShortcut | expectedSiteTitle | expectedSite
        "!yt"        | /.*YouTube.*/     | "YouTube"
        "!a"         | /.*Amazon.*/      | "Amazon"
        "!w"         | /.*Wikipedia.*/   | "Wikipedia"
        "!g"         | /.*Google.*/      | "Google"
    }


    def "Modelling complex content - And showing Spock Reporting capabilities"() {
        given: "I'm at the DuckDuckGo homepage"
        to DuckDuckGoHomePageV2

        when: "I search for 'maven'"
        search.searchFor "maven geb*"
        DuckDuckGoResultsPageV3 resultsPage = at DuckDuckGoResultsPageV3

        then: "Top result is maven.org"
        resultsPage.topResult == "http://search.maven.org/"
//        resultLink(0) == "http://search.maven.org/"
    }

    def "PageObject - using modules"() {
        given:
        to DuckDuckGoHomePageV2

        when:
        search.field = "!yt geb selenium webdriver groovy"
        search.button.click()

        then:
        at YouTubePage
        searchField == "geb selenium webdriver groovy"
    }

    def "PageObject usage 2"() {
        given:
        to DuckDuckGoHomePage

        when:
        String searchTerm = "geb selenium webdriver groovy"
        searchFor "!yt ${searchTerm}"

        then:
        at YouTubePage
        title == "${searchTerm} - YouTube"
        searchField == "${searchTerm}"
    }

    def "PageObject usage 3"() {
        given:
        DuckDuckGoHomePage homePage = to DuckDuckGoHomePage

        when:
        String searchTerm = "geb selenium webdriver groovy"
        homePage.searchFor "!yt ${searchTerm}"

        then:
        YouTubePage ytPage = at YouTubePage
        title == "${searchTerm} - YouTube"
        ytPage.searchField == "${searchTerm}"
    }

    @Unroll
    def "Search criteria appear in title when searching for '#searchTerm'"(String searchTerm) {
        given:
        to DuckDuckGoHomePage

        when:
        searchFor "!yt ${searchTerm}"

        then:
        at YouTubePage
        title == "${searchTerm} - YouTube"

        where:
        searchTerm << ["geb selenium webdriver groovy",
                       "java selenium webdriver pagefactory"]

    }


    def "Latest version of Geb-Spock module is 0.10.0"() {
        given:
        to DuckDuckGoHomePage

        when:
        searchFor "maven geb*"
        DuckDuckGoResultsPage resultsPage = at DuckDuckGoResultsPage

        then:
        resultsPage.mavenArtifacts()["geb-spock"].version == "0.10.0"
    }

    def "Latest version of Geb-Spock module is 0.10.0 - V2"() {
        given:
        to DuckDuckGoHomePageV2

        when:
        search.searchFor "maven geb*"
        DuckDuckGoResultsPageV2 resultsPage = at DuckDuckGoResultsPageV2

        then:
        resultsPage.mavenArtifacts()["geb-spock"].version == "0.10.0"
    }



}
