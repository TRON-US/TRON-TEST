package android.com.tronlink.wallet.import100account;

import android.com.wallet.UITest.base.Base;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.DiscoverPage;
import android.com.wallet.pages.MinePage;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


/**
 * run test before need modify xml to noReset = true
 */

public class AddMoreFavoritesTest extends Base {


    @BeforeClass(alwaysRun = true)
    public void setUpBefore() throws Exception {
        //TQ1EL7zJei3VePq5B6R6r8dcGHUTXrE4oe
        //b69c0ce7bcb061bb6a6d5c1582e7c42547c20421493ef9c623a6ec6f8a024647
//        new Helper().getSign("b69c0ce7bcb061bb6a6d5c1582e7c42547c20421493ef9c623a6ec6f8a024647", DRIVER);
    }


    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlinkpro.wallet");
        } catch (Exception e) {
        }
    }


    @Test(description = "import 100 address", alwaysRun = true)
    public void test001_AddMoreFavoritesTest() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        DiscoverPage page = asset.enterDiscoverPage();
        page.openDebugModel();
        page.gotoBrowserMainPage();
        for (int i = 0; i < 1; i++) {
            String url = RandomStringUtils.randomAlphanumeric(4).toLowerCase(Locale.ROOT);
            page.inputSearch(url + ".com");
            page.visitTheWeb();
            page.addFavorite();
            page.gotoBrowserMainPage();
        }

    }







}
