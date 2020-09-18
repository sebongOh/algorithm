package algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 백준_스타트와링크 {
	static int[][] arr;
	static boolean[] visited;
	static ArrayList<Integer> teamA;
	static ArrayList<Integer> teamB;
	static int N, min;

	public static void main(String[] args) throws Exception {
		min = Integer.MAX_VALUE;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		teamA = new ArrayList<>();
		teamB = new ArrayList<>();
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		visited = new boolean[N];
		String[] str;

		for (int i = 0; i < N; i++) {
			str = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(str[j]);
			}
		}

		solve(0, 0);
		
		System.out.println(min);
		
	}

	private static void solve(int depth, int cur) {
		int suma = 0;
		int sumb = 0;
		if (depth == N / 2) {
			for (int i = 0; i < N; i++) {
				if (visited[i]) {
					teamA.add(i);
				} else {
					teamB.add(i);
				}
			}
			for (int i = 0; i < teamA.size() - 1; i++) {
				for (int j = i + 1; j < teamA.size(); j++) {
					suma += arr[teamA.get(i)][teamA.get(j)];
					suma += arr[teamA.get(j)][teamA.get(i)];
					sumb += arr[teamB.get(i)][teamB.get(j)];
					sumb += arr[teamB.get(j)][teamB.get(i)];
				}
			}
			min = Math.min(min, Math.abs((suma - sumb)));
			teamA.clear();
			teamB.clear();
			return;
		}
		for (int i = cur; i < N; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			solve(depth + 1, i);
			visited[i] = false;
		}
	}
}
