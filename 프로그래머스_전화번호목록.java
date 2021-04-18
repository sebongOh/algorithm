import java.util.Arrays;

public class 프로그래머스_전화번호목록 {
	public static void main(String[] args) {
		String[] phone_book = { "12","123","1235","567","88"};
		boolean res = solution(phone_book);
		System.out.println(res);
	}

	private static boolean solution(String[] phone_book) {
		Arrays.sort(phone_book);

		for (int i = 0; i < phone_book.length - 1; i++) {
			if (phone_book[i].length() <= phone_book[i + 1].length()) {
				if (phone_book[i + 1].substring(0, phone_book[i].length()).equals(phone_book[i])) {
					return false;
				}
			}
		}

		return true;
	}
}
