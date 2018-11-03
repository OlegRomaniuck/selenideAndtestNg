package http.pogoclass;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Tester3
 */
public class GenerationNumber {

    private String total;

    private ItemsCallReport[] data;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        int huge = data.length;
        this.total = Integer.toString(huge);
    }

    public ItemsCallReport[] getItems() {
        return data;
    }

    public void setItems(ItemsCallReport[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ClassPojo [total = " + total + ", items = " + data + "]";
    }


    public String jsonCreator(GenerationNumber vItem) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        String json = gson.toJson(getItems());
        String newJson = json.replaceAll("\n", "");

        System.out.println(newJson);
        return json;
    }

    public String jsonCreator(ItemsCallReport vItem) {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        String json = gson.toJson(vItem);
        String newJson = json.replaceAll("\n", "");

        System.out.println(newJson);
        return newJson;
    }
}
