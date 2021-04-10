
public class sbtm_1번 {
	public static void main(String[] args) {
		int[][] office = { { 1, 0, 0, 0 }, { 0, 0, 0, 1 }, { 0, 0, 1, 0 }, { 0, 1, 1, 0 } };
		int k = 2;
		int res = solution(office, k);
		System.out.println(res);

	}

	private static int solution(int[][] office, int k) {
		int N = office.length;
		int[][] arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int sum = 0;
				for (int ii = i; ii < i + k; ii++) {
					for (int jj = j; jj < j + k; jj++) {
						if (jj < 0 || jj > N - 1 || ii < 0 || ii > N - 1)
							continue;
						sum += office[ii][jj];

					}
				}
				arr[i][j] = sum;
			}
		}
		int max = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				max = Math.max(max, arr[i][j]);
			}
		}

		return max;
	}
}
