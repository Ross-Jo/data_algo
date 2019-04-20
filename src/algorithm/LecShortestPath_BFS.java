package algorithm;

public class LecShortestPath_BFS {
	static final int MAX = 101, INF = 987654321;
	int[][] g = new int[MAX][MAX];
	int[] dist = new int[MAX];
	int[] p = new int[MAX];
	int[] queue = new int[MAX * MAX]; int front, rear;
	int V, E;
	
	void init() {
		for (int i=1; i<=V; i++) {
			dist[i] = INF;
			p[i] = 0;
		}
	}
	
	void pathPrint(int idx) {
		if (idx == 0) return;
		pathPrint(p[idx]);
		System.out.print(idx + " ");
	}
	
	void bfs(int start, int end) {
		init();
		dist[start] = 0;
		queue[rear++] = start;
		int u;
		while (front != rear) {
			u = queue[front++];
			for (int j = 1; j <= V; j++) {
				if (dist[j] > dist[u] + g[u][j]) {
					dist[j] = dist[u] + g[u][j];
					p[j] = u;
					queue[rear++] = j; // 거리 갱신한게 있으면 다시 집어넣음(거리 계산을 다시해야 하기 때문)
				}
			}
		}
		pathPrint(end);
	}
	
	public static void main(String args[]) {
		
	}
}
