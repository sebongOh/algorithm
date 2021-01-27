import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 프로그래머스_모의고사 {
	public static void main(String[] args) {
		int[] answers = { 1, 3, 2, 4, 2 };
		int[] answer = solution(answers);
		System.out.println(Arrays.toString(answer));
	}

	private static int[] solution(int[] answers) {
		int[] answer = {};
		int[] man1 = { 1, 2, 3, 4, 5 };
		int[] man2 = { 2, 1, 2, 3, 2, 4, 2, 5 };
		int[] man3 = { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 };
		int[] cnt = new int[4];
		int max = 0;
		for (int i = 0; i < answers.length; i++) {
			if (answers[i] == man1[i % 5]) {
				cnt[1]++;
			}
			if (answers[i] == man2[i % 8]) {
				cnt[2]++;
			}
			if (answers[i] == man3[i % 10]) {
				cnt[3]++;
			}
		}
		List<Integer> list = new ArrayList<Integer>();
		max = Math.max(cnt[3], Math.max(cnt[1], cnt[2]));
		for (int i = 1; i < cnt.length; i++) {
			if (max == cnt[i]) {
				list.add(i);
			}
		}
		answer = new int[list.size()];
		for (int i = 0; i < answer.length; i++) {
			answer[i] = list.get(i);
		}

		return answer;
	}

}
