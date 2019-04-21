package algorithm;

public class StringSearch_KMP {
	static final int STR_MAX = 100;
	static String str = "cccabcdabcef", key = "abcdabcef";
	static int[] failure = new int[STR_MAX];
	static int keyLen, strLen;
	
	static void createTable() {
		int len = keyLen - 1;
		for (int i = 1, j = 0; i < len; i++) {
			if (j > 0 && key.charAt(i) != key.charAt(j)) { j = 0; }
			if (key.charAt(i) == key.charAt(j)) { failure[i + 1] = ++j; }
		}
	}
	
	static int kmp_match() {
		int i = 0, j = 0;
		while (i < strLen && j < keyLen) {
			if (str.charAt(i) == key.charAt(j)) {
				i++; j++;
			} 
			else if (j == 0) i++;
			else j = failure[j];
		}
		return ((j == keyLen) ? (i - keyLen) : -1);
	}
	
	
	public static void main(String[] args) {
		createTable();
		kmp_match();
	}

}
