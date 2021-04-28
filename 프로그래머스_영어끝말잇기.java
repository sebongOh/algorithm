import java.util.Arrays;
import java.util.LinkedHashSet;

public class 프로그래머스_영어끝말잇기 {
	public static void main(String[] args) {
		int n = 3;
		String[] words = { "tank", "kick", "know", "wheel", "land", "dream", "mother", "robot", "tank" };
		System.out.println(Arrays.toString(solution(n, words)));

	}

	private static int[] solution(int n, String[] words) {
		int[] answer = new int[2];
		int idx = 1;
		LinkedHashSet<String> set = new LinkedHashSet<String>();
		String temp = words[0];
		set.add(temp);
		for (int i = 1; i < words.length; i++) {
			if (set.contains(words[i])) { // 있는 단어면
				answer[0] = (i % n) + 1;
				answer[1] = idx;
				break;
			}
			if (temp.charAt(temp.length() - 1) == words[i].charAt(0)) { // 끝말잇기 이어지는 단어면
				set.add(words[i]);
				temp = words[i];
			} else {
				answer[0] = (i % n) + 1;
				answer[1] = idx;
				break;
			}
			if ((i + 1) % n == 0) {
				idx++;
			}
		}

		return answer;

	}
}
