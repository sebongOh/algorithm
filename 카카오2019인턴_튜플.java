import java.util.Arrays;
import java.util.HashMap;

public class 카카오2019인턴_튜플 {

	public static void main(String[] args) {

		int[] answer = {};
		String s = "{{2},{2,1},{2,1,3},{2,1,3,4}}";
		String[] str;
		HashMap<String, Integer> h = new HashMap<String, Integer>();
		str = s.split("},{");
		System.out.println(Arrays.toString(str));
		String[] ss;

		for (int i = 0; i < str.length; i++) {
			str[i] = str[i].replace(" ", "");
			str[i] = str[i].replace("{", "");
			str[i] = str[i].replace("}", "");
		}

		for (int i = 0; i < str.length; i++) {
			ss = null;
			ss = str[i].split(",");
			for (int j = 0; j < ss.length; j++) {
				if (ss[j] == " ") {
					continue;
				} else {

				}
			}
		}
	}

}
