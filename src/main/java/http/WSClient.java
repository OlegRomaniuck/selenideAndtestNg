package http;

import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.eclipse.jetty.websocket.client.ClientUpgradeRequest;
import org.eclipse.jetty.websocket.client.WebSocketClient;

import java.io.File;
import java.net.URI;

/**
 * Created by Tester3
 */


public class WSClient {

    public boolean event;

    public void client(String url, File json, String path, String value, int time) {
        SslContextFactory sslContextFactory = new SslContextFactory();
        sslContextFactory.setTrustAll(true);
        WebSocketClient client = new WebSocketClient(sslContextFactory);

        ClientSocket socket = new ClientSocket(time);
        try {
            socket.setParam(json, path, value);
            client.start();
            URI echoUri = new URI(url);
            ClientUpgradeRequest request = new ClientUpgradeRequest();
            client.connect(socket, echoUri, request);
            socket.getLatch().await();
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            try {
                event = socket.isEventShouldDo();
                client.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isEventPresentInLog() {
        return this.event;
    }
}


