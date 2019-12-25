package activityServer.com.apiTest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import org.apache.http.HttpResponse;
import org.junit.Assert;
import org.omg.CORBA.Any;
import org.openqa.selenium.json.Json;
import org.openqa.selenium.json.JsonOutput;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;

import activityServer.com.utils.api;
import activityServer.com.utils.tronlinkSocketClient;

/**
 * Created by wangzihe on 2019/11/12.
 */

public class multiTransaction {
    private HttpResponse response;
    private JSONObject responseContent;
    private HashMap<String,String> param = new HashMap<>();
    private String multiAddress = "418da4f11299320fee533deaedb5523981320ac472";
    private String multiKey = "dc55f562ff23aea25241f671372447d439ebecd19cb266cce3fa07dc96494c0f";
    private String fullnodeHttpServer = "47.252.85.177:8090";
    private String base58MutiAddress = "TNt9sTSLrjdFJmyeQGYSMGu5sVat1Ew3Tz";
    private JSONObject rawDataObject;
    private JSONArray signatureArray;
    private JSONArray signature;
    private String signatureString;
    private String foundationAccountKey = "fb4bd20d2af1bfad551f917f6110cdc8948cb41cbf5e6e3dbd26acb0f20eecef";
    private String foundationAccountAddress = "TFoPyG82ixipCTVqzkmLa9ivftHcsrXVrp";
    private String foundationBase58Address = "413ff5c065bdcdf7c3da16823ad0f6d3dff611122e";
    private String hash;

    @Test(enabled = false, description = "Api first persion multi transaction")
    public void test001FirstOneMultiTransaction() throws Exception {
        String signString = api.getTransactionSignStringFromFullnode(fullnodeHttpServer,multiAddress,foundationAccountAddress,10L,3,foundationAccountKey);
        JsonObject jsonObject = new JsonParser().parse(signString).getAsJsonObject();
        jsonObject.remove("raw_data_hex");
        jsonObject.remove("txID");
        jsonObject.remove("visible");

        JsonObject transaction = new JsonObject();
        transaction.add("transaction",jsonObject);
        transaction.addProperty("address",base58MutiAddress);
        transaction.addProperty("netType","main_net");
        System.out.println(transaction);
        response = api.multiTransaction(transaction);
        api.printJsonContent(api.parseResponseContent(response));



    }
    @Test(enabled = true, description = "Api multi transaction")
    public void test002FirstOneMultiTransaction() throws Exception {
        String url = "wss://list.tronlink.org/api/wallet/multi/socket?address=" + base58MutiAddress + "&netType=main_net";
        tronlinkSocketClient mWs = new tronlinkSocketClient(url);
        mWs.connect();
        int i = 0;
        while (!mWs.isOpen() && i++ <= 5) {
            //System.out.println(mWs.getURI().toString());
            //System.out.println(mWs.isOpen());
            Thread.sleep(3000);
        }


        String transaction = tronlinkSocketClient.getResonse();
        rawDataObject = JSON.parseObject(transaction);
        JSONArray transactionArray = rawDataObject.getJSONArray("data");

        if (!transactionArray.isEmpty()){
            hash = transactionArray.getJSONObject(0).getString("hash");
            rawDataObject = transactionArray.getJSONObject(0).getJSONObject("currentTransaction").getJSONObject("raw_data");
            signatureArray = transactionArray.getJSONObject(0).getJSONObject("currentTransaction").getJSONArray("signature");
            //signature = transactionArray.getJSONObject(0).getJSONObject("currentTransaction").getJSONArray("signature");
        }

        System.out.println("rawDataObject:" + rawDataObject);
    }

//    @Test(enabled = true, description = "Api multi transaction when this transaction not create from tronlink,")
//    public void test003getTransaction() throws Exception {
//
//        String[] keyArray = new String[2];
//        keyArray[0] = foundationAccountKey;
//        //keyArray[1] = foundationAccountKey;
//        String signString = api.gettransactionsign(fullnodeHttpServer,rawDataObject.toString(),foundationAccountKey);
//        System.out.println(signString);
//        JsonObject jsonObject = new JsonParser().parse(signString).getAsJsonObject();
//        JsonObject rawdataObject = new JsonParser().parse(rawDataObject.toString()).getAsJsonObject();
//
//        JsonObject transaction = new JsonObject();
//        transaction.addProperty("address",base58MutiAddress);
//        transaction.addProperty("netType","main_net");
///*        jsonObject.remove("raw_data_hex");
//        jsonObject.remove("txID");
//        jsonObject.remove("visible");
//        jsonObject.remove("raw_data");*/
//        jsonObject.add("raw_data",rawdataObject);
//        transaction.add("transaction",jsonObject);
//        System.out.println("transaction:" + transaction);
//        response = api.multiTransaction(transaction);
//        api.printJsonContent(api.parseResponseContent(response));
//    }
    @Test(enabled = true, description = "Api multi transaction when this transaction not create from tronlink,")
    public void test003getTransaction() throws Exception {
        if (!hash.isEmpty()){
            JSONObject transaction = new JSONObject();
            transaction.put("address",base58MutiAddress);
            transaction.put("netType","main_net");
            JSONObject raw = new JSONObject();
            signatureArray.add(api.getSignature(hash,multiKey));
            raw.put("signature",signatureArray);
            raw.put("raw_data",rawDataObject);
            transaction.put("transaction",raw);
            System.out.println(transaction);
            response = api.multiTransaction(transaction);
            JSONObject jsonObject = api.parseResponseContent(response);
            api.printJsonContent(api.parseResponseContent(response));
            Assert.assertEquals(jsonObject.getString("message"),"OK");
        }

    }
}
