package ios.tronlink.com.tronlink.wallet.UITest.pages;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AbstractPage {

    @FindBy(className = "XCUIElementTypeStaticText")
    public List<WebElement> textArray;

    @FindBy(name = "white back arrow")
    public WebElement BackBtn_White;

    @FindBy(name = "前往配对 >")
    public WebElement goPairBtn;

    public void goPair(){
        goPairBtn.click();
    }

    @FindBy(name = "前往配对")
    public WebElement pairCold;

    public void enterPairColdWalletPage() throws Exception {
        pairCold.click();
        TimeUnit.SECONDS.sleep(1);
    }


    public IOSDriver<?> driver;
    private SimpleDateFormat timeStamp = new SimpleDateFormat("yyyy MM dd_ HH:mm:ss ");

    public WebElement queding_btn(){
        return driver.findElementByIosNsPredicate("type='XCUIElementTypeButton' AND name = '确定'");
    }
    public WebElement confirm_btn(){
        return driver.findElementByIosNsPredicate("type='XCUIElementTypeButton' AND name = '确认'");
    }

    @FindBy(xpath = "(//XCUIElementTypeButton[@name=\"确认\"])[1]")
    public WebElement confirmButton;




    public WebElement confirmWatchBtn(){
        return driver.findElementByIosNsPredicate("type='XCUIElementTypeButton' AND name = '生成交易二维码'");
    }


    public WebElement sendAction_btn(){
        return driver.findElementByIosNsPredicate("type='XCUIElementTypeButton' AND name = '发送'");
    }

    public WebElement getConfirm_btn(){
        return driver.findElementByIosNsPredicate("type='XCUIElementTypeButton' AND name = '完成'");
    }

    public String removeSymbol(String arg) {
        String value = arg;
        if (arg.contains(",")) {
            value = arg.replace(",", "");
        }
        return value;
    }

    public String removeSymbolNoDot(String arg) {
        String value = arg;
        if (arg.contains(",")) {
            value = arg.replace(",", "");
        }
        return value;
    }

    @FindBy(id = "waitingCell")
    public List<WebElement> waitingCells;

    @FindBy(name = "typeLabel")
    public WebElement typeLabel;

    @FindBy(xpath = "//XCUIElementTypeButton[@name=\"下一步\"]")
    public WebElement nextBtn;

    @FindBy(className = "XCUIElementTypeCell")
    public WebElement getFirstCell;


    @FindBy(className = "XCUIElementTypeSecureTextField")
    public WebElement checkPasswotd_input;

    @FindBy(id = "black path")
    public WebElement blackBackNavBtn;

    @FindBy(xpath = "//XCUIElementTypeButton[@name=\"完成\"]")
    public WebElement xpathFinish;

    public void  navBack(){
        blackBackNavBtn.click();
    }

    public void passwordInputFinish() throws Exception{
        checkPasswotd_input.sendKeys("Test0001");
        getConfirm_btn().click();
        TimeUnit.SECONDS.sleep(7);
    }

    public boolean iosToast(String text){
        try {
            WebDriverWait wait = new WebDriverWait(driver,20);
            //By.xpath("//*[contains(@text,'"+toast+"')]")
            wait.until(ExpectedConditions.presenceOfElementLocated(By.name(text)));
            return true;
        }catch (Exception e){
            return false;
        }

    }


    public AbstractPage(IOSDriver<?> driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    public WebElement WaitforElement(By element){
        PageFactory.initElements(driver, this);
        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(element));
        return driver.findElement(element);

    }

    public WebElement findByPredicate(String type,String name){
        return driver.findElementByIosNsPredicate("type= " + type + " AND name = "+ name + " ");

    }

    public void swipToLeave(){
        IOSTouchAction action = new IOSTouchAction(driver);
        Duration duration = Duration.ofMillis(200);
        action.press(
                        PointOption.point(-10, 100))
                .waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(260, 100))
                .release().perform();
    }

    public  void closeKeyBoard()  throws Exception{
        try {
            driver.findElementByName("完成").click();
            log("点击了 完成 收起键盘");
        }catch (Exception e){
            try {
                driver.findElementByName("Done").click();
                log("点击了 Done 收起键盘");
            }catch (Exception el){
                System.out.println("not found keyboard done");
                TouchAction action = new TouchAction(driver);
                PointOption whiteplace = PointOption.point(8,200);
                action.tap(whiteplace).perform().release();
                log("点击了 8，200 坐标 收起键盘");
            }
        }

    }

    public  void tapCloseKeyBoard()  throws Exception{
        TimeUnit.SECONDS.sleep(1);
        TouchAction action = new TouchAction(driver);
        PointOption whiteplace = PointOption.point(3,120);
        action.tap(whiteplace).perform().release();
        log("点击了 3，120 坐标 收起键盘");
    }

    @FindBy(name = "white back arrow")
    public WebElement whiteBackBtn;

    @FindBy(name = "black path")
    public WebElement blackBackBtn;

    public void log(String log) {
        String time = timeStamp.format(new Date()).toString();
        System.out.println(time + ": " + log);
    }
    public  String intToString(int value)
    {
        Integer integer = new Integer(value);
        return integer.toString();
    }

    public void waiteTime(long time) {
        driver.manage().timeouts().implicitlyWait(time,TimeUnit.SECONDS);
    }
    public void waiteTime() {
        waiteTime(15);
    }

    public void clickOffsetElement(WebElement wele){
        int x = wele.getLocation().getX();
        int y = wele.getLocation().getY();
        IOSTouchAction action = new IOSTouchAction(driver);
        action.press(PointOption.point(x+20,y+14)).release().perform();
    }

    public void TapAnyWhere(int x,int y){
        IOSTouchAction action = new IOSTouchAction(driver);
        action.press(PointOption.point(x,y)).release().perform();
    }

    public  boolean isElementExist( String name) {

        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        try {
            driver.findElementByName(name);
            System.out.println("IsFindByName: "+name);
            driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
            return  true;
        }catch (org.openqa.selenium.NoSuchElementException ex){
            try {
                driver.findElementById(name);
                System.out.println("IsFindById: "+name);
                driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
                return  true;
            }catch (org.openqa.selenium.NoSuchElementException eex){
                try {
                    if (driver.findElementByClassName("XCUIElementTypeButton").getText().contains(name)){
                        System.out.println("IsFindByBtn: "+name);
                        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
                        return  true;
                    }else {
                        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
                        return  false;
                    }
                }catch (org.openqa.selenium.NoSuchElementException e){
                    System.out.println("NotFound: "+name);
                    driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
                    return  false;
                }
            }
        }
    }

    @FindBy(id = "home manager")
    public WebElement homeManager;

    public void importWallet(String privateKey) throws Exception{
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);

        Boolean haveImport = isElementExist("walletName");
        System.out.println(timeStamp.format(new Date()).toString());
        System.out.println("Imported: " + haveImport);
        if(!haveImport){
            driver.findElementByName("导入钱包").click();
            findAcceptAndClick();
        }else {
            homeManager.click();
            driver.findElementByName("导入钱包").click();
        }
        TimeUnit.SECONDS.sleep(3);
        driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
        driver.findElementByClassName("XCUIElementTypeTextField").sendKeys(privateKey);
        closeKeyBoard();
        driver.findElementByName("下一步").click();
        slideScreenLitter();
        WebElement pass1 = (WebElement) driver.findElementsByClassName("XCUIElementTypeSecureTextField").get(0);
        WebElement pass2 = (WebElement) driver.findElementsByClassName("XCUIElementTypeSecureTextField").get(1);
        pass1.sendKeys("Test0001");
        closeKeyBoard();
        pass2.sendKeys("Test0001");
        closeKeyBoard();
        driver.findElementByName("导入私钥").click();
        TimeUnit.SECONDS.sleep(7);
    }

    public  void slideScreenLitter(){
        IOSTouchAction action = new IOSTouchAction(driver);
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        Duration duration = Duration.ofMillis(200);
        action.press(
                        PointOption.point(width/2, height*4/5))
                .waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(width/2, height*3/5))
                .release().perform();
    }


    public  void slideScreenMiddle(){
        IOSTouchAction action = new IOSTouchAction(driver);
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        Duration duration = Duration.ofMillis(200);
        action.press(
                        PointOption.point(width/2, height*4/5))
                .waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(width/2, height*2/5))
                .release().perform();
    }

    public void findAcceptAndClick(){
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        try {
            WebElement accBtn = driver.findElementByName("接受");
            while (!accBtn.isEnabled()) {
                IOSTouchAction action = new IOSTouchAction(driver);
                int width = driver.manage().window().getSize().width;
                int height = driver.manage().window().getSize().height;
                Duration duration = Duration.ofMillis(30);
                action.press(
                                PointOption.point(width/2, height*4/5))
                        .waitAction(WaitOptions.waitOptions(duration))
                        .moveTo(PointOption.point(width/2, height/5))
                        .release().perform();
            }
            accBtn.click();
        }catch (Exception e){
            driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
        }
        driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
    }

    public  void slideScreenBottom() throws Exception{
        AndroidTouchAction action = new AndroidTouchAction(driver);
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        Duration duration = Duration.ofMillis(200);
        action.press(
                        PointOption.point(width/2, 3*height/4 ))
                .waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(width/2, height/2))
                .release().perform();
        TimeUnit.SECONDS.sleep(1);
    }


    public  void slideScreenTop() throws Exception{
        AndroidTouchAction action = new AndroidTouchAction(driver);
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        Duration duration = Duration.ofMillis(200);
        action.press(
                        PointOption.point(width/2, height/3))
                .waitAction(WaitOptions.waitOptions(duration))
                .moveTo(PointOption.point(width/2, 3*height/4))
                .release().perform();
        TimeUnit.SECONDS.sleep(1);
    }

    public void unTillSomeThing(String name) throws Exception{
        int i = 0;
        while (i < 10){
            if (driver.findElementByName(name).isDisplayed()){
                break;
            }else {
                TimeUnit.SECONDS.sleep(1);
                i++;
            }
        }
    }

    public void unTillSomeThingEnable(String name) throws Exception{
        int i = 0;
        while (i < 10){
            if (driver.findElementByName(name).isEnabled()){
                break;
            }else {
                TimeUnit.SECONDS.sleep(1);
                i++;
            }
        }
    }

}
