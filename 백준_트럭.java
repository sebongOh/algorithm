import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 백준_트럭 {
	static int N, W, L;
	static int[] arr;
	static int cnt;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str;
		str = br.readLine().split(" ");
		N = Integer.parseInt(str[0]);
		W = Integer.parseInt(str[1]);
		L = Integer.parseInt(str[2]);
		arr = new int[N];
		str = br.readLine().split(" ");

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(str[i]);
		}
		int sum = 0;
		Queue<Integer> q = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			while (true) {
				if (q.size() == W) {
					sum -= q.poll();
				}
				if (sum + arr[i] <= L) {
					break;
				}
				q.add(0);
				cnt++;
			}
			q.add(arr[i]);
			sum += arr[i];
			cnt++;
		}
		
		cnt += W;
		
		System.out.println(cnt);

	}
}
