package test.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import http.pogoclass.FastStatRequestObject;

/**
 * Created by Tester
 */
public class JsonCreator {

    public String createJSONfromFastStat(FastStatRequestObject obj) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        return gson.toJson(obj);
    }

}
