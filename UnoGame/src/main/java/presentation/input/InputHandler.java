package presentation.input;

import java.io.IOException;
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
}