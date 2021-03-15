import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class 프로그래머스_가장큰수 {
	ArrayList<Integer> list;

	public static void main(String[] args) {
		int[] numbers = { 3, 30, 34, 5, 9 };
		String answer = solution(numbers);
		System.out.println(answer);

	}

	private static String solution(int[] numbers) {
		String res = "";
		String[] num = new String[numbers.length];
		for (int i = 0; i < num.length; i++) {
			num[i] = String.valueOf(numbers[i]);
		}

		Arrays.sort(num, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {

				return ((o2 + o1).compareTo(o1 + o2));
			}
		});

		if (num[0].equals("0")) {
			res = "0";
		} else {
			for (int i = 0; i < num.length; i++) {
				res += num[i];
			}
		}
		return res;
	}

}
