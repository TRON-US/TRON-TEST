package android.com.tronlink.wallet.import100account;

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


/**
 * run test before need modify xml to noReset = true
 */

public class ImportAddressText extends Base {


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
        } catch (Exception e) {
        }
    }


    @Test(description = "import 100 address", alwaysRun = true)
    public void test001_importAddress() throws Exception {
        findWebElement("com.tronlinkpro.wallet:id/my").click();
        findWebElement("com.tronlinkpro.wallet:id/address_book").click();
        for (int i = 0; i < addressList.length; i++) {
            findWebElement("com.tronlinkpro.wallet:id/iv_qr").click();

            findWebElement("com.tronlinkpro.wallet:id/et_address_name").sendKeys("Auto_AddressText_" + (i+1));
            findWebElement("com.tronlinkpro.wallet:id/et_address").sendKeys(addressList[i]);
            findWebElement("com.tronlinkpro.wallet:id/et_description").sendKeys("Description_" + (i+1));
            findWebElement("com.tronlinkpro.wallet:id/tv_bg_right").click();
            TimeUnit.SECONDS.sleep(1);
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
            } catch (NoSuchElementException e) {
                //Element_is_exist = false;
                TimeUnit.SECONDS.sleep(2);
            }
        }
        el = DRIVER.findElementById(element);
        return el;
    }


    public void swipUntilElementEnable(String id) throws Exception {
        TimeUnit.SECONDS.sleep(1);
        //while (findWebElement(id).isEnabled() == false) {
        while (!findWebElement(id).isEnabled()) {
            AndroidTouchAction action = new AndroidTouchAction(DRIVER);
            int width = DRIVER.manage().window().getSize().width;
            int height = DRIVER.manage().window().getSize().height;
            System.out.print("  " + width + "   " + height);
            Duration duration = Duration.ofMillis(200);
            action.press(
                    PointOption.point(width / 2, height * 4 / 5))
                    .waitAction(WaitOptions.waitOptions(duration))
                    .moveTo(PointOption.point(width / 2, height / 5))
                    .release().perform();
        }
    }



    String[] addressList = {
            "TARUVSxQw79V93kXCSpoK1uQifisnTjP57",
            "TVjovxe1aPUL2Pdk1Zb63Ki8BSbtkuwGke",
            "TBVwURejdcbXn8YCucMfc87SoniRdFQPeM",
            "TCgr7JJyntBSdEjXxEbEcr6oZF7aVSojnV",
            "TXL135QqJPEGnRNMyYLJpsgwTeTw1q9qdv",
            "TGeqk2793cXBAfH3ZgmoQQGS8qsH2FdbzE",
            "TVk4G3DZJpi7E78Qm9oGpm4S37wSJBVLVt",
            "TG6EvUKAm6jw3P1xb17UvBveWdyyzjtdT4",
            "TWS6ikopmP4qTJ3iyjmXkMJvX5KicYG1JH",
            "TDqpyoF319MmSKRLz5HCJkoY6nt6FovyQE",
            "TPjxipBEePZEVkhGnWyGNzLFJWsdDEd7FR",
            "TXcG7jnNy5E9FUqjyA2ih3o7c8ivW2Zfwv",
            "TKEWms3NBHU3E199avFuSHVbqhsABsRRHy",
            "TKBfCF7ke12tE5KRRGDN8e3DVyaeEM9qG3",
            "TUg5mFSpS3qHRaaLcuFJAX9Dz4QYbtkrBR",
            "TAMGiqv4HYNQDaMYVnr53nHPc7K4CjnbTM",
            "TC1zx9nJJvyPCZNxEwk75yNSGMzhbAfuvi",
            "TLyTBmRy7ntDPbcupbZKG2Fa4gZPyuGPsj",
            "TTGf3E9u6ntTLDhW2LcFar6CcooBAv28k9",
            "TTmQVChQ46MADVVAcH95rABceHs28EUBVQ",
            "TUPwBcNB34P7Z9Uyh5ABHEhdh4BDvF5LWe",
            "TB75VfYSSgbEUs9SQUkrNiciE5JTSAwahp",
            "TLDCj8AJrjV2rUfDKmQ9U552pFYHPXSSDB",
            "TP8FKN8LaBg7c69q99J4YrPQcDcNFK71DV",
            "TRWB7svWWYsXCxP7g3CogbNqVj3sPTSfiQ",
            "TCi3X8o6t21kfATzzaU2s7Eub1g3Z9ady7",
            "TEkVeFZhD6qdzSdPCBsCRtMdvCj2EUfETP",
            "TThdhRLpfveSnQsxqtHpsAUM8NiGFdhejv",
            "TKDB3vyqSbc8AqHjDYxVi6KG4qbaQJ7Z1x",
            "TYfKkKSwbh9rMqFnQw5bHSWySW86spvs63",
            "TGjmSCduLsQkjUcwLQQQ4J1xXAp1bG2pSh",
            "TMsrY8K5honJVrqHqjJ4sYr4zTBEV18wr3",
            "TVGL8Gy7SFGtQZVUy5DAjn44BDeHpHK62D",
            "TMYb4CToK5vhzBzs66bmZftJoQyeYxjtkg",
            "TBLNKTczmDg8yistUeRwkD8XxJtxAMgnZc",
            "TLQz7bmfmahK21fYizHcuP8KQC4su5f8ER",
            "TA6nPRhcQL3JbA4gACH3AHV6TGzgk6Ldbo",
            "TGm8yGsELnAbE4WwSQHuEncQMu1xPdiGGu",
            "TRKH94esbHQLDA7q6Uhsg9fQ2Y7btQXHdC",
            "TUvDadhT4TzfSk9b5ihQRJ5HEzB3bZtQNE",
            "TEq5mdC8Wif21W3zCvSUHj9t3t4wbJ8XcQ",
            "TL11zUHhWraRxdghbMzdzktDvKkaEwMskA",
            "TGrVJcntyqR26VpnH5KHSgyxKCmoVS488J",
            "TMEpSnqVetZ4DM2Gj5LyuJemLZSnsVtCew",
            "TJRMwXo8nMgLTXNxTaM6dboU3T8Rkb3YvQ",
            "TJPwCyC5YWyYWf6wM82YSfWkBEbYKRbwjq",
            "TY4iXnndeaRAFkj1UynVw3FUn1VANWZpaW",
            "TWZVTmV9XnKVMnMRhWJFi7WTBnLBnpbik2",
            "TWJxYgdi6u8TqX18KAYRrVGNAsaExfs9Hu",
            "TP6qXZMdWKZDDnZDQvSmMNYqF49Sde6Zq6",
            "TDsxhuxudr2D1aZeVYWpnZwKyBdhyV5RYx",
            "TUJi8LX5r4GJN5syY5NjaZpyEqkx71AG7P",
            "THM3t5S5iHGYTRpbjuynjrSNqttnmqe6kp",
            "TBs5JNgtxtUZxFiuUUdfkQQuq4DBjMNV9T",
            "TRQCiPcxGeL6s6wz9KNuZrjXcpJbG6nNVU",
            "THRRHzTjvTae1Wdoq8RpQBBksstjggJWWC",
            "TKVExeemjCcok4oScgrRADsNjWg9ygm3Yy",
            "TGTeMVcX2QwachKpzeDNybbvG38Q98zbXH",
            "TPZeqfjnqV3yRMTNPebxkmDec3UFVRdZ6H",
            "TYGbT54zJGLVwc9iHBFKHbYzXR4okitxsP",
            "TUADYFYRo5gFQJxBR9W7F5vQASoKP1jNE5",
            "TC3pYLshCMh5DUr5jkMnb4VkP1m2Pu6JR7",
            "TAdPWSmRM7EBGt9PD6fxZxxaxYemLv8zhZ",
            "TXdzDMaYxmvmDSRVHczyrydUdZJXC5acmf",
            "TAcWDZ14AbR8QPT5cq2TapUtMA8ZFFaoNo",
            "TLYRqC91ReEhYqTMPtDXm2SPmUsQx3Y2pb",
            "TWMxdErRzwbUAngcpN9PuYH7fp8KCCYv85",
            "TS6CrcLh2Sip9whbFm4nushmP6xfbx1wdA",
            "TVKwGGfCdmmareRLk8TYBzKgQc6p8HUhNU",
            "TAoMTaPb7JzBpHCKrSTKu1J2cXLVnDnVoM",
            "TK2KyL4SybKzSZu6LN4Cf3yprL3YcbTBjK",
            "TNMLPgPafuR8S8fmaHENEhCbS1DuNYEtKu",
            "TTJaFydGfhHjp674Bnz8Zsa2qoJ9JX8azw",
            "TYGHkj8ao45ao9k1EUnaJZux5mD4GFNh47",
            "TZ3akkh7M8HxvverM8mBVSaiNa8P11tYbE",
            "TASgJ41LDsFUSkqCrH8BPm3uEdd4Sdqsya",
            "TPWL6KoatYFRrWxwUe13thggvJ3PsZagWe",
            "TQJBsJomPxVHCMg4uC5kLgnpqpoCLcf8ix",
            "TU5oxk6MgjUw6ihicvNQNSdUp6Ned1wFMN",
            "TM5oADYvU6wFu8RdoWAP1iM6nnhVKFr7iW",
            "TWwe9TrctaX5D23EhnmfdSgZF6wjfZNg4g",
            "TJPjkXBhptXR8GSD2coNVrigfZtvy1S8x8",
            "TAP72HcL9FihZ94uE53g86R3GUpHkLNGts",
            "TG6F5LvKZuFRz77NmHq162QwTsJeMA8cSt",
            "TG68YidfKrJ5yiuLFHcy8s4LTFnqzkV9gv",
            "TWRLevdLAGMXB826h9KmRF6o5mZ5Qn3FSk",
            "TASWU5Ug7Vi67fRTHTKU1VcAbngRjsRddb",
            "TMFcPsnrnxKfpLWbVaRfQGk4qwC7Ln1V5H",
            "TTvvbwhWxLsbVhPG9sWFLvRbEwsZDLCmqW",
            "TCpQH77no2h21YmPafgU6YMjXmg7Bctevv",
            "TB9kpNepQhEHMgtX797fUmQeYPKtbwxmTp",
            "TKewufiBUJ83zP7jM3D2qevsjEbs41DR9Z",
            "TCYWMzT13Ve9zJPSPVkoKFULUx7MqcDVg1",
            "TKXcYDbVqBX6QDqJ29sa1VFngp9a4UBXVD",
            "TSb71QnEr41x22WCeBNDgkT8C7M11JLMVU",
            "TCF3rJLjSXy4VqGUZpCRvbew8MH23M2Xto",
            "TTutqbuutKXTKCpggGcp5XAGh5YMGkVdJ2",
            "TGvmyP9kvvbFDD7tbqtQFfh8T7vtGAgDeG",
            "TRkqdzx5tSFws4oriPhGSoh3qJAbQfwRfK",
            "THMtHkvWw8AaMYxynMe9PeruRwHS7ehr61",
            "TFDHbg6fpr2rXSqXa53WK5VwuaiJHZc3GG",
    };











}
