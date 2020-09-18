import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_미로탈출하기 {
	static int N, M;
	static char[][] map;
	static boolean[][] visited;
	static int cnt = 0;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		map = new char[N][M];
		visited = new boolean[N][M];
		String s;
		int tx = 0;
		int ty = 0;
		Queue<Node> q = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			str = br.readLine().split("");
			for (int j = 0; j < M; j++) {
				if (str[j].equals("U")) {
					map[i][j] = 0;
				} else if (str[j].equals("R")) {
					map[i][j] = 1;
				} else if (str[j].equals("D")) {
					map[i][j] = 2;
				} else if (str[j].equals("L")) {
					map[i][j] = 3;
				}
				tx = i + dx[map[i][j]];
				ty = j + dy[map[i][j]];
				if (tx < 0 || tx > N - 1 || ty < 0 || ty > M - 1) {
					q.add(new Node(i, j));
					cnt++;
				}
			}
		}

		while (!q.isEmpty()) {
			Node n = q.poll();
			for (int i = 0; i < 4; i++) {
				tx = n.x + dx[i];
				ty = n.y + dy[i];
				if (tx < 0 || tx > N - 1 || ty < 0 || ty > M - 1 || visited[tx][ty]) {
					continue;
				}
				if (map[tx][ty] == (i + 2) % 4) {
					visited[tx][ty] = true;
					q.add(new Node(tx, ty));
					cnt++;
				}
			}
		}

		System.out.println(cnt);

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
