import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SWEA_트리흑백색칠 {
	static final int mod = 1000000007;
	static int N;
	static List<Integer>[] list;
	static long[][] memo;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		String[] str;
		for (int tc = 1; tc <= t; tc++) {
			N = Integer.parseInt(br.readLine());
			list = new ArrayList[N + 1];
			for (int i = 0; i <= N; i++) {
				list[i] = new ArrayList<Integer>();
			}
			for (int i = 1; i < N; i++) {
				str = br.readLine().split(" ");
				int a = Integer.parseInt(str[0]);
				int b = Integer.parseInt(str[1]);
				list[a].add(b);
				list[b].add(a);
			}
			memo = new long[2][N + 1];

			long ans = (dfs(1, 0, -1) + dfs(1, 1, -1)) % mod;

			System.out.println("#" + tc + " " + ans);

		}

	}

	static long dfs(int v, int color, int parent) {
		// memo[color][v] 값이 있다면 계산하지 않고 그 값 리턴
		if (memo[color][v] != 0)
			return memo[color][v];

		long ret = 1;

		// color가 흑(0)인 경우
		// 자식 노드들을 백(1)으로 칠한 경우의 수들 곱
		if (color == 0) {
			for (int i = 0; i < list[v].size(); i++) {
				if (list[v].get(i) != parent) {
					ret *= dfs(list[v].get(i), 1, v);
					ret %= mod;
				}
			}
		}
		// color가 백(1)인 경우
		// 자식 노드들을 흑(0)으로 칠한 경우의 수들 곱
		// +
		// 자식 노드들을 백(1)으로 칠한 경우의 수들 곱
		else {
			for (int i = 0; i < list[v].size(); i++) {
				if (list[v].get(i) != parent) {
					ret *= dfs(list[v].get(i), 1, v) + dfs(list[v].get(i), 0, v);
					ret %= mod;
				}
			}
		}

		// memo[color][v] 에 기록
		memo[color][v] = ret;
		return ret;
	}
}
