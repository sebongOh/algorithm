import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 백준_색종이붙이기 {
	static int[][] arr;
	static int[] paper = { 0, 5, 5, 5, 5, 5 };
	static int result = Integer.MAX_VALUE;
	static int papercnt = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		arr = new int[10][10];
		String[] str;

		for (int i = 0; i < 10; i++) {
			str = br.readLine().split(" ");
			for (int j = 0; j < 10; j++) {
				arr[i][j] = Integer.parseInt(str[j]);
				if (arr[i][j] == 1)
					papercnt++;
			}
		}

		go(0, 0, 0);
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}

	private static void go(int row, int cnt, int total) {
		if (result <= cnt)
			return;
		if (total == papercnt) {
			result = Math.min(result, cnt);
			return;
		}
		for (int i = row; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (arr[i][j] == 1) {
					boolean flag = false;
					for (int k = 5; k >= 1; k--) {
						if ((i + k) <= 10 && (j + k) <= 10 && paper[k] > 0) {
							if (!flag) {
								flag = check(i, j, k);
							}
							if (flag) {
								visit(i, j, k);
								paper[k]--;
								go(i, cnt + 1, total + (k * k));
								paper[k]++;
								visit(i, j, k);
							}
						}
					}
					if (!flag) {
						return;
					}
				}
				if (arr[i][j] == 1) {
					return;
				}
			}
		}

	}

	private static void visit(int x, int y, int size) {
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				arr[i][j] = arr[i][j] ^ 1;
			}
		}
	}

	private static boolean check(int x, int y, int size) {
		for (int i = x; i < x + size; i++) {
			for (int j = y; j < y + size; j++) {
				if (arr[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}
}
