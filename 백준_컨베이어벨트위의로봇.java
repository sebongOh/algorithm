import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 백준_컨베이어벨트위의로봇 {
	static int N, K, cnt;
	static int[] arr;
	static boolean[] robot;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		K = Integer.parseInt(str[1]);
		arr = new int[2 * N];
		robot = new boolean[N];
		str = br.readLine().split(" ");
		for (int i = 0; i < arr.length; i++) {  
			arr[i] = Integer.parseInt(str[i]);
		}
		int idx = 1;
		while (true) {
			rotate();
			robotGo();
			if (arr[0] > 0 && !robot[0]) {
				arr[0]--;
				if (arr[0] == 0) {
					cnt++;
				}
				robot[0] = true;
			}
			if (cnt >= K) {
				break;
			}
			idx++;
		}
		System.out.println(idx);

	}

	private static void robotGo() {
		for (int i = N - 2; i >= 0; i--) {
			if (robot[i]) {
				if (!robot[i + 1] && arr[i + 1] > 0) {
					robot[i] = false;
					robot[i + 1] = true;
					arr[i + 1]--;
					if (arr[i + 1] == 0) {
						cnt++;
					}
				}
			}
		}
		if (robot[N - 1]) {
			robot[N - 1] = false;
		}
	}

	private static void rotate() {
		int temp = arr[2 * N - 1];
		for (int i = 2 * N - 1; i > 0; i--) {
			arr[i] = arr[i - 1];
		}
		arr[0] = temp;
		for (int i = N - 1; i > 0; i--) {
			robot[i] = robot[i - 1];
		}
		robot[0] = false;
		if (robot[N - 1]) {
			robot[N - 1] = false;
		}

	}

}
