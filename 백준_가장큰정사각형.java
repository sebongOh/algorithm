import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 백준_가장큰정사각형 {
	static int[] dx = { -1, -1, 0 };
	static int[] dy = { 0, -1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		int M = Integer.parseInt(str[1]);
		int[][] dp = new int[N + 1][M + 1];

		for (int i = 0; i < N; i++) {
			str = br.readLine().split("");
			for (int j = 0; j < M; j++) {
				dp[i + 1][j + 1] = Integer.parseInt(str[j]);
			}
		}
		int max = 0;
		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[i].length; j++) {
				if (dp[i][j] == 1) {
					int min = 999;
					for (int d = 0; d < 3; d++) {
						int tx = i + dx[d];
						int ty = j + dy[d];
						min = Math.min(min, dp[tx][ty]);
					} // for(dir)
					dp[i][j] = min + 1;
					max = Math.max(max, dp[i][j]);
				}
			}
		}

		System.out.println((int) Math.pow(max, 2));

	}
}
