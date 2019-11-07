package ios.tronlink.com.tronlink.wallet.regression;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import ios.tronlink.com.tronlink.wallet.UITest.base.Base;
import ios.tronlink.com.tronlink.wallet.utils.Helper;

public class DemoTest extends Base {


    @Parameters({"privateKey"})
    @BeforeClass()
    public void setUpBefore(String privateKey) throws Exception {
        System.out.print("status:" + DRIVER.getBatteryInfo().getState() + "\n");
        DRIVER.getBatteryInfo().getState();
        //DRIVER.getBatteryInfo().toString();
        System.out.println("333333333");
        new Helper().getSign(privateKey,DRIVER);
    }


    @AfterClass(alwaysRun = true)
    public void tearDownAfterClass() {
        //Base.tearDownAfterClass();
        DRIVER.quit();
    }

    @Test //测试是否正常进行投票页
    public void test01_checkPopularSearch() throws Exception {

        System.out.print(DRIVER.getBatteryInfo());
    }





}
