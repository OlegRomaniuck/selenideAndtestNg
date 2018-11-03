package http;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tester3
 */
public class HttpRequest {

    private final String USER_AGENT = "Mozilla/5.0";
    private List<String> cookies;

    public void sendPostWithParameters(String url, String message) {
        CookieHandler.setDefault(new CookieManager());
        try (CloseableHttpClient client = HttpClientBuilder.create().build()) {
            HttpPost post = new HttpPost(url);
            // add header
            post.setHeader("User-Agent", USER_AGENT);
            List<NameValuePair> urlParameters = new ArrayList<>();
            urlParameters.add(new BasicNameValuePair("data", message));
            post.setEntity(new UrlEncodedFormEntity(urlParameters));
            HttpResponse response = client.execute(post);
            System.out.println("Sending 'POST' request to URL : " + url);
            System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
