package common;

public class ValidationHelper {
    public static boolean isNumberBetween(String str, int min, int max) {
        try {
            int number = Integer.parseInt(str);
            return number >= min && number <= max;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static String stringIs15Characters(String str) {
        int stringLength = str.length();
        if (stringLength > 15) {
            return "";
        } else {
            int stringDif = 15 - stringLength;
            int stringEmpty1length = Math.floorDiv(stringDif, 2);
            int stringEmpty2length = (int) Math.ceil((double) stringDif / 2);//Ceil works with Double
            String stringEmpty1 = new String(new char[stringEmpty1length]).replace('\0', ' ');
            String stringEmpty2 = new String(new char[stringEmpty2length]).replace('\0', ' ');
            return stringEmpty1 + str + stringEmpty2;
        }
    }

    public static boolean stringIsCommaValid(String str) {
        boolean isValid = str.matches("\\d+(,\\d+)*");
        return isValid;
    }
}
