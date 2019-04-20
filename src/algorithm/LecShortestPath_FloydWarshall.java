package algorithm;

public class LecShortestPath_FloydWarshall {
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
	
	// 모든 노드사이의 최단 경로 산출 (하지만 이것보다 모든 노드에 대해서 다익스트라 돌리는게 더 빠름)
	// 음수 가중치를 갖는 간선도 순환만 없다면 잘 처리됨
	
	// 선행조건 : 2차원 배열 g에는 두 정점간 거리가 들어감. (단, 연결되지 않은 노드들 사이는 무한대 처리하고, i==j인 경우는 0처리) 
	void f_w(int start) {
		for (int k=1; k<=V; k++) { // 경유지 
			for (int i=1; i<=V; i++) { // 출발지
				for (int j=1; j<=V; j++) { // 도착지 
					if(g[i][j] > g[i][k] + g[k][j]) {
						g[i][j] = g[i][k] + g[k][j];
					}
				}
			}
		}
	}
	
	public static void main(String args[]) {
		
	}
}
