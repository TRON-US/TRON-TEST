package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.ios.IOSDriver;
import ios.tronlink.com.tronlink.wallet.utils.Helper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class NodeSetHelperPage extends AbstractPage {
    public IOSDriver<?> driver;

    public NodeSetHelperPage(IOSDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }

    //chainIP 节点选择页面 ip内容
    //chainName 节点选择页面 名称

    //nodeNameLabel  节点页面IP
    //nodeLatencyContentLabel  节点页面 延时
    @FindBy(name = "black path")
    public WebElement backBtn;

    @FindBy(id = "添加自定义节点")
    public WebElement addNewNodeBtn;

    @FindBy(id = "comfirmBtn")
    public WebElement comfirmBtn;

    @FindBy(id = "deleteBtn")
    public WebElement deleteBtn;

    @FindBy(id = "Solidity Node")
    public WebElement  SolidityNode;

    @FindBy(id = "nodeEditButton")
    public WebElement nodeEditButton;

    @FindBys({
            @FindBy(className = "XCUIElementTypeButton" ),
            @FindBy(name = "添加自定义节点")
    })
    public WebElement test;

//    @FindAll(FindBy)
////    @FindBys(id = "nodeLatencyContentLabel")


    public boolean enterNote()throws Exception{
        TimeUnit.SECONDS.sleep(1);
        addNewNodeBtn.click();
        try {
            driver.findElementByName("添加自定义节点").getLocation();
            return true;
        }catch (Exception e){
            return false;
        }

    }


    public boolean addNote()throws Exception{
        waiteTime(8);
        addNewNodeBtn.click();
        waiteTime();
        driver.findElementsByClassName("XCUIElementTypeTextField").get(0).sendKeys("192.168.1.1");
        waiteTime();
        driver.findElementsByClassName("XCUIElementTypeTextField").get(1).sendKeys("5000");
        waiteTime();
        Helper.closeKeyBoard(driver);
        comfirmBtn.click();
        TimeUnit.SECONDS.sleep(2);//必须这么用,因为要等待页面跳转
        try {

            driver.findElementByName("节点IP").getLocation();
            log("没有退出页面");
            return false;
        }catch (Exception e){
            return true;
        }

    }

    public boolean deleteNode()throws Exception{
        waiteTime();
        Helper.swipScreen(driver);
        waiteTime();
        Helper.TapLocationWL(driver,nodeEditButton);
        waiteTime();
        deleteBtn.click();
        TimeUnit.SECONDS.sleep(2);
        try {

            nodeEditButton.getLocation();
            return false;
        }catch (Exception e){
            return true;
        }

    }
    public boolean deleteSolidityNode()throws Exception{
        TimeUnit.SECONDS.sleep(1);
        SolidityNode.click();
        Helper.swipScreen(driver);
        TimeUnit.SECONDS.sleep(3);
        nodeEditButton.click();
        TimeUnit.SECONDS.sleep(1);
        deleteBtn.click();
        TimeUnit.SECONDS.sleep(1);
        try {
            nodeEditButton.getLocation();
            return false;
        }catch (Exception e){
            return true;
        }
    }
    //

    public boolean ipwrong()throws Exception{
        waiteTime();
        addNewNodeBtn.click();
        waiteTime();
        driver.findElementsByClassName("XCUIElementTypeTextField").get(0).sendKeys("192");
        Helper.closeKeyBoard(driver);
        return  driver.findElementById("errorStr").getText().contains("请输入正确的 IP 格式");
    }

    public boolean portwrong()throws Exception{
        waiteTime();
        addNewNodeBtn.click();
        waiteTime();
        driver.findElementsByClassName("XCUIElementTypeTextField").get(1).sendKeys("65555");
        Helper.closeKeyBoard(driver);
        return  driver.findElementById("errorStr").getText().contains("请输入正确的端口格式");
    }

    //ic arrow drop
    //XCUIElementTypeCell
    public boolean haveSolidity()throws Exception{
        waiteTime();
        addNewNodeBtn.click();
        waiteTime();
        driver.findElementById("ic arrow drop").click();
        waiteTime();
        return  driver.findElementsByClassName("XCUIElementTypeCell").size() == 2;
    }

    public boolean addSolidityNode()throws Exception{
        waiteTime();
        addNewNodeBtn.click();
        waiteTime();
        driver.findElementById("ic arrow drop").click();
        driver.findElementsByClassName("XCUIElementTypeCell").get(1).click();
        driver.findElementsByClassName("XCUIElementTypeTextField").get(0).sendKeys("192.168.1.1");
        driver.findElementsByClassName("XCUIElementTypeTextField").get(1).sendKeys("5000");
        Helper.closeKeyBoard(driver);
        comfirmBtn.click();
        TimeUnit.SECONDS.sleep(1);
        try {
            driver.findElementByName("节点类型").getLocation();
            return false;
        }catch (Exception e){
            return true;
        }

    }




    public boolean ipwrongSolidity()throws Exception{
        waiteTime();
        addNewNodeBtn.click();
        waiteTime();
        driver.findElementById("ic arrow drop").click();
        waiteTime();
        driver.findElementsByClassName("XCUIElementTypeCell").get(1).click();
        waiteTime();
        driver.findElementsByClassName("XCUIElementTypeTextField").get(0).sendKeys("192");
        Helper.closeKeyBoard(driver);
        return  driver.findElementById("errorStr").getText().contains("请输入正确的 IP 格式");
    }

    public boolean portwrongSolidity()throws Exception{
        waiteTime();
        addNewNodeBtn.click();
        waiteTime();
        driver.findElementById("ic arrow drop").click();
        waiteTime();
        driver.findElementsByClassName("XCUIElementTypeCell").get(1).click();
        waiteTime();
        driver.findElementsByClassName("XCUIElementTypeTextField").get(1).sendKeys("65555");
        Helper.closeKeyBoard(driver);
        return  driver.findElementById("errorStr").getText().contains("请输入正确的端口格式");
    }


    public boolean SelectSecondNode() throws Exception {
        TimeUnit.SECONDS.sleep(1);
        String selip;
//        if (timeLabels.get(1).getText().contains("连接超时")) {
//            if (timeLabels.get(2).getText().contains("连接超时")) {
//                driver.findElementsByClassName("XCUIElementTypeCell").get(3).click();
//                selip = driver.findElementsById("nodeNameLabel").get(3).getText();
//            } else {
//                driver.findElementsByClassName("XCUIElementTypeCell").get(2).click();
//                selip = driver.findElementsById("nodeNameLabel").get(2).getText();
//            }
//        } else {
//            driver.findElementsByClassName("XCUIElementTypeCell").get(1).click();
//            selip = driver.findElementsById("nodeNameLabel").get(1).getText();
//
//        }
//        try {
        driver.findElementsByClassName("XCUIElementTypeCell").get(1).click();
        TimeUnit.SECONDS.sleep(2);
        selip = driver.findElementsById("nodeNameLabel").get(1).getText();
        log(selip);
//        }catch (Exception e){
//            driver.findElementsByClassName("XCUIElementTypeCell").get(0).click();
//            selip = driver.findElementsById("nodeNameLabel").get(0).getText();
//        }
        TimeUnit.SECONDS.sleep(2);
        backBtn.click();
        TimeUnit.SECONDS.sleep(1);
        return driver.findElementById("chainIP").getText().contains(selip);

    }
}
