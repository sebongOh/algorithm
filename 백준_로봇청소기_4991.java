import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_로봇청소기_4991 {
	static int N, M;
	static char[][] map;
	static ArrayList<Node> list;
	static ArrayList<Integer> turn;
	static boolean[][] visited;
	static boolean[] check;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int sum;
	static int min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		list = new ArrayList<>();
		turn = new ArrayList<>();
		int dust;
		while (true) {
			sum = 0;
			min = Integer.MAX_VALUE;
			dust = 0;
			turn.clear();
			list.clear();
			str = br.readLine().split(" ");
			N = Integer.parseInt(str[0]);
			M = Integer.parseInt(str[1]);
			if (N == 0 && M == 0)
				break;
			map = new char[M+1][N+1];
			visited = new boolean[M+1][N+1];
			String s;
			for (int i = 1; i <= M; i++) {
				s = br.readLine();
				map[i] = s.toCharArray();
				for (int j = 0; j < s.length(); j++) {
					if (s.charAt(j) == 'o') {
						list.add(0, new Node(i, j+1));
					} else if (s.charAt(j) == '*') {
						list.add(new Node(i, j+1));
						dust++;
					}
				}
			} // for
			
			check = new boolean[list.size()];
			int d = bfs();
			if (dust != d) {
				System.out.println("-1");
				continue;
			}

			dfs(0);

			System.out.println(min);

		} // while

	}

	private static int bfs() {
		int cnt = 0;
		int tx = 0;
		int ty = 0;

		Queue<Node> q = new LinkedList<>();
		visited[list.get(0).x][list.get(0).y] = true;
		q.add(new Node(list.get(0).x, list.get(0).y));
		
		while (!q.isEmpty()) {
			Node n = q.poll();
			for (int i = 0; i < 4; i++) {
				tx = n.x + dx[i];
				ty = n.y + dy[i];
				if (tx < 1 || tx > M-1 || ty < 1 || ty > N-1)
					continue;
				if (!visited[tx][ty] && map[tx][ty] != 'x') {
					if (map[tx][ty] == '*')
						cnt++;
					q.add(new Node(tx, ty));
					visited[tx][ty] = true;
				}
			}
		}
		return cnt;
	}

	private static void dfs(int depth) {
		int a=0;
		int b=0;
		if (depth == list.size() - 1) {
			a = (int) Math.pow((list.get(0).x - list.get(turn.get(0)).x), 2);
			b = (int) Math.pow((list.get(0).y - list.get(turn.get(0)).y), 2);
			sum += Math.sqrt(a + b);

			for (int i = 0; i < turn.size() - 1; i++) {
				a = (int) Math.pow((list.get(turn.get(i)).x - list.get(turn.get(i + 1)).x), 2);
				b = (int) Math.pow((list.get(turn.get(i)).y - list.get(turn.get(i + 1)).y), 2);
				sum += Math.sqrt(a + b);
			}
			
			min = Math.min(min, sum);
			sum = 0;
			return;
		}

		for (int i = 1; i < list.size(); i++) {
			if (check[i])
				continue;
			check[i] = true;
			turn.add(i);
			dfs(depth + 1);
			turn.remove(turn.size() - 1);
			check[i] = false;

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
