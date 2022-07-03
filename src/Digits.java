import java.util.HashMap;
import java.util.Map;

class Digits {
    private static final Map<String, String> romanArabicMatch = new HashMap<>();
    private static final Map<Integer, String> arabicRomanMatch = new HashMap<>();

    static {
        romanArabicMatch.put("I", "1");
        romanArabicMatch.put("II", "2");
        romanArabicMatch.put("III", "3");
        romanArabicMatch.put("IV", "4");
        romanArabicMatch.put("V", "5");
        romanArabicMatch.put("VI", "6");
        romanArabicMatch.put("VII", "7");
        romanArabicMatch.put("VIII", "8");
        romanArabicMatch.put("IX", "9");
        romanArabicMatch.put("X", "10");
        arabicRomanMatch.put(1, "I");
        arabicRomanMatch.put(2, "II");
        arabicRomanMatch.put(3, "III");
        arabicRomanMatch.put(4, "IV");
        arabicRomanMatch.put(5, "V");
        arabicRomanMatch.put(6, "VI");
        arabicRomanMatch.put(7, "VII");
        arabicRomanMatch.put(8, "VIII");
        arabicRomanMatch.put(9, "IX");
        arabicRomanMatch.put(10, "X");
        arabicRomanMatch.put(20, "XX");
        arabicRomanMatch.put(30, "XXX");
        arabicRomanMatch.put(40, "XL");
        arabicRomanMatch.put(50, "L");
        arabicRomanMatch.put(60, "LX");
        arabicRomanMatch.put(70, "LXX");
        arabicRomanMatch.put(80, "LXXX");
        arabicRomanMatch.put(90, "XC");
        arabicRomanMatch.put(100, "C");
    }

    public boolean isRoman(String digit) {
        return romanArabicMatch.containsKey(digit);
    }

    public boolean isArabic(String digit) {
        return romanArabicMatch.containsValue(digit);
    }

    public String romanToArabic(String digit) {
        return romanArabicMatch.getOrDefault(digit, null);
    }

    public String arabicToRoman(Integer digit) throws CalcException {
        try {
            if (digit <= 0) {
                throw new CalcException("В римском исчислении отсутствуют отрицательные числа и ноль");
            } else {
                int dozens = digit / 10;
                int remainder = digit % 10;
                String result = "";
                if (dozens > 0) {
                    dozens *= 10;
                    result += arabicRomanMatch.get(dozens);
                }
                if (remainder > 0) {
                    result += arabicRomanMatch.get(remainder);
                }
                return result;
            }
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public int calc(int op1, String operator, int op2) throws CalcException {
        return switch (operator) {
            case "+" -> op1 + op2;
            case "-" -> op1 - op2;
            case "*" -> op1 * op2;
            case "/" -> op1 / op2;
            default -> throw new CalcException("Оператор должен быть одним из четырех символов +, -, *, /");
        };
    }

}
