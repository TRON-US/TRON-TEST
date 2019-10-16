package com.tronklink.wallet.regression;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import common.utils.Helper;
import wallet.UITest.base.Base;
import wallet.pages.AssetPage;
import wallet.pages.DAPP_BrowerPage;
import wallet.pages.DAPP_SearchPage;
import wallet.pages.DAPP_SearchResultPage;
import wallet.pages.DiscoverPage;


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

//    @BeforeClass()
//    public void setUpBefore(String privateKey) throws Exception {
//        Helper.getSign(privateKey,DRIVER);
//    }
//
//    @AfterMethod
//    public void afterMethod(){
//        DRIVER.closeApp();
//        DRIVER.activateApp("com.tronlink.wallet");
//    }

    @Parameters({"privateKey"})
    @BeforeClass()
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey,DRIVER);
    }

    @AfterMethod
    public void afterMethod(){
        DRIVER.closeApp();
        DRIVER.activateApp("com.tronlink.wallet");
    }

    @AfterClass
    public void tearDownAfterClass() {
        //Base.tearDownAfterClass();
        DRIVER.quit();
    }


//    @Test(description = "DAPP Search Test")
//    public void test001_quertDAPP_Search() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        DiscoverPage discover = asset.enterDiscoverPage();
//        DAPP_SearchPage dappSearch = discover.enterDAPP_SearchPage();
//        DAPP_SearchResultPage dappSearchResult = dappSearch.search("TRX");
//        Assert.assertTrue(dappSearchResult.searchResultFirst_btn.getText().contains("TRX"));
//    }

    @Test(description = "click Search Result")
    public void test002_clickSearchResult() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        DiscoverPage discover = asset.enterDiscoverPage();
        DAPP_SearchPage dappSearch = discover.enterDAPP_SearchPage();
        DAPP_SearchResultPage dappSearchResult = dappSearch.search("TRX");
        String serachResult = dappSearchResult.searchResultFirst_btn.getText();
        DAPP_BrowerPage dapp = dappSearchResult.enterDAPP_BrowerPage();
        Assert.assertEquals(serachResult,dapp.dappTtile_btn.getText());

    }












}
