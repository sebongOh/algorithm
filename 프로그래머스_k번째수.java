import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class 프로그래머스_k번째수 {
	public static void main(String[] args) {
		int[] array = { 1, 5, 2, 6, 3, 7, 4 };
		int[][] commands = { { 2, 5, 3 }, { 4, 4, 1 }, { 1, 7, 3 } };
		System.out.println(Arrays.toString(solution(array, commands)));
	}

	private static int[] solution(int[] array, int[][] commands) {
		int[] answer = new int[commands.length];
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < commands.length; i++) {
			list.clear();
			for (int j = commands[i][0] - 1; j < commands[i][1]; j++) {
				list.add(array[j]);
			}
			Collections.sort(list);
			answer[i] = list.get(commands[i][2] - 1);
		}

		return answer;
	}
}
