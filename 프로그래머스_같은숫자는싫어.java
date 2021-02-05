import java.util.ArrayList;
import java.util.Arrays;

public class 프로그래머스_같은숫자는싫어 {
	public static void main(String[] args) {
		int[] arr = { 1, 1, 3, 3, 0, 1, 1 };
		int[] res = solution(arr);
		System.out.println(Arrays.toString(res));
	}

	private static int[] solution(int[] arr) {
		int[] ans;
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(arr[0]);
		for (int i = 1; i < arr.length; i++) {
			if (list.get(list.size() - 1) != arr[i]) {
				list.add(arr[i]);
			}
		}
		ans = new int[list.size()];
		for(int i=0;i<ans.length;i++) {
			ans[i] = list.get(i);
		}
		return ans;
	}
}
