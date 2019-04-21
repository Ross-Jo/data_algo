package algorithm;

//https://www.acmicpc.net/problem/10216
//: https://www.youtube.com/watch?v=ID00PMy0-vE
//: http://rrljdy.tistory.com/6

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MyDisjointSet {
	static Point[] p;
	static int[] parent;
	static boolean[] check;
	
	static class Point {
		int x, y, radius;
		Point(int x, int y, int radius) {
			this.x = x;
			this.y = y;
			this.radius = radius;
		}
	}
	
	boolean isfriend(Point a, Point b) {
		if ((a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y) > (a.radius+b.radius)*(a.radius+b.radius)) return false;
		return true;
	}
	
	int find(int x) {
		if (parent[x] == x) return x;
		return parent[x] = find(parent[x]); // path compression
	}
	
	void union(int n, int p1, int p2) {
		int x = find(p1);
		int y = find(p2);
		for(int i=1; i<=n; i++) if(parent[i] == y) parent[i] = x;
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		MyDisjointSet q = new MyDisjointSet();
		
		int T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			int N = Integer.parseInt(br.readLine());
			p = new Point[3001];
			parent = new int[3001];
			check = new boolean[3001];
			
			StringTokenizer st;
			for (int i = 1; i <= N; i++) {
				parent[i] = i;
				st = new StringTokenizer(br.readLine());

				Point point = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				p[i] = point;
			}
			System.out.println(q.solve(N));
		}
		br.close();
	}
	
	int solve(int N) {
		for (int i = 1; i < N; i++) {
			for (int j = i + 1; j <= N; j++) {
				if (isfriend(p[i], p[j]) && parent[i] != parent[j]) union(N, i, j);
			}
		}
		int ret = 0;
		for (int i = 1; i <= N; i++) {
			if (check[parent[i]] == false) {
				check[parent[i]] = true;
				ret++;
			}
		}
		return ret;
	}
	
}
