
public class 프로그래머스_멀쩡한사각형 {
	public static void main(String[] args) {
		int w = 12;
		int h = 8;
		long res = solution(w, h);
		System.out.println(res);
	}

	private static long solution(int w, int h) {

		int gcd = findGcd(w, h);
		long res = ((long) w * (long) h) - (((long) w / gcd) + ((long) h / gcd) - 1) * gcd;
		return res;
	}

	private static int findGcd(int w, int h) {
		int min = 0, max = 0, gcd = 0;
		int mok = 0, namuzi = 0;
		if (w > h) {
			min = h;
			max = w;
		} else {
			min = w;
			max = h;
		}

		while (true) {
			mok = max / min;
			namuzi = max - mok * min;

			if (namuzi == 0) {
				gcd = min;
				break;
			}

			max = min;
			min = namuzi;

		}

		return gcd;

	}
}
