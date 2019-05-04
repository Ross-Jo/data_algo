package algorithm;

public class LecStringSearch_RabinKarp {
	static String str = "6843212431", key = "4321";
	static int strLen, keyLen;

	static int power(int base, int exp) {
		int res = 1;
		for (int i = exp; i > 0; i--) res = res * base;
		return res;
	}

	static int makeHash(String arr) {
	    int res = 0;
	    for (int i=0; i<keyLen; i++)
	        res += (arr.charAt(i) - '0') * power(10, keyLen - i - 1); // 해시값을 만들어내는 과정 : 4,3,2,1 -> 4321
	    return res;
	}

	static int reHash(int key, int prevN, int nextN) { // 이전 해시값을 재활용, 새 해시값 계산
	    int res = key;
	    
	    res -= prevN * 1000;
	    res = res * 10;
	    res += nextN;
	    return res;
	}

	static int check(int idx) { // 일치하는 해시값 찾아도 확실한지 검사 해줘야됨
		for (int i = 0; i < keyLen; i++) {
			if (key.charAt(i) != str.charAt(idx + i)) return 0;
		}
		return 1;
	}

	public static void main(String args[]) {
		strLen = str.length();
		keyLen = key.length();

		int hashKey = makeHash(key);
		int textKey = 0;
		int i = 0;
		int finded = -1;
		int iLen = strLen - keyLen;
		
		for (i = 0; i <= iLen; i++) {
			if (i == 0) textKey = makeHash(str);
			else textKey = reHash(textKey, str.charAt(i - 1) - '0', str.charAt(i + keyLen - 1) - '0');
			if (hashKey == textKey) {
				if (check(i) == 1) {
					finded = i;
					break;
				}
			}
		}
		
		System.out.println("finded: " + finded);
	}
}
