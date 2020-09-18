import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 백준_욕심쟁이판다 {
	static int N;
	static int[][] arr;
	static int cnt = 0;
	static int max = Integer.MIN_VALUE;
	static int[][] memoization;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		memoization = new int[N][N];

		String[] str;
		for (int i = 0; i < N; i++) {
			str = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(str[j]);
			}
		}

		
		
		int m = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				m = Math.max(m, go(i, j));
			}
		}
		
		
		
		
		System.out.println(m);
	
	}

	private static int go(int x, int y) {
		int tx = 0;
		int ty = 0;
		if(memoization[x][y]!=0) {
			return memoization[x][y];
		}
		int max = 1;
		for (int i = 0; i < 4; i++) {
			tx = x + dx[i];	
			ty = y + dy[i];
			if(tx<0|| tx>N-1 || ty<0 || ty>N-1)continue;
			if(arr[x][y]<arr[tx][ty]) {
				max = Math.max(max, go(tx,ty)+1);
				memoization[x][y] = max;
			}
		}
		return max;
	}

}
