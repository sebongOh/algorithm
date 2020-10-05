import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SWEA_활주로건설 {
	static int[][] arr;
	static int res, N, len;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		String[] str;
		for (int tc = 1; tc <= t; tc++) {
			res = 0;
			str = br.readLine().split(" ");
			N = Integer.parseInt(str[0]);
			len = Integer.parseInt(str[1]);
			arr = new int[N][N];
			visited = new boolean[N];

			for (int i = 0; i < N; i++) {
				str = br.readLine().split(" ");
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(str[j]);
				}
			}

			for (int i = 0; i < N; i++) {
				if (sero(i)) {
					res++;
				}
				if (garo(i)) {
					res++;
				}
			}

			System.out.println("#" + tc + " " + res);
		}
	}

	private static boolean garo(int i) {
		Arrays.fill(visited, false);
		int idx = 0;
		while (true) {
			if (idx == N - 1) {
				break;
			}
			if (arr[i][idx] + 1 == arr[i][idx + 1]) { // 증가
				if (idx - (len - 1) < 0) {
					return false;
				}
				for (int j = idx; j >= idx - (len - 1); j--) {
					if (visited[j] || arr[i][idx] != arr[i][j]) {
						return false;
					}
				}
				idx++;
			} else if (arr[i][idx] - 1 == arr[i][idx + 1]) {// 감소
				if (idx + len > N - 1) {
					return false;
				}
				for (int j = idx + 1; j <= idx + len; j++) {
					visited[j] = true;
					if (arr[i][idx] - 1 != arr[i][j]) {
						return false;
					}
				}
				idx += len;
			} else if (arr[i][idx] == arr[i][idx + 1]) {
				idx++;
			} else {
				return false;
			}
		}
		return true;
	}

	private static boolean sero(int i) {
		Arrays.fill(visited, false);
		int idx = 0;
		while (true) {
			if (idx == N - 1) {
				break;
			}
			if (arr[idx][i] + 1 == arr[idx + 1][i]) { // 증가
				if (idx - (len - 1) < 0) {
					return false;
				}
				for (int j = idx; j >= idx - (len - 1); j--) {
					if (visited[j] || arr[idx][i] != arr[j][i]) {
						return false;
					}
				}
				idx++;
			} else if (arr[idx][i] - 1 == arr[idx + 1][i]) {// 감소
				if (idx + len > N - 1) {
					return false;
				}
				for (int j = idx + 1; j <= idx + len; j++) {
					visited[j] = true;
					if (arr[idx][i] - 1 != arr[j][i]) {
						return false;
					}
				}
				idx += len;
			} else if (arr[idx][i] == arr[idx + 1][i]) {
				idx++;
			} else {
				return false;
			}
		}
		return true;
	}
}
