import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_두동전 {
	static int N, M;
	static char[][] arr;
	static boolean[][] visited;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int cnt;
	static Node[] dol;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		arr = new char[N][M];
		visited = new boolean[N][M];
		String s;
		dol = new Node[2];
		for (int i = 0; i < N; i++) {
			s = br.readLine();
			arr[i] = s.toCharArray();
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 'o') {
					if (dol[0] == null) {
						dol[0] = new Node(i, j, 0);
					} else {
						dol[1] = new Node(i, j, 0);
					}
				}
			}
		}

		int result = bfs();
		System.out.println(result);
	}

	private static int bfs() {
		int tx = 0;
		int ty = 0;
		int tx2 = 0;
		int ty2 = 0;

		Queue<Node> q = new LinkedList<>();
		q.add(new Node(dol[0].x, dol[0].y, dol[0].time));
		q.add(new Node(dol[1].x, dol[1].y, dol[1].time));
		while (!q.isEmpty()) {
			Node n = q.poll();
			Node n2 = q.poll();
			if (n.time >= 10) {
				return -1;
			}
			for (int i = 0; i < 4; i++) {
				cnt = 0;
				tx = n.x + dx[i];
				ty = n.y + dy[i];
				tx2 = n2.x + dx[i];
				ty2 = n2.y + dy[i];

				if (tx < 0 || tx > N - 1 || ty < 0 || ty > M - 1)
					cnt++;
				if (tx2 < 0 || tx2 > N - 1 || ty2 < 0 || ty2 > M - 1)
					cnt++;
				if (cnt == 2)
					continue;
				if (cnt == 1) {
					return n.time + 1;
				}
				if (arr[tx][ty] == '#' && arr[tx2][ty2] == '#')
					continue;
				if (arr[tx][ty] == '#') {
					q.add(new Node(n.x, n.y, n.time + 1));
					q.add(new Node(tx2, ty2, n.time + 1));
				} else if (arr[tx2][ty2] == '#') {
					q.add(new Node(tx, ty, n.time + 1));
					q.add(new Node(n2.x, n2.y, n2.time + 1));
				} else {
					q.add(new Node(tx, ty, n.time + 1));
					q.add(new Node(tx2, ty2, n.time + 1));
				}
			}
		}
		return -1;

	}

	static class Node {
		int x;
		int y;
		int time;

		public Node(int x, int y, int time) {
			this.x = x;
			this.y = y;
			this.time = time;
		}

	}

}
