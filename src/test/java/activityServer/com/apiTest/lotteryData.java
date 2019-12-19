package activityServer.com.apiTest;

import activityServer.com.utils.api;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;
import org.apache.http.HttpResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

public class lotteryData {
  private HttpResponse response;

  @Test(enabled = true, description = "Api GET /api/wallet/lottery/default_data test")
  public void test001LotteryData() throws Exception {
    response = api.lotteryData();
    JSONObject lotteryDataInfo = api.parseResponseContent(response);
    api.printJsonContent(lotteryDataInfo);

    JSONObject data = lotteryDataInfo.getJSONObject("data");
    if (!data.isEmpty()){
      Long startTime = data.getLong("startTime");
      Long endTime = data.getLong("endTime");
      int activeStatus = data.getInteger("activeStatus");
      if (System.currentTimeMillis()>endTime*1000){
        Assert.assertEquals(activeStatus,2);
      }else if (System.currentTimeMillis()< endTime*1000 && System.currentTimeMillis() > startTime*1000){
        Assert.assertEquals(activeStatus,1);
      }else if (System.currentTimeMillis() < startTime*1000){
        Assert.assertEquals(activeStatus,0);
      }
    }
  }
}
