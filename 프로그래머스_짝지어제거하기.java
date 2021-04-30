import java.util.Stack;

public class 프로그래머스_짝지어제거하기 {
	public static void main(String[] args) {
		System.out.println(solution("cdcd"));
	}

	private static int solution(String s) {
		Stack<Character> stack = new Stack<Character>();
		stack.push(s.charAt(0));
		for (int i = 1; i < s.length(); i++) {
			if (!stack.isEmpty() && (stack.peek() == s.charAt(i))) {
				stack.pop();
			} else {
				stack.push(s.charAt(i));
			}
		}

		return stack.isEmpty() ? 1 : 0;
	}
}
