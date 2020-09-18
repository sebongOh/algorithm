import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_보물섬 {
	static int N, M;
	static char[][] map;
	static boolean[][] visited;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		map = new char[N][M];
		int ans=0;
		int max=Integer.MIN_VALUE;
		visited = new boolean[N][M];
		String s;
		for (int i = 0; i < N; i++) {
			s = br.readLine();
			map[i] = s.toCharArray();
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 'L' && !visited[i][j]) {
					visited[i][j] = true;
					ans = bfs(i, j);
					max = Math.max(max, ans);
					bool();
				}
			}
		}
		System.out.println(max);

	}

	private static void bool() {
		for(int i=0;i<N;i++) {
			Arrays.fill(visited[i], false);
		}
	}

	private static int bfs(int x, int y) {
		int tx = 0;
		int ty = 0;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(x, y));
		int size = 0;
		int cnt = -1;
		while (!q.isEmpty()) {
			cnt++;
			size = q.size();
			for (int i = 0; i < size; i++) {
				Node n = q.poll();
				for (int k = 0; k < 4; k++) {
					tx = n.x + dx[k];
					ty = n.y + dy[k];
					if (tx < 0 || tx > N - 1 || ty < 0 || ty > M - 1)
						continue;
					if(map[tx][ty]=='L' && !visited[tx][ty]) {
						q.add(new Node(tx,ty));
						visited[tx][ty]=true;
					}
				}
			}
		}
		return cnt;
	}

}

class Node {
	int x;
	int y;

	public Node(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

}
