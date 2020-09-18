import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class SWEA_줄기세포배양 {
	static int N, M, K;
	static int[][] map;
	static boolean[][] visited;
	static PriorityQueue<Node> live;
	static Queue<Node> q;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		String[] str;
		for (int tc = 0; tc < t; tc++) {
			q = new LinkedList<>();
			live = new PriorityQueue<>();
			str = br.readLine().split(" ");
			N = Integer.parseInt(str[0]);
			M = Integer.parseInt(str[1]);
			K = Integer.parseInt(str[2]);
			visited = new boolean[N + K * 2][M + K * 2];
			for (int i = 0; i < N; i++) {
				str = br.readLine().split(" ");
				for (int j = 0; j < M; j++) {
					int life = Integer.parseInt(str[j]);
					if (life > 0) {
						Node n = new Node(i + K, j + K, life);
						visited[n.x][n.y] = true;
						live.add(n);
					}
				}
			} // for

			for (int i = 0; i < K; i++) {
				q.clear();
				while (!live.isEmpty()) {
					Node n = live.poll();
					if (n.state == 0) { // 비활성이면
						n.wait--;
						if (n.wait == 0) {
							n.state = 1;
						}
						q.add(n);
					} else if (n.state == 1) { // 활성이면
						for (int j = 0; j < 4; j++) {
							int tx = n.x + dx[j];
							int ty = n.y + dy[j];
							if (!visited[tx][ty]) { // 방문안했으면 고
								visited[tx][ty] = true;
								q.add(new Node(tx, ty, n.life));
							}
						} // for
						n.life--;
						if (n.life > 0) {
							q.add(n);
						}
					}
				}
				while (!q.isEmpty()) {
					live.add(q.poll());
				}
			}
			System.out.println("#" + (tc + 1) + " " + live.size());

		} // tc
	}// main

	static class Node implements Comparable<Node> {
		int x;
		int y;
		int wait;
		int life;
		int state;

		public Node(int x, int y, int life) {
			super();
			this.x = x;
			this.y = y;
			this.wait = this.life = life;
		}

		@Override
		public int compareTo(Node o) {
			return o.life - this.life;
		}

	}

}
