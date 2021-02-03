import java.util.Arrays;

public class 프로그래머스_체육복 {
	public static void main(String[] args) {
		int n = 5;
		int[] lost = { 2, 4 };
		int[] reserve = { 1, 3, 5 };
		int res = solution(n, lost, reserve);
		System.out.println(res);
	}

	private static int solution(int n, int[] lost, int[] reserve) {
		int res = 0;
		int[] arr = new int[n];

		for (int i = 0; i < lost.length; i++) {
			arr[lost[i] - 1]--;
		}
		for (int i = 0; i < reserve.length; i++) {
			arr[reserve[i] - 1]++;
		}

		for (int i = 0; i < arr.length; i++) {
			if (arr[i] < 0) {
				if (i != 0 && arr[i + 1] > 0) {
					arr[i]++;
					arr[i + 1]--;
				} else if (i != arr.length - 1 && arr[i - 1] > 0) {
					arr[i]++;
					arr[i - 1]--;
				}
			}
		}
		
		for (int i = 0; i < arr.length; i++) {
			if (!(arr[i] < 0)) {
				res++;
			}
		}

		return res;
	}
}
