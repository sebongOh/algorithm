import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SWEA_보물상자비밀번호 {
	static int N, K;
	static ArrayList<Character> list = new ArrayList<Character>();
	static Set<String> set = new HashSet<String>();
	static ArrayList<Integer> result = new ArrayList<Integer>();

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int tc = Integer.parseInt(br.readLine());
		String[] str;
		for (int t = 0; t < tc; t++) {
			set.clear();
			list.clear();
			result.clear();
			str = br.readLine().split(" ");
			N = Integer.parseInt(str[0]);
			K = Integer.parseInt(str[1]);
			String s = br.readLine();
			for (int i = 0; i < s.length(); i++) {
				list.add(s.charAt(i));
			}
			for (int i = 0; i < N / 4; i++) {
				rotate();
			}
			Iterator<String> it = set.iterator();
			while (it.hasNext()) {
				result.add(Integer.parseInt(it.next(), 16));
			}
			Collections.sort(result, new Comparator<Integer>() {

				@Override
				public int compare(Integer o1, Integer o2) {
					return o2 - o1;
				}
			});
			
			System.out.println("#"+(t+1)+" "+result.get(K-1));

		} // tc
	}

	private static void rotate() {
		String s = "";
		int idx = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < N / 4; j++) {
				s += list.get(idx);
				idx++;
			}
			set.add(s);
			s = "";
		}

		char ch = list.get(list.size() - 1);
		list.remove(list.size() - 1);
		list.add(0, ch);
	}
}
