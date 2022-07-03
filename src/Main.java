import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args)  {
        try {
            System.out.println(
                    "Введите выражение вида <операнд1> <оператор> <операнд2>, " +
                            "где операнды - целые арабские или римские числа от 1 до 10 включительно, " +
                            "а оператор - один из четырех символов: +, -, *, /");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String expression = bufferedReader.readLine();
            String result = calc(expression);
            System.out.println("Результат: " + result);
        } catch (IOException | CalcException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String calc(String input) throws CalcException {
        Digits digits = new Digits();
        String[] expArr = input.split(" ");
        if (expArr.length != 3) {
            throw new CalcException("Выражение должно быть вида <операнд1><пробел><оператор><пробел><операнд2>");
        }
        String operand1 = expArr[0];
        String operand2 = expArr[2];
        String operation = expArr[1];
        if (digits.isArabic(operand1) && digits.isArabic(operand2)) {
            return Integer.toString(digits.calc(Integer.parseInt(operand1), operation, Integer.parseInt(operand2)));
        } else if ((digits.isRoman(operand1)) && digits.isRoman(operand2)) {
            int opToArabic1 = Integer.parseInt(digits.romanToArabic(operand1));
            int opToArabic2 = Integer.parseInt(digits.romanToArabic(operand2));
            return digits.arabicToRoman(digits.calc(opToArabic1, operation, opToArabic2));
        } else throw new CalcException("Оба операнда должны быть либо арабскими, либо римскими (одновременно) " +
                "целыми числами от 1 до 10");
    }

}

