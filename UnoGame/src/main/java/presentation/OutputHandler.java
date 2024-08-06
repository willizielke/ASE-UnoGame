package presentation;

public class OutputHandler {
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

    public static final void printCompetitionProcessViewSelection() {
        System.out.println(
                "Please enter the number of one of the listed options.\n1 - Start Next Match | 2 - View all Matches played | 3 - Back");
    }

    public static final void printMatchRulesSelection() {
        System.out.println(
                "Please enter the number of one of the listed options.\n1 - Local Rules | 2 - Original Rules");
    }

    public static final void printCompetitionRulesSelection() {
        System.out.println(
                "Please enter the number of one of the listed options.\n1 - Match Wins | 2 - Last one under 101 Points");
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

    public static final void getMatchesToWin() {
        System.out.println("Please enter the number of matches to win the competition.");
    }

    public static final void printSlash(int now, int end) {
        System.out.print("(" + now + "/" + end + ") ");
    }

    public static final void printNextMove(String playerName, boolean hasAlreadyPulled, int cardsToDraw) {
        System.out.print(playerName + " please enter the number of your card(s) ");
        if (!hasAlreadyPulled) {
            System.out.println("or pull " + cardsToDraw + " card(s).\n0. Pull Cards");
        } else {
            System.out.println("or skip (0).\n0. Skip");
        }

    }

    public static final void printWishColorMessage() {
        System.out.println("Please enter the number of the color you wish.\n1. Red\n2. Green\n3. Blue\n4. Yellow");
    }

    public static final void printMatchOverMessage(String playerName) {
        System.out.println(playerName + " has won the game!");
    }

    public static final void printMatchScores() {
        System.out.println("Match scores:");
    }

    public static final void printMatchNumber(int matchNumber) {
        System.out.println("Match " + matchNumber + ":");
    }

    public static final void printTotalScores() {
        System.out.println("Total scores (you get elimination if more than 101):");
    }

    public static final void printTotalWins(int matchesToWin) {
        System.out.println("Total wins (matches to win " + matchesToWin + "):");
    }

    public static final void printLastCard() {
        System.out.println("Last card: ");
    }

    public static final void printWishedColor(String wishedColor) {
        System.out.println("Wished color: " + wishedColor);
    }

    public static final void printSortOptions() {
        System.out.println(
                "\nPlease enter the number of one of the listed options. Sort by:\n1 - Competition Count\n2 - Competition Win Count\n3 - Match Count\n4 - Match Win Count\n5 - Match Lose Count\n6 - Accumulated Points\n7 - Points Per Match\n8 - Back");
    }

    // printWinnerAndEndCompetition
    public static final void printWinnerOfCompetition(String winnerName) {
        System.out.println("The winner of the competition is: " + winnerName);
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

    // clear console
    public static void clearConsole() {
        // Clear console workaround
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
