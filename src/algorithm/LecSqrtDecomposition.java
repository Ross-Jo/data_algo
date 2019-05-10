package algorithm;
import java.util.Scanner;

// 참고 : https://kesakiyo.tistory.com/22

public class LecSqrtDecomposition {
	static int MAX = (int) 1e6 + 10;
	static int sz, n, m, k, q;
	static long y, A[] = new long[MAX], bucket[] = new long[MAX];
	
	static void init() {
		sz = (int) Math.sqrt(n);

		for (int i = 0; i < n; i++) {
			bucket[i / sz] += A[i];
		}
	}
	
	// query function
	static long query(int lo, int hi) {
		long ret = 0;

		// 2번 경우를 처리해 주는 부분
		// 구간의 왼쪽에 애매하게 걸쳐있는 묶음의 원소들을 모두 더해준다
		while (lo % sz != 0 && lo <= hi) {
			ret += A[lo++];
		}

		// 구간의 오른쪽에 애매하게 걸쳐있는 묶음의 원소들을 모두 더해준다
		while ((hi + 1) % sz != 0 && lo <= hi) {
			ret += A[hi--];
		}

		// 1번 경우를 처리해 주는 부분
		// 구간 내 완벽하게 포함된 묶음들의 합을 더해준다
		while (lo <= hi) {
			ret += bucket[lo / sz];
			lo += sz;
		}

		return ret;
	}
	
	// update function
	static void update(int pos, long val) {
		// 원래 원소의 값과 갱신해야 하는 값의 차이를 계산
		long diff = val - A[pos];

		// 기존 원소를 새로운 값으로 대체
		A[pos] = val;

		// 기존 원소가 속해있는 묶음에 갱신으로 인해 생기는 차이만큼의 값을 더해준다
		bucket[pos / sz] += diff;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		k = sc.nextInt();
		
		for (int i=0; i<n; i++) A[i] = sc.nextLong();
		
		init();
		
		for (int iter = 0; iter < m + k ; iter++) {
			int q = sc.nextInt();
			
			if (q == 1) {
				int pos;
				long val;
				pos = sc.nextInt();
				val = sc.nextLong();
				update(pos - 1, val);
			} else {
				int lo, hi;
				lo = sc.nextInt();
				hi = sc.nextInt();
				System.out.println(query(lo - 1, hi - 1));
			}
			
		}
		
		sc.close();
	}
}
