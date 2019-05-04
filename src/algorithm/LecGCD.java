package algorithm;

public class LecGCD {
	
	// 유클리드 호제법을 통해 최대 공약수를 찾는 방법
	// CF) LCM * GCD = p * q;
	
	int gcd (int p, int q) {
		if(q==0) return p;
		return gcd(p, p%q);
	}
	
	public static void main(String[] args) {

	}
}
