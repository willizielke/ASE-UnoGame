package presentation.input;

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
}