package algorithm;

public class LecDpKnapSack {
	static int MAXN = 101;
	static int MAXW = 1001;
	static int[] jw = { 0, 5, 4, 6, 3 }; // 보석의 무게
	static int[] jp = { 0, 10, 40, 30, 50 }; // 보석의 가격

	static int N = 4, W = 10;
	static int[] dp1 = new int[MAXW]; // 1차원 dp 

	static void knapSack1() { // 보석의 종류별로 그 갯수가 무한대인 경우 
		for (int i = 1; i <= W; i++) { // 가방에 담을 수 있는 무게가 1차적인 기준 
			for (int j = 1; j <= N; j++) { // 그 다음이 보석의 종류 
				if (i >= jw[j]) {
					if (dp1[i] < dp1[i - jw[j]] + jp[j]) { // 가치가 더 높은 경우 갱신 
						dp1[i] = dp1[i - jw[j]] + jp[j];
					}
				}
			}
		}
	}

	static int[][] dp2 = new int[MAXW][MAXW]; // 2차원 dp 
	static void knapSack2() { // 보석의 갯수가 제한되어 있는 경우 
		for (int i = 1; i <= N; i++) { // 이번에는 보석의 종류가 우선한다. 
			for (int j = jw[i]; j <= W; j++) { // i번째 보석의 무게부터 시작
				if (dp2[i - 1][j] > dp2[i - 1][j - jw[i]] + jp[i]) // i번째 보석을 담지 않은 경우보다, 담은 경우가 이득이면 갱신하고 그렇지 않으면 그대로. 
					dp2[i][j] = dp2[i - 1][j];
				else dp2[i][j] = dp2[i - 1][j - jw[i]] + jp[i];
			}
		}
	}
	
	public static void main(String[] args) {

	}
}
