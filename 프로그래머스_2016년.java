
public class 프로그래머스_2016년 {
	public static void main(String[] args) {
		int a = 12;
		int b = 31;
		System.out.println(solution(a, b));
	}

	private static String solution(int a, int b) {
		String[] days = { "FRI", "SAT", "SUN", "MON", "TUE", "WED", "THU" };
		int[] month = { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		int cnt = 0;

		for (int i = 0; i < a - 1; i++) {
			cnt += month[i];
		}
		cnt += b;
		cnt--;
		cnt = cnt % 7;
		return days[cnt];
	}
}
