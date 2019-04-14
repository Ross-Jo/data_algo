package algorithm;

/*
 * [병합정렬]
 * - 시간복잡도: O(NlogN)
 * - 성능 향상 방법: 
 *   1. 작은 사이즈의 부분 배열에 대하여 재귀 호출은 너무 많은 오버헤드를 가지니, 작은 부분 배열에 대해서는 '삽입'정렬을 사용할 것
 *   2. 이미 기존 배열이 이미 정렬되어 있는지 확인한다. 
 *   3. arr와 aux의 역할을 바뀌서 병합과정에서 arr의 aux로의 복사가 매번 일어나지 않게한다. (mergeSort호출전 arr -> aux로의 복사 1번만 발생) 
 */

public class MyMergeSort {
	
	static void mergeSort(int[] arr, int[] aux, int lo, int hi) {
		if(hi<=lo) return;
		int mid = lo + (hi-lo)/2;
		mergeSort(arr, aux, lo, mid);
		mergeSort(arr, aux, mid+1, hi);
		merge(arr, aux, lo, mid, hi);
	}
	
	static void merge(int[] arr, int[] aux, int lo, int mid, int hi) {
		for (int k=lo; k<=hi; k++) aux[k] = arr[k];
		int i = lo, j = mid+1;
		for (int k=lo; k<=hi; k++) {
			if (i>mid) arr[k] = aux[j++];
			else if (j>hi) arr[k] = aux[i++];
			else if (aux[j]<aux[i]) arr[k] = aux[j++];
			else arr[k] = aux[i++];
		}
	}
	
	// ================================================================
	static int[] src = {2,3,4,1,40,20,7,6,8,9}; // 머지를 하고 싶은 요소
	static int[] temp = new int[src.length]; // 부분적 머지 결과를 임시적으로 저장할 배열 
	
	static void mergeSort_Lec(int lo, int hi) {
		if (lo < hi) { // 머지 소트를 위해서는 적어도 2개의 요소가 필요함 
			int mid = lo + (hi-lo)/2;
			mergeSort_Lec(lo, mid);
			mergeSort_Lec(mid+1, hi);
			
			int p = lo; int q = mid + 1; int idx = lo;
			while(p<=mid || q<=hi) {
				if(q>hi || (p<=mid && src[p] < src[q])) temp[idx++] = src[p++];
				else temp[idx++] = src[q++];
			}
			for (int i=lo; i<=hi; i++) src[i] = temp[i];
		}
	}
	
	public static void main(String args[]) {
		
	}
}
