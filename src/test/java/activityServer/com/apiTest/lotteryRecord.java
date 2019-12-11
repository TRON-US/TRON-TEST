package activityServer.com.apiTest;

import activityServer.com.utils.api;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import org.apache.http.HttpResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

public class lotteryRecord {
  private HttpResponse response;
  private HashMap<String,String> parameter = new HashMap();

  @Test(enabled = true, description = "Api GET /api/wallet/lottery/record test")
  public void test001LotteryRecord() throws Exception {
//    parameter.put("address", api.testAddressBase64);
    response = api.lotteryRecord(parameter);

    JSONObject lotteryRecordInfo = api.parseResponseContent(response);
    api.printJsonContent(lotteryRecordInfo);

    JSONArray data = lotteryRecordInfo.getJSONObject("data").getJSONArray("data");
    JSONObject record1 = data.getJSONObject(0);
    Assert.assertTrue(!record1.getString("address").isEmpty());
    Assert.assertTrue(!record1.getString("integral").isEmpty());
    Assert.assertTrue(!record1.getString("resultType").isEmpty());
    Assert.assertTrue(!record1.getString("createTime").isEmpty());
    Assert.assertTrue(!record1.getString("result").isEmpty());

    JSONObject record20 = data.getJSONObject(19);


  }
}
