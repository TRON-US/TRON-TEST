package ios.tronlink.com.tronlink.wallet.notused;

import android.com.utils.Configuration;

import org.testng.annotations.Test;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;

public class ImportLotsWalllet extends BaseTest {



     @Test(alwaysRun = true)
     public void test002_ImportWallet() throws Exception {
         AssetPage assetPage = new AssetPage(DRIVER);
         for (int i = 1; i < 11; i++) {
            String privateKey =  Configuration.getByPath("testng.conf").getString("iosMultiSignAccount.multiSign" + i + "PrivateKey");
            assetPage.importWallet(privateKey);

         }
     }
}
