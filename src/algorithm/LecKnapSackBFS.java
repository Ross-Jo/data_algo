package algorithm;

public class LecKnapSackBFS {
	static int[] p = { 0, 40, 30, 50, 10 };
	static int[] w = { 0, 2, 5, 10, 5 };
	static int N = 4, W = 16, maxprofit;
	
	static class Node {
		int level, profit, weight;
		Node(int l, int p, int w) {
			this.level = l; this.profit = p; this.weight = w;
		}
	}
	
	static boolean promising(Node u) {
		int i, totalweight;
		float bound;

		if (u.weight >= W) return false;
		else {
			i = u.level + 1;
			bound = u.profit;
			totalweight = u.weight;
			while ((i <= N) && (totalweight + w[i] <= W)) {
				totalweight = totalweight + w[i];
				bound = bound + p[i];
				i++;
			}
		}
		if (i <= N) bound = bound + (W - totalweight) * (p[i] / w[i]);
		if (bound > maxprofit) return true;
		else return false;
	}
	
	static void knapsack() {
		Node[] q = new Node[100];
		int front = 0, rear = 0;
		Node u, v;
		v = new Node(0, 0, 0);

		q[rear++] = v;
		while (front != rear) {
			v = q[front++];
			u = new Node(v.level + 1, v.profit + p[v.level + 1], v.weight + w[v.level + 1]); // v.level + 1을 선택을 하는 경우

			if (u.weight <= W && u.profit > maxprofit) maxprofit = u.profit;
			if (promising(u)) q[rear++] = u; // BFS 방식에서는 DFS 방식과 다르게 선택을 하던, 안하던 그때마다 유망성 검사를 한다. (dfs는 파고 들어가는 방식이기 떄문)
			u.profit = v.profit; // v.level + 1을 선택을 하지 않는 경우
			u.weight = v.weight;
			if (promising(u)) q[rear++] = u;
		}
	}
	
	public static void main(String[] args) {

	}
}
