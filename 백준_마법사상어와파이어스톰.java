import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_마법사상어와파이어스톰 {
	static int N, q, size;
	static int[] Q;
	static int[][] map;
	static ArrayList<Node> list;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		q = Integer.parseInt(str[1]);
		Q = new int[q];
		size = (int) Math.pow(2, N);
		map = new int[size][size];
		list = new ArrayList<>();
		visited = new boolean[size][size];
		for (int i = 0; i < size; i++) {
			str = br.readLine().split(" ");
			for (int j = 0; j < size; j++) {
				map[i][j] = Integer.parseInt(str[j]);
			}
		}
		str = br.readLine().split(" ");
		for (int i = 0; i < q; i++) {
			Q[i] = Integer.parseInt(str[i]);
		}

		for (int i = 0; i < Q.length; i++) {
			int idx = Q[i];
			divide(idx);
			crush();
			for (int[] a : map) {
				System.out.println(Arrays.toString(a));
			}
			System.out.println();
		}

		int iceCnt = count();
		int maxCnt = Integer.MIN_VALUE;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (!visited[i][j] && map[i][j] > 0) {
					int iceSize = bfs(i, j);
					maxCnt = Math.max(maxCnt, iceSize);
				}

			}
		}

		System.out.println(iceCnt);
		if (maxCnt == Integer.MIN_VALUE) {
			System.out.println(0);
		} else {
			System.out.println(maxCnt);
		}

	}// main

	private static int count() {
		int sum = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				sum += map[i][j];
			}
		}
		return sum;
	}

	private static int bfs(int x, int y) {
		int cnt = 0, tx = 0, ty = 0;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(x, y));
		visited[x][y] = true;
		while (!q.isEmpty()) {
			Node n = q.poll();
			for (int i = 0; i < 4; i++) {
				tx = n.x + dx[i];
				ty = n.y + dy[i];
				if (tx < 0 || tx > size - 1 || ty < 0 || ty > size - 1)
					continue;
				if (!visited[tx][ty] && map[tx][ty] > 0) {
					cnt++;
					q.add(new Node(tx, ty));
					visited[tx][ty] = true;
				}
			} // for

		} // while

		return cnt + 1;

	}

	private static void crush() {
		int cnt = 0, tx = 0, ty = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				cnt = 0;
				for (int d = 0; d < 4; d++) {
					tx = i + dx[d];
					ty = j + dy[d];
					if (tx < 0 || tx > size - 1 || ty < 0 || ty > size - 1) {
						continue;
					}
					if (map[tx][ty] > 0) {
						cnt++;
					}
				} // dir
				if (cnt < 3) {
					list.add(new Node(i, j));
				}
			}
		} // for
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				int x = list.get(i).x;
				int y = list.get(i).y;
				if (map[x][y] > 0) {
					map[x][y]--;
				}
			}
			list.clear();
		}
	}

	private static void divide(int idx) {
		int point = (int) Math.pow(2, idx);
		int section = size / point;
		int[][] copy = new int[size][size];
		int row = -point;
		int col = -point;
		for (int i = 0; i < section; i++) {
			col = -point;
			row += point;
			for (int j = 0; j < section; j++) {
				col += point;
				for (int a = 0; a < point; a++) {
					for (int b = 0; b < point; b++) {
//						System.out.print((a + row) + " " + (b + col));
//						System.out.print(map[a + row][b + col]);
//						copy[a + row][b + col] = map[a + row][b + col];
						copy[b + row][col - a + point - 1] = map[a + row][b + col];
//						System.out.print((b + col) + " " + (row - a + point - 1) + " ");
//						System.out.println();
					}
				}
			}
		}
//		for (int[] a : copy) {
//			System.out.println(Arrays.toString(a));
//		}
		map = copy;
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
