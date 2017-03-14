/**
 * Created by myasnikov on 14.03.2017.
 */
public class ContinuedFraction {
    private static String fraction = "";
    public static void fraction() {
        fraction = (Utilits.takeFraction().replaceAll("[\\s]{2,}", " "));
        calculate(new String(new StringBuffer(fraction)));
    }
    private static void calculate(String toCalculate) {
        try {
            int ret = Integer.parseInt(toCalculate);
            System.out.println(ret);
        } catch (NumberFormatException e) {
            StringBuilder stringBuilder = new StringBuilder();
            int startReplace = 0;
            int endReplace = toCalculate.length();
            for (int i = 0; i < toCalculate.length(); i++) {
                if (toCalculate.charAt(i) == '(') {
                    startReplace++;
                }
        //        startReplace++;
                if (toCalculate.charAt(i) == ')') {
                    endReplace = i;
                    break;
                }
            }
            for (int i = startReplace; i < endReplace; i++) { // возможны проблемы индексов
                stringBuilder.append(toCalculate.charAt(i));
            }
            StringBuilder stringBuilder3 = new StringBuilder();
            for (int i = 0; i < startReplace; i++) {
                stringBuilder3.append(toCalculate.charAt(i));
            }
            stringBuilder3.append(Integer.parseInt(String.valueOf(expression(stringBuilder.toString()))));
            for (int i = endReplace; i < toCalculate.length(); i++) {
                stringBuilder3.append(toCalculate.charAt(i));
            }
            System.out.println(stringBuilder3.toString());
            calculate(stringBuilder3.toString());
        }
    }
    private static int expression(String exp) {
        try {
            return Integer.parseInt(exp);
        } catch (NumberFormatException e) {
            StringBuilder stringBuilder1 = new StringBuilder();
            StringBuilder stringBuilder2 = new StringBuilder();
            int fin = 0;
            int startReplace = 0;
            int endReplace = exp.length();
            for (int i = 0; i < exp.length(); i++) {
                if (exp.charAt(i) == '*' || exp.charAt(i) == '/') {
                    for (int j = i - 1;; j++) { // TODO: 14.03.2017 проверить 
                        if (!Character.isDigit(exp.charAt(j))) {
                            startReplace = j;
                            break;
                        }
                        stringBuilder1.append(exp.charAt(j));
                    }
                    for (int k = i + 1; k < exp.length(); k++) {
                        if (!Character.isDigit(exp.charAt(k))) {
                            endReplace = k;
                            break;
                        }
                        stringBuilder2.append(exp.charAt(k));
                    }
                    if (exp.charAt(i) == '*') {
                        fin = multiplication(Integer.parseInt(stringBuilder1.toString()),
                                Integer.parseInt(stringBuilder2.toString()));
                    }
                }
            }
            StringBuilder stringBuilder3 = new StringBuilder();
            for (int i = 0; i < startReplace; i++) {
                stringBuilder3.append(exp.charAt(i));
            }
            stringBuilder3.append(Integer.parseInt(String.valueOf(fin)));
            for (int i = endReplace; i < exp.length(); i++) {
                stringBuilder3.append(exp.charAt(i));
            }
            return expression(stringBuilder3.toString());
        }
    }

    private static int multiplication(final int mult1, final int mult2) {
        return mult1 * mult2;
    }
}
