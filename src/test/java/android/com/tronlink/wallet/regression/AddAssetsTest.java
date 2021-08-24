package android.com.tronlink.wallet.regression;

import android.com.utils.Configuration;
import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AddAssertPage;
import android.com.wallet.pages.AddressBookPage;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.MinePage;
import android.com.wallet.pages.SearchAssertPage;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * add asset test
 */
public class AddAssetsTest extends Base {


    @Parameters({"privateKey"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey) throws Exception {
        new Helper().getSign(privateKey, DRIVER);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("wallet.tronlink.global");
        }catch (Exception e){}

    }

    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }

    @Test(description = "Add Asset Page Tip Test",alwaysRun = true)
    public void test001_AddAssetPageTipTest() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        AddAssertPage page = asset.enterAddAssertPage();
//        Assert.assertTrue(page.tipview.getText().contains("可移除不需要展示的资产"));
//        page.close.click();
//        Assert.assertTrue(page.et_search.getText().contains("名称/ID/合约地址"));
//        Assert.assertTrue(page.iv_search_icon.isEnabled());
//        Assert.assertTrue(page.isElementExist("首页资产管理"));
        Assert.assertTrue(page.isElementExist("我的全部资产"));
//        Assert.assertTrue(page.sort_type.getText().contains("按价值"));
//        Assert.assertTrue(page.tv_common_title.getText().contains("资产"));
//        Assert.assertTrue(page.accountnumbers.size() > 1);

    }




//    @Test(description = "Add Asset Page Tip Test",alwaysRun = true)
//    public void test000_addressbook30Test() throws Exception{
//        AssetPage asset = new AssetPage(DRIVER);
//        MinePage page = asset.enterMinePage();
//        AddressBookPage addressBookPage = page.enterAddressBookPage();
//
//        int book = 11;
//        while (book < 11){
//            log("iosMultiSignAccount.owner"+String.valueOf(book)+"Address");
//
//            String addressString =  Configuration.getByPath("testng.conf")
//                    .getString("androidMultiSignAccount.multiSign"+String.valueOf(book)+"Address");
//            log("find address: " + addressString);
//
//            addressBookPage.addAddressBook_btn.click();
//            TimeUnit.SECONDS.sleep(2);
//
//            Random rand = new Random();
//            String addressName = "address-" + String.valueOf(book);
//            addressBookPage.addName_input.sendKeys(addressName);
//            addressBookPage.addAddress_input.sendKeys(addressString);
////            addressBookPage.addNote_input.sendKeys(addressString);
//            addressBookPage.save_btn.click();
//            book++;
//        }
//
//        while (book < 21){
//            log("iosMultiSignAccount.owner"+String.valueOf(book)+"Address");
//
//                        String addressString = Configuration.getByPath("testng.conf")
//                    .getString("iosMultiSignAccount.multiSign" + String.valueOf(book-10) + "Address");
//            log("find address: " + addressString);
//
//            addressBookPage.addAddressBook_btn.click();
//            TimeUnit.SECONDS.sleep(2);
//
//            Random rand = new Random();
//            String addressName = "address-" + String.valueOf(book);
//            addressBookPage.addName_input.sendKeys(addressName);
//            addressBookPage.addAddress_input.sendKeys(addressString);
////            addressBookPage.addNote_input.sendKeys(addressString);
//            addressBookPage.save_btn.click();
//            book++;
//        }
//
//        while (book < 31){
//            log("iosMultiSignAccount.owner"+String.valueOf(book)+"Address");
//
//            String addressString = Configuration.getByPath("testng.conf")
//                    .getString("iosMultiSignAccount.owner" + String.valueOf(book-20) + "Address");
//            log("find address: " + addressString);
//
//            addressBookPage.addAddressBook_btn.click();
//            TimeUnit.SECONDS.sleep(2);
//
//            Random rand = new Random();
//            String addressName = "address-" +String.valueOf(book);
//            addressBookPage.addName_input.sendKeys(addressName);
//            addressBookPage.addAddress_input.sendKeys(addressString);
////            addressBookPage.addNote_input.sendKeys(addressString);
//            addressBookPage.save_btn.click();
//            book++;
//        }
//
//    }





}
