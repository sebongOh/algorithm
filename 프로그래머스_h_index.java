import java.util.Arrays;

public class 프로그래머스_h_index {
	public static void main(String[] args) {
		int[] citations = { 3, 0, 6, 1, 5 };
		int res = solution(citations);
		System.out.println(res);
	}

	private static int solution(int[] citations) {
		int answer = 0;
		Arrays.sort(citations);

		for (int i = 0; i < citations.length; i++) {
			answer = i;
			if (citations.length - i - 1 >= answer && citations[i] >= i) {
				continue;
			} else {
				break;
			}

		}

		return answer;
	}
}
