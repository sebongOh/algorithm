import java.util.Arrays;

public class 프로그래머스_가사검색 {
	public static void main(String[] args) {
		int[][] key = { { 0, 0, 0 }, { 1, 0, 0 }, { 0, 1, 1 } };
		int[][] lock = { { 1, 1, 1 }, { 1, 1, 0 }, { 1, 0, 1 } };
		boolean flag = true;
		int top = Integer.MAX_VALUE;
		int bottom = Integer.MAX_VALUE;
		int left = Integer.MAX_VALUE;
		int right = Integer.MAX_VALUE;

		for (int i = 0; i < lock.length; i++) {
			for (int j = 0; j < lock[0].length; j++) {
				if (lock[i][j] == 0 && top == Integer.MAX_VALUE) {
					top = i;
				} else if (lock[i][j] == 0 && top != Integer.MAX_VALUE) {
					bottom = i;
				}
			}
		}

		for (int i = 0; i < lock.length; i++) {
			for (int j = 0; j < lock[0].length; j++) {
				if (lock[j][i] == 0 && left == Integer.MAX_VALUE) {
					left = i;
				} else if (lock[j][i] == 0 && left != Integer.MAX_VALUE) {
					right = i;
				}
			}
		}
		int copy[][] = new int[bottom - top + 1][right - left + 1];
		int w = 0;
		int h = 0;
		for (int i = top; i <= bottom; i++) {
			for (int j = left; j <= right; j++) {
				copy[w][h] = lock[i][j];
				h++;
			}
			w++;
			h = 0;
		}

		for (int rote = 0; rote < 4; rote++) {
			if (rote != 0) {
				key = rotate(key);
			}

			for (int i = 0; i < key.length; i++) {
				for (int j = 0; j < key[0].length; j++) {
					if (i + copy.length > key.length || j + copy[0].length > key[0].length) {
						continue;
					}
					boolean f = check(i, j, copy.length, copy[0].length, copy, key);
					if (f) {
						System.out.println("1");
						return;
					}
				}
			}
		}
		System.out.println("-1");
	}

	private static boolean check(int i, int j, int n, int m, int[][] copy, int[][] key) {
		for (int k = i; k < i + n; k++) {
			for (int l = j; l < j + m; l++) {
				if (key[k][l] != copy[k - i][l - j]) {
					return false;
				}
			}
		}
		return true;
	}

	private static int[][] rotate(int[][] key) {
		int[][] arr = new int[key.length][key[0].length];
		int n = key.length;
		int m = key[0].length;
		int w = 0;
		int h = 0;
		for (int i = 0; i < m; i++) {
			for (int j = n - 1; j >= 0; j--) {
				arr[w][h] = key[j][i];
				h++;
			}
			w++;
			h = 0;
		}
		return arr;
	}

}
