package Pizza_Problem;

/**
 * Eli and Beni ordered a pizza, Eli eats X times faster than Beni (X>1), we
 * need to divide the pizza to N parts, that Eli will eat as much as possible.
 * they need to finish the eating together that they won't reach last slice together.
 * solution:
 * lets assume the optimal division is N=X
 * so Eli will eat (N-1)/N slices, and Beni will eat 1/N
 * if we divide  N = X+1, than Eli wil eat (N/N+1), and Beni will eat 1/(N+1)
 * we need to peove that N/(N+1) > (N-1)/N
 * -> N(N+1) > (N-1)/N -> N^2 > (N-1)(N+1) -> N^2 > N^2 - 1
 * this inequality exists so it means that   N/(N+1) > (N-1)/N.
 * 
 * now lets assume there is better solution:
 * we divide the pizza to N=(X+1)*p+r, when p is number of rounds and r is 
 * the radix 2<r<X, for example Eli eats 7 times faster than Beni so we take 
 * p = 1 and r = 2 so we divide the pizza to 8*1+2 = 10,
 * Eli will eat seven slices while Beni eats 1 than each of them will take a 
 * slice from the two remain, so Eli ate 8/10 and Beni ate 2/10
 * and in general Eli eats (X*p + r-1)/{(X+1)*p + r} and Beni eats (p+1)/{(X+1)*p + r}
 * we need to prove that (X*p + r-1)/{(X+1)*p + r} < X/(X+1)
 * ->(X*p + r-1)/{(X+1)*p + r} < X/(X+1) -> (Xp+r-1)(X+1) < X(Xp+p+r)
 * -> X^2p + Xp + Xr + r - X - 1 < X^2p + Xp + Xr 
 * -> r-X-1 < 0 -> r < X+1
 * the radix is smaller from the division therefor the inequality exists
 * and optimal division is X/X+1.
 * 
 * @author spyro
 *
 */
public class PizzaProblem {
	public static boolean pizza(double X, int n) {
		int k = (int)X + 1;
		int p = n/(k);
		int r = n%(k) ;
		boolean ans = false;
		if(r != 1) {// forbiden state
			double t = (X*p+r-1)/((X+1)*p+r);
			System.out.println((X/(X+1)));
			if(t<(X/(X+1)))
				ans = true;// the formula is right
			else
				ans = false;// wrong formula
		}
		return ans;
	}
	
	public static void main(String[] args) {
		double X = 7;
		int n =10;
		System.out.println("is that formula works? " + pizza(X,n));
	}
	
}
