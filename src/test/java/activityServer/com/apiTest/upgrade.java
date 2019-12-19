package activityServer.com.apiTest;

import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.junit.Assert;
import org.testng.annotations.Test;
import activityServer.com.utils.api;

/**
 * Created by wangzihe on 2019/11/12.
 */

public class upgrade {
    private HttpResponse response;
    private JSONObject responseContent;
    private HashMap<String,String> param = new HashMap<>();

    @Test(enabled = true, description = "Api upgrade test")
    public void upgrade() throws Exception {
        param.put("packageName","com.tronlink.wallet");
        param.put("DownloadPlatform","googlePlayAisle");
        param.put("DeviceID","sdfsasdfsa");
        param.put("Lang","1");
        param.put("Version","3.2.0");
        param.put("System","Android");

        response = api.upgrade(param);
        responseContent = api.parseResponseContent(response);
        api.printJsonContent(responseContent);
        Assert.assertTrue(responseContent.size() >= 5);
        Assert.assertTrue(responseContent.containsKey("latest_version"));

        JSONObject data = responseContent.getJSONObject("data");

        Assert.assertTrue(!data.getString("internal_update_url").isEmpty());
        Assert.assertTrue(!data.getString("google_apk_url").isEmpty());
        Assert.assertTrue(!data.getString("version").isEmpty());
        Assert.assertTrue(!data.getString("url").isEmpty());

    }
}
