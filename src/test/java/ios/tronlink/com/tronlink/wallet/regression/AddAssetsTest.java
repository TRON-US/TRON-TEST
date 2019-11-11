package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.Base;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AddAssertPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.SearchAssertPage;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;


public class AddAssetsTest extends Base {



    @Parameters({"privateKey"})
    @BeforeClass()
    public void setUpBefore(String privateKey) throws Exception {
        //System.out.print("status:" + DRIVER.getBatteryInfo().getState() + "\n");
        //DRIVER.getBatteryInfo().getState();
        //DRIVER.getBatteryInfo().toString();
        System.out.println("333333333");
        new Helper().getSign(privateKey,DRIVER);
    }



    @AfterMethod
    public void afterMethod(){
        DRIVER.closeApp();
        DRIVER.launchApp();
    }



    @AfterClass
    public void tearDownAfterClass() {
        //Base.tearDownAfterClass();
        DRIVER.quit();
    }


    @Test(description = "test add assert",alwaysRun = true)
    public void test002_addAsset() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        AddAssertPage addAssertPage = assetPage.enterAddAssertPage();
        SearchAssertPage searchAssertPage =addAssertPage.enterSearchAssertPage();
        searchAssertPage.addAssert_input.sendKeys("1000027");
        searchAssertPage.keyboard_btn.click();
        searchAssertPage.turnAsset_btn.click();
        addAssertPage = searchAssertPage.enterAddAssertPage();
        assetPage = addAssertPage.enterAssetPage();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(assetPage.myNewAddAsset_text.getText().contains("testAssetIssue_1567077083240"));
    }



    @Test(description = "test remove asset",alwaysRun = true)
    public void test003_removeAsset() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        AddAssertPage addAssertPage =  assetPage.enterAddAssertPage();
        addAssertPage.mainPageAssetManage_tab.click();

        TimeUnit.SECONDS.sleep(10);
        System.out.println(DRIVER.getPageSource());
        System.out.println("12121212312321---"+addAssertPage.assertSwitch_btn.size());


        //addAssertPage.removeAsset();
        //Assert.assertFalse(addAssert.switchFirst_btn.isSelected());
    }














}
