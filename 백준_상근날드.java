import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 백준_상근날드 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int 버거 = Integer.MAX_VALUE;
		int 음료 = Integer.MAX_VALUE;
		int temp=0;
		for(int i=0;i<3;i++) {
			temp = Integer.parseInt(br.readLine());
			if(버거>temp) {
				버거 = temp;
			}
		}
		
		for(int i=0;i<2;i++) {
			temp = Integer.parseInt(br.readLine());
			if(음료>temp) {
				음료 = temp;
			}
		}
		
		System.out.println((버거+음료)-50);
		
		
	}

}
