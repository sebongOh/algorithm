import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_게임 {
	static int N, M;
	static int[][] arr;
	static int[][] memorization;
	static boolean[][] visited;
	static boolean flag;
	static int[] dx = { -1, 0, 1, 0 }; // 상우하좌
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		arr = new int[N][M];
		memorization = new int[N][M];
		visited = new boolean[N][M];
		flag = false;
		int max = 0;
		String s = "";
		for (int i = 0; i < N; i++) {
			s = br.readLine();
			for (int j = 0; j < M; j++) {
				if (s.charAt(j) == 'H') {
					arr[i][j] = -1;
				} else {
					arr[i][j] = s.charAt(j) - '0';
				}
			}
		}
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(arr[i][j]);
//			}
//			System.out.println();
//		}
		visited[0][0] = true;
		memorization[0][0] = 1;
		solve(0, 0, 1);
		if (flag) {
			System.out.println("-1");
		} else {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					max = Math.max(max, memorization[i][j]);
				}
			}
			System.out.println(max);
		}
	}

	private static void solve(int x, int y, int cnt) {
		int tx = 0;
		int ty = 0;
		for (int i = 0; i < 4; i++) {
			tx = arr[x][y] * dx[i] + x;
			ty = arr[x][y] * dy[i] + y;
			if (tx < 0 || tx > N - 1 || ty < 0 || ty > M - 1 || arr[tx][ty] == -1)
				continue;
			if (visited[tx][ty]) {
				flag = true;
				return;
			}
			if (memorization[tx][ty] > cnt) {
				continue;
			}
			memorization[tx][ty] = cnt + 1;
			visited[tx][ty] = true;
			solve(tx, ty, cnt + 1);
			visited[tx][ty] = false;
			if (flag) {
				return;
			}
		}
	}
}
