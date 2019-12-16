package android.com.utils;

import com.alibaba.fastjson.JSONArray;
import com.google.gson.JsonObject;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class autoCreateTestngXml {
    private String reportPath = "src/test/resources/tronlink-testng.xml";
    //private String adb = "/Users/tron/Library/Android/sdk/platform-tools/adb";
    private String adb = "adb";
    private String packagesName = "<package name=\"android.com.tronlink.wallet.regression.*\"></package>";
    private String singleTestPackagesName = "<package name=\"android.com.tronlink.wallet.committeeProposal.*\"></package>";
    private String platformName = "Android";
    private Boolean noReset = false;
    private Integer systemPort = 8200;
    private Integer port = 4800;
    List<String> deviceNameList = new ArrayList<>();
    static HttpClient httpClient;
    static HttpPost httppost;
    static HttpResponse response;
    static Integer connectionTimeout = 2000;
    static Integer soTimeout = 2000;
    static String transactionString;
    static String transactionSignString;
    static JSONObject responseContent;
    static JSONObject signResponseContent;
    static JSONObject transactionApprovedListContent;

    static {
        PoolingClientConnectionManager pccm = new PoolingClientConnectionManager();
        pccm.setDefaultMaxPerRoute(20);
        pccm.setMaxTotal(100);

        httpClient = new DefaultHttpClient(pccm);
    }


    @BeforeClass
    public void beforeClass() throws IOException{
        try {
            deviceNameList = AppiumTestCase.getDeviceList(adb + " devices");
        } catch (Exception e) {
            adb = "/Users/tron/Library/Android/sdk/platform-tools/adb";
            deviceNameList = AppiumTestCase.getDeviceList(adb + "  devices");
        }
        beforeWrite();
    }


    @Test(enabled = true)
    public void sendCoinToTestCount() throws IOException{
        String foundationAccountKey = "7400E3D0727F8A61041A8E8BF86599FE5597CE19DE451E59AED07D60967A5E25";
        String foundationAccountAddress = "TKpJUP4CCymphdug1XmGzDGDmGXZjLyf29";
        String httpnode = "47.252.85.177:8090";
        String dappChainHttpNode = "47.252.80.185:8090";
        //niluohe
        //String httpnode = "47.90.214.183:50051";
        HashMap<String,String> testAccountList = new HashMap<>();
        testAccountList.put("TR8CyAPJFMjCvphCVuWeeVxBh5iTG7VWxe","cfd889566341aea937737ecf4bc35f9be7c5b43f594c9a230a0348183472245e");
        testAccountList.put("TMhGDU7NiXwckCW64PqAvWFuC2kR1WSF5J","11c7013416aac83fd6070abb8ffceb0ad102d9f87dfc9c98308b0fd47e8c3a1a");
        testAccountList.put("TDf3JZtjDeEqsFdPGp6vT9meG3JxMwmXwA","0ea138885c1fb2b6adaad51033c8876df0e37ccf7dd322cfad5504d671fb2a79");
        testAccountList.put("TEtG9fnVi2qythiog6owPrg4sD9rwFBQBN","8f3839e21b4ada348da3d85ccc72e1f1898a39b877f0f6f5b35137588a344345");
        testAccountList.put("TUvda1oqrNLbqDKhZDxDnrPhiDCdxem218","cf9933e99ee2b272147dd563c7f880de751c30d61cd6681a158f0a8056023d9b");
        testAccountList.put("TKEH31jJ2YQ3Bteh1ngjwdT8667ztyYPSp","223f2e4a3010286540d3c119d2a1d55b1d54248f63f1c4d9ccfbd0d105ab15c7");
        testAccountList.put("TAzrJHKa57nXnn3dZGFG87PDuWx12dY97s","844f7f5da381943403e8324db4fda13dce9af35b72cf2ea3846fafa12c5d9890");
        testAccountList.put("TWhc6AAh6BWRr3k5dV8iMvkp8ys7NHzXCk","6850fd0a0f2cb94167bf0507a738fa9eef51d6fdc65e8452039f711a4bdf3135");
        testAccountList.put("TSsaSxHnb3xLTop2A8LrDk1P896yiDeupe","cec7fc3c9c603ae6cdc026c777db037b8ca4995d451fa5fe7b2f19a0dc01cd98");
        testAccountList.put("TMDs8oTj8mVnakqiVyDKdp2ruWPdFeDgbq","7652071f95c376e6d1100f9fed5c520f22262c1530f328bb1c3ed10bad771e68");
//        testAccountList.put("TWv2FEsoPp5XKxujVHffoNwksgJSxvf3QG","6a77e8edd232f4102e4fcaca02234df7176a9398fdde1792ae5377b009482fca");
//        testAccountList.put("TGamEmt6U9ZUg9bFsMq7KT9bRa3uvkdtHM","a3f47c598631ada1d24e186f96b9d6e5e5fcd1123bb51d4adfe08bb7c081ffde");
//        testAccountList.put("TXhQk442CCGLydh6cfyfqvM6yJanEGeQj1","b50aa8ce2140be6995e79d657064e5a3983ac0a47bfdcbb5e9f4b930ba2996a5");
//        testAccountList.put("TKktQcbjXsXZDKPYLvUm8sxox2cT83g5rP","d4446cf4ccfe02f165f0ba01e3d5a56546e41eebf26c3cfe33564bababeef74d");
//        testAccountList.put("TBQUhYhdQpMRksBGAbpbTWSiE7WkGgy3Km","3999ce04f0ba5e05776d355b194f369a6d56f4fd7711a31adf2044690236bf5b");
//        testAccountList.put("TALf34yjuLZjF1WQqCaUkf73X8WbhfiEyM","022f883a91a14567a8b1ad9722b73971f5c748586e951b7a8eed0ef6e29950ac");
//        testAccountList.put("TCGp3JAFM5vQZpsdNiKRTci7fVb7A2TPcu","4865dab66fe80391f8de760a586258dc3ebff66ee6408c2eff85e1a2e3e43e10");
//        testAccountList.put("TBExF3mNvnhmEFgHW4TmYXXdhevRchnQyb","a1866b9c8b2effb0edc091b3d56b787a03b455b8b001414cb19acc1869230026");
//        testAccountList.put("TS8o6WcHroSnzWNt4AiserAuVkye5Msvcm","f88184cfc003612d02b94956bccde12b8086c5010b3401357e7bdc8dd7727f4d");
//        testAccountList.put("TBtMRD79NkLyAvMkCTTj5VC5KZnz2Po2XZ","71951c4a6b1d827ee9180ddd46d61b9963c2763737f3d3724049c6ae50e5efed");

        Long balance = 0L;
        Long targetAmount = 1998000000L;
        Long tokenBalance = 0L;
        Long targetTokenAmount = 500000000L;
        for (HashMap.Entry entry : testAccountList.entrySet()) {
            try {
                balance = 0L;
                balance = getBalance(httpnode, entry.getKey().toString());
                tokenBalance = 0L;
                tokenBalance = getTokenBalance(httpnode,entry.getKey().toString());
            } catch (Exception e) {
                System.out.print(e + "\n");
            }
            System.out.print("balance:" + balance + "\n");
            if (balance <= targetAmount * 3 / 5) {
                sendCoin(httpnode,foundationAccountAddress,entry.getKey().toString(),targetAmount - balance,foundationAccountKey);
                //freezeBalance(httpnode,foundationAccountAddress,7000000000L,3,0,entry.getKey().toString(),foundationAccountKey);
            }

            if (tokenBalance <= targetTokenAmount * 3 / 5) {
                transferAsset(httpnode,foundationAccountAddress,entry.getKey().toString(),"1000042",targetTokenAmount - tokenBalance,foundationAccountKey);
            }

        }

        for (HashMap.Entry entry : testAccountList.entrySet()) {
            try {
                balance = 0L;
                balance = getBalance(dappChainHttpNode, entry.getKey().toString());
                tokenBalance = 0L;
                tokenBalance = getTokenBalance(dappChainHttpNode,entry.getKey().toString());
            } catch (Exception e) {
                System.out.print(e + "\n");
            }
            System.out.print("balance:" + balance + "\n");
            if (balance <= targetAmount * 3 / 5) {
                sendCoin(dappChainHttpNode,foundationAccountAddress,entry.getKey().toString(),targetAmount - balance,foundationAccountKey);
                //freezeBalance(dappChainHttpNode,foundationAccountAddress,7000000000L,3,0,entry.getKey().toString(),foundationAccountKey);
            }

            if (tokenBalance <= targetTokenAmount * 3 / 5) {
                transferAsset(dappChainHttpNode,foundationAccountAddress,entry.getKey().toString(),"1000042",targetTokenAmount - tokenBalance,foundationAccountKey);
            }


        }

    }


    @Test(enabled = true)
    public void createXml() throws IOException {
        HashMap<String,String> testAccountList = new HashMap<>();
        testAccountList.put("TR8CyAPJFMjCvphCVuWeeVxBh5iTG7VWxe","cfd889566341aea937737ecf4bc35f9be7c5b43f594c9a230a0348183472245e");
        testAccountList.put("TMhGDU7NiXwckCW64PqAvWFuC2kR1WSF5J","11c7013416aac83fd6070abb8ffceb0ad102d9f87dfc9c98308b0fd47e8c3a1a");
        testAccountList.put("TDf3JZtjDeEqsFdPGp6vT9meG3JxMwmXwA","0ea138885c1fb2b6adaad51033c8876df0e37ccf7dd322cfad5504d671fb2a79");
        testAccountList.put("TEtG9fnVi2qythiog6owPrg4sD9rwFBQBN","8f3839e21b4ada348da3d85ccc72e1f1898a39b877f0f6f5b35137588a344345");
        testAccountList.put("TUvda1oqrNLbqDKhZDxDnrPhiDCdxem218","cf9933e99ee2b272147dd563c7f880de751c30d61cd6681a158f0a8056023d9b");
        testAccountList.put("TKEH31jJ2YQ3Bteh1ngjwdT8667ztyYPSp","223f2e4a3010286540d3c119d2a1d55b1d54248f63f1c4d9ccfbd0d105ab15c7");
        testAccountList.put("TAzrJHKa57nXnn3dZGFG87PDuWx12dY97s","844f7f5da381943403e8324db4fda13dce9af35b72cf2ea3846fafa12c5d9890");
        testAccountList.put("TWhc6AAh6BWRr3k5dV8iMvkp8ys7NHzXCk","6850fd0a0f2cb94167bf0507a738fa9eef51d6fdc65e8452039f711a4bdf3135");
        testAccountList.put("TSsaSxHnb3xLTop2A8LrDk1P896yiDeupe","cec7fc3c9c603ae6cdc026c777db037b8ca4995d451fa5fe7b2f19a0dc01cd98");
        testAccountList.put("TMDs8oTj8mVnakqiVyDKdp2ruWPdFeDgbq","7652071f95c376e6d1100f9fed5c520f22262c1530f328bb1c3ed10bad771e68");
//        testAccountList.put("TWv2FEsoPp5XKxujVHffoNwksgJSxvf3QG","6a77e8edd232f4102e4fcaca02234df7176a9398fdde1792ae5377b009482fca");
//        testAccountList.put("TGamEmt6U9ZUg9bFsMq7KT9bRa3uvkdtHM","a3f47c598631ada1d24e186f96b9d6e5e5fcd1123bb51d4adfe08bb7c081ffde");
//        testAccountList.put("TXhQk442CCGLydh6cfyfqvM6yJanEGeQj1","b50aa8ce2140be6995e79d657064e5a3983ac0a47bfdcbb5e9f4b930ba2996a5");
//        testAccountList.put("TKktQcbjXsXZDKPYLvUm8sxox2cT83g5rP","d4446cf4ccfe02f165f0ba01e3d5a56546e41eebf26c3cfe33564bababeef74d");
//        testAccountList.put("TBQUhYhdQpMRksBGAbpbTWSiE7WkGgy3Km","3999ce04f0ba5e05776d355b194f369a6d56f4fd7711a31adf2044690236bf5b");
//        testAccountList.put("TALf34yjuLZjF1WQqCaUkf73X8WbhfiEyM","022f883a91a14567a8b1ad9722b73971f5c748586e951b7a8eed0ef6e29950ac");
//        testAccountList.put("TCGp3JAFM5vQZpsdNiKRTci7fVb7A2TPcu","4865dab66fe80391f8de760a586258dc3ebff66ee6408c2eff85e1a2e3e43e10");
//        testAccountList.put("TBExF3mNvnhmEFgHW4TmYXXdhevRchnQyb","a1866b9c8b2effb0edc091b3d56b787a03b455b8b001414cb19acc1869230026");
//        testAccountList.put("TS8o6WcHroSnzWNt4AiserAuVkye5Msvcm","f88184cfc003612d02b94956bccde12b8086c5010b3401357e7bdc8dd7727f4d");
//        testAccountList.put("TBtMRD79NkLyAvMkCTTj5VC5KZnz2Po2XZ","71951c4a6b1d827ee9180ddd46d61b9963c2763737f3d3724049c6ae50e5efed");

        StringBuilder sb = new StringBuilder();

        {
            int singleTestPackageIndex = 0;
            Iterator<HashMap.Entry<String, String>> entries = testAccountList.entrySet().iterator();
            for (Iterator<String> it = deviceNameList.iterator(); it.hasNext()&&entries.hasNext(); ) {
                HashMap.Entry<String, String> entry = entries.next();
                String udid = it.next();
                sb.append("    <test name= \"" + udid + "\">\n");
                //adb = "/Users/tron/Library/Android/sdk/platform-tools/adb -s " + udid;
                adb = "adb -s " + udid;
                AppiumTestCase.cmdReturn(adb + " uninstall com.tronlink.wallet");
                System.out.print("Uninstall succesfully\n");
                //install app
                //AppiumTestCase.cmdReturn(adb + " install -r TronLink.apk");
                AppiumTestCase.cmdReturn(adb + " install TronLink.apk");
                System.out.print("Install succesfully");
                String platformVersion = AppiumTestCase
                    .cmdReturn(adb + " shell getprop ro.build.version.release");
                String deviceName = AppiumTestCase
                    .cmdReturn(adb + " shell getprop ro.product.name");
                sb.append("        <parameter name=\"port\"  value=\"" + port++ + "\"/>\n");
                sb.append("        <parameter name=\"udid\" value=\"" + udid + "\"/>\n");
                sb.append(
                    "        <parameter name=\"platformName\" value=\"" + platformName + "\"/>\n");
                sb.append("        <parameter name=\"platformVersion\" value= \"" + platformVersion
                    + "\"/>\n");
                sb.append(
                    "        <parameter name=\"deviceName\" value= \"" + deviceName + "\"/>\n");
                sb.append(
                    "        <parameter name=\"noReset\" value=\"" + noReset.toString() + "\"/>\n");
                sb.append(
                    "        <parameter name=\"systemPort\"  value=\"" + systemPort++ + "\"/>\n");
                sb.append(
                    "        <parameter name=\"address\"  value=\"" + entry.getKey()
                        + "\"/>\n");
                sb.append(
                    "        <parameter name=\"privateKey\"  value=\"" + entry.getValue()
                        + "\"/>\n");
                sb.append("        <packages>\n" +
                    "            " + packagesName + "\n");
                if (singleTestPackageIndex++ == 0) {
                    sb.append("            " + singleTestPackagesName + "\n");
                }
                sb.append("        </packages>\n");
                sb.append("    </test>\n");
                it.remove();
            }
        }

        sb.append("</suite>");
        String res = sb.toString();
        try {
            Files.write((Paths.get(reportPath)), res.getBytes("utf-8"), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void beforeWrite() {
        StringBuilder sb = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
            + "<!DOCTYPE suite SYSTEM \"http://testng.org/testng-1.0.dtd\">\n"
            + "<suite name=\"android.com.tronlink\" parallel=\"tests\" thread-count=\"" + deviceNameList.size() + "\">\n"
            + "    <listeners>\n"
            + "        <listener class-name=\"android.com.wallet.UITest.retry.RetryListener\"/>\n"
            + "    </listeners>\n");
        String res = sb.toString();
        try {
            Files.write((Paths.get(reportPath)), res.getBytes("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static HttpResponse sendCoin(String httpNode, String fromAddress, String toAddress,
        Long amount, String fromKey) {
      try {
            final String requestUrl = "http://" + httpNode + "/wallet/createtransaction";
            JsonObject userBaseObj2 = new JsonObject();
            userBaseObj2.addProperty("to_address", toAddress);
            userBaseObj2.addProperty("owner_address", fromAddress);
            userBaseObj2.addProperty("amount", amount);
            userBaseObj2.addProperty("visible", true);
            System.out.print("userBaseObj2:" + userBaseObj2.toString());
            response = createConnect(requestUrl, userBaseObj2);
            transactionString = EntityUtils.toString(response.getEntity());

            transactionSignString = gettransactionsign(httpNode, transactionString, fromKey);
            response = broadcastTransaction(httpNode, transactionSignString);
        } catch (Exception e) {
            e.printStackTrace();
            httppost.releaseConnection();
            return null;
        }
        return response;
    }

    /**
     * constructor.
     */
    public static HttpResponse transferAsset(String httpNode, String ownerAddress,
                                             String toAddress, String assetIssueById, Long amount, String fromKey) {
        try {
            final String requestUrl = "http://" + httpNode + "/wallet/transferasset";
            JsonObject userBaseObj2 = new JsonObject();
            userBaseObj2.addProperty("owner_address", ownerAddress);
            userBaseObj2.addProperty("to_address", toAddress);
            userBaseObj2.addProperty("asset_name", assetIssueById);
            userBaseObj2.addProperty("amount", amount);
            userBaseObj2.addProperty("visible", true);
            response = createConnect(requestUrl, userBaseObj2);
            transactionString = EntityUtils.toString(response.getEntity());
            System.out.print(transactionString);
            transactionSignString = gettransactionsign(httpNode, transactionString, fromKey);
            response = broadcastTransaction(httpNode, transactionSignString);
        } catch (Exception e) {
            e.printStackTrace();
            httppost.releaseConnection();
            return null;
        }
        return response;
    }




    public static HttpResponse createConnect(String url, JsonObject requestBody) {
        try {
            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,
                connectionTimeout);
            httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, soTimeout);
            httppost = new HttpPost(url);
            httppost.setHeader("Content-type", "application/json; charset=utf-8");
            httppost.setHeader("Connection", "Close");
            if (requestBody != null) {
                StringEntity entity = new StringEntity(requestBody.toString(), Charset.forName("UTF-8"));
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                httppost.setEntity(entity);
            }
            response = httpClient.execute(httppost);
        } catch (Exception e) {
            e.printStackTrace();
            httppost.releaseConnection();
            return null;
        }
        return response;
    }


    public static String gettransactionsign(String httpNode, String transactionString,
        String privateKey) {
        try {
            String requestUrl = "http://" + httpNode + "/wallet/gettransactionsign";
            JsonObject userBaseObj2 = new JsonObject();
            userBaseObj2.addProperty("transaction", transactionString);
            userBaseObj2.addProperty("privateKey", privateKey);
            response = createConnect(requestUrl, userBaseObj2);
            transactionSignString = EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
            httppost.releaseConnection();
            return null;
        }
        return transactionSignString;
    }

    public static HttpResponse broadcastTransaction(String httpNode, String transactionSignString) {
        try {
            String requestUrl = "http://" + httpNode + "/wallet/broadcasttransaction";
            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,
                connectionTimeout);
            httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, soTimeout);
            httppost = new HttpPost(requestUrl);
            httppost.setHeader("Content-type", "application/json; charset=utf-8");
            httppost.setHeader("Connection", "Close");
            if (transactionSignString != null) {
                StringEntity entity = new StringEntity(transactionSignString, Charset.forName("UTF-8"));
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                httppost.setEntity(entity);
            }
            response = httpClient.execute(httppost);
        } catch (Exception e) {
            e.printStackTrace();
            httppost.releaseConnection();
            return null;
        }

        responseContent = parseResponseContent(response);
        Integer times = 0;

        while (times++ <= 10 && responseContent.getString("code") != null && responseContent
                .getString("code").equalsIgnoreCase("SERVER_BUSY")) {
            try {
                response = httpClient.execute(httppost);
            } catch (Exception e) {
                e.printStackTrace();
                httppost.releaseConnection();
                return null;
            }
            responseContent = parseResponseContent(response);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        httppost.releaseConnection();
        return response;
    }

    public static JSONObject parseResponseContent(HttpResponse response) {
        try {
            String result = EntityUtils.toString(response.getEntity());
            StringEntity entity = new StringEntity(result, Charset.forName("UTF-8"));
            response.setEntity(entity);
            JSONObject obj = JSONObject.parseObject(result);
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Long getBalance(String httpNode, String queryAddress) {
        try {
            String requestUrl = "http://" + httpNode + "/wallet/getaccount";
            JsonObject userBaseObj2 = new JsonObject();
            userBaseObj2.addProperty("address", queryAddress);
            userBaseObj2.addProperty("visible", true);
            response = createConnect(requestUrl, userBaseObj2);
        } catch (Exception e) {
            e.printStackTrace();
            httppost.releaseConnection();
            return null;
        }
        responseContent = parseResponseContent(response);
        //HttpMethed.printJsonContent(responseContent);
        httppost.releaseConnection();
        return Long.parseLong(responseContent.get("balance").toString());
    }


    public static Long getTokenBalance(String httpNode, String queryAddress) {
        try {
            String requestUrl = "http://" + httpNode + "/wallet/getaccount";
            JsonObject userBaseObj2 = new JsonObject();
            userBaseObj2.addProperty("address", queryAddress);
            userBaseObj2.addProperty("visible", true);
            response = createConnect(requestUrl, userBaseObj2);
        } catch (Exception e) {
            e.printStackTrace();
            httppost.releaseConnection();
            return null;
        }
        responseContent = parseResponseContent(response);
        JSONArray tokenArray = responseContent.getJSONArray("assetV2");
        for (int i = 0; i < tokenArray.size();i++) {
            System.out.print("V2 token:" + String.valueOf(tokenArray.getJSONObject(i).get("key")));
            if (Integer.valueOf(String.valueOf(tokenArray.getJSONObject(i).get("key"))) == 1000042) {
                return Long.parseLong(tokenArray.getJSONObject(i).get("value").toString());
            }
        }
        //HttpMethed.printJsonContent(responseContent);
        httppost.releaseConnection();
        return 0L;
    }



    public static HttpResponse freezeBalance(String httpNode, String ownerAddress,
                                             Long frozenBalance, Integer frozenDuration, Integer resourceCode, String receiverAddress,
                                             String fromKey) {
        try {
            final String requestUrl = "http://" + httpNode + "/wallet/freezebalance";
            JsonObject userBaseObj2 = new JsonObject();
            userBaseObj2.addProperty("owner_address", ownerAddress);
            userBaseObj2.addProperty("frozen_balance", frozenBalance);
            userBaseObj2.addProperty("frozen_duration", frozenDuration);
            userBaseObj2.addProperty("visible", true);
            if (resourceCode == 0) {
                userBaseObj2.addProperty("resource", "BANDWIDTH");
            }
            if (resourceCode == 1) {
                userBaseObj2.addProperty("resource", "ENERGY");
            }
            if (receiverAddress != null) {
                userBaseObj2.addProperty("receiver_address", receiverAddress);
            }
            response = createConnect(requestUrl, userBaseObj2);
            transactionString = EntityUtils.toString(response.getEntity());
            System.out.print(transactionString);
            transactionSignString = gettransactionsign(httpNode, transactionString, fromKey);
            response = broadcastTransaction(httpNode, transactionSignString);
        } catch (Exception e) {
            e.printStackTrace();
            httppost.releaseConnection();
            return null;
        }
        return response;
    }



}
