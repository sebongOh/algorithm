import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_인성문제있어 {
	static int W, H, O, F, sx, sy, ex, ey; // O : 장애물개수, F: 초기 힘
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static boolean flag;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		int t = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < t; tc++) {
			str = br.readLine().split(" ");
			flag = false;
			H = Integer.parseInt(str[0]);
			W = Integer.parseInt(str[1]);
			O = Integer.parseInt(str[2]);
			F = Integer.parseInt(str[3]);
			sx = Integer.parseInt(str[4]) - 1;
			sy = Integer.parseInt(str[5]) - 1;
			ex = Integer.parseInt(str[6]) - 1;
			ey = Integer.parseInt(str[7]) - 1;
			map = new int[H][W];
			visited = new boolean[H][W];
			for (int i = 0; i < O; i++) {
				str = br.readLine().split(" ");
				int x = Integer.parseInt(str[0]) - 1;
				int y = Integer.parseInt(str[1]) - 1;
				map[x][y] = Integer.parseInt(str[2]);
			}
			for (int[] a : map) {
				System.out.println(Arrays.toString(a));
			}

			solve();
			if (flag) {
				System.out.println("잘했어!!");
			} else {
				System.out.println("인성 문제있어??");
			}
		} // tc

	}

	private static void solve() {
		int tx = 0, ty = 0;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(sx, sy, F));
		visited[sx][sy] = true;
		while (!q.isEmpty()) {
			Node n = q.poll();
			if (n.power == 0) {
				continue;
			}
			if (n.x == ex && n.y == ey) {
				flag = true;
				break;
			}
			for (int i = 0; i < 4; i++) {
				tx = n.x + dx[i];
				ty = n.y + dy[i];
				if (tx < 0 || tx > H - 1 || ty < 0 || ty > W - 1)
					continue;
				if (!visited[tx][ty]) {
					if (map[tx][ty] <= map[n.x][n.y]) {
						q.add(new Node(tx, ty, n.power - 1));
						visited[tx][ty] = true;
					} else {
						int temp = map[tx][ty] - map[n.x][n.y];
						if (n.power >= temp) {
							q.add(new Node(tx, ty, n.power - 1));
							visited[tx][ty] = true;
						}
					}
				}
			}
		}

	}

	static class Node {
		int x;
		int y;
		int power;

		public Node(int x, int y, int power) {
			super();
			this.x = x;
			this.y = y;
			this.power = power;
		}

	}
}
