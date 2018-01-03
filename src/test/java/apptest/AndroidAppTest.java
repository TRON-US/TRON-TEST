package apptest;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.URL;

public class AndroidAppTest {

    AndroidDriver driver = null;
    DesiredCapabilities dc = new DesiredCapabilities();
    String testName = "Testing Android App with Java";
    String accessKey = "eyJ4cC51IjozMDAsInhwLnAiOjI5MywieHAubSI6Ik1UVXhNakk1TURjeE5URTVNUSIsImFsZyI6IkhTMjU2In0.eyJleHAiOjE4Mjg0NDE0NTQsImlzcyI6ImNvbS5leHBlcml0ZXN0In0.YjmUogdZQJwXHfybjoctPi-C1JCKeCLDjl5R-1kpako";

    @BeforeClass
    public void setUp() throws IOException {

        System.out.println("Starting the execution of the tests");
        dc.setCapability("testName", testName);
        dc.setCapability("accessKey", accessKey);
        //install the app on the device
        dc.setCapability(MobileCapabilityType.APP, "http://d242m5chux1g9j.cloudfront.net/eribank.apk");
        //get an Android device
        dc.setCapability("platformName", "Android");
        //launch the app
        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.experitest.ExperiBank");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".LoginActivity");
        driver = new AndroidDriver(new URL("https://beta.seetest.io:443/wd/hub"), dc);
        System.out.println("Created first driver");
    }

    @Test
    public void testYourAndroidApp() {
        driver.findElement(By.xpath("//*[@id='usernameTextField']")).sendKeys("company");
        driver.findElement(By.xpath("//*[@id='passwordTextField']")).sendKeys("company");
        driver.findElement(By.xpath("//*[@id='loginButton']")).click();
        driver.findElement(By.xpath("//*[@id='makePaymentButton']")).click();
        driver.findElement(By.xpath("//*[@id='phoneTextField']")).sendKeys("123456");
        driver.findElement(By.xpath("//*[@id='nameTextField']")).sendKeys("Test");
        driver.findElement(By.xpath("//*[@id='amountTextField']")).sendKeys("10");
        driver.findElement(By.xpath("//*[@id='countryTextField']")).sendKeys("US");
        driver.hideKeyboard();
        driver.findElement(By.xpath("//*[@id='sendPaymentButton']")).click();
        driver.findElement(By.xpath("//*[@id='button1']")).click();


    }

    @AfterClass
    public void tearDown() {
        if (driver != null)
        {
            driver.quit();
            System.out.println("Report URL : " + driver.getCapabilities().getCapability("reportUrl"));

        }
    }

}
