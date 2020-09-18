import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 백준_연구소 {
	static int N,M;
	static int [][] arr;
	public static void main(String[] args) throws Exception	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String [] str;
		str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		M = Integer.parseInt(str[1]);
		arr = new int[N][M];
		
		for(int i=0;i<N;i++) {
			str = br.readLine().split(" ");
			for(int j=0;j<M;j++) {
				arr[i][j] = Integer.parseInt(str[j]);
			}
		}
		
		
	}

}