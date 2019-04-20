package algorithm;

public class LecSubset {
	static final int MAX = 10;
	static int[] src = new int[MAX];
	static int[] a = new int[MAX];
	static int n, cnt, reCnt, key;
	
	static void print() {
		cnt++;
		
		int sum = 0;
		for (int i=1; i<=n; i++) {
			if(a[i]!=0) {
				sum += src[i];
			}
		}
		
		if (sum <= key) {
			reCnt++;
			System.out.print("{");
			for (int i=1; i<=n; i++) {
				if (a[i] != 0) {
					System.out.print(src[i]+ " ");
				}
			}
			System.out.println("}");
		}
		
	}
	
	static int getSum(int k) {
		int sum = 0;
		for (int i=1; i<=k; i++) {
			if(a[i] != 0) {
				sum += src[i];
			}
		}
		return sum;
	}
	
	static void subSet_backtracking_0(int k) {
		if (k==n) {
			print();
			return;
		}
		k++;
		
		a[k] = 0;
		subSet_backtracking_0(k);
		
		a[k] = 1;
		subSet_backtracking_0(k);
		
	}
	
	static void subSet_backtracking_1(int k) {
		if (getSum(k) > key) return;
		
		if (k==n) {
			print();
			return;
		}
		k++;
		
		a[k] = 0;
		subSet_backtracking_1(k);
		
		a[k] = 1;
		subSet_backtracking_1(k);
	}
	
	static void subSet_backtracking_2(int k, int sum) {
		if (sum > key) return;
		
		if (k==n) {
			reCnt++;
			return;
		}
		
		k++;
		subSet_backtracking_2(k, sum);
		subSet_backtracking_2(k, sum+src[k]);
	}
	
	static void subSet_backtracking_3(int k, int sum) {
		if (sum > key) return;
		
		if (sum == key) {
			reCnt++;
			return;
		}
		
		if (k==n) return;
		
		k++;
		subSet_backtracking_3(k, sum);
		subSet_backtracking_3(k, sum+src[k]);
	}
	
	static void subSet_backtracking_4(int k, int sum, int reSum) {
		if (sum > key) return;
		
		if (sum == key) {
			reCnt++;
			return;
		}
		
		if (sum+reSum < key) return;
		
		if (k==n) return;

		k++;
		subSet_backtracking_4(k, sum, reSum-src[k]);
		subSet_backtracking_4(k, sum+src[k], reSum - src[k]);
	}
	
	// 단점 : int 범위기 때문에, 판별 가능 원소가 int 범위내로 제한됨
	static void subSet_bit () {
		for (int i=0; i<(1<<n); i++) {
			for (int j=0; j<n; j++) {
				if((i&(1<<j)) != 0) {
					// do sth
				}
			}
		}
	}
	
	public static void main(String args[]) {
		n = 30;
		key = 1300;
		int totalSum = 0;
		
		for (int i=1; i<=n; i++) {
			src[i] = i;
			totalSum += src[i];
		}
		
	}
}
