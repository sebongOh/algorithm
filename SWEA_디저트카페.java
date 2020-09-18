import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SWEA_디저트카페 {
	static int N;
	static int[][] arr;
	static boolean[][] visited;
	static boolean[] count;
	static boolean flag;
	static int cnt;
	static int res;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		String[] str;

		for (int tc = 1; tc <= t; tc++) {
			cnt = 0;
			res = Integer.MIN_VALUE;
			flag = false;
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			visited = new boolean[N][N];
			count = new boolean[101];
			for (int i = 0; i < N; i++) {
				str = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(str[j]);
				}
			}

			for (int i = 0; i < N - 2; i++) {
				for (int j = 1; j < N - 1; j++) {
					for (int k = 1; k < N; k++) {
						int x1 = i + k;
						int y1 = j - k;
						if (x1 > N - 1 || y1 < 0) {
							continue;
						}
						for (int l = 1; l < N; l++) {
							int x2 = i + l;
							int y2 = j + l;
							if (x2 > N - 1 || y2 > N - 1) {
								continue;
							}
							if (x1 + l > N - 1 || y1 + l > N - 1) {
								continue;
							}
							go(i, j, x1, y1, x2, y2, x1 + l, y1 + l);
							if (!flag) {
								res = Math.max(res, cnt);
								cnt = 0;
							}
							flag = false;
							Arrays.fill(count, false);
						}
					}
				}
			}
			if (res == Integer.MIN_VALUE) {
				System.out.println("#" + tc + " " + "-1");
			} else {
				System.out.println("#" + tc + " " + res);
			}
		}
	}

	private static void go(int x, int y, int x1, int y1, int x2, int y2, int x3, int y3) {
		int start = x;
		int end = y;

		while (start < x1 && end > y1) {
			if (count[arr[start][end]]) {
				flag = true;
				return;
			} else {
				count[arr[start][end]] = true;
			}
			start++;
			end--;
		}

		while (start < x3 && end < y3) {
			if (count[arr[start][end]]) {
				flag = true;
				return;
			} else {
				count[arr[start][end]] = true;
			}
			start++;
			end++;
		}

		while (start > x2 && end < y2) {
			if (count[arr[start][end]]) {
				flag = true;
				return;
			} else {
				count[arr[start][end]] = true;
			}
			start--;
			end++;
		}
		while (start > x && end > y) {
			if (count[arr[start][end]]) {
				flag = true;
				return;
			} else {
				count[arr[start][end]] = true;
			}
			start--;
			end--;
		}

		for (int i = 0; i < count.length; i++) {
			if (count[i]) {
				cnt++;
			}
		}

	}
}
