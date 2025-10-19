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
            String customDelimiter = input.substring(2, delimiterEndIndex);
            delimiter = Pattern.quote(customDelimiter);
            numbersString = input.substring(delimiterEndIndex + 2);
        }

        String[] tokens = numbersString.split(delimiter);
        int sum = 0;

        for (String token : tokens) {
            sum += Integer.parseInt(token);
        }
        return sum;
    }
}
