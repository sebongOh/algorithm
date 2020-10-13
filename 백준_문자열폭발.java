import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 백준_문자열폭발 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		String word = br.readLine();
		StringBuilder sb = new StringBuilder();

		while (true) {
			if (str.contains(word)) {
				str = str.replace(word, "");
			} else {
				break;
			}
		}
		System.out.println(str);

	}
}
