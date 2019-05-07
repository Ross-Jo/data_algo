/*
 * 해싱
 * 많이 쓰이는 해싱 기법 : 주어진 문자열을 p진법으로 변환하고 그 결과값을 q로 나눈 나머지를 취한다. (이때, p와 q가 둘 다 소수면 좋음)
 * - 추천 문제) 연락처 database, 백준 5582(공통 부분 문자열), 백준 10840(구간 성분), 백준 10256(돌연변이)
 * - 참고:  naver.blog, kks227/220927272165
 */

// 문자열을 입력받고 [0, q) 범위의 정수로 변환
const int p = 311, q = 1000003;
int hash(char *str) {
	int ret = 0;
	for (int i=0; str[i]; i++) ret = (ret * p + str[i]) % q;
	return ret;
}

/*
 * 하지만 해싱의 경우 충돌이 발생하기 마련인데, 이와 같은 충돌을 해결하기 위한 몇가지 방안이 있다.
 * 1. 다른 쌍의 (p, q)로 해시를 두번한다.
 * 장점) 구현이 쉽다. 두 문자열의 동등비교에서 매우 유리
 * 단점) 문자열을 정수로 생각해서 접근하는 풀이가 불가능
 *
 * 시간 복잡도 : O(L)
 * 공간 복잡도 : O(N)
 *
 */

struct String {
	char *str;
	int h1, h2;

	String(char *str) : str(str) {
		h1 = hash(str, p1, q1);
		h2 = hash(str, p2, q2);
	}

	int hash(char *str, int p, int q) {
		int ret = 0;
		for (int i = 0; str[i]; i++) ret = (ret * p + str[i]) % q;
		return ret;
	}
};

/*
 * 2. 해시테이블을 링크드리스트로 만들어 연결한다.
 * 장점) 충돌 없음
 * 단점) 링크드리스트 구현 오버헤드
 *
 * 시간 복잡도 : O(L+(N/Q)L)
 * 공간 복잡도 : O(q + NL)
 */

// vector <pair<string, int>> table[q] 이라고 생각

vector<string> table[q];
vector<int> table_idx[q];

const int p = 311, q = 1e5 + 3;
int cur_idx = 0;

int hash(char *str) {
	int ret = 0;
	for (int i = 0; str[i]; i++) ret = (ret * p + str[i]) % q;

	// algorithm에 헤더에 있는 find 함수
	int idx = find(table[ret].begin(), table[ret].end(), string(str)) - table[ret].begin();

	// table[ret] 링크드리스트에서 str을 못찾았을 때
	if (idx == table[ret].size()) {
		// table에 추가하고 번호를 새로 매겨줌
		table[ret].push_back(string(str));
		table_idx[ret].push_back(cur_idx);
		return cur_idx++;
	}

	// 이미 테이블에 존재하는 경우 이미 저장된 번호를 리턴
	return table_idx[idx];
}

/*
 *3. 해시테이블을 한 칸씩 이동하면서 빈 테이블 찾기
 * 장점) 충돌 없음, 구현 쉬움
 * 단점) 시간복잡도, 예측 불가능
 *
 * 시간 복잡도 : O(L+???)
 * 공간 복잡도 : O(q)
 */

#define L 10

const int p = 311, q = 1e5 + 3;
char* table[q];

int hash(char *str) {
	int ret = 0;
	for (int i = 0; str[i]; i++) ret = (ret * p + str[i]) % q;

	// 테이블이 비어있지 않고, 해당 테이블에 들어있는 문자열과 현재 문자열이 다르다면
	while (table[ret][0] != '\0' && strcmp(table[ret], str) != 0) ret = (ret + 1) % q;
	table[ret] = str;
	return ret;
}

/*
 * 4. 트라이 이용하기
 * 장점) 충돌 없음. 시간 복잡도 제일 좋음
 * 단점) 트라이 이해 필요, 공간 복잡도 조금 큼
 *
 * 시간 복잡도 : O(L)
 * 공간 복잡도 : O(NLK), k = 문자의 종류
 * k = 문자의 종류
 */

#define k 26
int cur_idx = 0;
struct Node {
	int idx;
	node *child[k];
	node() {
		idx = -1;
		for (int i=0; i<k; i++) child[i] = 0;
	}

	int hash(char *str) {
		if (*str == '\0') {
			if (idx == -1) idx = cur_idx++;
			return idx;
		}
		// 문자가 알파벳 소문자로만 이뤄진 경우
		int next = *str - 'a';
		if (child[next] == NULL) child[next] = new node();
		return child[next] -> hash(str + 1);
	}
};

node *root;
int main() {
	root = new Node();
	char str[] = "erejrdf";
	cout << root -> hash(str) << endl;
	return 0;
}

