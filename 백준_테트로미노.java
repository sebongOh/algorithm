import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 백준_테트로미노 {
	static int N, M;
	static int[][] arr;
	static boolean[][] visited;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);

		arr = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			str = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(str[j]);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;
				dfs(i, j, 0, 0);
				visited[i][j] = false;
				go(i, j);
			}
		}

		System.out.println(max);

	}

	private static void go(int x, int y) {
		int sum = arr[x][y];
		if ((x == 0 || x == N - 1) && (y == 0 || y == M - 1)) {
			return;
		}

		if (x == 0) {
			sum += arr[x][y - 1] + arr[x][y + 1] + arr[x + 1][y];
		} else if (x == N - 1) {
			sum += arr[x][y - 1] + arr[x][y + 1] + arr[x - 1][y];
		} else if (y == 0) {
			sum += arr[x + 1][y] + arr[x - 1][y] + arr[x][y + 1];
		} else if (y == M - 1) {
			sum += arr[x + 1][y] + arr[x - 1][y] + arr[x][y - 1];
		} else {
			sum = Math.max(sum, arr[x][y] + arr[x][y - 1] + arr[x][y + 1] + arr[x + 1][y]);
			sum = Math.max(sum, arr[x][y] + arr[x][y - 1] + arr[x][y + 1] + arr[x - 1][y]);
			sum = Math.max(sum, arr[x][y] + arr[x + 1][y] + arr[x - 1][y] + arr[x][y + 1]);
			sum = Math.max(sum, arr[x][y] + arr[x + 1][y] + arr[x - 1][y] + arr[x][y - 1]);
		}
		max = Math.max(max, sum);

	}

	private static void dfs(int x, int y, int depth, int sum) {
		int tx = 0;
		int ty = 0;
		if (depth == 4) {
			max = Math.max(max, sum);
			sum = 0;
			return;
		}
		for (int i = 0; i < 4; i++) {
			tx = x + dx[i];
			ty = y + dy[i];
			if (tx < 0 || tx > N - 1 || ty < 0 || ty > M - 1)
				continue;
			if (!visited[tx][ty]) {
				visited[tx][ty] = true;
				dfs(tx, ty, depth + 1, sum + arr[tx][ty]);
				visited[tx][ty] = false;
			}
		}
	}

}
