import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class 백준_N과M_10 {
	static int N, M;
	static int[] arr;
	static boolean[] visited;
	static ArrayList<Integer> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		arr = new int[N];
		visited = new boolean[N];
		list = new ArrayList<Integer>();
		str = br.readLine().split(" ");
		for (int i = 0; i < str.length; i++) {
			arr[i] = Integer.parseInt(str[i]);
		}
		Arrays.sort(arr);
		dfs(0,0);

	}

	private static void dfs(int depth,int cur) {
		if (depth == M) {
			for (int i = 0; i < list.size(); i++) {
				System.out.print(list.get(i) + " ");
			}
			System.out.println();
		}
		int temp = 0;
		for (int i = cur; i < N; i++) {
			if (visited[i] || arr[i] == temp)
				continue;
			temp = arr[i];
			visited[i] = true;
			list.add(arr[i]);
			dfs(depth + 1,i+1);
			visited[i] = false;
			list.remove(list.size() - 1);
		}

	}
}
