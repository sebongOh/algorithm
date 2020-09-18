import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SWEA_핀볼게임 {
	static int[][] map;
	static int[][] hole;
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int N;
	static int cnt;
	static int Max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			Max = Integer.MIN_VALUE;
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			hole = new int[11][4];

			for (int i = 0; i < 11; i++) {
				for (int j = 0; j < 4; j++) {
					hole[i][j] = -1;
				}
			}

			for (int i = 0; i < N; i++) {
				str = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(str[j]);
					if (map[i][j] > 5) {
						if (hole[map[i][j]][0] == -1) {
							hole[map[i][j]][0] = i;
							hole[map[i][j]][1] = j;
						} else {
							hole[map[i][j]][2] = i;
							hole[map[i][j]][3] = j;
						}
					}
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 0) {
						for (int d = 0; d < 4; d++) {
							cnt = 0;
							solve(i, j, d);
							Max = Math.max(Max, cnt);
						}
					}
				}
			} // for

			System.out.println("#" + tc + " " + Max);
		} // tc
	}// main

	private static void solve(int x, int y, int dir) {
		Node start = new Node(x, y, dir);
		int tx = start.x;
		int ty = start.y;
		int d = start.d;

		while (true) {
			tx += dx[d];
			ty += dy[d];

			if (tx == start.x && ty == start.y) {
				return;
			}
			if (tx < 0 || tx > N - 1 || ty < 0 || ty > N - 1) { // 벽에 부딪힘
				cnt++;
				if (d % 2 == 0) {
					d += 1;
				} else {
					d -= 1;
				}
				continue;
			}

			int temp = map[tx][ty];

			switch (temp) {
			case -1:
				return;
			case 0:
				break;
			case 1:
				cnt++;
				if (d == 0) {
					d = 1;
				} else if (d == 1) {
					d = 2;
				} else if (d == 2) {
					d = 3;
				} else if (d == 3) {
					d = 0;
				}
				break;
			case 2:
				cnt++;
				if (d == 0) {
					d = 2;
				} else if (d == 1) {
					d = 0;
				} else if (d == 2) {
					d = 3;
				} else if (d == 3) {
					d = 1;
				}
				break;
			case 3:
				cnt++;
				if (d == 0) {
					d = 3;
				} else if (d == 1) {
					d = 0;
				} else if (d == 2) {
					d = 1;
				} else if (d == 3) {
					d = 2;
				}
				break;
			case 4:
				cnt++;
				if (d == 0) {
					d = 1;
				} else if (d == 1) {
					d = 3;
				} else if (d == 2) {
					d = 0;
				} else if (d == 3) {
					d = 2;
				}
				break;
			case 5:
				cnt++;
				if (d % 2 == 0) {
					d += 1;
				} else {
					d -= 1;
				}
				break;
			default:
				if (hole[temp][0] == tx && hole[temp][1] == ty) {
					tx = hole[temp][2];
					ty = hole[temp][3];
				} else {
					tx = hole[temp][0];
					ty = hole[temp][1];
				}
				break;
			}
		} // while
	}// solve

	static class Node {
		int x;
		int y;
		int d;

		public Node(int x, int y, int d) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", d=" + d + "]";
		}

	}
}
