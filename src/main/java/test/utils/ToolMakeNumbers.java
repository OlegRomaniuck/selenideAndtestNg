package test.utils;

import java.util.Random;

/**
 * Created by Tester3
 */
public class ToolMakeNumbers {

    public static int getRandomFromOneToNumber(int number) {
        return new Random().nextInt(number + 1);
    }
}
