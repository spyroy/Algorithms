package airplane_broblem;
import java.util.Arrays;
import java.util.ArrayList;

public class AirPlane {
	
		int numOfPaths, cheapestPrice, cheapestPrice2, len;
		private Node [][]mat;
		public AirPlane(Node[][] nodes){
			numOfPaths = 0;
			cheapestPrice = 0;
			mat = nodes;

		}
		private int getBestPrice2ij(int i, int j){
			int[] t = {mat[i-1][j].price+mat[i-1][j].y,  mat[i][j-1].price+mat[i][j-1].x,
					mat[i-1][j].price2+mat[i-1][j].y,  mat[i][j-1].price2+mat[i][j-1].x};
			Arrays.sort(t);
			int price2 = t[1];
			if (t[0] == t[1]) price2 = t[2];
			if (t[0] == t[1] && t[1] == t[2]) price2 = t[3];
			return price2;
		}
		public void getBestPrice(){
			// n rows, m columns
			int n = mat.length, m = mat[0].length;
			mat[0][0].price = 0;
			for (int i=1; i<n; i++){// first column
				mat[i][0].price = mat[i-1][0].y+  mat[i-1][0].price;
				mat[i][0].price2 = mat[i][0].price;
				mat[i][0].numOfPaths = 1;
			}
			for (int j=1; j<m; j++){// first row
				mat[0][j].price = mat[0][j-1].price +  mat[0][j-1].x;
				mat[0][j].price2 = mat[0][j].price;
				mat[0][j].numOfPaths = 1;
			}
			for (int i=1; i<n; i++){
				for (int j=1; j<m; j++){
					int a = mat[i-1][j].price+mat[i-1][j].y;
					int b = mat[i][j-1].price+mat[i][j-1].x;
					mat[i][j].price2 = getBestPrice2ij(i, j);
					if (a<b){
						mat[i][j].price = a;
						mat[i][j].numOfPaths = mat[i-1][j].numOfPaths;				
					}
					else if (a>b) {
						mat[i][j].price = b;
						mat[i][j].numOfPaths = mat[i][j-1].numOfPaths;
					}
					else{//x==y
						mat[i][j].price = a;
						mat[i][j].numOfPaths = mat[i][j-1].numOfPaths+mat[i-1][j].numOfPaths;
					}
				}
			}
			numOfPaths = mat[n-1][m-1].numOfPaths;
			cheapestPrice = mat[n-1][m-1].price;
			cheapestPrice2 = mat[n-1][m-1].price2;
		}
		String getOneCheapestPath(){
			String ans = "";
			int i = mat.length-1, j = mat[0].length-1;
			while(i>0 && j>0){
				int a = mat[i-1][j].price+mat[i-1][j].y;
				int b = mat[i][j-1].price+mat[i][j-1].x;
				if (a < b){
					ans = "1" + ans;
					i--;
				}
				else{//a>b
					ans = "0" + ans;
					j--;
				}
			}
			if (i==0){
				while (j>0){
					ans = "0" + ans;
					j--;
				}
			}
			else {//j==0
				while (i>0){
					ans = "1" + ans;
					i--;
				}
			}
			return ans;
		}
		//////
		//calculate all the cheapest pats
		public  void AllPathsRecurs(int teta){
			if (numOfPaths<=teta){
				ArrayList<String> paths = new  ArrayList<String>(numOfPaths);
				buildPaths(new String(), mat.length-1, mat[0].length-1, paths);
				System.out.println(paths);
			}
		}
		public void buildPaths(String path, int i, int j, ArrayList<String> paths){
			if (i>0 && j>0){
				int a = mat[i-1][j].price+mat[i-1][j].y;
				int b = mat[i][j-1].price+mat[i][j-1].x;
				if (a < b){
					buildPaths("1"+path, i-1, j, paths);
				}
				else if(a > b){
					buildPaths("0"+path, i, j-1, paths);
				}
				else{//a==b
					buildPaths("1"+path, i-1, j, paths);
					buildPaths("0" + new String(path), i, j-1, paths);
				}
			}
			else if (i==0 && j==0){
				paths.add(path);
			}
			else if (i==0){
				String t = new String();
				for(int k=0; k<j; k++) t = t +  "0";
				paths.add(t + path);
			}
			else if (j==0){
				String t = new String();
				for(int k=0; k<i; k++) t = t +  "1";
				paths.add(t + path);
			}
		}


		//////
		// Print matrix of nodes
		public void printNodes(){
			System.out.println("\nmatrix of nodes in right direction\n");
			for (int i=0; i<mat.length; i++){
				for (int j=0; j<mat[0].length; j++){
					System.out.print(mat[i][j]+" ");
				}
				System.out.println();
			}
			System.out.println();
		}
		// Print matrix of prices
		public void printPrices(){
			System.out.println("\nmatrix of prices in right direction\n");
			for (int i=0; i<mat.length; i++){
				for (int j=0; j<mat[0].length; j++){
					System.out.print(mat[i][j].price+" ");
				}
				System.out.println();
			}
		}
		// Print matrix of prices
		public void printPrices2(){
			System.out.println("\nmatrix of prices2 in right direction\n");
			for (int i=0; i<mat.length; i++){
				for (int j=0; j<mat[0].length; j++){
					System.out.print(mat[i][j].price2+" ");
				}
				System.out.println();
			}
		}
		// Print matrix of paths number
		public void printNumberOfPaths(){
			System.out.println("\nthe matrix of numbers of the cheapest paths \n");
			for (int i=0; i<mat.length; i++){
				for (int j=0; j<mat[0].length; j++){
					System.out.print(mat[i][j].numOfPaths+" ");
				}
				System.out.println();
			}
		}
		public int getNumOfPaths(){return numOfPaths;}
		public int getCheapestPrice(){return cheapestPrice;}
		public int getCheapestPrice2(){return cheapestPrice2;}

		public static void main(String[] args) {
			AirPlane bp = new AirPlane(MyMatrix.initMatOfNodes2());
			bp.getBestPrice();
			bp.printNodes();
			System.out.println("the price of the cheapest path: "+bp.getCheapestPrice());
			System.out.println("the price2 of the cheapest path: "+bp.getCheapestPrice2());
			System.out.println("number of the cheapest paths: "+bp.getNumOfPaths());
			bp.printPrices();
			bp.printPrices2();
			bp.printNumberOfPaths();
			System.out.println("one path: " + bp.getOneCheapestPath());
			System.out.println("all paths: ");
			int teta = 10;
			bp.AllPathsRecurs(teta);
		}
	}



