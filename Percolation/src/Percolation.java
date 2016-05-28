import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int N;
	private WeightedQuickUnionUF weightedUf;
	private boolean[] sites;
	private boolean percolates;
	
	public Percolation(int N) {
		if (N > 0) {
			this.N = N;
			sites = new boolean[N*N];
			percolates = false;
			weightedUf = new WeightedQuickUnionUF((N*N) + 1);
		} 
		else {
			throw new IllegalArgumentException("Input must be greater than 0");
		}
	}
	
	//i = row, j = column
	public void open(int i, int j) {
		if ((i+j >= 2) && ((i <= N) && (j <= N))) {
			if (!sites[xyToId(i, j)]) {
				sites[xyToId(i, j)] = true;
				
				
				if ((i > 1) && (sites[xyToId(i-1, j)])) {
					weightedUf.union(xyToId(i, j), xyToId(i-1, j));
				}
				if ((i < N) && (sites[xyToId(i+1, j)])) {
					weightedUf.union(xyToId(i, j), xyToId(i+1, j));
				}
				if ((j > 1) && (sites[xyToId(i, j-1)])) {
					weightedUf.union(xyToId(i, j), xyToId(i, j-1));
				}
				if ((j < N) && (sites[xyToId(i, j+1)])) {
					weightedUf.union(xyToId(i, j), xyToId(i, j+1));
				}
				
				if (i == 1) {
					weightedUf.union(xyToId(i, j), N*N);
				}
				
			}
		}
		else
			throw new IndexOutOfBoundsException("Coordinates are out of range");
	}
	
	public boolean isOpen(int i, int j) {
		if ((i+j >= 2) && ((i <= N) && (j <= N)))
			return (sites[xyToId(i, j)]);
		else 
			throw new IndexOutOfBoundsException("Coordinates are out of range");
		
	}
	
	public boolean isFull(int i, int j) {
		if ((i+j >= 2) && (i+j <= N*2)) {
			if (weightedUf.connected(xyToId(i, j), N*N) && isOpen(i, j)) {
				if (i == N) {
					percolates = true;
				}
				return true;
//				if(i < N)
//					return true;
//				else if(isOpen(i, j)) {
//					percolates = true;
//					return true;
//				}
//				else
//					return false;
			}
			else
				return false;
		} 
		else {
			throw new IndexOutOfBoundsException("Coordinates are out of range");
		}
	}
	
	public boolean percolates() {
		return percolates;
	}
	
	private int xyToId(int row, int col) {
		return ((row-1) * N) + col-1;
	}
	
	
	public static void main(String[] args) {
		Percolation perc = new Percolation(4);
		StdOut.print("a: ");
		perc.open(2, 3);
		StdOut.print("b: ");
		perc.open(3, 3);
		StdOut.print("c: ");
		perc.open(1, 3);
		StdOut.print("d: ");
		perc.open(4, 3);
		StdOut.print("e: ");
		perc.open(3, 2);
		
		
		StdOut.println(perc.percolates());
		StdOut.println(perc.isFull(3, 2));
		
		
	}
	
}
