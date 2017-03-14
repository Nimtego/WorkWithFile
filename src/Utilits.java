import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;

/**
 * Created by myasnikov on 14.03.2017.
 */
public class Utilits {
    public static String takeFraction() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder fraction = new StringBuilder();
        String tmpString = "";
        boolean choice = true;
        while (choice) {
            try {
                System.out.println("Enter fraction");
                tmpString = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
                choice = !(checkFraction(tmpString));
        }
        return tmpString;

    }
    private static boolean checkFraction(String fraction) {
        int bracketOpen = 0;
        int bracketClose = 0;
        if (fraction.matches("^[a-z]+")) {
            return false;
        }//"^\\D*$"
        for (int i = 0; i < fraction.length(); i++) {
            if (fraction.charAt(i) == '(') {
                bracketOpen++;
            }
            if (fraction.charAt(i) == ')') {
                bracketClose++;
            }
/*            if (fraction.charAt(i) == ')' && !(Character.toString(fraction.charAt(i)).matches("^-?\\d+$")) ||
                    fraction.charAt(i) == '(' && Character.toString(fraction.charAt(i)).matches("^-?\\d+$")) {
                return false;
            }*/
        }
        return bracketClose == bracketOpen;
    }
}
