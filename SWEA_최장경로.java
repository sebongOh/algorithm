import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class SWEA_최장경로 {
	static int N, M;
	static int[][] arr;
	static boolean[] visited;
	static int cnt;
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			result = Integer.MIN_VALUE;
			str = br.readLine().split(" ");
			N = Integer.parseInt(str[0]);
			M = Integer.parseInt(str[1]);
			arr = new int[N + 1][N + 1];
			visited = new boolean[N + 1];
			
			if(M==0) { //연결된 간선 없으면 하나가 최대
				System.out.println("#"+tc+" "+"1");
				continue;
			}
			
			for (int i = 0; i < M; i++) {
				str = br.readLine().split(" ");
				int a = Integer.parseInt(str[0]);
				int b = Integer.parseInt(str[1]);
				arr[a][b] = 1;
				arr[b][a] = 1;
			}
			
			for (int i = 1; i <= N; i++) {
				init();
				dfs(i, 1); //1번부터 경로시작
			}
			System.out.println("#"+tc+" "+result);
		}
	}

	private static void dfs(int idx, int cnt) {
		visited[idx] = true;
		for (int i = 1; i <= N; i++) {
			if (visited[i])
				continue;
			if (idx == i)
				continue;
			if (arr[idx][i] == 1) {
				dfs(i,cnt+1);
				visited[i]=false;
			}
		}
		if(cnt>result) result = cnt;
	}

	private static void init() {
		Arrays.fill(visited, false);
	}
}
