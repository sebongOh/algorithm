import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_숨바꼭질2 {
	static int start, end;
	static int[] arr;
	static int cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		cnt = 0;
		str = br.readLine().split(" ");
		start = Integer.parseInt(str[0]);
		end = Integer.parseInt(str[1]);
		arr = new int[end + 2];
		for (int i = 0; i < arr.length; i++) {
			Arrays.fill(arr, Integer.MAX_VALUE);
		}

		arr[start] = 0;
		bfs();
		System.out.println(arr[end]);
		System.out.println(cnt);
	}

	private static void bfs() {
		int next = 0;
		Queue<Node> q = new LinkedList<Node>();
		q.add(new Node(start, 0));
		while (!q.isEmpty()) {
			Node n = q.poll();
			if (n.x == end) {
				cnt++;
			}
			for (int i = 0; i < 3; i++) {
				if (i == 0) {
					next = n.x + 1;
					if (next < 0 || next > end+2)
						continue;
					if (arr[next] >= n.c + 1) {
						arr[next] = n.c + 1;
						q.add(new Node(next, n.c + 1));
					}
				} else if (i == 1) {
					next = n.x - 1;
					if (next < 0 || next > end+2)
						continue;
					if (arr[next] >= n.c + 1) {
						arr[next] = n.c + 1;
						q.add(new Node(next, n.c + 1));
					}
				} else if (i == 2) {
					next = n.x * 2;
					if (next < 0 || next > end+2)
						continue;
					if (arr[next] >= n.c + 1) {
						arr[next] = n.c + 1;
						q.add(new Node(next, n.c + 1));
					}
				}
			}
		}
	}

	static class Node {
		int x;
		int c;

		public Node(int x, int c) {
			super();
			this.x = x;
			this.c = c;
		}

	}
}
