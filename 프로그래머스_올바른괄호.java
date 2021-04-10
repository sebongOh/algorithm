import java.util.Stack;

public class 프로그래머스_올바른괄호 {
	public static void main(String[] args) {
		String s = ")()(";
		boolean res = solution(s);
		System.out.println(res);
	}

	private static boolean solution(String s) {
		Stack<Character> stack = new Stack<Character>();

		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.push('(');
			} else {
				if (stack.isEmpty()) {
					return false;
				} else {
					stack.pop();
				}
			}
		}

		return stack.isEmpty() ? true : false;
	}
}
