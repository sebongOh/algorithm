import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_스타트와링크 {
	static int F, S, G, U, D;
	static int[] building;
	static int[] dx;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		F = Integer.parseInt(str[0]);
		S = Integer.parseInt(str[1]);
		G = Integer.parseInt(str[2]);
		U = Integer.parseInt(str[3]);
		D = Integer.parseInt(str[4]);
		if(S==G) {
			System.out.println(0);
			return;
		}
		dx = new int[2];
		dx[0] = U;
		dx[1] = -D;
		building = new int[F + 1];
		Arrays.fill(building, Integer.MAX_VALUE);
		int next = 0;
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(S, 0));
		while (!q.isEmpty()) {
			Node n = q.poll();
			for (int i = 0; i < 2; i++) {
				next = n.x + dx[i];
				if (next < 1 || next > F)
					continue;
				if (building[next] <= n.cnt + 1)
					continue;
				building[next] = n.cnt + 1;
				q.add(new Node(next, n.cnt + 1));
			}
		}

		if (building[G] == Integer.MAX_VALUE) {
			System.out.println("use the stairs");
		} else {
			System.out.println(building[G]);
		}

	}

	static class Node {
		int x;
		int cnt;

		public Node(int x, int cnt) {
			this.x = x;
			this.cnt = cnt;
		}
	}
}