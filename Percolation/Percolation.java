import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private int numberOfOpenSites;
	private int sizeOfSystem;
	private int sizeOfOneD;
	private int topSite;
	private int botSite;
	private boolean system[][];
	private WeightedQuickUnionUF oneD;

	public Percolation(int n) {
		if (n <= 0) {
			throw new java.lang.IllegalArgumentException();
		}
		this.sizeOfSystem = n;
		this.system = new boolean[n][n];
		this.numberOfOpenSites = 0;
		this.sizeOfOneD = n*n + 2;
		this.topSite = sizeOfOneD - 2;
		this.botSite = sizeOfOneD - 1;
		oneD = new WeightedQuickUnionUF(this.sizeOfOneD);
	}
	
	private boolean validateIndices(int row, int col) {
		if (row < 1 || col < 1 || row > sizeOfSystem || col > sizeOfSystem)
			return false;
		return true;
		}
	
	public void open(int row, int col) {
		if (!validateIndices(row,col))
			throw new java.lang.IllegalArgumentException();
		int actRow = row -1;
		int actCol = col -1;
		system[actRow][actCol] = true;
		this.numberOfOpenSites++;
		openIfTopOrBot(row,col);
		//Check for top
		if (validateIndices(row-1,col)) {
			if(isOpen(row-1,col)) {
				connect(row-1,col,row,col);
			}
		}
		//Check for bottom
		if (validateIndices(row+1,col)) {
			if(isOpen(row+1,col)) {
				connect(row+1,col,row,col);
			}
		}
		//Check for left
		if (validateIndices(row,col-1)) {
			if(isOpen(row,col-1)) {
				connect(row,col-1,row,col);
			}
		}
		//Check for right						 
		if (validateIndices(row,col+1)) {
			if(isOpen(row,col+1)) {
				connect(row,col+1,row,col);
			}
		}
	}
	
	private void openIfTopOrBot(int x, int y) {
		int site = serialize(x,y);
		//Check if top or bot sites are being opened
		if (site < sizeOfSystem){
			oneD.union(site,topSite);
		}
		if (site < topSite && site >= topSite - sizeOfSystem)
			oneD.union(site,botSite);
	}
	
	private void connect(int i, int j, int x, int y) {
		int site1 = serialize(i,j);
		int site2 = serialize(x,y); //the site which was last opened
		oneD.union(site1,site2);
	}
	
	private int serialize(int row, int col) {
		int actRow = row - 1;
		int actCol = col - 1;
		return sizeOfSystem*actRow + actCol;
	}
	
	public boolean isOpen(int row, int col) {
		if (!validateIndices(row,col))
			throw new java.lang.IllegalArgumentException();
		int actRow = row -1;
		int actCol = col -1;
		return system[actRow][actCol];
	}
	
	public boolean isFull(int row, int col) {
		return oneD.connected(serialize(row,col), topSite);
	}
	
	public int numberOfOpenSites() {
		return this.numberOfOpenSites;
	}
	
	public boolean percolates() {
		return oneD.connected(topSite, botSite);
	}
	
	public static void main(String[] args){
/*		Percolation p = new Percolation(3);
		p.printMatrix();
		p.open(1,9);
		p.open(2,2);
		p.open(0,3);
		p.print1D();
		System.out.println(p.percolates());
*/
	}
}
