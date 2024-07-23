package presentation.input;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import common.Messages;
import common.ValidationHelper;

public class InputHandler {

    static Scanner scanner = new Scanner(System.in);

    public static String getInput() {
        return scanner.nextLine();
    }

    public static int getNumberBetween(int min, int max) {
        String input = getInput();
        while (!ValidationHelper.isNumberBetween(input, min, max)) {
            Messages.printInvalidInputMessageNumberBetween(min, max);
            input = getInput();
        }
        return Integer.parseInt(input);
    }

    public static String getName(String type) throws IOException {
        String name;
        do {
            name = getInput();
            if (name.isEmpty()) {
                Messages.printInvalidInputMessage();
                continue;
            }
            name = ValidationHelper.stringIs15Characters(name);
            if (name.isEmpty()) {
                Messages.printNameTooLongMessage();
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
                Messages.printInvalidInputMessage();
                numbersWithComma = "";
                continue;
            }
            String[] numbers = numbersWithComma.split(",");

            for (String number : numbers) {
                if (!ValidationHelper.isNumberBetween(number, 0, max)) {
                    Messages.printInvalidInputMessageNumberBetween(1, max);
                    numbersWithComma = "";
                    continue;
                }
                list.add(Integer.parseInt(number));
            }
            if(list.size()>2){
                Messages.printInvalidInputMessage();
                numbersWithComma = "";
            }
        } while (numbersWithComma.isEmpty());

        return list;
    }
}