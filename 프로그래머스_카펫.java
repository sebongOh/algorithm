import java.util.Arrays;

public class 프로그래머스_카펫 {
	public static void main(String[] args) {
		int brown = 8;
		int yellow = 1;
		int[] ans = solution(brown, yellow);
		System.out.println(Arrays.toString(ans));

	}

	private static int[] solution(int brown, int yellow) {
		int[] answer = new int[2];

		int blockCnt = brown + yellow;
		
		for (int i = 3; i * 3 <= blockCnt; i++) {
			int wid = i;
			int height = blockCnt / i;
			int yellowBlock = (wid - 2) * (height - 2);
			if (blockCnt % i == 0 && yellow == yellowBlock) {
				answer[0] = height;
				answer[1] = wid;
				break;
			}
		}

		return answer;
	}
}
