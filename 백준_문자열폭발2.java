import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

public class 백준_문자열폭발2 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		StringBuilder sb = new StringBuilder();
		String word = br.readLine();
		Stack<Character> stack = new Stack<Character>();
		String res = "";
		for (int i = 0; i < str.length(); i++) {
			stack.push(str.charAt(i));
			if (stack.size() >= word.length()) {
				boolean flag = true;
				for (int j = 0; j < word.length(); j++) {
					if (stack.get(stack.size() - word.length() + j) != word.charAt(j)) {
						flag = false;
						break;
					}
				}
				if (flag) {
					for (int j = 0; j < word.length(); j++) {
						stack.pop();
					}
				}
			}
		}
		for (Character c : stack) {
			sb.append(c);
		}
		if (sb.length()==0) {
			System.out.println("FRULA");
		} else {
			System.out.println(sb.toString());
		}
	}
}
