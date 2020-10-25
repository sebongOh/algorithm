import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class 백준_영역구하기 {
	static int N, M, K;
	static int[][] map;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static PriorityQueue<Integer> pq;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		K = Integer.parseInt(str[2]);
		map = new int[N][M];
		pq = new PriorityQueue<Integer>();

		for (int i = 0; i < K; i++) {
			str = br.readLine().split(" ");
			int ay = Integer.parseInt(str[0]);
			int ax = Integer.parseInt(str[1]);
			int by = Integer.parseInt(str[2]);
			int bx = Integer.parseInt(str[3]);
			for (int x = ax; x < bx; x++) {
				for (int y = ay; y < by; y++) {
					map[x][y] = 1;
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					int ans = bfs(i, j);
					pq.add(ans);
				}
			}
		}

		System.out.println(pq.size());
		while (!pq.isEmpty()) {
			System.out.print(pq.poll() + " ");
		}

	}

	private static int bfs(int x, int y) {
		int cnt = 0;
		int tx = 0, ty = 0;
		map[x][y] = -1;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(x, y));
		while (!q.isEmpty()) {
			Node n = q.poll();
			for (int i = 0; i < 4; i++) {
				tx = n.x + dx[i];
				ty = n.y + dy[i];
				if (tx < 0 || tx > N - 1 || ty < 0 || ty > M - 1 || map[tx][ty] != 0)
					continue;
				if (map[tx][ty] == 0) {
					q.add(new Node(tx, ty));
					cnt++;
					map[tx][ty] = -1;
				}

			}
		}
		return cnt + 1;

	}

	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}
}
