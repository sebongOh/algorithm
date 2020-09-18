import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 백준_치킨배달 {
	// 0 빈칸, 1 집 , 2 치킨집
	static int N, M;
	static int[][] map;
	static ArrayList<Node> house;
	static ArrayList<Node> list;
	static ArrayList<Integer> select;
	static boolean[] visited;
	static int Min;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		map = new int[N][N];
		list = new ArrayList<>();
		house = new ArrayList<>();
		select = new ArrayList<Integer>();
		Min = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			str = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(str[j]);
				if (map[i][j] == 2) {
					list.add(new Node(i, j));
				} else if (map[i][j] == 1) {
					house.add(new Node(i, j));
				}
			}
		}
		visited = new boolean[list.size()];
		dfs(0, 0);
		System.out.println(Min);

	}

	private static void dfs(int depth, int cur) {
		if (depth == M) {
			int distSum = 0;
			for (int i = 0; i < house.size(); i++) {
				int dist = Integer.MAX_VALUE;
				for (int j = 0; j < select.size(); j++) {
					int idx = select.get(j);
					dist = Math.min(dist,
							Math.abs(house.get(i).x - list.get(idx).x) + Math.abs(house.get(i).y - list.get(idx).y));
				}
				distSum += dist;
			}
			Min = Math.min(Min, distSum);
			return;
		}
		for (int i = cur; i < list.size(); i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			select.add(i);
			dfs(depth + 1, i + 1);
			visited[i] = false;
			select.remove(select.size() - 1);
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
