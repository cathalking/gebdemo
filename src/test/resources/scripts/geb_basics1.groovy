@Grapes([
   @Grab(group='org.gebish', module='geb-core', version='0.10.0'),    
   @Grab(group='org.seleniumhq.selenium', module='selenium-firefox-driver', version='2.44.0'),
   @Grab(group='org.seleniumhq.selenium', module='selenium-support', version='2.44.0')
   ])

import geb.Browser
import org.openqa.selenium.WebDriver
import org.openqa.selenium.firefox.FirefoxDriver

// This is a Groovy script. Notice how we do do not need the usual 
// "class-related" boilerplate: i.e. class MyClass {  public static void main(String[] args) { // code snippet };

// "Hello world!" - with plain old WebDriver 

WebDriver driver = new FirefoxDriver()
driver.get("http://www.duckduckgo.com")
assert driver.getTitle() == "DuckDuckGo"



// "Hello world!" - with Geb

Browser.drive {
    go "http://www.duckduckgo.com"
    assert title == "DuckDuckGo"
}


// Re-written with a little less magic

// "Hello world!" - Geb + Groovy "tricks" explained
// It's all just WebDriver "under the hood"

Browser browser = new Browser() // By default it'll "new up" a FirefoxDriver()
browser.getDriver().get("http://www.duckduckgo.com")
assert browser.getDriver().getTitle() == "DuckDuckGo"



// Groovy syntactic sugar
// Groovy-ism: Natural way to re-write the same sequence in Groovy. 
Browser browser = new Browser()
browser.driver.get("http://www.duckduckgo.com")
assert browser.driver.title == "DuckDuckGo"


// How does Geb know what "go" and "title" refer to?
// Geb leverage's Groovy's "methodMissing" support
// to "dynamically forward" to the correct underlying class


// From this:
Browser browser = new Browser()
browser.driver.get("http://www.duckduckgo.com")
assert browser.driver.title == "DuckDuckGo"

// To this:
Browser.drive {
    go "http://www.duckduckgo.com"
    assert title == "DuckDuckGo"
}

// Groovy + Geb are smart enough to resolve:
//    "go" to browser.go(String url)
//    "title" to browser.getDriver().getTitle()
//    Little touches that combine to keep the tests Natural-Language-like
//    Keeps boiler-plate code to a bare-minimum

Browser browser = new Browser() // By default it'll "new up" a FirefoxDriver()
browser.getDriver().get("http://www.duckduckgo.com")
assert browser.getDriver().getTitle() == "DuckDuckGo"