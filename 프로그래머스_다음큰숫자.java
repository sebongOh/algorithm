
public class 프로그래머스_다음큰숫자 {
	public static void main(String[] args) {
		int n = 15;
		int res = solution(n);
		System.out.println(res);
	}

	private static int solution(int n) {
		int idx = n + 1;
		String str = Integer.toBinaryString(n);
		int strLen = count(str);
		while (true) {
			if (equalCount(strLen, idx)) {
				break;
			}
			idx++;
		}

		return idx;
	}

	private static int count(String str) {
		int cnt = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '1') {
				cnt++;
			}
		}
		return cnt;
	}

	private static boolean equalCount(int strLen, int idx) {
		String str = Integer.toBinaryString(idx);
		int len = count(str);

		return strLen == len ? true : false;
	}
}
