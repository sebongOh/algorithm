import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_진우의민트초코우유 {
	static int N, M, H, res;
	static int[][] map;
	static Node n;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static boolean[][][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		H = Integer.parseInt(str[2]);
		visited = new boolean[N][N][1 << 10];
		res = 0;
		map = new int[N][N];
		int idx = 2;
		for (int i = 0; i < N; i++) {
			str = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(str[j]);
				if (map[i][j] == 1) {
					n = new Node(i, j, 0, 0, M);
				} else if (map[i][j] == 2) {
					map[i][j] = idx++;
				}
			}
		}

		visited[n.x][n.y][0] = true;
		solve();
		System.out.println(res);
	}

	private static void solve() {
		int tx = 0, ty = 0;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(n.x, n.y, 0, 0, M));
		while (!q.isEmpty()) {
			Node node = q.poll();
			if (node.power == 0) {
				continue;
			}
			node.power--;

			for (int i = 0; i < 4; i++) {
				tx = node.x + dx[i];
				ty = node.y + dy[i];
				if (tx < 0 || tx > N - 1 || ty < 0 || ty > N - 1)
					continue;
				if (tx == n.x && ty == n.y) {
					res = Math.max(res, node.cnt);
				}

				int tempv = node.v;
				int tempcnt = node.cnt;
				int temppower = node.power;

				if (map[tx][ty] > 1 && (tempv & 1 << (map[tx][ty] - 2)) == 0) {
					tempv = tempv | (1 << (map[tx][ty] - 2));
					tempcnt++;
					temppower += H;
				}

				if (visited[tx][ty][tempv])
					continue;
				visited[tx][ty][tempv] = true;

				q.add(new Node(tx, ty, tempv, tempcnt, temppower));
			}

		}

	}

	static class Node {
		int x;
		int y;
		int v;
		int cnt;
		int power;

		public Node(int x, int y, int v, int cnt, int power) {
			super();
			this.x = x;
			this.y = y;
			this.v = v;
			this.cnt = cnt;
			this.power = power;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", v=" + v + ", cnt=" + cnt + ", power=" + power + "]";
		}

	}
}
