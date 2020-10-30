import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

public class 백준_N과M_11 {
	static int N, M;
	static int[] arr;
	static ArrayList<Integer> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		arr = new int[N];
		list = new ArrayList<Integer>();
		str = br.readLine().split(" ");
		for (int i = 0; i < str.length; i++) {
			arr[i] = Integer.parseInt(str[i]);
		}
		Arrays.sort(arr);
		dfs(0);

	}

	private static void dfs(int depth) {
		if (depth == M) {
			for (int i = 0; i < list.size(); i++) {
				System.out.print(list.get(i) + " ");
			}
			System.out.println();
			return;
		}
		int temp = 0;
		for (int i = 0; i < N; i++) {
			if (arr[i] == temp)
				continue;
			list.add(arr[i]);
			dfs(depth + 1);
			temp = arr[i];
			list.remove(list.size() - 1);
		}

	}
}
