import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SWEA_특이한자석 {
	static int K;
	static ArrayList<Integer>[] list;
	static ArrayList<Integer> turn;
	static int[][] rotation;

	static public void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		String[] str;

		for (int tc = 1; tc <= t; tc++) {
			turn = new ArrayList<Integer>();
			K = Integer.parseInt(br.readLine());
			list = new ArrayList[5];
			for (int i = 1; i < 5; i++) {
				list[i] = new ArrayList<Integer>();
			}
			rotation = new int[K][2];
			for (int i = 1; i < 5; i++) {
				str = br.readLine().split(" ");
				for (int j = 0; j < 8; j++) {
					list[i].add(Integer.parseInt(str[j]));
				}
			}
			for (int i = 0; i < K; i++) {
				str = br.readLine().split(" ");
				rotation[i][0] = Integer.parseInt(str[0]);
				rotation[i][1] = Integer.parseInt(str[1]);
			}

			for (int i = 0; i < K; i++) {
				turn.clear();
				int idx = rotation[i][0];
				int dir = rotation[i][1];
				turn.add(idx);
				for (int j = idx - 1; j > 0; j--) { // 왼쪽
					if (list[j + 1].get(6) == list[j].get(2)) {
						break;
					}
					turn.add(j);
				}
				for (int j = idx + 1; j < 5; j++) { // 왼쪽
					if (list[j - 1].get(2) == list[j].get(6)) {
						break;
					}
					turn.add(j);
				}
				
				for (int j = 0; j < turn.size(); j++) { // 회전하기
					int curdir = 0;
					int cur = turn.get(j);
					if (Math.abs(idx - cur) % 2 == 0) {
						curdir = dir;
					} else {
						curdir = -1 * dir;
					}
					if (curdir == 1) {
						int temp = list[cur].get(list[cur].size() - 1);
						list[cur].add(0, temp);
						list[cur].remove(list[cur].size() - 1);
					} else {
						int temp = list[cur].get(0);
						list[cur].remove(0);
						list[cur].add(temp);
					}
				} // for 회전

			} // K rotation
			int res = 0;
			for (int i = 1; i < 5; i++) {
				if (list[i].get(0) == 1) {
					res += Math.pow(2, i - 1);
				}
			}

			System.out.println("#" + tc + " " + res);

		} // tc
	}// main
}
