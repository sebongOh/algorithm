import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_구슬탈출2 {
	static int N, M;
	static char[][] arr;
	static boolean[][][][] visited;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int cnt = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		int bx = 0, by = 0, rx = 0, ry = 0;
		arr = new char[N][M];
		visited = new boolean[N][M][N][M];

		for (int i = 0; i < N; i++) {
			str = br.readLine().split("");
			for (int j = 0; j < M; j++) {
				arr[i][j] = str[j].charAt(0);
				if (arr[i][j] == 'B') {
					bx = i;
					by = j;
				} else if (arr[i][j] == 'R') {
					rx = i;
					ry = j;
				}
			}
		}

		
		
		System.out.println(go(rx, ry, bx, by));

	}

	private static int go(int rx, int ry, int bx, int by) {
		Queue<ball> q = new LinkedList<>();
		q.add(new ball(rx, ry, bx, by, 0));
		visited[rx][ry][bx][by] = true;
		while (!q.isEmpty()) {
			ball b = q.poll();
			if (b.cnt > 10)
				continue;
			if (arr[b.bx][b.by] == 'O')
				continue;
			if (arr[b.rx][b.ry] == 'O')
				return b.cnt;

			for (int i = 0; i < 4; i++) {

				int nrx = b.rx;
				int nry = b.ry;
				while (true) {
					if (arr[nrx][nry] != '#' && arr[nrx][nry] != 'O') {
						nrx += dx[i];
						nry += dy[i];
					} else {
						if (arr[nrx][nry] == '#') {
							nrx -= dx[i];
							nry -= dy[i];
						}
						break;
					}
				}
				int nbx = b.bx;
				int nby = b.by;
				while (true) {
					if (arr[nbx][nby] != '#' && arr[nbx][nby] != 'O') {
						nbx += dx[i];
						nby += dy[i];
					} else {
						if (arr[nbx][nby] == '#') {
							nbx -= dx[i];
							nby -= dy[i];
						}
						break;

					}
				}

				if (nrx == nbx && nry == nby) {
					if (arr[nbx][nby] != 'O') {
						int red = Math.abs(nrx - b.rx) + Math.abs(nry - b.ry);
						int blue = Math.abs(nbx - b.bx) + Math.abs(nby - b.by);
						if (red > blue) {
							nrx -= dx[i];
							nry -= dy[i];
						} else {
							nbx -= dx[i];
							nby -= dy[i];
						}
					}
				}

				if (!visited[nrx][nry][nbx][nby]) {
					visited[nrx][nry][nbx][nby] = true;
					q.add(new ball(nrx, nry, nbx, nby, b.cnt + 1));
				}

			}

		} // while
		return -1;
	}

	static class ball {
		int rx;
		int ry;
		int bx;
		int by;
		int cnt;

		public ball(int rx, int ry, int bx, int by, int cnt) {
			this.rx = rx;
			this.ry = ry;
			this.bx = bx;
			this.by = by;
			this.cnt = cnt;
		}

	}
}
