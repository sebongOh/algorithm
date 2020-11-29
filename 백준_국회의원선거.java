import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class 백준_국회의원선거 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			arr[i] = num;
		}
		int dasom = arr[0];

		for (int i = 1; i < arr.length; i++) {
			pq.add(arr[i]);
		}
		int ans = 0;
		while (!pq.isEmpty()) {
			int temp = pq.poll();
			if (dasom > temp) {
				break;
			}
			dasom++;
			ans++;
			temp--;
			pq.add(temp);
		}
		
		System.out.println(ans);

	}// main
}
