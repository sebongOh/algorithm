import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_줄세우기 {
	static int N, M;
	static ArrayList<Integer>[] list;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		String[] str;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		list = new ArrayList[N + 1];
		arr = new int[N + 1];
		for (int i = 0; i < N + 1; i++) {
			list[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < M; i++) {
			str = br.readLine().split(" ");
			int x = Integer.parseInt(str[0]);
			int y = Integer.parseInt(str[1]);
			list[x].add(y);
			arr[y]++;
		}

		Queue<Integer> q = new LinkedList<Integer>();
		Queue<Integer> res = new LinkedList<Integer>();
		for (int i = 1; i < N + 1; i++) {
			if (arr[i] == 0) {
				q.add(i);
			}
		}

		while (!q.isEmpty()) {
			int cur = q.poll();
			res.add(cur);
			for (int i = 0; i < list[cur].size(); i++) {
				int num = list[cur].get(i);
				
				arr[num]--;
				if (arr[num] == 0) {
					q.add(num);
				}
			}
		}

		while (!res.isEmpty()) {
			System.out.print(res.poll() + " ");
		}

	}
}
