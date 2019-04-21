package algorithm;

public class LecMST_Prim_Fake {
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
	
	void prim(int start) {
		init();
		dist[start] = 0;
		
		int u = 0;
		int min;
		for (int k = 0; k < V; k++) { // vertex 갯수만큼 돌림
			min = INF;
			for (int i = 1; i <= V; i++) {
				if (visited[i] == 0 && min > dist[i]) { // 방문하지 않은 노드들 중, 가장 거리가 가까운 노드
					min = dist[i];
					u = i;
				}
			}
			
			visited[u] = 1; // visited 집합에 있는 원소의 수를 늘려나간다. (프림 알고리즘의 동작 과정을 떠올려 보자) 
			
			for (int j = 1; j <= V; j++) { // 도착지
				if (visited[j] == 0 && dist[j] > g[u][j]) { // 사이클이 생길수가 없는게, 방문한 점은 절대 재방문하지 않음
					dist[j] = g[u][j];
					p[j] = u;
				}
			}
		}
	}
	
	public static void main(String args[]) {
		
	}
}
