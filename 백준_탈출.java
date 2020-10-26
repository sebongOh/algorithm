import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_탈출 {
	static int R, C, res;
	static char[][] map;
	static boolean flag;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static ArrayList<Node> wList;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		res = 0;
		R = Integer.parseInt(str[0]);
		C = Integer.parseInt(str[1]);
		map = new char[R][C];
		wList = new ArrayList<>();
		int hx = 0, hy = 0;
		for (int i = 0; i < R; i++) {
			str = br.readLine().split("");
			for (int j = 0; j < C; j++) {
				map[i][j] = str[j].charAt(0);
				if (map[i][j] == '*') {
					wList.add(new Node(i, j));
				} else if (map[i][j] == 'S') {
					hx = i;
					hy = j;
				}
			}
		}

		bfs(hx, hy);
		if(flag) {
			System.out.println(res);
		}else {
			System.out.println("KAKTUS");
		}

	}

	private static void bfs(int hx, int hy) {
		int tx, ty = 0;
		flag = false;
		int cnt = 0;
		Queue<Node> hedgehog = new LinkedList<>();
		Queue<Node> water = new LinkedList<>();
		hedgehog.add(new Node(hx, hy));
		for (int i = 0; i < wList.size(); i++) {
			water.add(new Node(wList.get(i).x, wList.get(i).y));
		}
		a: while (!hedgehog.isEmpty()) {

			int waterSize = water.size();
			for (int i = 0; i < waterSize; i++) {
				Node n = water.poll();
				for (int j = 0; j < 4; j++) {
					tx = n.x + dx[j];
					ty = n.y + dy[j];
					if (tx < 0 || tx > R - 1 || ty < 0 || ty > C - 1)
						continue;
					if (map[tx][ty] == 'X' || map[tx][ty] == 'D')
						continue;
					if (map[tx][ty] == '.' || map[tx][ty] == '?') {
						water.add(new Node(tx, ty));
						map[tx][ty] = '*';
					}
				}

			}
			int hedgehogSize = hedgehog.size();
			for (int i = 0; i < hedgehogSize; i++) {
				Node n = hedgehog.poll();
				for (int j = 0; j < 4; j++) {
					tx = n.x + dx[j];
					ty = n.y + dy[j];
					if (tx < 0 || tx > R - 1 || ty < 0 || ty > C - 1)
						continue;
					if (map[tx][ty] == 'X' || map[tx][ty] == '*')
						continue;
					if (map[tx][ty] == '.') {
						hedgehog.add(new Node(tx, ty));
						map[tx][ty] = 'S';
						map[n.x][n.y] = '?';
					} else if (map[tx][ty] == 'D') {
						flag = true;
						res++;
						break a;
					}
				}
			}
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
