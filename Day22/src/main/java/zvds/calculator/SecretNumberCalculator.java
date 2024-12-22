package zvds.calculator;

public class SecretNumberCalculator {
    private final int PRUNE_VALUE = 16777216;

    public long[] predictNextSecretNumbers(int secretStartingNumber, int amountToPredict){
        long[] predictedNumbers = new long[amountToPredict + 1];
        predictedNumbers[0] = secretStartingNumber;
        long currentSecretNumber = secretStartingNumber;
        for (int i = 0; i < amountToPredict; i++) {
            currentSecretNumber = predictNextSecretNumber(currentSecretNumber);
            predictedNumbers[i + 1] = currentSecretNumber;
        }
        return predictedNumbers;
    }

    public long predictNextSecretNumber(long secretNumber) {
        long step1Calculation = secretNumber * 64;
        step1Calculation = mixValue(secretNumber, step1Calculation);
        step1Calculation = pruneValue(step1Calculation, PRUNE_VALUE);

        long step2Calculation = step1Calculation / 32;
        step2Calculation = mixValue(step1Calculation, step2Calculation);
        step2Calculation = pruneValue(step2Calculation, PRUNE_VALUE);

        long step3Calculation = step2Calculation * 2048;
        step3Calculation = mixValue(step2Calculation, step3Calculation);
        step3Calculation = pruneValue(step3Calculation, PRUNE_VALUE);

        return step3Calculation;
    }

    public long[] extractOnesPosition(long[] prices) {
        long[] onesPositionArray = new long[prices.length];
        for (int i = 0; i < prices.length; i++) {
            onesPositionArray[i] = prices[i]%10;
        }
        return onesPositionArray;
    }

    public long mixValue(long secretvalue, long mixValue) {
        long result = secretvalue ^ mixValue;
        return result;
    }

    public long pruneValue(long secretvalue, int pruneValue) {
        long result = secretvalue % pruneValue;
        return result;
    }
}
