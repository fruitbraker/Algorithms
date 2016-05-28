import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	
	private int iterations;
	private Percolation perc;
	private double[] fracs;
	
	public PercolationStats(int N, int T) {
		if(N > 0 && T > 0) {
			iterations = T;	
			fracs = new double[N];
			for(int counter=0; counter<iterations; counter++) {
				perc = new Percolation(N);
				int open = 0;
				while(!perc.percolates()) {
					int i = StdRandom.uniform(1, N + 1);
	                int j = StdRandom.uniform(1, N + 1);
	                if (!perc.isOpen(i, j)) {
	                    perc.open(i, j);
	                	perc.isFull(i, j);
	                    open++;
	                }
				}
				
				double fraction = (double) open / (N * N);
	            fracs[counter] = fraction;
			}
		} else {
			throw new IllegalArgumentException("Inputted values must be greater than 0");
		}
	}
	
	public double mean() {
		return StdStats.mean(fracs);	
	}
	
	public double stddev() {
		return StdStats.stddev(fracs);
	}
	
	public double confidenceLo() {
		return mean() - (1.96 * (stddev()/Math.sqrt(iterations)));
	}
	
	public double confidenceHi() {
		return mean() + (1.96 * (stddev()/Math.sqrt(iterations)));
	}
	
	public static void main(String...args) {
		PercolationStats percStats = new PercolationStats(200, 100);
		StdOut.println(percStats.mean() + "\n" + percStats.stddev() + "\n" + percStats.confidenceLo() + "\n" + percStats.confidenceHi());
	}
}
