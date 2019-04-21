package algorithm;

// https://www.acmicpc.net/problem/11375
// 참고 : http://jason9319.tistory.com/149

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MyBipartiteMatching {
	static int n, m;
	static int[] visited = new int[1010]; // 하나의 집단 A -> 방문 여부 기록 
	static int[] b = new int[1010]; // 다른 하나의 집단 B -> 카운터파트 기록 
	static ArrayList<ArrayList<Integer>> nodes; // 집단간의 연결관계를 나타냄 
	
	int dfs(int here) {
		if (visited[here] > 0) return 0; // 한 회차의 dfs 돌때 이미 한번 건드린 적이 있다면, 다시 건드릴 수 없음 dfs의 무한호출 방지 
		visited[here] = 1;
		for (int there: nodes.get(here)) {
			if (b[there] == 0 || dfs(b[there]) > 0) { // 만약 매칭이 되어 있지 않은 경우[즉, 매칭 카운터파트가 없는 경우] 매칭해줌. 혹시 매칭이 되어 있는 경우에는 매칭된 친구의 카운터파트가 다르게 매칭할 수 있는 경우가 있는지 판단. 
				b[there] = here;
				return 1;
			}
		}
		return 0;
	}
	
	int bmatch() {
		int ret = 0;
		for (int i = 1; i <= n; i++) {
			Arrays.fill(visited, 0); // (중요)각 노드별로 매칭 및 매칭을 위한 갱신이 필요할 때마다 방문 여부를 초기화 해야됨. 방문 여부라는 것은 매칭 판별을 위한 해당 회차에서만 유효 
			if (dfs(i) > 0) ret++;
		}
		return ret;
	}
	
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		MyBipartiteMatching q = new MyBipartiteMatching();
		n = sc.nextInt();
		m = sc.nextInt();

		nodes = new ArrayList<ArrayList<Integer>>(n + 1);
		for (int i = 0; i <= n; i++) nodes.add(new ArrayList<Integer>());

		for (int i = 1; i <= n; i++) {
			int v, x;
			v = sc.nextInt();
			ArrayList<Integer> node = nodes.get(i);
			while (v-- > 0) {
				x = sc.nextInt();
				node.add(x);
			}
		}
		System.out.println(q.bmatch());

		sc.close();
	}
}