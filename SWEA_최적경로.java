import java.util.ArrayList;
import java.util.Scanner;

public class SWEA_최적경로 {
	static int N;
	static Node[] arr;
	static ArrayList<Integer> list;
	static boolean [] visited;
	static int result;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		String[] str;
		list = new ArrayList<Integer>();
		
		for (int tc = 1; tc <= t; tc++) {
			result = Integer.MAX_VALUE;
			list.clear();
			N = sc.nextInt();
			arr = new Node[N + 2];
			visited = new boolean[N];
			for (int i = 0; i < N + 2; i++) {
				arr[i] = new Node(sc.nextInt(), sc.nextInt());
			}
			
			dfs(0);
			System.out.println("#"+tc+" "+result);
			
		} //t
		
	}// main

	private static void dfs(int depth) {
		if(depth==N) {
			solve();
			return;
		}
		for(int i=0;i<N;i++) {
			if(visited[i])continue;
			visited[i]=true;
			list.add(i+2);
			dfs(depth+1);
			visited[i]=false;
			list.remove(list.size()-1);
		}
		
	}

	private static void solve() {
		int sum = 0;
		sum = Math.abs(arr[0].x - arr[list.get(0)].x) + Math.abs(arr[0].y - arr[list.get(0)].y);
		for(int i=0;i<list.size()-1;i++) {
			sum += Math.abs(arr[list.get(i)].x - arr[list.get(i+1)].x) + Math.abs(arr[list.get(i)].y - arr[list.get(i+1)].y);
			if(result<sum)return;
		}
		sum += Math.abs(arr[list.get(N-1)].x - arr[1].x) + Math.abs(arr[list.get(N-1)].y - arr[1].y); 
		result = Math.min(sum, result);
	}

	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + "]";
		}

	}
}
