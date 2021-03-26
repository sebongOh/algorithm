
public class 프로그래머스_N개의최소공배수 {
	public static void main(String[] args) {
		int[] arr = { 2, 6, 8, 14 };
		int res = solution(arr);
		System.out.println(res);
	}

	private static int solution(int[] arr) {
		int res = 0;
		res = arr[0];

		for (int i = 0; i < arr.length; i++) {
			res = solve(res, arr[i]);
		}

		return res;

	}

	private static int solve(int a, int b) {
		return (a * b) / gcd(a, b);
	}

	private static int gcd(int a, int b) {
		while (b != 0) {
			int r = a % b;
			a = b;
			b = r;
		}
		return a;
	}

}
