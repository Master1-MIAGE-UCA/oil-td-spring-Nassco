package fr.unica.vandamme.dice;

public class DiceStats {
    private final double mean;
    private final double median;
    private final double stdDev;
    private final int min;
    private final int max;

    public DiceStats(double mean, double median, double stdDev, int min, int max) {
        this.mean = mean;
        this.median = median;
        this.stdDev = stdDev;
        this.min = min;
        this.max = max;
    }

    public double getMean() {
        return mean;
    }

    public double getMedian() {
        return median;
    }

    public double getStdDev() {
        return stdDev;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
