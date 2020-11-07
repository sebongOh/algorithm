import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_가스관 {
	static int R, C;
	static boolean hackFlag, resFlag;
	static char[] ch = { '|', '-', '+', '1', '2', '3', '4' };
	static char[][] map;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static Queue<Node> q;
	static ArrayList<Node> list;
	static char resLetter;
	static Node mos, zag, hackingPoint;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		R = Integer.parseInt(str[0]);
		C = Integer.parseInt(str[1]);
		map = new char[R][C];
		hackingPoint = new Node(0, 0);
		hackFlag = false;
		resFlag = false;
		list = new ArrayList<>();
		for (int i = 0; i < R; i++) {
			str = br.readLine().split("");
			for (int j = 0; j < C; j++) {
				map[i][j] = str[j].charAt(0);
				if (map[i][j] == 'M') {
					mos = new Node(i, j, 0);
				} else if (map[i][j] == 'Z') {
					zag = new Node(i, j, 0);
				}
				if (map[i][j] != '.' && map[i][j] != 'M' && map[i][j] != 'Z') {
					list.add(new Node(i, j));
				}
			}
		}

		searchDirect();
		if (!hackFlag) { // 못찾았으면 찾으러 ㄱㄱ
			searchPoint();
		}
		// 7개 넣어보면서 복구하기

		for (int i = 0; i < 7; i++) {
			char letter = ch[i];
			map[hackingPoint.x][hackingPoint.y] = letter;
			go();
			if (resFlag) {
				resLetter = letter;
				break;
			}
		}
		System.out.println((hackingPoint.x + 1) + " " + (hackingPoint.y + 1) + " " + resLetter);

	}

	private static void go() {
		int tx = 0, ty = 0;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(mos.x, mos.y, mos.d));
		while (!q.isEmpty()) {
			Node n = q.poll();
			tx = n.x + dx[n.d];
			ty = n.y + dy[n.d];
			if (tx < 0 || tx > R - 1 || ty < 0 || ty > C - 1)
				continue;
			char c = map[tx][ty];
			if (c == '|') {
				if (n.d == 0 || n.d == 2) {
					q.add(new Node(tx, ty, n.d));
				}
			} else if (c == '-') {
				if (n.d == 1 || n.d == 3) {
					q.add(new Node(tx, ty, n.d));
				}
			} else if (c == '+') {
				q.add(new Node(tx, ty, n.d));
			} else if (c == '1') {
				if (n.d == 3) {
					q.add(new Node(tx, ty, 2));
				} else if (n.d == 0) {
					q.add(new Node(tx, ty, 1));
				}
			} else if (c == '2') {
				if (n.d == 3) {
					q.add(new Node(tx, ty, 0));
				} else if (n.d == 2) {
					q.add(new Node(tx, ty, 1));
				}
			} else if (c == '3') {
				if (n.d == 2) {
					q.add(new Node(tx, ty, 3));
				} else if (n.d == 1) {
					q.add(new Node(tx, ty, 0));
				}
			} else if (c == '4') {
				if (n.d == 1) {
					q.add(new Node(tx, ty, 2));
				} else if (n.d == 0) {
					q.add(new Node(tx, ty, 3));
				}
			} else if (c == 'Z') {
				resFlag = true;
				break;
			}

		}
	}

	private static void searchPoint() {
		int tx = 0, ty = 0;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(mos.x, mos.y, mos.d));
		while (!q.isEmpty()) {
			Node n = q.poll();
			tx = n.x + dx[n.d];
			ty = n.y + dy[n.d];
			char c = map[tx][ty];
			if (c == '|') {
				q.add(new Node(tx, ty, n.d));
			} else if (c == '-') {
				q.add(new Node(tx, ty, n.d));
			} else if (c == '+') {
				for (int i = 0; i < 3; i++) {
					q.add(new Node(tx, ty, (n.d + i) % 4));
				}
			} else if (c == '1') {
				if (n.d == 3) {
					q.add(new Node(tx, ty, 2));
				} else if (n.d == 0) {
					q.add(new Node(tx, ty, 1));
				}
			} else if (c == '2') {
				if (n.d == 3) {
					q.add(new Node(tx, ty, 0));
				} else if (n.d == 2) {
					q.add(new Node(tx, ty, 1));
				}
			} else if (c == '3') {
				if (n.d == 2) {
					q.add(new Node(tx, ty, 3));
				} else if (n.d == 1) {
					q.add(new Node(tx, ty, 0));
				}
			} else if (c == '4') {
				if (n.d == 1) {
					q.add(new Node(tx, ty, 2));
				} else if (n.d == 0) {
					q.add(new Node(tx, ty, 3));
				}
			} else if (c == 'Z')
				continue;
			else if (c == '.') {
				hackFlag = true;
				hackingPoint.x = tx;
				hackingPoint.y = ty;
				break;
			}
		}

	}

	private static void searchDirect() {
		int tx = 0;
		int ty = 0;
		for (int i = 0; i < 4; i++) {
			tx = mos.x + dx[i];
			ty = mos.y + dy[i];
			if (tx < 0 || tx > R - 1 || ty < 0 || ty > C - 1)
				continue;
			if (map[tx][ty] != '.' && map[tx][ty] != 'Z') {
				mos.d = i;
				break;
			}
		} // for

	}

	static class Node {
		int x;
		int y;
		int d;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		public Node(int x, int y, int d) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", d=" + d + "]";
		}

	}
}
