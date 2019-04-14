package dataStructure;

public class LecHeap {
	static final int MAX = 10;
	static int[] heap = new int[MAX];
	static int hCnt;
	
	static void push(int d) {
		heap[++hCnt] = d;
		int idx = hCnt;
		while(idx > 1 && heap[idx/2] > heap[idx]) {
			int t = heap[idx/2];
			heap[idx/2] = heap[idx];
			heap[idx] = t;
			idx = idx/2;
		}
	}
	
	static int pop() {
		int reVal = heap[1];
		heap[1] = heap[hCnt--];
		
		int idx = 1;
		int next;
		while(true) {
			next = idx * 2;
			if (next < hCnt && heap[next] > heap[next+1]) next++;
			if (next > hCnt || heap[idx] < heap[next]) break;
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
