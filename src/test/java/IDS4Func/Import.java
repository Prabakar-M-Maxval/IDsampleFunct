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
public class Import extends PageObject{
    
    @FindBy(how = How.ID, using = "txtPatPubNo")
    public WebElement patPubNo;
    
    public void enterPatPubNo(String patPubNo){        
        
    }
    
    public void pleaseWaitForPageLoad(){        
        long timeoutInSeconds = 30;
        new WebDriverWait(driver, timeoutInSeconds)
                .until(ExpectedConditions.invisibilityOfElementLocated(By.id("divloading")));
    }
    
    
     public Import(WebDriver driver) {
        super(driver);
    } 
            
    
}
