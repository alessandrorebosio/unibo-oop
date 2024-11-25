public class Calculator {

    int nOpDone = 0;
    double ans = 0;

    public double add(final double a, final double b) {
        return operation(a + b);
    }

    public double sub(final double a, final double b) {
        return operation(a - b);
    }

    public double mul(final double a, final double b) {
        return operation(a * b);
    }

    public double div(final double a, final double b) {
        return operation(a / b);
    }

    private double operation(final double result) {
        this.nOpDone++;
        this.ans = result;
        return result;
    }

}
