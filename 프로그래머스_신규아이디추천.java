
public class 프로그래머스_신규아이디추천 {
	public static void main(String[] args) {
		String answer = "";
		String new_id = "...!@BaT#*..y.abcdefghijklm";

		answer = solution(new_id);
//		System.out.println(solution("...!@BaT#*..y.abcdefghijklm"));
//		System.out.println(solution("z-+.^."));
//		System.out.println(solution("=.="));
//		System.out.println(solution("123_.def"));
//		System.out.println(solution("abcdefghijklmn.p"));

	}

	private static String solution(String new_id) {
		String answer = "";
		StringBuffer sb = new StringBuffer();
		answer = new_id.toLowerCase();
		answer = answer.replaceAll("[^-_.a-z0-9]", "");
		answer = answer.replaceAll("[.]{2,}", ".");
		answer = answer.replaceAll("^[.]|[.]$", "");

		if (answer.length() == 0) {
			answer += "a";
		}
		if (answer.length() > 15) {
			answer = answer.substring(0, 15);
			if (answer.charAt(answer.length() - 1) == '.') {
				answer = answer.substring(0, 14);
			}
		}

		if (answer.length() < 3) {
			char ch = answer.charAt(answer.length() - 1);
			while (answer.length() < 3) {
				answer += ch;
			}
		}
		return answer;
	}
}
