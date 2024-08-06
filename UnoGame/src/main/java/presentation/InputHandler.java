package presentation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputHandler {

    static Scanner scanner = new Scanner(System.in);

    public static String getInput() {
        return scanner.nextLine();
    }

    public static int getNumberBetween(int min, int max) {
        String input = getInput();
        while (!ValidationHelper.isNumberBetween(input, min, max)) {
            OutputHandler.printInvalidInputMessageNumberBetween(min, max);
            input = getInput();
        }
        return Integer.parseInt(input);
    }

    public static String getName(String type) throws IOException {
        String name;
        do {
            name = getInput();
            if (name.isEmpty()) {
                OutputHandler.printInvalidInputMessage();
                continue;
            }
            name = ValidationHelper.stringIs15Characters(name);
            if (name.isEmpty()) {
                OutputHandler.printNameTooLongMessage();
            }
        } while (name.isEmpty());
        return name;
    }

    public static List<Integer> getNumbers(int max) {
        String numbersWithComma = "";
        List<Integer> list = new ArrayList<>();
        do {
            list.clear();
            numbersWithComma = getInput();
            if (!ValidationHelper.stringIsCommaValid(numbersWithComma)) {
                OutputHandler.printInvalidInputMessage();
                numbersWithComma = "";
                continue;
            }
            String[] numbers = numbersWithComma.split(",");

            for (String number : numbers) {
                if (!ValidationHelper.isNumberBetween(number, 0, max)) {
                    OutputHandler.printInvalidInputMessageNumberBetween(1, max);
                    numbersWithComma = "";
                    continue;
                }
                list.add(Integer.parseInt(number));
            }
            if(list.size()>2){
                OutputHandler.printInvalidInputMessage();
                numbersWithComma = "";
            }
        } while (numbersWithComma.isEmpty());

        return list;
    }
}