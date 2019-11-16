package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.Base;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.FrozenAndUnfreezePage;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class FrozenAndUnfreezeTest extends Base {


    @Parameters({"privateKey"})
    @BeforeClass()
    public void setUpBefore(String privateKey) throws Exception {
        System.out.print("status:" + DRIVER.getBatteryInfo().getState() + "\n");
        DRIVER.getBatteryInfo().getState();
        System.out.println("333333333");
        new Helper().getSign(privateKey,DRIVER);
    }



    @AfterMethod
    public void afterMethod(){
        DRIVER.closeApp();
        DRIVER.launchApp();
    }



    @Test //测试是否正常进行投票页
    public void test01_checkFrozen() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozenAndUnfreezePage = assetPage.enterFrozenAndUnfreezePage();
        Assert.assertEquals(frozenAndUnfreezePage.assert_title.getText(),"资源");
    }



    @Test //测试是否正常进行投票页
    public void test02_checkEnterFrozenAndUnfreeze() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozenAndUnfreezePage = assetPage.enterFrozenAndUnfreezePage();
        Assert.assertEquals(frozenAndUnfreezePage.assert_title.getText(),"资源");
    }











}
