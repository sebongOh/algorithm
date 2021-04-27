import java.util.Arrays;
import java.util.Comparator;

public class 프로그래머스_파일명정렬 {
	public static void main(String[] args) {
		String[] files = { "F-5 Freedom Fighter", "B-50 Superfortress", "A-10 Thunderbolt II", "F-14 Tomcat" };
		String[] answer = solution(files);
		System.out.println(Arrays.toString(answer));
	}

	private static String[] solution(String[] files) {

		Arrays.sort(files, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				String[] s1 = division(o1);
				String[] s2 = division(o2);
				if (s1[0].compareToIgnoreCase(s2[0]) == 0) {
					int num1 = Integer.parseInt(s1[1]);
					int num2 = Integer.parseInt(s2[1]);
					return num1 - num2;
				}

				return s1[0].compareToIgnoreCase(s2[0]);
			}

			private String[] division(String s) {
				String head = "";
				String number = "";
				String tail = "";

				int idx = 0;
				for (; idx < s.length(); idx++) {
					if (!(s.charAt(idx) >= '0' && s.charAt(idx) <= '9')) {
						head += s.charAt(idx);
						continue;
					}
					break;
				}
				for (; idx < s.length(); idx++) {
					if (s.charAt(idx) >= '0' && s.charAt(idx) <= '9') {
						number += s.charAt(idx);
						continue;
					}
					break;
				}
				for (; idx < s.length(); idx++) {
					tail += s.charAt(idx);
				}

				String[] file = { head, number, tail };
				return file;
			}
		});

		return files;
	}
}
