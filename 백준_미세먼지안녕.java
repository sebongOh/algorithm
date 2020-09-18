import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import org.omg.PortableServer.ID_UNIQUENESS_POLICY_ID;

public class 백준_미세먼지안녕 {
	static int R, C, T;
	static int[][] map;
	static int[][] spread;
	static Queue<Node> q;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static Dust[] D;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		R = Integer.parseInt(str[0]);
		C = Integer.parseInt(str[1]);
		T = Integer.parseInt(str[2]);
		map = new int[R][C];
		spread = new int[R][C];
		D = new Dust[2];
		q = new LinkedList<>();
		for (int i = 0; i < R; i++) {
			str = br.readLine().split(" ");
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(str[j]);
				if (map[i][j] == -1) {
					if (D[0] == null) {
						D[0] = new Dust(i, j);
					} else {
						D[1] = new Dust(i, j);
					}
				}
			}
		}

		for (int i = 0; i < T; i++) {
			spread();
			clean();
		}

		int result = count();
		System.out.println(result);

	}

	private static int count() {
		int cnt = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 0)
					cnt += map[i][j];
			}
		}
		return cnt;
	}

	private static void clean() {

		for (int i = D[0].x - 1; i > 0; i--) {
			map[i][0] = map[i - 1][0];
		}
		// 왼쪽
		for (int j = 0; j < C - 1; j++) {
			map[0][j] = map[0][j + 1];
		}
		// 위쪽
		for (int i = 0; i < D[0].x; i++) {
			map[i][C - 1] = map[i + 1][C - 1];
		}
		// 오른쪽
		for (int j = C - 1; j > 0; j--) {
			map[D[0].x][j] = map[D[0].x][j - 1];
		}
		map[D[0].x][1] = 0;
		// 아래쪽

		// 공청기2
		for (int i = D[1].x + 1; i < R - 1; i++) {
			map[i][0] = map[i + 1][0];
		}

		// 왼쪽
		for (int j = 0; j < C - 1; j++) {
			map[R - 1][j] = map[R - 1][j + 1];
		}
		// 아래
		for (int i = R - 1; i > D[1].x; i--) {
			map[i][C - 1] = map[i - 1][C - 1];
		}
		// 오른쪽
		for (int j = C - 1; j > 0; j--) {
			map[D[1].x][j] = map[D[1].x][j - 1];
		}
		map[D[1].x][1] = 0;
		//
	}

	private static void spread() {
		int tx = 0;
		int ty = 0;
		int count = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 0) {
					count = 0;
					for (int k = 0; k < 4; k++) {
						tx = i + dx[k];
						ty = j + dy[k];
						if (tx < 0 || tx > R - 1 || ty < 0 || ty > C - 1)
							continue;
						if (map[tx][ty] == -1)
							continue;
						spread[tx][ty] += map[i][j] / 5;
						count++;
					}
					spread[i][j] += map[i][j] - (map[i][j] / 5) * count;
				}
			}
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == -1)
					continue;
				map[i][j] = spread[i][j];
			}
		}
		
		for(int i=0;i<R;i++) {
			Arrays.fill(spread[i], 0);
		}
		
	}

	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + "]";
		}

	}

	static class Dust {
		int x;
		int y;

		public Dust(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}