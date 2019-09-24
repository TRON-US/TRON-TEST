package tronlink;

import org.junit.Assert;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import common.utils.TronLink;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class FreezeDetail {

    private AndroidDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        TronLink.screenOn();
        driver = TronLink.driverTron;
        driver = TronLink.importWallet(driver,TronLink.testPrivateKey);
    }

    @Test(enabled = true, threadPoolSize = 1, invocationCount = 1)
    public void test01FreezeDetail() {
        //Freeze energy detail
        TronLink.testOperation(driver,TronLink.freeezeUnfreezeId,"click","Enter to freeze/unfreeze screen");
        TronLink.testOperation(driver,TronLink.freezeEnergyDetailId,"click","Show energy detail");
        int myFrozenEnergy = Integer.valueOf(TronLink.getText(driver,TronLink.myFreezeEnergyAmountId));
        int otherFrozenEnergy = Integer.valueOf(TronLink.getText(driver,TronLink.otherFreezeEnergyAmountId));
        int totalEnergyAmount = Integer.valueOf(TronLink.getText(driver,TronLink.totalFreezeEnergyAmountId));
        Assert.assertTrue(myFrozenEnergy + otherFrozenEnergy == totalEnergyAmount);

        //Freeze bandwidth detail
        TronLink.testOperation(driver,TronLink.freezeBandwidthDetailId,"click","Show bandwidth detail");
        int myFrozenBandwidth = Integer.valueOf(TronLink.getText(driver,TronLink.myFreezeBandwidthAmountid));
        int otherFrozenBandwidth = Integer.valueOf(TronLink.getText(driver,TronLink.otherFreezeBandwidthAmountId));
        int totalBandwidthAmount = Integer.valueOf(TronLink.getText(driver,TronLink.totalFreezeBandwidthAmountId));
        Assert.assertTrue(myFrozenBandwidth + otherFrozenBandwidth == totalBandwidthAmount);

        //Vote power
        int votingPower = Integer.valueOf(TronLink.getText(driver,TronLink.votingPowerInFreezeId));
        Assert.assertTrue(votingPower == myFrozenBandwidth + myFrozenEnergy);

        //Energy and bandwidth obtained detail
        TronLink.swipeUp(driver);
        TronLink.testOperation(driver,TronLink.bandwidthOptionIconId,"click","Choose bandwidth option");
        TronLink.testOperation(driver,TronLink.bandwidthQuestionId,"click","Try to check bandwidth question");
        String bandwidthQuestionContent = TronLink.getText(driver,TronLink.bandwidthQuestionContentId);
        //Assert.assertTrue(bandwidthQuestionContent.contains("43_200_000_000"));
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        TronLink.testOperation(driver,TronLink.energyOptionIconId,"click","Choose energy option");
        TronLink.testOperation(driver,TronLink.energyQuestionId,"click","Try to check energy question");
        String energyQuestionContent = TronLink.getText(driver,TronLink.energyQuestionContentId);
        //Assert.assertTrue(energyQuestionContent.contains("50_000_000_000"));
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        //Balance in frozen screen equal

        int balanceInFrozenScreen = Integer.valueOf(TronLink.getText(driver,TronLink
                .balanceInFrozenScreenId).substring(TronLink.getText(driver,TronLink.balanceInFrozenScreenId).indexOf(" ") + 1,TronLink.getText(driver,TronLink.balanceInFrozenScreenId).length() - 3));
        System.out.println("balanceInFrozenScreen:" + balanceInFrozenScreen);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        System.out.println(TronLink.getText(driver,TronLink.trxValueInAssetScreenId));
        int balanceInAssetScreen = Integer.valueOf(TronLink.getText(driver,TronLink.trxValueInAssetScreenId).substring(0,TronLink.getText(driver,TronLink.trxValueInAssetScreenId).length() - 4));
        Assert.assertTrue(balanceInFrozenScreen + votingPower == balanceInAssetScreen);
    }


    @AfterClass
    public void tearDown() {
       driver.resetApp();
    }
}
