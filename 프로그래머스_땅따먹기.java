import java.util.Arrays;

public class 프로그래머스_땅따먹기 {
	public static void main(String[] args) {
		int[][] land = { { 1, 2, 3, 5 }, { 5, 6, 7, 8 }, { 4, 3, 2, 1 } };
		int res = solution(land);
		System.out.println(res);
	}

	private static int solution(int[][] land) {
		int[][] dp = new int[land.length][4];
		dp = land;
		int max = 0;
		for (int i = 1; i < land.length; i++) {
			for (int j = 0; j < 4; j++) {
				max = 0;
				for (int k = 0; k < 4; k++) {
					if (k == j)
						continue;
					max = Math.max(max, dp[i - 1][k]);
				}
				dp[i][j] += max;
			}
		}
		max = 0;
		for (int i = 0; i < 4; i++) {
			max = Math.max(max, dp[dp.length - 1][i]);
		}

		return max;
	}
}
