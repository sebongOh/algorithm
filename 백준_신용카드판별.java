import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 백준_신용카드판별 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		char[] ch;
		for (int tc = 1; tc <= t; tc++) {
			String s = br.readLine();
			ch = s.toCharArray();

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
				System.out.println("T");
			} else {
				System.out.println("F");
			}

		}
	}

}
