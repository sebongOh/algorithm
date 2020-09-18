import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_말이되고픈원숭이재채점 {

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int[] ddx = { -1, -2, -2, -1, 1, 2, 2, 1 };
	static int[] ddy = { -2, -1, 1, 2, 2, 1, -1, -2 };
	static int W, H, K;
	static int[][] arr;
	static int[][][] dp;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		K = Integer.parseInt(br.readLine()); // 말처럼 뛸수 있는 수
		str = br.readLine().split(" ");
		W = Integer.parseInt(str[0]);
		H = Integer.parseInt(str[1]);
		arr = new int[H][W];
		dp = new int[K + 1][H][W];
		for (int i = 0; i < H; i++) {
			str = br.readLine().split(" ");
			for (int j = 0; j < W; j++) {
				arr[i][j] = Integer.parseInt(str[j]);
			}
		}
		for (int i = 0; i < K + 1; i++) {
			for (int j = 0; j < H; j++) {
				Arrays.fill(dp[i][j], Integer.MAX_VALUE);
			}
		}
		if(W==1 && H ==1) {
			System.out.println("0");
			return;
		}
		bfs();
		

		if (min == Integer.MAX_VALUE) {
			System.out.println("-1");
		} else {
			System.out.println(min);
		}
	}

	private static void bfs() {
		Queue<Monkey> q = new LinkedList<>();
		q.add(new Monkey(0, 0, 0, 0));

		while (!q.isEmpty()) {
			Monkey m = q.poll();

			moveMonkey(m, q);
			if (m.k < K) {
				moveHorse(m, q);
			}
		}

		for (int i = 0; i < K + 1; i++) {
			if (min > dp[i][H - 1][W - 1]) {
				min = dp[i][H - 1][W - 1];
			}
		}
	}

	private static void moveMonkey(Monkey m, Queue<Monkey> q) {
		int tx = 0;
		int ty = 0;
		for (int i = 0; i < 4; i++) {
			tx = m.x + dx[i];
			ty = m.y + dy[i];
			if (tx < 0 || ty < 0 || tx > H - 1 || ty > W - 1)
				continue;
			if (arr[tx][ty] == 0) { // 갈수있다면
				int c = m.c;
				c += 1;
				if (dp[m.k][tx][ty] <= c) { // 기존에 갔던거보다 같거나 크면 패스
					continue;
				}
				dp[m.k][tx][ty] = c;
				q.add(new Monkey(tx, ty, c, m.k));
			}
		}
	}


	private static void moveHorse(Monkey m, Queue<Monkey> q) {
		int tx = 0;
		int ty = 0;
		int k = m.k;
		k += 1;
		for (int i = 0; i < 8; i++) {
			tx = m.x + ddx[i];
			ty = m.y + ddy[i];
			if (tx < 0 || ty < 0 || tx > H - 1 || ty > W - 1)
				continue;
			if (arr[tx][ty] == 0) { // 갈수있다면
				int c = m.c;
				c += 1;
				if (dp[k][tx][ty] <= c) { // 기존에 갔던거보다 같거나 크면
					continue;
				}
				dp[k][tx][ty] = c;
				q.add(new Monkey(tx, ty, c, k));
			}
		}
	}

	static class Monkey {
		int x;
		int y;
		int c;
		int k;

		public Monkey(int x, int y, int c, int k) {
			this.x = x;
			this.y = y;
			this.c = c;
			this.k = k;
		}

		@Override
		public String toString() {
			return "Monkey [x=" + x + ", y=" + y + ", c=" + c + ", k=" + k + "]";
		}
		
	}
}
