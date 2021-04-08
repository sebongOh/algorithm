import java.util.Arrays;
import java.util.Comparator;

public class 프로그래머스_방금그곡 {

	static String[] word = { "C#", "D#", "E#", "F#", "G#", "A#" };
	static String[] lowerWord = { "c", "d", "e", "f", "g", "a" };

	public static void main(String[] args) {
		String m = "ABC";
		String[] musicinfos = { "12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF" };
		String answer = solution(m, musicinfos);
		System.out.println(answer);
	}

	private static String solution(String m, String[] musicinfos) {
		String answer = "None";

		String[][] infos = changeWord(musicinfos);
		String melody = changeWord(m);

		Arrays.sort(infos, new Comparator<String[]>() {
			@Override
			public int compare(String[] o1, String[] o2) {
				int time1 = Integer.parseInt(o1[0]);
				int time2 = Integer.parseInt(o2[0]);
				return time2 - time1;
			}
		});

		for (int i = 0; i < infos.length; i++) {
			if (infos[i][2].contains(melody)) {
				return infos[i][1];
			}
		}

		return answer;
	}

	private static String[][] changeWord(String[] musicinfos) {
		String[][] infos = new String[musicinfos.length][3];

		for (int i = 0; i < musicinfos.length; i++) {
			String[] info = musicinfos[i].split(",");
			String[] start = info[0].split(":");
			String[] end = info[1].split(":");
			int time = getMinutes(start, end);
			String title = info[2];
			String code = info[3];

			for (int j = 0; j < word.length; j++) {
				code = code.replaceAll(word[j], lowerWord[j]);
			}
			String music = "";
			int idx = 0;
			for (int t = 0; t < time; t++) {
				music += code.charAt(idx);
				idx++;
				idx = idx % code.length();
			}

			infos[i][0] = String.valueOf(time);
			infos[i][1] = title;
			infos[i][2] = music;
		}
		return infos;
	}

	private static int getMinutes(String[] start, String[] end) {
		int sHour = Integer.parseInt(start[0]);
		int sM = Integer.parseInt(start[1]);
		int eHour = Integer.parseInt(end[0]);
		int eM = Integer.parseInt(end[1]);

		return (eHour * 60 + eM) - (sHour * 60 + sM);
	}

	private static String changeWord(String m) {
		String res = m;
		for (int i = 0; i < word.length; i++) {
			res = res.replaceAll(word[i], lowerWord[i]);
		}
		return res;
	}
}
