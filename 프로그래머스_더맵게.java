import java.util.PriorityQueue;

public class 프로그래머스_더맵게 {
	public static void main(String[] args) {
		int[] scoville = { 1, 2, 3, 9, 10, 12 };
		int K = 7;
		int res = solution(scoville, K);
		System.out.println(res);
	}

	private static int solution(int[] scoville, int K) {
		int cnt = 0;

		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		for (int i = 0; i < scoville.length; i++) {
			q.add(scoville[i]);
		}

		while (true) {
			if (q.peek() < K) {
				if (q.size() < 2)
					return -1;
				int a = q.poll();
				int b = q.poll();
				q.add((a + b * 2));
				cnt++;
			} else {
				break;
			}
		}

		return cnt;

	}
}
