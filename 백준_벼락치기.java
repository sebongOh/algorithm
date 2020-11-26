import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 백준_벼락치기 {
	static int N, T;
	static int[][] dp;
	static int[] K;
	static int[] S;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		T = Integer.parseInt(str[1]);
		dp = new int[N + 1][T + 1];
		K = new int[N + 1];
		S = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			str = br.readLine().split(" ");
			K[i] = Integer.parseInt(str[0]);
			S[i] = Integer.parseInt(str[1]);
		}

//		System.out.println(Arrays.toString(K));
//		System.out.println(Arrays.toString(S));

		for (int i = 1; i <= N; i++) { // 갯수
			for (int j = 1; j <= T; j++) { // 1~10000 무게
				dp[i][j] = dp[i - 1][j];
				if (j - K[i] >= 0) {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - K[i]] + S[i]);
				}
			}
		}

//		for (int[] a : dp) {
//			System.out.println(Arrays.toString(a));
//		}

		System.out.println(dp[N][T]);

	}

}
