import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 백준_마법사상어와토네이도2 {
	static int N;
	static int[][] map;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };

	static int outside;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			str = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		} // map

		int x = N / 2;
		int y = N / 2;
		int tx = 0, ty = 0, idx = 1, temp = 0, dir = 0;
		while (true) {
			for (int i = 0; i < 2; i++) { // 1칸*2 , 2칸*2, 3칸*2....
				temp = idx;
				while (temp > 0) {
					tx = x + dx[dir];
					ty = y + dy[dir];
					int sand = map[tx][ty];
					int sevenPer = (int) (sand * 0.07);
					int twoPer = (int) (sand * 0.02);
					int onePer = (int) (sand * 0.01);
					int tenPer = (int) (sand * 0.1);
					int fivePer = (int) (sand * 0.05);
					if (dir == 0) {
						if (tx - 1 >= 0) {
							map[tx - 1][ty] += sevenPer;
						} else {
							outside += sevenPer;
						}
						if (tx + 1 < N) {
							map[tx + 1][ty] += sevenPer;
						} else {
							outside += sevenPer;
						}
						if (tx - 2 >= 0) {
							map[tx - 2][ty] += twoPer;
						} else {
							outside += twoPer;
						}
						if (tx + 2 < N) {
							map[tx + 2][ty] += twoPer;
						} else {
							outside += twoPer;
						}
						if (tx - 1 >= 0 && ty - 1 >= 0) {
							map[tx - 1][ty - 1] += tenPer;
						} else {
							outside += tenPer;
						}
						if (tx + 1 < N && ty - 1 >= 0) {
							map[tx + 1][ty - 1] += tenPer;
						} else {
							outside += tenPer;
						}
						if (tx - 1 >= 0 && ty + 1 < N) {
							map[tx - 1][ty + 1] += onePer;
						} else {
							outside += onePer;
						}
						if (tx + 1 < N && ty + 1 < N) {
							map[tx + 1][ty + 1] += onePer;
						} else {
							outside += onePer;
						}
						if (ty - 2 >= 0) {
							map[tx][ty - 2] += fivePer;
						} else {
							outside += fivePer;
						}
						if (ty - 1 >= 0) {
							map[tx][ty - 1] += sand - (sevenPer * 2) - (twoPer * 2) - (onePer * 2) - (fivePer)
									- (tenPer * 2);
						} else {
							outside += sand - (sevenPer * 2) - (twoPer * 2) - (onePer * 2) - (fivePer) - (tenPer * 2);
						}
					} else if (dir == 1) {
						if (ty - 1 >= 0) {
							map[tx][ty - 1] += sevenPer;
						} else {
							outside += sevenPer;
						}
						if (ty + 1 < N) {
							map[tx][ty + 1] += sevenPer;
						} else {
							outside += sevenPer;
						}
						if (ty - 2 >= 0) {
							map[tx][ty - 2] += twoPer;
						} else {
							outside += twoPer;
						}
						if (ty + 2 < N) {
							map[tx][ty + 2] += twoPer;
						} else {
							outside += twoPer;
						}
						if (tx + 1 < N && ty - 1 >= 0) {
							map[tx + 1][ty - 1] += tenPer;
						} else {
							outside += tenPer;
						}
						if (tx + 1 < N && ty + 1 < N) {
							map[tx + 1][ty + 1] += tenPer;
						} else {
							outside += tenPer;
						}
						if (tx - 1 >= 0 && ty + 1 < N) {
							map[tx - 1][ty + 1] += onePer;
						} else {
							outside += onePer;
						}
						if (tx - 1 >= 0 && ty - 1 >= 0) {
							map[tx - 1][ty - 1] += onePer;
						} else {
							outside += onePer;
						}
						if (tx + 2 < N) {
							map[tx + 2][ty] += fivePer;
						} else {
							outside += fivePer;
						}
						if (tx + 1 < N) {
							map[tx + 1][ty] += sand - (sevenPer * 2) - (twoPer * 2) - (onePer * 2) - (fivePer)
									- (tenPer * 2);
						} else {
							outside += sand - (sevenPer * 2) - (twoPer * 2) - (onePer * 2) - (fivePer) - (tenPer * 2);
						}
					} else if (dir == 2) {
						if (tx - 1 >= 0) {
							map[tx - 1][ty] += sevenPer;
						} else {
							outside += sevenPer;
						}
						if (tx + 1 < N) {
							map[tx + 1][ty] += sevenPer;
						} else {
							outside += sevenPer;
						}
						if (tx - 2 >= 0) {
							map[tx - 2][ty] += twoPer;
						} else {
							outside += twoPer;
						}
						if (tx + 2 < N) {
							map[tx + 2][ty] += twoPer;
						} else {
							outside += twoPer;
						}
						if (tx - 1 >= 0 && ty + 1 < N) {
							map[tx - 1][ty + 1] += tenPer;
						} else {
							outside += tenPer;
						}
						if (tx + 1 < N && ty + 1 < N) {
							map[tx + 1][ty + 1] += tenPer;
						} else {
							outside += tenPer;
						}
						if (tx - 1 >= 0 && ty - 1 >= 0) {
							map[tx - 1][ty - 1] += onePer;
						} else {
							outside += onePer;
						}
						if (tx + 1 < N && ty - 1 >= 0) {
							map[tx + 1][ty - 1] += onePer;
						} else {
							outside += onePer;
						}
						if (ty + 2 < N) {
							map[tx][ty + 2] += fivePer;
						} else {
							outside += fivePer;
						}
						if (ty + 1 < N) {
							map[tx][ty + 1] += sand - (sevenPer * 2) - (twoPer * 2) - (onePer * 2) - (fivePer)
									- (tenPer * 2);
						} else {
							outside += sand - (sevenPer * 2) - (twoPer * 2) - (onePer * 2) - (fivePer) - (tenPer * 2);
						}
					} else if (dir == 3) {
						if (ty - 1 >= 0) {
							map[tx][ty - 1] += sevenPer;
						} else {
							outside += sevenPer;
						}
						if (ty + 1 < N) {
							map[tx][ty + 1] += sevenPer;
						} else {
							outside += sevenPer;
						}
						if (ty - 2 >= 0) {
							map[tx][ty - 2] += twoPer;
						} else {
							outside += twoPer;
						}
						if (ty + 2 < N) {
							map[tx][ty + 2] += twoPer;
						} else {
							outside += twoPer;
						}
						if (tx - 1 >= 0 && ty - 1 >= 0) {
							map[tx - 1][ty - 1] += tenPer;
						} else {
							outside += tenPer;
						}
						if (tx - 1 >= 0 && ty + 1 < N) {
							map[tx - 1][ty + 1] += tenPer;
						} else {
							outside += tenPer;
						}
						if (tx + 1 < N && ty + 1 < N) {
							map[tx + 1][ty + 1] += onePer;
						} else {
							outside += onePer;
						}
						if (tx + 1 < N && ty - 1 >= 0) {
							map[tx + 1][ty - 1] += onePer;
						} else {
							outside += onePer;
						}
						if (tx - 2 >= 0) {
							map[tx - 2][ty] += fivePer;
						} else {
							outside += fivePer;
						}
						if (tx - 1 >= 0) {
							map[tx - 1][ty] += sand - (sevenPer * 2) - (twoPer * 2) - (onePer * 2) - (fivePer)
									- (tenPer * 2);
						} else {
							outside += sand - (sevenPer * 2) - (twoPer * 2) - (onePer * 2) - (fivePer) - (tenPer * 2);
						}
					}
					map[tx][ty] = 0;
					x = tx;
					y = ty;
					temp--;
				} // while
				dir++;
				if (dir == 4) {
					dir = 0;
				}
			}
			idx++;
			if (idx == N) {
				for (int i = 0; i < N - 1; i++) {
					tx = x + dx[0];
					ty = y + dy[0];
					int sand = map[tx][ty];
					int sevenPer = (int) (sand * 0.07);
					int twoPer = (int) (sand * 0.02);
					int onePer = (int) (sand * 0.01);
					int tenPer = (int) (sand * 0.1);
					int fivePer = (int) (sand * 0.05);
					if (tx - 1 >= 0) {
						map[tx - 1][ty] += sevenPer;
					} else {
						outside += sevenPer;
					}
					if (tx + 1 < N) {
						map[tx + 1][ty] += sevenPer;
					} else {
						outside += sevenPer;
					}
					if (tx - 2 >= 0) {
						map[tx - 2][ty] += twoPer;
					} else {
						outside += twoPer;
					}
					if (tx + 2 < N) {
						map[tx + 2][ty] += twoPer;
					} else {
						outside += twoPer;
					}
					if (tx - 1 >= 0 && ty - 1 >= 0) {
						map[tx - 1][ty - 1] += tenPer;
					} else {
						outside += tenPer;
					}
					if (tx + 1 < N && ty - 1 >= 0) {
						map[tx + 1][ty - 1] += tenPer;
					} else {
						outside += tenPer;
					}
					if (tx - 1 >= 0 && ty + 1 < N) {
						map[tx - 1][ty + 1] += onePer;
					} else {
						outside += onePer;
					}
					if (tx + 1 < N && ty + 1 < N) {
						map[tx + 1][ty + 1] += onePer;
					} else {
						outside += onePer;
					}
					if (ty - 2 >= 0) {
						map[tx][ty - 2] += fivePer;
					} else {
						outside += fivePer;
					}
					if (ty - 1 >= 0) {
						map[tx][ty - 1] += sand - (sevenPer * 2) - (twoPer * 2) - (onePer * 2) - (fivePer)
								- (tenPer * 2);
					} else {
						outside += sand - (sevenPer * 2) - (twoPer * 2) - (onePer * 2) - (fivePer) - (tenPer * 2);
					}
					map[tx][ty] = 0;
					x = tx;
					y = ty;
				}
				break;
			}
		} // while
		System.out.println(outside);
	}

}