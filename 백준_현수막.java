import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_현수막 {
	static int[][] map;
	static boolean[][] visited;
	static int N, M;
	static int[] dx = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int[] dy = { -1, 0, 1, 1, 1, 0, -1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		map = new int[N][M];
		visited = new boolean[N][M];
		int res = 0;
		for (int i = 0; i < N; i++) {
			str = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		for(int [] a : map) {
			System.out.println(Arrays.toString(a));
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j] && map[i][j] == 1) {
					bfs(i, j);
					res++;
				}
			}
		}

		System.err.println(res);

	}

	private static void bfs(int i, int j) {
		int tx = 0, ty = 0;
		visited[i][j] = true;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(i, j));
		while (!q.isEmpty()) {
			Node n = q.poll();
			for (int d = 0; d < 8; d++) {
				tx = n.x + dx[d];
				ty = n.y + dy[d];
				if (tx < 0 || tx > N - 1 || ty < 0 || ty > M - 1)
					continue;
				if (!visited[tx][ty] && map[tx][ty] == 1) {
					visited[tx][ty] = true;
					q.add(new Node(tx, ty));
				}
			}
		} // while

	}

	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

}
