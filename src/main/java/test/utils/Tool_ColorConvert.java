package test.utils;

/**
 * Created by Tester3
 */
public class Tool_ColorConvert {

    private String text = "string text";
    private String temp1 = "0";

    public Tool_ColorConvert(String text) {
        this.text = text;
    }

    public String getColor() {
        for (int i = 5; i < 6; i++) {
            if ("1".equals(temp1) || "2".equals(temp1) || "3".equals(temp1) || "4".equals(temp1) || "5".equals(temp1) || "6".equals(temp1) || "7".equals(temp1) || "8".equals(temp1) || "9".equals(temp1) || "0".equals(temp1)) {
                temp1 = text.substring(i);
            } else {
                temp1 = ", ";
            }
        }

        String temp2 = "background-color: rgb(" + temp1;
        String temp3 = temp2.substring(0, temp2.length() - 4) + ");";

        return temp3;
    }

    public String getColorReal(){

        for (int i = 5; i < 6; i++) {
            if ("1".equals(temp1) || "2".equals(temp1) || "3".equals(temp1) || "4".equals(temp1) || "5".equals(temp1) || "6".equals(temp1) || "7".equals(temp1) || "8".equals(temp1) || "9".equals(temp1) || "0".equals(temp1)) {
                temp1 = text.substring(i);
            } else {
                temp1 = ", ";
            }
        }

        String temp2 = temp1;
        String temp3 = temp2.substring(0, temp2.length() -4) ;
        String []numberString = temp3.split("\\s*(,|\\s)\\s*");
        for (String st : numberString){
            System.out.println(st);
        }
        int r = Integer.parseInt(numberString[0]);
        int g =Integer.parseInt(numberString[1]);
        int b = Integer.parseInt(numberString[2]);
        return new ColorUtils().getColorNameFromRgb(r,g,b);

    }
}
