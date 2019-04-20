package algorithm;

public class LecDFS_BFS_Graph {
	static final int MAX = 101;
	int[][] g = new int[MAX][MAX];
	int[] visited = new int[MAX];
	int[] stack = new int[MAX]; int top;
	int[] queue = new int[MAX * MAX]; int front, rear; // 원형 큐를 쓰지 않는 경우는 큐의 길이를 생각지도 못할정도로 충분한 길이로 잡아야 함. 
	int V, E;
	
	void init() {
		for (int i=1; i<=V; i++) visited[i] = 0;
	}
	
	void dfs1(int start) {
		init();
		stack[top++] = start;
		visited[start] = 1;
		int u;
		while (top > 0) {
			u = stack[--top];
			for (int i = 1; i <= V; i++) {
				if (visited[i] == 0 && g[u][i] == 1) {
					stack[top++] = 1;
					visited[i] = 0;
				}
			}
		}
	}
	
	void dfs2(int start) {
		init();
		stack[top++] = start;
		int u;
		while (top > 0) {
			u = stack[--top];
			if (visited[u] == 0) { // 스택에 들어갈 때, 방문처리 해주지 않은 경우는 이와 같이 처리(같은 노드가 2번 들어갈 수 있기 때문)
				visited[u] = 1;
				for (int i = 1; i <= V; i++) {
					if (visited[i] == 0 && g[u][i] == 1) {
						stack[top++] = i;
					}
				}
			}
		}
	}
	
	void dfs_rec(int start) {
		visited[start] = 1;
		for (int i=1; i<=V; i++) {
			if (visited[i] == 0 && g[start][i] == 1) {
				dfs_rec(i);
			}
		}
	}
	
	void bfs(int start) {
		init();
		queue[rear++] = start;
		visited[start] = 1;
		int u;
		while (front != rear) {
			u = queue[front++];
			for (int i = 1; i <= V; i++) {
				if (visited[i] == 0 && g[u][i] == 1) {
					queue[rear++] = i;
					visited[i] = 0;
				}
			}
		}
	}
	
	public static void main(String args[]) {
		
	}
}
