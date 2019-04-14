package algorithm;

public class LecPermutation {
	static char[] src = {'\0', 'a', 'b', 'c'};
	static final int MAX = 4;
	static char[] temp = new char[MAX];
	
	static boolean[] visited = new boolean[MAX];
	
	// 백트래킹류의 순열생성을 위해 필요한 변수
	static int n_b, r_b;
	static int[] copy = new int[MAX];
	
	static void print() {
		System.out.print("[");
		for (char el: temp) System.out.print(el + " ");
		System.out.println("]");
	}
	
	static void print_b() {
		System.out.print("[");
		for (int el: copy) System.out.print(src[el] + " ");
		System.out.println("]");
	}
	
	static void swap(int a, int b) {
		char t = src[a];
		src[a] = src[b];
		src[b] = t;
	}
	
	static void permutation(int n, int r) {
		if (r==0) {
			print();
			return;
		}
		
		for (int i=n; i>0; i--) {
			swap(i, n);
			
			temp[r] = src[n];
			permutation(n-1, r-1);
			
			swap(i, n);
		}
	}
	
	static void permutation_visited_method(int k) {
		if (k==r_b) {
			print_b();
			return;
		}
		
		k++;
		for (int i=1; i<=n_b; i++) {
			if (!visited[i]) {
				visited[i] = true;
				
				copy[k] = i;
				permutation_visited_method(k);
				
				visited[i] = false;
			}
		}
	}
	
	static void permutationWithRepetition(int n, int r) {
		if (r==0) {
			print();
			return;
		}
		
		for (int i=n; i>0; i--) {
			swap(i, n);
			
			temp[r] = src[n];
			permutationWithRepetition(n, r-1); // 뽑은거에서 또 뽑을 수 있음. 여기가 차이점 
			
			swap(i, n);
		}
	}
	
	static void permutationWithRepetition_visited_method(int k) {
		if (k==r_b) {
			print_b();
			return;
		}
		
		k++;
		for (int i=1; i<=n_b; i++) {
			copy[k] = i;
			permutationWithRepetition_visited_method(k);
		}
	}
	
	// 독특한 방식의 순열 생성
	static int makeCandidate(int[] candidate, int chosenCount) {
		int[] inPermutation = new int[MAX];
		for (int i=1; i<chosenCount; i++) inPermutation[copy[i]] = 1; // copy에 들어있는 숫자를 후보군에서 제외시키기 위함 
		int len = 0;
		for (int i=1; i<=n_b; i++) {
			if (inPermutation[i] == 0) candidate[++len] = i; // 이미 선택한 숫자를 제외한 순열생성가능 후보군을 만들어줌 
		}
		return len; // 후보군의 길이를 리턴함 
	}
	
	static void backtracking_permutation(int chosenCount) {
		if(chosenCount == r_b) {
			print_b();
			return;
		}
		
		chosenCount++;
		int[] candidate = new int[MAX];
		int candidateLength = makeCandidate(candidate, chosenCount); // 순열을 만들 수 있는 후보군 파악 
		
		for (int i=1; i<=candidateLength; i++) {
			copy[chosenCount] = candidate[i]; // copy는 선택한 숫자의 인덱스를 기억하기 위한 배열 
			backtracking_permutation(chosenCount);
			copy[chosenCount] = 0; // 재귀 호출 후 타깃 위치에 박아줬던 숫자를 초기화 해야함
		}
	}
	
	public static void main(String args[]) {
		permutation(3, 3);
		System.out.println("====================");
		
		permutationWithRepetition(3, 3);
		System.out.println("====================");
		
		n_b = 3; r_b = 3;
		backtracking_permutation(0);
		System.out.println("====================");
		
		copy = new int[MAX];
		permutation_visited_method(0);
		System.out.println("====================");
		
		copy = new int[MAX];
		permutationWithRepetition_visited_method(0);
		System.out.println("====================");
	}
}
