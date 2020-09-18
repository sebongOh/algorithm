import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_적록색약 {
	static int N;
	static char[][] arr;
	static boolean[][] visited;
	static int cnt;
	static int RGcnt;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new char[N][N];
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().toCharArray();
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					bfs(i, j);
					cnt++;
				}
			}
		}
		init();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					rgbfs(i, j);
					RGcnt++;
				}
			}
		}
		
		System.out.print(cnt+" "+RGcnt);
	}

	private static void rgbfs(int x, int y) {
		int tx = 0;
		int ty = 0;

		Queue<Node> q = new LinkedList<>();
		q.add(new Node(x, y));
		char temp = arr[x][y];
		visited[x][y] = true;
		while (!q.isEmpty()) {
			Node n = q.poll();
			for (int i = 0; i < 4; i++) {
				tx = n.x + dx[i];
				ty = n.y + dy[i];
				if (tx < 0 || tx > N - 1 || ty < 0 || ty > N - 1)
					continue;
				if (!visited[tx][ty]) {
					if (temp == 'B') {
						if (arr[tx][ty] == temp) {
							visited[tx][ty] = true;
							q.add(new Node(tx, ty));
						}
					} else {
						if (arr[tx][ty] == 'R' || arr[tx][ty] == 'G') {
							visited[tx][ty] = true;
							q.add(new Node(tx, ty));
						}
					}
				}
			}
		}

	}

	private static void bfs(int x, int y) {
		int tx = 0;
		int ty = 0;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(x, y));
		visited[x][y] = true;
		char temp = arr[x][y];
		while (!q.isEmpty()) {
			Node n = q.poll();
			for (int i = 0; i < 4; i++) {
				tx = n.x + dx[i];
				ty = n.y + dy[i];
				if (tx < 0 || tx > N - 1 || ty < 0 || ty > N - 1)
					continue;
				if (!visited[tx][ty] && arr[tx][ty] == temp) {
					visited[tx][ty] = true;
					q.add(new Node(tx, ty));
				}
			}
		}
	}

	private static void init() {
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited[i], false);
		}
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
