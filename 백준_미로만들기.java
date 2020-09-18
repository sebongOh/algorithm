import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_미로만들기 {
	static int N;
	static int[][] map;
	static int[][] memo;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		memo = new int[N][N];
		String[] str;
		for (int i = 0; i < N; i++) {
			str = br.readLine().split("");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}

		for (int i = 0; i < N; i++) {
			Arrays.fill(memo[i], 999);
		}

		bfs();


		System.out.println(memo[N - 1][N - 1]);
	}

	private static void bfs() {
		int tx = 0;
		int ty = 0;
		min = Integer.MAX_VALUE;

		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 0, 0));
		while (!q.isEmpty()) {
			Node n = q.poll();
			for (int i = 0; i < 4; i++) {
				tx = n.x + dx[i];
				ty = n.y + dy[i];
				if (tx < 0 || tx > N - 1 || ty < 0 || ty > N - 1)
					continue;
				if (map[tx][ty] == 1 && memo[tx][ty] > n.black) {
					memo[tx][ty] = n.black;
					q.add(new Node(tx, ty, n.black));
				} else if (map[tx][ty] == 0 && memo[tx][ty] > (n.black + 1)) {
					q.add(new Node(tx, ty, n.black + 1));
					memo[tx][ty] = n.black + 1;
				}
			}
		}

	}

	static class Node {
		int x;
		int y;
		int black;

		public Node(int x, int y, int black) {
			this.x = x;
			this.y = y;
			this.black = black;
		}

	}

}
