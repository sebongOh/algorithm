import java.util.HashMap;
import java.util.Map;

public class 프로그래머스_위장 {
	public static void main(String[] args) {
		String[][] clothes = { { "yellowhat", "headgear" }, { "bluesunglasses", "eyewear" },
				{ "green_turban", "headgear" } };
		int res = solution(clothes);
		System.out.println(res);

	}

	private static int solution(String[][] clothes) {
		int answer = 1;

		Map<String, Integer> map = new HashMap<String, Integer>();
		for (int i = 0; i < clothes.length; i++) {
			if (map.containsKey(clothes[i][1])) {
				map.put(clothes[i][1], map.get(clothes[i][1]) + 1);
			} else {
				map.put(clothes[i][1], 1);
			}
		}

		for (String s : map.keySet()) {
			answer *= map.get(s) + 1;
		}

		return answer - 1;
	}
}
