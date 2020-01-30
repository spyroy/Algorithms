package FindMinMaxInArray;

/**
 * in this class we find the Maximum and Minimum elements in array
 * 
 * @author spyro
 *
 */
public class FindMax {
	/**
	 * complexity - O(n). proof of the algorithm: in induction on numbers of
	 * elements in the array, base: n = 1 and indeed arr[0] = max. assuming the
	 * algorithm works for - n we need to prove for - n + 1. first we mark -
	 * max(arr[0] ... arr[n]) = maxn now we need to fid the maximum between
	 * (maxn,arr[n+1). by the assumption maxn is the max between - n elements divide
	 * to two options: 1) maxn >= arr[n+1] in that case maxn is the maximum between
	 * all elements. 2) maxn < arr[n+1] in that case its obvious that arr[n+1] is
	 * the maximum (by Transitive relation).
	 * 
	 * @param arr
	 * @return
	 */
	public static int GetMax(int arr[]) {
		int max = arr[0];
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] > max)
				max = arr[i];
		}
		return max;
	}

	/**
	 * to find the minimum and the maximum in array with least comparisons we need
	 * to compare between two adjacent elements the bigger one is candidate for
	 * maximum and the other for minimum. number of comparisons - 3n/2 complexity -
	 * O(n).
	 * 
	 * @param arr
	 * @return
	 */
	public static int[] GetMinMax(int arr[]) {
		int max = arr[0];// we choose randomly who is bigger first element or second
		int min = arr[1];
		int comparisons = 1;
		if (arr[0] < arr[1]) {// making sure our guess was right
			min = arr[0];// if not than switch
			max = arr[1];
		}
		for (int i = 2; i < arr.length; i += 2) {// compare between every pair
			comparisons++;
			if ((i + 1) < arr.length && arr[i] <= arr[i + 1]) {
				comparisons = comparisons + 2;
				if (arr[i] < min)
					min = arr[i];
				if (arr[i + 1] > max)
					max = arr[i + 1];
			} else if ((i + 1) < arr.length) {
				comparisons = comparisons + 2;
				if (arr[i + 1] < min)
					min = arr[i + 1];
				if ((arr[i]) > max)
					max = arr[i];
			}
		}
		if (arr.length % 2 != 0) {// if size of array is odd so we need to check the last element
			comparisons++;
			if (arr[arr.length - 1] > max)
				max = arr[arr.length - 1];
			else {
				comparisons++;
				if (arr[arr.length - 1] < min)
					min = arr[arr.length - 1];
			}
		}
		int ans[] = { min, max, comparisons };
		return ans;

	}

	/**
	 * we need to find the biggest two elements in the array so to every element we
	 * will attach stack, at first all stacks are empty. for each pair in the array
	 * the maximum between the two will stay (candidate for max1) and the minimum
	 * will go to the stack that attached to his pair(candidate for max2). at the
	 * end of this process all elements in the new array will be candidates for max1
	 * and in the stacks will be all elements candidates for max2. we go through the
	 * new array on pairs, for each pair we choose the maximum and other will go to
	 * the stack attached to his pair. we continue the process until we have last
	 * pair, from the pair we take max1 and the other to max1 stack, from the stack
	 * we choose max2.
	 *
	 * because we split the array each time to two equal parts number of entrance to
	 * stack is log(n).
	 *
	 * number of comparisons is n/2 + n/4 + ... + 1 = n-1 + log(n)
	 *
	 * @return
	 */
	static int comparisons = 0;
	public static int Get2Max(Node arr[], int low, int high) {
		int index;
		int middle;
		int i;
		int j;
		if (low < high) {
			index = 0;
			middle = (low + high)/2;
			i = Get2Max(arr,low,middle);
			j = Get2Max(arr,middle+1,high);
			comparisons++;
			
			if(arr[i].num > arr[j].num) {
				arr[i].st.push(arr[j].num);
				index = i;
			}
			else {
				arr[j].st.push(arr[i].num);
				index = j;
			}
			return index;
		}
		else {
			return low;
		}
	}
	
	public static void Get2Max(int[]a) {
		int index;
		int max2;
		Node[] arr = new Node[a.length];
		
		for(int i = 0; i < a.length; i++) {
			arr[i] = new Node(a[i]);
		}
		index = Get2Max(arr,0,a.length-1);
		System.out.println("index of max1 = "+index+"\nmax1 = "+arr[index].num);
		max2 = arr[index].st.pop();
		
		while(!arr[index].st.isEmpty()) {
			int tmp = arr[index].st.pop();
			comparisons++;
			if(tmp > max2) {
				max2 = tmp;
			}
		}
		System.out.println("max2 = "+max2);
		System.out.println("number of comparisons is: " + comparisons);
	}

	public static void main(String[] args) {
		int arr[] = { 6, 345, 53, 7, 453, 2, 34, 56, 7, 34, 1 };
		System.out.println(GetMax(arr));
		int ans[] = GetMinMax(arr);
		System.out.println("minimum is: " + ans[0] + " maximum is  " + ans[1] + " number of comparisons: " + ans[2]);
		Get2Max(arr);
	}

}
