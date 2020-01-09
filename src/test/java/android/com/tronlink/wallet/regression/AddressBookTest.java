package android.com.tronlink.wallet.regression;

import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AddAssertPage;
import android.com.wallet.pages.AddressBookPage;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.FriendInvitationPage;
import android.com.wallet.pages.MinePage;
import android.com.wallet.pages.SearchAssertPage;
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

    @Parameters({"privateKey","address"})
    @BeforeClass(alwaysRun = true)
    public void setUpBefore(String privateKey,String address) throws Exception {
        addressString = address;
        new Helper().getSign(privateKey, DRIVER);
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() throws Exception {
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlink.wallet");
        }catch (Exception e){}
    }

    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        try {
            DRIVER.quit();
        } catch (Exception e) {
        }
    }

    @Test(enabled = false,description = "Address book page test", alwaysRun = true)
    public void test001_enterAddressBookPage() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage minePage = asset.enterMinePage();
        AddressBookPage addressBookPage = minePage.enterAddressBookPage();
        Assert.assertTrue(addressBookPage.addressBook_title.getText().equals("地址本")
            || addressBookPage.addressBook_title.getText().equalsIgnoreCase("Address Book"));
        Assert.assertTrue(addressBookPage.addAddressBook_btn.isEnabled());
    }

    @Test(enabled = false,description = "Add address book test", alwaysRun = true)
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
        addressName = "addressBook-" + rand.nextInt(1000);
        addressBookPage.addName_input.sendKeys(addressName);

        addressBookPage.save_btn.click();
        Assert.assertTrue(addressBookPage.addressError_info.getText().equalsIgnoreCase("Please enter an address")
            || addressBookPage.addressError_info.getText().equals("请输入地址"));

        addressBookPage.addAddress_input.sendKeys(addressString);
        addressBookPage.addNote_input.sendKeys(addressString);
        addressBookPage.save_btn.click();
    }

    @Test(enabled = false,description = "Address information test", alwaysRun = true)
    public void test003_checkAddressInformation() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage minePage = asset.enterMinePage();
        AddressBookPage addressBookPage = minePage.enterAddressBookPage();
        addressBookPage.name_display.getText().equals(addressName);
        addressBookPage.address_display.getText().equals(addressString);
        addressBookPage.note_display.getText().equals(addressString);
    }


}
