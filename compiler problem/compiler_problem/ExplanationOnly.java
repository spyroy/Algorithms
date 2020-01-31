package compiler_problem;

public class ExplanationOnly {
	/**
	 * every software on the compiler consists from length (li) and probability of use(pi)
	 * to reach a specific software the compiler need to go through all previous softwares 
	 * we need to arrange the softwares so the average time will be as low as possible
	 * average time is T = l1p1 + (l1+l2)p2 +...+(l1+...ln)pn
	 * assuming all probabilities are equal in that case
	 * T = (l1 + (l1+l2) + ... + (l1+...+ln)P
	 * we got the secretary problem which we solved and we need to arrange
	 * in ascending order.
	 * assuming all lengths are equal in that case
	 * T = L(p1+2p2+...+npn)
	 * so we to arrange in descending order so the biggest probability will get the smallest coefficient.
	 * now we look on the general case:
	 * for example: L = p1+2p2+3p3 - L' = p1+2p3+3p2 -> L-L' = -p2+p3 > 0 -> L>L'
	 * if p3>p2 we get a better solution if we swap them, so when do we need to swap?
	 * in general case swaping i with i+1: T = l1p1 + (l1+...+l[i-1]+li)pi +...+(l1+...li+l[i+1])p[i+1] + ... +(l1+...ln)pn
	 * , T' = l1p1 + (l1+...+l[i-1]+l[i+1])p[i+1] +...+(l1+...l[i+1+li)pi + ... +(l1+...ln)pn
	 * we subtract and get: T - T' = p[i+1]li - pil[i+1]
	 * we check when T' < T and we need to swap
	 * T-T' > 0 -> p[i+1]li - pil[i+1] > 0 -> p[i+1]li > pil[i+1] -> p[i+1]/l[i+1] > pi/li
	 * so only if this relation is true we need to swap adjacent elements.
	 * 
	 * complexity: O(n) + (nlogn)  = O(nlogn), calculate the series pi/li + sort.
	 *
	 */
	
	public static void main(String[] args) {
		int l1 = 8, p1 = 3;
		int l2 = 11, p2 = 2;
		int l3 = 7, p3 = 7;
		
		int[] ans = {l1,p1,l1,l2,p2,l1,l2,l3,p3};
		if(ans[1]/ans[0] < ans[4]/ans[3]) {
			ans[0] = l2;
			ans[1] = p2;
			ans[3] = l1;
			ans[4] = p1;
		}
		if(ans[4]/ans[3] < ans[8]/ans[7]) {
			ans[3] = l3;
			ans[4] = p3;
			ans[6] = l3;
			ans[7] = l2;
			ans[8] = p2;
		}
		int result = ans[1]*ans[0];
		result += (ans[2]+ans[3])*ans[4];
		result += (ans[5]+ans[6]+ans[7])*ans[8];
		System.out.println(result);
	}
}
