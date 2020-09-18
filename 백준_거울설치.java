import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 백준_거울설치 {
	static int N;
	static char[][] arr;
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new char[N][N];
		String [] str;
		for(int i=0;i<N;i++) {
			str = br.readLine().split("");
			for(int j=0;j<N;j++) {
				arr[i][j] = str[j].charAt(0);
				if(arr[i][j] =='#') {
					
				}
			}
		}
		
		
	}

	
	
	static class Node {
		int x;
		int y;
		int cnt;

		public Node(int x, int y, int cnt) {
			super();
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}

	}
}
