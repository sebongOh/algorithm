import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 백준_RBY팡 {
	static int N, min, sum;
	static int[] arr;
	static ArrayList<Integer> list = new ArrayList<Integer>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		min = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		for (int i = 0; i < N; i++) {
			for (int j = 1; j < 4; j++) {
				if (arr[i] != j) {
					int cnt = solve(i, j, arr);
					min = Math.min(min, cnt);
				}
			}
		}
		System.out.println(min);

	}

	private static int solve(int n, int changenum, int[] arr) {
		list.clear();
		for (int i = 0; i < arr.length; i++) {
			list.add(arr[i]);
		}
		list.set(n, changenum);
		int start = n;
		int end = n;
		while (start > 0 && end < list.size() - 1) {
			int curcolor = list.get(start);
			if (list.size() < 4) {
				break;
			}
			while (start > 0) {
				if (list.get(start-1) == curcolor) {
					start--;
				} else {
					break;
				}
			}
			while (end < list.size() - 1) {
				if (list.get(end+1) == curcolor) {
					end++;
				} else {
					break;
				}
			}
			if (end - start >= 3) {
				for (int i = start; i <= end; i++) {
					list.remove(start);
				}
				end = start;
			} else {
				break;
			}
		}
		return list.size();
	}
}
