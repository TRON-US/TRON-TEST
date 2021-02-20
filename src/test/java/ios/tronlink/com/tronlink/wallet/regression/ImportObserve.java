package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.*;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ImportObserve extends BaseTest {

    String addressNew = "TXzBUz6qrD2AXmJsMVamTaWudoUsAPGqcG";


    public ObservePage enterImportObservePage() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        waiteTime();
        assetPage.addWallet_btn.click();

        waiteTime();
        DRIVER.findElementByName("观察钱包").click();
        TimeUnit.SECONDS.sleep(2);
        return new ObservePage(DRIVER);
    }

    @Test(description = "Observe Address Format Incorrect", alwaysRun = true)
    public void test001_importObserveFormatIncorrect() throws Exception {
        ObservePage importObservePage = enterImportObservePage();
        importObservePage.inputAddreseString("ecd4bbba178b1b0d2a0c1e6e9108e0cab8");
        TimeUnit.SECONDS.sleep(2);
        String hits =importObservePage.getError_hits();
        Assert.assertTrue(hits.contains("格式不合法"));
    }


    @Test(description = "Observe Address Is NULL", alwaysRun = true)
    public void test002_importObserveIsNull() throws Exception {
        ObservePage importObservePage = enterImportObservePage();
        Assert.assertFalse(importObservePage.getNext_btn().isEnabled());
    }


    @Test(description = "Observe Address Is Too Large", alwaysRun = true)
    public void test003_importObserveIsTooLarge() throws Exception {
        ObservePage importObservePage = enterImportObservePage();
        importObservePage.inputAddreseString("ecd4bbba178b1b0dskalkfdsadfasfsafsaffasdfsafdsaf2a0c1e6e9108e0cab8");
        String hits =importObservePage.getError_hits();
        Assert.assertTrue(hits.contains("格式不合法"));
    }
    @Parameters({"address"})
    @Test(description = "Observe Address Already haved", alwaysRun = true)
    public void test004_importObserveAlreadyhave(String address) throws Exception {
        ObservePage importObservePage = enterImportObservePage();
        importObservePage.inputAddreseString(address);
        String hits =importObservePage.getError_hits();
        Assert.assertTrue(hits.contains("钱包已存在"));
    }


    @Test(description = "Observe Name Too Long", alwaysRun = true)
    public void test005_ObserveNameTooLong() throws Exception {
        ObservePage importObservePage = enterImportObservePage();
        importObservePage.inputAddreseString(addressNew);
        PrivateKeySetNamePage namePage = importObservePage.enterPrivateKeySetNamePage();
        namePage.name_input.sendKeys("123456789012345");
        Helper.tapWhitePlace(DRIVER);
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(namePage.toolongname.isDisplayed());
    }
    @Test(description = "Observe Name chinese Too Long", alwaysRun = true)
    public void test006_ObserveNamechineseTooLong() throws Exception {
        ObservePage importObservePage = enterImportObservePage();
        importObservePage.inputAddreseString(addressNew);
        PrivateKeySetNamePage namePage = importObservePage.enterPrivateKeySetNamePage();
        namePage.setNameover("一二三四五六七超");
        Helper.tapWhitePlace(DRIVER);
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(namePage.toolongname.isDisplayed());

    }
    @Test(groups = {"P0"},description = "Observe Name Has Alerady Exist", alwaysRun = true)
    public void test007_walletNameHasAleradyExist() throws Exception {
        ObservePage importObservePage = enterImportObservePage();
        importObservePage.inputAddreseString(addressNew);
        PrivateKeySetNamePage namePage = importObservePage.enterPrivateKeySetNamePage();
        namePage.name_input.sendKeys("Auto_test");
        Helper.tapWhitePlace(DRIVER);
        TimeUnit.SECONDS.sleep(2);
        String hits = namePage.getError_hits();
        System.out.println(hits);
        Assert.assertTrue(hits.contains("钱包名称已存在") || hits.contains("already exists"));
    }

//    @Test(description = "Observe The same name but different capitalization", alwaysRun = true)
//    public void test008_ObservesameNameButDifferentCapitalization() throws Exception {
//        ObservePage importObservePage = enterImportObservePage();
//        importObservePage.inputAddreseString(addressNew);
//        PrivateKeySetNamePage namePage = importObservePage.enterPrivateKeySetNamePage();
//        namePage.name_input.sendKeys("AUto_test");
//        Helper.tapWhitePlace(DRIVER);
//        Assert.assertTrue(namePage.getComplish_btn().isEnabled());
//    }


// 不添加因为有风险，删除时候找不到元素就会导致其他案例失败
//    @Test(description = "test import Observe  Success",alwaysRun = true)
//    public  void test009_ObserveNameSetSuccess() throws Exception {
//        ObservePage importObservePage = enterImportObservePage();
//        importObservePage.inputAddreseString(addressNew);
//        PrivateKeySetNamePage namePage = importObservePage.enterPrivateKeySetNamePage();
//        namePage.name_input.sendKeys(wallet);
//        Helper.tapWhitePlace(DRIVER);
//        namePage.getComplish_btn().click();
//        TimeUnit.SECONDS.sleep(3);
//        System.out.println(DRIVER.findElementByName("trxLabel").getText().split(" ")[0]);
//        Assert.assertTrue(Integer.parseInt(DRIVER.findElementByName("trxLabel").getText().split(" ")[0]) == 0);
//    }

}
