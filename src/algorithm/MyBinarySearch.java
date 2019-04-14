package algorithm;

public class MyBinarySearch {
	
	int binarySearch(int[] a, int x) {
		int low = 0;
		int high = a.length - 1;
		int mid;
		
		while(low <= high) { // 바이너리 서치는 하나의 요소를 찝어내는 것이기 떄문에 <=까지 간다 
			mid = (low + high) / 2;
			if (a[mid] < x) {
				low = mid + 1;
			} else if (a[mid] > x) {
				high = mid - 1;
			} else {
				return mid;
			}
		}
		return -1; // 오류 
	}
	
	int binarySearchRecursive(int[] a, int x, int low, int high) {
		if (low > high) return -1;
		int mid = (low + high) / 2;
		if (a[mid] < x) {
			return binarySearchRecursive(a, x, mid + 1, high);
		} else if (a[mid] > x) {
			return binarySearchRecursive(a, x, low, mid - 1);
		} else {
			return mid;
		}
	}
	
	public static void main(String args[]) {}
}
