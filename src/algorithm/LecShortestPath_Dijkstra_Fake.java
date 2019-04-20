package algorithm;

public class LecShortestPath_Dijkstra_Fake {
	static final int MAX = 101, INF = 987654321;
	int[][] g = new int[MAX][MAX];
	int[] visited = new int[MAX];
	int[] dist = new int[MAX];
	int[] p = new int[MAX];
	int V, E;
	
	void init() {
		for (int i=1; i<=V; i++) {
			visited[i] = 0;
			dist[i] = INF;
			p[i] = 0;
		}
	}
	
	void pathPrint(int idx) {
		if (idx == 0) return;
		pathPrint(p[idx]);
		System.out.print(idx + " ");
	}
	
	void dijkstra(int start, int end) {
		init();
		dist[start] = 0;
		
		int u = 0;
		int min;
		while (true) {
			min = INF;

			// 아직 방문하지 않았고, 가장 가까운 곳에 있는 노드를 선택한다.
			for (int i = 1; i <= V; i++) {
				if (visited[i] == 0 && min > dist[i]) {
					min = dist[i];
					u = i;
				}
			}

			visited[u] = 0;
			if (u == end) break;

			for (int j = 1; j <= V; j++) { // 도착지
				if (visited[j] == 0 && dist[j] > dist[u] + g[u][j]) { // 더 단거리로 갈 수 있을 때마다 갱신
					dist[j] = dist[u] + g[u][j];
					p[j] = u;
				}
			}
		}
		pathPrint(end);
	}
	
	
	public static void main(String[] args) {

	}
}
