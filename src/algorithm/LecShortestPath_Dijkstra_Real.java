package algorithm;

public class LecShortestPath_Dijkstra_Real {
	static final int MAX = 101, INF = 987654321;
	int[][] g = new int[MAX][MAX];
	int[] dist = new int[MAX];
	int[] p = new int[MAX];
	int V, E;
	int[] heap = new int[MAX];
	int hCnt; // heap count
	
	void init() {
		for (int i=1; i<=V; i++) {
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
	
	void dijkstra_heap(int start, int end) {
		init();
		// 일단 스타트 지점의 dist는 0임. 나머지 거리는 다 무한대로 같고. 
		heap[1] = start;
		heap[start] = 1;
		dist[start] = 0;
		
		int u;
		
		while (hCnt > 0) {
			u = pop();
			// if(u == end) break; // 프림은 여기에 브레이크를 걸 수 없지만, 다익스트라는 가능 
			                       // s~e 까지의 최단 거리 구할 경우, 주석해제. s부터 다른 모든 정점까지의 최단거리 구하는 경우 주석처리 
			for (int j = 1; j <= hCnt; j++) {
				if (dist[heap[j]] > dist[u] + g[u][heap[j]]) {
					dist[heap[j]] = dist[u] + g[u][heap[j]];
					p[heap[j]] = u;
					push(j); // 실제 바뀐건 heap의 j번째 index. 힙의 어디가 바뀐건지 통지.
				}
			}
		}
		
		pathPrint(end);
 	}
	
	void push(int idx) {
		while (idx > 1 && dist[heap[idx / 2]] > dist[heap[idx]]) {
			int t = heap[idx / 2];
			heap[idx / 2] = heap[idx];
			heap[idx] = t;
			idx = idx / 2;
		}
	}
	
	int pop() {
		int reVal = heap[1];
		heap[1] = heap[hCnt--];

		int idx = 1;
		int next;
		while (true) {
			next = idx * 2;
			if (next < hCnt && dist[heap[next]] > dist[heap[next + 1]]) next++;
			if (next > hCnt || dist[heap[idx]] < dist[heap[next]]) break;
			int t = heap[idx];
			heap[idx] = heap[next];
			heap[next] = t;
			idx = next;
		}
		return reVal;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
