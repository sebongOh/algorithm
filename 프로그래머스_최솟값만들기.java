import java.util.Arrays;

public class 프로그래머스_최솟값만들기 {
	public static void main(String[] args) {
		int[] A = {1,4,2};
		int[] B = {5,4,4};
		int answer = solution(A, B);
		System.out.println(answer);
	}

	private static int solution(int[] A, int[] B) {
		int answer = 0;

		Arrays.sort(A);
		Arrays.sort(B);

		int idx = 0;
		for (int i = 0; i < A.length; i++) {
			answer += A[i] * B[B.length - i - 1];
		}

		return answer;
	}
}
