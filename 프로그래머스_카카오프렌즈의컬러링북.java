import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 프로그래머스_카카오프렌즈의컬러링북 {
	static boolean[][] visited;
	static int maxSize = 0;

	public static void main(String[] args) {
		int m = 6;
		int n = 4;
		int[][] picture = { { 1, 1, 1, 0 }, { 1, 2, 2, 0 }, { 1, 0, 0, 1 }, { 0, 0, 0, 1 }, { 0, 0, 0, 3 },
				{ 0, 0, 0, 3 } };
		int[] arr = solution(m, n, picture);
		System.out.println(Arrays.toString(arr));
	}

	private static int[] solution(int m, int n, int[][] picture) {
		int cnt = 0;
		maxSize = 0;
		visited = new boolean[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (picture[i][j] != 0 && !visited[i][j]) {
					bfs(i, j, m, n, picture);
					cnt++;
				}
			}
		}
		int[] res = new int[2];
		res[0] = cnt;
		res[1] = maxSize;
		return res;
	}

	private static void bfs(int x, int y, int m, int n, int[][] picture) {
		visited[x][y] = true;
		int size = 1;
		int color = picture[x][y];
		int[] dx = { -1, 0, 1, 0 };
		int[] dy = { 0, 1, 0, -1 };
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(x, y));
		while (!q.isEmpty()) {
			Node node = q.poll();
			for (int i = 0; i < 4; i++) {
				int tx = node.x + dx[i];
				int ty = node.y + dy[i];
				if (tx < 0 || ty < 0 || tx > m - 1 || ty > n - 1 || visited[tx][ty])
					continue;
				if (picture[tx][ty] == color) {
					q.add(new Node(tx, ty));
					visited[tx][ty] = true;
					size++;
				}
			}
		}
		maxSize = Math.max(maxSize, size);
		
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
