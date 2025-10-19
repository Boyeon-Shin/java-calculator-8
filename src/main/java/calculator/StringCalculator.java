package calculator;

public class StringCalculator {
    private static final String DEFAULT_DELIMITER = "[,:]";

    public int calculate(final String input) {
        if(input == null || input.isEmpty()) {
            return 0;
        }

        String[] tokens = input.split(DEFAULT_DELIMITER);
        int sum = 0;

        for (String token : tokens) {
            sum += Integer.parseInt(token);
        }
        return sum;
    }
}
