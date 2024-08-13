package presentation;

public class InputHandlerFake extends InputHandler {
    private static String currentName = "TestPlayer";

    public void setName(String name) {
        currentName = name;
    }

    public int getNumberBetween(int min, int max) {
        return min;
    }

    public String getName(String prompt) {
        return currentName;
    }
}