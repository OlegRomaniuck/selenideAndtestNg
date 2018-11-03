package http;

import com.jayway.jsonpath.Configuration;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import logic.TestBase;
import org.testng.Assert;

import java.util.List;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

/**
 * Created by Tester3
 */
public class RestAssureBackend {

    public Map<String, String> cookies;
    public String token;

    final static String LOGIN = "";
    final static String PASSWORD = "";

    public static String referURL = "https://develop.com/manager/";
    public static final String BASEURL = TestBase.Base_URL;

    static {
        if (BASEURL.equals("https://g2.com/manager/")) {
            referURL = "https://g.com";
        }
    }


    public String getTokenBeforeLogin() {
        Response response = given()
                .header("Referer", BASEURL + "login")
                .header("Origin", referURL).
                        when().
                        get(BASEURL + "api3/user/simple_login/");
        String body = response.getBody().asString();
        cookies = response.getCookies();
        JsonPath jsonPath = new JsonPath(body);
        token = jsonPath.getString("token");
        return token;
    }

    public boolean signInService(String token) {
        Response response = given()
                .header("Referer", BASEURL + "login")
                .header("Origin", referURL)
                .cookies(cookies)
                .formParam("login", LOGIN).formParam("password", PASSWORD).formParam("csrfmiddlewaretoken", token).
                        when().
                        post(BASEURL + "api3/user/simple_login/");
        cookies = response.getCookies();
        String body = response.getBody().asString();
        System.out.println(body);
        JsonPath jsonPath = new JsonPath(body);
        boolean successSignIn = jsonPath.getBoolean("success");
        return successSignIn;
    }

    public void testGetListSimServersStatus200() {
        Response response = given().
                cookies(cookies).
                when().
                get(BASEURL + "api3/bank/get/");
        int statusCode = response.statusCode();
        Assert.assertEquals(statusCode, 200);
    }

    public void writeCDRtoaccount(String json) {
        Response responseCDR =
                given().
                        header("Referer", BASEURL).
                        cookies(cookies).
                        param("data", json).
                        when().
                        get(BASEURL + "/api3/cdr/create/");
        System.out.println("RESPONSE CDR " + responseCDR.getBody().asString());
    }

    public void deleteALLcdr() {
        Response resp = given().
                header("Referer", BASEURL).
                cookies(cookies).
                param("filters", "{\"id__gt\":0}").
                when().
                get(BASEURL + "api3/cdr/delete/");
        System.out.println("RESPONSE  DELET CDR " + resp.getBody().asString());
    }

    public String getLocationID(String name) {
        Response resp = given().
                header("Referer", BASEURL).
                cookies(cookies).
                param("filters", "{\"name\":\"" + name + "\"}").
                when().
                get(BASEURL + "/api3/location/get/");

        System.out.println("Location response " + resp.getBody().asString());
        String body = resp.getBody().asString();
        JsonPath jsonPath = new JsonPath(body);
        String id = jsonPath.getString("items.id").replace("[", "").replace("]", "");
        return id;
    }

    public void deleteAllFastStat() {
        Response resp = given().
                header("Referer", BASEURL).
                cookies(cookies).
                param("filters", "{\"id__gt\":0}").
                when().
                get(BASEURL + "/api3/location_gate_tariff_carrier_fast_stat/delete/");
        System.out.println("RESPONSE  DELET FAST STAT " + resp.getBody().asString());
    }


    public String getGateId(String getName) {
        Response resp = given().
                header("Referer", BASEURL).
                cookies(cookies).
                param("filters", "{\"name\":\"" + getName + "\"}").
                when().
                get(BASEURL + "/api3/gate/get/");
        System.out.println("RESPONSE  GATE" + resp.getBody().asString());
        String body = resp.getBody().asString();
        JsonPath jsonPath = new JsonPath(body);
        String id = jsonPath.getString("items.id").replace("[", "").replace("]", "");
        return id;

    }

    public String getTariffID(String tariffName) {
        Response resp = given().
                header("Referer", BASEURL).
                cookies(cookies).
                param("filters", "{\"name\":\"" + tariffName + "\"}").
                when().
                get(BASEURL + "/api3/tarif/get/");
        System.out.println("RESPONSE  Tariff" + resp.getBody().asString());
        String body = resp.getBody().asString();
        JsonPath jsonPath = new JsonPath(body);
        String id = jsonPath.getString("items.id").replace("[", "").replace("]", "");
        return id;
    }

    public String getCarrierID(String carrierName) {
        Response resp = given().
                header("Referer", BASEURL).
                cookies(cookies).
                param("filters", "{\"name\":\"" + carrierName + "\"}").
                when().
                get(BASEURL + "/api3/carrier/get/");
        System.out.println("RESPONSE  CARRIER " + resp.getBody().asString());
        String body = resp.getBody().asString();
        JsonPath jsonPath = new JsonPath(body);
        String id = jsonPath.getString("items.id").replace("[", "").replace("]", "");
        return id;
    }

    public void sendFastStat(String jsonFastStat) {
        System.out.println(jsonFastStat);
        Response responseCDR =
                given().
                        header("Referer", BASEURL).
                        cookies(cookies).
                        param("data", jsonFastStat).
                        when().
                        get(BASEURL + "api3/location_gate_tariff_carrier_fast_stat/create/");
        System.out.println("RESPONSE CDR " + responseCDR.getBody().asString());
    }


    public String getChannelsById(String gate_id) {
        Response resp = given().
                header("Referer", BASEURL).
                cookies(cookies).
                param("filters", "{\"gate_id\":\"" + gate_id + "\"}").
                when().
                get(BASEURL + "/api3/channel/get/");
        System.out.println("RESPONSE  GATE" + resp.getBody().asString());
        String body = resp.getBody().asString();
        return body;

    }

    public String getIdChannelByPositionAndJsonPath(String channelsById, String positions) {

        List<Integer> id = com.jayway.jsonpath.JsonPath.read(channelsById, "$.items[?(@.position==" + positions + ")].id");
        if (id.size() > 2 || id.size() == 0) {
            System.out.println("ERROR WE HAVE SEVERAL POSTION or NULL");
            throw new AssertionError("NOT FIND IN JSON YOUR POSTION : " + positions);
        }
        return String.valueOf(id.get(0));
    }

    public String updateChannelSettings(String numb, String d_calls, String time) {

        String datas = "{\"d_calls\":\"" + d_calls + "\",\"d_duration\":\"" + time + "\"}";
        String id = "{\"id\":\"" + numb + "\"}";

        Response responseCDR =
                given().
                        header("Referer", BASEURL).
                        cookies(cookies).
                        param("filters", id).
                        param("data", datas).
                        when().
                        get(BASEURL + "api3/channel/update/");

        return responseCDR.getBody().asString();

    }

    public String[] getDataFromJsonChannel(String id,String ... param) {
        Response resp = given().
                header("Referer", BASEURL).
                cookies(cookies).
                param("filters", "{\"id\":\"" + id + "\"}").
                when().
                get(BASEURL + "/api3/channel/get/");
        System.out.println("RESPONSE  GATE" + resp.getBody().asString());
        String rs=resp.getBody().asString();

        String [] accum = new String[param.length];
        for (int i=0;i<param.length;i++){
            Object ob= com.jayway.jsonpath.JsonPath.read(rs, param[i]);
            accum[i]=String.valueOf(ob);
        }
          return accum;
    }
}
