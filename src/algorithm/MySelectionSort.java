package algorithm;

/*
 * [선택 정렬의 특징]
 * - 선택 정렬의 경우 비교횟수 > 교환횟수
 *   - 비교횟수 : n^2/2
 *   - 교환횟수 : N번 
 * - 안정정렬 아님 
 */

public class MySelectionSort {
	
	static void selectionSort(int[] arr) {
		int n = arr.length;
		for (int i=0; i<n; i++) {
			int min = i;
			for (int j=i+1; j<n; j++) {
				if(arr[j] < arr[min]) min = j;
			}
			int tmp = arr[i];
			arr[i] = arr[min];
			arr[min] = tmp;
		}
	}
	
	public static void main(String args[]) {
		
	}
}
