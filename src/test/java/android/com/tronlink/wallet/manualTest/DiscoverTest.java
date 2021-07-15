package android.com.tronlink.wallet.manualTest;

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
        //dappNameList.add("Knight Story");
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
            DRIVER.activateApp("wallet.tronlink.harmony");
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


    @Test(enabled = true,description = "Nile main chain discover page test", alwaysRun = true)
    public void test001_discoverTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        DiscoverPage discover = asset.enterDiscoverPage();

        //尼罗河测试点
        String noteContent = discover.nile_discover_note.getText();
        System.out.println(noteContent);
        Assert.assertTrue(noteContent.contains("尼罗河"));
        Assert.assertTrue(noteContent.contains("DAPP") || noteContent.contains("DApp"));
        Assert.assertTrue(noteContent.contains("暂不支持"));


        //主网测试点
/*        Assert.assertTrue(discover.dapp_title.getText().contains("DAPP"));
        Assert.assertTrue(discover.qr_scan_btn.isEnabled());
        Assert.assertTrue(discover.search_history_btn.isEnabled());
        Assert.assertTrue(discover.search_btn.isEnabled());
        discover.search_btn.click();
        Assert.assertTrue(discover.search_icon_in_searchPage_btn.isEnabled());*/

    }

    @Test(enabled = true,description = "Nile dapp chain discover page test",alwaysRun = true)
    public void test002_dappDiscoverTest() throws Exception {
        AssetPage asset = enterDappAssetPage();
        DiscoverPage discover = asset.enterDiscoverPage();
        try {
            TimeUnit.SECONDS.sleep(10);
            // if page display AD , cloese the AD
/*            if (ad_pic.isDisplayed()){
                adClose_btn.click();
                TimeUnit.SECONDS.sleep(1);
            }*/
        } catch (Exception e){}


        //尼罗河测试点
        Assert.assertTrue(discover.dapp_title.getText().contains("DApp"));

    }

    @Test(enabled = true,description = "Online main chain discover page test",alwaysRun = true)
    public void test003_onlineMainNetDiscoverTest() throws Exception {
        enterOnlineAssetPage();
      try {
        DRIVER.closeApp();
        DRIVER.activateApp("wallet.tronlink.harmony");
      }catch (Exception e){}
      AssetPage asset = new AssetPage(DRIVER);

        DiscoverPage discover = asset.enterDiscoverPage();

        //主网测试点
        Assert.assertTrue(discover.dapp_title.getText().contains("DApp") ||
            discover.dapp_title.getText().contains("DAPP"));
        Assert.assertTrue(discover.qr_scan_btn.isEnabled());
        Assert.assertTrue(discover.search_history_btn.isEnabled());
        Assert.assertTrue(discover.search_btn.isEnabled());
        discover.search_btn.click();
        Assert.assertTrue(discover.search_icon_in_searchPage_btn.isEnabled());


    }

    @Test(enabled = true,description = "Online dapp list search test",alwaysRun = true)
    public void test004_onlineDappListSearch() throws Exception {

        for (int i = dappListIndex;i < dappNameList.size();i++) {
            AssetPage asset = new AssetPage(DRIVER);
            DiscoverPage discover = asset.enterDiscoverPage();
            DAPP_SearchPage dapp_search_page = discover.enterDAPP_SearchPage();
            dapp_search_page.search_text.sendKeys(dappNameList.get(dappListIndex));
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e){}
            String dappNameInSearchResult = dapp_search_page.dappName_text.getText();
            System.out.println("dappName_text:" + dappNameInSearchResult);
            Assert.assertTrue(dappNameInSearchResult.equalsIgnoreCase(dappNameList.get(dappListIndex)) ||
                dappNameInSearchResult.contains(dappNameList.get(dappListIndex)));
            dappListIndex++;

            try {
                DRIVER.closeApp();
                DRIVER.activateApp("wallet.tronlink.harmony");
            }catch (Exception e){}
        }
    }

    @Test(enabled = true,description = "Online dapp chain discover page test",alwaysRun = true)
    public void test005_onlineDappNetDiscoverTest() throws Exception {
        AssetPage asset = enterDappAssetPage();
        DiscoverPage discover = asset.enterDiscoverPage();
        Assert.assertTrue(discover.nile_discover_note.getText().contains("DApp"));
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
