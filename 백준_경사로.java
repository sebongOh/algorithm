import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 백준_경사로 {
	static int N, L;
	static int[][] arr;
	static boolean[] visited;
	static int cnt = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		L = Integer.parseInt(str[1]);
		arr = new int[N][N];
		visited = new boolean[N];

		for (int i = 0; i < N; i++) {
			str = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(str[j]);
			}
		}
		int[] temp = new int[N];
		for (int i = 0; i < N; i++) {
			if (go(arr[i]))
				cnt++;
			for (int j = 0; j < N; j++) {
				temp[j] = arr[j][i];
			}
			if (go(temp))
				cnt++;
		}

		System.out.println(cnt);

	}

	private static boolean go(int[] arr) {
		Arrays.fill(visited, false);
		int idx = 0;

		while (true) {
			if (idx == N - 1)
				break;
			// 증가
			if (arr[idx + 1] == arr[idx] + 1) {
				if (idx - (L - 1) < 0)
					return false;
				for (int i = idx - (L - 1); i <= idx; i++) {
					if (visited[i] || arr[i] != arr[idx])
						return false;
				}
				idx++;
			}

			// 감소
			else if (arr[idx + 1] == arr[idx] - 1) {
				if (idx + L >= N)
					return false;
				for (int i = idx + 1; i <= idx + L; i++) {
					visited[i] = true;
					if (arr[i] != arr[idx] - 1)
						return false;
				}
				idx += L;
			}
			// 같을때
			else if (arr[idx] == arr[idx+1]) {
				idx++;
			} else {
				return false;
			}
		}

		return true;
	}

}
