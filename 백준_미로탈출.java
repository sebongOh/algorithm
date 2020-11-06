import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_미로탈출 {
	static int[][] map;
	static int[][] visited;
	static int N, M, cnt, res;
	static int sx, sy, ex, ey;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		map = new int[N][M];
		visited = new int[N][M];
		str = br.readLine().split(" ");
		sx = Integer.parseInt(str[0]) - 1;
		sy = Integer.parseInt(str[1]) - 1;
		str = br.readLine().split(" ");
		ex = Integer.parseInt(str[0]) - 1;
		ey = Integer.parseInt(str[1]) - 1;

		for (int i = 0; i < N; i++) {
			str = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}
		res = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited[i], 999);
		}
		visited[sx][sy] = 0;
		solve();
		if (res == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(res);
		}

	}

	private static void solve() {
		int tx = 0, ty = 0;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(sx, sy, 0, 0));
		while (!q.isEmpty()) {
			Node n = q.poll();
			if (n.x == ex && n.y == ey) {
				res = n.cnt;
				break;
			}
			for (int i = 0; i < 4; i++) {
				tx = n.x + dx[i];
				ty = n.y + dy[i];
				if (tx < 0 || tx > N - 1 || ty < 0 || ty > M - 1)
					continue;
				if (visited[tx][ty] <= n.wall)
					continue;
				if (map[tx][ty] == 0) {
					q.add(new Node(tx, ty, n.cnt + 1, n.wall));
					visited[tx][ty] = n.wall;
				} else if (map[tx][ty] == 1) {
					if (n.wall == 0) {
						q.add(new Node(tx, ty, n.cnt + 1, n.wall + 1));
						visited[tx][ty] = n.wall + 1;
					}
				}
			}
		}

	}

	static class Node {
		int x;
		int y;
		int cnt;
		int wall;

		public Node(int x, int y, int cnt, int wall) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.wall = wall;
		}

	}

}
