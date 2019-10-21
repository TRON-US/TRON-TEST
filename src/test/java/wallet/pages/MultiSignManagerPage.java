package wallet.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;

public class MultiSignManagerPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public MultiSignManagerPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


    @FindBy(id = "com.tronlink.wallet:id/bt_go")
    public WebElement addPermission_btn;


    @FindBy(id = "com.tronlink.wallet:id/iv_qr")
    public WebElement question_btn;


    @FindBy(id = "com.tronlink.wallet:id/tv_permission_name")
    public WebElement permissionName_text;


    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.TextView")
    public WebElement question_hits;


    @FindBy(id = "com.tronlink.wallet:id/ll_more")
    public WebElement more_btn;


//    @FindBy(id = "/hierarchy/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout/android.widget.LinearLayout[2]/android.widget.TextView")
//    public WebElement delSign_btn;

    @FindBy(xpath = "//*[@text='删除权限']")
    public WebElement delConfirmC_btn;

    @FindBy(xpath = "//*[@text='Delete permission']")
    public WebElement delConfirmE_btn;


    @FindBy(xpath = "//*[@text='修改权限']")
    public WebElement modifyConfirmC_btn;


    @FindBy(xpath = "//*[@text='Modify permissions']")
    public WebElement modifyConfirmE_btn;


    @FindBy(id = "com.tronlink.wallet:id/tv_ok")
    public WebElement delConfirm_btn;



    @FindBy(id = "com.tronlink.wallet:id/et_new_password")
    public WebElement password_input;


    @FindBy(id = "com.tronlink.wallet:id/bt_send")
    public WebElement send_btn;





    public AddPermissionPage enterAddPermissionPage(){
        try {
            addPermission_btn.click();
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e){
            System.out.println(1);
        }
        return new AddPermissionPage(driver);
    }


    public String questionClick(){
        String content = "";
        try {
            question_btn.click();
            TimeUnit.SECONDS.sleep(1);
            content = question_hits.getText();
        }catch (Exception e){
            System.out.println(e);
        }
        return content;
    }


    public void delSign() throws Exception{
        more_btn.click();
        TimeUnit.SECONDS.sleep(2);
        try {
            delConfirmC_btn.click();
        }catch (Exception e){
            delConfirmE_btn.click();
        }
        TimeUnit.SECONDS.sleep(1);
        delConfirm_btn.click();
        TimeUnit.SECONDS.sleep(1);
        password_input.sendKeys("Test0001");
        send_btn.click();
        TimeUnit.SECONDS.sleep(2);
    }


    public ModifyPermissionPage enterModifyPermissionPage() throws Exception {
        more_btn.click();
        TimeUnit.SECONDS.sleep(2);
        try {
            modifyConfirmC_btn.click();
        }catch (Exception e){
            modifyConfirmE_btn.click();
        }
        TimeUnit.SECONDS.sleep(1);
        return new ModifyPermissionPage(driver);
    }





}
