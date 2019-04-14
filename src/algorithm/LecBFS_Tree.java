package algorithm;

public class LecBFS_Tree {
	static int[] src = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
	static int[] leftC = {0,2,4,6,8,10};
	static int[] rightC = {0,3,5,7,9};
	
	static void bfs(int start) {
		int[] queue = new int[100];
		int front = 0;
		int rear = 0;
		queue[rear++] = start;
		int u;
		while(front!=rear) {
			u = queue[front++];
			if(src[u*2]!=0) queue[rear++] = u*2;
			if(src[u*2+1]!=0) queue[rear++] = u*2+1;
		}
	}
	
	public static void main(String args[]) {
		int start = 1;
	}
}
