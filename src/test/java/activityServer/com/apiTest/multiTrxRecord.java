package activityServer.com.apiTest;

import com.alibaba.fastjson.JSONObject;

import org.apache.http.HttpResponse;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import com.alibaba.fastjson.JSONArray;
import activityServer.com.utils.api;

/**
 * Created by wangzihe on 2019/11/12.
 */

public class multiTrxRecord {
    private HttpResponse response;
    private JSONObject responseContent;
    private HashMap<String,String> param = new HashMap<>();

    @Test(enabled = true, description = "Api upgrade test")
    public void multiTrxRecord() throws Exception {
        param.put("address","TRqgwhHbfscXq3Ym3FJSFwxprpto1S4nSW");
        param.put("start","0");
        param.put("limit","39");
        param.put("state","1");
        param.put("netType","main_net");
        response = api.multiTrxReword(param);
        Assert.assertTrue(api.verificationResult(response));
        responseContent = api.parseResponseContent(response);
        api.printJsonContent(responseContent);
        Assert.assertTrue(responseContent.size() >= 3);
        Assert.assertTrue(responseContent.getString("message").equals("OK"));

        JSONArray array = responseContent.getJSONObject("data").getJSONArray("data");
        array.contains("signatureProgress");
        for (int i = 0; i < array.size();i++) {
            System.out.println(array.get(i));
            System.out.println("-----------------------");
        }

    }
}
