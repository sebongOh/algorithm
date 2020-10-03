import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_상범빌딩 {
	static int L, R, C;
	static char[][][] map;
	static boolean[][][] visited;
	static int cnt;
	static Node sang, escape;
	static int[] dz = { 1, -1 };
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static boolean flag = false;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;

		while (true) {
			cnt = 0;
			flag = false;
			str = br.readLine().split(" ");
			L = Integer.parseInt(str[0]);
			R = Integer.parseInt(str[1]);
			C = Integer.parseInt(str[2]);
			if (L == 0 && R == 0 && C == 0)
				break;
			map = new char[L][R][C];
			visited = new boolean[L][R][C];

			String s;
			for (int i = 0; i < L; i++) {
				for (int j = 0; j < R; j++) {
					s = br.readLine();
					map[i][j] = s.toCharArray();
				}
				s = br.readLine();
			}

			for (int i = 0; i < L; i++) {
				for (int j = 0; j < R; j++) {
					for (int k = 0; k < C; k++) {
						if (map[i][j][k] == 'S') {
							sang = new Node(i, j, k);
						} else if (map[i][j][k] == 'E') {
							escape = new Node(i, j, k);
						}
					}
				}
			}

			bfs();
			if (!flag) {
				System.out.println("Trapped!");
			} else {
				System.out.println("Escaped in " + (cnt+1) + " minute(s).");
			}
		}

	}

	private static void bfs() {
		int tx = 0;
		int ty = 0;
		int tz = 0;
		Queue<Node> q = new LinkedList<>();
		visited[sang.z][sang.x][sang.y] = true;
		q.add(new Node(sang.z, sang.x, sang.y));
		while (!q.isEmpty()) {
			int qsize = q.size();
			for (int i = 0; i < qsize; i++) {
				Node n = q.poll();
				for (int j = 0; j < 4; j++) {
					tz = n.z;
					tx = n.x + dx[j];
					ty = n.y + dy[j];
					if (tx < 0 || tx > R - 1 || ty < 0 || ty > C - 1)
						continue;
					if (map[tz][tx][ty] == '#')
						continue;
					if (!visited[tz][tx][ty] && map[tz][tx][ty] == '.') {
						visited[tz][tx][ty] = true;
						q.add(new Node(tz, tx, ty));
					} else if (map[tz][tx][ty] == 'E') {
						flag = true;
						return;
					}
				}
				for (int j = 0; j < 2; j++) {
					tx = n.x;
					ty = n.y;
					tz = n.z + dz[j];
					if (tz < 0 || tz > L - 1)
						continue;
					if (map[tz][tx][ty] == '#')
						continue;
					if (!visited[tz][tx][ty] && map[tz][tx][ty] == '.') {
						visited[tz][tx][ty] = true;
						q.add(new Node(tz, tx, ty));
					} else if (map[tz][tx][ty] == 'E') {
						flag = true;
						return;
					}
				}
			} // qsize
			cnt++;
		} // while

	}

	static class Node {
		int z;
		int x;
		int y;

		public Node(int z, int x, int y) {
			super();
			this.z = z;
			this.x = x;
			this.y = y;
		}

	}

}