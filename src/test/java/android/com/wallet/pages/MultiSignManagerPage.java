package android.com.wallet.pages;

import android.com.utils.Helper;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.android.AndroidDriver;

public class MultiSignManagerPage extends AbstractPage {

    public AndroidDriver<?> driver;

    public MultiSignManagerPage(AndroidDriver<?> driver) {
        super(driver);
        this.driver = driver;
    }


//    @FindBy(id = "com.tronlinkpro.wallet:id/btn_asset_confirm")
//    public WebElement addPermission_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/tv_add")
    public WebElement addPermission_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/iv_qr")
    public WebElement question_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_permission_name")
    public WebElement permissionName_text;


    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.TextView")
    public WebElement question_hits;


    @FindBy(id = "com.tronlinkpro.wallet:id/ll_more")
    public WebElement more_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/ll_edit")
    public WebElement edit_btn;

    @FindBy(id = "com.tronlinkpro.wallet:id/iv_more")
    public WebElement iv_more;

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


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_ok")
    public WebElement delConfirm_btn;



    @FindBy(id = "com.tronlinkpro.wallet:id/et_new_password")
    public WebElement password_input;


    @FindBy(id = "com.tronlinkpro.wallet:id/bt_send")
    public WebElement send_btn;


    @FindBy(id = "com.tronlinkpro.wallet:id/tv_common_title")
    public WebElement title_text;


//    @FindBy(id = "com.tronlinkpro.wallet:id/ll_indicator")
//    public WebElement mulSign_span;


    @FindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.TextView")
    public List<WebElement> mulSign_span;





    public AddPermissionPage enterAddPermissionPage(){
        try {
            addPermission_btn.click();
            TimeUnit.SECONDS.sleep(2);
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

        iv_more.click();
        TimeUnit.SECONDS.sleep(2);
        findElementByText("删除权限").click();
        TimeUnit.SECONDS.sleep(1);
        delConfirm_btn.click();
        untilElementEnable(findElementByText("确认"));
        findElementByText("确认").click();
        password_input.sendKeys("Test0001");
        send_btn.click();
        TimeUnit.SECONDS.sleep(6);
    }


    public String getName(){
        return "active";
    }

    public void  swipLeftTimes(){
        for (int i=0;i<=7;i++){
            Helper.swipeLeftScreen(driver);
        }
    }

    public ModifyPermissionPage enterModifyPermissionPage() throws Exception {
        swipLeftTimes();
        iv_more.click();
        TimeUnit.SECONDS.sleep(2);
        findElementByText("修改权限").click();
        TimeUnit.SECONDS.sleep(1);
        return new ModifyPermissionPage(driver);
    }



    public ModifyPermissionPage enterModifyPage() throws Exception {
        swipLeftTimes();
        TimeUnit.SECONDS.sleep(1);
        iv_more.click();
        TimeUnit.SECONDS.sleep(1);
        edit_btn.click();
        TimeUnit.SECONDS.sleep(1);
        return new ModifyPermissionPage(driver);
    }


}
