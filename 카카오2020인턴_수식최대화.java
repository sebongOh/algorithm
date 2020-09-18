import java.util.ArrayList;
import java.util.Stack;

public class 카카오2020인턴_수식최대화 {

	static boolean[] visited;
	static ArrayList<Integer> list;
	static ArrayList<Character> ope;
	static long max = 0;
	static char[] ch;
	static Stack<String> stack;
	static ArrayList<String> arr;

	public static void main(String[] args) {
		String expression = "50*6-3*2";
		long answer = 0;
		ch = expression.toCharArray();
		list = new ArrayList<Integer>();
		ope = new ArrayList<Character>();
		visited = new boolean[3];
		stack = new Stack<String>();
		arr = new ArrayList<String>();
		perm(0);
		System.out.println(max);
		System.out.println(Math.abs(answer));
	}

	private static void perm(int depth) {
		if (depth == 3) {
			solve();
			return;
		}
		for (int i = 0; i < 3; i++) {
			if (visited[i]) {
				continue;
			}
			visited[i] = true;
			list.add(i + 1);
			perm(depth + 1);
			visited[i] = false;
			list.remove(list.size() - 1);
		}
	}

	private static void solve() {
		ope.clear();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == 1) {
				ope.add('+');
			} else if (list.get(i) == 2) {
				ope.add('-');
			} else if (list.get(i) == 3) {
				ope.add('*');
			}
		}
		String s = "";
		arr.clear();
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (c != '-' && c != '+' && c != '*') {
				s += c;
			} else {
				arr.add(s);
				s = "";
				arr.add(String.valueOf(c));
			}
		}
		arr.add(s);

		char opp = ope.get(0);
		long num = 0;
		while (true) {
			for (int i = 1; i < arr.size(); i += 2) {
				if (arr.get(i).equals(String.valueOf(opp))) {
					if (opp == '+') {
						num = Long.parseLong(arr.get(i - 1)) + Long.parseLong(arr.get(i + 1));
					} else if (opp == '-') {
						num = Long.parseLong(arr.get(i - 1)) - Long.parseLong(arr.get(i + 1));
					} else if (opp == '*') {
						num = Long.parseLong(arr.get(i - 1)) * Long.parseLong(arr.get(i + 1));
					}
					arr.remove(i - 1);
					arr.remove(i - 1);
					arr.remove(i - 1);
					arr.add(i - 1, String.valueOf(num));
					i = -1;
				}
			}
			
			ope.remove(0);
			if (ope.size() != 0) {
				opp = ope.get(0);
			}
			if (arr.size() == 1) {
				break;
			}
		}
		long sum = Long.parseLong(arr.get(0));
		max = Math.max(Math.abs(sum), Math.abs(max));
	}
}
