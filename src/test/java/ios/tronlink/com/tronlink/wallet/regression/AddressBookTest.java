package ios.tronlink.com.tronlink.wallet.regression;

import ios.tronlink.com.tronlink.wallet.UITest.base.BaseTest;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.MinePage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.NotebookHelpPage;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AddressBookTest extends BaseTest {
//TXqHdPhZLh4AZh7CK9Qv6JH6dyPFEv8dXW
    //TSG8ZCCFBA1WyuxkSrYPk4oPfkzCMqjFim

    public String addressBookAddress = "TTwaSwvSQur5G54vMSTiu4AEuW4cdhrQzq";

    public NotebookHelpPage enterAdrNoteBook() throws Exception{
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage mine = assetPage.enterMinePage();
        mine.enterAddressBook();
        mine.enterBookAdd();
        return new NotebookHelpPage(DRIVER);
    }

    public NotebookHelpPage enterAddressBook() throws Exception{
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage mine = assetPage.enterMinePage();
        mine.enterAddressBook();
        TimeUnit.SECONDS.sleep(2);
        return new NotebookHelpPage(DRIVER);
    }


    @Test(alwaysRun = true)
    public void Test001_addressBookTest() throws Exception {
        AssetPage assetPage = new AssetPage(DRIVER);
        MinePage mine =  assetPage.enterMinePage();
        mine.enterAddressBook();
        Assert.assertTrue(isElementExist("暂无数据"));
        mine.enterBookAdd();
        Assert.assertTrue(isElementExist("添加地址"));
        Assert.assertTrue(isElementExist("nameField"));
        Assert.assertTrue(isElementExist("addressField"));
        Assert.assertTrue(isElementExist("remarkField"));
        Assert.assertTrue(isElementExist("addressClearBtn"));
    }


    @Test(groups = {"P0"},description = "import a address into notebook",alwaysRun = true)
    public void Test002_ImportAddressIsBooktest() throws Exception{
        NotebookHelpPage page = enterAdrNoteBook();
        page.importSuccess(addressBookAddress);
        Assert.assertEquals(page.addressLabel.getText(),addressBookAddress);
    }

    @Test(description = "import a address is exist",alwaysRun = true)
    public void Test003_ImportAddressIsExist() throws Exception{
        NotebookHelpPage page = enterAdrNoteBook();
        page.importAddressAndNameSuccess(addressBookAddress,"successAdr");
        Assert.assertTrue(page.textlabels.get(0).getText().contains("名称已存在")&&page.textlabels.get(1).getText().contains("地址已存在"));

    }

    @Test(description = "import a double address Error",alwaysRun = true)
    public void Test004_ImportDoubleAddressErrorTest() throws Exception{
        NotebookHelpPage page = enterAdrNoteBook();
        Assert.assertTrue(page.importWrongAddr(addressBookAddress).contains("地址已存在"));
    }

    @Test(description = "import a double address Error",alwaysRun = true)
    public void Test005_ImportAddressErrorTest() throws Exception{
        NotebookHelpPage page = enterAdrNoteBook();
        Assert.assertTrue(page.importWrongAddr(addressBookAddress+"longlong").contains("请输入正确的地址"));
    }


    @Test(description = "import a null name Error",alwaysRun = true)
    public void Test006_ImportNullNameErrorTest() throws Exception{
        NotebookHelpPage page = enterAdrNoteBook();
        Assert.assertTrue(page.inportNullName().contains("请输入名称"));
    }
    @Test(description = "import a long name Error",alwaysRun = true)
    public void Test007_ImportLongNameTest() throws Exception{
        NotebookHelpPage page = enterAdrNoteBook();
        Assert.assertTrue(page.inputLongName().contains("12345678901234567890"));
    }

    @Test(groups = {"P0"},description = "modify address book name",alwaysRun = true)
    public void Test008_ModifyAddressBookNameTest() throws Exception{
        NotebookHelpPage page = enterAddressBook();
        page.modifyNoteName("newNameAdr");
        Assert.assertTrue(isElementExist("newNameAdr"));
    }

    @Test(groups = {"P0"},description = "modify address book address",alwaysRun = true)
    public void Test009_ModifyAddressBookAddressTest() throws Exception{
        NotebookHelpPage page = enterAddressBook();
        page.modifyNoteAddress("TXqHdPhZLh4AZh7CK9Qv6JH6dyPFEv8dXW");
        Assert.assertEquals(page.addressLabel.getText(),"TXqHdPhZLh4AZh7CK9Qv6JH6dyPFEv8dXW");
    }

    @Test(groups = {"P0"},description = "delete address book address",alwaysRun = true)
    public void Test010_DeleteAddressBookAddressTest() throws Exception{
        NotebookHelpPage page = enterAddressBook();
        page.deleteNoteAddress();
        Assert.assertTrue(isElementExist("暂无数据"));
    }

    @Parameters({"address"})
    @Test(alwaysRun = true)
    public void test011_AddAutoTestAddress(String address) throws Exception {
        NotebookHelpPage page = enterAdrNoteBook();
        page.importAddressAndNameSuccess(address,"TestBook");
        Assert.assertTrue(page.addressLabel.getText().contains(address));
        Assert.assertTrue(page.addressNameLabel.getText().contains("TestBook"));

    }

}
