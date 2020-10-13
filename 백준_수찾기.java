import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 백준_수찾기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		int[] arr;
		int N = Integer.parseInt(br.readLine());
		arr = new int[N];
		str = br.readLine().split(" ");
		for (int i = 0; i < str.length; i++) {
			arr[i] = Integer.parseInt(str[i]);
		}
		Arrays.sort(arr);
		int M = Integer.parseInt(br.readLine());
		str = br.readLine().split(" ");
		for (int i = 0; i < M; i++) {
			int num = Integer.parseInt(str[i]);
			int min = 0;
			int mid = arr.length / 2;
			int max = arr.length-1;
			boolean flag = false;
			while (min <= max) {
				mid = (max + min) / 2;
				if (arr[mid] < num) {
					min = mid+1;
				} else if (arr[mid] > num) {
					max = mid-1;
				} else if (arr[mid] == num) {
					flag = true;
					break;
				}
			}
			if (flag) {
				System.out.println("1");
			} else {
				System.out.println("0");
			}

		}

	}
}
