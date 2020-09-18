import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class 카카오2020인턴_보석쇼핑2 {
	static HashSet<String> hs;
	static HashMap<String, Integer> hm;
	static Queue<String> q;
	static int length = Integer.MAX_VALUE;
	static int startPoint = 0;

	public static void main(String[] args) {
		String[] gems = { "DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA" };
		int[] answer = new int[2];
		hs = new HashSet<String>();
		hm = new HashMap<String, Integer>();
		q = new LinkedList<String>();
		int start = 0;
		for (String s : gems) {
			hs.add(s);
		}
		for (int i = 0; i < gems.length; i++) {
			// map안에 없으면 넣고 있으면 개수 추가
			if (!hm.containsKey(gems[i])) {
				hm.put(gems[i], 1);
			} else {
				hm.put(gems[i], hm.get(gems[i]) + 1);
			}
			q.add(gems[i]);

			while (true) {
				String s = q.peek();
				if (hm.get(s) > 1) {
					hm.put(s, hm.get(s) - 1);
					q.poll();
					startPoint++;
				} else {
					break;
				}
			}

			if (hm.size() == hs.size() && length > q.size()) {
				length = q.size();
				start = startPoint;
			}
		} // for
		answer[0] = start + 1;
		answer[1] = start + length;
		System.out.println(Arrays.toString(answer));
	}
}
