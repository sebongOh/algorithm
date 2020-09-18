import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class 백준_뱀 {
	static int N, K, L;
	static int[][] arr;
	static turn[] tt;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static ArrayList<sneak> list;
	static int time = 0;
	static int dir;
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		list = new ArrayList<>();
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		arr = new int[N + 1][N + 1];
		for (int i = 0; i < K; i++) {
			str = br.readLine().split(" ");
			arr[Integer.parseInt(str[0])][Integer.parseInt(str[1])] = 1;
		}
		L = Integer.parseInt(br.readLine());
		tt = new turn[L];
		for (int i = 0; i < L; i++) {
			str = br.readLine().split(" ");
			tt[i] = new turn(Integer.parseInt(str[0]), str[1].charAt(0));
		}
		System.out.println(Arrays.toString(tt));
		list.add(new sneak(1, 1)); // 시작위치
		dir = 1;
		arr[1][1] = 9;
		go();
		System.out.println(list);
		System.out.println("result ============= :: " + time);
	}

	private static void go() {
		sneak s;
		int tx = 0;
		int ty = 0;
		int idx = 0;
		int cnt = 0;
		int temp = 0;
		result = 0;
		char c;
		temp = tt[idx].t;
		c = tt[idx].c;

		while (true) {
			if (idx < K) {
				if (cnt == temp) {
					dir = change(c);
					idx++;
					temp = tt[idx].t;
					c = tt[idx].c;
				}
			}

			for (int i = 0; i < N + 1; i++) {
				System.out.println(Arrays.toString(arr[i]));
			}
			System.out.println();

			s = list.get(0);
			tx = s.x + dx[dir];
			ty = s.y + dy[dir];
			time++;
			cnt++;
			if (tx < 1 || tx > N || ty < 1 || ty > N || arr[tx][ty] == 9)
				return;
			list.add(0, new sneak(tx, ty));
			if (arr[tx][ty] == 1) {
				arr[tx][ty] = 9;
			} else if (arr[tx][ty] == 0) {
				arr[tx][ty] = 9;
				arr[list.get(list.size() - 1).x][list.get(list.size() - 1).y] = 0;
				list.remove(list.size() - 1);
			}
		}

	}

	private static int change(char c) {
		if (c == 'L') {
			if (dir == 0) {
				dir = 3;
			} else {
				dir--;
			}
		} else {
			if (dir == 3) {
				dir = 0;
			} else {
				dir++;
			}
		}
		return dir;
	}

	static class sneak {
		int x;
		int y;

		public sneak(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "sneak [x=" + x + ", y=" + y + "]";
		}

	}

	static class turn {
		int t;
		char c;

		public turn(int t, char c) {
			this.t = t;
			this.c = c;
		}

		@Override
		public String toString() {
			return "turn [t=" + t + ", c=" + c + "]";
		}

	}

}
