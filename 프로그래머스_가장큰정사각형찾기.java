import java.util.Arrays;

public class 프로그래머스_가장큰정사각형찾기 {
	static int[] dx = { -1, -1, 0 };
	static int[] dy = { 0, -1, -1 };

	public static void main(String[] args) {
		int[][] board = { { 0, 1, 1, 1 }, { 1, 1, 1, 1 }, { 1, 1, 1, 1 }, { 0, 0, 1, 0 } };
		int res = solution(board);
		System.out.println(res);
	}

	private static int solution(int[][] board) {
		int res = 0;
		int[][] dp = new int[board.length + 1][board[0].length + 1];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				dp[i + 1][j + 1] = board[i][j];
			}
		}

//		for (int[] a : dp) {
//			System.out.println(Arrays.toString(a));
//		}
		int max = 0;
		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[i].length; j++) {
				if (dp[i][j] == 1) { // 1이면
					int min = 999;
					for (int d = 0; d < 3; d++) {
						int tx = i + dx[d];
						int ty = j + dy[d];
						min = Math.min(min, dp[tx][ty]);
					}
					dp[i][j] = min + 1;
					max = Math.max(dp[i][j], max);
				}
			}
		}

//		for (int[] a : dp) {
//			System.out.println(Arrays.toString(a));
//		}

		return (int) Math.pow(max, 2);
	}
}
