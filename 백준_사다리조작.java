import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 백준_사다리조작 {
	static int N, M, H;
	static int[][] arr;
	static final int LEFT = 1;
	static final int RIGHT = 2;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		H = Integer.parseInt(str[2]);
		arr = new int[H][N];

		for (int i = 0; i < M; i++) {
			str = br.readLine().split(" ");
			arr[Integer.parseInt(str[0]) - 1][Integer.parseInt(str[1]) - 1] = RIGHT;
			arr[Integer.parseInt(str[0]) - 1][Integer.parseInt(str[1])] = LEFT;
		}
//		for(int [] a:arr) {
//			System.out.println(Arrays.toString(a));
//		}

		int res = solve(0, 0);
		if (res == Integer.MAX_VALUE) {
			System.out.println(-1);
		} else {
			System.out.println(res);
		}
	}

	private static int solve(int cur, int cnt) {
		int min = Integer.MAX_VALUE;
		int r = cur / N;
		int c = cur % N;
		if (cur >= N * H || cnt == 3) {
			if (check()) {
				return cnt;
			}
			return Integer.MAX_VALUE;
		}
		if (c != N - 1 && arr[r][c] == 0 && arr[r][c + 1] == 0) {
			arr[r][c] = RIGHT;
			arr[r][c + 1] = LEFT;
			min = Math.min(min, solve(cur + 1, cnt + 1));
			arr[r][c] = 0;
			arr[r][c + 1] = 0;
		}
		min = Math.min(min, solve(cur + 1, cnt));
		return min;
	}

	private static boolean check() {
		for (int i = 0; i < N; i++) {
			int col = i;
			int row = 0;
			while (row != H) {
				if (arr[row][col] == LEFT) {
					col--;
				} else if (arr[row][col] == RIGHT) {
					col++;
				}
				row++;
			}
			if (col != i) {
				return false;
			}
		}
		return true;
	}
}
