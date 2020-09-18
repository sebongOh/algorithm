import java.util.LinkedList;
import java.util.Queue;

public class 카카오2020인턴_경주로건설 {
	static int[][] arr;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int N;
	static int Min = Integer.MAX_VALUE;

	public static void main(String[] args) {
		int[][] board = { { 0, 0, 0, 0, 0, 0, 0, 1 }, { 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 1, 0, 0 },
				{ 0, 0, 0, 0, 1, 0, 0, 0 }, { 0, 0, 0, 1, 0, 0, 0, 1 }, { 0, 0, 1, 0, 0, 0, 1, 0 },
				{ 0, 1, 0, 0, 0, 1, 0, 0 }, { 1, 0, 0, 0, 0, 0, 0, 0 } };
		int answer = 0;
		N = board.length;
		arr = board;
		bfs();
		answer = Min;
	}

	private static void bfs() {
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 0, 0, -1));
		arr[0][0] = 1;
		while (!q.isEmpty()) {
			Node n = q.poll();
			if (n.x == N - 1 && n.y == N - 1) {
				Min = Math.min(Min, n.cost);
				continue;
			}
			for (int i = 0; i < 4; i++) {
				int tx = n.x + dx[i];
				int ty = n.y + dy[i];
				if (tx < 0 || tx > N - 1 || ty < 0 || ty > N - 1 || arr[tx][ty] == 1) {
					continue;
				}
				int cost = 0;
				if (n.dir == -1 || n.dir == i) {
					cost = n.cost + 100;
				} else {
					cost = n.cost + 600;
				}
				if (arr[tx][ty] == 0) {
					arr[tx][ty] = cost;
					q.add(new Node(tx, ty, cost, i));
				} else {
					if (arr[tx][ty] >= cost) {
						arr[tx][ty] = cost;
						q.add(new Node(tx, ty, cost, i));
					}
				}
			}

		}
	}

	static class Node {
		int x;
		int y;
		int cost;
		int dir;

		public Node(int x, int y, int cost, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.cost = cost;
			this.dir = dir;
		}

	}
}
