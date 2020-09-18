import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class SWEA_의석이의우뚝선산 {
	static int N;
	static int[] mo;

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			mo = new int[N];
			for (int i = 0; i < N; i++) {
				mo[i] = sc.nextInt();
			}
			int res = 0;
			int start = 0;
			int top = 0;
			int bottom = 0;
			for (int i = 0; i < N; i++) {
				start = bottom = top = i;
				while (i + 1 < N && mo[i] < mo[i + 1]) // 오르막구간
					i++;
				top = i; // 오르막 끝
				while (i + 1 < N && mo[i] > mo[i + 1])// 내리막구간
					i++;
				bottom = i;
				if (start < top && top < bottom) {
					res += (top - start) * (bottom - top);
				}
				if (bottom+1 < N) {
					i--;
				}
			}
			System.out.println("#" + tc + " " + res);

		}
	}
}
