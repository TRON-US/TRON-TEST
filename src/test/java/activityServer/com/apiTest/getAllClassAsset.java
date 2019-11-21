package activityServer.com.apiTest;

import activityServer.com.utils.api;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.testng.annotations.Test;

/**
 * Created by wangzihe on 2019/11/12.
 */

public class getAllClassAsset {
    private HttpResponse response;
    private JSONObject responseContent;
    private JSONObject addressJson = new JSONObject();

    @Test(enabled = true, description = "Api /api/wallet/class/allasset test")
    public void test001AllClassAsset() throws Exception {
        addressJson.put("address",api.testAddressBase64);
        response = api.getAllClassAsset(addressJson);

        JSONObject assetInformation = api.parseResponseContent(response);
        api.printJsonContent(assetInformation);

        JSONArray tokenArray = assetInformation.getJSONArray("data");
        //Assert.assertTrue(tokenArray.size() >= 2);
        api.printJsonArray(tokenArray);
    }
}
