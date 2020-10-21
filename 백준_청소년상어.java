import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 백준_청소년상어 {
	static int[] dx = { 0, -1, -1, 0, 1, 1, 1, 0, -1 }; // 1부터 반시계방향으로
	static int[] dy = { 0, 0, -1, -1, -1, 0, 1, 1, 1 };
	static Fish[] fish;
	static int[][] arr;
	static int ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		fish = new Fish[17]; // 물고기
		arr = new int[4][4]; // 숫자 -1:상어 0:빈칸 1~16:물고기
		ans = Integer.MIN_VALUE;

		for (int i = 0; i < 4; i++) {
			str = br.readLine().split(" ");
			for (int j = 0; j < 4; j++) {
				fish[Integer.parseInt(str[j * 2])] = new Fish(i, j, Integer.parseInt(str[j * 2 + 1]), true);
				arr[i][j] = Integer.parseInt(str[j * 2]);
			}
		}

//		for (Fish f : fish) {
//			System.out.println(f);
//		}
//		for (int[] a : arr) {
//			System.out.println(Arrays.toString(a));
//		}
		int start = arr[0][0];
		int dir = fish[start].dir;
		fish[start].live = false;
		arr[0][0] = -1;
		dfs(0, 0, dir, start);
		System.out.println(ans);
	}

	private static void dfs(int x, int y, int dir, int sum) {
		if (ans < sum) {
			ans = sum;
		}
		int[][] copy = new int[4][4];
		Fish[] fishCopy = new Fish[17];
		for (int i = 1; i <= 16; i++) {
			fishCopy[i] = new Fish(0, 0, 0, true);
		}
		copymap(arr, copy, fish, fishCopy);
		// 물고기 이동
		fishMove();
//		for (int[] a : arr) {
//			System.out.println(Arrays.toString(a));
//		}
//		for (Fish f : fish) {
//			System.out.println(f);
//		}
		// 상어 갈수 있는곳 찾기
		for (int i = 1; i < 4; i++) {
			int tx = x + dx[dir] * i;
			int ty = y + dy[dir] * i;
			if (tx >= 0 && tx < 4 && ty >= 0 && ty < 4) { // 범위안이고
				if (arr[tx][ty] > 0) { // 물고기 있으면
					int temp = arr[tx][ty];
					int tdir = fish[temp].dir;
					moveShark(x, y, tx, ty, temp, true);
					dfs(tx, ty, tdir, sum + temp);
					moveShark(x, y, tx, ty, temp, false);
				}
			} else {
				break;
			}
		}
		// 다시 되돌리기
		copymap(copy, arr, fishCopy, fish);
	}

	private static void moveShark(int x, int y, int tx, int ty, int num, boolean flag) {
		if (flag) {
			arr[tx][ty] = -1;
			arr[x][y] = 0;
			fish[num].live = false;
		} else {
			arr[x][y] = -1;
			arr[tx][ty] = num;
			fish[num].live = true;
		}
	}

	private static void fishMove() {
		int tx = 0;
		int ty = 0;

		for (int i = 1; i <= 16; i++) { // 1번 물고기 부터 이동
			boolean flag = false;
			if (!fish[i].live) // 죽었으면 패스
				continue;
			int x = fish[i].x;
			int y = fish[i].y;
			int dir = fish[i].dir;
			tx = x + dx[dir];
			ty = y + dy[dir];
			if (tx >= 0 && tx < 4 && ty >= 0 && ty < 4) { // 범위 안
				if (arr[tx][ty] == 0) {
					arr[tx][ty] = i;
					fish[i].x = tx;
					fish[i].y = ty;
					arr[x][y] = 0;
					flag = true;
				} else if (arr[tx][ty] != -1) {
					swap(x, y, tx, ty);
					flag = true;
				}
			} // if 범위

			if (!flag) {
				int tdir = dir;
				tdir++;
				if (tdir == 9) {
					tdir = 1;
				}
				while (dir != tdir) {
					tx = x + dx[tdir];
					ty = y + dy[tdir];
					if (tx >= 0 && tx < 4 && ty >= 0 && ty < 4) {
						if (arr[tx][ty] == 0) {
							arr[tx][ty] = i;
							fish[i].x = tx;
							fish[i].y = ty;
							arr[x][y] = 0;
							fish[i].dir = tdir;
							break;
						} else if (arr[tx][ty] != -1) {
							swap(x, y, tx, ty);
							fish[i].dir = tdir;
							break;
						}
					} // if
					tdir++;
					if (tdir == 9) {
						tdir = 1;
					}
				} // while
			}
		} // for 16

	}

	private static void swap(int x, int y, int tx, int ty) {
		int a = arr[x][y];
		int b = arr[tx][ty];
		arr[tx][ty] = a;
		arr[x][y] = b;
		fish[a].x = tx;
		fish[a].y = ty;
		fish[b].x = x;
		fish[b].y = y;
	}

	private static void copymap(int[][] arr, int[][] copy, Fish[] fish, Fish[] fishCopy) {

		for (int i = 0; i < 4; i++) {
			copy[i] = Arrays.copyOf(arr[i], arr[i].length);
		}
		for (int i = 1; i <= 16; i++) {
			fishCopy[i].x = fish[i].x;
			fishCopy[i].y = fish[i].y;
			fishCopy[i].dir = fish[i].dir;
			fishCopy[i].live = fish[i].live;
		}
	}

	static class Fish {
		int x;
		int y;
		int dir;
		boolean live;

		public Fish(int x, int y, int dir, boolean live) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.live = live;
		}

		@Override
		public String toString() {
			return "Fish [x=" + x + ", y=" + y + ", dir=" + dir + ", live=" + live + "]";
		}

	}
}
