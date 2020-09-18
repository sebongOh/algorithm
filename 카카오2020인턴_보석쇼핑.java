import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class 카카오2020인턴_보석쇼핑 {
	public static void main(String[] args) {
		String[] gems = { "DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA" };
		int[] answer = new int[2];
		Set<String> set = new HashSet<String>();
		for (int i = 0; i < gems.length; i++) {
			set.add(gems[i]);
		}
		Map<String, Integer> map = new HashMap<String, Integer>();

		aa: while (true) {
			a: for (int i = 0; i < gems.length; i++) {
				String s = gems[i];
				for (String keys : map.keySet()) {
					if (s.equals(keys)) {
						map.replace(keys, i + 1);
						continue a;
					}
				}
				map.put(s, i + 1);
				if (map.size() == set.size()) {
					break aa;
				}
			}
		}

		List<Integer> list = new ArrayList<Integer>();
		for (String ss : map.keySet()) {
			list.add(map.get(ss));
		}
		Collections.sort(list);
		answer[0] = list.get(0);
		answer[1] = list.get(list.size() - 1);

	}
}
