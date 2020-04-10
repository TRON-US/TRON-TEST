package android.com.tronlink.wallet.shieldTransaction;

import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AddwalletPage;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.MinePage;
import android.com.wallet.pages.MyPursePage;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * 新建匿名交易钱包测试
 * */
public class CreateShieldWalletTest extends Base {

    @BeforeClass(alwaysRun = true)
    public void setUpBefore() throws Exception {
        new Helper().getCreateWalletSign(false,"name","Test0001", DRIVER);
    }

    @Test(groups = {"P0"},description = "Create shield wallet test", alwaysRun = true)
    public void test001CreateShieldWallet() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage minePage = asset.enterMinePage();
        MyPursePage myPursePage = minePage.enterMyPursePage();
        String shieldSddress = myPursePage.address_text.getText();
        myPursePage.backupPrivateKey_btn.click();
        myPursePage.password_et.sendKeys("Test0001");
        myPursePage.confirm_btn.click();
        String sk = myPursePage.sk_text.getText();
        System.out.println(sk);
        StringBuilder sb = new StringBuilder();
        sb.append(sk + "\n");
        sb.append(shieldSddress + "\n");
        sb.append("\n");
        sb.append("\n");
        //String res = sb.toString();
        //String reportPath = "sk.txt";
        //Files.write((Paths.get(reportPath)), res.getBytes("utf-8"), StandardOpenOption.APPEND);
    }


    @AfterMethod(alwaysRun = true)
    public void afterMethod() throws Exception {
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlink.wallet");
        }catch (Exception e){}
    }

    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }


}
