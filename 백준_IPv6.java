import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class 백준_IPv6 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] str = br.readLine().split(":");
		System.out.println(Arrays.toString(str));
		ArrayList<Character> list = new ArrayList<Character>();
		String res = "";
		int cnt = 0;
		boolean flag = false;
		for (int i = 0; i < str.length; i++) {
			if (str[i].equals("")) {
				if (!flag) {
					flag = true;
					for (int j = i + 1; j < str.length; j++) {
						if (!str[j].equals("")) {
							cnt++;
						}
					}
					for (int j = 0; j < 8 - cnt - i; j++) {
						for (int k = 0; k < 4; k++) {
							list.add('0');
						}
						list.add(':');
					}
				}
			} else {
				if (str[i].length() == 4) {
					for (int j = 0; j < str[i].length(); j++) {
						list.add(str[i].charAt(j));
					}
					list.add(':');
				} else {
					for (int j = 0; j < 4 - str[i].length(); j++) {
						list.add('0');
					}
					for (int j = 0; j < str[i].length(); j++) {
						list.add(str[i].charAt(j));
					}
					list.add(':');
				}
			}
		}
		for (int i = 0; i < list.size() - 1; i++) {
			res += list.get(i);
		}
		list.clear();
		String[] r = res.split(":");
		if (r.length < 8) {
			int idx = 8 - r.length;
			for (int i = 0; i < idx; i++) {
				list.add(':');
				for (int j = 0; j < 4; j++) {
					list.add('0');
				}
			}
		}
		for (int i = 0; i < list.size(); i++) {
			res += list.get(i);
		}
		System.out.println(res);

	}
}
