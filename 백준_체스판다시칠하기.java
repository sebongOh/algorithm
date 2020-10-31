import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 백준_체스판다시칠하기 {
	static int N, M, res, cnt;
	static char[][] map;
	static char[][] WHITE = { { 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B' }, { 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W' },
			{ 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B' }, { 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W' },
			{ 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B' }, { 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W' },
			{ 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B' }, { 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W' }, };

	static char[][] BLACK = { { 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W' }, { 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B' },
			{ 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W' }, { 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B' },
			{ 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W' }, { 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B' },
			{ 'B', 'W', 'B', 'W', 'B', 'W', 'B', 'W' }, { 'W', 'B', 'W', 'B', 'W', 'B', 'W', 'B' }, };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		map = new char[N][M];
		res = Integer.MAX_VALUE;
		cnt = 0;
		for (int i = 0; i < N; i++) {
			map[i] = br.readLine().toCharArray();
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (i + 8 <= N && j + 8 <= M) {
					int cnt = solve(i, j);
					res = Math.min(res, cnt);
				}
			}
		}

		System.out.println(res);

	}

	private static int solve(int x, int y) {
		int cnt = 0;
		int min = Integer.MAX_VALUE;
		for (int i = x; i < x + 8; i++) { // 검은돌시작
			for (int j = y; j < y + 8; j++) {
				if (map[i][j] != BLACK[i - x][j - y]) {
					cnt++;
				}
			}
		}
		min = Math.min(cnt, min);
		cnt = 0;
		for (int i = x; i < x + 8; i++) { // 흰돌시작
			for (int j = y; j < y + 8; j++) {
				if (map[i][j] != WHITE[i - x][j - y]) {
					cnt++;
				}
			}
		}
		min = Math.min(cnt, min);
		return min;

	}

}
