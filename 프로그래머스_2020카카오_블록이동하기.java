import java.util.LinkedList;
import java.util.Queue;

public class 프로그래머스_2020카카오_블록이동하기 {
	static int[][] arr;
	static boolean[][][][] visited;
	static int Min, N;

	public static void main(String[] args) {
		int[][] board = { { 0, 0, 0, 1, 1 }, { 0, 0, 0, 1, 0 }, { 0, 1, 0, 1, 1 }, { 1, 1, 0, 0, 1 },
				{ 0, 0, 0, 0, 0 } };
		arr = board;
		N = board.length;
		visited = new boolean[N][N][N][N];
		Min = Integer.MAX_VALUE;
		int answer = 0;
		solve();
		answer = Min;
		System.out.println(answer);
	}

	private static void solve() {
		int tx = 0;
		int ty = 0;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(0, 0, 0, 1, 0));
		visited[0][0][0][0] = true;
		visited[0][0][0][1] = true;
		while (!q.isEmpty()) {
			Node n = q.poll();
			if (n.x2 == N - 1 && n.y2 == N - 1) {
				Min = n.time;
				return;
			}
			// 4가지 방법
			for (int i = 0; i < 5; i++) {
				if (i == 0) { // 오른쪽으로 이동
					if (n.x == n.x2) { // 가로모양
						tx = n.x2;
						ty = n.y2 + 1;
						if (ty > N - 1 || visited[n.x2][n.y2][tx][ty] || visited[tx][ty][n.x2][n.y2]
								|| arr[tx][ty] == 1) {
							continue;
						}
						visited[n.x2][n.y2][tx][ty] = true;
						q.add(new Node(n.x2, n.y2, tx, ty, n.time + 1));
					} else { // 세로모양
						tx = n.x2;
						ty = n.y2 + 1;
						if (ty > N - 1 || visited[n.x][n.y + 1][n.x2][n.y2 + 1] || visited[n.x2][n.y2 + 1][n.x][n.y + 1]
								|| arr[tx][ty] == 1 || arr[n.x][n.y + 1] == 1) {
							continue;
						}
						visited[n.x][n.y + 1][n.x2][n.y2 + 1] = true;
						q.add(new Node(n.x, n.y + 1, n.x2, n.y2 + 1, n.time + 1));
					}
				} else if (i == 1) { // 왼쪽으로 이동
					if (n.x == n.x2) { // 가로모양
						tx = n.x;
						ty = n.y - 1;
						if (ty < 0 || visited[tx][ty][n.x][n.y] || visited[n.x][n.y][tx][ty] || arr[tx][ty] == 1) {
							continue;
						}
						visited[tx][ty][n.x][n.y] = true;
						q.add(new Node(tx, ty, n.x, n.y, n.time + 1));
					} else { // 세로모양
						tx = n.x;
						ty = n.y - 1;
						if (ty < 0 || visited[tx][ty][n.x2][n.y2 - 1] || visited[n.x2][n.y2 - 1][tx][ty]
								|| arr[tx][ty] == 1 || arr[n.x2][n.y2 - 1] == 1) {
							continue;
						}
						visited[tx][ty][n.x2][n.y2 - 1] = true;
						q.add(new Node(tx, ty, n.x2, n.y2 - 1, n.time + 1));
					}
				} else if (i == 2) { // 위로 이동
					if (n.x == n.x2) { // 가로모양
						tx = n.x - 1;
						ty = n.y;
						if (tx < 0 || visited[n.x - 1][n.y][n.x2 - 1][n.y2] || visited[n.x2 - 1][n.y2][n.x - 1][n.y]
								|| arr[n.x - 1][n.y] == 1 || arr[n.x2 - 1][n.y2] == 1) {
							continue;
						}
						visited[n.x - 1][n.y][n.x2 - 1][n.y] = true;
						q.add(new Node(n.x - 1, n.y, n.x2 - 1, n.y2, n.time + 1));
					} else { // 세로모양
						tx = n.x - 1;
						ty = n.y;
						if (tx < 0 || visited[tx][ty][n.x2 - 1][n.y2] || visited[n.x2 - 1][n.y2][tx][ty]
								|| arr[tx][ty] == 1) {
							continue;
						}
						visited[tx][ty][n.x2 - 1][n.y2] = true;
						q.add(new Node(tx, ty, n.x2 - 1, n.y2, n.time + 1));
					}
				} else if (i == 3) { // 아래로 이동
					if (n.x == n.x2) { // 가로모양
						tx = n.x + 1;
						ty = n.y;
						if (tx > N - 1 || visited[tx][ty][n.x2 + 1][n.y2] || visited[n.x2 + 1][n.y2][tx][ty]
								|| arr[tx][ty] == 1 || arr[n.x2 + 1][n.y2] == 1) {
							continue;
						}
						visited[tx][ty][n.x2 + 1][n.y2] = true;
						q.add(new Node(tx, ty, n.x2 + 1, n.y2, n.time + 1));
					} else { // 세로모양
						tx = n.x2 + 1;
						ty = n.y2;
						if (tx > N - 1 || visited[n.x2][n.y2][tx][ty] || visited[tx][ty][n.x2][n.y2]
								|| arr[tx][ty] == 1) {
							continue;
						}
						visited[n.x2][n.y2][tx][ty] = true;
						q.add(new Node(n.x2, n.y2, tx, ty, n.time + 1));
					}
				} else if (i == 4) { // 회전
					if (n.x == n.x2) { // 가로일때
						///////// 왼쪽좌표값기준
						for (int j = 0; j < 2; j++) {
							// 시계방향
							if (j == 0) {
								tx = n.x + 1;
								ty = n.y + 1;
								if (tx > N - 1 || arr[tx][ty] == 1 || arr[n.x + 1][n.y] == 1) {
									continue;
								}
								if (visited[n.x][n.y][n.x + 1][n.y] || visited[n.x + 1][n.y][n.x][n.y]) {
									continue;
								}
								visited[n.x][n.y][n.x + 1][n.y] = true;
								q.add(new Node(n.x, n.y, n.x + 1, n.y, n.time + 1));
							} else {
								// 반시계
								tx = n.x - 1;
								ty = n.y + 1;
								if (tx < 0 || arr[tx][ty] == 1 || arr[n.x - 1][n.y] == 1) {
									continue;
								}
								if (visited[n.x][n.y][n.x - 1][n.y] || visited[n.x - 1][n.y][n.x][n.y]) {
									continue;
								}
								visited[n.x][n.y][n.x - 1][n.y] = true;
								q.add(new Node(n.x, n.y, n.x - 1, n.y, n.time + 1));
							}

						}
						///////// 오른쪽좌표값기준
						for (int j = 0; j < 2; j++) {
							// 시계방향
							if (j == 0) {
								tx = n.x2 - 1;
								ty = n.y2 - 1;
								if (tx < 0 || arr[tx][ty] == 1 || arr[n.x2 - 1][n.y2] == 1) {
									continue;
								}
								if (visited[n.x2 - 1][n.y2][n.x2][n.y2] || visited[n.x2][n.y2][n.x2 - 1][n.y2]) {
									continue;
								}
								visited[n.x2 - 1][n.y2][n.x2][n.y2] = true;
								q.add(new Node(n.x2 - 1, n.y2, n.x2, n.y2, n.time + 1));
							} else {
								// 반시계
								tx = n.x2 + 1;
								ty = n.y2 - 1;
								if (tx > N - 1 || arr[tx][ty] == 1 || arr[n.x2 + 1][n.y2] == 1) {
									continue;
								}
								if (visited[n.x2][n.y2][n.x2 + 1][n.y2] || visited[n.x2 + 1][n.y2][n.x2][n.y2]) {
									continue;
								}
								visited[n.x2][n.y2][n.x2 + 1][n.y2] = true;
								q.add(new Node(n.x2, n.y2, n.x2 + 1, n.y2, n.time + 1));
							}
						}
					} else { // 세로일때
						// 위쪽좌표기준
						for (int j = 0; j < 2; j++) {
							// 시계방향
							if (j == 0) {
								tx = n.x + 1;
								ty = n.y - 1;
								if (ty < 0 || arr[tx][ty] == 1 || arr[n.x][n.y - 1] == 1) {
									continue;
								}
								if (visited[n.x][n.y][n.x][n.y - 1] || visited[n.x][n.y - 1][n.x][n.y]) {
									continue;
								}
								visited[n.x][n.y][n.x][n.y - 1] = true;
								q.add(new Node(n.x, n.y, n.x, n.y - 1, n.time + 1));
							} else {
								// 반시계
								tx = n.x + 1;
								ty = n.y + 1;
								if (ty > N - 1 || arr[tx][ty] == 1 || arr[n.x][n.y + 1] == 1) {
									continue;
								}
								if (visited[n.x][n.y][n.x][n.y + 1] || visited[n.x][n.y + 1][n.x][n.y]) {
									continue;
								}
								visited[n.x][n.y][n.x][n.y + 1] = true;
								q.add(new Node(n.x, n.y, n.x, n.y + 1, n.time + 1));
							}

						}
						// 아래쪽 좌표기준
						for (int j = 0; j < 2; j++) {
							// 시계방향
							if (j == 0) {
								tx = n.x2 - 1;
								ty = n.y2 + 1;
								if (ty > N - 1 || arr[tx][ty] == 1 || arr[n.x2][n.y2 + 1] == 1) {
									continue;
								}
								if (visited[n.x2][n.y2][n.x2][n.y2 + 1] || visited[n.x2][n.y2 + 1][n.x2][n.y2]) {
									continue;
								}
								visited[n.x2][n.y2][n.x2][n.y2 + 1] = true;
								q.add(new Node(n.x2, n.y2, n.x2, n.y2 + 1, n.time + 1));
							} else {
								// 반시계
								tx = n.x2 - 1;
								ty = n.y2 - 1;
								if (ty < 0 || arr[tx][ty] == 1 || arr[n.x2][n.y2 - 1] == 1) {
									continue;
								}
								if (visited[n.x2][n.y2][n.x2][n.y2 - 1] || visited[n.x2][n.y2 - 1][n.x2][n.y2]) {
									continue;
								}
								visited[n.x2][n.y2][n.x2][n.y2 - 1] = true;
								q.add(new Node(n.x2, n.y2, n.x2, n.y2 - 1, n.time + 1));
							}

						}
					}
				}
			}
		}
	}// solve

	static class Node {
		int x;
		int y;
		int x2;
		int y2;
		int time;

		public Node(int x, int y, int x2, int y2, int time) {
			super();
			this.x = x;
			this.y = y;
			this.x2 = x2;
			this.y2 = y2;
			this.time = time;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", x2=" + x2 + ", y2=" + y2 + ", time=" + time + "]";
		}

	}
}
