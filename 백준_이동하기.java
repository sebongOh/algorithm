import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 백준_이동하기 {
	static int N, M, res;
	static int[][] map;
	static int[][] dp;
	static int[] dx = { -1, 0, -1 };
	static int[] dy = { 0, -1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		res = Integer.MIN_VALUE;
		map = new int[N][M];
		dp = new int[N][M];
		for (int i = 0; i < N; i++) {
			str = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}
		dp[0][0] = map[0][0];
		int tx = 0;
		int ty = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int k = 0; k < 3; k++) {
					tx = i + dx[k];
					ty = j + dy[k];
					if (tx < 0 || tx > N - 1 || ty < 0 || ty > M - 1)
						continue;
					dp[i][j] = Math.max(dp[tx][ty] + map[i][j], dp[i][j]);
				}
			}
		}
		System.out.println(dp[N - 1][M - 1]);

	}

}
