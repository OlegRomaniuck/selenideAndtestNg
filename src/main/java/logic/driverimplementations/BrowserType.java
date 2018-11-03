package logic.driverimplementations;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Tester3 on 01.07.2016.
 */
public enum BrowserType {

    FIREFOX("firefox"),
    CHROME("chrome"),
    SELENIDE("selenide");

    private String browserKey;
    private static Map<String, BrowserType> browserMap = new HashMap<String, BrowserType>();

    static {
        for (BrowserType bt : BrowserType.values()) {
            browserMap.put(bt.key(), bt);
        }
    }

    private BrowserType(String key) {
        browserKey = key;
    }

    private String key() {
        return this.browserKey;
    }

    public static BrowserType get(String key) {
        if (browserMap.containsKey(key)) {
            return browserMap.get(key);
        } else {
            return SELENIDE;
        }
    }
}
