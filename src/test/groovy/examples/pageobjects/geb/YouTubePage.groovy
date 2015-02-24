package examples.pageobjects.geb

import geb.Page

class YouTubePage extends Page {

    static url = "https://www.youtube.com"
    static at = { title ==~ /.*YouTube.*/  }

    static content = {
        searchField { $("#masthead-search-term") }
    }
}
