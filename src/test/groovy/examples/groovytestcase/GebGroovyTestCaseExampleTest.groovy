package examples.groovytestcase

import geb.Browser

class GebGroovyTestCaseExampleTest extends GroovyTestCase {

    void testArithmeticStillWorks1() {
        assert 1 + 2 == 3
    }

    void testArithmeticStillWorks2() {
        assert 2 + 2 == 4
    }

    void testBrowserDrive() {
        Browser.drive {
            go "http://www.wikipedia.org"
        }
    }
}
