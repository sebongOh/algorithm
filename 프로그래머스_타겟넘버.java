
public class 프로그래머스_타겟넘버 {
	static boolean[] visited;
	static int cnt;

	public static void main(String[] args) {
		int[] numbers = { 1, 1, 1, 1, 1 };
		int target = 3;
		int ans = solution(numbers, target);
		System.out.println(ans);
	}

	private static int solution(int[] numbers, int target) {
		cnt = 0;
		visited = new boolean[numbers.length];

		dfs(numbers, target, 0, 0);

		return cnt;
	}

	private static void dfs(int[] numbers, int target, int depth, int cur) {
		if (depth == visited.length) {
			if (solve(numbers, target)) {
				cnt++;
			}
			return;
		}

		if (solve(numbers, target)) {
			cnt++;
		}

		for (int i = cur; i < visited.length; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			dfs(numbers, target, depth + 1, i);
			visited[i] = false;
		}

	}

	private static boolean solve(int[] numbers, int target) {
		int sum = 0;
		for (int i = 0; i < visited.length; i++) {
			if (visited[i]) {
				sum += numbers[i];
			} else {
				sum -= numbers[i];
			}
		}
		if (sum == target) {
			return true;
		} else {
			return false;
		}
	}

}
