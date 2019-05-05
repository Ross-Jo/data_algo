package algorithm;

public class LecDpCoin {
	static final int MAXN = 101, MAXMONEY = 1001, MAX = 987654321;
	static int moneys[] = { 0, 1, 4, 6 };

	static int moneyN, money;
	static int[] dp = new int[MAXMONEY]; // 1차원 dp 

	// 해당 금액에 대해서 어떻게 하면 가장 적은 동전의 갯수를 반환할 수 있는가?
	void coin() {
		for (int i = 1; i <= money; i++) dp[i] = MAX;
		for (int i = 1; i <= money; i++) {
			for (int j = 1; j <= moneyN; j++) {
				if (i > moneys[j]) { // i(거슬러 받고자 하는 총 금액)이 현재 다루는 화폐의 단위(moneys[j])보다는 커야함. 
					if (dp[i] > dp[i - moneys[j]] + 1) // i - moneys[j]는 공간을 확보하는 개념. 새로운 화폐단위를 추가해서 거슬러 줄 수 있는 돈의 갯수가, 현재 dp[i]보다 작으면 (즉, 더 적은 갯수의 화폐들을 이용해서 돈을 거슬러 줄 수 있으면) 갱신. 
						dp[i] = dp[i - moneys[j]] + 1;
				}
			}
		}
	}

	public static void main(String[] args) {

	}
}
