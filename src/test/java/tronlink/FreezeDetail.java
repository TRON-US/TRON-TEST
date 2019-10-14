package tronlink;

import org.junit.Assert;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import common.utils.AppiumTestCase;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class FreezeDetail extends AppiumTestCase {

    @Test(enabled = true, threadPoolSize = 1, invocationCount = 1)
    public void test01FreezeDetail() {
        importWallet(testPrivateKey);

        //Freeze energy detail
         testOperation(freeezeUnfreezeId,"click","Enter to freeze/unfreeze screen");
         testOperation(  freezeEnergyDetailId,"click","Show energy detail");
        int myFrozenEnergy = Integer.valueOf( getText(  myFreezeEnergyAmountId));
        int otherFrozenEnergy = Integer.valueOf( getText(  otherFreezeEnergyAmountId));
        int totalEnergyAmount = Integer.valueOf( getText(  totalFreezeEnergyAmountId));
        Assert.assertTrue(myFrozenEnergy + otherFrozenEnergy == totalEnergyAmount);

        //Freeze bandwidth detail
         testOperation(  freezeBandwidthDetailId,"click","Show bandwidth detail");
        int myFrozenBandwidth = Integer.valueOf( getText(  myFreezeBandwidthAmountid));
        int otherFrozenBandwidth = Integer.valueOf( getText(  otherFreezeBandwidthAmountId));
        int totalBandwidthAmount = Integer.valueOf( getText(  totalFreezeBandwidthAmountId));
        Assert.assertTrue(myFrozenBandwidth + otherFrozenBandwidth == totalBandwidthAmount);

        //Vote power
        int votingPower = Integer.valueOf( getText(  votingPowerInFreezeId));
        Assert.assertTrue(votingPower == myFrozenBandwidth + myFrozenEnergy);

        //Energy and bandwidth obtained detail
         swipeUp();
         testOperation(  bandwidthOptionIconId,"click","Choose bandwidth option");
         testOperation(  bandwidthQuestionId,"click","Try to check bandwidth question");
        String bandwidthQuestionContent =  getText(  bandwidthQuestionContentId);
        //Assert.assertTrue(bandwidthQuestionContent.contains("43_200_000_000"));
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

         testOperation(  energyOptionIconId,"click","Choose energy option");
         testOperation(  energyQuestionId,"click","Try to check energy question");
        String energyQuestionContent =  getText(  energyQuestionContentId);
        //Assert.assertTrue(energyQuestionContent.contains("50_000_000_000"));
        driver.pressKey(new KeyEvent(AndroidKey.BACK));

        //Balance in frozen screen equal

        int balanceInFrozenScreen = Integer.valueOf( getText(balanceInFrozenScreenId).substring( getText(  balanceInFrozenScreenId).indexOf(" ") + 1, getText(  balanceInFrozenScreenId).length() - 3));
        System.out.println("balanceInFrozenScreen:" + balanceInFrozenScreen);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        System.out.println( getText(  trxValueInAssetScreenId));
        int balanceInAssetScreen = Integer.valueOf( getText(  trxValueInAssetScreenId).substring(0, getText(  trxValueInAssetScreenId).length() - 4));
        Assert.assertTrue(balanceInFrozenScreen + votingPower == balanceInAssetScreen);
    }

    @AfterClass
    public void teardown(){
        driver.quit();
    }

}
