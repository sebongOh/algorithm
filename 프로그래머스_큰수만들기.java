
public class 프로그래머스_큰수만들기 {
	public static void main(String[] args) {
		String number = "4177252841";
		int k = 4;
		String res = solution(number, k);
		System.out.println(res);
	}

	private static String solution(String number, int k) {
		String res = "";
		int len = number.length(); // 입력 길이
		int cnt = len - k; // 결과 자리 수
		int left = 0;
		int right = len - cnt;
		StringBuilder sb = new StringBuilder();
		int max = -1;
		int idx = 0;

		while (cnt > 0) {
			max = -1;
			for (int i = left; i <= right; i++) {
				if (max < number.charAt(i) - '0') {
					max = number.charAt(i) - '0';
					idx = i;
				}
			}
			sb.append(number.charAt(idx));
			left = idx+1;
			cnt--;
			right = len - cnt;
		} // while

		return sb.toString();
	}// solution

}
