/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IDS4Func;
import org.testng.annotations.Test;

/**
 *
 * @author prabakar
 */
public class IDSFunctionRunner extends DriverSetup {

    SoftAsset IDSAsset = new SoftAsset();
    String searchQueryRefTab = "DKN:(\"12636-347.201\")";

    //@Test
    public void dashboardReport() throws InterruptedException {
        navigateToLogin();
        LoginAction loginAction = new LoginAction(driver);
        loginAction.enterUserName("maxval");
        loginAction.enterPassword("Qcom2015*");
        TermsAndCondition termsAndConditions = loginAction.loginSubmit();
        Dashboard dashboard = termsAndConditions.Accept();

        String a1 = dashboard.getRecentlyPublishedCount();
        SearchList searchList = new SearchList(driver);
        String searchCount = searchList.getSearchResultCount(a1);
        System.out.println("published count from search list" + searchCount);
        String query = searchList.getQueryAndReturnDashboard();
        System.out.println("published count query" + query);
        IDSAsset.verifyEquals(searchCount, a1, "Verification of Recent Published Count");

        String b1 = dashboard.getAssociatedAssetLastWeek();
        String searchCountRecentAssociated = searchList.getSearchResultCount(b1);
        System.out.println("recent associated count from search list" + searchCountRecentAssociated);
        String queryNew = searchList.getQueryAndReturnDashboard();
        System.out.println("recent associated query" + queryNew);
        IDSAsset.verifyEquals(searchCountRecentAssociated, b1, "Verification of Associated State count Recent");

        String d1 = dashboard.getFiledCount();
        String searchCountRecentmapping = searchList.getSearchResultCount(searchCount);

        String c1 = dashboard.getAssociatedAsset();
        String searchCountAssociated = searchList.getSearchResultCount(c1);
        System.out.println("associated count from search list" + searchCountAssociated);
        String queryNew1 = searchList.getQueryAndReturnDashboard();
        System.out.println("associated query" + queryNew1);
        IDSAsset.verifyEquals(searchCountAssociated, c1, "Verification of Associated State count");
        IDSAsset.checkForVerificationErrors();
        String passedNotes = IDSAsset.assertPassedCondition();
        System.out.println("pass" + passedNotes);
    }
    // @Test
    public void fullTextSearchFromDashboard() {
        navigateToLogin();
        LoginAction loginAction = new LoginAction(driver);
        loginAction.enterUserName("maxval");
        loginAction.enterPassword("Qcom2015*");
        TermsAndCondition termsAndConditions = loginAction.loginSubmit();
        Dashboard dashboard = termsAndConditions.Accept();
        dashboard.enterFullTextSearchQuery("RID:(R15540)");
        SearchList searchList = dashboard.submitFullTextSearchQueryFromDashboard();
        searchList.getSearchResultCount();
    }
    // @Test(threadPoolSize = 3, invocationCount = 10)
    public void waitLongTime() throws Exception {
        System.out.println("invocationCount");
        System.out.printf("Thread Id : %s%n", Thread.currentThread().getId());
    }
    //@Test
    public void USSingleRefAssociateToCiteAction() throws InterruptedException {
        navigateToLogin();
        LoginAction loginAction = new LoginAction(driver);
        loginAction.enterUserName("maxval");
        loginAction.enterPassword("Qcom2015*");
        TermsAndCondition termsAndConditions = loginAction.loginSubmit();
        Dashboard dashboard = termsAndConditions.Accept();
        dashboard.enterFullTextSearchQuery(searchQueryRefTab);
        PatPubDetailView patPubDetailView = dashboard.submitSingleSearch();
        SourceUpdate sourceUpdate = patPubDetailView.actionUSrefAssociatetoCite();
        sourceUpdate.chooseSource("PTO/892");
        sourceUpdate.enterSourceDate("2016/10/10");
        sourceUpdate.enterSourceComments("State changed from Associate to Cited");
        sourceUpdate.enableCiteCheckBoxAndEnterCitedDate(true, "2016/10/10");
        patPubDetailView = sourceUpdate.sourceSubmit();
        //verification
        //source verification
        //source comments
        //cited Date        
    }
    //@Test
    public void actionUSRefAddFlag() throws InterruptedException{
        String expectedFlagComments = "US reference added flag comments";
        navigateToLogin();
        LoginAction loginAction = new LoginAction(driver);
        loginAction.enterUserName("maxval");
        loginAction.enterPassword("Qcom2015*");
        TermsAndCondition termsAndConditions = loginAction.loginSubmit();
        Dashboard dashboard = termsAndConditions.Accept();
        dashboard.enterFullTextSearchQuery(searchQueryRefTab);
        PatPubDetailView patPubDetailView = dashboard.submitSingleSearch();
        patPubDetailView.actionAddFlagSingleUSRef(expectedFlagComments);        
        String actualResult = patPubDetailView.verifyAddedFlagComments();
        //verification.
    }
    
   // @Test
    public void actionUSRefFreeze() throws InterruptedException {
        String expectedFreezeComments = "Test freeze";
        navigateToLogin();
        LoginAction loginAction = new LoginAction(driver);
        loginAction.enterUserName("maxval");
        loginAction.enterPassword("Qcom2015*");
        TermsAndCondition termsAndConditions = loginAction.loginSubmit();
        Dashboard dashboard = termsAndConditions.Accept();
        dashboard.enterFullTextSearchQuery(searchQueryRefTab);
        PatPubDetailView patPubDetailView = dashboard.submitSingleSearch();
        patPubDetailView.actionUSReferenceFreeze(expectedFreezeComments);
    }
    
    @Test
    public void actionUSunfreeze() throws InterruptedException {
        navigateToLogin();
        LoginAction loginAction = new LoginAction(driver);
        loginAction.enterUserName("maxval");
        loginAction.enterPassword("Qcom2015*");
        TermsAndCondition termsAndConditions = loginAction.loginSubmit();
        Dashboard dashboard = termsAndConditions.Accept();
        dashboard.enterFullTextSearchQuery(searchQueryRefTab);
        PatPubDetailView patPubDetailView = dashboard.submitSingleSearch();
        String actionRecId = patPubDetailView.actionUSReferenceUnFreeze();
        patPubDetailView.verifyUSReferenceState(actionRecId);
    }
}