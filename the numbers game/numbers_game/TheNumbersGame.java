package numbers_game;

/**
 * in this game are two players in every turn a player must take a number from
 * an edges of array the player with the most score wins. the optimal solution
 * is to build a matrix that will present every situation in the game. to build
 * that matrix we take all elements from the array and put them in the main
 * diagonal, after that we start from the bottom right and the mat[i][j] =
 * max(game[i]-mat[i+1][j],game[j]-mat[i][j-1] after that we look at the upper
 * right corner and check how did we get there 
 * (same as befor - max(game[i]-mat[i+1][j],game[j]-mat[i][j-1])
 * and add that score to the player.
 * 
 * @author spyro
 *
 */
public class TheNumbersGame {

	// build help matrix to calculate all possible choices
	public static int[][] buildMatrix(int game[]) {
		int n = game.length;
		int[][] mat = new int[n][n];
		for (int i = 0; i < n; i++) {
			mat[i][i] = game[i];// main diagonal from the array
		}
		for (int i = n - 2; i >= 0; i--) {
			for (int j = i + 1; j < n; j++) {
				mat[i][j] = Math.max(game[i] - mat[i + 1][j], game[j] - mat[i][j - 1]);
			}
		}
		return mat;
	}

	public static void GameStrategy(int game[]) {
		int i = 0;
		int n = game.length;
		int j = n - 1;
		int first = 0;
		int second = 0;
		int firstSum = 0;
		int secondSum = 0;
		int[][] mat = buildMatrix(game);

		for (int k = 0; k < (n / 2); k++) {
			if (game[i] - mat[i + 1][j] > game[j] - mat[i][j - 1]) {// first choose
				firstSum += game[i];
				first = i++;
			} else {
				firstSum += game[j];
				first = j--;
			}
			if (i != j) {
				if (game[i] - mat[i + 1][j] > game[j] - mat[i][j - 1]) {// second choose
					secondSum += game[i];
					second = i++;
				} else {
					secondSum += game[j];
					second = j--;
				}
			} else { // second takes the last number
				second = j;
				secondSum += game[j];
			}
			System.out.println("first chose: " + (game[first]) + " second chose: " + (game[second]));
		}
		System.out.println("first score is: " + firstSum + " second score is: " + secondSum);
	}

	public static void main(String[] args) {
		int[] arr = { 5,8,4,6,5,8};
		GameStrategy(arr);
	}
}
