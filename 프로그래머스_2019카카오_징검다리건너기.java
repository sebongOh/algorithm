
public class 프로그래머스_2019카카오_징검다리건너기 {
	static int max, min;

	public static void main(String[] args) {
		int[] stones = { 2, 4, 5, 3, 2, 1, 4, 2, 5, 1 };
		int k = 3;
		int answer = 0;
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;

		for (int a : stones) {
			max = Math.max(max, a);
			min = Math.min(min, a);
		}

		answer = go(stones, k, min, max);

		System.out.println(answer);

	}

	private static int go(int[] stones, int k, int low, int high) {
		if (low == high) {
			return low;
		}

		while (low < high) {
			int mid = low + (high - low) / 2;
			if (solve(stones, mid, k)) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		return low - 1;
	}

	private static boolean solve(int[] stones, int friends, int k) {
		int cnt = 0;
		for (int stone : stones) {
			if (stone - friends < 0) {
				cnt++;
			} else {
				cnt = 0;
			}
			if (cnt == k)
				return false;
		}

		return true;
	}
}
