package algorithm;

// 참고: https://www.nayuki.io/page/next-lexicographical-permutation-algorithm
// import java.util.Arrays;

public class MyNextPermutation {
	static int[] a = {5,4,3,2,1,0};
	static boolean next_permutation() { 
		// Find longest non-increasing suffix
		int i = a.length - 1;
		while (i > 0 && a[i - 1] >= a[i]) i -= 1;
		// Now i is the head index of the suffix
		
		// Are we at the last permutation already?
		if (i <= 0) return false;

		// Let array[i - 1] be the pivot
		// Find rightmost element that exceeds the pivot
		int j = a.length - 1;
		while (a[j] <= a[i - 1]) j -= 1;
		// Now the value array[j] will become the new pivot
		// Assertion: j >= i
		
		// Swap the pivot with j
		int temp = a[i - 1];
		a[i - 1] = a[j];
		a[j] = temp;

		// Reverse the suffix
		j = a.length - 1;
		while (i < j) {
			temp = a[i];
			a[i] = a[j];
			a[j] = temp;
			i += 1;
			j -= 1;
		}
		
		// Successfully computed the next permutation
		return true;
	}
	
	public static void main(String args[]) {
//		Arrays.sort(a);
		for (int i=0; i<1000; i++) {
			next_permutation();
			for (int k=0; k<a.length; k++) {
				System.out.print(a[k]+" ");
			}
			System.out.println();
		}
		
	}
}