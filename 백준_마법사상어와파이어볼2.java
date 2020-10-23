import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 백준_마법사상어와파이어볼2 {
	static int N, M, K;
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static ArrayList<Ball>[][] list;

	static public void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		K = Integer.parseInt(str[2]);
		list = new ArrayList[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				list[i][j] = new ArrayList<>();
			}
		}

		for (int i = 0; i < M; i++) {
			str = br.readLine().split(" ");
			int x = Integer.parseInt(str[0]);
			int y = Integer.parseInt(str[1]);
			int m = Integer.parseInt(str[2]);
			int s = Integer.parseInt(str[3]);
			int d = Integer.parseInt(str[4]);
			list[x - 1][y - 1].add(new Ball(m, s, d));
		}

		while (K > 0) {
			go();
			divide();
			K--;
		}
		int res = count();
		System.out.println(res);

	}// main

	private static int count() {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < list[i][j].size(); k++) {
					sum += list[i][j].get(k).m;
				}
			}
		}
		return sum;
	}// count

	private static void divide() {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (list[i][j].size() > 1) { // 2개 이상이면
					int even = 0;
					int odd = 0;
					int sSum = 0;
					int mSum = 0;
					boolean flag = false;
					for (Ball b : list[i][j]) {
						sSum += b.s;
						mSum += b.m;
						if (b.d % 2 == 0) {
							even++;
						} else {
							odd++;
						}
					}
					if (even > 0 && odd > 0) {
						flag = false;
					} else {
						flag = true;
					}
					mSum = mSum / 5;
					sSum = sSum / list[i][j].size();
					list[i][j].clear();
					if (mSum > 0) {
						if (flag) {
							list[i][j].add(new Ball(mSum, sSum, 0));
							list[i][j].add(new Ball(mSum, sSum, 2));
							list[i][j].add(new Ball(mSum, sSum, 4));
							list[i][j].add(new Ball(mSum, sSum, 6));
						} else {
							list[i][j].add(new Ball(mSum, sSum, 1));
							list[i][j].add(new Ball(mSum, sSum, 3));
							list[i][j].add(new Ball(mSum, sSum, 5));
							list[i][j].add(new Ball(mSum, sSum, 7));
						}
					}
				}
			}
		}
	}

	private static void go() {
		int tx = 0;
		int ty = 0;
		ArrayList<Ball>[][] copy = new ArrayList[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				copy[i][j] = new ArrayList<>();
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (list[i][j].size() > 0) { // 볼 있으면
					for (Ball b : list[i][j]) {
						tx = i + dx[b.d] * b.s;
						ty = j + dy[b.d] * b.s;
						if (tx >= N) {
							tx = tx % N;
						} else if (tx < 0) {
							while (tx < 0) {
								tx += N;
							}
						}
						if (ty >= N) {
							ty = ty % N;
						} else if (ty < 0) {
							while (ty < 0) {
								ty += N;
							}
						}
						copy[tx][ty].add(new Ball(b.m, b.s, b.d));
					}
				}

			}
		} // for
		list = copy;
	}// go

	static class Ball {
		int m;
		int s;
		int d;

		public Ball(int m, int s, int d) {
			super();
			this.m = m;
			this.s = s;
			this.d = d;
		}

		@Override
		public String toString() {
			return "Ball [m=" + m + ", s=" + s + ", d=" + d + "]";
		}

	}
}
