import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SWEA_벽돌깨기 {
	static int N, W, H;
	static int[][] arr;
	static int[][] copy;
	static boolean[][] visited;
	static ArrayList<Integer> list;
	static int MIN;
	static int result;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		String[] str;
		for (int tc = 0; tc < t; tc++) {
			result = 0;
			MIN = Integer.MAX_VALUE;
			str = br.readLine().split(" ");
			N = Integer.parseInt(str[0]);
			W = Integer.parseInt(str[1]);
			H = Integer.parseInt(str[2]);
			arr = new int[H][W];
			copy = new int[H][W];
			visited = new boolean[H][W];
			list = new ArrayList<Integer>();
			for (int i = 0; i < H; i++) {
				str = br.readLine().split(" ");
				for (int j = 0; j < W; j++) {
					arr[i][j] = Integer.parseInt(str[j]);
				}
			}
			dfs(0);
			System.out.println("#" + (tc + 1) + " " + MIN);
		} // tc
	}

	private static void dfs(int depth) {
		if (depth == N) {
			copy();
			for (int i = 0; i < list.size(); i++) {
				crush(list.get(i));
			}
			count();
			MIN = Math.min(MIN, result);
			return;
		}
		for (int i = 0; i < W; i++) {
			list.add(i);
			dfs(depth + 1);
			list.remove(list.size() - 1);
		}
	}

	private static void count() {
		result = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (copy[i][j] > 0) {
					result++;
				}
			}
		}
	}

	private static void copy() {
		for (int i = 0; i < H; i++) {
			copy[i] = Arrays.copyOf(arr[i], arr[i].length);
		}
	}

	private static void crush(int order) {
		Queue<Node> q = new LinkedList<>();
		int tx = 0;
		int ty = 0;
		int idx = -1;
		for (int i = 0; i < H; i++) {
			if (copy[i][order] != 0) {
				idx = i;
				break;
			}
		}
		if (idx == -1) {
			return;
		}
		q.add(new Node(idx, order));
		while (!q.isEmpty()) {
			Node n = q.poll();
			int temp = copy[n.x][n.y];
			copy[n.x][n.y] = 0;
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < temp; j++) {
					tx = n.x + dx[i] * j;
					ty = n.y + dy[i] * j;
					if (tx < 0 || tx > H - 1 || ty < 0 || ty > W - 1)
						continue;
					if (copy[tx][ty] > 0) {
						q.add(new Node(tx, ty));
					}
				}
			}
		}
		fall();

	}

	private static void fall() {
		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 0; i < W; i++) {
			for (int j = H - 1; j >= 0; j--) {
				if (copy[j][i] != 0) {
					q.add(copy[j][i]);
				}
			}
			for (int j = H - 1; j >= 0; j--) {
				if (!q.isEmpty()) {
					copy[j][i] = q.poll();
				} else {
					copy[j][i] = 0;
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
}
