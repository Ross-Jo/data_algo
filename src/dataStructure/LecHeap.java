package dataStructure;

// 우선순위 큐

public class LecHeap {
	static final int MAX = 10;
	static int[] heap = new int[MAX];
	static int hCnt;
	
	// 아래의 힙 함수는 min heap을 구성하기 위함
	
	static void push(int d) {
		heap[++hCnt] = d;
		int idx = hCnt;
		while(idx > 1 && heap[idx/2] > heap[idx]) { // 루트가 아닌 경우에 계속 시도
			int t = heap[idx/2];
			heap[idx/2] = heap[idx];
			heap[idx] = t;
			idx = idx/2;
		}
	}
	
	static int pop() {
		int reVal = heap[1];
		heap[1] = heap[hCnt--]; // 마지막 자리에 있는 놈을 루트로 당겨옴 
		
		int idx = 1;
		int next;
		while(true) {
			next = idx * 2;
			if (next < hCnt && heap[next] > heap[next+1]) next++; // min heap이기 때문에 더 작은 쪽으로 가기 위함
			if (next > hCnt || heap[idx] < heap[next]) break; // 올바른 위치로 판단되는 경우 브레이크 할 것
			int t = heap[idx];
			heap[idx] = heap[next];
			heap[next] = t;
			idx = next;
		}
		
		return reVal;
	}
	
	public static void main(String args[]) {
		
	}
}
