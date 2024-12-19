package zvds.calculator.distance;

public class SimpleDistanceCalculator implements DistanceCalculator {
    @Override
    public int calculateListDifference(int[] inp1, int[] inp2) {
        int totalDistance = 0;
        for (int i = 0; i < inp1.length; i++) {
            totalDistance += calculateNumberDifference(inp1[i], inp2[i]);
        }
        return totalDistance;
    }

    @Override
    public int calculateNumberDifference(int num1, int num2) {
        return Math.abs(num1 - num2);
    }
}
