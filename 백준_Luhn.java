import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 백준_Luhn {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		char[] ch = s.toCharArray();
		int first = 0;
		int second = 0;
		for (int i = 14; i >= 0; i -= 2) {
			int num = ch[i] - '0';
			num *= 2;
			if (num > 9) {
				first += num / 10;
				first += num % 10;
			} else {
				first += num;
			}
		}
		for (int i = 15; i > 0; i -= 2) {
			int num = ch[i] - '0';
			second += num;
		}
		if ((first + second) % 10 == 0) {
			System.out.println("DA");
		} else {
			System.out.println("NE");
		}
	}
}
