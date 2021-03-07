import java.util.LinkedList;
import java.util.Queue;

public class 프로그래머스_다리를지나는트럭 {
	public static void main(String[] args) {
		int bridge_length = 2;
		int weight = 10;
		int[] truck_weights = {7,4,5,6};
		int res = solution(bridge_length, weight, truck_weights);
		System.out.println(res);
	}

	private static int solution(int bridge_length, int weight, int[] truck_weights) {
		int res = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		int sum = 0;
		for (int i = 0; i < truck_weights.length; i++) {

			while (true) {
				if (q.isEmpty()) { // 큐가 비었다
					q.add(truck_weights[i]);
					sum += truck_weights[i];
					res++;
					break;
				} else if (q.size() == bridge_length) {
					sum -= q.poll();
				} else {
					if (sum + truck_weights[i] > weight) {
						q.add(0);
						res++;
					} else {
						q.add(truck_weights[i]);
						sum += truck_weights[i];
						res++;
						break;
					}

				}
			}

		}

		return res+bridge_length;
	}
}
