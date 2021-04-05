import java.util.Arrays;

public class 프로그래머스_주식가격 {
	public static void main(String[] args) {
		int[] prices = { 1, 2, 3, 2, 3 };
		int[] arr = solution(prices);
		System.out.println(Arrays.toString(arr));
	}

	private static int[] solution(int[] prices) {
		int[] res = new int[prices.length];
		for (int i = 0; i < prices.length; i++) {
			int price = prices[i];
			for (int j = i + 1; j < prices.length; j++) {
				if (price > prices[j]) {
					res[i] = j - i;
					break;
				}
				if (j == res.length - 1) {
					res[i] = j - i;
				}
			}
		}

		return res;
	}
}