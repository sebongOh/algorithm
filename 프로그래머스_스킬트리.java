
public class 프로그래머스_스킬트리 {
	public static void main(String[] args) {
		String skill = "CBD";
		String[] skill_trees = { "BACDE", "CBADF", "AECB", "BDA" };

		int res = solution(skill, skill_trees);
		System.out.println(res);

	}

	private static int solution(String skill, String[] skill_trees) {
		int res = 0;
		boolean[] check = new boolean[26];
		for (int i = 0; i < skill.length(); i++) {
			check[skill.charAt(i) - 'A'] = true;
		}
		for (int i = 0; i < skill_trees.length; i++) {
			String str = skill_trees[i];
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < str.length(); j++) {
				if (check[str.charAt(j) - 'A']) {
					sb.append(str.charAt(j));
				}
			}

			if (compare(skill, sb)) {
				res++;
			}

		}

		return res;
	}

	private static boolean compare(String skill, StringBuilder sb) {
		for (int i = 0; i < sb.length(); i++) {
			if (sb.toString().charAt(i) != skill.charAt(i)) {
				return false;
			}
		}
		return true;
	}
}
