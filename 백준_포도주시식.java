import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 백준_포도주시식 {
	static int N;
	static int[] arr;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[10001];
		dp = new int[10001];
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		dp[0] = 0;
		dp[1] = arr[1];
		dp[2] = dp[1] + arr[2];
		for (int i = 3; i <= N; i++) {
			int temp = Math.max(dp[i - 3] + arr[i - 1] + arr[i], dp[i - 2] + arr[i]);
			dp[i] = Math.max(dp[i - 1], temp);
		}

		System.out.println(dp[N]);
	}
}
