import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	private int N;
	private WeightedQuickUnionUF weightedUf;
	private byte[] sites;
	
	//Virtual top: N*N
	//Virtual bottom: N*N+1
	
	public Percolation(int N) {
		//TODO: initialize N/X, all "blocked"
		if(N > 0) {
			this.N = N;
			sites = new byte[N*N];
			weightedUf = new WeightedQuickUnionUF((N*N) + 2);
			for(int i=0; i<sites.length; i++) {
				sites[i] = 0;
				if(i >= 0 && i < N) {
					//Virtual top
					weightedUf.union(N*N, i);
				} else if(i >= (N * (N-1)) && i < N*N) {
					//Virtual bottom
					weightedUf.union(N*N+1, i);
				}
			}
		} else {
			throw new IllegalArgumentException("Input must be greater than 0");
		}
	}
	
	//i = row, j = column
	public void open(int i, int j) {
		if((i+j >= 2) && ((i <= N) && (j <= N))) {
			if(sites[xyToId(i, j)] == 0) {
				sites[xyToId(i, j)] = 1;
				
				
				if((i > 1) && (sites[xyToId(i-1, j)] != 0)) {
					StdOut.print("1: ");
					weightedUf.union(xyToId(i-1, j), xyToId(i, j));
					printStuff();
				}
				if((i < N) && (sites[xyToId(i+1, j)] != 0)) {
					StdOut.print("2: ");
					weightedUf.union(xyToId(i+1, j), xyToId(i, j));
					printStuff();
				}
				if((j > 1) && (sites[xyToId(i, j-1)] != 0)) {
					StdOut.print("3: ");
					weightedUf.union(xyToId(i, j), xyToId(i, j-1));
					printStuff();
				}
				if((j < N) && (sites[xyToId(i, j+1)] != 0)) {
					StdOut.print("4: ");
					weightedUf.union(xyToId(i, j), xyToId(i, j+1));
					printStuff();
				}
				
				
				if(weightedUf.connected(xyToId(i, j), N*N))
					sites[xyToId(i, j)] = 2;
			}
			
		}
		else
			throw new IndexOutOfBoundsException("Coordinates are out of range");
	}
	
	public boolean isOpen(int i, int j) {
		if((i+j >= 2) && ((i <= N) && (j <= N)))
			return (sites[xyToId(i, j)] > 0);
		else 
			throw new IndexOutOfBoundsException("Coordinates are out of range");
		
	}
	
	public boolean isFull(int i, int j) {
		if((i+j >= 2) && ((i <= N) && (j <= N))) {
			switch(sites[xyToId(i, j)]) {
				case 0:
					return false;
				case 1:
					return false;
				case 2: 
					return true;
				default:
					return false;
			}
		}
		else {
			throw new IndexOutOfBoundsException("Coordinates are out of range");
		}
	}
	
	public boolean peroclates() {
		return weightedUf.connected(N*N, N*N+1);
	}
	
	private int xyToId(int row, int col) {
		return ((row-1) * N) + col-1;
	}
	
	public void printStuff() {
		for(int i=0; i<sites.length; i++) {
			System.out.print(weightedUf.find(i) + " ");
		}
		System.out.println("\n");
	}
	
	public void printSite(int i, int j) {
		StdOut.println(xyToId(i, j) + "   " + sites[xyToId(i, j)]);
	}
	
	public static void main(String[] args) {
		Percolation perc = new Percolation(4);
		perc.printStuff();
		perc.open(2, 3);
		perc.printStuff();
		perc.open(3, 3);
		perc.printStuff();
		perc.open(1, 3);
		perc.printStuff();
		perc.open(4, 3);
		
		
		StdOut.println(perc.peroclates());
		StdOut.println(perc.isFull(1, 3));
		
		
	}
	
}
