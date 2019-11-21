package activityServer.com.apiTest;

import activityServer.com.utils.api;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Created by wangzihe on 2019/11/12.
 */

public class addAsset {
    private HttpResponse response;
    private JSONObject responseContent;
    private JSONObject tokenJson = new JSONObject();
    List<String> trc10tokenList = new ArrayList<>();
    List<String> trc20ContractAddressList = new ArrayList<>();

    @BeforeClass(enabled = true)
    public void removeAllTokenList() throws Exception{
        tokenJson.put("address",api.testAddressBase64);
        //只传输地址的时候，会返回该地址当前添加过的资产
        response = api.addAsset(tokenJson);
        JSONObject assetInformation = api.parseResponseContent(response);
        JSONArray tokenArray = assetInformation.getJSONArray("data");
        trc10tokenList = api.getTrc10TokenIdList(tokenArray);
        trc20ContractAddressList = api.getTrc20AddressList(tokenArray);
        tokenJson.put("token10Cancel",trc10tokenList);
        tokenJson.put("token20Cancel",trc20ContractAddressList);
        response = api.addAsset(tokenJson);
        assetInformation = api.parseResponseContent(response);
        tokenArray = assetInformation.getJSONArray("data");
        //Assert.assertTrue(tokenArray.size() == 1);

        //获取未添加的trc10和trc20列表
        response = api.getAllClassAsset(tokenJson);
        assetInformation = api.parseResponseContent(response);
        tokenArray = assetInformation.getJSONArray("data");
        //api.printJsonArray(tokenArray);
        trc10tokenList = api.getTrc10TokenIdList(tokenArray);
        trc20ContractAddressList = api.getTrc20AddressList(tokenArray);

    }

    @Test(enabled = true,description = "Test add all trc10 token to account.")
    public void test001AddAllTrc10TokenToAccount() throws Exception {
        tokenJson.clear();
        tokenJson.put("address",api.testAddressBase64);
        tokenJson.put("token10",trc10tokenList);
        response = api.addAsset(tokenJson);
        JSONObject assetInformation = api.parseResponseContent(response);
        JSONArray tokenArray = assetInformation.getJSONArray("data");
        System.out.println("trc10 token size are:" + tokenArray.size());
        Assert.assertTrue(tokenArray.size() >= 40);
    }

    @Test(enabled = true,description = "Test add all trc20 token to account.")
    public void test002AddAllTrc20ToAccount() throws Exception {
        tokenJson.clear();
        tokenJson.put("address",api.testAddressBase64);
        tokenJson.put("token20",trc20ContractAddressList);
        response = api.addAsset(tokenJson);
        JSONObject assetInformation = api.parseResponseContent(response);
        JSONArray tokenArray = assetInformation.getJSONArray("data");
        //api.printJsonArray(tokenArray);
        System.out.println("trc10 + trc 20 token size are:" + tokenArray.size());
        Assert.assertTrue(tokenArray.size() >= 40);


        tokenJson.clear();
        tokenJson.put("address",api.testAddressBase64);
        //只传输地址的时候，会返回该地址当前添加过的资产
        response = api.addAsset(tokenJson);
        assetInformation = api.parseResponseContent(response);
        tokenArray = assetInformation.getJSONArray("data");
        System.out.println("Now account have token size:" + tokenArray.size());
    }

    @Test(enabled = false,description = "Test cancel trc10 token to account.")
    public void test003CancelTrc10FromAccount() throws Exception {
        tokenJson.clear();
        tokenJson.put("address",api.testAddressBase64);
        tokenJson.put("token10Cancel",trc10tokenList);
        response = api.addAsset(tokenJson);
        JSONObject assetInformation = api.parseResponseContent(response);
        JSONArray tokenArray = assetInformation.getJSONArray("data");
        api.printJsonArray(tokenArray);
        Assert.assertTrue(tokenArray.size() >= 5);
    }


    @Test(enabled = false,description = "Test cancel trc20 token to account.")
    public void test004CancelTrc20FromAccount() throws Exception {
        tokenJson.clear();
        tokenJson.put("address",api.testAddressBase64);
        tokenJson.put("token20Cancel",trc20ContractAddressList);
        response = api.addAsset(tokenJson);
        JSONObject assetInformation = api.parseResponseContent(response);
        JSONArray tokenArray = assetInformation.getJSONArray("data");
        api.printJsonArray(tokenArray);
        Assert.assertTrue(tokenArray.size() >= 1);
    }








}
