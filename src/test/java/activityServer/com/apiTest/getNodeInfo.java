package activityServer.com.apiTest;

import activityServer.com.utils.api;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

public class getNodeInfo {
  private HttpResponse response;

  @Test(enabled = true, description = "Api POST /api/wallet/node_info")
  public void test001NodeInfo() throws Exception {
//    parameter.put("System", "Android");
//    parameter.put("DeviceID","xxxx2xxx");
    String parameter = "[{\"12sfa4\":1},{\"12sfa5\":1}]";
    response = api.getNodeInfo(parameter);

    JSONObject nodeInfo = api.parseResponseContent(response);
    api.printJsonContent(nodeInfo);

    JSONArray data = nodeInfo.getJSONArray("data");

    JSONObject mainNodeInfoJson = data.getJSONObject(0);
    JSONObject dappNodeInfoJson = data.getJSONObject(1);

    JSONArray fullNodeArray = mainNodeInfoJson.getJSONArray("fullNode");
    JSONArray solidityNodeArray = mainNodeInfoJson.getJSONArray("solidityNode");
    Assert.assertTrue(!fullNodeArray.isEmpty());
    Assert.assertTrue(!solidityNodeArray.isEmpty());
    System.out.println("fullNode : ");
    for (Object fullNode : fullNodeArray){
      System.out.println(fullNode.toString());
    }
    System.out.println("solidityNode : ");
    for (Object solidityNode : solidityNodeArray){
      System.out.println(solidityNode.toString());
    }
    String chainNameMain = mainNodeInfoJson.getString("chainName");
    String chainNameDapp = dappNodeInfoJson.getString("chainName");

    Assert.assertEquals(chainNameMain,"MainChain");
    Assert.assertTrue(mainNodeInfoJson.getInteger("isMainChain") == 1);
    Assert.assertEquals(chainNameDapp,"DAppChain");
    Assert.assertTrue(dappNodeInfoJson.getInteger("isMainChain") == 0);

  }

}
