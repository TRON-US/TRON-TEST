package android.com.tronlink.wallet.mainTest;

import android.com.utils.Configuration;
import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.MinePage;
import android.com.wallet.pages.SwapPage;
import android.com.wallet.pages.TrxPage;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SwapNativeTest extends Base {

    Double sentAmountRecoder;

    @Parameters({"privateKey"})
    @BeforeClass(groups = {"P0"},alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        System.out.println("执行setUpBefore");
        new Helper().getSign(privateKey, DRIVER);
    }


    @AfterMethod(groups = {"P0"},alwaysRun = true)
    public void afterMethod() {
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlinkpro.wallet");
        }catch (Exception e){}
    }


    @AfterClass(groups = {"P0"},alwaysRun = true)
    public void tearDownAfterClass() {
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }

    @Test(enabled = true)
    public void test001_swapMainPageTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SwapPage page =  asset.enterSwapPage();
        TimeUnit.SECONDS.sleep(6);
        Assert.assertTrue(isElementTextExist("闪兑"));
        Assert.assertTrue(page.tv_token_name_to.getText().contains("USDT")&&page.tv_token_name_from.getText().contains("TRX"));
        Assert.assertTrue(page.tv_token_to.getText().contains("USDT")&&page.tv_token_from.getText().contains("TRX"));
        Assert.assertTrue(isElementTextExist("查看币种信息"));
        page.tv_token_name_to.click();
        Assert.assertTrue(page.nav_title.getText().contains("选择币种"));
        page.findElementByText("SUNOLD").click();
        TimeUnit.SECONDS.sleep(4);
        Assert.assertTrue(page.tv_token_name_to.getText().contains("SUNOLD")&&page.tv_token_name_from.getText().contains("TRX"));
        Assert.assertTrue(page.tv_token_to.getText().contains("SUNOLD")&&page.tv_token_from.getText().contains("TRX"));
        page.tv_token_name_from.click();
        Assert.assertTrue(page.nav_title.getText().contains("选择币种"));
        page.findElementByText("HT").click();
        TimeUnit.SECONDS.sleep(4);
        Assert.assertTrue(page.tv_token_name_from.getText().contains("HT")&&page.tv_token_name_to.getText().contains("SUNOLD"));
        Assert.assertTrue(page.tv_token_from.getText().contains("HT")&&page.tv_token_to.getText().contains("SUNOLD"));

    }


    @Test(enabled = true)
    public void test002_swapSuccessTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SwapPage page =  asset.enterSwapPage();
        TimeUnit.SECONDS.sleep(5);
        Double sentAmount =getAnAmount();
        sentAmountRecoder = sentAmount;
        page.inputFromAmount(sentAmount.toString());
        TimeUnit.SECONDS.sleep(1);
        page.swapConfirmView();
        page.swapSentAction();
        TimeUnit.SECONDS.sleep(10);
        System.out.println(page.token_consume_count.getText());
        System.out.println(sentAmount.toString());
        Assert.assertTrue(page.token_consume_count.getText().contains(sentAmount.toString()));
        Assert.assertTrue(page.tv_date.getText().contains("2022"));
    }


     @Test(alwaysRun = true)
     public void test003_ivSendNoticeTest() throws Exception {
         AssetPage asset = new AssetPage(DRIVER);
         Assert.assertTrue(isElementShotId("iv_red_dot"));
         MinePage page = asset.enterMinePage();
         Assert.assertTrue(isElementShotId("tv_bell"));
         page.tv_bell.click();
         Assert.assertTrue(page.firstContent.getText().contains(sentAmountRecoder.toString()));
         DRIVER.navigate().back();
         TimeUnit.SECONDS.sleep(1);
         Assert.assertFalse(isElementShotId("iv_red_dot"));
         Assert.assertFalse(isElementShotId("tv_bell"));
     }

}
