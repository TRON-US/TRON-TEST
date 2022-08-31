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

    String[] dappNameList = {"TRONSCAN","TRONLENDING","SunSwap","Bankroll","zkWrapper","JUST","SUN"};
    int dappListIndex = 0;

    @Parameters({"privateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey, DRIVER);

    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            TimeUnit.SECONDS.sleep(2);
            DeviceRestart();
        }catch (Exception e){

        }

    }

    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        DeviceQuit();
    }

    @Test(description = "Online dapp list search test",alwaysRun = true)
    public void test001_onlineDappListSearch() throws Exception {
        AssetPage asset =  enterOnlineAssetPage();
        DiscoverPage discover = asset.enterDiscoverPage();
        DAPP_SearchPage dapp_search_page = discover.enterDAPP_SearchPage();
        for (int i = dappListIndex;i < dappNameList.length;i++) {
            String findName = dappNameList[i];
            System.out.println("SearchName:" + findName + " index: " + i);
            dapp_search_page.search_text.sendKeys(findName);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e){}
            String dappNameInSearchResult = dapp_search_page.dappName_text.getText();
            TimeUnit.SECONDS.sleep(2);
            System.out.println("DAppName:" + dappNameInSearchResult  + " index: " + i);
            Assert.assertTrue(dappNameInSearchResult.equalsIgnoreCase(findName) ||
                    dappNameInSearchResult.toLowerCase().contains(findName.toLowerCase()));
            dappListIndex++;
            dapp_search_page.iv_delete.click();
        }
    }

    @Test(alwaysRun = true)
    public void test002_historyEnableTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        DiscoverPage discover = asset.enterDiscoverPage();
        discover.findByShotId("imageview").click();
        Assert.assertTrue(discover.nav_title.getText().contains("历史记录"));
        discover.backBtn.click();
        Assert.assertTrue(discover.findByShotId("tv_title").getText().contains("DApp"));
    }

    @Test(alwaysRun = true)
    public void test003_elementExistedText() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        DiscoverPage discover = asset.enterDiscoverPage();
        Assert.assertTrue(isElementShotId("banner_image"));
        Assert.assertTrue(discover.findByShotId("tv_discovery").getText().contains("发现"));
    }
}
