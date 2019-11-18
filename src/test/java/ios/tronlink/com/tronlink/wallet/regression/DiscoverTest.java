package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;
import org.testng.Assert;
import org.testng.annotations.*;


/**
 * Discover page function test
 */
public class DiscoverTest extends BaseTest {


//    @Test(description = "DAPP Search Test")
//    public void test001_quertDAPP_Search() throws Exception {
//        AssetPage asset = new AssetPage(DRIVER);
//        DiscoverPage discover = asset.enterDiscoverPage();
//        DAPP_SearchPage dappSearch = discover.enterDAPP_SearchPage();
//        DAPP_SearchResultPage dappSearchResult = dappSearch.search("TRX");
//        Assert.assertTrue(dappSearchResult.searchResultFirst_btn.getText().contains("TRX"));
//    }

    @Test(description = "click Search Result",enabled = false,alwaysRun = true)
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
