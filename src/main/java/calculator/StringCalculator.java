package calculator;

import java.util.regex.Pattern;

public class StringCalculator {
    private static final String DEFAULT_DELIMITER = "[,:]";
    private static final String CUSTOM_DELIMITER_PREFIX = "//";
    private static final String CUSTOM_DELIMITER_SUFFIX = "\\n";


    public int calculate(final String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }

        String delimiter = DEFAULT_DELIMITER;
        String numbersString = input;

        if (input.startsWith(CUSTOM_DELIMITER_PREFIX)) {
            int delimiterEndIndex = input.indexOf(CUSTOM_DELIMITER_SUFFIX);
            if (delimiterEndIndex == -1) {
                throw new IllegalArgumentException("잘못된 커스텀 구분자 형식입니다.");
            }
            String customDelimiter = input.substring(2, delimiterEndIndex);
            if (customDelimiter.isEmpty()) {
                throw new IllegalArgumentException("커스텀 구분자가 비어있습니다.");
            }
            delimiter = Pattern.quote(customDelimiter);
            numbersString = input.substring(delimiterEndIndex + 2);
        }

        return sumNumbers(numbersString, delimiter);
    }


    private int sumNumbers(String numbersString, String delimiter) {
        if (numbersString.isEmpty()) {
            return 0;
        }
        String[] tokens = numbersString.split(delimiter);
        int sum = 0;

        for (String token : tokens) {
            int number = parseNumber(token);
            validateNoNegative(number);
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

    private void validateNoNegative(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("음수는 입력할 수 없습니다: " + number);
        }
    }
}
