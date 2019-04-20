package algorithm;

public class LecFibonacci {
	static final int MAX = 101;
	int[] dpMemo1 = new int[MAX];
	int[] dpMemo2 = new int[MAX];
	int[] dpMemo3 = new int[3];
	
	int fibo(int n) {
		if (n < 2) return n;
		return fibo(n - 1) + fibo(n - 2);
	}

	int fibo_rec_dp(int n) {
		if (n < 2) dpMemo1[n] = n;
		else {
			if (dpMemo1[n] == 0) dpMemo1[n] = fibo_rec_dp(n - 1) + fibo_rec_dp(n - 2);
		}
		return dpMemo1[n];
	}

	int fibo_iter_dp(int n) {
		dpMemo2[1] = 1;
		if (n < 2) return dpMemo2[n];
		for (int i = 2; i <= n; i++) dpMemo2[i] = dpMemo2[i - 1] + dpMemo2[i - 2];
		return dpMemo2[n];
	}

	int fibo_iter_dp2(int n) { // 메모리 사용을 축소시킨 점을 유의해서 볼 것
		dpMemo3[1] = 1;
		if (n < 2) return dpMemo3[n];
		for (int i = 2; i <= n; i++) dpMemo3[i % 3] = dpMemo3[(i - 1) % 3] + dpMemo3[(i - 2) % 3];
		return dpMemo3[n % 3];
	}

	public static void main(String args[]) {

	}
}
