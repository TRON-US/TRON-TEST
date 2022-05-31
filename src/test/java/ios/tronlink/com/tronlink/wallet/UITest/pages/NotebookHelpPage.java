package ios.tronlink.com.tronlink.wallet.UITest.pages;

import org.openqa.selenium.support.FindBy;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;

import io.appium.java_client.ios.IOSDriver;
import ios.tronlink.com.tronlink.wallet.utils.Helper;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class NotebookHelpPage extends AbstractPage {
    public IOSDriver<?> driver;
    public NotebookHelpPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;

    }

    @FindBy(id = "保存")
    public WebElement saveBtn ;

    @FindBy(id = "nameField")
    public WebElement nameField;
    
    @FindBy(id = "addressField")
    public WebElement addressField;
    
    @FindBy(id = "remarkField")
    public WebElement remarkField;

    //addressLabel 列表页 地址
    //copyBtn 列表页copybutton
    //editBtn 列表页  编辑按钮
    //addressNameLabel 列表页名称btn,包含一个图标
//deleteBtn
//textlabel  地址地方报错信息的id


    public void intoAddPage()throws Exception{
        driver.findElementById("addressBook add").click();
        TimeUnit.SECONDS.sleep(1);

    }
    public void inputNameTF(String name) throws Exception{
        nameField.sendKeys(name);
        closeKeyBoard();
    }
    public void inputAddressTF(String addr) throws Exception{
        addressField.sendKeys(addr);
        closeKeyBoard();
    }
    public void inputRemarkTF(String addr) throws Exception{
        remarkField.sendKeys(addr);
        closeKeyBoard();
    }



    public void saveBtnClick() throws Exception{
        saveBtn.click();
        TimeUnit.SECONDS.sleep(4);
    }

    
    public void importSuccess(String addr) throws Exception{
        inputNameTF("successAdr");
        inputAddressTF(addr);
        saveBtnClick();
    }
    public void importAddressAndNameSuccess(String addr,String name) throws Exception{
        inputNameTF(name);
        inputAddressTF(addr);
        saveBtnClick();
    }

    @FindBy(id = "textlabel")
    public List<WebElement> textlabels;

    public String  importWrongAddr(String addr) throws Exception{
        inputAddressTF(addr);
        TimeUnit.SECONDS.sleep(2);
        return driver.findElementById("textlabel").getText();
    }
    public String  inportNullName() throws Exception{
        nameField.sendKeys("s");
        nameField.clear();
        return driver.findElementById("textlabel").getText();
    }
    public String  inputLongName() throws Exception{
        inputNameTF("123456789012345678901234560");
        return nameField.getText();
    }

    public void modifyNoteName(String name)throws Exception{
        editBtn.click();
        TimeUnit.SECONDS.sleep(1);
        nameField.click();
        nameField.clear();
        nameField.sendKeys(name);
        closeKeyBoard();
        saveBtnClick();
    }
    
    @FindBy(name = "editBtn")
    public WebElement editBtn;

    public void modifyNoteAddress(String name)throws Exception{
        editBtn.click();
        TimeUnit.SECONDS.sleep(1);
        addressField.click();
        addressField.clear();
        addressField.sendKeys(name);
        closeKeyBoard();
        saveBtnClick();
    }
    
    @FindBy(id = "deleteBtn")
    public WebElement deleteBtn;
    public void deleteNoteAddress()throws Exception{
        editBtn.click();
        deleteBtn.click();
        TimeUnit.SECONDS.sleep(3);
//        driver.findElementByName("删除地址").click();
//        TimeUnit.SECONDS.sleep(2);
    }
    
    @FindBy(id = "addressNameLabel")
    public WebElement addressNameLabel;

    @FindBy(id = "addressLabel")
    public WebElement addressLabel;



}
