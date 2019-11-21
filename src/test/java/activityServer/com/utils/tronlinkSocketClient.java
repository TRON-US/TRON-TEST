package activityServer.com.utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
/**
 * Created by wangzihe on 2019/11/13.
 */

public class tronlinkSocketClient extends WebSocketClient {
    public tronlinkSocketClient(String url) throws URISyntaxException {
        super(new URI(url));
    }

    @Override
    public void onOpen(ServerHandshake shake) {
        System.out.println("握手...");
        for(Iterator<String> it = shake.iterateHttpFields(); it.hasNext();) {
            String key = it.next();
            System.out.println(key+":"+shake.getFieldValue(key));
        }
    }

    @Override
    public void onMessage(String paramString) {
        writeResponse(paramString);
        System.out.println("接收到消息："+paramString);
    }

    @Override
    public void onClose(int paramInt, String paramString, boolean paramBoolean) {
        System.out.println("关闭...");
    }

    @Override
    public void onError(Exception e) {
        System.out.println("异常"+e);

    }

    public void writeResponse(String responseMessage) {
        String socketMessage = "socketMessage.log";
        StringBuilder sb = new StringBuilder();
        sb.append(responseMessage);
        String res = sb.toString();
        try {
            Files.write((Paths.get(socketMessage)), res.getBytes("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getResonse(){
        String transaction = "";
        try{
            InputStream is = new FileInputStream("socketMessage.log");
            int iAvail = is.available();
            byte[] bytes = new byte[iAvail];
            is.read(bytes);
            transaction = new String(bytes);
            is.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return transaction;
    }

}
