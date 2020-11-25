package android.com.tronlink.wallet.regression;

import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.DAPP_SearchPage;
import android.com.wallet.pages.DiscoverPage;
import android.com.wallet.pages.MinePage;
import android.com.wallet.pages.NodeSetPage;
import android.com.wallet.pages.SettingPage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
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

    List<String> dappNameList = new ArrayList<>();
    int dappListIndex = 0;

    @Parameters({"privateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey, DRIVER);
        dappNameList.add("Poloni DEX");
        dappNameList.add("TRONSCAN");
        dappNameList.add("WINk");
        dappNameList.add("TRONLENDING");
        dappNameList.add("RocketGame");
        dappNameList.add("JustSwap");
        dappNameList.add("TronVegas");
        dappNameList.add("TRON UP");
        dappNameList.add("Newdex");
        dappNameList.add("Bankroll");
        dappNameList.add("888TRON");
        dappNameList.add("TRONTOPIA");

    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlinkpro.wallet");
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


    @Test(enabled = true,description = "test001_discoverNileTest", alwaysRun = true)
    public void test001_discoverNileTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        DiscoverPage discover = asset.enterDiscoverPage();
        String noteContent = discover.nile_discover_note.getText();
        System.out.println(noteContent);
        Assert.assertTrue(noteContent.contains("尼罗河"));
        Assert.assertTrue(noteContent.contains("DApp"));
        Assert.assertTrue(noteContent.contains("暂不支持"));
        Assert.assertTrue(discover.isElementExist("iv_empty_icon"));

    }

    @Test(enabled = true,description = "test002_discoverMainNetTest", alwaysRun = true)
    public void test002_discoverMainNetTest() throws Exception {
        enterOnlineAssetPage();
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlinkpro.wallet");
        }catch (Exception e){}
        AssetPage asset = new AssetPage(DRIVER);
        DiscoverPage discover = asset.enterDiscoverPage();
        Assert.assertTrue(discover.dapp_title.getText().contains("DApp"));
        Assert.assertTrue(discover.qr_scan_btn.isEnabled());
        Assert.assertTrue(discover.search_history_btn.isEnabled());
        Assert.assertTrue(discover.search_btn.isEnabled());
        Assert.assertTrue(discover.hot_title.getText().contains("热门推荐"));
        Assert.assertTrue(discover.tv_type_title.getText().contains("游戏"));

    }

    @Test(enabled = true,description = "test003_discoverHotsUseAbleTest", alwaysRun = true)
    public void test003_discoverHotsUseAbleTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        DiscoverPage discover = asset.enterDiscoverPage();
        List<WebElement>  namelists = discover.hotDapp_names;
        for (int i = 0 ;i<namelists.size();i++){
            WebElement item =  namelists.get(i);
            item.click();
            try{
                discover.tv_ok.click();
            }catch (Exception e){}
            Assert.assertFalse(discover.isElementExist("net_error"));
            try{
                discover.ll_common_left.click();

            }catch (Exception ee){
                discover.ll_close.click();
                discover.ll_common_left.click();
            }
        }
    }

    @Test(enabled = true,description = "Online dapp list search test",alwaysRun = true)
    public void test004_onlineDappListSearch() throws Exception {

            AssetPage asset = new AssetPage(DRIVER);
            DiscoverPage discover = asset.enterDiscoverPage();
            DAPP_SearchPage dapp_search_page = discover.enterDAPP_SearchPage();
        for (int i = dappListIndex;i < dappNameList.size();i++) {

            dapp_search_page.search_text.sendKeys(dappNameList.get(dappListIndex));
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e){}
            String dappNameInSearchResult = dapp_search_page.dappName_text.getText();
            System.out.println("DAppName:" + dappNameInSearchResult  + " index: " + i);
            Assert.assertTrue(dappNameInSearchResult.equalsIgnoreCase(dappNameList.get(dappListIndex)) ||
                dappNameInSearchResult.contains(dappNameList.get(dappListIndex)));
            dappListIndex++;
            dapp_search_page.iv_delete.click();
        }
    }

    @Test(enabled = true,description = "Online dapp chain discover page test",alwaysRun = true)
    public void test005_onlineDappNetDiscoverTest() throws Exception {
        AssetPage asset = enterDappAssetPage();
        DiscoverPage discover = asset.enterDiscoverPage();
        Assert.assertTrue(discover.nile_discover_note.getText().contains("DApp"));
        String noteContent = discover.nile_discover_note.getText();
        Assert.assertTrue(noteContent.contains("目前还没有DApp信息"));
    }



    //enter SettingPage
    public SettingPage enterSettingPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        return mine.enterSettingPage();
    }

    //enter dapp page
    public AssetPage enterDappAssetPage() throws Exception {
        SettingPage set = enterSettingPage();
        NodeSetPage nodeSet = set.enterNodeSetPage();
        set = nodeSet.enterSettingPageChoiseDappChain();
        MinePage mine = set.enterMinePage();
        AssetPage asset = mine.enterAssetPage();
        return asset;
    }


    //enter online page
    public AssetPage enterOnlineAssetPage() throws Exception {
        SettingPage set = enterSettingPage();
        set.version_btn.click();
        set.online_version_icon.click();

        try {
            TimeUnit.SECONDS.sleep(10);

        } catch (Exception e){}

        AssetPage asset = new AssetPage(DRIVER);
        return asset;
    }




}
