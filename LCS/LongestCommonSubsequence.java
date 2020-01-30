package LCS;
/**
 * this class represent the LCS problem - we need to find 
 * the longest common subsequence between two words
 * to do we will build a matrix with first colomn and row with - '0'
 * for each cell in the matrix if the letters in this cell index in the two
 * words is equal we take the element from the main diagonal + 1.
 * else we will take the maximum between left cell to upper cell.
 * 
 * complexity - O(m*n) + O(m+n), finding length + finding word.
 * 
 * proof:
 * we mark f(X,Y) as LCS between X and Y 
 * if the Strings contains only one letter if its the same letter
 * so f(X,Y) = 1, else f(X,Y) = 0
 * when we add letters to the words so if those letters are 
 * equal so f(Xl,Yl) = f(X,Y)+1
 * else f(Xl,Yl) = max(f(Xl,Y),f(X,Yl')
 * and this is the recursive formula.
 * 
 * @author spyro
 *
 */
public class LongestCommonSubsequence {
	/**
	 * build the matrix wich contains all possible subsequences
	 * @param X
	 * @param Y
	 * @return
	 */
	public static int[][] BuildMatrix(String X, String Y){
		int row = X.length()+1;
		int col = Y.length()+1;
		int mat[][] = new int[row][col];
		int i = 0;
		int j = 0;
		for(i = 0; i < row; i++) {
			mat[i][0] = 0;
		}
		for(j=0; j < col; j++) {
			mat[0][j] = 0;
		}
		for(i=1; i < row; i++) {
			for(j=1; j < col; j++) {
				if(X.charAt(i-1) == Y.charAt(j-1))
					mat[i][j] = mat[i-1][j-1] +1;
				else
					mat[i][j] = Math.max(mat[i-1][j], mat[i][j-1]);
			}
		}
		int length = mat[row-1][col-1];
		return mat;
	}
	
	/**
	 * turn back from the bottom left to the way we came from
	 * @param X
	 * @param Y
	 * @return
	 */
	public static String MaxSequence(String X, String Y) {
		int mat[][] = BuildMatrix(X,Y);
		int row = mat.length;
		int col = mat[0].length;
		int seqLength = mat[row-1][col-1];
		String result = "";
		int i =row-1;
		int j = col-1;
		int count = seqLength-1;
		
		while(count >= 0) {
			if(X.charAt(i-1) == Y.charAt(j-1)) {
				result = X.charAt(i-1) + result;
				i--;
				j--;
				count--;
			}
			else if(mat[i][j] == mat[i][j-1])
				j--;
			else
				i--;
		}
		return result;
	}
	
	public static void main(String[] args) {
		String X = "abada";
		String Y = "adada";
		String s = MaxSequence(X,Y);
		System.out.println(s.toString());
	}

}
