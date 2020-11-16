import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class 백준_가장긴증가하는수열4 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] memo = new int[N];
		int res = 0;
		String[] str;
		str = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(str[i]);
		}
		
		memo[0] = 1;
		for (int i = 1; i < N; i++) {
			memo[i] = 1;
			for (int j = 0; j < i; j++) {
				if (arr[j] < arr[i]) {
					if (memo[i] <= memo[j]) {
						memo[i] = memo[j] + 1;
					}
				}
			}
		}
		System.out.println(Arrays.toString(memo));
		int idx = 0;
		for (int i = 0; i < N; i++) {
			if (res < memo[i]) {
				res = memo[i];
				idx = i;
			}
		}

		Stack<Integer> stack = new Stack<Integer>();
		stack.push(arr[idx]);
		int temp = arr[idx];
		int cnt = memo[idx] - 1;
		for (int i = idx - 1; i >= 0; i--) {
			if (temp > arr[i] && memo[i] == cnt) {
				temp = arr[i];
				cnt--;
				stack.push(temp);
			}
		}
		System.out.println(res);
		while (!stack.isEmpty()) {
			System.out.print(stack.pop() + " ");
		}

	}
}
