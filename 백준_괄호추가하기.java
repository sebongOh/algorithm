import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_괄호추가하기 {
	static int N;
	static int[] num;
	static int[] ope;
	static boolean[] check;
	static int result;
	static int sum;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		ope = new int[N / 2];
		num = new int[(N / 2) + 1];
		String s;
		int idx1 = 0;
		int idx2 = 0;
		s = br.readLine();
		for (int i = 0; i < s.length(); i++) {
			if (i % 2 == 0) {
				num[idx1++] = s.charAt(i) - '0';
			} else {
				ope[idx2++] = s.charAt(i);
			}
		}
		result = Integer.MIN_VALUE;
		check = new boolean[N / 2];
		powerSet(N / 2);
		System.out.println(result);

	}

	private static void powerSet(int n) {
		for (int i = 0; i < (1 << n); i++) {
			for (int j = 0; j < n; j++) {
				if (((1 << j) & i) > 0) {
					check[j] = true;
				}
			}
			solve();
			for (int j = 0; j < n; j++) {
				check[j] = false;
			}
		}
	}

	private static void solve() {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(num[0]);

		for (int i = 0; i < check.length; i++) {
			if (check[i]) {
				int temp = ((LinkedList<Integer>) q).pollLast();
				if (ope[i] == '+') {
					q.add(temp + num[i + 1]);
				} else if (ope[i] == '-') {
					q.add(temp - num[i + 1]);
				} else {
					q.add(temp * num[i + 1]);
				}
				if (i < check.length - 1)
					check[i + 1] = false;
			} else {
				q.add(ope[i]);
				q.add(num[i + 1]);
			}
		}
		sum = q.poll();
		while (!q.isEmpty()) {
			int 연산자 = q.poll();
			int 피연산자 = q.poll();
			if (연산자 == '+') {
				sum += 피연산자;
			} else if (연산자 == '-') {
				sum -= 피연산자;
			} else {
				sum *= 피연산자;
			}
		}
		result = Math.max(sum, result);
		sum = 0;
	}

}