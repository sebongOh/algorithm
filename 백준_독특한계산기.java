import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

public class 백준_독특한계산기 {
	static List<Character> ope;
	static List<Long> numbers;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		char[] arr = s.toCharArray();
		ope = new LinkedList<Character>();
		numbers = new LinkedList<Long>();
		boolean flag = false;
		if (arr[0] == '-') {
			flag = true;
		}
		int idx = 0;
		if (flag) {
			for (int i = 1; i < arr.length; i++) {
				if ('0' <= arr[i] && arr[i] <= '9') {
					String temp = "";
					idx = i;
					while ('0' <= arr[idx] && '9' >= arr[idx]) {
						temp += arr[idx];
						idx++;
						if (idx > arr.length - 1) {
							break;
						}
					}
					i = idx - 1;
					numbers.add(Long.parseLong(temp));
				} else {
					ope.add(arr[i]);
				}
			}
			long num = numbers.get(0);
			numbers.remove(0);
			numbers.add(0, -num);
		} else {
			for (int i = 0; i < arr.length; i++) {
				if ('0' <= arr[i] && arr[i] <= '9') {
					String temp = "";
					idx = i;
					while ('0' <= arr[idx] && '9' >= arr[idx]) {
						temp += arr[idx];
						idx++;
						if (idx > arr.length - 1) {
							break;
						}
					}
					i = idx - 1;
					numbers.add(Long.parseLong(temp));
				} else {
					ope.add(arr[i]);
				}
			}
		}

		if (ope.size() == 0) {
			System.out.println(numbers.get(0));
			return;
		}
		while (ope.size() != 1) {

			if ((ope.get(0) == '+' || ope.get(0) == '-')
					&& (ope.get(ope.size() - 1) == '*' || ope.get(ope.size() - 1) == '/')) {// 뒤에꺼 계산
				long a = numbers.get(numbers.size() - 2);
				long b = numbers.get(numbers.size() - 1);
				long res = solve(a, b, ope.get(ope.size() - 1));
				numbers.remove(numbers.size() - 1);
				numbers.remove(numbers.size() - 1);
				numbers.add(res);
				ope.remove(ope.size() - 1);

			} else if ((ope.get(0) == '*' || ope.get(0) == '/')
					&& (ope.get(ope.size() - 1) == '+' || ope.get(ope.size() - 1) == '-')) { // 앞에꺼 계산
				long a = numbers.get(0);
				long b = numbers.get(1);
				long res = solve(a, b, ope.get(0));
				numbers.remove(0);
				numbers.remove(0);
				numbers.add(0, res);
				ope.remove(0);
			} else { // 우선순위 같을 때 큰 값부터계산
				long a = numbers.get(0);
				long b = numbers.get(1);
				long first = solve(a, b, ope.get(0));

				long c = numbers.get(numbers.size() - 2);
				long d = numbers.get(numbers.size() - 1);
				long last = solve(c, d, ope.get(ope.size() - 1));

				if (first >= last) {
					numbers.remove(0);
					numbers.remove(0);
					numbers.add(0, first);
					ope.remove(0);
				} else {
					numbers.remove(numbers.size() - 1);
					numbers.remove(numbers.size() - 1);
					numbers.add(last);
					ope.remove(ope.size() - 1);
				}

			}
		} // while

		long answer = solve(numbers.get(0), numbers.get(1), ope.get(0));
		System.out.println(answer);

	}

	private static long solve(long a, long b, char ch) {
		long num = 0;
		switch (ch) {
		case '+':
			num = a + b;
			break;
		case '-':
			num = a - b;
			break;
		case '*':
			num = a * b;
			break;
		case '/':
			num = a / b;
			break;
		}
		return num;

	}
}