package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
    private static final String DEFAULT_DELIMITER = "[,:]";
    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final Pattern CUSTOM_DELIMITER_PATTERN = Pattern.compile("^//(.)\n(.*)$");

    public int calculate(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        String delimiter = DEFAULT_DELIMITER;
        String numbersText = input;

        if (input.startsWith(CUSTOM_DELIMITER_PREFIX)) {
            Matcher matcher = CUSTOM_DELIMITER_PATTERN.matcher(input);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("잘못된 커스텀 구분자 형식입니다.");
            }
            delimiter = Pattern.quote(matcher.group(1));
            numbersText = matcher.group(2);
        }

        return sum(numbersText, delimiter);
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