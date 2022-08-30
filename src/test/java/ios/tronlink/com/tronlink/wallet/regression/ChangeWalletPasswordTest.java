package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.MinePage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.MyPursePage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.WalletPasswordPage;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ChangeWalletPasswordTest extends BaseTest {
    String keystore = "";
    String oldPassword = "Test0001";
    String newPassword = "Admin1234";



    public WalletPasswordPage walletPasswordPage() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        MyPursePage myPursePage = asset.enterMyPursePage();
        WalletPasswordPage walletPasswordPage = myPursePage.enterwalletPasswordPage();
        return walletPasswordPage;
    }

    public boolean contentTexts(List<WebElement> list, String name) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getText());
            if (list.get(i).getText().contains(name)){
                return  true;
            }
        }
        return  false;
    }

    @Test(description = "Input dont match password",alwaysRun = true)
    public void test001_InputIncorrectPassword() throws Exception{
        WalletPasswordPage walletPasswordPage = walletPasswordPage();
        walletPasswordPage.changePassword(oldPassword,newPassword,oldPassword);
        Assert.assertTrue(contentTexts(walletPasswordPage.testarray,"两次输入密码不一致"));

    }

    @Test(description = "input incorrect password",alwaysRun = true)
    public void test002_InputIncorrectPassword() throws Exception{
        WalletPasswordPage walletPasswordPage = walletPasswordPage();
        walletPasswordPage.changePassword(newPassword,newPassword,newPassword);
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(contentTexts(walletPasswordPage.testarray,"密码错误"));

    }

    @Test(description = "input incorrect password",alwaysRun = true)
    public void test003_InputCorrectPassword() throws Exception{
        WalletPasswordPage walletPasswordPage = walletPasswordPage();
        walletPasswordPage.changePassword(oldPassword,"",newPassword);
        Assert.assertTrue(contentTexts(walletPasswordPage.testarray,"字符数不合法"));

    }
    @Test(description = "input incorrect password",alwaysRun = true)
    public void test004_InputCorrectPassword() throws Exception{
        WalletPasswordPage walletPasswordPage = walletPasswordPage();
        walletPasswordPage.changePassword(oldPassword,"1",newPassword);
        Assert.assertTrue(contentTexts(walletPasswordPage.testarray,"至少由大小写字母和数字组成"));

    }
    @Test(description = "Input uncorrect password",alwaysRun = true)
    public void test005_InputCorrectPassword() throws Exception{
        WalletPasswordPage walletPasswordPage = walletPasswordPage();
        walletPasswordPage.changePassword(oldPassword,oldPassword,oldPassword);
        TimeUnit.SECONDS.sleep(1);
        Assert.assertTrue(contentTexts(walletPasswordPage.testarray,"新旧密码不能一致"));
    }




}
