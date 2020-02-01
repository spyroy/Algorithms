package PowerAndFibboancci;

public class LogNPowerAndFibi {
	public static int PowerLoop(int x, int n) {
		int result = 1;
		while (n != 0) {
			if (n % 2 == 1) {
				result = result * x;
			}
			x = x * x;
			n = n / 2;
		}
		return result;
	}

	public static int PowerRecursion(int x, int n) {
		if (n == 0)
			return 1;
		if (n % 2 == 0)
			return PowerRecursion(x * x, n / 2);
		return x * PowerRecursion(x * x, (n - 1) / 2);
	}

	public static int MatrixFibi(int n) {
		int ans[][] = { { 1, 1 }, { 1, 0 } };
		int A[][] = { { 1, 1 }, { 1, 0 } };
		n -= 2;
		while (n != 0) {
			if(n%2 == 1)
				ans = multiplyMatrices(ans, A,2,2,2);
			A = multiplyMatrices(A,A,2,2,2);
			n = n/2;
		}
		return ans[0][0];
	}

	public static int[][] multiplyMatrices(int[][] firstMatrix, int[][] secondMatrix, int r1, int c1, int c2) {
		int[][] product = new int[r1][c2];
		for (int i = 0; i < r1; i++) {
			for (int j = 0; j < c2; j++) {
				for (int k = 0; k < c1; k++) {
					product[i][j] += firstMatrix[i][k] * secondMatrix[k][j];
				}
			}
		}
		return product;
	}


	public static void main(String[] args) {
		System.out.println(PowerRecursion(2, 5));
		System.out.println(PowerLoop(3, 7));
		System.out.println(MatrixFibi(6));
	}

}
