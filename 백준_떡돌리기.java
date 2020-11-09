import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class 백준_떡돌리기 {
	static int N, M, X, Y;
	static ArrayList<Node>[] list;
	static boolean[] visited;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		X = Integer.parseInt(str[2]);
		Y = Integer.parseInt(str[3]);
		arr = new int[N];
		visited = new boolean[N];
		Arrays.fill(arr, 987654321);
		list = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			str = br.readLine().split(" ");
			int x = Integer.parseInt(str[0]);
			int y = Integer.parseInt(str[1]);
			int k = Integer.parseInt(str[2]);
			list[x].add(new Node(y, k));
			list[y].add(new Node(x, k));
		}
		visited[Y] = true;
		arr[Y] = 0;
		for (int i = 0; i < list[Y].size(); i++) {
			arr[list[Y].get(i).x] = list[Y].get(i).k;
		}
		for (int j = 0; j < N; j++) {
			int min = Integer.MAX_VALUE;
			int idx = 0;
			for (int i = 0; i < N; i++) {
				if (!visited[i]) {
					if (min > arr[i]) {
						min = arr[i];
						idx = i;
					}
				}
			}
			visited[idx] = true;
			for (int i = 0; i < list[idx].size(); i++) {
				int x = list[idx].get(i).x;
				int k = list[idx].get(i).k;
				arr[x] = Math.min(arr[x], arr[idx] + k);
			}
		}
		Arrays.sort(arr);
		int time = 0;
		int res = 1;
		for (int i = 0; i < N; i++) {
			if (X < arr[i] * 2) {
				res = -1;
				break;
			}
			time += arr[i] * 2;
			if (time > X) {
				res++;
				time = arr[i] * 2;
			}
		}
		System.out.println(res);

	}// main

	static class Node {
		int x;
		int k;

		public Node(int x, int k) {
			super();
			this.x = x;
			this.k = k;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", k=" + k + "]";
		}

	}
}
