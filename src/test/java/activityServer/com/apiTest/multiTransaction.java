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

    private String testKey = "de164ddc64b1783c3ebced73b32c479b3daa203cf323bd738a9ecfc4674d6017";
    private String testAddress = "TN2jfdYCX9vvozqjwVvPjMd7vRj8HKyxUe";

    @Test(enabled = false, description = "Api first persion multi transaction")
    public void test000FirstOneMultiTransaction() throws Exception {
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
    public void test001FirstOneMultiTransaction() throws Exception {
        String url = "wss://testlist.tronlink.org/api/wallet/multi/socket?address=" + base58MutiAddress + "&netType=main_net";
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

    @Test(enabled = true, description = "SignOldTransactionERROR")
    public void test003signError() throws Exception {
        String oldTransaction = "{\"address\":\"TN2jfdYCX9vvozqjwVvPjMd7vRj8HKyxUe\",\"netType\":\"main_net\",\"transaction\":{\"signature\":[\"e885f6bba7832989a9ab9bad653919705b874c7f061e0c62fff2766e02b83126e2218e81ea8749f186110791b251dc6b3559845152d700a0565614cc648852f900\",\"9fd630a6c150dbc201c1474a9f5267bf264dc05c02edb64af5c27de658c4e4431c8893d82e2b98e4c81fbc274b61eddfd0579f2c450844eb17f5715bc7820d1600\"],\"raw_data\":{\"contract\":[{\"parameter\":{\"value\":\"0A1541844C5AEC5EFC5C533AF5E7ACAD9393CB2B41CD3C121541FE0F564CFC6B04E53EAFF09B8C1B6C1A5F8779C618904E\",\"type_url\":\"type.googleapis.com/protocol.TransferContract\"},\"type\":\"TransferContract\",\"Permission_id\":2}],\"ref_block_bytes\":\"6de0\",\"ref_block_hash\":\"8306900920a4a871\",\"expiration\":1577414772000,\"timestamp\":1577328372971}}}\n";
        JSONObject transaction = JSONObject.parseObject(oldTransaction);
        response = api.multiTransaction(transaction);
        JSONObject jsonObject = api.parseResponseContent(response);
        api.printJsonContent(api.parseResponseContent(response));
        Assert.assertEquals(jsonObject.getString("message"),"Sign old transaction");
        Assert.assertEquals(jsonObject.getString("code"),"20305");
    }

}
