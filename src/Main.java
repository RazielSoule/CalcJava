import java.io.IOException;
import java.util.Scanner;

// Делал всё быстро, с одного захода, толком не вспомнив java основываясь на том задании, что я делал для GO
// потому костыли, ужас. Слабонервным прочь из репы

public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println("Hello and welcome!");
        System.out.println("Enter what need to calculate");

        while (true) {
            Scanner in = new Scanner(System.in);
            System.out.println("Result = " + calc(in.nextLine()));
        }
    }

    public static String calc(String input) throws IOException {
        String[] inputSeparated = input.split(" ");

        if (inputSeparated.length != 3) {
            System.out.println("Wrong number of inputs");
            throw new IOException();
        }

        int firstNumber,secondNumber,result;
        String operation = inputSeparated[1];
        boolean firstRoma = false, secondRoma = false, romanOut = false;

        try {
            firstNumber = Integer.parseInt(inputSeparated[0], 10);
        }catch (NumberFormatException e){
            firstNumber = toArabicConvert(inputSeparated[0].toUpperCase());
            firstRoma = true;
        }
        try {
            secondNumber = Integer.parseInt(inputSeparated[2], 10);
        }catch (NumberFormatException e){
            secondNumber = toArabicConvert(inputSeparated[2].toUpperCase());
            secondRoma = true;
        }

        if (firstRoma || secondRoma) {
            if (firstRoma && secondRoma){
                romanOut = true;
            }
            else {
                System.out.println("only 1 type of numbers at time!");
                throw new IOException();
            }
        }

        if (1 > firstNumber || firstNumber > 10 || 1 > secondNumber || secondNumber > 10){
            System.out.println("too big!");
            throw new IOException();
        }


        result = switch (operation) {
            case "+" -> firstNumber + secondNumber;
            case "-" -> firstNumber - secondNumber;
            case "*" -> firstNumber * secondNumber;
            case "/" -> firstNumber / secondNumber;
            default -> {
                System.out.println("unknown operation");
                throw new IOException();
            }
        };

        if (romanOut) {
            if (result > 0) {
                return toRomanConvert(result);
            }
            else {
                System.out.println("roman numbers can't be 0 or below");
                throw new IOException();
            }
        }
        else {
            return "" + result;
        }


    }

    static int toArabicConvert(String roman) throws IOException {
        return switch (roman) {
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII" -> 8;
            case "IX" -> 9;
            case "X" -> 10;
            default -> {
                System.out.println("wrong number");
                throw new IOException();
            }
        };
    }

    static String toRomanConvert(int arabic){
        int counter = 0;
        String romanOut = "";
        if (arabic >= 100) {
            counter = arabic / 100;
            arabic %= 100;
            for (int y = counter; y != 0; y--) {
                romanOut += "C";
            }
            counter = 0;
        }

        if (arabic >= 90) {
            arabic %= 90;
            romanOut += "XC";
        }

        if (arabic >= 50) {
            arabic %= 50;
            romanOut += "L";
        }

        if (arabic >= 10) {
            counter = arabic / 10;
            arabic %= 10;
            if (counter == 4) {
                romanOut += "XL";
                counter = 0;
            }
            for (int y = counter; y != 0; y--) {
                romanOut += "X";
            }
            counter = 0;
        }

        if (arabic == 9) {
            arabic = 0;
            romanOut += "IX";
        }

        if (5 <= arabic) {
            arabic %= 5;
            romanOut += "V";
        }

        if (arabic >= 1) {
            counter = arabic;
            arabic = 0;
            if (counter == 4) {
                romanOut += "IV";
                counter = 0;
            }
            for (int y = counter; y != 0; y--) {
                romanOut += "I";
            }
            counter = 0;
        }

        return romanOut;
    }
}