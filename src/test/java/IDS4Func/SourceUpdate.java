/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IDS4Func;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author prabakar
 */
public class SourceUpdate extends PageObject {

    @FindBy(how = How.ID, using = "txtSourceDate")
    public WebElement sourceDate;

    @FindBy(how = How.ID, using = "chkBoxb")
    public WebElement markasCitedCheckbox;

    @FindBy(how = How.ID, using = "btnSaveSourceComment")
    public WebElement sourceSave;

    @FindBy(how = How.ID, using = "ddlCitedSource")
    public WebElement sourceDropDown;
    
    @FindBy(how = How.CSS, using = "AddSourceModal/textarea")
    public WebElement sourceUpdate;

    @FindBy(how = How.XPATH, using = "//div[@id='AddSourceModal']/div[2]/div/div[2]/div/div[2]/div[3]/textarea")
    public WebElement sourceComments;

    @FindBy(how = How.XPATH, using = "//*[@id='AddSourceModal']/div[2]/div/div[2]/div/div[2]/div[4]/input")
    public WebElement citedDate;

    public SourceUpdate(WebDriver driver) {
        super(driver);
    }

    public void enterSourceDate(String dateValue) {
        this.sourceDate.clear();
        this.sourceDate.sendKeys(dateValue);
    }

    public void enterSourceComments(String sourceComments) {
        this.sourceComments.clear();
        this.sourceComments.sendKeys(sourceComments);
    }   

    public void chooseSource(String sourceValue) {
        new Select(sourceDropDown).selectByVisibleText(sourceValue);
    }
    

    public void enableCiteCheckBoxAndEnterCitedDate(boolean citedYesNo, String citedDate) {
        if (citedYesNo) {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", markasCitedCheckbox);
            this.citedDate.clear();
            this.citedDate.sendKeys(citedDate);
        }
    }

    public PatPubDetailView sourceSubmit() {
        sourceSave.click();
        pleaseWaitForPageLoad();
        return new PatPubDetailView(driver);
    }

    public void pleaseWaitForPageLoad() {
        long timeoutInSeconds = 90;
        new WebDriverWait(driver, timeoutInSeconds)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.id("divloading")));
    }
}
