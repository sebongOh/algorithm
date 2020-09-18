import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 백준_연산자끼워넣기 {
	static int N;
	static int[] arr;
	static int[] ope;
	static boolean[] check;
	static ArrayList<Integer> list;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	static int sum = 0;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		N = Integer.parseInt(br.readLine());

		arr = new int[N];
		ope = new int[N - 1];
		list = new ArrayList<Integer>();
		check = new boolean[N-1];
		str = br.readLine().split(" ");
		for (int i = 0; i < str.length; i++) {
			arr[i] = Integer.parseInt(str[i]);
		}
		str = br.readLine().split(" ");
		int t = 0;
		for (int i = 0; i < str.length; i++) {
			int temp = Integer.parseInt(str[i]);
			for (int j = 0; j < temp; j++) {
				ope[t] = i;
				t++;
			}
		}
		sum = arr[0];
		dfs(0);
		
		System.out.println(max);
		System.out.println(min);

	}

	private static void dfs(int depth) {
		if (depth == N - 1) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i) == 0) {
					sum += arr[i + 1];
				} else if (list.get(i) == 1) {
					sum -= arr[i + 1];
				} else if (list.get(i) == 2) {
					sum *= arr[i + 1];
				} else if (list.get(i) == 3) {
					sum /= arr[i + 1];
				}
			}

			max = Math.max(max, sum);
			min = Math.min(min, sum);
			sum = arr[0];
			
			return;
			

		}
		for (int i = 0; i < N - 1; i++) {
			if (check[i])
				continue;
			check[i] = true;
			list.add(ope[i]);
			dfs(depth + 1);
			list.remove(list.size() - 1);
			check[i] = false;
		}
	}

}
