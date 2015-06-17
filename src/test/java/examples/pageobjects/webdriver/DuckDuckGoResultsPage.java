package examples.pageobjects.webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DuckDuckGoResultsPage {

    public boolean at() {
        return webDriver.getTitle().contains("DuckDuckGo");
    }

    @FindBy(name = "q")
    private WebElement searchField;

    public void searchFor(String searchCriteria) {
        searchField.sendKeys(searchCriteria);
        searchField.submit();
    }

    private WebDriver webDriver;

    public DuckDuckGoResultsPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public List<WebElement> results() {
        return webDriver.findElements(By.cssSelector("#links div.result"));
    }

    public WebElement result(int i) {
        return results().get(i);
    }

    public String resultLink(int i) {
        return result(i).findElement(By.cssSelector("a.result__a")).getAttribute("href");
    }

    public String topResult() {
        return resultLink(0);
    }

    public Map<String, Map<String, String>> mavenArtifacts() {
        List<WebElement> moduleDetailsElems = webDriver.findElements(By.cssSelector(".c-base__content > table:nth-child(1) > tbody:nth-child(1) > tr:not(:first-child)"));
        Map<String, Map<String, String>> artifactsMap = new HashMap<>();
        for (WebElement tableRow : moduleDetailsElems) {
            List<String> rowVals = new ArrayList<>();
            for(WebElement tableCell : tableRow.findElements(By.tagName("td"))) {
                rowVals.add(tableCell.getText());
            }
            artifactsMap.put(rowVals.get(1), new HashMap<String, String>() {{
                                                    put("groupId", rowVals.get(0));
                                                    put("artifactId", rowVals.get(1));
                                                    put("version", rowVals.get(2)); }});
        }
        return artifactsMap;
    }

    // Any Java 8 specific methods are shown below

    public Map<String, ModuleDetails> mavenArtifactsJava8Impl() {
        List<WebElement> moduleDetailsElems = webDriver.findElements(By.cssSelector(".c-base__content > table:nth-child(1) > tbody:nth-child(1) > tr:not(:first-child)"));
        return moduleDetailsElems.stream()
                .map(tableRow -> {
                    List<String> rowVals =
                            tableRow.findElements(By.tagName("td")).stream()
                                    .map(tableCell -> tableCell.getText())
                                    .collect(Collectors.toList());
                    return new ModuleDetails(rowVals.get(1), rowVals.get(0), rowVals.get(2));
                })
                .collect(Collectors.toMap(ModuleDetails::getArtifactId, md -> md));
    }

    public static class ModuleDetails {
        public ModuleDetails(String artifactId, String groupId, String version) {
            this.artifactId = artifactId;
            this.groupId = groupId;
            this.version = version;
        }

        public String getArtifactId() {
            return artifactId;
        }

        public String getGroupId() {
            return groupId;
        }

        public String getVersion() {
            return version;
        }

        String artifactId;
        String groupId;
        String version;

        @Override
        public String toString() {
            return "ModuleDetails{" +
                    "artifactId='" + artifactId + '\'' +
                    ", groupId='" + groupId + '\'' +
                    ", version='" + version + '\'' +
                    '}';
        }
    }
}

