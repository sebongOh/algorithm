import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 백준_리모컨 {
	static boolean[] broken;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int page = Integer.parseInt(br.readLine());
		int blen = Integer.parseInt(br.readLine());
		broken = new boolean[10];
		String[] str;
		if (blen != 0) {
			str = br.readLine().split(" ");
			for (int i = 0; i < str.length; i++) {
				int n = Integer.parseInt(str[i]);
				broken[n] = true;
			}

		}

		int ans = Math.abs(page - 100);

		for (int i = 0; i <= 1000000; i++) {
			int temp = i;
			int len = possible(temp);
			if (len > 0) {
				int press = Math.abs(temp - page);
				if (ans > len + press) {
					ans = len + press;
				}
			}
		}

		System.out.println(ans);

	}

	private static int possible(int temp) {
		if (temp == 0) {
			if (broken[0]) {
				return 0;
			} else {
				return 1;
			}
		}
		int len = 0;
		while (temp > 0) {
			if (broken[temp % 10]) {
				return 0;
			}
			len += 1;
			temp /= 10;
		}
		return len;
	}
}
