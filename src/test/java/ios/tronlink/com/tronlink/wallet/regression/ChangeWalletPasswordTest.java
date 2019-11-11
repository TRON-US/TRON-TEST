package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.Base;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import org.testng.annotations.Test;

public class ChangeWalletPasswordTest extends Base {


    @Test(description = "Input dont match password",alwaysRun = true)
    public void test0002InputIncorrectPassword() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        assetPage.enterMinePage();

    }









}
