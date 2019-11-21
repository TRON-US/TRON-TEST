package activityServer.com.apiTest;

import activityServer.com.utils.api;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import org.apache.http.HttpResponse;
import org.junit.Assert;
import org.testng.annotations.Test;

/**
 * Created by wangzihe on 2019/11/12.
 */

public class trxTransferRecord {
    private HttpResponse response;
    private JSONObject responseContent;
    private HashMap<String,String> param = new HashMap<>();

    @Test(enabled = true, description = "Trx transfer record")
    public void trxTransferRecord() throws Exception {
        param.put("address","TKpJUP4CCymphdug1XmGzDGDmGXZjLyf29");
        param.put("from","TKpJUP4CCymphdug1XmGzDGDmGXZjLyf29");
        param.put("limit","-1");



        response = api.trxTransferRecord(param);
        //Assert.assertTrue(api.verificationResult(response));
        responseContent = api.parseResponseContent(response);
        api.printJsonContent(responseContent);
        JSONArray transferArray = responseContent.getJSONArray("data");
        System.out.println(transferArray.size());
        //最大limit数值为50
        Assert.assertTrue(transferArray.size() == 50);
        Assert.assertTrue(responseContent.size() == 3);
        Assert.assertTrue(responseContent.containsKey("rangeTotal"));

    }
}
