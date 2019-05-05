package algorithm;

public class LecKnapSackDFS {
	static final int INF = 987654321;
	
	static int[] p = {0, 40, 30, 50, 10};
	static int[] w = {0, 2, 5, 10, 5};
	static int N = 4;
	static int W = 16;
	static int maxprofit;
	
	static boolean promising(int k, int profit, int weight) {
		int i, totalweight;
		float bound;

		if (weight >= W) return false;
		else {
			i = k + 1;
			bound = profit;
			totalweight = weight;
			while ((i <= N) && (totalweight + w[i]) <= W) { // 담을 수 있는 최대 이익을 계산하기 위함 
				totalweight = totalweight + w[i];
				bound = bound + p[i];
				i++;
			}
		}
		if (i <= N) bound = bound + (W - totalweight) * (p[i] / w[i]); // fractional 한 이익까지 모두 계산 
		if (bound > maxprofit) return true; // 현재까지의 최고 이익보다 더 많은 이익을 가져다 줄 state인지 판정하고 맞다면 true을 아니라면 false을 반환한다. 
		else return false;
	}
	
	static void knapsack(int k, int profit, int weight) {
		if (weight <= W && profit > maxprofit) maxprofit = profit;
		
		if (promising(k, profit, weight)) { // 진입하려고 하는 state가 유망한 경우에만 진입한다. 
			k++;
			knapsack(k, profit + p[k], weight + w[k]); // k번째 물건을 선택하는 경우 
			knapsack(k, profit, weight); // k번째 물건을 선택하지 않는 경우 
		}
	}

	public static void main(String[] args) {
		knapsack(0, 0, 0);
	}
}
