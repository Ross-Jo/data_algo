/*
 * 간선리스트 : 제대로 사용하기 위해서는 각 정점별로 간선 리스트의 어느 곳부터 시작하는지 필요 && 정렬 필수
 * 정점별로 시작 위치와 길이 따로 관리할 것
 */

struct edge {
    int from, to;
    bool operator < (const edge& e) const {
    	return from < e.from;
    }
};

edge E[M];
int start[N], len[N];
int main() {
	int n, m;
	scanf("%d%d", &n, &m);
	for (int i = 0; i < m; i++) {
		int from, to;
		scanf("%d%d", &from, &to);
		E[i] = {from, to};
	}
	sort(E, E + m);
	for (int i = 0; i < n; i++) start[i] = -1;
	for (int i = 0; i < m; i++) {
		if (start[E[i].from] == -1) start[E[i].from] = i;
		len[E[i].from]++;
	}

	for (int i = 0; i < n; i++) { // 각 정점의 간선들을 확인하는 코드
		for (int j = 0; j < len[i]; j++) {
			edge& e = E[start[i] + j];
		}
	}
}
