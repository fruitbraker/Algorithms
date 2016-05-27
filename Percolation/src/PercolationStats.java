
public class PercolationStats {
	
	public PercolationStats(int N, int T) {
		if(N > 0 && T > 0) {
			
		} else {
			throw new IllegalArgumentException("Inputted values must be greater than 0");
		}
	}
	
	public double mean() {
		return 0.0;
	}
	
	public double stddev() {
		return 0.0;
	}
	
	public double confidenceLo() {
		return 0.0;
	}
	
	public double confidenceHi() {
		return 0.0;
	}
}
