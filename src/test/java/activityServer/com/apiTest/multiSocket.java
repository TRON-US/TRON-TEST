package activityServer.com.apiTest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.apache.http.HttpResponse;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import activityServer.com.utils.api;

/**
 * Created by wangzihe on 2019/11/12.
 */

public class multiSocket {
    private HttpResponse response;
    private JSONObject responseContent;
    private HashMap<String,String> param = new HashMap<>();

    @Test(enabled = true, description = "Mutli socket test")
    public void multiTrxRecord() throws Exception {
        param.put("address","TBExF3mNvnhmEFgHW4TmYXXdhevRchnQyb");
        param.put("start","0");
        param.put("limit","40");
        param.put("state","0");
        param.put("netType","main_net");
        response = api.multiSocket(param);
        responseContent = api.parseResponseContent(response);
        api.printJsonContent(responseContent);
        Assert.assertTrue(responseContent.size() >= 3);
        Assert.assertTrue(responseContent.getString("message").equals("OK"));

        JSONArray array = responseContent.getJSONObject("data").getJSONArray("data");
        for (int i = 0; i < array.size();i++) {
            System.out.println(array.get(i));
            System.out.println("-----------------------");
        }

    }
}
