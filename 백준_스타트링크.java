import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_스타트링크 {
	static int F, S, G, U, D;
	static int[] arr;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		F = Integer.parseInt(str[0]);
		S = Integer.parseInt(str[1]);
		G = Integer.parseInt(str[2]);
		U = Integer.parseInt(str[3]);
		D = Integer.parseInt(str[4]);

		arr = new int[1000001];
		visited = new boolean[1000001];

		bfs(S, 0);
		if (S == G) {
			System.out.println("0");
		} else if (arr[G] == 0) {
			System.out.println("use the stairs");
		} else {
			System.out.println(arr[G]);
		}

	}

	private static void bfs(int start, int cnt) {
		int up = 0;
		int down = 0;
		Queue<Node> q = new LinkedList<>();
		visited[start] = true;
		q.add(new Node(start, cnt));
		while (!q.isEmpty()) {
			Node n = q.poll();
			up = n.value + U;
			down = n.value - D;
			if (0 < up && up < 1000001 && !visited[up]) {
				arr[up] = n.cnt + 1;
				visited[up] = true;
				q.add(new Node(up, n.cnt + 1));
			}
			if (0 < down && down < 1000001 && !visited[down]) {
				arr[down] = n.cnt + 1;
				visited[down] = true;
				q.add(new Node(down, n.cnt + 1));
			}
		}
	}

	static class Node {
		int value;
		int cnt;

		public Node(int value, int cnt) {
			this.value = value;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Node [value=" + value + ", cnt=" + cnt + "]";
		}

	}

}
