import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_벽부수고이동하기 {
	static int N, M;
	static int[][] arr;
	static int[][] memo;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int result = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		arr = new int[N][M];
		memo = new int[N][M];

		for (int i = 0; i < N; i++) {
			str = br.readLine().split("");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(str[j]);
			}
		}

		for (int i = 0; i < N; i++) {
			Arrays.fill(memo[i], 999);
		}
		memo[0][0] = 0;
		bfs();
		
		if (result == Integer.MAX_VALUE) {
			System.out.println("-1");
		} else {
			System.out.println(result);
		}

	}

	private static void bfs() {
		int tx = 0;
		int ty = 0;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 0, 1, 0));
		while (!q.isEmpty()) {
			Node n = q.poll();

			if (n.x == N - 1 && n.y == M - 1) {
				result = n.cnt;
				break;
			}

			for (int i = 0; i < 4; i++) {
				tx = n.x + dx[i];
				ty = n.y + dy[i];
				if (tx < 0 || tx > N - 1 || ty < 0 || ty > M - 1)
					continue;
				if (memo[tx][ty] <= n.wall)
					continue;
				if (arr[tx][ty] == 0) {
					memo[tx][ty] = n.wall;
					q.add(new Node(tx, ty, n.cnt + 1, n.wall));
				} else {
					if (n.wall == 0) {
						memo[tx][ty] = n.wall + 1;
						q.add(new Node(tx, ty, n.cnt + 1, n.wall + 1));
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

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", cnt=" + cnt + ", wall=" + wall + "]";
		}

	}

}
