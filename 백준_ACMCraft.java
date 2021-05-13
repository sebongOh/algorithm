import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_ACMCraft {
	static int N, K;
	static int[] count, time;
	static ArrayList<Integer>[] list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		int tc = Integer.parseInt(br.readLine());
		for (int t = 0; t < tc; t++) {
			str = br.readLine().split(" ");
			N = Integer.parseInt(str[0]); // 건물의 개수 1~N
			K = Integer.parseInt(str[1]); // 건설 순서 규칙 개수
			list = new ArrayList[N + 1];
			count = new int[N + 1];
			time = new int[N + 1];
			for (int i = 0; i < list.length; i++) {
				list[i] = new ArrayList<Integer>();
			}

			str = br.readLine().split(" ");
			for (int i = 0; i < str.length; i++) {
				time[i + 1] = Integer.parseInt(str[i]);
			}
			for (int i = 0; i < K; i++) {
				str = br.readLine().split(" ");
				int a = Integer.parseInt(str[0]);
				int b = Integer.parseInt(str[1]);
				list[a].add(b);
				count[b]++;
			}

			int num = Integer.parseInt(br.readLine());
			solve(num);
		} // tc

	}// main

	private static void solve(int num) {
		System.out.println(Arrays.toString(count));
		Queue<Integer> q = new LinkedList<Integer>();
		int[] result = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {

			result[i] = time[i];

			if (count[i] == 0) {
				q.add(i);
			}
		}

		while (!q.isEmpty()) {
			int n = q.poll();
			for (int temp : list[n]) {
				result[temp] = Math.max(result[temp], result[n] + time[temp]);
				count[temp]--;
				if (count[temp] == 0) {
					q.add(temp);
				}
			}
		}

		System.out.println(result[num]);

	}
}// class
