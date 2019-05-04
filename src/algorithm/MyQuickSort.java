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

	// ================================================================
	static int[] src = {2,3,4,1,40,20,7,6,8,9}; // 머지를 하고 싶은 요소
	
	static void quickSort_Lec_Hoare(int lo, int hi) {
		if (lo < hi) { // 정렬이기 때문에 요소가 적어도 2개는 있어야 한다. 
			int p = src[lo]; // 피벗 설정
			int l = lo;
			int r = hi;
			while(l < r) {
				while(p >= src[l] && l<hi) {l++;}
				while(p < src[r]) {r--;}
				if(l < r) {
					int t = src[r];
					src[r] = src[l];
					src[l] = t;
				}
			}
			int t = src[r];
			src[r] = src[lo];
			src[lo] = t;
			
			quickSort_Lec_Hoare(lo, r-1); // 정렬된 요소는 빼고 호출
			quickSort_Lec_Hoare(r+1, hi); // 역시 정렬된 요소는 빼고 호출
		}
	}
	
	static void quickSort_Lec_Hoare_Center(int lo, int hi) { // 위와는 피벗의 위치가 다른 점이 차이점임 
		if (lo < hi) {
			int p = src[lo + (hi-lo)/2]; // 피벗을 값을 이렇게 뽑아놔야 함. (lo + (hi-lo)/2) 값만 p에 저장해두고, 아래애서 src[p]를 이용해 값을 비교하면, 정렬이되면서 중간에 p값이 바뀌어 버리고 종국적으로 피벗값이 계속 바뀌기 때문에 정렬이 되지 않음.  
			int l = lo;
			int r = hi;
			while(l <= r) { // 피벗이 가운데에 끼어 있기 때문에 이런식(<=)으로 while문을 돌림. 그렇지 않으면 무한루프 
	                        // l, r의 역전이 일어나지 않기 때문 
				while(p > src[l]) {l++;}
				while(p < src[r]) {r--;}
				if(l <= r) { // l==r는 피벗에서 만나는 경우를 의미함
					         // 피벗 요소의 위치 바뀜이 일어남에 유의
					int t = src[r];
					src[r] = src[l];
					src[l] = t;
					l++;
					r--;
				}
			}
			quickSort_Lec_Hoare_Center(lo, l-1); // 피벗의 위치가 계속 바뀌기 때문에 이렇게 해 줘야함 
			quickSort_Lec_Hoare_Center(l, hi);
		}
	}
	
	static void quickSort_Lec_Lomuto(int lo, int hi) {
		if (lo < hi) {
			int p = src[hi];
			int i = lo-1;
			for (int j=lo; j<hi; j++) {
				if (p >= src[j]) {
					i++;
					
					int t = src[j];
					src[j] = src[i];
					src[i] = t;
					
				}
			}
			
			int t = src[i+1];
			src[i+1] = src[hi];
			src[hi] = t; 
			
			quickSort_Lec_Lomuto(lo, i);
			quickSort_Lec_Lomuto(i+2, hi);
		}
	}
	
	public static void main(String args[]) {
		quickSort_Lec_Lomuto(0, src.length-1);
	}
}
