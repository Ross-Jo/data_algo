package algorithm;

public class LecPrimeDetection {
	
	// 뉴튼 랩슨 방법으로 루트값 구하기 
	int root(int n) {
		double r = 1.0;
		double chk = 5.0e-1; // 왼쪽의 경우는 유효범위가 0.1인 경우. 만약 -1이 아니라 -2가 된다면, 유효범위가 0.01인 경우를 의미한다. -3이면 0.001.
		while (true) {
			r = ((n / r) + r) / 2;
			if (n - chk <= r * r && r * r <= chk) break;
		}
		return (int) r; // 이렇게 리턴을 하게 되면, 루트값의 자연수 부분만 필요한 경우 이렇게 처리해주면 된다.
	}
	
	int isPrime1(int n) {
		if (n == 1) return 0;
		for (int i = 2; i < n; i++) if ((n % i) == 0) return 0;
		return 1;
	}
	
	int isPrime2(int n) {
		int iLen = root(n);
		if (n == 1) return 0;
		for (int i = 2; i <= iLen; i++) if ((n % i) == 0) return 0;
		return 1;
	}
	
	int isPrime3(int n) {
		int iLen = root(n);
		if (n == 1) return 0;
		if (n == 2) return 1;
		if ((n & 1) == 0) return 0;
		for (int i = 3; i <= iLen; i++) if ((n % i) == 0) return 0;
		return 1;
	}
	
	public static void main(String[] args) {

	}

}
