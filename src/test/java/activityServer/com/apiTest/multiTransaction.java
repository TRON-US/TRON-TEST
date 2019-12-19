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
    private String multiAddress = "4183671DDACCF655D3A6FA026DE0896E83D67720E3";
    private String multiKey = "dad5b1d416822eb02e79bb818c35411e58b88db85562bcc8e71cac2c1ffa441c";
    private String fullnodeHttpServer = "47.252.85.177:8090";
    private String base58MutiAddress = "TMx13rffk9sFto1LYv42wh9WmFYpYoKRcS";
    private JSONObject rawDataObject;
    private String signatureString;
    private String foundationAccountKey = "7400E3D0727F8A61041A8E8BF86599FE5597CE19DE451E59AED07D60967A5E25";
    private String foundationAccountAddress = "416C0214C9995C6F3A61AB23F0EB84B0CDE7FD9C7C";
    private String signString;

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
            rawDataObject = transactionArray.getJSONObject(0).getJSONObject("currentTransaction").getJSONObject("raw_data");
        }







    }

    @Test(enabled = true, description = "Api multi transaction when this transaction not create from tronlink,")
    public void test003getTransaction() throws Exception {

        String[] keyArray = new String[2];
        keyArray[0] = multiKey;
        //keyArray[1] = foundationAccountKey;
        String signString = api.getTransactionSignStringFromFullnode(fullnodeHttpServer,multiAddress,foundationAccountAddress,10L,3,foundationAccountKey);
        System.out.println(signString);
        JsonObject jsonObject = new JsonParser().parse(signString).getAsJsonObject();
        JsonObject rawdataObject = new JsonParser().parse(rawDataObject.toString()).getAsJsonObject();

        JsonObject transaction = new JsonObject();
        String base58MutiAddress = "TMx13rffk9sFto1LYv42wh9WmFYpYoKRcS";
        transaction.addProperty("address",base58MutiAddress);
        transaction.addProperty("netType","main_net");
        jsonObject.remove("raw_data_hex");
        jsonObject.remove("txID");
        jsonObject.remove("visible");
        jsonObject.remove("raw_data");
        jsonObject.add("raw_data",rawdataObject);
        transaction.add("transaction",jsonObject);
        System.out.println("transaction:" + transaction);
        response = api.multiTransaction(transaction);
        api.printJsonContent(api.parseResponseContent(response));


    }



}
