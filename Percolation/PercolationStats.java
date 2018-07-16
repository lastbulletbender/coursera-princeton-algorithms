import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
	private int n;
	private int trials;
	private double percolationThresholds[];
	private double mean;
	private double stddev;
	
	public PercolationStats(int n, int trials) {
		if (n <= 0 || trials <= 0) {
			throw new java.lang.IllegalArgumentException();
		}
		this.n = n;
		this.trials = trials;
		percolationThresholds = new double[trials];
		for (int i = 0;i < trials;i++) {
			Percolation p = new Percolation(this.n);
			int attempts = 0;
			while(!p.percolates()) {
				int x = StdRandom.uniform(this.n) + 1;
				int y = StdRandom.uniform(this.n) + 1;
				if (!p.isOpen(x,y)) {
					p.open(x,y);
					attempts++;
				}
			}
			percolationThresholds[i] = attempts / Math.pow(this.n,2);	
		}
		mean = StdStats.mean(percolationThresholds);
		stddev = StdStats.stddev(percolationThresholds);
	}
	
	public double mean() {
		return mean;
	}
	
	public double stddev() {
		if (this.trials == 1) return Double.NaN;
		return stddev;
	}
	
	public double confidenceLo() {
		return mean() - (1.96 *stddev())/Math.sqrt(trials);
	}
	
	public double confidenceHi() {
		return mean() + (1.96 *stddev())/Math.sqrt(trials);
	}
	
	public static void main(String[] args) {
		PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
		System.out.println("mean						= " + ps.mean());
		System.out.println("stddev						= " + ps.stddev());
		System.out.println("95% confidence interval		= [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]"); 
	}
	
}
	
