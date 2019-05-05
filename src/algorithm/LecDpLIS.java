package algorithm;

public class LecDpLIS {
	static final int NMAX = 100;
	static final int NUMMAX = 10001;
	static int[] src = {8, 2, 4, 3, 6, 11, 7, 10, 14, 5};
	static int n;
	
	static int[] dp = new int[NMAX];
	static int[] p = new int[NUMMAX];
	
	static int dpCnt;

	static int getIndex(int num) { // 나보다 큰 것 중 가장 작은수와 바꾸기
		if (dpCnt == 0 || dp[0] > num) return 0; // 제일 앞의 인덱스거나, 제일 앞에 있는 수가, 현재 다루는 수보다 크다면 제일 앞으로 집어넣음
		else if (dp[dpCnt - 1] < num) return dpCnt; // dp[dpCnt - 1] < num 의 의미는 앞에 쓰여진 수들보다 num이 크다는 의미이기 때문에 가장 뒷자리에 num을 집어 넣는다. 
		else { // num이 들어갈 위치에 대한 이분 탐색을 실시한다. 
			int s = 0;
			int e = dpCnt - 1;
			while (s <= e) {
				int c = (s + e) / 2;
				if (dp[c] < num) s = c + 1;
				else if (dp[c] > num) e = c - 1;
				else return c; // 반환하는 것이 인덱스임에 유의하자 
			}
			if (dp[e] < num) e++; // 나보다 큰 것 중 가장 작은수와 바꾸기 위함 
			return e;
		}
	}
	
	static void pathPrint(int idx) { // 앞에서부터 path를 출력하기 위함
		if (idx == -1) return;
		pathPrint(p[idx]);
		System.out.print(idx + " ");
	}
	
	public static void main(String[] args) {
		n = 10;
		int insertIdx;
		for (int i = 0; i < n; i++) {
			insertIdx = getIndex(src[i]); // 해당 숫자가 dp테이블에서 들어갈 위치를 찾고
			dp[insertIdx] = src[i]; // 삽입 
			if (insertIdx == dpCnt) dpCnt++;

			p[src[i]] = insertIdx == 0 ? -1 : dp[insertIdx - 1];
		}
		pathPrint(dp[dpCnt - 1]);
	}
}
