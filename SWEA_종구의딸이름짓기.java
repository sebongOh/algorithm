import java.awt.image.RescaleOp;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

public class SWEA_종구의딸이름짓기 {
	static int N, M;
	static char[][] arr;
	static boolean[][] visited;
	static ArrayList<Character> list;
	static int[] dx = { 1, 0 };
	static int[] dy = { 0, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		String[] str;

		for (int tc = 1; tc <= T; tc++) {
			list = new ArrayList<Character>();
			str = br.readLine().split(" ");
			N = Integer.parseInt(str[0]);
			M = Integer.parseInt(str[1]);
			arr = new char[N][M];
			visited = new boolean[N][M];
			for (int i = 0; i < N; i++) {
				str = br.readLine().split("");
				for (int j = 0; j < M; j++) {
					arr[i][j] = str[j].charAt(0);
				}
			}
			int tx = 0;
			int ty = 0;
			String name = "";
			PriorityQueue<Node> q = new PriorityQueue<>();
			q.add(new Node(0, 0, arr[0][0]));
			visited[0][0] = true;
			list.add(arr[0][0]);
			a: while (!q.isEmpty()) {
				int qsize = q.size();
				for (int i = 0; i < qsize; i++) {
					Node n = q.poll();
					if (n.x == N - 1 && n.y == M - 1) {
						break a;
					}
					for (int k = 0; k < 2; k++) {
						tx = n.x + dx[k];
						ty = n.y + dy[k];
						if (tx > N - 1 || ty > M - 1)
							continue;
						if (visited[tx][ty])
							continue;
						q.add(new Node(tx, ty, arr[tx][ty]));
						visited[tx][ty] = true;
					}
				}
				Node temp = q.poll();
				list.add(temp.c);
				q.clear();
				q.add(temp);
			}//while
			
			for(int i=0;i<list.size();i++) {
				name += list.get(i);
			}
			
			System.out.println("#"+tc+" "+name);

		}
	}

	static class Node implements Comparable<Node> {
		int x;
		int y;
		char c;

		public Node(int x, int y, char c) {
			super();
			this.x = x;
			this.y = y;
			this.c = c;
		}

		@Override
		public int compareTo(Node o) {
			return this.c - o.c;
		}

	}
}
