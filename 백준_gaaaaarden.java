import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_gaaaaarden {
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int[][] map; // 0:호수 , 1:배양액x , 2:배양o
	static int[][][] timemap;
	static int N, M, G, R, cnt, result = Integer.MIN_VALUE;
	static boolean[][][] visited;
	static boolean[] select;
	static boolean[] select2;
	static ArrayList<Node> possible;
	static ArrayList<Integer> firlist;
	static int[] g;
	static int[] r;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		G = Integer.parseInt(str[2]);
		R = Integer.parseInt(str[3]);
		g = new int[G];
		r = new int[R];
		map = new int[N][M];
		possible = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			str = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(str[j]);
				if (map[i][j] == 2) {
					possible.add(new Node(i, j));
				}
			}
		}
		firlist = new ArrayList<>();
		visited = new boolean[N][M][2];
		timemap = new int[N][M][2];
		
		select = new boolean[possible.size()];
		select2 = new boolean[R + G];

		perm(0, 0);

		System.out.println(result);

	}

	private static void perm(int depth, int cur) {
		if (depth == G + R) {
			// 배양액 뿌릴 수 있는 위치 중 G+R 갯수만큼 자리 선정
			perm2(0, 0);
			return;
		}
		for (int i = cur; i < possible.size(); i++) {
			if (select[i]) {
				continue;
			}
			select[i] = true;
			firlist.add(i);
			perm(depth + 1, i + 1);
			select[i] = false;
			firlist.remove(firlist.size() - 1);
		}
	}

	private static void perm2(int depth, int cur) {
		if (depth == G) {
			int ridx = 0;
			int gidx = 0;
			for (int i = 0; i < select2.length; i++) {
				if (select2[i]) {
					g[gidx++] = firlist.get(i);
				} else {
					r[ridx++] = firlist.get(i);
				}
			}

			bfs();
			result = Math.max(result, cnt/2);
			init();

			return;
		}
		for (int i = cur; i < firlist.size(); i++) {
			if (select2[i]) {
				continue;
			}
			select2[i] = true;
			perm2(depth + 1, i + 1);
			select2[i] = false;
		}

	}

	private static void init() {
		Arrays.fill(r, 0);
		Arrays.fill(g, 0);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				for (int k = 0; k < 2; k++) {
					visited[i][j][k] = false;
					timemap[i][j][k] = 0;
				}
			}
		}
	}

	private static void bfs() {
		// 0 : red , 1 : green
		int time = 0;
		cnt = 0;
		int tx = 0;
		int ty = 0;

		Queue<State> q = new LinkedList<>();
		for (int i = 0; i < r.length; i++) {
			int x = possible.get(r[i]).x;
			int y = possible.get(r[i]).y;
			q.add(new State(x, y, 0));
			visited[x][y][0] = true;
		}
		for (int i = 0; i < g.length; i++) {
			int x = possible.get(g[i]).x;
			int y = possible.get(g[i]).y;
			q.add(new State(x, y, 1));
			visited[x][y][1] = true;
		}

		while (!q.isEmpty()) {
			time++;
			int qsize = q.size();
			for (int i = 0; i < qsize; i++) {
				State s = q.poll();
				if (timemap[s.x][s.y][0] != 0 && timemap[s.x][s.y][0] == timemap[s.x][s.y][1]) {
					cnt++;
					continue;
				}
				for (int j = 0; j < 4; j++) {
					tx = s.x + dx[j];
					ty = s.y + dy[j];

					if (tx >= 0 && tx < N && ty >= 0 && ty < M && !visited[tx][ty][s.type] && map[tx][ty] != 0) {
						visited[tx][ty][s.type] = true;
						timemap[tx][ty][s.type] = time;
						q.add(new State(tx, ty, s.type));
					}
				}
			}
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

	static class State {
		int x;
		int y;
		int type;

		public State(int x, int y, int type) {
			super();
			this.x = x;
			this.y = y;
			this.type = type;
		}

	}

}
