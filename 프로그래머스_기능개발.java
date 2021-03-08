import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class 프로그래머스_기능개발 {
	public static void main(String[] args) {
		int[] progresses = { 95, 90, 99, 99, 80, 99 };
		int[] speeds = { 1, 1, 1, 1, 1, 1 };
		int[] res = solution(progresses, speeds);
		System.out.println(Arrays.toString(res));
	}

	private static int[] solution(int[] progresses, int[] speeds) {

		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 0; i < progresses.length; i++) {
			if ((100 - progresses[i]) % speeds[i] == 0) {
				q.add((100 - progresses[i]) / speeds[i]);
			} else {
				q.add(((100 - progresses[i]) / speeds[i]) + 1);
			}
		}


		int first = q.poll();
		int idx = 1;
		ArrayList<Integer> list = new ArrayList<Integer>();

		while (!q.isEmpty()) {
			int second = q.poll();
			if (first >= second) {
				idx++;
			} else {
				list.add(idx);
				idx = 1;
				first = second;
			}
		}
		list.add(idx);
		int[] res = new int[list.size()];
		for (int i = 0; i < list.size(); i++) {
			res[i] = list.get(i);
		}

		return res;
	}
}
