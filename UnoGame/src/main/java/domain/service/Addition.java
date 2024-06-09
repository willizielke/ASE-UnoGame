package domain.service;

public class Addition implements CalculatorOperation {
    private int left;
    private int right;
    private int result = 0;

    public Addition(int left, int right) {
        this.left = left;
        this.right = right;
    }
    public int getLeft() {
        return left;
    }
    public int getRight() {
        return right;
    }
    public void setResult(int result) {
        this.result = result;
    }
}


