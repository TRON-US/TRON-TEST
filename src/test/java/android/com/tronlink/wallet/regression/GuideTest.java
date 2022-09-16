package android.com.tronlink.wallet.regression;

import android.com.utils.AppiumTestCase;
import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.GuidePage;

import org.junit.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class GuideTest extends Base {


    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            TimeUnit.SECONDS.sleep(2);
            DRIVER.closeApp();
        }catch (Exception e){}
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        try {
            DRIVER.activateApp("com.tronlink.global");
        }catch (Exception e){
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }


    @Test(description = "Guide info test", alwaysRun = true)
    public void test001_GuideInfo() throws Exception {
        GuidePage guide = new GuidePage(DRIVER);
        Assert.assertTrue(guide.title.getText().contains("波场支持"));
        Assert.assertTrue(guide.subtitle.getText().contains("全面支持 TRX 及主链通证，各类功能，应有尽有"));
        Assert.assertTrue(guide.indicator1.getText().contains("最丰富的"));
        Assert.assertTrue(guide.indicator2.getText().contains("最安全的"));
        Assert.assertTrue(guide.indicator3.getText().contains("最便捷的"));
        Assert.assertTrue(guide.observationBtn.getText().contains("观察钱包"));
        Assert.assertTrue(guide.ledgerBtn.getText().contains("Ledger"));
        Assert.assertTrue(guide.importBtn.getText().contains("导入账户"));
        Assert.assertTrue(guide.creatBtn.getText().contains("创建钱包"));
        Assert.assertTrue(guide.switchBtn.isDisplayed());
        guide.LeftSwapScreen();
        Assert.assertTrue(guide.title.getText().contains("资管选择"));
        Assert.assertTrue(guide.subtitle.getText().contains("波场安全团队保驾护航，私钥自持，多层防护"));
        Assert.assertTrue(guide.indicator1.getText().contains("最丰富的"));
        Assert.assertTrue(guide.indicator2.getText().contains("最安全的"));
        Assert.assertTrue(guide.indicator3.getText().contains("最便捷的"));
        Assert.assertTrue(guide.observationBtn.getText().contains("观察钱包"));
        Assert.assertTrue(guide.ledgerBtn.getText().contains("Ledger"));
        Assert.assertTrue(guide.importBtn.getText().contains("导入账户"));
        Assert.assertTrue(guide.creatBtn.getText().contains("创建钱包"));
        Assert.assertTrue(guide.switchBtn.isDisplayed());
        guide.LeftSwapScreen();
        Assert.assertTrue(guide.title.getText().contains("使用体验"));
        Assert.assertTrue(guide.subtitle.getText().contains("一键创建，轻松上手；多个账户，统一管理"));
        Assert.assertTrue(guide.indicator1.getText().contains("最丰富的"));
        Assert.assertTrue(guide.indicator2.getText().contains("最安全的"));
        Assert.assertTrue(guide.indicator3.getText().contains("最便捷的"));
        Assert.assertTrue(guide.observationBtn.getText().contains("观察钱包"));
        Assert.assertTrue(guide.ledgerBtn.getText().contains("Ledger"));
        Assert.assertTrue(guide.importBtn.getText().contains("导入账户"));
        Assert.assertTrue(guide.creatBtn.getText().contains("创建钱包"));
        Assert.assertTrue(guide.switchBtn.isDisplayed());
    }

    @Test(description = "test002_entrancePathTest", alwaysRun = true)
    public void test002_entrancePathTest() throws Exception {
        GuidePage guide = new GuidePage(DRIVER);
        guide.creatBtn.click();
        Assert.assertTrue(guide.safariBtn.isDisplayed());
        Assert.assertTrue(guide.accBtn.getText().contains("请上滑看完本条款再同意"));
        guide.driver.navigate().back();
        guide.importBtn.click();
        Assert.assertTrue(guide.safariBtn.isDisplayed());
        Assert.assertTrue(guide.accBtn.getText().contains("请上滑看完本条款再同意"));
        guide.driver.navigate().back();
        guide.observationBtn.click();
        Assert.assertTrue(guide.safariBtn.isDisplayed());
        Assert.assertTrue(guide.accBtn.getText().contains("请上滑看完本条款再同意"));
        guide.driver.navigate().back();
        guide.ledgerBtn.click();
        Assert.assertTrue(guide.safariBtn.isDisplayed());
        Assert.assertTrue(guide.accBtn.getText().contains("请上滑看完本条款再同意"));
    }

    @Test(alwaysRun = true)
    public void test003_coldModeTestOnLineTest() throws Exception{
        GuidePage guide = new GuidePage(DRIVER);
        guide.switchBtn.click();
        Assert.assertTrue(guide.title.getText().contains("冷钱包模式描述"));
        Assert.assertTrue(guide.noticeTitle.getText().contains("当前设备已连接网络，请关闭网络后，再进行创建或导入"));
        Assert.assertTrue(guide.knowBtn.getText().contains("我知道了"));
        guide.knowBtn.click();
        Assert.assertFalse( isElementTextExist("冷钱包模式描述"));
    }

    @Parameters({"udid"})
    @Test(alwaysRun = true)
    public void test004_coldModeTestOffLineTest(String udid) throws Exception{
        GuidePage guide = new GuidePage(DRIVER);
        wifiClose(udid);
        TimeUnit.SECONDS.sleep(3);
        guide.switchBtn.click();
        Assert.assertTrue(guide.title.getText().contains("冷钱包模式描述"));
        Assert.assertTrue(guide.cancelBtn.getText().contains("返回"));
        Assert.assertTrue(guide.modeconfirmBtn.getText().contains("选择此模式"));
        guide.cancelBtn.click();
        TimeUnit.SECONDS.sleep(1);
        guide.switchBtn.click();
        wifiOpen(udid);
        TimeUnit.SECONDS.sleep(3);

    }

    @Parameters({"udid"})
    @Test(alwaysRun = true)
    public void test005_coldModeInfoTest(String udid) throws Exception{
        GuidePage guide = new GuidePage(DRIVER);
        wifiClose(udid);
        TimeUnit.SECONDS.sleep(3);
        guide.switchBtn.click();
        guide.modeconfirmBtn.click();
        waiteTime(1);
        Assert.assertFalse( Helper.isElementExist(guide.driver,"Ledger"));
        Assert.assertFalse( Helper.isElementExist(guide.driver,"观察钱包"));
        waiteTime();
        Assert.assertTrue(guide.importBtn.getText().contains("导入账户"));
        Assert.assertTrue(guide.creatBtn.getText().contains("创建钱包"));
        guide.switchBtn.click();
        wifiOpen(udid);
        TimeUnit.SECONDS.sleep(3);
        Assert.assertTrue(guide.observationBtn.getText().contains("观察钱包"));
        Assert.assertTrue(guide.ledgerBtn.getText().contains("Ledger"));
    }

}
