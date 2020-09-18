import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_바이러스 {
	static int [][] arr;
	static boolean [] visited;
	static int N;
	static int count=0;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1][N+1];
		visited = new boolean[N+1];
		
		int K = Integer.parseInt(br.readLine());
		String [] str;
		
		for(int i=0;i<K;i++) {
			str = br.readLine().split(" ");
			arr[Integer.parseInt(str[0])][Integer.parseInt(str[1])]=1;
			arr[Integer.parseInt(str[1])][Integer.parseInt(str[0])]=1;
		}
		
		bfs(1);
		System.out.println(count);
		
		
		
		
	}
	private static void bfs(int start) {
		visited[start]=true;
		Queue<Integer> q = new LinkedList<>();
		q.add(start);
		while(!q.isEmpty()) {
			int n = q.poll();
			for(int i=1;i<=N;i++) {
				if(arr[n][i]==1 && !visited[i]) {
					q.add(i);
					visited[i]=true;
					count++;
				}
			}
		}
	}

}
