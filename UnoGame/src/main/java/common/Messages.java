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
    public static final void printLoadMessage(String type) {
        System.out.println("Please enter the number of one of the listed " + type + "s.");
    }

    // invalid Messages
    public static final void printInvalidInputMessageNumberBetween(int lowerBound, int upperBound) {
        System.out.println("Invalid input. Please enter a number between " + lowerBound + " and " + upperBound + ".");
    }

    public static final void printNotInDBMessage(String type) {
        System.out.println("The are no " + type + "s in the database to be loaded.");
    }
}
