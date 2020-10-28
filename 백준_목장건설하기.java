import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 백준_목장건설하기 {
	static int M, N;
	static int[][] map;
	static int[][] dp;
	static int[] dx = { -1, -1, 0 };
	static int[] dy = { 0, -1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		M = Integer.parseInt(str[0]);
		N = Integer.parseInt(str[1]);
		map = new int[M][N];
		dp = new int[M][N];
		for (int i = 0; i < M; i++) {
			str = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(str[j]);
				dp[i][j] = 1;
				if (map[i][j] > 0) {
					dp[i][j] = 0;
				}
			}
		}
		int tx = 0;
		int ty = 0;
		int MIN = 0;
		for (int i = 1; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (dp[i][j] == 0) {
					continue;
				}
				MIN = Integer.MAX_VALUE;
				int temp = 0;
				for (int d = 0; d < 3; d++) {
					tx = i + dx[d];
					ty = j + dy[d];
					if (tx < 0 || ty < 0) {
						temp = 0;
					} else {
						temp = dp[tx][ty];
					}
					MIN = Math.min(temp, MIN);
				}
				if (MIN == Integer.MAX_VALUE) {
					dp[i][j] = 1;
				} else {
					dp[i][j] = MIN + 1;

				}
			}
		}
		for (int[] a : dp) {
			System.out.println(Arrays.toString(a));
		}
		int res = Integer.MIN_VALUE;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				res = Math.max(res, dp[i][j]);
			}
		}

		System.out.println(res);
	}
}
