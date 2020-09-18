import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class 백준_최단경로 {
	static int K;
	static int V, E;
	static int u, v, w;
	static int[] dist;
	static boolean[] visited;
	static ArrayList<Node>[] list;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		V = Integer.parseInt(str[0]);
		E = Integer.parseInt(str[1]);
		dist = new int[V + 1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		list = new ArrayList[V + 1];
		for (int i = 0; i < V + 1; i++) {
			list[i] = new ArrayList<Node>();
		}
		visited = new boolean[V + 1];
		K = Integer.parseInt(br.readLine());

		for (int i = 0; i < E; i++) {
			str = br.readLine().split(" ");
			u = Integer.parseInt(str[0]);
			v = Integer.parseInt(str[1]);
			w = Integer.parseInt(str[2]);
			list[u].add(new Node(v, w));
		}

		go();
		
		for(int i=1;i<V+1;i++) {
			if(dist[i]==Integer.MAX_VALUE) {
				System.out.println("INF");
			}else {
				System.out.println(dist[i]);
			}
		}
	}

	private static void go() {
		PriorityQueue<Node> q = new PriorityQueue<>();
		q.add(new Node(K, 0));
		dist[K] = 0;
		while (!q.isEmpty()) {
			int n = q.poll().index;
			if (visited[n])
				continue;
			visited[n] = true;
			for (Node node : list[n]) {
				if (dist[node.index] > dist[n] + node.value) {
					dist[node.index] = dist[n] + node.value;
					q.add(new Node(node.index, dist[node.index]));
				}

			}
		}
	}

	static class Node implements Comparable<Node>{
		int index;
		int value;

		public Node(int index, int value) {
			this.index = index;
			this.value = value;
		}

		@Override
		public int compareTo(Node o) {
			return this.value-o.value;
		}
		
		
	}
}
