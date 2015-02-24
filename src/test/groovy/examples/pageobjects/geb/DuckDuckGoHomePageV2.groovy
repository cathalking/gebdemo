package examples.pageobjects.geb

import examples.pageobjects.geb.pagemodules.DuckDuckSearchModule
import geb.Page

class DuckDuckGoHomePageV2 extends Page {

    static url = "https://duckduckgo.com"

    static at = {
        title == "DuckDuckGo"
        about
    }

    static content = {
        search { module DuckDuckSearchModule }
        about { $("#content_homepage") }
    }

}
