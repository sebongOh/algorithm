import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SWEA_무선충전 {
	static int M, A;
	static int[][] user;
	static ArrayList<Node> bc;
	static int[] dx = { 0, -1, 0, +1, 0 };
	static int[] dy = { 0, 0, 1, 0, -1 };
	static ArrayList<Integer> alist;
	static ArrayList<Integer> blist;
	static int result, max;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		String[] str;
		for (int tc = 1; tc <= t; tc++) {
			result = 0;
			max = Integer.MIN_VALUE;
			alist = new ArrayList<Integer>();
			blist = new ArrayList<Integer>();
			bc = new ArrayList<>();
			str = br.readLine().split(" ");
			M = Integer.parseInt(str[0]);
			A = Integer.parseInt(str[1]);
			user = new int[2][M + 1];
			for (int i = 0; i < 2; i++) {
				str = br.readLine().split(" ");
				for (int j = 1; j <= M; j++) {
					user[i][j] = Integer.parseInt(str[j - 1]);
				}
			}
			bc.add(new Node(0, 0, 0, 0));
			for (int i = 0; i < A; i++) {
				str = br.readLine().split(" ");
				bc.add(new Node(Integer.parseInt(str[1]), Integer.parseInt(str[0]), Integer.parseInt(str[2]),
						Integer.parseInt(str[3])));
			}

			solve();
			System.out.println("#"+tc+" "+result);
		} // tc
	}// main

	private static void solve() {
		int atx = 1;
		int aty = 1;
		int btx = 10;
		int bty = 10;
		int sum = 0;

		for (int i = 0; i < M + 1; i++) {

			atx += dx[user[0][i]];
			aty += dy[user[0][i]];
			alist = check(atx, aty);

			btx += dx[user[1][i]];
			bty += dy[user[1][i]];
			blist = check(btx, bty);

			sum = 0;
			if (alist.size() == 0) {
				alist.add(0);
			}
			if (blist.size() == 0) {
				blist.add(0);
			}

			for (int j = 0; j < alist.size(); j++) {
				for (int k = 0; k < blist.size(); k++) {
					sum = 0;
					if (alist.get(j) == blist.get(k)) {
						sum += bc.get(alist.get(j)).p;
					} else {
						sum += bc.get(alist.get(j)).p;
						sum += bc.get(blist.get(k)).p;
					}
					max = Math.max(sum, max);
				}
			}
			result += max;
			max = Integer.MIN_VALUE;
		}
	}

	private static ArrayList<Integer> check(int tx, int ty) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < bc.size(); i++) {
			int temp = Math.abs(tx - bc.get(i).x) + Math.abs(ty - bc.get(i).y);
			if (bc.get(i).c >= temp) {
				list.add(i);
			}
		}
		return list;
	}

	static class Node {
		int x;
		int y;
		int c;
		int p;

		public Node(int x, int y, int c, int p) {
			super();
			this.x = x;
			this.y = y;
			this.c = c;
			this.p = p;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", c=" + c + ", p=" + p + "]";
		}

	}
}
