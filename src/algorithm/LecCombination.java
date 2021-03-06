package algorithm;

public class LecCombination {
	static char[] src = {'\0', 'a', 'b', 'c'};
	static char[] temp = new char[4];
	static int[][] dp = new int[101][101];
	
	static void print() {
		System.out.print("[");
		for (char el: temp) System.out.print(el + " ");
		System.out.println("]");
	}
	
	static void swap(int a, int b) {
		char t = src[a];
		src[a] = src[b];
		src[b] = t;
	}
	
	static void combination(int n, int r) {
		if (r==0) {
			print();
			return;
		}
		
		if (n < r) return;
		
		temp[r] = src[n];
		combination(n-1, r-1); // (하나 뽑아놓은게)포함 
		combination(n-1, r); // (하나 뽑아놓은게)불포함 
	}
	
	static void combinationWithRepetition(int n, int r) {
		if (r==0) {
			print();
			return;
		}
		if (n==0) return;
		
		temp[r] = src[n];
		combinationWithRepetition(n, r-1); // (하나 뽑아놓은게)포함 - 그런데 여기서는 그걸 또 뽑을 수 있음 
		combinationWithRepetition(n-1, r); // (하나 뽑아놓은게)불포함 

	}
	
	static void combinationDpIter(int n, int r) { // 최종 결과값은 dp[n][r]에 저장 
		for (int i = 0; i <= n; i++) {
			int jLen = r; // 최대한 뽑을 수 있는 갯수 
			if (i < r) jLen = i; // 만약 n보다 r이 더 큰 경우라면 r을 n값(여기서는 i값)으로 조정해 줘야함. 
			for (int j = 0; j <= jLen; j++) { // n이 고정되어 있을때, j값을 늘려가면서 필요한 값을 하나씩 채워나감 
				if (j == 0 || i == j) dp[i][j] = 1;
				else dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
			}
		}
	}
	
	static int combinationCount(int n, int r) {
		if (r==0 || n==r) return 1;
		return combinationCount(n-1, r-1) + combinationCount(n-1, r);
	}
	
	static int combinationCountDp(int n, int r) {
		if (r==0 || n==r) dp[n][r] = 1;
		else {
			if (dp[n][r] == 0) {
				dp[n][r] = combinationCountDp(n-1, r-1) + combinationCountDp(n-1, r);
			}
		}
		return dp[n][r];
	}
	
	public static void main(String args[]) {
		combination(3, 2);
		System.out.println("====================");
		
		System.out.println(combinationCount(3, 2));
		System.out.println("====================");
		
		System.out.println(combinationCountDp(3, 2));
		System.out.println("====================");                                              
		
		combinationWithRepetition(3, 2);
		
		// 2중 for 문을 통해 알아본 순열과 조합 - 물론 아래의 것들은 r==2인 경우만 커버함 
		int n = 10; // 임의의 수 
		
		// 순열
		for (int i=1; i<=n; i++) {
			for (int j=1; j<=n; j++) {
				if (i!=j) {
					// void 
				}
			}
		}
		
		// 중복순열
		for (int i=1; i<n; i++) {
			for (int j=1; j<=n; j++) {
				// void
			}
		}
		
		// 조합
		for (int i=1; i<=n; i++) {
			for (int j=i+1; j<=n; j++) {
				// void
			}
		}
		
		// 중복조합
		for (int i=1; i<=n; i++) {
			for (int j=i; j<=n; j++) {
				// void 
			}
		}
		
	}
}
