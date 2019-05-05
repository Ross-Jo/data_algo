package algorithm;

// 참고 : http://hochulshin.com/longest-common-subsequence/

public class LecDpLCS {
	static int T;
	static int[][] dp;
	static String s1, s2;
	
	public static void main(String[] args) {
		s1 = "0" + "abcdefghijklmn"; // LCS에서는 초기값 때문에 이와 같이 전처리를 하는 방식이 편리하다. 
		s2 = "0" + "ace132k1n";
		int L1 = s1.length(), L2 = s2.length();
		dp = new int[L1][L2]; // 이 dp 테이블은 s1, s2의 부분 문자열들의 LCS의 길이를 나타낸다고 보면 된다. 

		for (int i = 1; i < L1; i++) {
			for (int j = 1; j < L2; j++) {
				if (s1.charAt(i) == s2.charAt(j)) dp[i][j] = dp[i - 1][j - 1] + 1; // 같은 글자인 경우, 좌상단 대각선 값에서 + 1.
				else dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]); // 다른 글자인 경우는 좌측값 혹은 상단값 중 큰 값을 채택한다. 
			}
		}
		
		System.out.println(dp[L1 - 1][L2 - 1]);
	}
}
