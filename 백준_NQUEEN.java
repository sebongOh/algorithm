import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 백준_NQUEEN {
	static int N;
	static int count;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		for (int i = 1; i <= N; i++) {
			int[] col = new int[N + 1];

			col[1] = i;

			dfs(col, 1);
		}
		System.out.println(count);

	}

	private static void dfs(int[] col, int row) {
		if (row == N) {
			count++;
		} else {
			for (int i = 1; i <= N; i++) {
				col[row + 1] = i;
				if (isPossible(col, row + 1)) {
					dfs(col, row + 1);
				}
			}
		}

	}

	private static boolean isPossible(int[] col, int row) {
		for (int i = 1; i < row; i++) {
			if (col[i] == col[row])
				return false;

			if (Math.abs(row - i) == Math.abs(col[row] - col[i]))
				return false;
		}
		return true;
	}

}
