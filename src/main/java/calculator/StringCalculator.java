package calculator;

public class StringCalculator {
    private static final String DEFAULT_DELIMITER = "[,:]";

    public int calculate(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        return sum(input, DEFAULT_DELIMITER);
    }

    private int sum(String text, String delimiter) {
        String[] tokens = text.split(delimiter);
        int sum = 0;

        for (String token : tokens) {
            sum += Integer.parseInt(token.trim());
        }

        return sum;
    }
}