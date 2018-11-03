package test.utils;

import com.skype.Skype;
import com.skype.SkypeException;

import java.util.Arrays;

/**
 * Created by Tester3
 */
public class SkypeSender {

    public static final String[] SKYPE_NAMES_DEVELOPERS = {"testtest", "tester"};

    public static void sentMessageBySkype(String message, String[] sentTo) {
        try {
            for (String skypeName : sentTo) {
                Skype.chat(skypeName).send(message);
            }
            System.out.println("Results send by skype to developers: {" + Arrays.toString(SKYPE_NAMES_DEVELOPERS) + "}");
        } catch (SkypeException e) {
            e.printStackTrace();
        }
    }

}
