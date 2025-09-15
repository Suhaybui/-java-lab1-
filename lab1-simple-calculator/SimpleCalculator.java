import java.util.Scanner;

class Calculator {
    private double currentValue = 0.0;
    private char pendingOperator = 0; // 0 means none
    private boolean hasPending = false;

    public void inputNumber(double value) {
        if (!hasPending) {
            currentValue = value;
        } else {
            currentValue = compute(currentValue, pendingOperator, value);
            hasPending = false;
            pendingOperator = 0;
        }
    }

    public void inputOperator(char op) {
        if (op == '+' || op == '-' || op == '*' || op == '/') {
            pendingOperator = op;
            hasPending = true;
        } else if (op == '=') {
            // '=' just shows current value (no change)
        } else {
            System.out.println("Unknown operator: " + op);
        }
    }

    private double compute(double left, char op, double right) {
        switch (op) {
            case '+': return left + right;
            case '-': return left - right;
            case '*': return left * right;
            case '/': return right == 0 ? Double.NaN : left / right;
            default:  return left;
        }
    }

    public String getDisplay() {
        return String.valueOf(currentValue);
    }
}

public class SimpleCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Calculator calc = new Calculator();

        System.out.println("Simple Calculator");
        System.out.println("Enter a number or operator (+, -, *, /, =). Type 'exit' to quit.");

        while (true) {
            System.out.print("> ");
            if (!sc.hasNextLine()) break;
            String token = sc.nextLine().trim();

            if (token.equalsIgnoreCase("exit")) break;

            // Number?
            try {
                double value = Double.parseDouble(token);
                calc.inputNumber(value);
                System.out.println(calc.getDisplay());
                continue;
            } catch (NumberFormatException ignored) {}

            // Operator?
            if (token.length() == 1) {
                char op = token.charAt(0);
                calc.inputOperator(op);
                System.out.println(calc.getDisplay());
            } else {
                System.out.println("Invalid input. Enter a number or + - * / =");
            }
        }

        sc.close();
        System.out.println("Goodbye.");
    }
}
