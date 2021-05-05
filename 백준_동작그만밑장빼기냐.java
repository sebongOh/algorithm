import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 백준_동작그만밑장빼기냐 {
	static int N;
	static int[] a, b, arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		N = Integer.parseInt(br.readLine());
		str = br.readLine().split(" ");
		a = new int[N / 2];
		b = new int[N / 2];
		arr = new int[N];
		int idx = 0;
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(str[i]);
		}
		for (int i = 0; i < N; i += 2) {
			a[idx++] = Integer.parseInt(str[i]);
		}
		idx = 0;
		for (int i = 1; i < N; i += 2) {
			b[idx++] = Integer.parseInt(str[i]);
		}
		int sum = 0;
		int max = 0;

		for (int i = 2; i < N - 1; i++) {
			for (int j = 0; j < 2; j++) {
				if (j == 0) {
					for (int k = 0; k < i; k += 2) {
						sum += arr[k];
					}
					for (int k = i; k < N - 1; k += 2) {
						sum += arr[k];
					}
					max = Math.max(max, sum);
					sum = 0;
				} else {
					for (int k = 0; k < i; k += 2) {
						sum += arr[k];
					}
					for (int k = i + 1; k < N - 1; k += 2) {
						sum += arr[k];
					}
					sum += arr[arr.length - 1];
					max = Math.max(max, sum);
					sum = 0;
				}
			}
		}

		System.out.println(max);

	}// main
}
