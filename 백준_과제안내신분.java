import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 백준_과제안내신분 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean [] num = new boolean[31];
		int t =0;
		for(int i=0;i<28;i++) {
			t = Integer.parseInt(br.readLine());
			num[t] = true;
		}
		
		for(int i=1;i<31;i++) {
			if(!num[i]) {
				System.out.println(i);
			}
		}
		
	}

}
