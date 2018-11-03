package http;

import org.cloudinary.json.JSONObject;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.*;
import test.utils.JsonSchemaAndStatusValidator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Tester3
 */

@WebSocket
public class ClientSocket {

    private Session session;
    List<JSONObject> js_ob_list = new ArrayList<>();
    static CountDownLatch latch = null;
    static File pathfile = null;
    static String pathToData = null;
    static String value = null;
    public boolean eventDoing;

    ClientSocket(int time) {
        latch = new CountDownLatch(time);
    }


    @OnWebSocketMessage
    public void onMessage(String message) throws IOException {
        System.out.println("Message received from server: " + message);

        eventDoing = new JsonSchemaAndStatusValidator().validStatusAndJson(pathfile, message, pathToData, value);

        latch.countDown();
        if (eventDoing) {
            long lost = latch.getCount();
            for (long i = 0; i <= lost; i++) {
                latch.countDown();
            }
        }
    }

    @OnWebSocketConnect
    public void onConnect(Session session) {
        System.out.println("Connected to server");
        this.session = session;
    }

    @OnWebSocketClose
    public void onClose(int statusCode, String reason) {
        System.out.println("Close connection reason: " + reason + " and status code: " + statusCode);
    }

    @OnWebSocketError
    public void onError(Throwable cause) {
        System.out.println("EROR  WSS : " + cause);
    }

    public void sendMessage(String str) {
        try {
            System.out.println("send mess  " + str);
            session.getRemote().sendString(str);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public CountDownLatch getLatch() {
        return latch;
    }

    public List<JSONObject> getJsonObject() {
        return js_ob_list;
    }

    public void setParam(File json, String path, String param) {
        pathfile = json;
        pathToData = path;
        value = param;
    }

    public boolean isEventShouldDo() {
        return eventDoing;
    }

}
