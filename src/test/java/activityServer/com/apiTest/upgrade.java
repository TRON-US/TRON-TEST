package activityServer.com.apiTest;

import com.alibaba.fastjson.JSONObject;
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

    @Test(enabled = true)
    public void sendCoinToTestCount() throws Exception {
        response = api.upgrade();
        //Assert.assertTrue(api.verificationResult(response));
        responseContent = api.parseResponseContent(response);
        api.printJsonContent(responseContent);

    }
}
