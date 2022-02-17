package ios.tronlink.com.tronlink.wallet.regression;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AdvanceFuncPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.FrozenAndUnfreezePage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.MinePage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.MnemonicToolsPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.NodeSetDetailPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.NodeSetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.ReceiptPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.SendTrxPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.SettingPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.TrxPage;
import ios.tronlink.com.tronlink.wallet.utils.Helper;

/**
 * setting function test
 */
public class ShastaTest extends BaseTest {
    public String successNumber;

    @Parameters({"privateKey"})
    @BeforeClass(groups = {"P0"},alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().importFirstWallet(Helper.importType.normal,privateKey,DRIVER);
        Helper.guaranteeShastaChain(DRIVER);
    }

    @Test(description = "Shasta Transfer TRX")
    public void test001_ShastaTransferTRXSuccess() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        TrxPage tokenpage = asset.enterTrxPage();
        double trcBefore = Double.parseDouble(removeSymbol(tokenpage.trxTotal_text.getText()));
        String count = random(10,10);
        count = Helper.getPrettyNumber(count);
        log(count);
        successNumber = count;
        SendTrxPage transfer = tokenpage.enterTransferPage();
        transfer.sendTrxWithNumber(successNumber);
        TimeUnit.SECONDS.sleep(5);
        double trcafter = Double.parseDouble(removeSymbol(tokenpage.trxTotal_text.getText()));
        System.out.println("   count:" +count + "   trcBefore:" + trcBefore + " trcafter:" + trcafter);
        Assert.assertTrue(trcafter + Integer.parseInt(removeSymbol(count)) <= trcBefore);
    }



    @Parameters({"address"})
    @Test(description = "test003_FrozenTestSuccess")
    public void test003_FrozenTestSuccess(String address) throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        FrozenAndUnfreezePage frozen = asset.enterFrozenAndThawingPage();
        String availableTrxOld = frozen.getAvailableTrx();
        frozen.inputFrozenCount("1");
        Helper.tapWhitePlace(frozen.driver);
        frozen.frozenTheEnergy(); //Freeze operating
        TimeUnit.SECONDS.sleep(3);
        String availableTrxNew = frozen.getAvailableTrx();
        availableTrxOld = availableTrxOld.replace(",","");
        availableTrxNew = availableTrxNew.replace(",","");
        log("availableTrxOld: "+availableTrxOld + "availableTrxNew: " + availableTrxNew);
        Assert.assertTrue(Double.parseDouble(availableTrxNew) + 1 == Double.parseDouble(availableTrxOld));
    }

}
