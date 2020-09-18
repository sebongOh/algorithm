import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 백준_커맨드 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		String s;
		for (int i = 0; i < t; i++) {
			char[] arr = new char[7];
			s = br.readLine();
			if (s.length() != 7) {
				System.out.println("0");
			} else {
				arr = s.toCharArray();
				if ((arr[0] == arr[1] && arr[1] == arr[4])
						&& (arr[2] == arr[3] && arr[3] == arr[5] && arr[5] == arr[6])) {
					if (arr[0]!=arr[2]) {
						System.out.println("1");
					}else {
						System.out.println("0");
					}
				} else {
					System.out.println("0");
				}
			}
		}

	}
}
