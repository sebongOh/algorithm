import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class 카카오2019인턴_불량사용자 {
	static int banLen, userLen;
	static String[] user;
	static String[] ban;
	static boolean[] visited;
	static Set<String> set;
	static Set<Set<String>> result;

	public static void main(String[] args) {
		String[] user_id = { "frodo", "fradi", "crodo", "abc123", "frodoc" };
		String[] banned_id = { "fr*d*", "*rodo", "******", "******" };
		result = new HashSet<Set<String>>();
		set = new LinkedHashSet<String>();
		user = user_id;
		ban = banned_id;
		userLen = user_id.length;
		banLen = banned_id.length;
		visited = new boolean[userLen];
		dfs(0);
		System.out.println(result.size());
	}

	private static void dfs(int depth) {
		if (set.size() == banLen) {
			if (check()) {
				String s = "";
				result.add(new HashSet<>(set));
			}
			return;
		}
		for (String s : user) {
			if (!set.contains(s)) {
				set.add(s);
				dfs(depth + 1);
				set.remove(s);
			}
		}
	}

	private static boolean check() {
		int idx = 0;
		for (String s : set) {
			if (!samename(s, ban[idx++])) {
				return false;
			}
		}
		return true;
	}

	private static boolean samename(String s, String banid) {
		if (s.length() != banid.length()) {
			return false;
		}
		for (int i = 0; i < s.length(); i++) {
			if (banid.charAt(i) == '*') {
				continue;
			}
			if (banid.charAt(i) != s.charAt(i)) {
				return false;
			}
		}
		return true;
	}

}
