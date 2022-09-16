package android.com.wallet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;

public class SearchAssertPage  extends AbstractPage {

    public AndroidDriver<?> driver;

    public SearchAssertPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }



    @FindBy(id = "com.tronlink.global:id/et_search")
    public WebElement addAssert_input;


    @FindBy(id = "com.tronlink.global:id/iv_switch")
    public WebElement turnAsset_btn;


    @FindBy(id = "com.tronlink.global:id/iv_common_left")
    public WebElement back_btn;


    @FindBy(id = "com.tronlink.global:id/iv_common_left")
    public WebElement back_s;



    @FindBy(id = "com.tronlink.global:id/tv_common_title")
    public WebElement search_btn;



    /**
     * turn on assert(open)
     */
    public void openAssert(){
        try {
            TimeUnit.SECONDS.sleep(1);
            if (turnAsset_btn.isSelected() == false){
                turnAsset_btn.click();
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    /**
     * turn off assert(close)
     */
    public void cloeAssert(){
        try {
            TimeUnit.SECONDS.sleep(1);
            if (turnAsset_btn.isSelected() == true){
                turnAsset_btn.click();
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public AddAssertPage enterAddAssertPage(){
        back_btn.click();
        try {
            TimeUnit.SECONDS.sleep(2);

        }catch (Exception e){
            System.out.println(e);
        }
        return new AddAssertPage(driver);
    }




}
