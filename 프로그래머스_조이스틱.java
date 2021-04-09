
public class 프로그래머스_조이스틱 {
	public static void main(String[] args) {
		String name = "ABAAAAAAAAABB";
		int res = solution(name);
		System.out.println(res);
	}

	private static int solution(String name) {
		int res = 0;
		boolean[] notA = new boolean[name.length()];
		int cnt = 0;
		for (int i = 0; i < name.length(); i++) {
			if (name.charAt(i) != 'A') {
				cnt++;
				continue;
			}
			notA[i] = true;
		}
		int idx = 0;
		for (int i = 0; i < cnt; i++) {
			if (notA[idx]) {
				int lidx = idx;
				int ridx = idx;
				int left = 0;
				int right = 0;

				while (notA[lidx]) {
					if (lidx == 0) {
						lidx = name.length() - 1;
						left++;
					} else {
						lidx--;
						left++;
					}
				}

				while (notA[ridx]) {
					ridx = (ridx + 1) % name.length();
					right++;
				}

				if (left < right) {
					idx = lidx;
					res += left;
				} else {
					idx = ridx;
					res += right;
				}

			} // if
			notA[idx] = true;
			res += checkAlphabet(idx, name);
		}

		return res;
	}

	private static int checkAlphabet(int idx, String name) {
		char c = name.charAt(idx);

		int up = c - 'A';
		int down = 'Z' - c + 1;

		if (up > down) {
			return down;
		} else {
			return up;
		}
	}
}
