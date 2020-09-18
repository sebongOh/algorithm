import java.util.ArrayList;
import java.util.Scanner;

public class 증가하는수 {
	static ArrayList<Integer> list;
	static ArrayList<ArrayList> alist;
	static boolean[] visited;
	static int count = 0;
	static int[] num = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		alist = new ArrayList<ArrayList>();
		int idx = 1;
		list = new ArrayList<Integer>();
		visited = new boolean[10];
		for (int i = 1; i < num.length; i++) {
			solve(0, 0, i);
		}
		String str = "";
		for (int i = 0; i < alist.get(N).size(); i++) {
			str += alist.get(N).get(i);
		}
		System.out.println(str);
	}

	private static void solve(int depth, int cur, int cnt) {
		if (depth == cnt) {
			count++;
			alist.add(new ArrayList(list));
			return;
		}
		for (int i = cur; i < num.length; i++) {
			if (visited[i]) {
				continue;
			}
			visited[i] = true;
			list.add(num[i]);
			solve(depth + 1, i + 1, cnt);
			visited[i] = false;
			list.remove(list.size() - 1);

		}
	}
}
