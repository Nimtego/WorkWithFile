/**
 * Created by myasnikov on 14.03.2017.
 */
public class ContinuedFraction {
    private static String fraction = "";
    public static void fraction() {
        fraction = (Utilits.takeFraction().replaceAll(" ", ""));
        long timeout= System.currentTimeMillis();
        calculate(fraction);
        long time = System.currentTimeMillis() - timeout;
        System.out.println("TIME :" +(float)time / 1000);
    }
    private static void calculate(String toCalculate) {
        try {
            int ret = Integer.parseInt(toCalculate);
            System.out.println("\nИТОГО : " +ret);
        } catch (NumberFormatException e) {
            StringBuilder stringBuilder = new StringBuilder();
            int startReplace = 0;
            int endReplace = toCalculate.length();
            for (int i = 0; i < toCalculate.length(); i++) {
                if (toCalculate.charAt(i) == '(') {
                    startReplace = i + 1;
                }
                if (toCalculate.charAt(i) == ')') {
                    endReplace = i;
                    break;
                }
            }
            for (int i = startReplace; i < endReplace; i++) { // возможны проблемы индексов
                stringBuilder.append(toCalculate.charAt(i));
            }
            StringBuilder stringBuilder3 = new StringBuilder();
            for (int i = 0; i < startReplace - 1; i++) {
                stringBuilder3.append(toCalculate.charAt(i));
            }
            System.out.println("Выражение уходит в рекурсию метод calculate = (" +stringBuilder.toString() +")");
            stringBuilder3.append(Integer.parseInt(String.valueOf(expression(stringBuilder.toString()))));
            for (int i = endReplace + 1; i < toCalculate.length(); i++) {
                stringBuilder3.append(toCalculate.charAt(i));
            }
            System.out.println("\n[ Ход расчёта = " +stringBuilder3.toString() +" ]" +"\n");
            calculate(stringBuilder3.toString());
        }
    }
    private static int expression(String exp) {
        try {
            int ret = Integer.parseInt(exp);
            return ret;
        } catch (NumberFormatException e) {
            StringBuilder stringBuilder1 = new StringBuilder();
            StringBuilder stringBuilder2 = new StringBuilder();
            int fin;
            int startReplace = 0;
            int endReplace = exp.length();
            int flagOfOperator = 0;
            for (int i = 0; i < exp.length(); i++) {
                if (exp.charAt(i) == '*' || exp.charAt(i) == '/' && i != 0) {
                    flagOfOperator = i;
                    for (int j = flagOfOperator - 1; j > 0;) {
                        if (Character.isDigit(exp.charAt(j)) || (exp.charAt(j) == '-' && !Character.isDigit(exp.charAt(j - 1)))) {
                            --j;
                            continue;
                        }
                        startReplace = j + 1;
                        break;
                    }
                    for (int k = flagOfOperator + 1; k < exp.length();) {
                        if (exp.charAt(flagOfOperator + 1) == '-' && k == flagOfOperator + 1) {
                            ++k;
                            continue;
                        }
                        if (Character.isDigit(exp.charAt(k))) {
                            ++k;
                            continue;
                        }
                        endReplace = k;
                        break;
                    }
                    break;
                }
            }
            if (flagOfOperator == 0) {
                for (int i = 0; i < exp.length(); i++) {
                    if (exp.charAt(i) == '+' || exp.charAt(i) == '-' && i != 0) {
                        flagOfOperator = i;
                        for (int j = flagOfOperator - 1; j > 0;) {
                            if (Character.isDigit(exp.charAt(j)) || exp.charAt(j) == '-') {
                                --j;
                                continue;
                            }
                            startReplace = j;
                            break;
                        }
                        for (int k = flagOfOperator + 1; k < exp.length();) {
                            if (Character.isDigit(exp.charAt(k)) || (exp.charAt(k) == '-' && k == flagOfOperator + 1)) {
                                ++k;
                                continue;
                            }
                            endReplace = k;
                            break;
                        }
                        break;
                    }
                }
            }
            for (int i = startReplace; i < flagOfOperator; i++) {
                stringBuilder1.append(exp.charAt(i));
            }
            for (int i = flagOfOperator + 1; i < endReplace; i++) {
                stringBuilder2.append(exp.charAt(i));
            }
            fin = operator(exp.charAt(flagOfOperator), Integer.parseInt(stringBuilder1.toString()),
                        Integer.parseInt(stringBuilder2.toString()));
            StringBuilder stringBuilder3 = new StringBuilder();
            for (int i = 0; i < startReplace; i++) {
                stringBuilder3.append(exp.charAt(i));
            }
            stringBuilder3.append(Integer.parseInt(String.valueOf(fin)));
            for (int i = endReplace; i < exp.length(); i++) {
                stringBuilder3.append(exp.charAt(i));
            }
            System.out.println("             Уходит в рекурсию метод expression = " +stringBuilder3);
            return expression(stringBuilder3.toString());
        }
    }
    private static int operator(final char operation, final int first, final int second) {
        int result;
        switch (operation) {
            case '*':  result = multiplication(first, second);
                break;
            case '/':  result = division(first, second);
                break;
            case '+':  result = addition(first, second);
                break;
            case '-':  result = subtraction(first, second);
                break;
            default:   result = 0;
                break;
        }
        return result;
    }

    private static int subtraction(int first, int second) {
        return first - second;
    }

    private static int addition(int first, int second) {
        return first + second;
    }

    private static int division(int first, int second) {
        return first / second;
    }

    private static int multiplication(final int first, final int second) {
        return first * second;
    }
}
