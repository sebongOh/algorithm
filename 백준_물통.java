import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class 백준_물통 {
	static int[] bottle;
	static int A, B, C;
	static Set<Integer> set;
	static int[] start = { 0, 0, 1, 1, 2, 2, };
	static int[] end = { 1, 2, 0, 2, 0, 1 };
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		bottle = new int[3];
		visited = new boolean[201][201];
		set = new TreeSet<Integer>();
		str = br.readLine().split(" ");
		for (int i = 0; i < 3; i++) {
			bottle[i] = Integer.parseInt(str[i]);
		}
		Queue<Node> q = new LinkedList<Node>();
		q.add(new Node(0, 0));
		set.add(bottle[2]);
		visited[0][0] = true;

		while (!q.isEmpty()) {
			Node n = q.poll();
			int x = n.x;
			int y = n.y;
			int z = bottle[2] - x - y;
			for (int i = 0; i < 6; i++) {
				int[] next = { x, y, z };
				next[end[i]] += next[start[i]];
				next[start[i]] = 0;

				if (next[end[i]] > bottle[end[i]]) {
					next[start[i]] = next[end[i]] - bottle[end[i]];
					next[end[i]] = bottle[end[i]];
				}
				if (!visited[next[0]][next[1]]) {
					visited[next[0]][next[1]] = true;
					q.add(new Node(next[0], next[1]));
					if (next[0] == 0) {
						set.add(next[2]);
					}
				}

			}
		} // while

		Iterator<Integer> it = set.iterator();
		while (it.hasNext()) {
			System.out.print(it.next()+" ");
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
