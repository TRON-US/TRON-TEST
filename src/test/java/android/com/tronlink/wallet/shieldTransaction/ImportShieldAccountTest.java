package android.com.tronlink.wallet.shieldTransaction;

import android.com.utils.Configuration;
import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.MinePage;
import android.com.wallet.pages.MyPursePage;
import android.com.wallet.pages.SendTrzPage;
import android.com.wallet.pages.TrzPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * 新建匿名交易钱包测试
 * */
public class ImportShieldAccountTest extends Base {

    @Parameters({"shieldSK"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String shieldSK) throws Exception {
        new Helper().getShieldSign(shieldSK, DRIVER);
    }

    @Parameters({"shieldAddress"})
    @Test(enabled = false,description = "Import shield wallet test", alwaysRun = true)
    public void test001ImportShieldWallet(String shieldAddress) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage minePage = asset.enterMinePage();
        MyPursePage myPursePage = minePage.enterMyPursePage();
        String shieldAddressStr = myPursePage.address_text.getText();
        Assert.assertEquals(shieldAddress,shieldAddressStr);
    }

    @Parameters({"shieldAddress"})
    @Test(enabled = false,description = "Shield receive address test", alwaysRun = true)
    public void test002ReceiverAddressTest(String shieldAddress) throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        asset.receipt_btn.click();
        Assert.assertEquals(asset.shieldAddress_text.getText(),shieldAddress);
        Assert.assertTrue(asset.saveQR_btn.isEnabled());
    }

    @Test(description = "Shield commit note test", alwaysRun = true)
    public void test003ShieldNoteTest() throws Exception {
        TrzPage trz = enterTrzPage();
        //System.out.println(trz.driver.getPageSource());
        String shieldBalance = removeSymbol(trz.trzTotal_text.getText());
        trz.noteDetail_btn.click();
        //System.out.println(trz.driver.getPageSource());
        String tokenBalanceInNoteDetail = removeSymbol(trz.tokenBalanceInNoteDetail_text.getText());
        System.out.println("shieldBalance:" + shieldBalance);
        System.out.println("tokenBalanceInNoteDetail:" + tokenBalanceInNoteDetail);
        Assert.assertEquals(shieldBalance,tokenBalanceInNoteDetail);

    }


    public TrzPage enterTrzPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrzPage transfer = asset.enterTrzPage();
        return transfer;
    }



    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        DRIVER.closeApp();
        DRIVER.activateApp("com.tronlink.wallet");
    }

    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }


}
