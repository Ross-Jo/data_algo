package algorithm;

public class LecStringSearch_BoyerMoore {
	static int[] skip = new int[128]; // skip table (bad match table). 아스키 코드를 담을 수 있을 정도의 길이로 만들어줌.
	
	static void createSkipTable(String key) {
		// 보이어 무어의 bad match table의 값은 key 기준으로, length - index - 1 (키의 마지막 글자 및 다른 모든 글자의 경우는 패턴의 길이 값을 저장) 
		// 같은 글자가 연속해서 나오는 경우 뒷 글자가 기준 
		int keyLen = key.length();
		for (int i = 0; i < 128; i++) skip[i] = keyLen;
		keyLen--;
		for (int i = 0; i < keyLen; i++) skip[key.charAt(i)] = keyLen - i;
	}
	
	static int boyerMoore(String str, String key) {
		int strLen, keyLen;
		strLen = str.length();
		keyLen = key.length();
		int i = 0, j = 0;
		int len = strLen - keyLen;
		while (i <= len) {
			for (j = keyLen - 1; j >= 0 && key.charAt(j) == str.charAt(i + j); j--); // 끝에서 부터 비교해 나감 
			if (j < 0) return i; // 찾고자 하는 문자열을 찾은 경우 해당 문자열의 str에서의 시작점을 반환한다. 
			i = i + skip[str.charAt(i + keyLen - 1)]; // 무조건 마지막 글자기준 shift
		}
		return -1; 
	}
	
	public static void main(String[] args) {
		String str = "A STRING SEARCHING EXAMPLE CCNSISTING CF";
		String key = "STING";
		int idx;
		
		createSkipTable(key);
		idx = boyerMoore(str, key);
		if (idx > 0) {
			// 찾은 문자열 출력
		}
	}
}
