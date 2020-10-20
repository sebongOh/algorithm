import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class 백준_암호만들기2 {
	static int L, C;
	static char[] arr;
	static boolean[] visited;
	static ArrayList<Character> list;
	static ArrayList<String> select;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		L = Integer.parseInt(str[0]);
		C = Integer.parseInt(str[1]);
		arr = new char[C];
		str = br.readLine().split(" ");
		for (int i = 0; i < str.length; i++) {
			arr[i] = str[i].charAt(0);
		}
		Arrays.sort(arr);
		visited = new boolean[C];
		list = new ArrayList<Character>();
		select = new ArrayList<String>();
		solve(0,0);
		Collections.sort(select);
		
		for(String s : select) {
			System.out.println(s);
		}
		
	}

	private static void solve(int depth,int cur) {
		if (depth == L) {
			int zaumcnt = 0;
			int moumcnt = 0;
			String s = "";
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) == 'a' || list.get(i) == 'e' || list.get(i) == 'i' || list.get(i) == 'o'
						|| list.get(i) == 'u') {
					moumcnt++;
				} else {
					zaumcnt++;
				}
			}
			if (zaumcnt > 1 && moumcnt > 0) {
				for (int i = 0; i < list.size(); i++) {
					s += list.get(i);
				}
				select.add(s);
			}
		}
		for (int i = cur; i < C; i++) {
			if (visited[i])
				continue;
			list.add(arr[i]);
			visited[i] = true;
			solve(depth + 1,i+1);
			visited[i] = false;
			list.remove(list.size() - 1);
		}

	}
}
