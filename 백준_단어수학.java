import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 백준_단어수학 {
	static ArrayList<Integer> list;
	static ArrayList<Integer> order;
	static boolean[] visited;
	static int cnt, N;
	static char[][] ch;
	static int sum;
	static int max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		sum = 0;
		max = Integer.MIN_VALUE;
		ch = new char[N][];
		cnt = 0;
		list = new ArrayList<Integer>();
		order = new ArrayList<Integer>();
		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			ch[i] = s.toCharArray();
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < ch[i].length; j++) {
				if (!order.contains(ch[i][j] - 65)) {
					order.add(ch[i][j] - 65);
				}
			}
		}
		visited = new boolean[order.size()];
		solve(0);
		System.out.println(max);
	}

	private static void solve(int depth) {
		if (depth == order.size()) {
			cal();
			max = Math.max(max, sum);
			sum = 0;
			return;
		}
		for (int i = 0; i < order.size(); i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			list.add(9 - i);
			solve(depth + 1);
			list.remove(list.size() - 1);
			visited[i] = false;
		}
	}

	private static void cal() {
		for (int i = 0; i < N; i++) {
			int num = 0;
			for (int j = 0; j < ch[i].length; j++) {
				num *= 10;
				num += list.get(order.indexOf(ch[i][j] - 65));
			}
			sum += num;
		}

	}
}
