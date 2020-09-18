import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class 백준_친구네트워크 {
	static Map<String, Integer> map;
	static int[] parent;
	static int[] count;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		int N;
		String[] str;

		for (int t = 0; t < tc; t++) {

			map = new HashMap<>();
			N = Integer.parseInt(br.readLine());

			count = new int[N*2+1];
			parent = new int[N*2+1];
			Arrays.fill(count, 1);
			makeSet();
			int idx = 1;
			int a = 0;
			int b = 0;
			for (int i = 0; i < N; i++) {
				str = br.readLine().split(" ");
				if (!map.containsKey(str[0])) {
					map.put(str[0], idx);
					a = idx;
					idx++;
				} else {
					a = map.get(str[0]);
				}

				if (!map.containsKey(str[1])) {
					map.put(str[1], idx);
					b = idx;
					idx++;
				} else {
					b = map.get(str[1]);
				}

				a = findSet(a);
				b = findSet(b);
				
				if(a==b) {
					System.out.println(count[a]);
				}else {
					System.out.println(count[a]+count[b]);
				}
				
				unionSet(a, b);
				
			} // 입력

		}

	}

	private static void unionSet(int a, int b) {
		int x = findSet(a);
		int y = findSet(b);
		if (x == y) {
			return;
		} else {
			parent[y] = parent[x];
			count[x] += count[y];
			count[y] = count[x];
		}
	}

	private static int findSet(int x) {
		if (parent[x] == x) {
			return x;
		}
		return parent[x] = findSet(parent[x]);

	}

	private static void makeSet() {
		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}
	}

}
