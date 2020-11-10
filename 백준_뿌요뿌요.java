import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_뿌요뿌요 {
	static char[][] map;
	static boolean[][] visited;
	static ArrayList<Node> equals;
	static ArrayList<Node> list;
	static int cnt, res;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader((new InputStreamReader(System.in)));
		cnt = 0;
		res = 0;
		map = new char[12][6];
		visited = new boolean[12][6];
		list = new ArrayList<>();
		equals = new ArrayList<>();
		String[] str;
		for (int i = 0; i < 12; i++) {
			map[i] = br.readLine().toCharArray();
		}

		while (true) {
			for (int i = 11; i >= 0; i--) {
				for (int j = 0; j < 6; j++) {
					if (map[i][j] != '.' && !visited[i][j]) {
						solve(i, j);
					}
				}
			}
			if (list.size() == 0) {
				break;
			} else {
				crush();
				down();
				list.clear();
				res++;
			}
			for (int i = 0; i < 12; i++) {
				Arrays.fill(visited[i], false);
			}
		} // while

		System.out.println(res);

	}

	private static void down() {
		Queue<Character> q = new LinkedList<Character>();
		for (int i = 0; i < 6; i++) {
			for (int j = 11; j >= 0; j--) {
				if (map[j][i] != '.') {
					q.add(map[j][i]);
				}
			}
			for (int j = 11; j >= 0; j--) {
				if (q.isEmpty()) {
					map[j][i] = '.';
				} else {
					map[j][i] = q.poll();
				}
			}
		}
	}

	private static void crush() {
		int x = 0, y = 0;
		for (int i = 0; i < list.size(); i++) {
			x = list.get(i).x;
			y = list.get(i).y;
			map[x][y] = '.';
		}
	}

	private static void solve(int i, int j) {
		int tx = 0, ty = 0;
		char temp = map[i][j];
		Queue<Node> q = new LinkedList<>();
		visited[i][j] = true;
		equals.add(new Node(i, j));
		q.add(new Node(i, j));
		while (!q.isEmpty()) {
			Node n = q.poll();
			for (int d = 0; d < 4; d++) {
				tx = n.x + dx[d];
				ty = n.y + dy[d];
				if (tx < 0 || tx > 11 || ty < 0 || ty > 5)
					continue;
				if (map[tx][ty] == temp && !visited[tx][ty]) {
					equals.add(new Node(tx, ty));
					q.add(new Node(tx, ty));
					visited[tx][ty] = true;
				}
			}
		}
		if (equals.size() >= 4) {
			for (int s = 0; s < equals.size(); s++) {
				list.add(new Node(equals.get(s).x, equals.get(s).y));
			}
			equals.clear();
		} else {
			equals.clear();
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

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + "]";
		}

	}
}
