package ios.tronlink.com.tronlink.wallet.regression;


import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AboutUsPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.MinePage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.VersionsPage;
import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * 关于我们功能测试
 */
public class AboutUsTest extends BaseTest {

    @Test(description = "test AboutUs assert",alwaysRun = true)
    public void test001_aboutUs() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage =  assetPage.enterMinePage();
        AboutUsPage aboutusPage = minePage.enterAboutUsPage();
        VersionsPage versionsPage = aboutusPage.enterVersionsPage();
        Assert.assertEquals(versionsPage.title.getText(),"版本日志");
    }


}
