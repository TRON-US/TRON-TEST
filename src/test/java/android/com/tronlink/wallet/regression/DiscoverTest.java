package android.com.tronlink.wallet.regression;

import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.DAPP_BrowerPage;
import android.com.wallet.pages.DAPP_SearchPage;
import android.com.wallet.pages.DAPP_SearchResultPage;
import android.com.wallet.pages.DiscoverPage;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


/**
 * Discover page function test
 */
public class DiscoverTest extends Base {

//    @Parameters({"privateKey"})
//    @BeforeMethod()
//    public void setUpBefore(String privateKey) throws Exception{
//        DRIVER.closeApp();
//        DRIVER.launchApp();
//        getSign(privateKey);
//    }

//    @BeforeClass(alwaysRun = true)
//    public void setUpBefore(String privateKey) throws Exception {
//        Helper.getSign(privateKey,DRIVER);
//    }
//
//    @AfterMethod(alwaysRun = true)
//    public void afterMethod(){
//        DRIVER.closeApp();
//        DRIVER.activateApp("com.tronlink.wallet");
//    }

    @Parameters({"privateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey, DRIVER);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlink.wallet");
        }catch (Exception e){}
    }

    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        //Base.tearDownAfterClass();
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }


//    @Test(description = "DAPP Search Test")
//    public void test001_quertDAPP_Search() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        DiscoverPage discover = asset.enterDiscoverPage();
//        DAPP_SearchPage dappSearch = discover.enterDAPP_SearchPage();
//        DAPP_SearchResultPage dappSearchResult = dappSearch.search("TRX");
//        Assert.assertTrue(dappSearchResult.searchResultFirst_btn.getText().contains("TRX"));
//    }



}
