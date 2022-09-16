package ui.mobile.base;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class BaseTest extends Base {

    @FindBy(id = "com.tronlink.global:id/assets")
    public WebElement assetsMain_btn;

    @Parameters({"privateKey","platformName"})
    @BeforeClass(groups = {"P0"},alwaysRun = true)
    public void setUpBefore(String privateKey,String platformName) throws Exception {
        if (platformName.contains("iOS")){
//            new Helper().importFirstWallet(Helper.importType.normal,privateKey,IDRIVER);
        }else {
            importPrivateKey(privateKey);
        }

    }


    @Parameters({"bundleId","platformName"})
    @BeforeMethod(groups = {"P0"},alwaysRun = true)
    public void beforeMethod(String bundleId,String platformName,Method method) throws Exception {

        if (platformName.contains("iOS")){
            iosCloseAndActivateApp(bundleId);
        }else {
            androidCloseAndActiveApp();
        }

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>> Test case: " + method.getName());

    }

    @Parameters({"platformName"})
    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass(String platformName) {
        if (platformName.contains("iOS")){
            IDRIVER.quit();
        }else {
            ADRIVER.quit();
        }
    }



    public void iosCloseAndActivateApp(String bundleId){
        Map<String, Object> params = new HashMap<>();
        params.put("bundleId", bundleId);
        IDRIVER.executeScript("mobile: terminateApp", params);
        int tries = 0;
        Boolean driver_is_start = false;
        while (!driver_is_start && tries < 5) {
            tries++;
            try {
                IDRIVER.executeScript("mobile: activateApp", params);
                driver_is_start = true;
            } catch (Exception e) {
            }
        }
    }

    public void androidCloseAndActiveApp(){
        try {
            ADRIVER.closeApp();
            ADRIVER.activateApp("com.tronlink.global");
        }catch (Exception e){}
    }



    public void importPrivateKey(String testPrivateKey){

        try {
            assetsMain_btn.isDisplayed();
        }catch (Exception e){
            getSignOperate(testPrivateKey);
        }
    }

    public void getSignOperate(String testPrivateKey){
        try {
            findWebElement("com.tronlink.global:id/tv_import").click();
            swipUntilElementEnable("com.tronlink.global:id/bt_accept");
            findWebElement("com.tronlink.global:id/bt_accept").click();
            findWebElement("com.tronlink.global:id/cd_pk").click();
            findWebElement("com.tronlink.global:id/et_content").sendKeys(testPrivateKey);
            findWebElement("com.tronlink.global:id/bt_next").click();
            findWebElement("com.tronlink.global:id/et_name").sendKeys("Auto-test");
            findWebElement("com.tronlink.global:id/creat").click();
            findWebElement("com.tronlink.global:id/et_password").sendKeys("Test0001");
            TimeUnit.SECONDS.sleep(1);
            findWebElement("com.tronlink.global:id/creat").click();
            TimeUnit.SECONDS.sleep(1);
            findWebElement("com.tronlink.global:id/et_password").sendKeys("Test0001");
            findWebElement("com.tronlink.global:id/creat").click();
            TimeUnit.SECONDS.sleep(3);
            //校验是否导入成功
            System.out.println("开始校验是否导入成功");
            findWebElement("com.tronlink.global:id/assets_name");
            System.out.println("完成校验，导入成功");
        }catch (Exception e){
            System.out.println("\n-----------\n导入失败!!!!!\n");

            System.out.println(e);
        }

    }

    public WebElement findWebElement(String element) throws Exception {
        int tries = 0;
        Boolean Element_is_exist = false;
        WebElement el = null;
        while (!Element_is_exist && tries < 5) {
            tries++;
            try {
                ADRIVER.findElementById(element);
                Element_is_exist = true;
            }catch (NoSuchElementException e){
                TimeUnit.SECONDS.sleep(2);
            }
        }
        el = ADRIVER.findElementById(element);
        return el;
    }


    public void swipUntilElementEnable(String id) throws Exception{
        TimeUnit.SECONDS.sleep(1);
        while (!findWebElement(id).isEnabled()) {
            AndroidTouchAction action = new AndroidTouchAction(ADRIVER);
            int width = ADRIVER.manage().window().getSize().width;
            int height = ADRIVER.manage().window().getSize().height;
            Duration duration = Duration.ofMillis(200);
            action.press(
                    PointOption.point(width/2, height*4/5))
                    .waitAction(WaitOptions.waitOptions(duration))
                    .moveTo(PointOption.point(width/2, height/5))
                    .release().perform();
        }
    }
}
