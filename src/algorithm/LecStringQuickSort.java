package algorithm;

// 특정 문자열의 접미사들을 정렬한다. 

public class LecStringQuickSort {
	static String str = "abcd";
	static int strLen = str.length();
	static int[] suffix = new int[strLen];
	
	static int compare(int s1, int s2) {
		int len1 = strLen - s1;
		int len2 = strLen - s2;
		int lim = Math.min(len1, len2);
		
		int k = 0;
		while (k < lim) {
			char c1 = str.charAt(s1 + k);
			char c2 = str.charAt(s2 + k);
			if (c1 != c2) return c1 - c2; // 만약 다른 부분이 나오면 (당연하게도) 알파벳 순으로 앞서는 친구가 우선순위 
			k++;
		}
		return len1 - len2; // 길이가 긴 접미사가 후순위
	}
	
	static void qSort(int s, int e) {
		if (s < e) {
			int p = s;
			int l = s;
			int r = e;
			while (l < r) {
				while (compare(suffix[p], suffix[l]) >= 0 && l < e) l++;
				while (compare(suffix[p], suffix[r]) < 0) r--;
				if (l < r) {
					int t = suffix[r];
					suffix[r] = suffix[l];
					suffix[l] = t;
				}
			}
			int t = suffix[r];
			suffix[r] = suffix[p];
			suffix[p] = t;
			qSort(s, r - 1);
			qSort(r + 1, e);
		}
	}
	
	public static void main(String[] args) {
		for (int i=0; i<strLen; i++) suffix[i] = i;
		qSort(0, strLen - 1); // 접미사 배열 정렬 
	}
}
