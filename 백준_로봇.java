import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_로봇 {
	static int[] dx = { 0, 0, 0, 1, -1 };
	static int[] dy = { 0, 1, -1, 0, 0 };
	static int N, M, res;
	static int[][] map;
	static boolean[][][] visited;
	static Node start, end;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		map = new int[N][M];
		res = Integer.MAX_VALUE;
		visited = new boolean[N][M][5];
		for (int i = 0; i < N; i++) {
			str = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}
		str = br.readLine().split(" ");
		start = new Node(Integer.parseInt(str[0]) - 1, Integer.parseInt(str[1]) - 1, Integer.parseInt(str[2]), 0);
		str = br.readLine().split(" ");
		end = new Node(Integer.parseInt(str[0]) - 1, Integer.parseInt(str[1]) - 1, Integer.parseInt(str[2]), 0);

		bfs();
		System.out.println(res);
	}

	private static void bfs() {
		int tx = 0, ty = 0;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(start.x, start.y, start.d, start.cnt));
		visited[start.x][start.y][start.d] = true;
		while (!q.isEmpty()) {
			Node n = q.poll();
			if (n.x == end.x && n.y == end.y && n.d == end.d) {
				res = Math.min(res, n.cnt);
				continue;
			}
			for (int i = 1; i <= 3; i++) { // 1~3칸 가봄
				tx = n.x + (dx[n.d] * i);
				ty = n.y + (dy[n.d] * i);
				if (tx < 0 || tx > N - 1 || ty < 0 || ty > M - 1)
					continue;
				if (map[tx][ty] == 1) { // 벽이면 그 다음 명령도 어차피 못감
					break;
				}
				if (!visited[tx][ty][n.d]) {
					q.add(new Node(tx, ty, n.d, n.cnt + 1));
					visited[tx][ty][n.d] = true;
				}

			} // for - i

			for (int i = 1; i <= 4; i++) { // 방향 전환
				if (n.d != i && !visited[n.x][n.y][i]) { // 방향 다르고 그 방향 방문안했으면
					int rotaion = 1;
					if (n.d == 1) { // 동쪽..
						if (i == 2) { // 서쪽이면 방향 전환 2번 명령
							rotaion++;
						}
					} else if (n.d == 2) {// 서쪽
						if (i == 1) {
							rotaion++;
						}
					} else if (n.d == 3) {// 남쪽
						if (i == 4) {
							rotaion++;
						}
					} else if (n.d == 4) {// 북쪽
						if (i == 3) {
							rotaion++;
						}
					}
					q.add(new Node(n.x, n.y, i, n.cnt + rotaion));
					visited[n.x][n.y][i] = true;
				}
			}
		} // while

	}

	static class Node {
		int x;
		int y;
		int d;
		int cnt;

		public Node(int x, int y, int d, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", d=" + d + ", cnt=" + cnt + "]";
		}

	}
}
