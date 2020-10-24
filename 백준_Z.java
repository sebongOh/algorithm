import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 백준_Z {
	static int size, r, c;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		int N = Integer.parseInt(str[0]);
		r = Integer.parseInt(str[1]);
		c = Integer.parseInt(str[2]);
		size = (int) Math.pow(2, N);
		// 왼쪽위오른쪽위왼쪽밑오른쪽밑
		int x = size / 2;
		int y = size / 2;
		int cnt = 0;
		for (int i = N - 1; i >= 0; i--) { // N번 반복
			int temp = (int) Math.pow(2, i) / 2;
			int square = (int) Math.pow(4, i);
			if (r < x && c < y) { // 1사분면
				x -= temp;
				y -= temp;
			} else if (r < x && c >= y) { // 2사분면
				x -= temp;
				y += temp;
				cnt += square;
			} else if (r >= x && c < y) { // 3사분면
				x += temp;
				y -= temp;
				cnt += square * 2;
			} else { // 4사분면
				x += temp;
				y += temp;
				cnt += square * 3;
			}
		}
		System.out.println(cnt);

	}

}
