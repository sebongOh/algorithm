import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 백준_점수계산 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int [] num = new int[8];
		int [] score = new int [8];
		int temp=0;
		for(int i=0;i<8;i++) {
			temp = Integer.parseInt(br.readLine());
			num[i] = temp;
			score[i] = temp;
		}
		Arrays.sort(score);
		int result=0;
		for(int i=7;i>2;i--) {
			result += score[i];
		}
		int [] res = new int[5];
		int cnt=0;
		a:for(int i=7;i>2;i--) {
			for(int j=0;j<8;j++) {
				if(score[i]==num[j]) {
					res[cnt]=j+1;
					cnt++;
					continue a;
				}
			}
		}
		
		Arrays.sort(res);
		
		
		System.out.println(result);
		for(int i=0;i<5;i++) {
			System.out.print(res[i]+" ");
		}
	}

}
