
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_로봇청소기 {
	static int N, M;
	static int[][] arr;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int cnt = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		arr = new int[N][M];
		str = br.readLine().split(" ");
		Robot r = new Robot(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]));
		
		for (int i = 0; i < N; i++) {
			str = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(str[j]);
			}
		}

		clean(r);
		check();

		System.out.println(cnt);

	}

	private static void check() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[i][j] == 9) {
					cnt++;
				}
			}
		}
	}

	private static void clean(Robot r) {
		int tx = 0;
		int ty = 0;
		boolean flag = false;
		Queue<Robot> q = new LinkedList<>();
		q.add(r);
		arr[r.x][r.y] = 9;
		while (!q.isEmpty()) {
			flag = false;
			Robot ro = q.poll();
			int d = ro.dir;
			int nextd = 0;
			for (int i = 0; i < 4; i++) {
				d = (--d<0)? 3:d--;
				tx = ro.x + dx[d];
				ty = ro.y + dy[d];
				if (tx < 0 || tx > N - 1 || ty < 0 || ty > M - 1)
					continue;
				if (arr[tx][ty] == 0) {
					q.add(new Robot(tx, ty, d));
					arr[tx][ty] = 9;
					flag = true;
					break;
				}
			} // for
			if (!flag) {
				nextd = (d + 2) % 4;
				tx = ro.x + dx[nextd];
				ty = ro.y + dy[nextd];
				if (arr[tx][ty] != 1) {
					q.add(new Robot(tx, ty, d));
					arr[tx][ty] = 9;
				} 
			}
		} // while

	}

	static class Robot {
		int x;
		int y;
		int dir;

		public Robot(int x, int y, int dir) {
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}

}