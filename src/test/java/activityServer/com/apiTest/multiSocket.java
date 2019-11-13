package activityServer.com.apiTest;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.java_websocket.WebSocketImpl;
import org.java_websocket.enums.CloseHandshakeType;
import org.java_websocket.enums.HandshakeState;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidHandshakeException;
import org.java_websocket.framing.Framedata;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ClientHandshakeBuilder;
import org.java_websocket.handshake.HandshakeBuilder;
import org.java_websocket.handshake.ServerHandshakeBuilder;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import java.net.URI;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.drafts.Draft;
import activityServer.com.utils.api;
import activityServer.com.utils.newTronlinkSocketClient;
import activityServer.com.utils.tronlinkSocketClient;
/**
 * Created by wangzihe on 2019/11/12.
 */

public class multiSocket {
    private HttpResponse response;
    private JSONObject responseContent;
    private HashMap<String,String> param = new HashMap<>();


    @Test(enabled = true, description = "Mutli socket test")
    public void multiSocket() throws Exception {
        String url = "wss://testlist.tronlink.org/api/wallet/multi/socket?address=TBExF3mNvnhmEFgHW4TmYXXdhevRchnQyb&state=1";
        //String url = "wss://testlist.tronlink.org/api/wallet/multi/socket/";

        URI uri = new URI(url);
/*
        tronlinkSocketClient mWs = new tronlinkSocketClient(url);

        mWs.connect();

        while (!mWs.getReadyState().equals(WebSocketClient.READYSTATE.OPEN, WebElement)) {
            WebSocketClient.class.getR

            System.out.println(mWs.getURI().toString());
            System.out.println("还没有打开");
            System.out.println(mWs.isOpen());
        }
        mWs.send("address=TBExF3mNvnhmEFgHW4TmYXXdhevRchnQyb&state=1");


*/
        param.put("Connection","Upgrade");
        param.put("Upgrade","websocket");
        param.put("Pragma","no-cache");
        param.put("Cache-Control","no-cache");
        param.put("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36");


        param.put("Sec-WebSocket-Version","13");
        param.put("Accept-Encoding","gzip, deflate, br");
        param.put("Accept-Language","zh-CN,zh;q=0.9");
        param.put( "Origin", "localhost" );
        param.put("Sec-WebSocket-Key","YJjfZ/N4xV7oCbYRrqhTZA==");

        newTronlinkSocketClient c = new newTronlinkSocketClient( new URI( url ),param); // more about drafts here: http://github.com/TooTallNate/Java-WebSocket/wiki/Drafts

        c.connect();
        //c.isOpen();
        System.out.println(c.isOpen());




        System.exit(1);

        param.put("address","TBExF3mNvnhmEFgHW4TmYXXdhevRchnQyb");
        param.put("start","0");
        param.put("limit","40");
        param.put("state","0");
        param.put("netType","main_net");


    }
}
