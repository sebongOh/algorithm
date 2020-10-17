import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 백준_k번째수 {
	static int N, K;
	static long res;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		res = 0;
		long left = 1;
		long right = K;

		long ans = bSearch(left, right);

		System.out.println(ans);
	}

	private static long bSearch(long left, long right) {
		int cnt = 0;
		long mid = (left + right) / 2;
		if (left > right) {
			return res;
		}
		for (int i = 1; i <= N; i++) {
			cnt += Math.min(mid / i, N);
		}
		if (K <= cnt) {
			res = mid;
			return bSearch(left, mid - 1);
		} else {
			return bSearch(mid + 1, right);
		}
	}
}
