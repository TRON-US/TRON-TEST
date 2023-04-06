package android.com.tronlink.wallet.regression;

import android.com.utils.Configuration;
import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AddAssertPage;
import android.com.wallet.pages.AddressBookPage;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.FriendInvitationPage;
import android.com.wallet.pages.FrozenAndUnfreezePage;
import android.com.wallet.pages.MinePage;
import android.com.wallet.pages.SearchAssertPage;
import android.com.wallet.pages.SendTrxPage;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * 关于地址本功能测试
 */
public class AddressBookTest extends Base {

    String addressName;
    String addressString;
    static String shieldAddress = Configuration.getByPath("testng.conf")
        .getString("foundationAccount.shieldAddress");

    @Parameters({"privateKey","address"})
    @BeforeClass(groups = {"P0"},alwaysRun = true)
    public void setUpBefore(String privateKey,String address) throws Exception {
        addressString = "TQJtMKHsgLytLmRo7KXwhsT39Pa6mCbHFq";
        new Helper().getSign(privateKey, DRIVER);
    }

    @AfterMethod(groups = {"P0"},alwaysRun = true)
    public void afterMethod() throws Exception {
        try {
            TimeUnit.SECONDS.sleep(2);
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlinkpro.wallet");
        }catch (Exception e){}
    }

    @AfterClass(groups = {"P0"},alwaysRun = true)
    public void tearDownAfterClass() {
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }

    @Test(enabled = true,description = "Address book page test", alwaysRun = true)
    public void test001_enterAddressBookPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage minePage = asset.enterMinePage();
        AddressBookPage addressBookPage = minePage.enterAddressBookPage();
        Assert.assertTrue(addressBookPage.nav_title.getText().equals("地址本")
            || addressBookPage.addressBook_title.getText().equalsIgnoreCase("Address Book"));
        Assert.assertTrue(addressBookPage.addAddressBook_btn.isEnabled());

    }

    @Test(groups = {"P0"},enabled = true,description = "Add address book test", alwaysRun = true)
    public void test002_addAddressBook() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage minePage = asset.enterMinePage();
        AddressBookPage addressBookPage = minePage.enterAddressBookPage();
        addressBookPage.addAddressBook_btn.click();
        TimeUnit.SECONDS.sleep(2);
        Assert.assertTrue(addressBookPage.addressBook_title.getText().equals("添加地址")
            || addressBookPage.addressBook_title.getText().equalsIgnoreCase("Add Address"));


        addressBookPage.save_btn.click();
        Assert.assertTrue(addressBookPage.nameError_info.getText().equalsIgnoreCase("Please enter a name")
        || addressBookPage.nameError_info.getText().equals("请输入名称"));

        Random rand = new Random();
        addressName = "Book" + rand.nextInt(10);
        addressBookPage.addName_input.sendKeys(addressName);

        addressBookPage.save_btn.click();
        Assert.assertTrue(addressBookPage.addressError_info.getText().equalsIgnoreCase("Please enter an address")
            || addressBookPage.addressError_info.getText().equals("请输入地址"));

        addressBookPage.addAddress_input.sendKeys(addressString);
        addressBookPage.addNote_input.sendKeys(addressString);
        addressBookPage.save_btn.click();
    }

    @Test(enabled = true,description = "Address information test", alwaysRun = true)
    public void test003_checkAddressInformation() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage minePage = asset.enterMinePage();
        AddressBookPage addressBookPage = minePage.enterAddressBookPage();
        Assert.assertTrue(addressBookPage.name_display.getText().equals(addressName));
        Assert.assertTrue(addressBookPage.address_display.getText().equals(addressString));
        Assert.assertTrue(addressBookPage.note_display.getText().equals(addressString));
        Assert.assertTrue(addressBookPage.edit_btn.isEnabled());
        Assert.assertTrue(addressBookPage.copy_btn.isEnabled());
    }

    @Test(enabled = true,description = "Send coin use address book test", alwaysRun = true)
    public void test004_sendCoinUseAddressBook() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage transfer = enterToSendTrxPage();
        transfer.addressBookTab().click();
        Assert.assertTrue(transfer.tv_name.getText().contains(addressName));
    }

    @Test(groups = {"P0"},enabled = true,description = "Delete address book test", alwaysRun = true)
    public void test006_DeleteAddressBook() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage minePage = asset.enterMinePage();
        AddressBookPage addressBookPage = minePage.enterAddressBookPage();
        addressBookPage.name_display.click();
        addressBookPage.deleteAddress_btn.click();
        Assert.assertTrue(!addressBookPage.addAddress_input.getText().equals(addressString));
        Assert.assertTrue(addressBookPage.scan_btn.isEnabled());

        addressBookPage.deleteBook_btn.click();
        Assert.assertTrue(addressBookPage.deleteConfirm_btn.isEnabled());
        Assert.assertTrue(addressBookPage.deleteCancle_btn.isEnabled());
        addressBookPage.deleteCancle_btn.click();
        Assert.assertTrue(addressBookPage.save_btn.isEnabled());

        addressBookPage.deleteBook_btn.click();
        addressBookPage.deleteConfirm_btn.click();
        Assert.assertTrue(addressBookPage.dataInfo_display.getText().equals("暂无数据")
        || addressBookPage.dataInfo_display.getText().equalsIgnoreCase("No data"));
    }


    @Test(enabled = true,description = "Add shield address book test", alwaysRun = true)
    public void test007_addShieldAddressBook() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage minePage = asset.enterMinePage();
        AddressBookPage addressBookPage = minePage.enterAddressBookPage();
        addressBookPage.addAddressBook_btn.click();
        TimeUnit.SECONDS.sleep(2);

        Random rand = new Random();
        addressName = "shield-" + rand.nextInt(1000);
        addressBookPage.addName_input.sendKeys(addressName);

        addressBookPage.addAddress_input.sendKeys(shieldAddress);
        addressBookPage.addNote_input.sendKeys(shieldAddress);
        addressBookPage.save_btn.click();
    }


    public SendTrxPage enterToSendTrxPage() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        SendTrxPage transfer = asset.enterSendTrxPage();
        return transfer;
    }


}
