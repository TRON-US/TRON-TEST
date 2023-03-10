package android.com.tronlink.wallet.import100account;

import android.com.wallet.UITest.base.Base;
import android.com.utils.Helper;
import android.com.wallet.pages.AssetPage;
import android.com.wallet.pages.MinePage;
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
    public void test001_importAddress() throws Exception {
        AssetPage asset = new AssetPage(DRIVER);
        MinePage mine =  asset.enterMinePage();
        mine.enterAddressBookPage();

        System.out.println(addressList.length);
        for (int i = 0; i < addressList.length; i++) {
            findByShotId("iv_qr").click();
            findByShotId("et_address_name").sendKeys("address_" + (i+1));
            findByShotId("et_address").sendKeys(addressList[i]);
            findByShotId("tv_bg_right").click();

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
            "THv4eFL1ShwUtQ5472TSkbanuTQJaEvUYk",
            "THSjCwmYVeK1oLR3TdnXhP7vcU9xVssR3m",
            "TVcZYxYGLcL72nSo8rh16N1M1nRAGMBFEG",
            "TUVkbW84R4t4ewtXfTnbF2QKPv4kiH5tve",
            "TLAMqT2rHdVKgTKvRptfyC8jnZqUDyMNps",
            "TAHiALst69f91bft9B1L3h5rjPep1v5Lbf",
            "TJvqNiWUN2v2NBG12UhfV7WSvReJkRP3VC",
            "TJLnaYh91pENftw3hB6Gp7ep9KCubAWR89",
            "TAP7qf8Ao26ZAKYS5E6SGozUNoSLvBHsGa",
            "TXkbti2FbWat8yLjXg5oKYb2Y97RUAnm68",
            "TJ2X9enWLfQhyLUSuDTiELZ7SBAEPkZcJZ",
            "TNeX58mQxcQndNVFMicZXHrqYtbk2eN19T",
            "TNTNgtrJpQTDCCwvB9Dh17GNc22WzYbF7n",
            "TApkr8onQYb4YkGxCHLbQkMQNstF1td1hw",
            "TNoUWaZgSNia49qShdzB5VdaNF89it6hxf",
            "TFVge5Nb6or8cpdtdwPuXSGqLHpj3PM9Rp",
            "TKdwCRZJ7jzsmndyUi658A5EU5FHFoKMVU",
            "TVupLGJBE5jN7zBk5G9PcS9BcqikTWDdLA",
            "TSsm5A3btNpfa1xQopzGfVMJbWCPhVB1jP",
            "TZF68ApzvfGRsXbhuAX4JcsTJzQW5gpxDa",
            "TNkssRtjrtPvophW4PXaAqDH5mD9hiMV89",
            "TWViCSgeZ8xhZMXD3aiKKJF48fwmhHas3q",
            "TD8TabsUUQPpvbhDGG7AfRqrYRrkwGS2Yz",
            "TBdvhbUAuDwC3B21SGoSsjJWH2ctS9EzEy",
            "TAMPqizEWsYp8RBsAanLdRqjP6sd9Unscz",
            "TSJywyW5zmJQ3wPhbNWRoxZ74roLNwu3CA",
            "TACYt54Qxtv3HxPGp9sWrbRJjTFEMaURPp",
            "TUfusVHNhywdbqFw5rgRe2a9ChM4vTcv7N",
            "TQ7ZTbPKgZFTh3WanAx9aZTNDee9eMDREa",
            "TRXmYPy2AXZxacoSbKGjiVto8K1EqwexU7",
            "TUXDfjhAuwvgPeGJB8C3bSNhpoz9bPcpRt",
            "TN1NZ7YLbZinUUB8EmarGsiqTMFSs7CAxs",
            "TZ7EvoZdEet2P9E2DKf9CtKf6EAyFwRAS3",
            "TAgEv9SiteDeJWu6Prros3NgMbC78u565n",
            "TEvq5hDqLR1z9CoSnWfFvSg4UT455ioDsV",
            "TNuhGXdAhjiJyya6BQ3hR9mHQPTBYHhQ1b",
            "TKRYQndqTdnU4Bg17gY39rooyK6CzM3ush",
            "TTdyBPmeWDfF9eXCdur9e9GdHnX3AZc8Ae",
            "TPrDk8WfuMheRFiC3Q5FsmCq74GuQXGcdL",
            "TKcVKREApseEtC6Jxcv9Dso8xtiJcdXU6d",
            "THvZvKPLHKLJhEFYKiyqj6j8G8nGgfg7ur",
            "TWb4fGe45Sg5Egk1PXGDbHscFs4aoxLRtD",
            "TELY7x8LJSsj75hLK8xh9S5Ht7BNfYXSkK",
            "TNkjPoQSh3bx38Frz44DHXxY2G3yj73PVs",
            "THgLniqRhDg5zePSrDTdU9QwY8FjD9nLYt",
            "TJcUfhsoBRoJCFTo82ACv5Fkp9vPdbQ3tn",
            "TUmzcczaosRkmLLqCSAAuUL7Dsq4aGeyoL",
            "TT3LGnQvWUXs8pWLJfCkUKKpo4nCx7c946",
            "TEXtxKmvYY1SuFmEXQKSYToY2mP2ncMif1",
            "THsxKhGentRkjdUfP2qMQbcyN6DbKeFZ1N",
            "TBpxVGYFojghQDn9zKY15ThZWLyak3E4HV",
            "TGbu32VEGpS4kDmjrmn5ZZJgUyHQiaweoq",
            "TASrsr1Qsz8eUSajiTxXSLo44uAFfvZVUG",
            "TXQzpozgRefXAZU1aFUvR9DqPJKcaPPBGy",
            "TAzopbXcjczXr12k8uaXH1Adg9gpFKuUE3",
            "TNq5PbSssK5XfmSYU4Aox4XkgTdpDoEDiY",
            "TUcPC42A1QZR7T7gHZDn6sd7kwdoe69xba",
            "TBGcAYjZ621Zo4rQ3CuF2LxMgiYGw8jV14",
            "THBvB5ptRTVAxVrN3WBPbU653R6JMC9S59",
            "TTgNnc26QYaZyRKdj6iNmhZr89BJEsvTWC",
            "TPWSUqMpcJ1sRfXDMLKksd8jkBHf7k6pA3",
            "TS9j7tRdpY9FDtJbgfZZwcj1JAm4z8jbzT",
            "TFiN25tzwwW8WQEWbckohqmxd7Jd1nynFa",
            "TX8mKRD7CCvxh98rg7MdCGjtjSuHw9LSZg",
            "TDLzqsr1NsMhUw7b3tN7M6DfcxSVdUaJcC",
            "TDc1rtiY9PES9iKuLhs1q8Kf4zRBVNBy5R",
            "TJhBZCBa7bhEWDGPhAZ31eNwS8idKKFzz3",
            "TLkKuma8mDUJYeaY97NqGbuyzuq3BEvcVj",
            "TTrCFA3maDrpuep5ad32xmw51wsdDmHPgo",
            "TKWcoHvuextZtPC5ztxSX9Ragz2X2f1QGj",
            "TQyT8ZZgoyXXkJWZnMwWQ31H9M69xZEezr",
            "TUH4wj8tox7sVxvkTqHLogn6mRVKfrK71M",
            "TDaBezkqSwNZYCr2HPqPMoyPykD5FUvKNP",
            "TTa7Liuaca3bFgjxkz84smyiXaECk2TjvE",
            "TCRhVHPv6efvXgogNMhiunAMXFKcMmv2pF",
            "TGvTEbQb7bcN44FanFqfrvdWEsXBrvcRgF",
            "TD2931N32Sqp8hYS6pv7rSjpQyBN9VaGX7",
            "TSeG6DRgt7iEqnaEaDqsYvvTYYRCjrtk1P",
            "TKg3dNAYxVgNEfzBjM8ucyEr6sz2k6fUMm",
            "TWmU3XbU7vjdW8swaKsKuUbWwA5QtyrkEN",
            "TNoSu9vMr8kmrHWDXeE4BXbLLmijXrLSiy",
            "TK3qbo9oX8GP9e2pcNq9hjUiUwaR2aW394",
            "TJZwSoCdz3SraNaeX5HCS2eFAyzj1cmgeu",
            "TTq5jUHenWvbKXk4SmqFyXQtr6pig93oeJ",
            "TNzAESDxnQjppQ23CFbeUNrq3LcJzwvTYi",
            "TBLrDTgEsQk6a6PkB6KYp9xU4oJpHiRde8",
            "TQzGuuTWute287To3kyGDWUGxqZv7QDXgL",
            "TR2XCGSShy2chMQtsiXkXs2wzWXND3RThg",
            "TPt8DTDBZYfJ9fuyRjdWJr4PP68tRfptLG",
            "TMSWrENmfjf6RtHzVeVWU7zHz1sUSod7Jq",
            "THczeHtN8uy2xirumo9yAziSw3XhmKVpkx",
            "TNQwHfwLcAPXtCfxTwuXsSKcY6s8hBvbVr",
            "TURRqyAZ6ZfW7yVbMZ4L8kaQM76LFmw5rs",
            "TBuLwao9iXDgfPP1TKbkes6ZcWQisLoop9",
            "THU38thXe3tijdtPZf38PAsANaTLmscv4W",
            "TJoSXFhctyq8Ug2rD8WLKDsDGAWUXf5rDm",
            "TXT9SRaj6y5TJGcPd1C1J33EWGHhbsDCMJ",
            "TFHcoENHu3HGUu2wiPGptb8cyBfMeWhrTW",
            "TFyfB5ggmPwVDs8kU2X9vPY7idzaNpaVno",
            "TJmki9vmWXP6QxHJ9JVCd5iyAs8xwS5HL4",
            "TAhXFNAnpLr1PPdhZh33oQXi6tMT1Qqm24",
            "TLvdAAh7hxDWvvhGwQbuuee565fKNXeG4w",
            "TUSkYMEQqtJLUWdMTtbcggxE8BVxbmyKnf",
            "TAcfhsJ26fU5u2rR9FVdnJAQVmfMV9xLhw",
            "TTGSPXnTYdHFJkHjdKyaQH2RciT8RpjroU",
            "TKGaR7hWDqm4k4U1MSHrK5LWgteSXbTuKx",
            "TFVFbULw95Bj12hRELMdzHbjmEY5cx25sn",
            "TLPDSEEkW1yJ8oQitn1LsU31PXvazw9jm7",
            "TBd9yxF6FhYhfMwUEDDYaUrrGSgHRMJgpJ",
            "TGY7UTqSdS1A4CXsHFvR8qRFhSHSn4zSXT",
            "TV61VjeDAtPTuLrfDBbBj139vuvhjGr4qy",
            "TWtoPN4ZNQ9Qr7yyEyW6Aat8VJycNxJ8E4",
            "TUDjTAwaq4nj2HJANwqc7mF4mQ3Hhqs3VX",
            "TGpXTrLoJX7JQuV94U2ieYKnVdNgXPssN6",
            "TBW6PGw7PHuZiNWYr51JWwzs5zU891TUJB",
            "TG4DDUHsU9haW2EqVVo6barsBMSyb2SM86",
            "TMA9ma43rvdEZHygpDResKuQfuE2iide23",
            "TXD17YmEp1nRNUyxpB8aQ5ruxKAJRp68oL",
            "TExgUFR43XR7Q89C8nHMPmd2oxNJyM2j44",
            "TXxoKWsyw5x3wdeWnsqedjSk5nuLUGXcfT",
            "TR3DLthpnDdCGabhVDbD3VMsiJoCXY3bZd",
            "TBhxyECmAg3uCqqmEHQvGJbrgj9cn1yMZ1",
            "THDfpFaF3yhQD6USTMgykF76vmwpJWMkGM",
            "TB3hHBrQK7xKswgmQWP1hRdnobPPkVJ36J",
            "TWUs7FajJdRK26U1A5KZJbo8wP7jHiRFwk",
            "TSngG7y4RDSVG6QwoWM4MvVWJb3k8VLZJk",
            "TYAVfebadsPQxHH6Wp5nfS37G4ShNLxMqi",
            "TLQRG1vajqi3CiH27tHQNNjnK4dAMb4s3v",
            "TBp6ZMzkxci5o4sJjFa6Fo9Wy36gcubQLW",
            "TWGZ7HnAhZkvxiT89vCBSd6Pzwin5vt3ZA",
            "TLGpmtJf6C7ek3XdAKZXTKdTgYtdswoVai",
            "TGFtw5V842oiiE1remVrSFH19CVMaaaMcr",
            "TBYAX4PHE6oRP6F9d6exQLYqF5ctNyW9UE",
            "TQRqT39kCQWZCJtuYTA5Txn6ciNEPJU4uN",
            "THffre48BTSEuz2f81a5L1p24GKV8d4feu",
            "TQorn8S5iy6fMVkPJ41pjyYc9F7Axtm8B6",
            "TKxzVFGsBbQGLgMhpwbvkjBxuRCvLtExnK",
            "TFFxGGpVqxCDvo6upD3od5dFNtiRMv4Hc4",
            "TNo59Khpq46FGf4sD7XSWYFNfYfbc8CqNK",
            "TMNTn2uFAHhkGE3uuM84rhfRRjt6ry9xnL",
            "TRTpDp88d7BwxWM61ssExuFWDrZ39Dav95",
            "TPBCaP2KeEurcmU4aMnRHoq3yP8KYDdFuq",
            "TX4fcmMFDfwDgrWmvZBoJKUEwxTkqE96ty",
            "TGcjhDQ9d5d8DqkHgcdyUF6KsTSikYLUsE",
            "TXyrq6xE49dJhBGBYRH4pZzq9QdXNKWUQa",
            "TVbxxBaZLZ5UiAVF9ivuBizHVUZ9ndwTUZ",
            "TZHTVehxLeoVQ5uuQLy3TDx4Uuw6EmHVNF",
            "TLWH5YFXkd9UgU6gspkfy7bUEJuYmtDq5i",
            "TXHv4N6bbUnEogb7TJmsrWf8GVePtgdo4B",
            "TQ12oPTr9s6oJkkD4mo5gmEJMx1YgrwHc3",
            "TBgwArPSVBYvgaF4AQnHbtRpHHjQf6uXzJ",
            "TQvpksoUg42FMT5dmDJ685xWg1ZrXiwBTv",
            "TWGtWbv2aBTwbUK1NUktGyYhRDcgCHdCBa",
            "TJpV3HeJQBj6cgJEFRzzAAWi8sJdvuTduu",
            "TYPkXfKaGBJDjjs1pf9Xum6TwLEVxj77S3",
            "TSB3KYV2dSvafvDNnq82PWZeh4wP4FDhPK",
            "TVWpCqWppnqTqTvmJpV8ftUU5yqx78fC5o",
            "TMnqP8yuZrKJFXN59KoVDthkmM3LRkiZXP",
            "TKzmGojLDCCmgaKY39amVNnfkmJSGkKqNP",
            "TDAB8XCoVpqwVUBG8TTpX6WjvNEJVvFRGi",
            "THCkjnZTcHgEaDrcyrowTDHbDeeZm86J5C",
            "TDqGQLfAq37Y8hc5VNPNp8k8pV2AN1LYma",
            "TV2SZWCnsGLX3h9i3SZ1dyivDbeCuoB7fH",
            "TAnGtDViwZBsppvN3FTthNRmpSBnyMnaZ3",
            "TGBfBt6Y2Dm3RHdNpZAdqywBsvfdysf834",
            "TBygReeC6sYDVzpEQ8xjMEZBhd4PVCVew8",
            "TEqK3awVu3jy6xEg6LpNYwUgTHdzPBmzGz",
            "TFLu6ZctS5G8RFPBsAMZWCe7wZZno7gUQR",
            "TCMPsXjCA1FyvBrn4NR1xoMe5Cm1nLCJz8",
            "THsSSczBw9RRMJWYL5j2MtcgaPasL2xPGP",
            "TQDoSZbxZhW55qGq1P2RUpJTvA6HSUJWEp",
            "TTXx5wXS4pw63QzgrdxCsU96LC2Ed7BNPZ",
            "TDXJ1dSPLsN52jhP32NvVyhQ5X265BNU5c",
            "TWXZxJDGWVhNz2SXx31kQGA5kb2cjAz9ys",
            "TEoEk74PhcrtoZepG1LpELy8qEyxHVvBB7",
            "TBj4M1feMvimdJRoiAtP3evawQtLvsApEo",
            "TYz2awuo1Ba75ZWSqQWPJmDmDc1gRUmDGj",
            "TKkizhNw9Qrj7aR2jSmfRCeVeuGFzqvKiB",
            "TN4PXvT6uiev1pGtHsqNNfxJNrN12BPFAo",
            "TU7uTrWbnt9RVQJgXtWHrf6Jt6C6gmUFcx",
            "TDDS8x6inWYK2XrroxQLd4ZeKzjTG3zyPm",
            "TWvipYRjC6bsXRxupe4CSg2uzutHsNQvFr",
            "TKQKCpeMdhfCSBQKFeNUY48i4VU8BsbpGe",
            "TEzLwVRPoK77DYsCqpkLc5AkqiRALvnH8d",
            "TCkb46As8FHJHEH4F1zJ9taGPnGpQBgUm3",
            "TB73wkFj6CpUtWXUVs9Z1BKm1NnbfWyTMR",
            "TEQuSBH1jxGzqJfZSQx8tusHrLaKobnRZz",
            "TEfnpLH77nr5VuPBoFPmak4KrwV3TkcFwq",
            "TZ2YD4juh7nTDZ2YUB4UDsKvmbopxxP6Xa",
            "TL9gVJ25JaFAA25uygAHAtFXpHU3Vi3AF7",
            "TSUN94PqmxcVGxphWBQLgYwNks3HkFEzRr",
            "TGbt2sNbHb3Vjc6xnfvgXd52BrAMvkHQvG",
            "TAw8xzsg5GDJn48FjkxKjeJTdumFEieKyK",
            "TXMKm8FSp6zcm3cdgkgcM1fwdkuJbZ4Hgv",
            "TL7RJPC7zqpj9B2F4RRc4mk4YpzuVrd4Ar",
            "TWfTeP65rJebcppK4itGgwLFLrk4aswVaA",
            "TWHjymfkWsnhK41wBbCogWSKLCaDomEJMH",
            "TKGchxxbU71437A2xv6i6enzfXEsMaiKmE",
            "TCqEa49dKocHp5AnogfmgXkwut6upbuNUv"
    };


/*
* [
  "TEaqGBJqgZ8wT9CQDQ55zMRrKGftJHE51Q",
  "TT5ZmVgbxiPyixb5Y99F4i5vcDygwH1mPm",
  "TXHEjrFdBTJoXV5Q4QSZtoJm5RQS3UUqvP",
  "TRxiyR3cJPwyMMpq3WQQF7xiRkNDLkyd9X",
  "TWzxkYH7d4yj2zX479f4ywHo8QQLsywJgT",
  "TFtzWUH2oBXTXZfUSuS7CZJcj2BS2zr9Cq",
  "TXTNcgJHD9GPfpiTbSG2VGtfdfii9VcpEr",
  "TPVwNaCHo6po4s1Cvpo8q2Kdq9VnUBeXMF",
  "TKGRE6oiU3rEzasue4MsB6sCXXSTx9BAe3",
  "TXxTPxdfygLVjj6zLNukCELkGtMjPVQg16",
  "TB233wApK7DtxAsES8pvDuuNULR7uhBJvj",
  "TTQjvjhipmFCmChYQmNLo79S6UF8Fz63VE",
  "TQGbbjiEsk2nLq3nurEGL94rymyBsoY4ub",
  "TTJJvoPKGVKnbUBPVTn1Zi8o6k3EfFDXVS",
  "TDEmLiGoSgWHjnQiodF2vAp4dHAbSLzf3S",
  "TKpJUP4CCymphdug1XmGzDGDmGXZjLyf29",
  "TDV741pDJPbH89ucYnrZeEpQrzmQMQ3jZX",
  "TXNKy284JtZv8DZvzUPxvAtR1PBhYa3N5X",
  "TQ9gXwoLcLi6W5gV5RjRykVuLVYTDY54nK",
  "TRXPT6Ny7EFvTPv7mFUqaFUST39WUZ4zzz",
  "TQ6j2LUBnPQScrHxgTrDNTKUD3J8p6qo6L",
  "TZCPuWgEoQqqQgvyeUWJgrQyjTNJVtouiu",
  "TD8xC9omUeZDsDLjGUjhfVFLEVyzH6S3nv",
  "TRx4znAxu5FWxb5ccVUX89TtZ8qWF2PM2b",
  "TGXD5vRuCng2k8Ei51u4fmLxYzAUcJngGP",
  "TBkfseHPqFrT3N2jEQjuWCRcjL1tpJpwYP",
  "TDdau99uUurNVnfJwguVqxfjCWLvGNKWwn",
  "TG3Bm5oPtXbyEc3K85ssMxDfFRik7A9g7A",
  "TEFfp7bvxAdhsH1uCsAL17qbcR5RSDPcFB",
  "TWtdkGzHbMz7EcgvXHZcf4RzAXaNCyNTkj",
  "TFZn8ujPoZLc8h4ZAaApon8bPMXcihdn7j",
  "TERUsMsUrgw6eJEAaXZwmtnHwR2DKWaa6Q",
  "TUsUQUgr3bbR62iZeVM3gNxMCTgBVPNFnV",
  "THph9K2M2nLvkianrMGswRhz5hjSA9fuH7",
  "TCkvyP5AzwuMVmgmX59kTC8KWbshf99999",
  "TCrDi83pUoK17GbwxN1SckM3YNXzahWvoN",
  "THa9tCSeDyJns8fWCJoYTHZWsLEUfLtDcy",
  "TDcHeAMHPYfJKsknLCnV89K9wu8dqdD3rm",
  "TEUyJsXbKbC1oprGnEpeADPyKEPLQJ2Rid",
  "TB89kyWjpu8Q1uNTZcUA6cjeENCEvXTyUX",
  "TXDaynu1efMr4NHcidQEkUVWsvoS4rn6LK",
  "TU5AuFTkKaAypdz5SdwyGhKDf97ZfoPWn2",
  "TL9U7eUUthDe946uCj97W1gnM55GQRVnih",
  "TWjvFoH2HgkNCsf897tG5BSzx7ZpfkqHPs",
  "TUTxy4PRoVoFMeCgq2sGfiLYZhLTSG6tM1",
  "TCacbFbmK9WjRzeEpqkGRoPRewagQM7MG8",
  "TFRWUrJ4Yp8zxZn7xvyoVFfhe4iHnhLvuC",
  "TKYT8YiiL58h8USHkmVEhCYpNfgSyiWPcW",
  "TDchKqQ8T2BhGfL7m2DfWfxp5eqa1we5hu",
  "TPYFECURCk4jvD41KfDpFNx5sNiCYtEnRZ"
]
*
* [
  "TE57xfg6d7qRZvsyyzd7x899JfKKyfcdoU",
  "TDeXdjHSF5YwwbaWVpRZKS9DUj8mo3Azxu",
  "TLUuESW7uWRYjU4HBWoXMr3G4JcRoZn9eu",
  "TMU2ZzHzFvKfPTqfVzVH7PSLMTpfyP2SYp",
  "TMkb6t1FPBSuBNBWXvkWEBF8sAxqmXFc91",
  "TTeT5eGRpDumftCCjKrWUvvUKuLZsxuZf9",
  "TA1sv7PxgJXhAbRDJyqyMBrFxhGHZCZDw9",
  "TTkZjy57233jiTf9rjqEBCD1UAAgxxgYKM",
  "TDBvcfjQrdWQCsAnZGmDH3NmenqVtyVd5n",
  "TXmTRMpZMh8wUHkigkDkxWHmTatUeDxyE4",
  "TJd1DpcSzFgFoMqUk23SSWeggzcmM6EdQe",
  "TXBHL93y2mhbJcyGPpvpeoToH1eRheFaou",
  "TJEuSMoC7tbs99XkbGhSDk7cM1xnxR931s",
  "TD3kix5cjeveSjTbCUo4K2B4GAZH8Gzhnv",
  "TVBmzABEN9kLtbp9Hr2EJWpjdFRzUxUhPs",
  "TQ9vk8MvjahkxKr3iCYJHtj2cT1bVCSP3h",
  "TWP5toD9aPuWd77SCXwSZkhrs1Ex4vPvgn",
  "TLKQfdBL9UMB3WCENTPtvCueqjymhH7PNp",
  "TQ3stkRQ2tisZsA59JZNHdi8Qx2yMbshwL",
  "TWPL7jfPxYHYCi2AGhVG8YnT7WY859RrYM",
  "TWRQyMTZVDGmEqzKsLh9HCx7t9fZKupXMd",
  "TDdoeSCEGqfFwUBAn4cf1sVrQ9t7SqdM4L",
  "TR1iBZZBjJKsAfNnUaZzjYeSbv685ULeax",
  "TG18qpVeKEanswrcPVWguRRr56Rirof5Ub",
  "TFuu73qQ8sPqQYMSB169fp9F3u7n1q7kZo",
  "TCsSECZsCeaUiZkeZ1nc1nqLN15H2xrWRe",
  "TWCj8RT256Yjct4bXpc1Zh1J6siAH9EsQW",
  "TUHUShjKGuk6eSAk5asQMzY3rqk8kmrNYo",
  "TVRU7mHANTwKZdyRYnWW5pJpHrVApbkF97",
  "TFj4syqrAQp545Xgaw8NDew79U6PUHVMBZ",
  "TZJ6cum2HGUpLACw9rKoRjZJi7os14REQf",
  "TD2PzKBW9A5LyuXs1s1Z7ijGHF774ffv4i",
  "TWuPoiLwpU7bJ1Wu68vtWZQ8HQCmYXet93",
  "TD7LffYdhnQt6rj9VYij6M7CRddtqLwfmE",
  "TT4MHXVApKfbcq7cDLKnes9h9wLSD4eMJi",
  "TXmHTj3t5LXGvqGkr4jRNw7nf9GjquQ5yf",
  "TUBqwNDdewu8B5g9GYRa1uCFxrujNaoq8g",
  "TTVfeUpfxxmo1ed3Z9wXfYhRmRa1Wk8yjU",
  "TPb6X8ZHUgA4PfH8DYwVf4UAphBzqL5pYw",
  "TM8CFhGEm4YP6t5WNsSamdBCw3tycQaiMn",
  "TPC3igJhbTDxoRpNmMJcLom4r9nbDHMjXu",
  "TF4WKLbLVomXTKjM8J1U8ZhjemW8uuPWat",
  "TRURXG7kZ1WfDxptA5pUgGt3g2SYFwLze9",
  "TLN3noetv7xPkTi1Qy71apidRMiJU8smHW",
  "TTwjBQdkhcieG7aPHGME15gJKkt7MiZ8qF",
  "TS8cwD4jmjkqZrF1QX5MbzQNww6tfEU5vT",
  "TT1smsmhxype64boboU8xTuNZVCKP1w6qT",
  "TWpFXPyjtG7EgsF64Qm4k8wzKge7hRZp6A",
  "TVBtmzbTE3D7S2wbRoEBBqA6mF2NoBGQMq",
  "TTjKhHGXiUX1aUub8cr35rgs2M92botRyd"
]
*
* */










}
