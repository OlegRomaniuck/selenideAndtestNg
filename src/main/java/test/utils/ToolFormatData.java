package test.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by Tester3
 */
public class ToolFormatData {

    public static String getActualData() {
        Date now = new Date();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateActual = formatter.format(now);
        return dateActual;
    }

    public static String getYesterdayData() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return dateFormat.format(cal.getTime());
    }

    public static String dataFormatterForFuture(String s) {
        if (s == null) {
            return null;
        }

        String s1 = s.substring(0, 19);
        String timeToSend = "";
        try {
            DateFormat formatter;
            formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            // you can change format of date
            Date date = formatter.parse(s1);

            long ts1 = date.getTime();

            long ts2 = ts1 + 60000;

            Timestamp timestamp_f = new Timestamp(ts2);
            timeToSend = timestamp_f.toString().substring(11, 19);


        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeToSend;
    }

    public static String getServerTime() {
        //  return executeJavaScript("return GoAntiFraud.component.User.serverTime.toLocaleTimeString()");
        return $(byId("server_time_value")).getText();
    }

    public static String getPriciseTime() {

        return dataFormatterForFuture(getServerTime());
    }
}
