import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class SWEA_운동 {
	static int N, M;
	static int[][] arr;
	static boolean[] visited;
	static int res;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		String[] str;

		for (int tc = 1; tc <= T; tc++) {
			res = Integer.MAX_VALUE;
			N = sc.nextInt();
			M = sc.nextInt();
			arr = new int[N + 1][N + 1];
			visited = new boolean[N + 1];
			for (int i = 0; i < M; i++) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				int c = sc.nextInt();
				arr[a][b] = c;
			}

			for (int i = 1; i <= N; i++) {
				init();
				dfs(i, i, 0);
			}
			
			System.out.println("#"+tc+" "+res);

		}

	}

	private static void dfs(int start, int now, int dist) {
		if (start == now && visited[start]) {
			res = Math.min(res, dist);
			return;
		}

		if (visited[now])
			return;

		if (dist >= res)
			return;

		visited[now]=true;
		for (int i = 1; i <= N; i++) {
			if(arr[now][i]>0) {
				dfs(start,i,dist+arr[now][i]);
			}
		}

	}

	private static void init() {
		Arrays.fill(visited, false);
	}
}
