import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class 프로그래머스_오픈채팅방 {
	public static void main(String[] args) {
		String[] record = { "Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234", "Enter uid1234 Prodo",
				"Change uid4567 Ryan" };
		String[] res = solution(record);
		System.out.println(Arrays.toString(res));
	}

	private static String[] solution(String[] record) {

		String[] str;
		String[][] arr = new String[record.length][2];
		Map<String, String> map = new HashMap<String, String>();
		int cnt = 0;
		for (int i = 0; i < record.length; i++) {
			str = record[i].split(" ");
			if (str[0].equals("Enter")) {
				cnt++;
				map.put(str[1], str[2]);
				arr[i][0] = str[1];
				arr[i][1] = "Enter";
			} else if (str[0].equals("Change")) {
				arr[i][0] = str[1];
				arr[i][1] = "Change";
				map.put(str[1], str[2]);
			} else {
				cnt++;
				arr[i][0] = str[1];
				arr[i][1] = "Leave";
			}
		}

		for (String[] s : arr) {
			System.out.println(Arrays.toString(s));
		}

		Iterator<String> keys = map.keySet().iterator();
		while (keys.hasNext()) {
			String key = keys.next();
			System.out.println(String.format("키 : %s, 값 : %s", key, map.get(key)));
		}

		String[] ans = new String[cnt];
		String name;
		int idx = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i][1].equals("Enter")) {
				name = map.get(arr[i][0]);
				ans[idx++] = name + "님이 들어왔습니다.";
			} else if (arr[i][1].equals("Change")) {
				continue;
			} else {
				name = map.get(arr[i][0]);
				ans[idx++] = name + "님이 나갔습니다.";
			}
		}

		return ans;
	}
}
