package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import ios.tronlink.com.tronlink.wallet.utils.Helper;

import java.util.concurrent.TimeUnit;

public class NotebookHelpPage extends AbstractPage {
    public IOSDriver<?> driver;
    public NotebookHelpPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;

    }
    //addressLabel 列表页 地址
    //copyBtn 列表页copybutton
    //editBtn 列表页  编辑按钮
    //addressNameLabel 列表页名称btn,包含一个图标
//deleteBtn
//textlabel  地址地方报错信息的id

    public void intoAddpage()throws Exception{
        driver.findElementById("addressBook add").click();
        TimeUnit.SECONDS.sleep(1);

    }
    public void inputNameTF(String name) throws Exception{
        driver.findElementById("nameField").sendKeys(name);
        Helper.closeKeyBoard(driver);
    }
    public void inputAddressTF(String addr) throws Exception{
        driver.findElementById("addressField").sendKeys(addr);
        Helper.closeKeyBoard(driver);
    }
    public void saveBtnClick() throws Exception{
        driver.findElementById("rightLabel").click();
        TimeUnit.SECONDS.sleep(2);
    }
    public void importSuccess(String addr) throws Exception{
        intoAddpage();
        inputNameTF("successAdr");
        inputAddressTF(addr);
        saveBtnClick();
    }
    public void importAddressAndNameSuccess(String addr,String name) throws Exception{
        intoAddpage();
        inputNameTF(name);
        inputAddressTF(addr);
        saveBtnClick();
    }
    public String  importWrongAddr(String addr) throws Exception{
        intoAddpage();
        inputAddressTF(addr);
        TimeUnit.SECONDS.sleep(2);
        return driver.findElementById("textlabel").getText();
    }
    public String  inportNullName() throws Exception{
        intoAddpage();
        driver.findElementById("nameField").sendKeys("s");
        driver.findElementById("nameField").clear();
        return driver.findElementById("textlabel").getText();
    }
    public String  inputLongName() throws Exception{
        intoAddpage();
        inputNameTF("123456789012345678901234560");
        return driver.findElementById("nameField").getText();
    }
    public void modifyNoteName(String name)throws Exception{
        driver.findElementById("editBtn").click();
        driver.findElementById("nameField").click();
        driver.findElementById("nameField").clear();
        driver.findElementById("nameField").sendKeys(name);
        Helper.closeKeyBoard(driver);
        saveBtnClick();
    }
    public void modifyNoteAddress(String name)throws Exception{
        driver.findElementById("editBtn").click();
        driver.findElementById("addressField").click();
        driver.findElementById("addressField").clear();
        driver.findElementById("addressField").sendKeys(name);
        Helper.closeKeyBoard(driver);
        saveBtnClick();
    }
    public void deleteNoteAddress()throws Exception{
        driver.findElementById("editBtn").click();
        driver.findElementById("deleteBtn").click();
        TimeUnit.SECONDS.sleep(1);
        try{
            driver.findElementByIosNsPredicate("type = 'XCUIElementTypeButton' AND name = '删除地址'").click();
        }catch (Exception e){
            System.out.println(e);
        }
        TimeUnit.SECONDS.sleep(2);
    }

    public boolean isNoDate(){
        try {
            if(driver.findElementByName("暂无数据").getText().contains("暂无数据")){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            log("note add success Or no El");
            return false;
        }
    }
    public boolean isName(String name){
        try {
            if(driver.findElementById("addressNameLabel").getText().contains(name)){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            log("note add success Or no El");
            return false;
        }
    }

    public boolean isAddress(String addr){
        try {
            if(driver.findElementById("addressLabel").getText().contains(addr)){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            log("note add success Or no El");
            return false;
        }
    }


}
