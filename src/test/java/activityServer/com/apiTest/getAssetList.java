package activityServer.com.apiTest;

import activityServer.com.utils.api;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import org.apache.http.HttpResponse;
import org.junit.Assert;
import org.testng.annotations.Test;

/**
 * Created by wangzihe on 2019/11/12.
 */

public class getAssetList {
    private HttpResponse response;
    private JSONObject responseContent;
    private JsonObject addressJson = new JsonObject();

    @Test(enabled = true, description = "Api /api/wallet/assetlist test")
    public void test001Assetlist() throws Exception {
        addressJson.addProperty("address",api.testAddressBase64);
        response = api.getAssetList(addressJson);

        JSONObject assetInformation = api.parseResponseContent(response).getJSONObject("data");
        api.printJsonContent(assetInformation);

        Assert.assertTrue(assetInformation.getFloat("totalTRX") > 0);
        Assert.assertTrue(assetInformation.getJSONObject("price").getDouble("priceUSD") > 0);
        Assert.assertTrue(assetInformation.getJSONObject("price").getDouble("priceCny") > 0);

        JSONArray tokenArray = assetInformation.getJSONArray("token");
        Assert.assertTrue(tokenArray.size() >= 2);
        api.printJsonArray(tokenArray);
    }


    @Test(enabled = true, description = "Api /api/wallet/assetlist exception test")
    public void test002AssetlistException() throws Exception {
        //Base58 decode address can't get right information
        addressJson.addProperty("address",api.testAddressBase58);
        response = api.getAssetList(addressJson);

        JSONObject assetInformation = api.parseResponseContent(response).getJSONObject("data");
        api.printJsonContent(assetInformation);

        Assert.assertTrue(assetInformation.getFloat("totalTRX") == 0);
        Assert.assertTrue(assetInformation.getJSONObject("price").getDouble("priceUSD") > 0);
        Assert.assertTrue(assetInformation.getJSONObject("price").getDouble("priceCny") > 0);

        JSONArray tokenArray = assetInformation.getJSONArray("token");
//        Assert.assertTrue(tokenArray.size() == 1);
        api.printJsonArray(tokenArray);
    }

}
