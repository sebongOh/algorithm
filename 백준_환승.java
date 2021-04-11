import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_환승 {
	static int N, M, K, result;
	static ArrayList<Integer>[] list;
	static int[] dist;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]); // 역의 수
		K = Integer.parseInt(str[1]); // 하이퍼튜브가 연결하는 역의 수
		M = Integer.parseInt(str[2]); // 하이퍼튜브 갯수
		list = new ArrayList[N + M + 1];
		dist = new int[N + M + 1];
		result = -1;
		for (int i = 0; i < list.length; i++) {
			list[i] = new ArrayList<Integer>();
		}

		for (int i = 1; i <= M; i++) { // 하이퍼튜브 갯수
			str = br.readLine().split(" ");
			for (int j = 0; j < str.length; j++) {
				int temp = Integer.parseInt(str[j]);
				list[temp].add(N + i);
				list[N + i].add(temp);
			}
		}

//		for (ArrayList<Integer> a : list) {
//			System.out.println(a);
//		}

		solve();

		System.out.println(result == -1 ? -1 : result);

	}

	private static void solve() {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(1);
		dist[1] = 1;
		while (!q.isEmpty()) {
			int n = q.poll();
			if (n == N) {
				result = dist[n];
				return;
			}
			for (int temp : list[n]) {
				if (dist[temp] == 0) {
					if (temp > N) {
						dist[temp] = dist[n];
					} else {
						dist[temp] = dist[n] + 1;
					}
					q.add(temp);
				}

			}
		}

	}
}
