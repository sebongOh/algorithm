import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class SWEA_미생물격리2 {
	static int[][] arr;
	static int[] dx = { 0, -1, 1, 0, 0 };
	static int[] dy = { 0, 0, 0, -1, 1 };
	static int N, M, K;
	static ArrayList<Node> list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		String[] str;

		for (int t = 1; t <= tc; t++) {
			str = br.readLine().split(" ");
			N = Integer.parseInt(str[0]);
			M = Integer.parseInt(str[1]);
			K = Integer.parseInt(str[2]);
			list = new ArrayList<>();
			for (int i = 0; i < K; i++) {
				str = br.readLine().split(" ");
				list.add(new Node(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]),
						Integer.parseInt(str[3])));
			}


			for (int time = 0; time < M; time++) {

				for (int j = 0; j < list.size(); j++) {
					Node n = list.get(j);
					n.x = n.x + dx[n.dir];
					n.y = n.y + dy[n.dir];
					if (n.x == 0 || n.x == N - 1 || n.y == 0 || n.y == N - 1) {
						n.cnt /= 2;
						if (n.cnt == 0) { // 미생물이 0보다 작아지면 버림
							list.remove(j);
							j--;
							continue;
						}
						if (n.dir % 2 == 0) {
							n.dir -= 1;
						} else {
							n.dir += 1;
						}
					} // if
				} // qsize


				Collections.sort(list);

				for (int j = 0; j < list.size() - 1; j++) {
					Node n = list.get(j);
					Node next = list.get(j + 1);
					if (n.x == next.x && n.y == next.y) {
						n.cnt += next.cnt;
						list.remove(j + 1);
						j--;
					}
				}
			} // for(M)

			int result = 0;
			for (int i = 0; i < list.size(); i++) {
				result += list.get(i).cnt;
			}

			System.out.println("#" + t + " " + result);

		}

	}

	static class Node implements Comparable<Node> {
		int x;
		int y;
		int cnt;
		int dir;

		public Node(int x, int y, int cnt, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.dir = dir;
		}

		@Override
		public int compareTo(Node o) {
			if (this.x == o.x && this.y == o.y) {
				return o.cnt - this.cnt;
			} else if (this.x == o.x) {
				return this.y - o.y;
			}
			return this.x - o.x;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", cnt=" + cnt + ", dir=" + dir + "]";
		}

	}
}
