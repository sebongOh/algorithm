import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SWEA_수지의수지맞는여행 {
	static int N, M;
	static char[][] arr;
	static int[] check;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		String[] str;
		for (int tc = 1; tc <= t; tc++) {
			result = Integer.MIN_VALUE;
			str = br.readLine().split(" ");
			N = Integer.parseInt(str[0]);
			M = Integer.parseInt(str[1]);
			arr = new char[N][M];
			check = new int[26];
			for (int i = 0; i < N; i++) {
				str = br.readLine().split("");
				for (int j = 0; j < M; j++) {
					arr[i][j] = str[j].charAt(0);
				}
			}
			check[arr[0][0] - 'A'] = 1;
			solve(0, 0, 1);

			System.out.println("#" + tc + " " + result);
		} // t
	}

	private static void solve(int x, int y, int max) {
		if (result < max) {
			result = max;
		}
		if (result == 26)
			return;
		for (int i = 0; i < 4; i++) {
			int tx = x + dx[i];
			int ty = y + dy[i];
			if (tx < 0 || tx > N - 1 || ty < 0 || ty > M - 1)
				continue;
			if (check[arr[tx][ty] - 'A'] == 0) {
				check[arr[tx][ty] - 'A'] = 1;
				solve(tx, ty, max + 1);
				check[arr[tx][ty] - 'A'] = 0;
			}
		}
	}
}
