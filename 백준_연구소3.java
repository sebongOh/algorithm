import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_연구소3 {
	static int N, M;
	static int[][] arr;
	static boolean[][] visited;
	static boolean[] check;
	static ArrayList<Node> list;
	static ArrayList<Integer> select;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int max;
	static int result;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		max = Integer.MIN_VALUE;
		result = Integer.MAX_VALUE;
		str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		arr = new int[N][N];
		visited = new boolean[N][N];
		list = new ArrayList<>();
		select = new ArrayList<>();
		int count = 0;

		for (int i = 0; i < N; i++) {
			str = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(str[j]);
				if (arr[i][j] == 2) {
					list.add(new Node(i, j));
				} else if (arr[i][j] == 0) {
					count++;
				}
			}
		}

		if (count == 0) {
			System.out.println("0");
			return;
		}

		check = new boolean[list.size()];

		select(0, 0);

		if (result == Integer.MAX_VALUE) {
			System.out.println("-1");
		} else {
			System.out.println(result);
		}

	}

	private static void select(int depth, int cur) {
		if (depth == M) {
			go(arr);
			result = Math.min(result, max);
			init();
			return;
		}
		for (int i = cur; i < list.size(); i++) {
			if (check[i])
				continue;
			check[i] = true;
			select.add(i);
			select(depth + 1, i + 1);
			check[i] = false;
			select.remove(select.size() - 1);
		}

	}

	private static void init() {
		for (int i = 0; i < N; i++) {
			Arrays.fill(visited[i], false);
		}
	}

	private static void go(int[][] arr) {
		int[][] copy = new int[N][N];

		for (int i = 0; i < N; i++) {
			copy[i] = Arrays.copyOf(arr[i], N);
		}

		max = Integer.MIN_VALUE;
		int tx = 0;
		int ty = 0;
		Queue<Node> q = new LinkedList<>();
		for (int i = 0; i < M; i++) {
			int x = list.get(select.get(i)).x;
			int y = list.get(select.get(i)).y;
			q.add(new Node(x, y));
			visited[x][y] = true;
			copy[x][y] = -999;
		}
		int time = 1;

		while (!q.isEmpty()) {
			int qsize = q.size();
			for (int i = 0; i < qsize; i++) {
				Node n = q.poll();
				for (int j = 0; j < 4; j++) {
					tx = dx[j] + n.x;
					ty = dy[j] + n.y;
					if (tx < 0 || tx > N - 1 || ty < 0 || ty > N - 1 || visited[tx][ty])
						continue;
					if (copy[tx][ty] == 0 || copy[tx][ty]==2) {
						copy[tx][ty] = time;
						visited[tx][ty] = true;
						q.add(new Node(tx, ty));
					}
				} // for
			} // qsize
			time++;
		} // while

		
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && copy[i][j] == 0) {
					max = Integer.MAX_VALUE;
					return;
				}
				if(arr[i][j]==0 && max < copy[i][j]) {
					max = copy[i][j];
				}
			}
		}

	}

	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}
}
