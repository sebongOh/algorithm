import java.util.PriorityQueue;

public class 프로그래머스_카카오2018_셔틀버스 {
	static PriorityQueue<Integer> lines;
	static int[] busTime;

	public static void main(String[] args) {
		int n = 2;
		int t = 1;
		int m = 2;
		int res = 0;
		String answer = "";
		String[] timetable = { "09:00", "09:00", "09:00", "09:00" };
		lines = new PriorityQueue<Integer>();
		for (int i = 0; i < timetable.length; i++) {
			lines.add(getMintime(timetable[i]));
		}
		busTime = new int[n];
		busTime[0] = getMintime("09:00");
		for (int i = 1; i < n; i++) {
			busTime[i] = busTime[i - 1] + t;
		}
		int nums = 0;
		int idx = 0;
		while (n-- > 0) { // 버스 한대씩
			nums = m; // 1대당 탈수 있는 사람 수
			int last = 0; // 마지막 탄 사람시간
			while (!lines.isEmpty()) { // 대기인원 있으면
				if (lines.peek() <= busTime[idx] && nums > 0) { // 대기인원이 버스도착시간보다 먼저 왔고 탈 수 있으면
					nums--; // 한명 태우고
					last = lines.poll(); // 대기줄 한명 뺌
				} else { // 버스보다 먼저 온 대기인원 없거나 인원 다찼으면 스톱
					break;
				}
			} // while
			if (n > 0) { // 버스가 마지막이 아니면
				if (lines.isEmpty()) { // 마지막 버스 아닌데 대기인원도 없으면
					res = busTime[busTime.length - 1]; // 마지막 버스 타면 됨
					break;
				}
				idx++; // 마지막 버스아닌데 대기인원 있으면 다음버스로 이동
			} else { // 마지막 버스면
				if (nums > 0) { // 탈 자리가 있으면
					res = busTime[idx]; // 타면 됨
				} else { // 탈자리 없으면
					res = last - 1; // 마지막으로 탄 사람 보다 1분 일찍
				}
				break;
			}
		}
		answer = getStringtime(res);
		System.out.println(answer);

	}

	private static String getStringtime(int res) {
		String Hour = "";
		String Min = "";
		if (res / 60 < 10) {
			Hour = "0" + String.valueOf(res / 60);
		} else {
			Hour = String.valueOf(res / 60);
		}
		if (res % 60 < 10) {
			Min = "0" + String.valueOf(res % 60);
		} else {
			Min = String.valueOf(res % 60);
		}
		return Hour + ":" + Min;
	}

	private static int getMintime(String time) {
		int Min = 0;
		String[] times = time.split(":");
		Min += Integer.parseInt(times[0]) * 60;
		Min += Integer.parseInt(times[1]);
		return Min;

	}
}
