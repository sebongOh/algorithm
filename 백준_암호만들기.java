import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class 백준_암호만들기 {
	static int N, M;
	static char[] arr;
	static ArrayList<Character> list;
	static boolean[] check;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		arr = new char[M];
		check = new boolean[M];
		list = new ArrayList<Character>();
		str = br.readLine().split(" ");

		for (int i = 0; i < M; i++) {
			arr[i] = str[i].charAt(0);
		}
		Arrays.sort(arr);

		dfs(0,0);

	}

	private static void dfs(int depth,int cur) {
		int mocnt = 0;
		int jacnt = 0;
		if (depth == N) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) == 'a' || list.get(i) == 'e' || list.get(i) == 'i' || list.get(i) == 'o'
						|| list.get(i) == 'u') {
					mocnt++;
				} else {
					jacnt++;
				}
			}
			if (mocnt >= 1 && jacnt >= 2) {
				for (int i = 0; i < list.size(); i++) {
					System.out.print(list.get(i));
				}
				System.out.println();
			}
			return;
		}

		for (int i = cur; i < M; i++) {
			if (check[i])
				continue;
			check[i] = true;
			list.add(arr[i]);
			dfs(depth + 1,i);
			list.remove(list.size() - 1);
			check[i] = false;
		}

	}

}
