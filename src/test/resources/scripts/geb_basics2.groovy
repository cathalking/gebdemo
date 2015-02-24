@Grapes([
   @Grab(group='org.gebish', module='geb-core', version='0.10.0'),
   @Grab(group='org.seleniumhq.selenium', module='selenium-firefox-driver', version='2.44.0'),
   @Grab(group='org.seleniumhq.selenium', module='selenium-support', version='2.44.0')
   ])

import geb.Browser
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver

// More Geb scripting examples

// The Navigator API - "jQuery-inspired" content selection
// The "find" method a.k.a "$"

Browser.drive {
    // $(«css selector», «index or range», «attribute / text matchers»)

    println find("#links h2.result__title").size()
        
    println $("#links h2.result__title").size()
    // The results are iterable: 29 results

    println $("#links h2.result__title", 0..10)
    // Returns the first 10 matching WebElements

    println $("#links h2.result__title", 0).text()
    // But defaults to returning the text of the *first* matching WebElement

    println $("#links h2.result__title", 0..10)*.text()
    // Returns the text of the first 10 matching WebElements
    
    println $("#links h2.result__title", 0..10).each { elem -> elem.text() }
    // Spread operator is shorthand for this each/closure

    println $("#links h2.result__title", text: contains("Cucumber")).text()
    // Return text of the first matching element that also mentions "Cucumber"
    
    println $("#links h2.result__title > a", href: notContains("github"))*.@href
    // Exclude github links
    
    
}


// The Navigator API - "Traversing" nodes + "Chaining" calls

Browser.drive {
    // $(«css selector», «index or range», «attribute / text matchers»)    
    println $("#links div.result", 1).$("h2").text()
    // geb/geb-example-maven · GitHub
    println $("#links div.result", 1).previous().$("h2").text()
    // maven central
    println $("#links div.result", 0).$("h2").text()
    
    println $("#links div.result", 0).nextAll().$("h2")*.text()
    // Exclude the first search result text
    println $("#links div.result", 2).siblings()*.text()
    // Print links 1 + 3
    
    // And more
    println $("#links div.result", 2).children()*.text()
}

// Geb Shortcuts for working with Forms

Browser.drive {
    go "http://www.duckduckgo.com"
        
    $("form").q = "maven geb*"
    // Within the form element use the field named "q"
    // Equivalent to these chained calls:
    //$("form").find("input", name: "q").value("maven geb*")
    
    $("#search_button_homepage").click()
}

