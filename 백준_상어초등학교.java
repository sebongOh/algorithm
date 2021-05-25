import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class 백준_상어초등학교 {
	static int N, len;
	static int[][] arr;
	static ArrayList<Integer>[] student;
	static PriorityQueue<Node> pq;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		N = Integer.parseInt(br.readLine());
		len = (int) Math.pow(N, 2);
		student = new ArrayList[len + 1];
		pq = new PriorityQueue<>();

		for (int i = 0; i < student.length; i++) {
			student[i] = new ArrayList<Integer>();
		}

		arr = new int[N][N];

		for (int i = 0; i < len; i++) {
			str = br.readLine().split(" ");
			int num = Integer.parseInt(str[0]);
			for (int j = 1; j <= 4; j++) {
				student[num].add(Integer.parseInt(str[j]));
			}
			assign(num);

			Node n = pq.poll();
			arr[n.x][n.y] = num;
			pq.clear();
		}

		System.out.println(solve());
	}

	private static int solve() {
		int sum = 0;
		int cnt = 0;
		int tx = 0;
		int ty = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int num = arr[i][j];
				for (int d = 0; d < 4; d++) {
					tx = i + dx[d];
					ty = j + dy[d];
					if (tx < 0 || tx > N - 1 || ty < 0 || ty > N - 1)
						continue;
					if (student[num].indexOf(arr[tx][ty]) != -1)
						cnt++;
				}
				switch (cnt) {
				case 0:
					sum += 0;
					break;
				case 1:
					sum += 1;
					break;
				case 2:
					sum += 10;
					break;
				case 3:
					sum += 100;
					break;
				case 4:
					sum += 1000;
					break;
				default:
					break;
				}
				cnt = 0;
			}
		}

		return sum;
	}

	private static void assign(int num) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == 0) {
					check(i, j, num);
				}
			}
		}
	}

	private static void check(int x, int y, int num) {
		int tx = 0;
		int ty = 0;
		int emptyCnt = 0;
		int likeCnt = 0;

		for (int d = 0; d < 4; d++) {
			tx = x + dx[d];
			ty = y + dy[d];
			if (tx < 0 || tx > N - 1 || ty < 0 || ty > N - 1)
				continue;
			if (arr[tx][ty] == 0) {
				emptyCnt++;
			} else {
				if (student[num].indexOf(arr[tx][ty]) != -1) {
					likeCnt++;
				}
			}
		}
		pq.add(new Node(x, y, likeCnt, emptyCnt));

	}

	static class Node implements Comparable<Node> {
		int x;
		int y;
		int like;
		int empty;

		public Node(int x, int y, int like, int empty) {
			super();
			this.x = x;
			this.y = y;
			this.like = like;
			this.empty = empty;
		}

		@Override
		public int compareTo(Node o) {
			if (this.like == o.like) {
				if (this.empty == o.empty) {
					if (this.x == o.x) {
						return this.y - o.y;
					}
					return this.x - o.x;
				}
				return o.empty - this.empty;
			}
			return o.like - this.like;
		}

	}

}