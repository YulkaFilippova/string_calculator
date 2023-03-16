import java.util.Scanner;

class Calculator {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String expression = scanner.nextLine();
        String result = calc(expression);
        System.out.println(result);
    }

    public static String calc(String input) throws Exception {
        int num1;
        int num2;
        String oper;
        String result;
        boolean isRoman;
        String[] operands = input.split("[+\\-*/]");
        oper = detectOperation(input);
        if (oper == null) throw new Exception("Неподдерживаемая математическая операция");
        if (operands.length != 2)
            throw new Exception("Формат математической операции не удовлетворяет заданию - два операнда и один оператор");

        if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[1])) {
            //конвертируем оба числа в арабские для вычесления действия
            num1 = Roman.convertToArabian(operands[0]);
            num2 = Roman.convertToArabian(operands[1]);
            isRoman = true;
        } else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[1])) {
            try {
                num1 = Integer.parseInt(operands[0]);
                num2 = Integer.parseInt(operands[1]);
                isRoman = false;
            } catch (NumberFormatException e) {
                throw new Exception("Дробные числа использовать нельзя");
            }
        } else {
            throw new Exception("Используются одновременно разные системы исчисления");
        }
        if (num1 > 10 || num2 > 10 || num1 < 1 || num2 < 1) {
            throw new Exception("Возможно использовать числа от 1 до 10 (от I до X)");
        }
        int arabian = calc(num1, num2, oper);
        if (isRoman) {

            if (arabian <= 0) {
                throw new Exception("В римской системе нет отрицательных чисел");
            }

            result = Roman.convertToRoman(arabian);
        } else {

            result = String.valueOf(arabian);
        }

        return result;
    }

    static String detectOperation(String expression) {
        if (expression.contains("+")) return "+";
        else if (expression.contains("-")) return "-";
        else if (expression.contains("*")) return "*";
        else if (expression.contains("/")) return "/";
        else return null;
    }

    static int calc(int a, int b, String oper) {

        if (oper.equals("+")) return a + b;
        else if (oper.equals("-")) return a - b;
        else if (oper.equals("*")) return a * b;
        else return a / b;
    }

}

class Roman {
    static String[] romanArray = new String[]{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
            "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
            "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
            "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
            "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
            "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
            "XCVIII", "XCIX", "C"};


    public static boolean isRoman(String val) {
        for (int i = 0; i < romanArray.length; i++) {
            if (val.equals(romanArray[i])) {
                return true;
            }
        }
        return false;
    }

    public static int convertToArabian(String roman) {
        for (int i = 0; i < romanArray.length; i++) {
            if (roman.equals(romanArray[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String convertToRoman(int arabian) {
        return romanArray[arabian];
    }

}
