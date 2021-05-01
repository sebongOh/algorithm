import java.util.Arrays;

public class 프로그래머스_예산 {
	public static void main(String[] args) {
		int[] d = { 1, 3, 2, 5, 4 };
		int budget = 9;
		int res = solution(d, budget);
		System.out.println(res);
	}

	private static int solution(int[] d, int budget) {
		int answer = 0;
		int sum = 0;
		Arrays.sort(d);

		for (int i = 0; i < d.length; i++) {
			sum += d[i];
			if (sum > budget) {
				answer = i;
				break;
			}
		}
		if (sum <= budget) {
			answer = d.length;
		}

		return answer;
	}
}
