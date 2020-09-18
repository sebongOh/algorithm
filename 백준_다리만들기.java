import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_다리만들기 {
	static int N;
	static int[][] arr;
	static int[][] copy;
	static boolean[][] visited;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int idx;
	static int cnt = 0;
	static boolean flag;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		copy = new int[N][N];
		visited = new boolean[N][N];

		for (int i = 0; i < N; i++) {
			str = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(str[j]);
			}
		}
		idx = 2;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && arr[i][j] == 1) {
					check(i, j);
					idx++;
				}
			}
		}
		init();
		
		int result = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (copy[i][j] != 0 && !visited[i][j]) {
					bfs(i, j);
					if (flag) {
						result = Math.min(result, cnt);
					}
					cnt = 0;
					flag=false;
					init();
				}
			}
		}

		System.out.println(result);

	}

	private static void bfs(int x, int y) {
		int tx = 0;
		int ty = 0;
		Queue<Node> q = new LinkedList<>();
		visited[x][y] = true;
		int temp = copy[x][y];
		q.add(new Node(x, y));
		while (!q.isEmpty()) {
			int qsize = q.size();
			for (int k = 0; k < qsize; k++) {
				Node n = q.poll();
				for (int i = 0; i < 4; i++) {
					tx = n.x + dx[i];
					ty = n.y + dy[i];
					if (tx < 0 || tx > N - 1 || ty < 0 || ty > N - 1)
						continue;
					if (copy[tx][ty] == 0 && !visited[tx][ty]) {
						visited[tx][ty] = true;
						q.add(new Node(tx, ty));
					} else if (copy[tx][ty] != temp && !visited[tx][ty]) {
						flag = true;
						return;
					}
				}
			} // qsize
			cnt++;
		} // while
	}

	private static void init() {
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited[i], false);
		}
	}

	private static void check(int x, int y) {
		int tx = 0;
		int ty = 0;
		Queue<Node> q = new LinkedList<>();
		visited[x][y] = true;
		copy[x][y] = idx;
		q.add(new Node(x, y));
		while (!q.isEmpty()) {
			Node n = q.poll();
			for (int i = 0; i < 4; i++) {
				tx = n.x + dx[i];
				ty = n.y + dy[i];
				if (tx < 0 || tx > N - 1 || ty < 0 || ty > N - 1)
					continue;
				if (!visited[tx][ty] && arr[tx][ty] == 1) {
					copy[tx][ty] = idx;
					visited[tx][ty] = true;
					q.add(new Node(tx, ty));
				}
			}
		}

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