package algorithm;

public class LecShortestPath_Iterative {
	static final int MAX = 101, INF = 987654321;
	int[][] g = new int[MAX][MAX];
	int[] visited = new int[MAX];
	int[] dist = new int[MAX];
	int[] p = new int[MAX];
	int[] queue = new int[MAX * MAX]; int front, rear;
	int V, E;
	int[] heap = new int[MAX];
	int hCnt; // heap count
	
	void init() {
		for (int i=1; i<=V; i++) {
			visited[i] = 0;
			dist[i] = INF;
			p[i] = 0;
			heap[i] = i; // 어차피 거리가 다 무한대라 그냥 노드들이 먼저 들어가 있어도 상관 없음, min_heap(거리가 짧을수록 우선순위 높음) 
		}
		hCnt = V; // 미리 V 갯수만큼 넣어놔서 hCnt가 V
	}
	
	void pathPrint(int idx) {
		if (idx == 0) return;
		pathPrint(p[idx]);
		System.out.print(idx + " ");
	}
	
	void iter(int start, int end) {
		init();
		dist[start] = 0;
		
		for (int i = 1; i <= V; i++) {
			for (int j = 1; j <= V; j++) {
				if (dist[j] > dist[i] + g[i][j]) {
					dist[j] = dist[i] + g[i][j];
					p[j] = i;
				}
			}
		}
		
		pathPrint(end);
	}
	
	
	public static void main(String[] args) {

	}

}
