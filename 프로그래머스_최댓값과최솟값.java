import java.util.Arrays;

public class 프로그래머스_최댓값과최솟값 {
	public static void main(String[] args) {
		String s = "-1 -2 -3 -4";
		String answer = solution(s);
		System.out.println(answer);
		
		
	}

	private static String solution(String s) {
		String answer = "";
		String[] str = s.split(" ");
		int[] arr = new int[str.length];
		for (int i = 0; i < str.length; i++) {
			arr[i] = Integer.parseInt(str[i]);
		}

		Arrays.sort(arr);
		answer += arr[0];
		answer += " ";
		answer += arr[arr.length - 1];

		return answer;
	}
}
