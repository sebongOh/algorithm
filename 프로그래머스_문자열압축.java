
public class 프로그래머스_문자열압축 {
	public static void main(String[] args) {
		String s = "a";
		int res = solution(s);
		System.out.println(res);

	}

	private static int solution(String s) {
		int res = 999;
		StringBuffer sb = new StringBuffer();
		String nowStr = "";
		String pattern = "";
		if(s.length()==1) {
			res = 1;
			return res;
		}
		int cnt = 1;
		for (int len = 1; len <= s.length() / 2; len++) { // 자를 길이

			for (int j = 0; j < s.length(); j += len) { // 자를 자리

				if (j + len >= s.length()) {
					nowStr = s.substring(j, s.length());
				} else {
					nowStr = s.substring(j, j + len);
				}

				if (j != 0) { // 첫번째가 아니면
					if (pattern.equals(nowStr)) {
						cnt++;
					} else if (cnt > 1) {
						sb.append(cnt);
						sb.append(pattern);
						cnt = 1;
					} else {
						sb.append(pattern);
					}
				}

				pattern = nowStr;

			} // for(j)
			if (cnt > 1) {
				sb.append(cnt);
				sb.append(pattern);
				cnt = 1;
			} else {
				sb.append(nowStr);
			}
			res = Math.min(res, sb.length());
			sb.delete(0, sb.length());
		} // for(len)
		return res;
	}
}
