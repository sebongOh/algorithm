
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 백준_파이어볼테스트2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static Queue<FireBall> q = new LinkedList<FireBall>();
	static Queue<XY> xyq = new LinkedList<XY>();
	static int N, M, K, result;
	static Map[][] map;

	public static void main(String[] args) throws IOException {
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new Map[N][N];
		for (int a = 0; a < M; a++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			q.add(new FireBall(x, y, m, s, d, 1));
		}
		while (K >= 0) {
			K--;
			makeMap();
			while (!q.isEmpty()) {
				FireBall fb = q.poll();
				if (fb.dir < 8) {
					int qx = fb.x + dx[fb.dir] * fb.spd;
					qx = check(qx);
					int qy = fb.y + dy[fb.dir] * fb.spd;
					qy = check(qy);
					if (map[qx][qy].cnt != 0) {
						map[qx][qy].dir = checkDir(map[qx][qy].dir, fb.dir);
					} else {
						map[qx][qy].dir = fb.dir;
					}
					map[qx][qy].sMass += fb.mass;
					map[qx][qy].sSpd += fb.spd;
					map[qx][qy].cnt += 1;
					xyq.add(new XY(qx, qy));
				} else if (fb.dir == 10) {
					fb.mass = fb.mass / 5;
					fb.spd = fb.spd / fb.cnt;
					for (int a = 1; a < 8; a += 2) { // 대각선으로 퍼지기
						int qx = fb.x + dx[a] * fb.spd;
						qx = check(qx);
						int qy = fb.y + dy[a] * fb.spd;
						qy = check(qy);
						if (map[qx][qy].cnt != 0) {
							map[qx][qy].dir = checkDir(map[qx][qy].dir, a);
						} else {
							map[qx][qy].dir = a;
						}
						map[qx][qy].sMass += fb.mass;
						map[qx][qy].sSpd += fb.spd;
						map[qx][qy].cnt += 1;
						xyq.add(new XY(qx, qy));
					}
				} else {
					fb.mass = fb.mass / 5;
					fb.spd = fb.spd / fb.cnt;
					for (int a = 0; a < 8; a += 2) { // 십자로 퍼지기
						int qx = fb.x + dx[a] * fb.spd;
						qx = check(qx);
						int qy = fb.y + dy[a] * fb.spd;
						qy = check(qy);
						if (map[qx][qy].cnt != 0) {
							map[qx][qy].dir = checkDir(map[qx][qy].dir, a);
						} else {
							map[qx][qy].dir = a;
						}
						map[qx][qy].sMass += fb.mass;
						map[qx][qy].sSpd += fb.spd;
						map[qx][qy].cnt += 1;
						xyq.add(new XY(qx, qy));
					}
				}
			}
			while (!xyq.isEmpty()) {
				XY loc = xyq.poll();
				if (map[loc.x][loc.y].sMass == 0)
					continue;
				int m = map[loc.x][loc.y].sMass;
				int s = map[loc.x][loc.y].sSpd;
				int d = map[loc.x][loc.y].dir;
				int cnt = map[loc.x][loc.y].cnt;
				q.add(new FireBall(loc.x, loc.y, m, s, d, cnt));
				map[loc.x][loc.y].sMass = 0;
			}
		}
		sum();
		System.out.println(result);
	}

	private static void sum() {
		while (!q.isEmpty()) {
			FireBall fb = q.poll();
			result += fb.mass;
		}

	}

	private static int checkDir(int dir, int dir2) {
		if (dir == 10) {
			return 10;
		} else if (dir % 2 == 0 && dir2 % 2 == 0) {
			return 8;
		} else if (dir % 2 == 1 && dir2 % 2 == 1) {
			return 9;
		} else
			return 10;
	}

	private static void makeMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new Map(0, 0, 0, 0);
			}
		}
	}

	private static int check(int c) {
		if (c < 0) {
			c = N - (Math.abs(c) % N);
		} else if (c >= N) {
			c = c % N;
		}
		return c;
	}

	static class FireBall {
		int x;
		int y;
		int mass;
		int spd;
		int dir;
		int cnt;

		public FireBall(int x, int y, int mass, int spd, int dir, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.mass = mass;
			this.spd = spd;
			this.dir = dir;
			this.cnt = cnt;
		}

	}

	static class Map {
		int sMass;
		int sSpd;
		int dir;
		int cnt;

		public Map(int sMass, int sSpd, int dir, int cnt) {
			super();
			this.sMass = sMass;
			this.sSpd = sSpd;
			this.dir = dir;
			this.cnt = cnt;
		}
	}

	static class XY {
		int x, y;

		public XY(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}
}