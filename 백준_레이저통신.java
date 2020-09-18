import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_레이저통신 {
	static int N, M;
	static char[][] arr;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static Node[] a;
	static int[][] count;
	static int result;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		result = Integer.MAX_VALUE;
		arr = new char[M][N];
		count = new int[M][N];

		a = new Node[2];

		for (int i = 0; i < M; i++) {
			str = br.readLine().split("");
			for (int j = 0; j < N; j++) {
				arr[i][j] = str[j].charAt(0);
				if (arr[i][j] == 'C') {
					if (a[0] == null) {
						a[0] = new Node(i, j, -1, 0);
					} else {
						a[1] = new Node(i, j, -1, 0);
					}
				}
				count[i][j] = Integer.MAX_VALUE;
			}
		}
		
		
		bfs();
		System.out.println(result);

	}

	private static void bfs() {
		int tx = 0;
		int ty = 0;

		Queue<Node> q = new LinkedList<>();
		q.add(new Node(a[0].x, a[0].y, a[0].dir, a[0].cnt));
		count[a[0].x][a[0].y] = 0;
		while (!q.isEmpty()) {
			Node n = q.poll();
			if (n.x == a[1].x && n.y == a[1].y) {
				if(result>n.cnt) {
					result = n.cnt;
				}
			}
			
			for (int i = 0; i < 4; i++) {
				tx = n.x + dx[i];
				ty = n.y + dy[i];
				int c = n.cnt;

				if (tx < 0 || tx > M - 1 || ty < 0 || ty > N - 1)
					continue;
				if (arr[tx][ty] == '*')
					continue;
				if (n.dir != i && n.dir != -1)
					c++;
				if (count[tx][ty] < c)
					continue;
				count[tx][ty] = c;
				q.add(new Node(tx, ty, i, c));

			}
		}
	}

	static class Node {
		int x;
		int y;
		int dir;
		int cnt;

		public Node(int x, int y, int dir, int cnt) {
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", dir=" + dir + ", cnt=" + cnt + "]";
		}

	}

}
