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

    @Test(enabled = true, description = "Api multiTrxReword test")
    public void multiTrxRecord0() throws Exception {
        param.put("address","TRqgwhHbfscXq3Ym3FJSFwxprpto1S4nSW");
        param.put("start","0");
        param.put("limit","10");
        param.put("state","0");
        param.put("netType","main_net");
        response = api.multiTrxReword(param);
        Assert.assertTrue(api.verificationResult(response));
        responseContent = api.parseResponseContent(response);
        api.printJsonContent(responseContent);
        Assert.assertTrue(responseContent.size() >= 3);
        Assert.assertTrue(responseContent.getString("message").equals("OK"));

        JSONArray array = responseContent.getJSONObject("data").getJSONArray("data");
        for (Object signatureProgress:array) {
            JSONObject info = (JSONObject) signatureProgress;
            Assert.assertEquals(info.getString("state"),"0");
            Assert.assertTrue(!info.getJSONArray("signatureProgress").isEmpty());
            Assert.assertTrue(!info.getString("originatorAddress").isEmpty());
            Assert.assertTrue(!info.getString("currentWeight").isEmpty());
            Assert.assertTrue(!info.getString("contractType").isEmpty());
            Assert.assertTrue(!info.getString("expireTime").isEmpty());
            Assert.assertTrue(!info.getString("threshold").isEmpty());
            Assert.assertTrue(!info.getString("isSign").isEmpty());
            Assert.assertTrue(!info.getString("state").isEmpty());
            Assert.assertTrue(!info.getString("hash").isEmpty());
        }
    }

    @Test(enabled = true, description = "Api multiTrxReword test success")
    public void multiTrxRecord1() throws Exception {
        param.put("address","TRqgwhHbfscXq3Ym3FJSFwxprpto1S4nSW");
        param.put("start","0");
        param.put("limit","10");
        param.put("state","1");
        param.put("netType","main_net");
        response = api.multiTrxReword(param);
        Assert.assertTrue(api.verificationResult(response));
        responseContent = api.parseResponseContent(response);
        api.printJsonContent(responseContent);
        Assert.assertTrue(responseContent.size() >= 3);
        Assert.assertTrue(responseContent.getString("message").equals("OK"));

        JSONArray array = responseContent.getJSONObject("data").getJSONArray("data");
        for (Object signatureProgress:array) {
            JSONObject info = (JSONObject) signatureProgress;
            Assert.assertEquals(info.getString("state"),"1");
            Assert.assertTrue(!info.getJSONArray("signatureProgress").isEmpty());
            Assert.assertTrue(!info.getString("originatorAddress").isEmpty());
            Assert.assertTrue(!info.getString("currentWeight").isEmpty());
            Assert.assertTrue(!info.getString("contractType").isEmpty());
            Assert.assertTrue(!info.getString("expireTime").isEmpty());
            Assert.assertTrue(!info.getString("threshold").isEmpty());
            Assert.assertTrue(!info.getString("isSign").isEmpty());
            Assert.assertTrue(!info.getString("state").isEmpty());
            Assert.assertTrue(!info.getString("hash").isEmpty());
        }
    }

    @Test(enabled = true, description = "Api multiTrxReword test fail")
    public void multiTrxRecord2() throws Exception {
        param.put("address","TRqgwhHbfscXq3Ym3FJSFwxprpto1S4nSW");
        param.put("start","0");
        param.put("limit","10");
        param.put("state","2");
        param.put("netType","main_net");
        response = api.multiTrxReword(param);
        Assert.assertTrue(api.verificationResult(response));
        responseContent = api.parseResponseContent(response);
        api.printJsonContent(responseContent);
        Assert.assertTrue(responseContent.size() >= 3);
        Assert.assertTrue(responseContent.getString("message").equals("OK"));

        JSONArray array = responseContent.getJSONObject("data").getJSONArray("data");
        for (Object signatureProgress:array) {
            JSONObject info = (JSONObject) signatureProgress;
            Assert.assertEquals(info.getString("state"),"2");
            Assert.assertTrue(!info.getJSONArray("signatureProgress").isEmpty());
            Assert.assertTrue(!info.getString("originatorAddress").isEmpty());
            Assert.assertTrue(!info.getString("currentWeight").isEmpty());
            Assert.assertTrue(!info.getString("contractType").isEmpty());
            Assert.assertTrue(!info.getString("expireTime").isEmpty());
            Assert.assertTrue(!info.getString("threshold").isEmpty());
            Assert.assertTrue(!info.getString("isSign").isEmpty());
            Assert.assertTrue(!info.getString("state").isEmpty());
            Assert.assertTrue(!info.getString("hash").isEmpty());
        }
    }


}
