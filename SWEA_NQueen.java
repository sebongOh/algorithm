import java.util.Scanner;

public class SWEA_NQueen {
	static int[] cols;
	static int result;
	static int N;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int tc = sc.nextInt();
		for (int t = 0; t < tc; t++) {
			result = 0;
			N = sc.nextInt();
			cols = new int[N];
			dfs(0);
			System.out.println((t + 1) + " " + result);

		} // tc

	}

	private static void dfs(int depth) {
		if (depth == N) {
			result++;
			return;
		}
		for (int j = 0; j < N; j++) {
			cols[depth] = j;
			if (impossible(depth)) {
				dfs(depth + 1);
			}
		}

	}

	private static boolean impossible(int depth) {
		for (int i = 0; i < depth; i++) {
			if (cols[i] == cols[depth] || Math.abs(depth-i) == Math.abs(cols[depth] - cols[i])) {
				return false;
			}
		}

		return true;
	}
}
