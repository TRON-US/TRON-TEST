package ios.tronlink.com.tronlink.wallet.UITest.base;

import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.touch.offset.PointOption;
import ios.tronlink.com.tronlink.wallet.UITest.pages.AssetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.MinePage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.NodeSetPage;
import ios.tronlink.com.tronlink.wallet.UITest.pages.SettingPage;
import ios.tronlink.com.tronlink.wallet.utils.Helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

public class BaseTest extends Base {

    @Parameters({"privateKey","bundleId"})
    @BeforeClass(groups = {"P0"},alwaysRun = true)
    public void setUpBefore(String privateKey,String bundleId) throws Exception {
        log("BaseTest --Begin");
        Map<String, Object> params = new HashMap<>();
        params.put("bundleId", bundleId);
        DRIVER.executeScript("mobile: terminateApp", params);
        TimeUnit.SECONDS.sleep(2);
        DRIVER.executeScript("mobile: activateApp", params);
        TimeUnit.SECONDS.sleep(2);
        log("BaseTest Import ---start");
        new Helper().importFirstWallet(Helper.importType.normal,privateKey,DRIVER);
        log("BaseTest Import ---Success");
    }

    @Parameters({"bundleId"})
    @AfterMethod(groups = {"P0"},alwaysRun = true)
    public void afterMethod(Method method, String bundleId) throws Exception {
        TimeUnit.SECONDS.sleep(2);
        Map<String, Object> params = new HashMap<>();
        params.put("bundleId", bundleId);
        DRIVER.executeScript("mobile: terminateApp", params);
        TimeUnit.SECONDS.sleep(2);

    }

    @Parameters({"bundleId"})
    @BeforeMethod(groups = {"P0"},alwaysRun = true)
    public void beforeMethod(String bundleId,Method method) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("bundleId", bundleId);
        int tries = 0;
        Boolean driver_is_start = false;
        while (!driver_is_start && tries < 5) {
            tries++;
            try {
                DRIVER.executeScript("mobile: activateApp", params);
                driver_is_start = true;
            } catch (Exception e) {
                System.out.println(e);
                DRIVER.executeScript("mobile: terminateApp", params);
                TimeUnit.SECONDS.sleep(2);
            }
        }
        TimeUnit.SECONDS.sleep(2);
    }

    @Parameters({"bundleId"})
    @AfterClass (groups = {"P0"},alwaysRun = true)
    public void afterClass(String bundleId) throws Exception {

        try {
            DRIVER.quit();
        } catch (Exception e) {
        }

//        Map<String, Object> params = new HashMap<>();
//        params.put("bundleId", bundleId);
//        DRIVER.executeScript("mobile: terminateApp", params);
//        TimeUnit.SECONDS.sleep(2);
    }




    public void guaranteeDAppChain() throws Exception{
        AssetPage asset = new AssetPage(DRIVER);
        if(Helper.assetFindMainChain(asset)) {
            MinePage mine = asset.enterMinePage();
            SettingPage set =  mine.enterSettingPage();
            set.changeToDAppChain();
            TimeUnit.SECONDS.sleep(1);
            set.enterMinePage();
            mine.enterAssetPage();
        }
    }

    public  void guaranteeShastaChain(String udid) throws  Exception{
        AssetPage asset = new AssetPage(DRIVER);
        if (asset.chainNameLabel.getText().contains("Shasta Testnet")){

        }else {
            MinePage mine = asset.enterMinePage();
            SettingPage set = mine.enterSettingPage();
            set.changeToShastaChain();
            TimeUnit.SECONDS.sleep(1);
            restartApp(udid);

        }
    }

    public void restartApp(String bundleId) throws Exception{
        Map<String, Object> params = new HashMap<>();
        params.put("bundleId", bundleId);
        DRIVER.executeScript("mobile: terminateApp", params);
        TimeUnit.SECONDS.sleep(6);
        DRIVER.executeScript("mobile: activateApp", params);
    }

    public String timeYMD(){
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        return  ft.format(dNow);
    }
}
