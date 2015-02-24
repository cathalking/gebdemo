import org.openqa.selenium.firefox.FirefoxDriver

import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver

// default is to use firefox
driver = { new FirefoxDriver() }


// e.g. mvn clean test -Dgeb.env=win-ie
/*
environments {
    // when system property 'geb.env' is set to 'win-ie' use a remote IE driver
    'win-ie' {
        driver = {
            new RemoteWebDriver(new URL("http://windows.ci-server.local"), DesiredCapabilities.internetExplorer())
        }
    }
    'chrome' {
        //driver = { .... }
    }
}
*/

// unexpectedPages = [PageNotFoundPage, InternalServerErrorPage]

/*
waiting {
    timeout = 10
    retryInterval = 0.5
}
*/


