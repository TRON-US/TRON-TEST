package android.com.tronlink.wallet.multiSignatureTransaction;

import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.*;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class AllSignatureSuccTest extends Base {


    public String sourceWallet = "";
    public String fromAccountPrivateKey1 = "dad5b1d416822eb02e79bb818c35411e58b88db85562bcc8e71cac2c1ffa441c";
    //public String fromAccountAddress = "TMx13rffk9sFto1LYv42wh9WmFYpYoKRcS";
    public String signatureAccountPrivateKey2 = "451a602d36e0158b5d642daca47e01ec5abdc96ec67a9f88dbc165c7dbb2a08a";
    public String signatureAccountAddress = "TS9XrumdDFBs5bQkVnhFTexoqwqaxUVG8v";


    @Parameters({"privateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey, DRIVER);
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


    //import two privateKay(wallet)
    public AssetPage importTwoPrivateKay() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        //MinePage mine = asset.enterMinePage();
        //MyPursePage myPursePage = mine.enterMyPursePage();
        MyPursePage myPursePage = asset.enterMyPursePage();
        AddwalletPage walletPage = myPursePage.enterAddwalletPage();
        ImportPrivateKeyPage importPrivateKey = walletPage.enterImportPrivateKeyPage();
        PrivateKeySetNamePage privateKeySetNamePage = importPrivateKey.enterPrivateKeySetNamePage(fromAccountPrivateKey1);
        PrivateKeySetPwdPage privateKeySetPwd = privateKeySetNamePage.enterPrivateKeySetPwdPage("FromAccount");
        PrivateKeySetPwdAgainPage privateKeySetPwdAgain = privateKeySetPwd.enterPrivateKeySetPwdAgainPage("Test0001");
        asset = privateKeySetPwdAgain.enterAssetPage("Test0001");
        //mine = asset.enterMinePage();
        //myPursePage = mine.enterMyPursePage();
        myPursePage = asset.enterMyPursePage();
        walletPage = myPursePage.enterAddwalletPage();
        importPrivateKey = walletPage.enterImportPrivateKeyPage();
        privateKeySetNamePage = importPrivateKey.enterPrivateKeySetNamePage(signatureAccountPrivateKey2);
        privateKeySetPwd = privateKeySetNamePage.enterPrivateKeySetPwdPage("SignAccount");
        privateKeySetPwdAgain = privateKeySetPwd.enterPrivateKeySetPwdAgainPage("Test0001");
        asset = privateKeySetPwdAgain.enterAssetPage("Test0001");
        return asset;
    }


    @Parameters({"address"})
    @Test(description = "Developer options Test", alwaysRun = true, enabled = false)
    public void test001_multSignOptions() throws Exception {
//        AssetPage asset = importTwoPrivateKay();
//        MyPursePage myPurse = asset.enterMyPursePage();
//        myPurse.changeWalletAccount("SignAccount");

        AssetPage asset = new AssetPage(DRIVER);
        MyPursePage myPurse = asset.enterMyPursePage();
        myPurse.changeWalletAccount("FromAccount");
        SendTrxPage SendTrx = asset.enterSendTrxPage();
        asset = SendTrx.sendRamonTrxSuccess();
        asset = myPurse.changeWalletAccount("SignAccount");
        asset.enterMinePage();







//        AssetPage asset = new AssetPage(DRIVER);
//        MyPursePage myPurse = asset.enterMyPursePage();
//        Helper.swipToChangeMyPaurse(DRIVER);
//        Helper.swipToChangeMyPaurse(DRIVER);
//        Helper.swipToChangeMyPaurse(DRIVER);
//        Helper.swipToChangeMyPaurse(DRIVER);
//        Helper.swipToChangeMyPaurse(DRIVER);
//        TimeUnit.SECONDS.sleep(5);



//        MinePage mine = asset.enterMinePage();
//        MyPursePage myPurse = mine.enterMyPursePage();
//        myPurse.changeWalletAccount("FromAccount");
//        asset = myPurse.checkAccount();
//        SendTrxPage sendTrxPage = asset.enterSendTrxPage();
//        sendTrxPage.normalSendTrx(signatureAccountAddress);

    }








}
