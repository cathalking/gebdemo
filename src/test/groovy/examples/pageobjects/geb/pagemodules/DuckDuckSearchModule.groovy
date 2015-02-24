package examples.pageobjects.geb.pagemodules

import geb.Module

class DuckDuckSearchModule extends Module {

    static content = {
        field { $("input[name=q]") }
        button { $("#search_button_homepage")}
    }

    def searchFor (String searchCriteria) {
        field = searchCriteria
        button.click()
    }
}
