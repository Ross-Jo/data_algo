package algorithm;

/*
 * [삽입 정렬의 특징]
 * - 삽입정렬의 경우 교환횟수 > 비교횟수
 *   - 이동 및 비교횟수[역순의 경우] : N*(N-1)/2
 *     cf. 선택정렬은[역순의 경우] 비교만 N*(N-1)/2이고 추가적으로 N번의 교환이 있음 
 * - 삽입 정렬의 경우 레코드가 큰 경우 부적절(이유 : 많은 교환)
 * - 삽입 정렬은 입력자료에 굉장히 민감(자료가 어느정도 정렬됐는지에 따라 성능이 많이 다름. 역순인 경우 최악) 
 * - 삽입 정렬은 안정정렬이다. 
 */

public class MyInsertionSort {
	
	static void insertionSort(int[] arr) {
		int n = arr.length;
		int exchanges = 0;
		for (int i=n-1; i>0; i--) { // sentinel 배치 - 삽입할 곳을 찾을 때, 벗어남 방지를 위함 
			if (arr[i] < arr[i-1]) {
				int tmp = arr[i];
				arr[i] = arr[i-1];
				arr[i-1] = tmp;
				exchanges++;
			}
		}
		if (exchanges == 0) return; // 이미 정렬이 되어 있다는 소리 
		
		for (int i=2; i<n; i++) {
			int v = arr[i];
			int j = i;
			while(v < arr[j-1]) {
				arr[j] = arr[j-1];
				j--;
			}
			arr[j] = v;
		}
	}
	
	public static void main(String args[]) {
	}
}
