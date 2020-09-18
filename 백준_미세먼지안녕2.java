import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 백준_미세먼지안녕2 {
	static int R, C, T;
	static int[][] map;
	static int[][] spreadMap;
	static Node[] airCleaner;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		R = Integer.parseInt(str[0]);
		C = Integer.parseInt(str[1]);
		T = Integer.parseInt(str[2]);
		map = new int[R][C];
		spreadMap = new int[R][C];
		airCleaner = new Node[2];

		for (int i = 0; i < R; i++) {
			str = br.readLine().split(" ");
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(str[j]);
				if (map[i][j] == -1) {
					if (airCleaner[0] == null) {
						airCleaner[0] = new Node(i, j);
					} else {
						airCleaner[1] = new Node(i, j);
					}
				}
			}
		}

		for (int i = 0; i < T; i++) {
			spread();
			init();
			clean();
		}
		int cnt = count();
		System.out.println(cnt);
	}

	private static void init() {
		for (int i = 0; i < R; i++) {
			map[i] = Arrays.copyOf(spreadMap[i], spreadMap[i].length);
		}
		map[airCleaner[0].x][airCleaner[0].y] = -1;
		map[airCleaner[1].x][airCleaner[1].y] = -1;

		for (int i = 0; i < R; i++) {
			Arrays.fill(spreadMap[i], 0);
		}
	}

	private static int count() {
		int sum = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				sum += map[i][j];
			}
		}
		return sum + 2;

	}

	private static void clean() {
		////// 윗라인
		// 아래로
		for (int i = airCleaner[0].x - 1; i > 0; i--) {
			map[i][0] = map[i - 1][0];
		}
		// 왼쪽으로
		for (int j = 0; j < C - 1; j++) {
			map[0][j] = map[0][j + 1];
		}
		// 위로
		for (int i = 0; i < airCleaner[0].x; i++) {
			map[i][C - 1] = map[i + 1][C - 1];
		}
		// 오른쪽으로
		for (int j = C - 1; j > 1; j--) {
			map[airCleaner[0].x][j] = map[airCleaner[0].x][j - 1];
		}
		map[airCleaner[0].x][1] = 0;

		///// 아랫라인
		// 위로
		for (int i = airCleaner[1].x + 1; i < R - 1; i++) {
			map[i][0] = map[i + 1][0];
		}
		// 왼쪽으로
		for (int j = 0; j < C - 1; j++) {
			map[R - 1][j] = map[R - 1][j + 1];
		}
		// 아래로
		for (int i = R - 1; i > airCleaner[1].x; i--) {
			map[i][C - 1] = map[i - 1][C - 1];
		}
		// 오른쪽으로
		for (int j = C - 1; j > 1; j--) {
			map[airCleaner[1].x][j] = map[airCleaner[1].x][j - 1];
		}
		map[airCleaner[1].x][1] = 0;
	}

	private static void spread() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				int cnt = 0;
				if (map[i][j] > 0) {
					for (int k = 0; k < 4; k++) {
						int tx = i + dx[k];
						int ty = j + dy[k];
						if (tx < 0 || tx > R - 1 || ty < 0 || ty > C - 1 || map[tx][ty] == -1) {
							continue;
						}
						spreadMap[tx][ty] += (map[i][j] / 5);
						cnt++;
					}
					spreadMap[i][j] += map[i][j] - (map[i][j] / 5) * cnt;
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
