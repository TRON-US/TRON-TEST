package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class DappAddAssetsTest extends BaseTest {
    //enter SettingPage
    public SettingPage enterSettingPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine = asset.enterMinePage();
        return mine.enterSettingPage();
    }

    //enter TRXPage
    public TrxPage enterTrxPage() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        if(!Helper.assetFindMainChain(asset)){
            return asset.enterTrxPage();
        }else{
            SettingPage set = enterSettingPage();
            NodeSetPage nodeSet = set.enterNodeSetPage();
            set = nodeSet.enterSettingPageChoiseDappChain();
            MinePage mine  = set.enterMinePage();
            asset = mine.enterAssetPage();
            return asset.enterTrxPage();
        }

    }
    //enter Dapp AssetPage
    public AssetPage enterAssetPage() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        if(!Helper.assetFindMainChain(asset)){
            return asset;
        }else{
            SettingPage set = enterSettingPage();
            NodeSetPage nodeSet = set.enterNodeSetPage();
            set = nodeSet.enterSettingPageChoiseDappChain();
            MinePage mine  = set.enterMinePage();
            asset = mine.enterAssetPage();
            return asset;
        }
    }

//    @Test(description = "guarantee Chain in Dappchain",alwaysRun = true)
//    public void test000_GuaranteeChainName() throws Exception {
//        Assert.assertTrue( Helper.guaranteeDappChain(DRIVER));
//    }

//    @Test(description = "test add assert",alwaysRun = true)
//    public void test002_addAsset() throws Exception {
//        AssetPage assetPage = new AssetPage(DRIVER);
//        AddAssertPage addAssertPage = assetPage.enterAddAssertPage();
//        SearchAssertPage searchAssertPage =addAssertPage.enterSearchAssertPage();
//        searchAssertPage.addAssert_input.sendKeys("1000027");
//        searchAssertPage.keyboard_btn.click();
//        searchAssertPage.turnAsset_btn.click();
//        addAssertPage = searchAssertPage.enterAddAssertPage();
//        assetPage = addAssertPage.enterAssetPage();
//        TimeUnit.SECONDS.sleep(2);
//        assertTrue(Helper.isElementExist(assetPage.driver,"testAssetIssue_1567077083240"));
//    }
//
//
//
//    @Test(description = "test remove asset",alwaysRun = true)
//    public void test003_removeAsset() throws Exception {
//        AssetPage assetPage = new AssetPage(DRIVER);
//        AddAssertPage addAssertPage =  assetPage.enterAddAssertPage();
//        addAssertPage.mainPageAssetManage_tab.click();
//        addAssertPage.hotAssetManage_tab.click();
//        addAssertPage.mainPageAssetManage_tab.click();
//        addAssertPage.hotAssetManage_tab.click();
//        addAssertPage.mainPageAssetManage_tab.click();
//        addAssertPage.hotAssetManage_tab.click();
//        addAssertPage.mainPageAssetManage_tab.click();
//        addAssertPage.turnAsset_btn.click();
//        assetPage =  addAssertPage.enterAssetPage();
//        TimeUnit.SECONDS.sleep(2);
//        assertFalse(Helper.isElementExist(assetPage.driver,"testAssetIssue_1567077083240"));
//
//    }

}
