import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 백준_파이프옮기기2 {
	static int N;
	static int[][] arr;
	static long[][][] dp;
	static int[] dx = { 0, -1, -1 }; // 왼 대각 위
	static int[] dy = { -1, -1, 0 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		String[] str;
		arr = new int[N + 1][N + 1];
		dp = new long[N + 1][N + 1][3];
		for (int i = 1; i < N + 1; i++) {
			str = br.readLine().split(" ");
			for (int j = 1; j < N + 1; j++) {
				arr[i][j] = Integer.parseInt(str[j - 1]);
			}
		}

		dp[1][2][0] = 1; // 파이프시작 위치

		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {

				go(i, j, 0);
				go(i, j, 1);
				go(i, j, 2);
			}
		}

		System.out.println(dp[N][N][0] + dp[N][N][1] + dp[N][N][2]);

	}

	private static void go(int x, int y, int dir) {
		int tx = 0;
		int ty = 0;

		tx = x + dx[dir];
		ty = y + dy[dir];

		if (tx < 1 || tx > N || ty < 1 || ty > N)
			return;
		if (arr[x][y] == 1)
			return;

		if (dir == 0) {
			dp[x][y][0] += (dp[tx][ty][0] + dp[tx][ty][1]);
		} else if (dir == 1) {
			if (arr[x][y - 1] == 1 || arr[x - 1][y] == 1)
				return;
			dp[x][y][1] += (dp[tx][ty][0] + dp[tx][ty][1] + dp[tx][ty][2]);
		} else if (dir == 2) {
			dp[x][y][2] += (dp[tx][ty][1] + dp[tx][ty][2]);
		}
	}

}
