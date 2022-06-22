package android.com.tronlink.wallet.regression;


import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;

import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.ProjectItemPage;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * 资产页面测试
 */
public class AssetsPageTest extends Base {

    @Parameters({"privateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey, DRIVER);
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
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }

    @Test(enabled = true,description = "assets total amount test", alwaysRun = true)
    public void test001_AssetsTotalAmountTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TimeUnit.SECONDS.sleep(3);
        String about = asset.trxValue.getText();
        String result = sepLeftNumberTextToString(about,"TRX").trim();
        Double number = Double.parseDouble(removeSymbolFloat(result));
        System.out.println("Total number:" + number);
        Assert.assertTrue(number > 0 );
    }

    @Test(enabled = true,description = "assets total about amount test", alwaysRun = true)
    public void test002_AssetsTotalAboutAmountTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);//  DRIVER.navigate().back();
        TimeUnit.SECONDS.sleep(3);
        String about = asset.abountmoneyvalue.getText();
        String result;
        if (about.contains("$")){
            result = about.replace("≈$"," ").trim();
        }else {
            result = about.replace("≈¥"," ").trim();
        }
        Double number = Double.parseDouble(removeSymbolFloat(result));
        System.out.println("Total about number:" + number);
        Assert.assertTrue(number > 0 );
    }

    @Test(description = "five button can click",alwaysRun = true)
    public void test003_fivemainButtonPathchick() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        asset.findElementByText("转账").click();
        Assert.assertTrue(asset.isTextExist("转账"));
        asset.driver.navigate().back();
        asset.findElementByText("收款").click();
        Assert.assertTrue(asset.isTextExist("收款"));
        asset.driver.navigate().back();
        asset.findElementByText("闪兑").click();
        Assert.assertTrue(asset.isTextExist("闪兑"));
        asset.driver.navigate().back();
        asset.findElementByText("质押").click();
        Assert.assertTrue(asset.isTextExist("质押 TRX"));
        asset.driver.navigate().back();
        asset.findElementByText("投票").click();
        asset.driver.navigate().back();
        asset.driver.navigate().back();
        asset.findElementByText("投票").click();
        Assert.assertTrue(asset.isTextExist("投票"));
        asset.driver.navigate().back();
        Assert.assertTrue(asset.isTextExist("收藏品"));
    }


    @Test(description = "close eyes and open eyes",alwaysRun = true)
    public void test004_eyesCloseOpenTest() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        Assert.assertTrue(asset.trxValue.getText().contains("TRX"));
        Assert.assertTrue(asset.abountmoneyvalue.getText().contains("≈$"));
        asset.eyesButton.click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(asset.trxValue.getText().contains("****"));
        Assert.assertTrue(asset.abountmoneyvalue.getText().contains("****"));
        asset.eyesButton.click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(asset.trxValue.getText().contains("TRX"));
        Assert.assertTrue(asset.abountmoneyvalue.getText().contains("≈$"));
    }

    @Test(description = "test005_AssetTabChangeTest",alwaysRun = true)
    public void test005_AssetTabChangeTest() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        Assert.assertTrue(asset.isTextExist("TRX"));
        Helper.swipScreenLitte(asset.driver);
        Assert.assertTrue(asset.isTextExist("tronlink_token"));
        asset.findElementByText("收藏品").click();
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(asset.isTextExist("TNFT"));
    }

    @Test(alwaysRun = true)
    public void test006_EyeOpenAndCloseTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        asset.eyesButton.click();
        Assert.assertEquals(asset.trxValue.getText(),"****");
        Assert.assertEquals(asset.tv_money_value.getText(),"****");
        asset.enterSwitchWallet();
        Assert.assertEquals(asset.tv_value.getText(),"****");
        asset.driver.navigate().back();
        asset.eyesButton.click();
        Assert.assertNotEquals(asset.trxValue.getText(),"****");
        Assert.assertNotEquals(asset.tv_money_value.getText(),"****");
        asset.enterSwitchWallet();
        Assert.assertNotEquals(asset.tv_value.getText(),"****");
    }

    @Test(alwaysRun = true)
    public void test007_ProjectTronLinkToken() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        asset.enterTrx10Page();
        ProjectItemPage page = asset.enterProjectItem();
        Assert.assertEquals(page.assets_name.getText(),"tronlink_token");
        Assert.assertEquals(page.assets_tag.getText(),"TRC10");
        Assert.assertEquals(page.token_id.getText(),"1000002");
        Assert.assertEquals(page.token_url.getText(),"http://nileex.io/");
        Assert.assertEquals(page.token_publisher.getText(),"TN21Wx2yoNYiZ7znuQonmZMJnH5Vdfxu78");
        Helper.swipScreenLitte(DRIVER);
        Assert.assertEquals(page.start_time.getText(),"2019-11-27 15:11:00");
        Assert.assertEquals(page.end_time.getText(),"2025-09-11 20:50:00");
        Assert.assertEquals(page.total_circulation.getText(),"1000000000");



    }
}
