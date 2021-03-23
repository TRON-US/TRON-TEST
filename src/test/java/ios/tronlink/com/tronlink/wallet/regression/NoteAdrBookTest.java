package ios.tronlink.com.tronlink.wallet.regression;

import android.com.utils.Configuration;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.MinePage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.NotebookHelpPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class NoteAdrBookTest extends BaseTest {
//TXqHdPhZLh4AZh7CK9Qv6JH6dyPFEv8dXW
    //TSG8ZCCFBA1WyuxkSrYPk4oPfkzCMqjFim

    public NotebookHelpPage enterAdrNoteBook() throws Exception{
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage minePage = assetPage.enterMinePage();
        minePage.driver.findElementById("地址本").click();
        TimeUnit.SECONDS.sleep(1);
        return new NotebookHelpPage(DRIVER);
    }


    @Test(groups = {"P0"},description = "import a address into notebook",alwaysRun = true)
    public void Test01_ImportAddressIntoNoteBookSuccess() throws Exception{
        NotebookHelpPage page = enterAdrNoteBook();
        page.importSuccess("TTwaSwvSQur5G54vMSTiu4AEuW4cdhrQzq");
        Assert.assertTrue(page.isAddress("TTwaSwvSQur5G54vMSTiu4AEuW4cdhrQzq"));
    }
    @Test(description = "import a address is exist",alwaysRun = true)
    public void Test02_ImportAddressIsExist() throws Exception{
        NotebookHelpPage page = enterAdrNoteBook();
        Assert.assertTrue(page.isName("successAdr"));
    }
    @Test(description = "import a double address Error",alwaysRun = true)
    public void Test03_ImportDoubleAddressErrorTest() throws Exception{
        NotebookHelpPage page = enterAdrNoteBook();
         Assert.assertTrue(page.importWrongAddr("TTwaSwvSQur5G54vMSTiu4AEuW4cdhrQzq").contains("地址已存在"));
    }
    @Test(description = "import a null name Error",alwaysRun = true)
    public void Test04_ImportNullNameErrorTest() throws Exception{
        NotebookHelpPage page = enterAdrNoteBook();
        Assert.assertTrue(page.inportNullName().contains("请输入名称"));
    }
    @Test(description = "import a long name Error",alwaysRun = true)
    public void Test05_ImportLongNameTest() throws Exception{
        NotebookHelpPage page = enterAdrNoteBook();
        Assert.assertTrue(page.inputLongName().length() == 20);
    }
    @Test(description = "import a wrong format address Error",alwaysRun = true)
    public void Test06_ImportWrongFormatAddressErrorTest() throws Exception{
        NotebookHelpPage page = enterAdrNoteBook();
        Assert.assertTrue(page.importWrongAddr("aT").contains("请输入正确的地址"));
    }
    @Test(groups = {"P0"},description = "modify address book name",alwaysRun = true)
    public void Test07_ModifyAddressBookNameTest() throws Exception{
        NotebookHelpPage page = enterAdrNoteBook();
        page.modifyNoteName("newNameAdr");
        Assert.assertTrue(page.isName("newNameAdr"));
    }
    @Test(groups = {"P0"},description = "modify address book address",alwaysRun = true)
    public void Test08_ModifyAddressBookAddressTest() throws Exception{
        NotebookHelpPage page = enterAdrNoteBook();
        page.modifyNoteAddress("TXqHdPhZLh4AZh7CK9Qv6JH6dyPFEv8dXW");
        Assert.assertTrue(page.isAddress("TXqHdPhZLh4AZh7CK9Qv6JH6dyPFEv8dXW"));
    }

    @Test(groups = {"P0"},description = "delete address book address",alwaysRun = true)
    public void Test09_DeleteAddressBookAddressTest() throws Exception{
        NotebookHelpPage page = enterAdrNoteBook();
        page.deleteNoteAddress();
        Assert.assertTrue(page.isNoDate());
    }


//    @Test(description = "test000_addressBookTest",alwaysRun = true)
//    public void test000_addressBookFunctionTest() throws Exception {
//        NotebookHelpPage addressBookPage =  enterAdrNoteBook();
//
//        int book = 1;
//        String addressString;
//        String addressName;
//        while (book < 30) {
//            if (book < 11){
//                addressString = Configuration.getByPath("testng.conf")
//                        .getString("androidMultiSignAccount.multiSign" + String.valueOf(book) + "Address");
//                addressName = "addressBook-" + String.valueOf(book);
//
//            }else if(book < 21){
//                addressString = Configuration.getByPath("testng.conf")
//                        .getString("iosMultiSignAccount.multiSign" + String.valueOf(book - 10) + "Address");
//                addressName = "addressBook-" + String.valueOf(book);
//
//            }else {
//                addressString = Configuration.getByPath("testng.conf")
//                        .getString("iosMultiSignAccount.owner" + String.valueOf(book-20) + "Address");
//                addressName = "addressBook-" + String.valueOf(book);
//
//            }
//            addressBookPage.importAddressAndNameSuccess(addressString,addressName);
//            book++;
//        }
//    }


}
