import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import java.io.BufferedReader;

public class 백준_다오의데이트 {
	static int N, M;
	static char[][] arr;
	static int ch;
	static char[][] turn;
	static Dao d;
	static boolean flag = false;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		arr = new char[N + 1][M + 1];
		ArrayList<Character> list = new ArrayList<Character>();
		for (int i = 1; i <= N; i++) {
			str = br.readLine().split("");
			for (int j = 1; j <= M; j++) {
				arr[i][j] = str[j - 1].charAt(0);
				if (arr[i][j] == 'D') {
					d = new Dao(i, j, list);
				}
			}
		}

		ch = Integer.parseInt(br.readLine());
		turn = new char[ch][2];
		for (int i = 0; i < ch; i++) {
			str = br.readLine().split(" ");
			for (int j = 0; j < 2; j++) {
				turn[i][j] = str[j].charAt(0);
			}
		}

//		for(int i=1;i<N+1;i++) {
//			for(int j=1;j<M+1;j++) {
//				System.out.print(arr[i][j]);
//			}
//			System.out.println();
//		}
//		
//		for(int i=0;i<ch;i++) {
//			for(int j=0;j<2;j++) {
//				System.out.print(turn[i][j]);
//			}
//			System.out.println();
//		}

		bfs();

		if (!flag) {
			System.out.println("NO");
		}
		
	}

	private static void bfs() {
		int tx = 0;
		int ty = 0;
		char dir = 0;
		Queue<Dao> q = new LinkedList<>();
		q.add(new Dao(d.x, d.y, d.list));
		int idx = 0;
		a: while (!q.isEmpty()) {
			for (int a = 0; a < ch; a++) {
				int qsize = q.size();
				for (int j = 0; j < qsize; j++) {
					Dao dao = q.poll();
					
					for (int i = 0; i < 2; i++) {
						if (turn[idx][i] == 'A') {
							tx = dao.x;
							ty = dao.y - 1;
							dir = 'A';
						} else if (turn[idx][i] == 'W') {
							tx = dao.x - 1;
							ty = dao.y;
							dir = 'W';
						} else if (turn[idx][i] == 'D') {
							tx = dao.x;
							ty = dao.y + 1;
							dir = 'D';
						} else if (turn[idx][i] == 'S') {
							tx = dao.x + 1;
							ty = dao.y;
							dir = 'S';
						}
						if (tx < 1 || tx > N || ty < 1 || ty > M)
							continue;
						if (arr[tx][ty] == '@')
							continue;
						if (arr[tx][ty] == '.') {
							dao.list.add(dir);
							q.add(new Dao(tx, ty, dao.list));
						} else if (arr[tx][ty] == 'Z') {
							dao.list.add(dir);
							q.add(new Dao(tx,ty,dao.list));
						}
					}
				}
			}

		}

	}

	static class Dao {
		int x;
		int y;
		ArrayList<Character> list;

		public Dao(int x, int y, ArrayList<Character> list) {
			super();
			this.x = x;
			this.y = y;
			this.list = list;
		}

	}

}
