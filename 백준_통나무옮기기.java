import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_통나무옮기기 {
	static Tree[] start, end;
	static int N;
	static char[][] map;
	static boolean[][][] visited;
	static int res;
	static int[] dx = { -1, -1, -1, 0, 1, 1, 1, 0 };
	static int[] dy = { -1, 0, 1, 1, 1, 0, -1, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		res = 0;
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		visited = new boolean[N][N][5];
		start = new Tree[3];
		end = new Tree[3];
		int bcnt = 0;
		int ecnt = 0;
		for (int i = 0; i < N; i++) {
			str = br.readLine().split("");
			for (int j = 0; j < N; j++) {
				map[i][j] = str[j].charAt(0);
				if (map[i][j] == 'B') {
					map[i][j] = '0';
					start[bcnt++] = new Tree(i, j, 0, 0);
				} else if (map[i][j] == 'E') {
					end[ecnt++] = new Tree(i, j, 0, 0);
				}
			}
		}
		if (start[0].x == start[1].x) {
			start[1].type = 2; // 2 가로
		} else {
			start[1].type = 3; // 3 세로
		}
		if (end[0].x == end[1].x) {
			end[1].type = 2;
		} else {
			end[1].type = 3;
		}

		bfs();
		System.out.println(res);

	}

	private static void bfs() {
		Queue<Tree> q = new LinkedList<>();
		q.add(new Tree(start[1].x, start[1].y, 0, start[1].type));
		visited[start[1].x][start[1].y][start[1].type] = true;
		while (!q.isEmpty()) {
			Tree t = q.poll();
			if (t.x == end[1].x && t.y == end[1].y && t.type == end[1].type) {
				res = t.cnt;
				break;
			}

			int tx = 0, ty = 0, cnt = 0;
			for (int i = 0; i < 8; i++) {
				tx = t.x + dx[i];
				ty = t.y + dy[i];
				if (tx < 0 || tx > N - 1 || ty < 0 || ty > N - 1)
					continue;
				if (map[tx][ty] != '1') {
					cnt++;
				}
			}

			if (cnt == 8) {
				if (t.type == 2 && !visited[t.x][t.y][3]) {
					visited[t.x][t.y][3] = true;
					q.add(new Tree(t.x, t.y, t.cnt + 1, 3));
				} else if (t.type == 3 && !visited[t.x][t.y][2]) {
					visited[t.x][t.y][2] = true;
					q.add(new Tree(t.x, t.y, t.cnt + 1, 2));
				}
			}

			if (t.type == 2) { // 가로
				if (t.x - 1 >= 0 && map[t.x - 1][t.y] != '1' && map[t.x - 1][t.y - 1] != '1'
						&& map[t.x - 1][t.y + 1] != '1' && !visited[t.x - 1][t.y][t.type]) { // UUU
					visited[t.x - 1][t.y][t.type] = true;
					q.add(new Tree(t.x - 1, t.y, t.cnt + 1, t.type));
				}
				if (t.x + 1 < N && map[t.x + 1][t.y] != '1' && map[t.x + 1][t.y - 1] != '1' // DDD
						&& map[t.x + 1][t.y + 1] != '1' && !visited[t.x + 1][t.y][t.type]) {
					visited[t.x + 1][t.y][t.type] = true;
					q.add(new Tree(t.x + 1, t.y, t.cnt + 1, t.type));
				}
				if (t.y + 2 < N && map[t.x][t.y + 2] != '1' && !visited[t.x][t.y + 1][t.type]) { // RRR
					visited[t.x][t.y + 1][t.type] = true;
					q.add(new Tree(t.x, t.y + 1, t.cnt + 1, t.type));
				}
				if (t.y - 2 >= 0 && map[t.x][t.y - 2] != '1' && !visited[t.x][t.y - 1][t.type]) { // LLL
					visited[t.x][t.y - 1][t.type] = true;
					q.add(new Tree(t.x, t.y - 1, t.cnt + 1, t.type));
				}
			} else if (t.type == 3) { // 세로
				if (t.x - 2 >= 0 && map[t.x - 2][t.y] != '1' && !visited[t.x - 1][t.y][t.type]) { // UUU
					visited[t.x - 1][t.y][t.type] = true;
					q.add(new Tree(t.x - 1, t.y, t.cnt + 1, t.type));
				}
				if (t.x + 2 < N && map[t.x + 2][t.y] != '1' && !visited[t.x + 1][t.y][t.type]) { /// DDD
					visited[t.x + 1][t.y][t.type] = true;
					q.add(new Tree(t.x + 1, t.y, t.cnt + 1, t.type));
				}
				if (t.y + 1 < N && map[t.x][t.y + 1] != '1' && map[t.x - 1][t.y + 1] != '1'
						&& map[t.x + 1][t.y + 1] != '1' && !visited[t.x][t.y + 1][t.type]) { // RRR
					visited[t.x][t.y + 1][t.type] = true;
					q.add(new Tree(t.x, t.y + 1, t.cnt + 1, t.type));
				}
				if (t.y - 1 >= 0 && map[t.x][t.y - 1] != '1' && map[t.x - 1][t.y - 1] != '1'
						&& map[t.x + 1][t.y - 1] != '1' && !visited[t.x][t.y - 1][t.type]) { // LLL
					visited[t.x][t.y - 1][t.type] = true;
					q.add(new Tree(t.x, t.y - 1, t.cnt + 1, t.type));
				}
			}

		}

	}

	static class Tree {
		int x;
		int y;
		int cnt;
		int type;

		public Tree(int x, int y, int cnt, int type) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.type = type;
		}

		@Override
		public String toString() {
			return "Tree [x=" + x + ", y=" + y + ", cnt=" + cnt + ", type=" + type + "]";
		}

	}
}
