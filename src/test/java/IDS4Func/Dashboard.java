/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IDS4Func;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author prabakar
 */
public class Dashboard extends PageObject {

    long timeoutInSeconds = 90;
    WebElement myDynamicElement;

    @FindBy(how = How.XPATH, using = "//strong[@ng-bind='summary.PublishedIssuedRecent']")
    public WebElement countRecentlyPublishedApplication;

    @FindBy(how = How.XPATH, using = "//strong[@ng-bind='summary.RecentAddedRecords']")
    public WebElement countNewlyAddedRecords;

    @FindBy(how = How.XPATH, using = "//strong[@ng-bind='summary.RIARecent']")
    public WebElement associatedStateLastWeek;

    @FindBy(how = How.XPATH, using = "//strong[@ng-bind='summary.RIA']")
    public WebElement associatedState;

    @FindBy(how = How.XPATH, using = "//label[@ng-bind='summary.Issued']")
    public WebElement countIssuedApplication;

    @FindBy(how = How.XPATH, using = "//label[@ng-bind='summary.Published']")
    public WebElement countPublishedApplication;

    @FindBy(how = How.XPATH, using = "//label[@ng-bind='summary.Filed']")
    public WebElement countFiledApplication;

    @FindBy(how = How.ID, using = "txtfulltextsearch")
    public WebElement fullTextSearchTextBox;

    @FindBy(how = How.ID, using = "btnSearch")
    public WebElement searchSubmit;

    public String getRecentlyPublishedCount() {
        myDynamicElement = (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.elementToBeClickable(countRecentlyPublishedApplication));
        new WebDriverWait(driver, timeoutInSeconds)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.id("divloading")));
        String pubCount = countRecentlyPublishedApplication.getText();
        clickCount(countRecentlyPublishedApplication);
        System.out.println("published count" + pubCount);
        return pubCount;
    }

    public String getNewlyAddedRecordCount() {
        myDynamicElement = (new WebDriverWait(driver, 30))
                .until(ExpectedConditions.elementToBeClickable(countNewlyAddedRecords));
        new WebDriverWait(driver, timeoutInSeconds)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.id("divloading")));
        String newCount = countNewlyAddedRecords.getText();
        clickCount(countNewlyAddedRecords);
        System.out.println("new record count" + newCount);
        return newCount;
    }

    public String getAssociatedAssetLastWeek() {
        myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(associatedStateLastWeek));
        new WebDriverWait(driver, timeoutInSeconds).until(ExpectedConditions.invisibilityOfElementLocated(By.id("divloading")));
        String associatedCount = associatedStateLastWeek.getText();
        clickCount(associatedStateLastWeek);
        System.out.println("associated last Week count" + associatedCount);
        return associatedCount;
    }

    public String getAssociatedAsset() {
        myDynamicElement = (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable(associatedState));
        new WebDriverWait(driver, timeoutInSeconds).until(ExpectedConditions.invisibilityOfElementLocated(By.id("divloading")));
        String associatedCount = associatedState.getText();
        clickCount(associatedState);
        System.out.println("associated count" + associatedCount);
        return associatedCount;
    }

    public String getIssuedCount() {
        String issueCount = countIssuedApplication.getText();
        System.out.println("issue count" + issueCount);
        return issueCount;
    }

    public String getPublishedCount() {
        String publiCount = countPublishedApplication.getText();
        System.out.println("publi count" + publiCount);
        return publiCount;
    }

    public String getFiledCount() {
        // driver.manage().timeouts().implicitlyWait(5, TimeUnit.MINUTES);
        String filedCount = countFiledApplication.getText();
        System.out.println("filed count" + filedCount);
        return filedCount;
    }

    public SearchList clickCount(WebElement element) {
        new WebDriverWait(driver, timeoutInSeconds)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.id("divloading")));
        element.click();
        new WebDriverWait(driver, timeoutInSeconds)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.id("divloading")));

        return new SearchList(driver);
    }

    public SearchList submitFullTextSearchQueryFromDashboard() {
        pleaseWaitForPageLoad();
        searchSubmit.click();
        return new SearchList(driver);
    }

    public PatPubDetailView submitSingleSearch() {
        pleaseWaitForPageLoad();
        searchSubmit.click();
        return new PatPubDetailView(driver);
    }

    public void enterFullTextSearchQuery(String query) {
        pleaseWaitForPageLoad();
        this.fullTextSearchTextBox.clear();
        this.fullTextSearchTextBox.sendKeys(query);
        pleaseWaitForPageLoad();
    }

    public Dashboard(WebDriver driver) {
        super(driver);
    }

    public void pleaseWaitForPageLoad() {
        new WebDriverWait(driver, timeoutInSeconds)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.id("divloading")));
    }
}
