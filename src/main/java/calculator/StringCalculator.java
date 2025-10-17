package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    private static final String DEFAULT_DELIMITER = "[,:]";
    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final String CUSTOM_DELIMITER_SUFFIX = "\\n";
    private static final Pattern CUSTOM_DELIMITER_PATTERN = Pattern.compile("^//(.)\\n(.*)$", Pattern.DOTALL);

    public int calculate(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        String delimiter = DEFAULT_DELIMITER;
        String numbersText = input;

        if (input.startsWith(CUSTOM_DELIMITER_PREFIX)) {
            int delimiterIndex = 2;

            int newlineIndex = input.indexOf('\n');
            if (newlineIndex == -1) {
                newlineIndex = input.indexOf("\\n");
            }

            if (newlineIndex == -1 || newlineIndex <= delimiterIndex) {
                throw new IllegalArgumentException("잘못된 커스텀 구분자 형식입니다.");
            }

            String customDelimiter = input.substring(delimiterIndex, newlineIndex);
            delimiter = Pattern.quote(customDelimiter);

            int numberStartIndex = input.charAt(newlineIndex) == '\\' ? newlineIndex + 2 : newlineIndex + 1;
            numbersText = input.substring(numberStartIndex);
        }

        return sum(numbersText, delimiter);
    }

    private int sum(String text, String delimiter) {
        String[] tokens = text.split(delimiter);
        int sum = 0;

        for (String token : tokens) {
            int number = parseNumber(token);
            validatePositive(number);
            sum += number;
        }

        return sum;
    }

    private int parseNumber(String token) {
        try {
            return Integer.parseInt(token.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자가 아닌 값이 포함되어 있습니다: " + token);
        }
    }

    private void validatePositive(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("음수는 입력할 수 없습니다: " + number);
        }
    }
}