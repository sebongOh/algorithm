import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_로봇청소기_4991_다시 {
	static int N, M;
	static char[][] map; // 입력배열
	static ArrayList<Node> list; // 0 : 청소기 , 나머지 먼지
	static ArrayList<Integer> turn; // 순열 리스트
	static boolean[][] visited; // 방문체크
	static int[][] dist; // 거리 저장 배열
	static boolean[] check; // 순열 방문체크 배열
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static int sum; // 이동거리 합
	static int min; // 결과값
	static int dust; // 먼지 갯수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		list = new ArrayList<>();
		turn = new ArrayList<>();

		while (true) {
			sum = 0;
			min = Integer.MAX_VALUE;
			dust = 0;
			turn.clear();
			list.clear();
			str = br.readLine().split(" ");
			N = Integer.parseInt(str[0]);
			M = Integer.parseInt(str[1]);
			if (N == 0 && M == 0)
				break;
			map = new char[M][N];
			visited = new boolean[M][N];
			String s;
			for (int i = 0; i < M; i++) {
				s = br.readLine();
				map[i] = s.toCharArray();
				for (int j = 0; j < s.length(); j++) {
					if (s.charAt(j) == 'o') {
						list.add(0, new Node(i, j)); // 청소기는 0번째로
					} else if (s.charAt(j) == '*') {
						list.add(new Node(i, j));
						dust++;
					}
				}
			} // for
			dist = new int[dust + 1][dust + 1];
			check = new boolean[list.size()];

			int d = possible(); // 청소기부터 bfs돌리면서 모든 먼지 갈수있는지 체크 // 거리도 저장함
			if (dust != d) {
				System.out.println("-1");
				continue;
			}

			for (int i = 1; i < list.size(); i++) {
				init();
				bfs(i); // 1번먼지부터 거리배열갱신
			}

			dfs(0); // 순열

			System.out.println(min);

		} // while

	}

	private static void init() {
		for (int i = 0; i < M; i++) {
			Arrays.fill(visited[i], false);
		}
	}

	private static void bfs(int index) {
		int tx = 0;
		int ty = 0;
		int qsize = 0;
		int ss = 0; // 거리
		int cnt = 0;

		Queue<Node> q = new LinkedList<>();
		q.add(new Node(list.get(index).x, list.get(index).y));
		visited[list.get(index).x][list.get(index).y] = true;
		while (!q.isEmpty()) {
			qsize = q.size();
			for (int i = 0; i < qsize; i++) {
				Node n = q.poll();
				for (int j = 0; j < 4; j++) {
					tx = n.x + dx[j];
					ty = n.y + dy[j];
					if (tx < 0 || tx > M - 1 || ty < 0 || ty > N - 1)
						continue;
					if (map[tx][ty] == 'o')
						continue;
					if (!visited[tx][ty] && map[tx][ty] != 'x') {
						if (map[tx][ty] == '*') {
							for (int k = index + 1; k < list.size(); k++) {
								if (list.get(k).x == tx && list.get(k).y == ty) {
									dist[index][k] = ss + 1;
									dist[k][index] = ss + 1;
									break;
								}
							}
							cnt++;
						}
						visited[tx][ty] = true;
						q.add(new Node(tx, ty));
					}
				}
				if(cnt==dust) {
					return;
				}
			}
			ss++;
		}
	}

	private static int possible() {
		int cnt = 0;
		int tx = 0;
		int ty = 0;
		int ss = 0;
		int qsize = 0;
		Queue<Node> q = new LinkedList<>();
		visited[list.get(0).x][list.get(0).y] = true;
		q.add(new Node(list.get(0).x, list.get(0).y));

		while (!q.isEmpty()) {
			qsize = q.size();
			for (int k = 0; k < qsize; k++) {
				Node n = q.poll();
				for (int i = 0; i < 4; i++) {
					tx = n.x + dx[i];
					ty = n.y + dy[i];
					if (tx < 0 || tx > M - 1 || ty < 0 || ty > N - 1)
						continue;
					if (!visited[tx][ty] && map[tx][ty] != 'x') {
						if (map[tx][ty] == '*') {
							for (int z = 1; z < list.size(); z++) {
								if (list.get(z).x == tx && list.get(z).y == ty) {
									dist[0][z] = ss + 1;
									dist[z][0] = ss + 1;
									break;
								}
							}
							cnt++;
						}
						q.add(new Node(tx, ty));
						visited[tx][ty] = true;
					}
				}
			}
			ss++;
		}
		return cnt;
	}

	private static void dfs(int depth) {
		int a = 0;
		int b = 0;
		if (depth == list.size() - 1) {
			sum += dist[0][turn.get(0)];
			for (int i = 0; i < turn.size() - 1; i++) {
				sum += dist[turn.get(i)][turn.get(i + 1)];
			}

			min = Math.min(min, sum);
			sum = 0;
			return;
		}

		for (int i = 1; i < list.size(); i++) {
			if (check[i])
				continue;
			check[i] = true;
			turn.add(i);
			dfs(depth + 1);
			turn.remove(turn.size() - 1);
			check[i] = false;

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

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + "]";
		}

	}
}
