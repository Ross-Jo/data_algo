package algorithm;

// 3-way partition 방법 중복 키가 많은 경우 
// https://algs4.cs.princeton.edu/23quicksort/

/*
 * [퀵소트]
 * - 시간복잡도 : O(NlogN) - 베스트/평균, O(N^2) - 최악
 * - 성능 향상 방법
 *   - median of three (3개의 값중 중간값)
 *   - median of medians (9개의 값중 중간값)
 *   - quick and insertion sort의 하이브리드 
 *   - random shuffling (shuffle 방법 ex. knuth shuffle)
 */

public class MyQuickSort {
	static void threeWayquickSort(int[] arr, int lo, int hi) {
		if (hi <= lo) return;
		int lt = lo, gt = hi;
		int v = arr[lo]; // 피벗 
		int i = lo + 1;
		while (i<=gt) {
			int cmp = arr[i] - v;
			if (cmp<0) exch(arr, lt++, i++); // arr[i] < v
			else if(cmp>0) exch(arr, i, gt--); // arr[i] > v
			else i++;
		}
		threeWayquickSort(arr, lo, lt-1);
		threeWayquickSort(arr, gt+1, hi);
	}
	
	static void quickSort(int[] arr, int lo, int hi) {
		if (hi <= lo) return;
		int j = partition1(arr, lo, hi);
		quickSort(arr, lo, j-1);
		quickSort(arr, j+1, hi);
	}
	
	static int partition1(int[] arr, int lo, int hi) {
		int i = lo;
		int j = hi + 1;
		int v = arr[lo];
		while(true) {
			while(arr[++i] < v) {
				if (i == hi) break;
			}
			while(v < arr[--j]) {
				if (j == lo) break;
			}
			if (i>=j) break;
			exch(arr, i, j);
		}
		exch(arr, lo, j);
		return j;
	}
	
	// N-1에 양의 무한대 값의 sentinal이 박혀있다고 가정하고 0~N-2의 값들을 정렬하려고 하는 경우 => 구현이 조금 간편해짐 && if 문의 갯수를 줄일 수 있음 
	static int partition2(int[] arr, int lo, int hi) {
		int i = lo;
		int j = hi + 1;
		int v = arr[lo];
		do {
			do i++; while(arr[i] < v);
			do j--; while(v < arr[j]);
			if (i<j) exch(arr, i, j);
		} while (i<j);
		exch(arr, lo, j);
		return j;
	}
	
	static void exch(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	
	public static void main(String args[]) {
		
	}
}
