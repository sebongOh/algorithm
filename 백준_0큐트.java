import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 백준_0큐트 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int cnt = 0;
		int t=0;
		for (int i = 0; i < N; i++) {
			t = Integer.parseInt(br.readLine());
			if(t==0) {
				cnt--;
			}else {
				cnt++;
			}
		}
		if(cnt>0) {
			System.out.println("Junhee is cute!");
		}else {
			System.out.println("Junhee is not cute!");
		}
	}

}
