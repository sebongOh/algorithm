import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_소문난칠공주 {
	static int[][] map;
	static boolean[][] check;
	static boolean[] visited;
	static int res;
	static ArrayList<Integer> list;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		res = 0;
		map = new int[5][5];
		check = new boolean[5][5];
		visited = new boolean[25];
		list = new ArrayList<Integer>();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		for (int i = 0; i < 5; i++) {
			str = br.readLine().split("");
			for (int j = 0; j < 5; j++) {
				if (str[j].charAt(0) == 'Y') { // 0 : 임도연파 1 : 이다솜파
					map[i][j] = 0;
				} else {
					map[i][j] = 1;
				}
			}
		}

		solve(0, 0);
		System.out.println(res);

	}

	private static void solve(int depth, int cur) {
		if (depth == 7) {
			int cnt = 0;
			boolean flag = false;
			for (int i = 0; i < list.size(); i++) {
				if (map[list.get(i) / 5][list.get(i) % 5] == 1) {
					cnt++;
				}
				if (cnt > 3) {
					flag = true;
					break;
				}
			}
			if (flag) {
				isConnect();
			}

		}
		for (int i = cur; i < 25; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			check[i / 5][i % 5] = true;
			list.add(i);
			solve(depth + 1, i + 1);
			visited[i] = false;
			check[i / 5][i % 5] = false;
			list.remove(list.size() - 1);
		}
	}

	private static void isConnect() {
		Queue<Node> q = new LinkedList<Node>();
		int tx = 0;
		int ty = 0;
		int cnt = 1;
		boolean[][] ch = new boolean[5][5];
		ch[list.get(0) / 5][list.get(0) % 5] = true;
		q.add(new Node(list.get(0) / 5, list.get(0) % 5));
		while (!q.isEmpty()) {
			Node n = q.poll();
			for (int i = 0; i < 4; i++) {
				tx = n.x + dx[i];
				ty = n.y + dy[i];
				if (tx < 0 || tx > 4 || ty < 0 || ty > 4)
					continue;
				if (check[tx][ty] && !ch[tx][ty]) {
					ch[tx][ty] = true;
					q.add(new Node(tx, ty));
					cnt++;
				}
			}
		}
		if (cnt == 7) {
			res++;
		}
	}

	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

}
