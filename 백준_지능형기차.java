import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 백준_지능형기차 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int number=0;
		int max = Integer.MIN_VALUE;
		String [] str;
		for(int i=0;i<4;i++) {
			str = br.readLine().split(" ");
			number -= Integer.parseInt(str[0]);
			number += Integer.parseInt(str[1]);
			if(max<number) {
				max = number;
			}
		}
		System.out.println(max);
		
	}

}
