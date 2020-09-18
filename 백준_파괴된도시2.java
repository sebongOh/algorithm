import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class 백준_파괴된도시2 {
	static int N, M;
	static ArrayList<Integer>[] town;
	static int[] fire;
	static int[] arr;
	static boolean[] visited;
	static ArrayList<Integer> list;
	static boolean[] check;
	static boolean flag;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		arr = new int[N + 1];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
		town = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			town[i] = new ArrayList<Integer>();
		}
		list = new ArrayList<Integer>();
		visited = new boolean[N + 1];
		check = new boolean[N + 1];

		for (int i = 0; i < M; i++) {
			str = br.readLine().split(" ");
			int a = Integer.parseInt(str[0]);
			int b = Integer.parseInt(str[1]);
			town[a].add(b);
			town[b].add(a);
		}

		fire = new int[Integer.parseInt(br.readLine())];
		str = br.readLine().split(" ");

		for (int i = 0; i < str.length; i++) {
			fire[i] = Integer.parseInt(str[i]);
		}

		bit();
		System.out.println("-1");

	}

	private static void bit() {
		for (int i = 0; i < 1 << N; i++) {
			for (int j = 0; j < N; j++) {
				if ((i & 1 << j) != 0)
					System.out.println(arr[j]);
			}
			
			solve();
			if (flag) {
				System.out.println(list.size());
				for (int j = 0; j < list.size(); j++) {
					System.out.print(list.get(j) + " ");
				}
				System.exit(0);
			}

			init();
			list.clear();
			return;

		}

	}

	private static void solve() {
		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < town[list.get(i)].size(); j++) {
				check[list.get(i)] = true;
				check[town[list.get(i)].get(j)] = true;
			}
		}
		int cnt = 0;
		for (int i = 1; i < check.length; i++) {
			if (check[i])
				cnt++;
		}

		if (cnt != fire.length) {
			return;
		}

		for (int i = 0; i < fire.length; i++) {
			if (!check[fire[i]]) {
				return;
			}
		}
		flag = true;

	}

	private static void init() {
		Arrays.fill(check, false);
	}

}
