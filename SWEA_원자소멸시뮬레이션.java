import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA_원자소멸시뮬레이션 {
	static int[][] map = new int[4002][4002];
	static Queue<Node> q;
	static int result;
	static int[] dx = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		String[] str;
		for (int tc = 1; tc <= t; tc++) {
			result = 0;
			q = new LinkedList<>();
			int N = Integer.parseInt(br.readLine());
			for (int i = 0; i < N; i++) {
				str = br.readLine().split(" ");
				int y = Integer.parseInt(str[0]);
				int x = Integer.parseInt(str[1]);
				int d = Integer.parseInt(str[2]);
				int k = Integer.parseInt(str[3]);
				map[(x + 1000) * 2][(y + 1000) * 2] = k;
				q.add(new Node((x + 1000) * 2, (y + 1000) * 2, d, k));
			} // for
			bfs();
			System.out.println("#" + tc + " " + result);

		} // tc
	}// main

	private static void bfs() {
		int tx = 0;
		int ty = 0;

		while (!q.isEmpty()) {
			Node n = q.poll();
			if (map[n.x][n.y] != n.k) {
				result += map[n.x][n.y];
				map[n.x][n.y] = 0;
				continue;
			}

			tx = n.x + dx[n.d];
			ty = n.y + dy[n.d];

			if (tx >= 0 && tx <= 4000 && ty >= 0 && ty <= 4000) {
				if (map[tx][ty] != 0) {
					map[tx][ty] += n.k;
				} else {
					map[tx][ty] = n.k;
					q.add(new Node(tx, ty, n.d, n.k));
				}
				map[n.x][n.y] = 0;
			}
		}
	}

	static class Node {
		int y;
		int x;
		int d;
		int k;

		public Node(int y, int x, int d, int k) {
			super();
			this.y = y;
			this.x = x;
			this.d = d;
			this.k = k;
		}

		@Override
		public String toString() {
			return "Node [y=" + y + ", x=" + x + ", d=" + d + ", k=" + k + "]";
		}

	}
}
