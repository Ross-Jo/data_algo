package algorithm;

public class MyBubbleSort {
	
	static void bubbleSort(int[] arr) {
		int n = arr.length;
		int tmp = 0;
		for (int i=0; i<n; i++) {
			for (int j=1; j<(n-i); j++) {
				if (arr[j-1] > arr[j]) {
					tmp = arr[j-1];
					arr[j-1] = arr[j];
					arr[j] = tmp;
				}
			}
		}
	}
	
	public static void main(String args[]) {
		
	}
}
