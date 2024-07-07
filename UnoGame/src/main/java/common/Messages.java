package common;

public class Messages {
    public static final void printMainMenuSelection() {
        System.out.println(
                "Please enter the number of one of the listed options.\n1 - Match | 2 - Competition | 3 - History | 4 - Settings | 5 - Exit");
    }

    public static final void printMatchViewSelection() {
        System.out.println(
                "Please enter the number of one of the listed options.\n1 - New Fast Match | 2 - New Match | 3 - Back");
    }

    public static final void printCompetitionViewSelection() {
        System.out.println(
                "Please enter the number of one of the listed options.\n1 - New Competition | 2 - Load Competition | 3 - Back");
    }

    public static final void printMatchRulesSelection() {
        System.out.println(
                "Please enter the number of one of the listed options.\n1 - Local Rules | 2 - Original Rules");
    }

    public static final void printCreateOrLoadMessage(String type) {
        System.out.println("Do you want to create a new " + type + " or load a " + type + "? (new - 1 | load - 2)");
    }

    public static final void printLoadMessage(String type) {
        System.out.println("Please enter the number of one of the listed " + type + "s.");
    }

    public static final void printLoadMultipleMessage(String type) {
        System.out.println("Please enter the numbers of one or more of the listed " + type + "s.(for example: 1,3,5)");
    }

    public static final void printGetPlayerCountMessage() {
        System.out.println("How many Players?");
    }

    public static final void printGetNameMessage(String type) {
        System.out.println("Please enter the name of the " + type + " (max 15 characters)");
    }

    public static final void printSlash(int now, int end) {
        System.out.print("(" + now + "/" + end + ") ");
    }

    // invalid Messages
    public static final void printInvalidInputMessageNumberBetween(int lowerBound, int upperBound) {
        System.out.println("Invalid input. Please enter a number between " + lowerBound + " and " + upperBound + ".");
    }

    public static final void printNotInDBMessage(String type) {
        System.out.println("The are no " + type + "s in the database to be loaded.");
    }

    public static final void printNameTooLongMessage() {
        System.out.println("The name is too long. Please enter a name with a maximum of 15 characters.");
    }

    public static final void printInvalidInputMessage() {
        System.out.println("Invalid input. Please enter a valid input.");
    }

    public static final void printInvalidInputMessagePlayerIsAlreadyInTheGame() {
        System.out.println("The Player is already in the Game. Please enter a different Player.");
    }
}
