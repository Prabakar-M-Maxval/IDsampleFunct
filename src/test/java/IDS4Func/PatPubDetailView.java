/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IDS4Func;

import java.util.List;
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
public class PatPubDetailView extends PageObject {

    @FindBy(how = How.XPATH, using = "//input[@ng-model='Query.SearchTerm']")
    public WebElement searchQuery;

    @FindBy(how = How.ID, using = "tblusref")
    public WebElement usReferences;

    @FindBy(how = How.XPATH, using = "//*[@id='tblusref']/tbody")
    public WebElement usReferenceTable;

    @FindBy(how = How.ID, using = "tblforref")
    public WebElement foreignReferences;

    @FindBy(how = How.ID, using = "tblotherref")
    public WebElement nplReferences;

    @FindBy(how = How.XPATH, using = "//*[@id='tblusref']/tbody/tr[1]/td[4]")
    public WebElement referenceCurrentState;

    @FindBy(how = How.XPATH, using = "//*[@id='tblusref']/tbody/tr[1]/td[5]")
    public WebElement referenceRecordNo;

    @FindBy(how = How.XPATH, using = "//*[@id='tblusref']/tbody/tr[1]/td[24]")
    public WebElement referenceCurrentSource;

    @FindBy(how = How.ID, using = "RefAction")
    public WebElement referenceActionDropDown;

    int rowid = 0;
    String referenceRecordId;

    public SourceUpdate actionUSrefAssociatetoCite() throws InterruptedException {
        pleaseWaitForPageLoad();
        int assid = 0;
        Thread.sleep(5000);
        List<WebElement> allRows = usReferenceTable.findElements(By.tagName("tr"));
        for (int i = 1; i <= allRows.size(); i++) {
            //String currentState = driver.findElement(By.xpath("//*[@id='tblusref']/tbody/tr[" + i + "]/td[4]")).getText();
            String currentState = usReferenceTable.findElement(By.xpath("tr[" + i + "]/td[4]")).getText();
            String temp = currentState.substring(currentState.indexOf("Associate"));
            if (temp.equals("A Associate")) {
                assid = i;
                break;
            }
        }
        WebElement sourceSelection = driver.findElement(By.xpath("//*[@id='tblusref']/tbody/tr[" + assid + "]/td[24]/a/img"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", sourceSelection);
        pleaseWaitForPageLoad();
        System.out.println("successfully mapped");
        return new SourceUpdate(driver);
    }

    public void actionAddFlagSingleUSRef(String comments) throws InterruptedException {
        pleaseWaitForPageLoad();
        Thread.sleep(5000);
        List<WebElement> allRows = usReferenceTable.findElements(By.tagName("tr"));
        for (int i = 1; i <= allRows.size(); i++) {
            String currentState = usReferenceTable.findElement(By.xpath("tr[" + i + "]/td[4]")).getText();
            System.out.println("gh" + currentState);
            if (currentState.equals("A Associate") || currentState.equals("A ! Associate")) {
                rowid = i;
                break;
            }
        }
        WebElement referenceSelection = driver.findElement(By.xpath("//*[@id='tblusref']/tbody/tr[" + rowid + "]/td[2]/input"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", referenceSelection);
        pleaseWaitForPageLoad();
        addFlag(comments);
    }

    public void actionUSReferenceFreeze(String comments) throws InterruptedException {
        pleaseWaitForPageLoad();
        Thread.sleep(5000);
        WebElement referenceSelection = driver.findElement(By.xpath("//*[@id='tblusref']/tbody/tr[1]/td[2]/input"));
        //collect the patent no
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", referenceSelection);
        pleaseWaitForPageLoad();
        addFreeze(comments);
    }

    public String actionUSReferenceUnFreeze() throws InterruptedException {
        pleaseWaitForPageLoad();
        Thread.sleep(5000);
        List<WebElement> allRows = usReferenceTable.findElements(By.tagName("tr"));
        for (int i = 1; i <= allRows.size(); i++) {
            String currentState = usReferenceTable.findElement(By.xpath("tr[" + i + "]/td[4]")).getText();
            System.out.println("gh" + currentState);
            if (currentState.contains("Freeze")) {
                referenceRecordId = usReferenceTable.findElement(By.xpath("tr[" + i + "]/td[5]")).getText();
                rowid = i;
                break;
            }
        }
        System.out.println("referencetecprd" + referenceRecordId);
        WebElement referenceSelection = driver.findElement(By.xpath("//*[@id='tblusref']/tbody/tr[" + rowid + "]/td[2]/input"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", referenceSelection);
        pleaseWaitForPageLoad();
        unFreeze();
        return referenceRecordId;
    }

    public void addFlag(String flagComments) throws InterruptedException {
        new Select(referenceActionDropDown).selectByVisibleText("Add Flag");
        pleaseWaitForPageLoad();
        driver.findElement(By.xpath("//div[@id='FlagCommentModal']/div[2]/div/div[2]/div[2]/div/div/textarea")).sendKeys(flagComments);
        driver.findElement(By.xpath("//input[@value='Save']")).click();
        pleaseWaitForPageLoad();
        // verifyAddedFlagComments();
    }

    public void addFreeze(String freezeComments) {
        new Select(referenceActionDropDown).selectByVisibleText("Freeze");
        pleaseWaitForPageLoad();
        driver.findElement(By.id("txtFreezeComments")).sendKeys(freezeComments);
        driver.findElement(By.xpath("//*[@id='FreezedCommentModal']/div[2]/div/div[2]/div[2]/div[2]/button")).click();
        pleaseWaitForPageLoad();
    }

    public void unFreeze() {
        new Select(referenceActionDropDown).selectByVisibleText("Un Freeze");
        pleaseWaitForPageLoad();
    }

    public String verifyAddedFlagComments() throws InterruptedException {

        pleaseWaitForPageLoad();
        System.out.println("fd" + rowid);
        WebElement flaggedReference = driver.findElement(By.xpath("//*[@id='tblusref']/tbody/tr[" + rowid + "]/td[4]/span[2]/span/a/img"));
        Thread.sleep(4000);
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", flaggedReference);
        Thread.sleep(7000);
        String flagComments = driver.findElement(By.xpath("//p[contains(@ng-bind-html,'FlagComments')]")).getText();
        System.out.println("comment" + flagComments);
        return flagComments;
    }

    public void pleaseWaitForPageLoad() {
        long timeoutInSeconds = 90;
        new WebDriverWait(driver, timeoutInSeconds)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.id("divloading")));
    }

    public String verifyUSReferenceState(String refRecId) {
        String refCurrentState = null;
        List<WebElement> allRows = usReferenceTable.findElements(By.tagName("tr"));
        for (int i = 1; i <= allRows.size(); i++) {
            String recId = usReferenceTable.findElement(By.xpath("tr[" + i + "]/td[5]")).getText();
            if (recId.equals(refRecId)) {
                refCurrentState = usReferenceTable.findElement(By.xpath("tr[" + i + "]/td[4]")).getText();
                break;
            }
        }
        return refCurrentState;
    }

    public PatPubDetailView(WebDriver driver) {
        super(driver);
    }
}
