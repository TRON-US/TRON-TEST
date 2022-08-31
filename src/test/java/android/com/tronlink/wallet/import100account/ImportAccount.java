package android.com.tronlink.wallet.import100account;



import android.com.utils.Helper;
import android.com.wallet.UITest.base.Base;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ImportAccount extends Base {



    @BeforeClass(alwaysRun = true)
    public void setUpBefore() throws Exception {
        //TQ1EL7zJei3VePq5B6R6r8dcGHUTXrE4oe
        //b69c0ce7bcb061bb6a6d5c1582e7c42547c20421493ef9c623a6ec6f8a024647
        //new Helper().getSign("b69c0ce7bcb061bb6a6d5c1582e7c42547c20421493ef9c623a6ec6f8a024647", DRIVER);
    }



    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        try {
            DRIVER.closeApp();
            DRIVER.activateApp("com.tronlinkpro.wallet");
        }catch (Exception e){}
    }



    @Test(description = "import 100 account", alwaysRun = true)
    public void test001_importWallet() throws Exception {
//        new Helper().getSign("a1866b9c8b2effb0edc091b3d56b787a03b455b8b001414cb19acc1869230026", DRIVER);

        TimeUnit.SECONDS.sleep(3);
//        findWebElement("com.tronlinkpro.wallet:id/tv_walletname").click();
//        System.out.println("点击完我的钱包名称222222222");
//        findWebElement("com.tronlinkpro.wallet:id/tv_create").click();
//        System.out.println("点击完新建按钮3333333333");
        for (int i = 0;i<testAddressList.length;i++){
            new Helper().AddMoreWalletWithPrivateKey(testAddressList[i], DRIVER);
            TimeUnit.SECONDS.sleep(2);
        }
    }


    public void scrollImport() throws Exception {


    }



    public WebElement findWebElement(String element) throws Exception {
        int tries = 0;
        Boolean Element_is_exist = false;
        WebElement el = null;
        while (!Element_is_exist && tries < 5) {
            tries++;
            try {
                el = DRIVER.findElementById(element);
                Element_is_exist = true;
            }catch (NoSuchElementException e){
                //Element_is_exist = false;
                TimeUnit.SECONDS.sleep(2);
            }
        }
        el = DRIVER.findElementById(element);
        return el;
    }




    public void swipUntilElementEnable(String id) throws Exception{
        TimeUnit.SECONDS.sleep(1);
        //while (findWebElement(id).isEnabled() == false) {
        while (!findWebElement(id).isEnabled()) {
            AndroidTouchAction action = new AndroidTouchAction(DRIVER);
            int width = DRIVER.manage().window().getSize().width;
            int height = DRIVER.manage().window().getSize().height;
            System.out.print("  " + width + "   " + height);
            Duration duration = Duration.ofMillis(200);
            action.press(
                    PointOption.point(width/2, height*4/5))
                    .waitAction(WaitOptions.waitOptions(duration))
                    .moveTo(PointOption.point(width/2, height/5))
                    .release().perform();
        }
    }


//
//  testAccountList.put("TR8CyAPJFMjCvphCVuWeeVxBh5iTG7VWxe","cfd889566341aea937737ecf4bc35f9be7c5b43f594c9a230a0348183472245e");
//        testAccountList.put("TMhGDU7NiXwckCW64PqAvWFuC2kR1WSF5J","11c7013416aac83fd6070abb8ffceb0ad102d9f87dfc9c98308b0fd47e8c3a1a");
//        testAccountList.put("TDf3JZtjDeEqsFdPGp6vT9meG3JxMwmXwA","0ea138885c1fb2b6adaad51033c8876df0e37ccf7dd322cfad5504d671fb2a79");
//        testAccountList.put("TEtG9fnVi2qythiog6owPrg4sD9rwFBQBN","8f3839e21b4ada348da3d85ccc72e1f1898a39b877f0f6f5b35137588a344345");
//        testAccountList.put("TUvda1oqrNLbqDKhZDxDnrPhiDCdxem218","cf9933e99ee2b272147dd563c7f880de751c30d61cd6681a158f0a8056023d9b");
//        testAccountList.put("TKEH31jJ2YQ3Bteh1ngjwdT8667ztyYPSp","223f2e4a3010286540d3c119d2a1d55b1d54248f63f1c4d9ccfbd0d105ab15c7");
//        testAccountList.put("TAzrJHKa57nXnn3dZGFG87PDuWx12dY97s","844f7f5da381943403e8324db4fda13dce9af35b72cf2ea3846fafa12c5d9890");
//        testAccountList.put("TWhc6AAh6BWRr3k5dV8iMvkp8ys7NHzXCk","6850fd0a0f2cb94167bf0507a738fa9eef51d6fdc65e8452039f711a4bdf3135");
//        testAccountList.put("TSsaSxHnb3xLTop2A8LrDk1P896yiDeupe","cec7fc3c9c603ae6cdc026c777db037b8ca4995d451fa5fe7b2f19a0dc01cd98");
//        testAccountList.put("TMDs8oTj8mVnakqiVyDKdp2ruWPdFeDgbq","7652071f95c376e6d1100f9fed5c520f22262c1530f328bb1c3ed10bad771e68");
//
//
//  testAccountList.put("TWv2FEsoPp5XKxujVHffoNwksgJSxvf3QG","6a77e8edd232f4102e4fcaca02234df7176a9398fdde1792ae5377b009482fca");
//        testAccountList.put("TXhQk442CCGLydh6cfyfqvM6yJanEGeQj1","b50aa8ce2140be6995e79d657064e5a3983ac0a47bfdcbb5e9f4b930ba2996a5");
//        testAccountList.put("TKktQcbjXsXZDKPYLvUm8sxox2cT83g5rP","d4446cf4ccfe02f165f0ba01e3d5a56546e41eebf26c3cfe33564bababeef74d");
//        testAccountList.put("TBQUhYhdQpMRksBGAbpbTWSiE7WkGgy3Km","3999ce04f0ba5e05776d355b194f369a6d56f4fd7711a31adf2044690236bf5b");
//        testAccountList.put("TALf34yjuLZjF1WQqCaUkf73X8WbhfiEyM","022f883a91a14567a8b1ad9722b73971f5c748586e951b7a8eed0ef6e29950ac");
//        testAccountList.put("TBExF3mNvnhmEFgHW4TmYXXdhevRchnQyb","a1866b9c8b2effb0edc091b3d56b787a03b455b8b001414cb19acc1869230026");
//        testAccountList.put("TS8o6WcHroSnzWNt4AiserAuVkye5Msvcm","f88184cfc003612d02b94956bccde12b8086c5010b3401357e7bdc8dd7727f4d");
//        testAccountList.put("TBtMRD79NkLyAvMkCTTj5VC5KZnz2Po2XZ","71951c4a6b1d827ee9180ddd46d61b9963c2763737f3d3724049c6ae50e5efed");
//

    String[] testAddressList = {
            "11c7013416aac83fd6070abb8ffceb0ad102d9f87dfc9c98308b0fd47e8c3a1a",
            "cfd889566341aea937737ecf4bc35f9be7c5b43f594c9a230a0348183472245e",
            "0ea138885c1fb2b6adaad51033c8876df0e37ccf7dd322cfad5504d671fb2a79",
            "8f3839e21b4ada348da3d85ccc72e1f1898a39b877f0f6f5b35137588a344345",
            "cf9933e99ee2b272147dd563c7f880de751c30d61cd6681a158f0a8056023d9b",
            "223f2e4a3010286540d3c119d2a1d55b1d54248f63f1c4d9ccfbd0d105ab15c7",
            "844f7f5da381943403e8324db4fda13dce9af35b72cf2ea3846fafa12c5d9890",
            "6850fd0a0f2cb94167bf0507a738fa9eef51d6fdc65e8452039f711a4bdf3135",
            "cec7fc3c9c603ae6cdc026c777db037b8ca4995d451fa5fe7b2f19a0dc01cd98",
            "7652071f95c376e6d1100f9fed5c520f22262c1530f328bb1c3ed10bad771e68",

    };

    String[] addressList = {
            "55eaf3ccb59835bd8a62cb5b5df91a858cfbaacb8ca9dbd4f8f6f6cde7a8366a",
            "4fc6e3743f9649b5b89d2b6a2aafab4c93ca35ff6f2ffee22cdcc4623a42ac2b",
            "a79780b541fa2ee0d415912246e7ffbd7f2763442af788752f10129e2b1fe6cb",
            "c4afde92d4cee0b538011eb9b4ea2d819de34d50f4fe157f7fe334cb8297d031",
            "ef9c2d99ac489a160e8cced0727c28713d55b95f69616aafcc5f647f926f48a8",
            "d2aeb45b967b0e2a3ba63303ea64f090eada07b63f6c59681db55e326ab8fdbe",
            "097aafff07fc6b926211c53f3e2c72e4337022955a52ce079092b5f28993fef4",
            "3c3641a9cdc68d1d002f7f4cccc1f78ed2ce27a722eecba647cf4812bce44cae",
            "40549c858bfc6ecb6c899b2d14ccb869d2ee4f93487ba8a847d1ca3570693b6d",
            "89e8558ad69ac6ac65db0f24bb361302144459d5ff15b5e4c6b91524dc8fda39",
            "177a68971740342e9b4690a6788938482c3b97ddd2fd673b466f02a387820e82",
            "24efbb25ec2176a27e24e60a17b290b21208c2a3931d939d959789a187fe5cd4",
            "f99898139aaba098deb18b8d3e53c8bec15bb9fa7084ff240467616c98f4f31b",
            "6453c78e60d935ff130381680f6b835cf92375fd03df455cdf1a8b75c6dfdd94",
            "fceea6b9c27a9a4df07de0f258084b3081c8aa7ea5d2270f1c50333f07fd9c7f",
            "699a07552bef4b0dde5cdd9db8faabd04b0b027e49c54841c8aa7de373d5c74d",
            "a4b609efc33c6154e51397af7838d1c5caaaf0c1149205b3ac7eaa7ef1fe6ef0",
            "b78c2364588fbc59aca4d5c35abf0ee1307fda007723181575307eceab3b6c57",
            "3d293422e326c86a9b43f4d2a48628aeedddd436a244dc726867981c38054ad4",
            "256c22b1281e97014eef58e16fde544db3da53c67c4088580f00988fedb26399",
            "8bb6637860efc1286359c422b9bee94b000d4a6cb1f76ea96acb8ac467084922",
            "effdc60c25e11d73ebd94066104319b0a2a0d1dae5ff8f3f315ae7af04d478ca",
            "71f00c1750466299bee225884504a701a78ffbd7bd95d091a30fd10afd1f0551",
            "992a2ed96dedee24b717f9fdcdd691764d2b45a6f61fd9128d480f511cb79f46",
            "1afcb931b77ba2febad54997d690aa8b442d600fc097fe19f0c20729ef6464cf",
            "67c3b50257fcb7ac000959cb4cb55d825598a59c8fc37a7db134acf84184955a",
            "780b16a9920cb3aa9519fa812934539a3029e6fcdbc67246fed4247df0c67aee",
            "0a6cb78f8166223a81db8d23b74e6ab46599e441a8ae55dd86af5a14906cd810",
            "aa295e59ddfe10bc90adaf2ad66a0e44f9ac8ad15103e3550f8d1e5871714c2f",
            "051acf7b65b12b4c28204d106c757e7f50c769f00d735ebd7a0c3e29e6315687",
            "8320b4ae37fac7273e3a1865df153a0d08c515927cdd86339c0fd476dd54a479",
            "2245539fae9171da10cde73c801a20e1b81603d6a51437de679b68b21baa1d22",
            "e67846903343594d8052e533b697362acf38d4b03dda4de0fef725aa08ce2d45",
            "3d41b855feea328b48dbe37625de1f7b0b0076abdf47b132d01e71d733b15764",
            "cfa0a9d06de01a8163020ec5c0300866c5bd942ac4f47517ccf75579e307e32d",
            "bb0b22e0d4a9d7835dbdf8d7795d33995c5d1e8de9159494ed8391d86e3e9e18",
            "d7b45112fdeec703ae50597675bf3fc02b06e4d17d30dba0fbcd2507d7939d99",
            "dac11de8c64680e3f554b805132ecdced54e8ca54af17aa2e4cdf2f2964c18b7",
            "640a2eb10f6ffad828db91fc56a26d95e758c34f1c195c903662477f0203139f",
            "1250ccf60eaa5b1a42abe9f17aa6d0e0c2c95e3cda4bd4c827b8f168f43481b4",
            "9ecc0461eb9128a84a30485eae9f1e8a4bb1e8e3bba5bf9eb06a3b5f816578af",
            "1061177c7f22e9cacb592925658c4021ec0838b062eed220cc5964b65f7c5d38",
            "47068439ebcbcfbda4cd1421abe3342623bd34aff75d59ee8811229549eddb71",
            "cb697ce590c24618823adb2785074834e80ccd14eb0342907433ff361cb0a677",
            "5e55ed0fdc6cafcd70adaee8d0ba0e580bbf61c0b8deccaa447431bcf91cc5cf",
            "99a8fce2f108a2ebbb192e8774de64824a1390a01d098dcf5fd6db3b0a218024",
            "29593137c7efcff24547b2556adf7442ee9025dad9f00262766f3f0eaeb5c90d",
            "b53e5fa3156e1c0bd8534d19da52acfb2d92d93f6029da5ebae32962b494ea21",
            "a77f062627f74d865bcbcda412c0d684586594306659ff0415d0f5d4a93963e2",
            "03697f4debb41dbe7089bdef4c4f362964accce4779b61adfa2f86d60ec166a3",
            "105cdb393abcbcb16f3d141d4e073e992e8315074a654c30cb3a2561992dfd26",
            "13fe4629a2bd62331510aeecc883d390fe87aaf98cf307f1c55fdd1d58aa2a0f",
            "9c2c85d2ce0efcea5ba205ea76dee457e4ccf0f519cfc053ebd828a804f814de",
            "467525e2da10ebf73bc8335db6de5ba016790087e6d44acbff41e9962c16e515",
            "37d5c9e308e676623ca519ddcdb2a48ac5b2c883cdde28cea0a8c301103dd61a",
            "cfb0cc489afa8d96995c091cd7aaa66c82f70ea061c823a853b9b73753ca5b97",
            "b1f35c222d68f83a79e707477d65ff159b85232e1ff5b347f595847e45fe1fd1",
            "3048718adcfc1e5670cb95696f7e0df570c37aac76f73b57e52df9c5c85f2a2f",
            "fca0c8c45be43a826dcd85981050fec09ff6e304ad357b439fae0358191c1677",
            "ccd407455c7ce008249402079a686743223778ff9711fd5b401bc12eefbca5f5",
            "fa8af086a6a3dc8e8788199755162ff3f5d4a8de930b9756c5a3cf4c512c529c",
            "455aecc0fbcf44d0157892c2a4d457cdae76fe7d846e1e5e087b218f5fcd0f81",
            "44ebfa9a0ad0347cf9de6b731f1d385984060208a34f0ab4515cf0b26e6a39b9",
            "04dc95a522d41d2a4463ee952490b4812ad5db4da09883bcad0ec2803765eb16",
            "d9efd0c306d5b1a13a50f67e3d14a9042acc3917d2eace8f5d20ff7a1a2ed447",
            "bce0656252c38f0a0626a88e8c82715e7adb474e12272546e6fb634a5bed2769",
            "17992a8bf1be997a0c15a233e6ecd28b613a80040044f931e403cf1d4be959c5",
            "01248d1bf24d6fd6ef11e84a46ad7eb6bd172e0d999d109807622a5b0327162c",
            "1ccee69018dd6e9d5723c96ba7537d32081147324d592c00db74b7bbe261901c",
            "dc8a9c8f8a2206817fb65540a5828d61fd61fc1b46561bd5c19374b428d151a3",
            "85edf5858d3a2655b261a7260005add00657d8ef7791b6fc75690f9e984db2bb",
            "338cff50f383946b31656c8ac41ebde2ddb81de05e7e6bf8fd5d30b56ae17edc",
            "b5d31edd1158ba36f4ef83bc94e3764d9bb5e3a1cb086497a0c4cdf880c2028b",
            "4e5b45055ac1369eeaabb68a2c3ac4215c5d3cab59117d5f4fb086be85260938",
            "933b06db9765bd35c851b67d506e9cdd31f3793e7261b53824d00117187c5d9d",
            "7716e69d7075b8d86a3020e94fea45a55fdafb640aa08da5db9fc1601cd90beb",
            "513a755711a15c8ba5987ece908882e42e81dfbce8f5e6da1bb2b6ac8cac1d78",
            "0dc768d9a26e08a98569ddb3dbfa71b6c8a49730f7937f34e8f8bfec2bc02db6",
            "80a24c42373d8adc3c209b2aeb4d001b5810793de3e62032d57dfc820257961b",
            "daa860411c36e4969ef9e0f5e2fd9f096d2787517f1a91ffa790a943ed995f18",
            "1be64fb225c059fc07c07a9b2f51e6643a39a1fc1a42f215bc15773acab6c89f",
            "5820f7f4385c42f965aadc9f58ccf6deec5aa0a23ee8e6d1131ac1e954372278",
            "196c5ba0abb9f3d9f9e66f2b2e4eefddd22843bea8f7bc92b297db2dc836502b",
            "52e8303796d25e665e1af053f0c87a5ebc29cf27147b84df4a697e0453192c9a",
            "833676bb3964cacd80f34be773e3a15b012738235343746b4f5bec5e2d723f36",
            "f7a2571a8b05330aa12a0f1b2156db888777eef924d1fbacf94ce5a8899727cc",
            "26800ce9f7eac202c379fa5204774701a3cb7d9f5061c2aa5084f3ac5cf92cf0",
            "f8fa7a2bd2c1427455e5febcaa8de1df17bf16353075d95c7ff13c0c1c59f778",
            "5a495e36786f466b19b0b10e8aca0ea65fe4a3d987a1a8ee87c9e6f2c12465a0",
            "c6d1035363d1505271f6b26697468b987d5b2a85026a65c25f2525a851d14a88",
            "95e8675473840acdc45a60cb87282f39877b39bd94c13bd442286576c7ed3bb6",
            "926eac2969daf78d779b83cceeec748496e41712be9725fa343a6f772620e8fe",
            "3da8990ea9703dd799c4931b8e88d5ea84d3b0e41cc214600ab66a1991baacba",
            "4bb326612129ba7e8e7c61bf2de8b1afff23d8490acd3d27ecbc91d81ca1631f",
            "0baf7589e0ae2e159b8b6250ff0b38b4e768c563dac56dbf9485d09acd75a2b3",
            "19d965bdb82eaa973bcaf6196bb18fe67b4e8d564863f20f842ddca96a37bea4",
            "c94f80b9d6618b9c810801978b51e2ea3486af5e4a11e15f638cf65d766b25d0",
            "fe0a9f8297e75fd29ea5b6b8ccb2dc38fccf9adb4e435937b1e5312a26229967",
            "2615ccec057d9e122ecf13aff30b3aa1930dfb9e5ac656563d7c5ffe00057677",
            "8140f33eef6053cc53e16a11dee78cf068f795a891697f71f1b26f0ff4eb7997"
    };


    public static void main(String[] args) {
        for (int i = 0;i<10;i++){
            System.out.println("i="+i);
            System.out.println(i+4);
        }

    }

}
