
public class 프로그래머스_JadenCase문자열만들기 {
	public static void main(String[] args) {
		String answer = solution("for the last week");
		System.out.println(answer);

	}

	private static String solution(String s) {
		StringBuilder sb = new StringBuilder();
		sb.append((s.charAt(0) + "").toUpperCase());
		for (int i = 1; i < s.length(); i++) {
			String str = s.charAt(i) + "";
			if (str.equals(" ")) {
				sb.append(" ");
			} else if (s.charAt(i - 1) == ' ') {
				sb.append(str.toUpperCase());
			} else {
				sb.append(str.toLowerCase());
			}
		}

		return sb.toString();
	}
}
