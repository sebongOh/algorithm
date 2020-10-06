import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 백준_빗물 {
	static int H, W;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		H = Integer.parseInt(str[0]);
		W = Integer.parseInt(str[1]);
		arr = new int[W];
		str = br.readLine().split(" ");
		for (int i = 0; i < W; i++) {
			arr[i] = Integer.parseInt(str[i]);
		}
		int res = 0;
		int left = 0;
		int right = 0;
		for (int i = 1; i < W - 1; i++) {
			left = 0;
			right = 0;
			for (int j = i - 1; j >= 0; j--) { // 왼쪽
				left = Math.max(left, arr[j]);
			}
			for (int j = i + 1; j < W; j++) { // 오른쪽
				right = Math.max(right, arr[j]);
			}
			if (Math.min(right, left) - arr[i] > 0) {
				res += Math.min(right, left) - arr[i];
			}
		} // for

		System.out.println(res);

	}
}
